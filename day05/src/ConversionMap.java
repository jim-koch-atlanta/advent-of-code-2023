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
}
