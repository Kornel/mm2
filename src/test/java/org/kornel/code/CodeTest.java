package org.kornel.code;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

public class CodeTest {

    @Test(expected = IllegalStateException.class)
    public void shouldRefuseMatchingWhenCodeSizesDiffer() {
        // given
        final Code code = new Code(new int[] { 1 });
        final Code secret = new Code(new int[] { 1, 2 });

        // when
        code.matchesAt(0, secret);

        // then
        // exception
    }

    @Test
    public void shouldReportColorMatch() {
        // given
        final Code code = new Code(new int[] { 1 });
        final Code secret = new Code(new int[] { 1 });

        // when
        final boolean matches = code.matchesAt(0, secret);

        // then
        assertThat(matches).isTrue();
    }

    @Test(expected = IllegalStateException.class)
    public void souldFailWhenRequestedIndexIsBelowZero() {
        // given
        final Code code = new Code(new int[] { 1, 2, 3 });

        // when
        code.getColor(-1);

        // then
        // exception
    }

    @Test(expected = IllegalStateException.class)
    public void souldFailWhenRequestedIndexIsTooHigh() {
        // given
        final Code code = new Code(new int[] { 1 });

        // when
        code.getColor(1);

        // then
        // exception
    }

    @Test
    public void souldReturnCodeLength() {
        // given
        final Code code = new Code(new int[] { 1, 2, 3 });

        // when
        final int size = code.getSize();

        // then
        assertThat(size).isEqualTo(size);

    }

    @Test
    public void souldReturnValidCode() {
        // given
        final Code code = new Code(new int[] { 1, 2, 3 });

        // when
        final int color1 = code.getColor(0);
        final int color2 = code.getColor(1);
        final int color3 = code.getColor(2);

        // then
        assertThat(color1).isEqualTo(1);
        assertThat(color2).isEqualTo(2);
        assertThat(color3).isEqualTo(3);
    }

}
