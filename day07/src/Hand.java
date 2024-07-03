import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hand implements Comparable<Hand> {
    private List<CardRank> cards;
    private Long bid;

    public Hand(String hand) {
        this.cards = new ArrayList<>();
        String[] split = hand.split(" ");
        for (char c : split[0].toCharArray()) {
            CardRank card = CardRank.fromChar(c);
            cards.add(card);
        }

        this.bid = Long.valueOf(split[1]);
    }

    public HandType determineHandType() {
        Map<CardRank, Integer> rankCounts = new HashMap<>();

        // Count occurrences of each rank
        for (CardRank card : this.cards) {
            rankCounts.put(card, rankCounts.getOrDefault(card, 0) + 1);
        }

        // Jacks are wild.
        Integer jackCount = 0;
        if (rankCounts.containsKey(CardRank.JACK) && (rankCounts.get(CardRank.JACK) != 5)) {
            jackCount = rankCounts.get(CardRank.JACK);
            rankCounts.remove(CardRank.JACK);
        }

        // Use the jacks for the max card that is the max count.
        int maxCount = 0;
        CardRank maxCountCard = null;
        for (var entry : rankCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                maxCountCard = entry.getKey();
            } else if ((entry.getValue() == maxCount) &&
                       ((maxCountCard == null) ||
                        (entry.getKey().getRankValue() > maxCountCard.getRankValue()))) {
                maxCountCard = entry.getKey();
            }
        }

        if (maxCountCard != null) {
            rankCounts.put(maxCountCard, maxCount + jackCount);
        }

        // Determine the hand type based on counts
        boolean threeOfAKind = false;
        boolean pair = false;
        maxCount = 0;
        int secondMaxCount = 0;

        for (int count : rankCounts.values()) {
            if (count > maxCount) {
                secondMaxCount = maxCount;
                maxCount = count;
            } else if (count > secondMaxCount) {
                secondMaxCount = count;
            }
            if (count == 3) threeOfAKind = true;
            if (count == 2) pair = true;
        }

        if (maxCount == 5) return HandType.FIVE_OF_A_KIND;
        if (maxCount == 4) return HandType.FOUR_OF_A_KIND;
        if (maxCount == 3 && secondMaxCount == 2) return HandType.FULL_HOUSE;
        if (maxCount == 3) return HandType.THREE_OF_A_KIND;
        if (maxCount == 2 && secondMaxCount == 2) return HandType.TWO_PAIR;
        if (maxCount == 2) return HandType.ONE_PAIR;

        return HandType.HIGH_CARD;
    }

    public Long getBid() {
        return this.bid;
    }

    @Override
    public int compareTo(Hand other) {
        int compare = this.determineHandType().compareTo(other.determineHandType());
        if (compare != 0) {
            return compare;
        }

        for (int i = 0; i < this.cards.size(); i++) {
            CardRank thisCard = this.cards.get(i);
            CardRank otherCard = other.cards.get(i);

            compare = thisCard.compareTo(otherCard);
            if (compare != 0) {
                return compare;
            }
        }

        // Define natural ordering logic
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (CardRank cr : this.cards) {
            result.append(cr.getSymbol());
        }

        return result.toString();
    }
}
