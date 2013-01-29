package org.kornel.code.impl;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kornel.code.Code;
import org.kornel.color.ColorGenerator;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CodeGeneratorImplTest {

    @Mock
    private ColorGenerator colorGenerator;

    @InjectMocks
    private CodeGeneratorImpl codeGeneratorImpl;

    @Test
    public void shouldGenerateCodeOfProperLength() {
        // given
        final int length = 5;
        final int maxColor = 1;
        final int constantColor = 7;
        given(colorGenerator.getRandomColor(maxColor)).willReturn(constantColor);

        // when
        final Code code = codeGeneratorImpl.generate(length, maxColor);

        // then
        assertThat(code.getSize()).isEqualTo(length);
        assertThat(code.getColor(0)).isEqualTo(constantColor);
        assertThat(code.getColor(1)).isEqualTo(constantColor);
        assertThat(code.getColor(2)).isEqualTo(constantColor);
        assertThat(code.getColor(3)).isEqualTo(constantColor);
        assertThat(code.getColor(4)).isEqualTo(constantColor);
    }
}
