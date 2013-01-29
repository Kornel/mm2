package org.kornel.assertions;

import org.kornel.code.CodeResult;

public class Assertions {

    public static CodeResultAssertion assertThat(final CodeResult codeResult) {
        return new CodeResultAssertion(codeResult);
    }

}
