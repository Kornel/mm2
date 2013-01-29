package org.kornel.code.impl;

import java.util.HashMap;
import java.util.Map;

import org.kornel.code.Code;
import org.kornel.code.CodeEvaluator;
import org.kornel.code.CodeResult;

//TODO: Implement cache clearing, make this class work. Now it is less efficient then the regular ?!
public final class CachingCodeEvaluatorImpl implements CodeEvaluator {

    private final CodeEvaluator codeEvaluator = new CodeEvaluatorImpl();

    private final Map<Code, Map<Code, CodeResult>> cache = new HashMap<>();

    public int x = 0;

    @Override
    public CodeResult evalute(final Code code, final Code secret) {

        if (!cache.containsKey(secret)) {
            cache.put(secret, new HashMap<Code, CodeResult>());
        }

        final Map<Code, CodeResult> codesForSecret = cache.get(secret);

        final CodeResult result;
        if (codesForSecret.containsKey(code)) {
            result = codesForSecret.get(code);
            x++;
        } else {
            result = codeEvaluator.evalute(code, secret);
            codesForSecret.put(code, result);
        }

        return result;
    }

}
