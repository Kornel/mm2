package org.kornel.code.impl;

import org.kornel.code.Code;
import org.kornel.code.CodeGenerator;
import org.kornel.color.ColorGenerator;

public final class CodeGeneratorImpl implements CodeGenerator {

    private final ColorGenerator colorGenerator;

    public CodeGeneratorImpl(final ColorGenerator colorGenerator) {
        this.colorGenerator = colorGenerator;
    }

    @Override
    public Code generate(final int length, final int maxColor) {

        final Integer[] result = new Integer[length];
        for (int i = 0; i < length; ++i) {
            result[i] = colorGenerator.getRandomColor(maxColor);
        }

        return new Code(result);
    }

}
