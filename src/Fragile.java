// Fragile.java
public class Fragile extends Article {
    private boolean emballage;

    public Fragile(String marque, double prixHT, String paysDestination, boolean emballage) {
        super(marque, prixHT, paysDestination);
        this.emballage = emballage;
    }

    public Fragile(String marque, double prixHT, String paysDestination) {
        super(marque, prixHT, paysDestination);
        this.emballage = false;
    }

    @Override
    public double prixTransport() throws PRTInfA10Exception {
        // Double the transport price for fragile items
        return 2 * super.prixTransport();
    }

    // Getter for emballage
    public boolean hasEmballage() {
        return emballage;
    }

    @Override
    public String toString() {
        return "Fragile [marque=" + marque + ", prixHT=" + prixHT + ", paysDestination=" + getPaysDestination() + ", emballage=" + emballage + "]";
    }
}
