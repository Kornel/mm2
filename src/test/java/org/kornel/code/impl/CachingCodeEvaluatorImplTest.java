package org.kornel.code.impl;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;
import org.kornel.code.Code;
import org.kornel.code.CodeResult;

public class CachingCodeEvaluatorImplTest {

    private final CachingCodeEvaluatorImpl cachingEvaluator = new CachingCodeEvaluatorImpl();

    @Test
    public void shouldReturnTheSameValueForSecondAndThirdCall() {
        // given
        final Code code = new Code(1, 2, 3);
        final Code secret = new Code(1, 2, 3);
        final CodeResult firstEval = cachingEvaluator.evalute(code, secret);

        // when
        final CodeResult secondEval = cachingEvaluator.evalute(code, secret);
        final CodeResult thirdEval = cachingEvaluator.evalute(code, secret);

        // then
        assertThat(firstEval).isSameAs(secondEval).isSameAs(thirdEval);
    }

}
