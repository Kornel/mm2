package org.kornel.code.impl;

import static com.google.common.base.Preconditions.checkState;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

import org.kornel.code.Code;
import org.kornel.code.CodeGenerator;

public final class NonRepeatingGeneratorImpl implements CodeGenerator {

    private final Stack<Code> codesAvailable = new Stack<>();
    private final int length;
    private final int maxColor;

    private final LinkedList<Integer[]> guesses;

    public NonRepeatingGeneratorImpl(final int length, final int maxColor) {
        this.length = length;
        this.maxColor = maxColor;

        this.guesses = new LinkedList<>();
        generate(guesses);
    }

    @Override
    public Code generate(final int length, final int maxColor) {
        checkState(!codesAvailable.isEmpty());
        return codesAvailable.pop();
    }

    private void generate(final Integer[] array, final int i, final LinkedList<Integer[]> guesses) {

        if (i >= array.length) {
            return;
        }

        for (int c = 0; c < maxColor; ++c) {
            final Integer[] next = array.clone();
            next[i] = c;

            if (i == length - 1) {
                guesses.add(next);
            }

            generate(next.clone(), i + 1, guesses);
        }
    }

    public void generate(final LinkedList<Integer[]> guesses) {
        final Integer[] array = new Integer[length];
        for (int i = 0; i < length; ++i) {
            array[i] = 0;
        }
        generate(array.clone(), 0, guesses);
    }

    public void init() {
        codesAvailable.clear();
        Collections.shuffle(guesses);
        for (final Integer[] guess : guesses) {
            codesAvailable.add(new Code(guess));
        }

    }
}
