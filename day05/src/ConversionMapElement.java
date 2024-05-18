public class ConversionMapElement {
    Long destinationRangeStart;
    Long sourceRangeStart;
    Long rangeLength;

    public ConversionMapElement(Long destinationRangeStart, Long sourceRangeStart, Long rangeLength) {
        this.destinationRangeStart = destinationRangeStart;
        this.sourceRangeStart = sourceRangeStart;
        this.rangeLength = rangeLength;
    }

    public boolean contains(Long value) {
        return ((value >= sourceRangeStart) && (value < (sourceRangeStart + rangeLength)));
    }

    public Long convert(Long value) {
        return value - sourceRangeStart + destinationRangeStart ;
    }

    public boolean intersects(Long lower, Long upper) {
        return ((lower <= sourceRangeStart + rangeLength - 1) && (sourceRangeStart <= upper));
    }

    public Pair<Long, Long> convert(Long lower, Long upper) {
        Long intersectingLower = Math.max(lower, sourceRangeStart);
        Long intersectingUpper = Math.min(upper, sourceRangeStart + rangeLength - 1);
        return new Pair<>(convert(intersectingLower), convert(intersectingUpper));
    }
}
