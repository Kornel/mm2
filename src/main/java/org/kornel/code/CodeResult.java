package org.kornel.code;

import java.text.MessageFormat;

public final class CodeResult {

    private final int black;
    private final int white;

    public CodeResult(final int black, final int white) {
        this.black = black;
        this.white = white;
    }

    @Override
    public boolean equals(final Object obj) {

        final boolean result;
        if (obj == this) {
            result = true;
        } else if (obj == null) {
            result = false;
        } else if (obj.getClass().equals(CodeResult.class)) {
            final CodeResult that = (CodeResult) obj;
            result = this.black == that.black && this.white == that.white;
        } else {
            result = false;
        }
        return result;
    }

    public int getBlack() {
        return black;
    }

    public int getWhite() {
        return white;
    }

    @Override
    public String toString() {
        return MessageFormat.format("Black: {0}, White: {1}", black, white);
    }

}
