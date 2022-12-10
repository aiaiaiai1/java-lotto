package lotto.domain;

public enum Rank {
    FIRST(6, 0),
    SECOND(5, 1),
    THIRD(5, 0),
    FOURTH(4, 0),
    FIFTH(3, 0),
    NONE(0, 0);

    private final int countOfMatch;
    private final int countOfBonusMatch;

    Rank(int countOfMatch, int countOfBonusMatch) {
        this.countOfMatch = countOfMatch;
        this.countOfBonusMatch = countOfBonusMatch;
    }

    public static Rank of(int countOfMatch, int countOfBonusMatch) {
        int validatedCountOfMatch = validateCountOfMatch(countOfMatch);
        int validatedCountOfBonusMatch = validateCountOfBonusMatch(countOfMatch, countOfBonusMatch);
        for (Rank rank : Rank.values()) {
            if (rank.isSame(validatedCountOfMatch, validatedCountOfBonusMatch))
                return rank;
        }
        throw new IllegalArgumentException();
    }

    private boolean isSame(int validatedCountOfMatch, int validatedCountOfBonusMatch) {
        return (this.countOfMatch == validatedCountOfMatch && this.countOfBonusMatch == validatedCountOfBonusMatch);
    }

    private static int validateCountOfBonusMatch(int countOfMatch, int countOfBonusMatch) {
        if (countOfBonusMatch < NONE.countOfBonusMatch || countOfBonusMatch > SECOND.countOfBonusMatch) {
            throw new IllegalArgumentException();
        }
        if (countOfMatch != SECOND.countOfMatch) {
            return NONE.countOfBonusMatch;
        }
        return countOfBonusMatch;
    }

    private static int validateCountOfMatch(int countOfMatch) {
        if (countOfMatch < NONE.countOfMatch || countOfMatch > FIRST.countOfMatch) {
            throw new IllegalArgumentException();
        }
        if (countOfMatch < FIFTH.countOfMatch) {
            return NONE.countOfMatch;
        }
        return countOfMatch;
    }
}
