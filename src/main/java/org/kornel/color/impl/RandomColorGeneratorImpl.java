package org.kornel.color.impl;

import java.util.Random;

import org.kornel.color.ColorGenerator;

public final class RandomColorGeneratorImpl implements ColorGenerator {

    private final Random random;

    public RandomColorGeneratorImpl(final Random random) {
        this.random = random;
    }

    @Override
    public int getRandomColor(final int maxAllowed) {
        return random.nextInt(maxAllowed);
    }

}
