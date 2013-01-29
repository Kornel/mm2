package org.kornel.color.impl;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RandomColorGeneratorImplTest {

    @InjectMocks
    private RandomColorGeneratorImpl randomGenerator;

    @Mock
    private Random random;

    @Test
    public void shouldCallRandom() {
        // given
        final int maxAllowed = 10;
        final Integer result = 50;
        given(random.nextInt(maxAllowed)).willReturn(result);

        // when
        final int randomColor = randomGenerator.getRandomColor(maxAllowed);

        // then
        assertThat(randomColor).isEqualTo(result);
        verify(random).nextInt(maxAllowed);
    }

}
