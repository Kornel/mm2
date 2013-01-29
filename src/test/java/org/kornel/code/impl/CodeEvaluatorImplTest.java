package org.kornel.code.impl;

import static org.kornel.assertions.Assertions.assertThat;

import org.junit.Test;
import org.kornel.code.Code;
import org.kornel.code.CodeResult;

public class CodeEvaluatorImplTest {

    private final CodeEvaluatorImpl evaluator = new CodeEvaluatorImpl();

    @Test
    public void shouldReportAllBlack() {
        // given
        final Code code = new Code(new int[] { 1, 2, 3, 4 });
        final Code secret = new Code(new int[] { 1, 2, 3, 4 });

        // when
        final CodeResult result = evaluator.evalute(code, secret);

        // then
        assertThat(result).hasBlack(4).hasWhite(0);
    }

    @Test
    public void shouldReportAllWhite() {
        // given
        final Code code = new Code(new int[] { 1, 2, 3, 4 });
        final Code secret = new Code(new int[] { 4, 3, 2, 1 });

        // when
        final CodeResult result = evaluator.evalute(code, secret);

        // then
        assertThat(result).hasBlack(0).hasWhite(4);
    }

    @Test
    public void shouldReportOneBlack() {
        // given
        final Code code = new Code(new int[] { 1, 2, 3, 4 });
        final Code secret = new Code(new int[] { 1, 0, 0, 0 });

        // when
        final CodeResult result = evaluator.evalute(code, secret);

        // then
        assertThat(result).hasBlack(1).hasWhite(0);
    }

    @Test
    public void shouldReportOneBlackOneWhite() {
        // given
        final Code code = new Code(new int[] { 1, 1, 2, 1 });
        final Code secret = new Code(new int[] { 1, 2, 0, 0 });

        // when
        final CodeResult result = evaluator.evalute(code, secret);

        // then
        assertThat(result).hasBlack(1).hasWhite(1);
    }

    @Test
    public void shouldReportOneWhite() {
        // given
        final Code code = new Code(new int[] { 1, 2, 3, 4 });
        final Code secret = new Code(new int[] { 0, 1, 0, 1 });

        // when
        final CodeResult result = evaluator.evalute(code, secret);

        // then
        assertThat(result).hasBlack(0).hasWhite(1);
    }

    @Test
    public void shouldReportTwoBlackAndOneWhite() {
        // given
        final Code code = new Code(new int[] { 1, 2, 3, 5 });
        final Code secret = new Code(new int[] { 1, 2, 4, 3 });

        // when
        final CodeResult result = evaluator.evalute(code, secret);

        // then
        assertThat(result).hasBlack(2).hasWhite(1);
    }

    @Test
    public void shouldReportTwoBlackAndTwoWhite() {
        // given
        final Code code = new Code(new int[] { 1, 2, 3, 5 });
        final Code secret = new Code(new int[] { 1, 2, 5, 3 });

        // when
        final CodeResult result = evaluator.evalute(code, secret);

        // then
        assertThat(result).hasBlack(2).hasWhite(2);
    }

    @Test
    public void shouldReportTwoWhiteAndOneBlack() {
        // given
        final Code code = new Code(new int[] { 1, 2, 2, 4 });
        final Code secret = new Code(new int[] { 4, 3, 2, 1 });

        // when
        final CodeResult result = evaluator.evalute(code, secret);

        // then
        assertThat(result).hasBlack(1).hasWhite(2);
    }
}
