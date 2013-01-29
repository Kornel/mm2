package org.kornel.game;

import java.util.LinkedList;
import java.util.Random;

import org.kornel.code.Code;
import org.kornel.code.CodeEvaluator;
import org.kornel.code.CodeGenerator;
import org.kornel.code.CodeResult;
import org.kornel.code.impl.CachingCodeEvaluatorImpl;
import org.kornel.code.impl.CodeEvaluatorImpl;
import org.kornel.code.impl.CodeGeneratorImpl;
import org.kornel.code.impl.NonRepeatingGeneratorImpl;
import org.kornel.color.ColorGenerator;
import org.kornel.color.impl.RandomColorGeneratorImpl;
import org.kornel.game.GameResult.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Game {

    private final static Logger log = LoggerFactory.getLogger(Game.class);

    private final static int LENGTH = 5;
    private final static int MAXCOLOR = 6;

    public static void main(final String[] args) {
        log.info("Playing with regular evaluator");
        new Game(new CodeEvaluatorImpl()).playMultiple();
        log.info("Playing with caching evaluator");
        new Game(new CachingCodeEvaluatorImpl()).playMultiple();
    }

    private final ColorGenerator colorGenerator = new RandomColorGeneratorImpl(new Random());

    private final CodeGenerator codeGenerator = new CodeGeneratorImpl(colorGenerator);

    private final NonRepeatingGeneratorImpl nonRepeatingGenerator = new NonRepeatingGeneratorImpl(LENGTH, MAXCOLOR);
    // private final CachingCodeEvaluatorImpl codeEvaluator = new
    // CachingCodeEvaluatorImpl();
    private final CodeEvaluator codeEvaluator;

    private Game(final CodeEvaluator evaluator) {
        this.codeEvaluator = evaluator;
    }

    public GameResult play() {

        nonRepeatingGenerator.init();

        final Code secret = codeGenerator.generate(LENGTH, MAXCOLOR);

        log.debug("Secret: {}", secret);

        final long nanoTime1 = System.nanoTime();

        final Builder solveBuilder = solve(LENGTH, MAXCOLOR, secret);

        final long nanoTime2 = System.nanoTime();

        final long nanoDiff = nanoTime2 - nanoTime1;

        solveBuilder.nanoSeconds(nanoDiff);

        log.debug("Solving took {} nanoseconds which is {} seconds", nanoDiff, nanoDiff / (double) 1000000000);

        return solveBuilder.build();
    }

    public void playMultiple() {
        long nanoAvg = 0;
        int guessesAvg = 0;

        final int total = 1000;

        for (int i = 0; i < total; ++i) {
            final GameResult play = play();

            nanoAvg += play.getNanoSeconds();
            guessesAvg += play.getGuesses();

            if (i % 100 == 0) {
                log.info("Game {} out of {}", i + 1, total);
            }
        }

        log.info("Average time: {} seconds", nanoAvg / (double) total / 1000000000);
        log.info("Average guesses: {}", guessesAvg / (double) total);
        // log.info("X: {}", codeEvaluator.x);
    }

    private GameResult.Builder solve(final int length, final int maxColor, final Code secret) {
        int n = 0;

        final GameResult.Builder builder = new GameResult.Builder();

        final LinkedList<Code> guesses = new LinkedList<>();

        do {

            Code code;
            boolean done = false;
            do {
                code = nonRepeatingGenerator.generate(length, maxColor);

                int equal = 0;
                for (final Code guess : guesses) {
                    final CodeResult baseEval = codeEvaluator.evalute(guess, secret);
                    final CodeResult guessEval = codeEvaluator.evalute(code, guess);
                    if (guessEval.equals(baseEval)) {
                        equal++;
                    }
                }
                if (equal == guesses.size()) {
                    done = true;
                }

            } while (!done);

            guesses.add(code);

            final CodeResult result = codeEvaluator.evalute(code, secret);

            log.debug("Guess[{}] after {} has result {}", n, code, result);

            if (result.getBlack() == length) {
                log.debug("Found solution for {} as {} after {} tries", secret, code, n);
                break;
            }
        } while (n++ < 1000);

        return builder.guesses(n);
    }
}
