public enum CardRank {
    TWO('2', 2),
    THREE('3', 3),
    FOUR('4', 4),
    FIVE('5', 5),
    SIX('6', 6),
    SEVEN('7', 7),
    EIGHT('8', 8),
    NINE('9', 9),
    TEN('T', 10),
    JACK('J', 11),
    QUEEN('Q', 12),
    KING('K', 13),
    ACE('A', 14);

    private final char symbol;
    private final int rankValue;

    CardRank(char symbol, int rankValue) {
        this.symbol = symbol;
        this.rankValue = rankValue;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getRankValue() {
        return rankValue;
    }

    public static CardRank fromChar(char c) {
        for (CardRank rank : CardRank.values()) {
            if (rank.symbol == c) {
                return rank;
            }
        }
        throw new IllegalArgumentException("Invalid card rank: " + c);
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
