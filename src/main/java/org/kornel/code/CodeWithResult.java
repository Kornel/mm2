package org.kornel.code;

public final class CodeWithResult {

    private final Code code;
    private final CodeResult codeResult;

    public CodeWithResult(final Code code, final CodeResult codeResult) {
        this.code = code;
        this.codeResult = codeResult;
    }

    public Code getCode() {
        return code;
    }

    public CodeResult getCodeResult() {
        return codeResult;
    }

}
