package org.kornel.code;

import static com.google.common.base.Preconditions.checkState;

import java.util.Arrays;

public final class Code {

    private final int[] code;

    public Code(final int[] code) {
        this.code = code;
    }

    public Code(final Integer[] code) {
        this.code = new int[code.length];
        for (int i = 0; i < code.length; ++i) {
            this.code[i] = code[i];
        }
    }

    @Override
    public boolean equals(final Object obj) {

        final boolean result;
        if (obj == this) {
            result = true;
        } else if (obj == null) {
            result = false;
        } else if (obj.getClass().equals(Code.class)) {
            result = Arrays.equals(code, (int[]) obj);
        } else {
            result = false;
        }
        return result;
    }

    public int getColor(final int idx) {
        checkState(idx < code.length && idx >= 0);
        return code[idx];
    }

    public int getSize() {
        return code.length;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(code);
    }

    public boolean matchesAt(final int position, final Code other) {
        checkState(other.getSize() == getSize());
        return other.getColor(position) == code[position];
    }

    @Override
    public String toString() {
        return Arrays.toString(code);
    }
}
