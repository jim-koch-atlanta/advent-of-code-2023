import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ConversionMap {
    List<ConversionMapElement> elements;

    public ConversionMap() {
        elements = new Vector<>();
    }

    public void addElement(Long destinationRangeStart, Long sourceRangeStart, Long rangeLength) {
        ConversionMapElement element = new ConversionMapElement(destinationRangeStart, sourceRangeStart, rangeLength);
        this.elements.add(element);
    }
    public Long convert(Long value) {
        for (ConversionMapElement element : elements) {
            if (element.contains(value)) {
                return element.convert(value);
            }
        }

        return value;
    }

    public List<Pair<Long, Long>> convert(List<Pair<Long, Long>> currentRanges) {
        List<Pair<Long, Long>> newRanges = new ArrayList<>();

        while (!currentRanges.isEmpty()) {
            Pair<Long, Long> currentRange = currentRanges.getFirst();
            currentRanges.removeFirst();

            boolean foundIntersection = false;
            for (ConversionMapElement element : elements) {
                Pair<Long, Long> intersection = element.getIntersection(currentRange.getKey(), currentRange.getValue());

                // If there's an intersection, add it to newRanges. Then see if any values fell outside of this
                // ConversionMapElement's range.
                if (intersection != null) {
                    foundIntersection = true;
                    newRanges.add(element.convert(intersection.getKey(), intersection.getValue()));
                    if (!intersection.getKey().equals(currentRange.getKey()) ||
                            !intersection.getValue().equals(currentRange.getValue())) {
                        if (currentRange.getKey() < intersection.getKey()) {
                            currentRanges.addLast(new Pair<Long, Long>(currentRange.getKey(), intersection.getKey() - 1));
                        }
                        if (currentRange.getValue() > intersection.getKey()) {
                            currentRanges.addLast(new Pair<Long, Long>(intersection.getValue() + 1, currentRange.getValue()));
                        }
                    }
                }
            }

            if (!foundIntersection) {
                newRanges.add(currentRange);
            }
        }

        return newRanges;
    }
}
