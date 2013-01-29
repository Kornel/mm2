package org.kornel.assertions;

import org.fest.assertions.internal.Failures;
import org.kornel.code.CodeResult;

public class CodeResultAssertion {

    private final CodeResult result;

    public CodeResultAssertion(final CodeResult result) {
        this.result = result;
    }

    public CodeResultAssertion hasBlack(final int black) {
        if (result.getBlack() != black) {
            throw Failures.instance().failure("Expected " + black + " black but got " + result.getBlack());
        }
        return this;
    }

    public CodeResultAssertion hasWhite(final int white) {
        if (result.getWhite() != white) {
            throw Failures.instance().failure("Expected " + white + " white but got " + result.getWhite());
        }
        return this;
    }
}
