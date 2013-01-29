package org.kornel.game;

public final class GameResult {

    public static final class Builder {
        private long nanoSeconds;
        private int guesses;

        public GameResult build() {
            return new GameResult(this);
        }

        public Builder guesses(final int guesses) {
            this.guesses = guesses;
            return this;
        }

        public Builder nanoSeconds(final long nanoSeconds) {
            this.nanoSeconds = nanoSeconds;
            return this;
        }
    }

    private final long nanoSeconds;

    private final int guesses;

    private GameResult(final Builder builder) {
        this.nanoSeconds = builder.nanoSeconds;
        this.guesses = builder.guesses;
    }

    public int getGuesses() {
        return guesses;
    }

    public long getNanoSeconds() {
        return nanoSeconds;
    }
}
