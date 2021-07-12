import java.util.List;
import java.util.ArrayList;

// Class for immutable objects
// It simulates a safe for storing jewelry.
public final class Strongbox {
    private final List<Double> jewelryPrices;
    private final String owner;

    public Strongbox(String owner, List<Double> jewelryPrices) {
        this.owner = owner;
        this.jewelryPrices = new ArrayList<>();
        this.jewelryPrices.addAll(jewelryPrices);
    }

    public String getOwner() {
        return owner;
    }

    public List<Double> getJewelryPrices() {
        List<Double> copy = new ArrayList<>();
        copy.addAll(jewelryPrices);
        return copy;
    }
}
