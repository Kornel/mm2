package org.kornel.code.impl;

import java.util.Set;

import org.kornel.code.Code;
import org.kornel.code.CodeEvaluator;
import org.kornel.code.CodeResult;

import com.google.common.collect.Sets;

public final class CodeEvaluatorImpl implements CodeEvaluator {

    @Override
    public CodeResult evalute(final Code code, final Code secret) {

        int black = 0;

        final Set<Integer> rest = Sets.newHashSet();
        final Set<Integer> restS = Sets.newHashSet();

        for (int i = 0; i < code.getSize(); ++i) {
            if (code.matchesAt(i, secret)) {
                black++;
            } else {
                rest.add(code.getColor(i));
                restS.add(secret.getColor(i));
            }
        }

        rest.retainAll(restS);

        return new CodeResult(black, rest.size());
    }

    @Override
    public String getStats() {
        return "";
    }

}
