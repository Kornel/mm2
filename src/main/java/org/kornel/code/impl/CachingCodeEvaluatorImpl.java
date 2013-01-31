package org.kornel.code.impl;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.kornel.code.Code;
import org.kornel.code.CodeEvaluator;
import org.kornel.code.CodeResult;

//TODO: Implement cache clearing, make this class work. Now it is less efficient then the regular ?!
public final class CachingCodeEvaluatorImpl implements CodeEvaluator {

    private final CodeEvaluator codeEvaluator = new CodeEvaluatorImpl();

    private final Map<Code, Map<Code, CodeResult>> cache = new HashMap<>();

    private int hit = 0;
    private int miss = 0;
    private int secretHit = 0;

    @Override
    public CodeResult evalute(final Code code, final Code secret) {

        if (!cache.containsKey(secret)) {
            if (secretHit == 1) {
                throw new IllegalStateException();
            }
            cache.put(secret, new HashMap<Code, CodeResult>());
            secretHit++;
        }

        final Map<Code, CodeResult> codesForSecret = cache.get(secret);

        final CodeResult result;
        if (codesForSecret.containsKey(code)) {
            result = codesForSecret.get(code);
            hit++;
        } else {
            result = codeEvaluator.evalute(code, secret);
            codesForSecret.put(code, result);
            miss++;
        }

        return result;
    }

    @Override
    public String getStats() {
        return MessageFormat.format("Cache hits {0}, cache misses {1}", hit, miss);
    }

}
