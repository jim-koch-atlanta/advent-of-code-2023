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
}
