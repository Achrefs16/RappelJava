public class Article {
    protected int code;
    protected String marque;
    protected double prixHT;
    protected String paysDestination;
    protected boolean fragile;

    public Article(String marque, double prixHT, String paysDestination) {
        this.marque = marque;
        this.prixHT = prixHT;
        this.paysDestination = paysDestination;
        this.fragile = false; // Default is not fragile
    }

    public Article(int code, String marque, double prixHT, String paysDestination, boolean fragile) {
        this.code = code;
        this.marque = marque;
        this.prixHT = prixHT;
        this.paysDestination = paysDestination;
        this.fragile = fragile;
    }

    public double prixTransport() {
        return fragile ? prixHT * 0.10 : prixHT * 0.05;
    }

    public String getPaysDestination() {
        return paysDestination;
    }

    public int getCode() {
        return code;
    }

    public String getMarque() {
        return marque;
    }

    public double getPrixHT() {
        return prixHT;
    }

    public boolean isFragile() {
        return fragile;
    }

    @Override
    public String toString() {
        return "Article [code=" + code + ", marque=" + marque + ", prixHT=" + prixHT + ", paysDestination=" + paysDestination + ", fragile=" + fragile + "]";
    }
}
