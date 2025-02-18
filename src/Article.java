// Article.java
public class Article implements Exportable {
    public static final double TVA = 0.18;
    private static int nombreArticle = 0;
    private int code;
    protected String marque;
    protected double prixHT;
    protected String paysDestination;

    public Article(String marque, double prixHT, String paysDestination) {
        this.code = ++nombreArticle;
        this.marque = marque;
        this.prixHT = prixHT;
        this.paysDestination = paysDestination;
    }

    public double prixTransport() throws PRTInfA10Exception {
        double prix = 0.05 * prixHT;
        if (prix < 10) {
            throw new PRTInfA10Exception("Le prix du transport est inférieur à 10");
        }
        return prix;
    }

    @Override
    public String getPaysDestination() {
        return paysDestination;
    }

    // Additional getters
    public String getMarque() {
        return marque;
    }

    public double getPrixHT() {
        return prixHT;
    }

    @Override
    public String toString() {
        return "Article [code=" + code + ", marque=" + marque + ", prixHT=" + prixHT + ", paysDestination=" + paysDestination + "]";
    }
}
