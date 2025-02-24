public class Fragile extends Article {
    private boolean emballage;

    public Fragile(String marque, double prixHT, String paysDestination, boolean emballage) {
        super(marque, prixHT, paysDestination);
        this.emballage = emballage;
        this.fragile = true;
    }

    public boolean hasEmballage() {
        return emballage;
    }

    @Override
    public double prixTransport() {
        return emballage ? super.prixTransport() + 50 : super.prixTransport();
    }

    @Override
    public String toString() {
        return "Fragile [marque=" + marque + ", prixHT=" + prixHT + ", paysDestination=" + paysDestination + ", fragile=" + fragile + ", emballage=" + emballage + "]";
    }
}
