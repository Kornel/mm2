package org.kornel.code;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

public class CodeResultTest {

    @Test
    public void shouldBeEqual() {
        // given
        final CodeResult cr1 = new CodeResult(1, 20);
        final CodeResult cr2 = new CodeResult(1, 20);

        // when
        final boolean equals = cr1.equals(cr2);

        // then
        assertThat(equals).isTrue();
    }

    @Test
    public void shouldBeEqualForSameObject() {
        // given
        final CodeResult cr1 = new CodeResult(20, 20);

        // when
        final boolean equals = cr1.equals(cr1);

        // then
        assertThat(equals).isTrue();
    }

    @Test
    public void shouldNotBeEqualForDifferentBlack() {
        // given
        final CodeResult cr1 = new CodeResult(1, 20);
        final CodeResult cr2 = new CodeResult(20, 20);

        // when
        final boolean equals = cr1.equals(cr2);

        // then
        assertThat(equals).isFalse();
    }

    @Test
    public void shouldNotBeEqualForDifferentBlackAndWhite() {
        // given
        final CodeResult cr1 = new CodeResult(1, 20);
        final CodeResult cr2 = new CodeResult(20, 1);

        // when
        final boolean equals = cr1.equals(cr2);

        // then
        assertThat(equals).isFalse();
    }

    @Test
    public void shouldNotBeEqualForDifferentClass() {
        // given
        final CodeResult cr1 = new CodeResult(20, 20);

        // when
        final boolean equals = cr1.equals("X");

        // then
        assertThat(equals).isFalse();
    }

    @Test
    public void shouldNotBeEqualForDifferentWhite() {
        // given
        final CodeResult cr1 = new CodeResult(20, 20);
        final CodeResult cr2 = new CodeResult(20, 10);

        // when
        final boolean equals = cr1.equals(cr2);

        // then
        assertThat(equals).isFalse();
    }

    @Test
    public void shouldNotBeEqualForNull() {
        // given
        final CodeResult cr1 = new CodeResult(20, 20);
        final CodeResult cr2 = null;

        // when
        final boolean equals = cr1.equals(cr2);

        // then
        assertThat(equals).isFalse();
    }

}
