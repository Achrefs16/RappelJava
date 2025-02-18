import java.util.Scanner;

public class TestArticle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            // Prompt for basic Article details
            System.out.println("Enter details for a standard Article:");
            System.out.print("Marque: ");
            String marque1 = scanner.nextLine();
            System.out.print("Prix HT: ");
            double prixHT1 = scanner.nextDouble();
            scanner.nextLine(); // consume newline
            System.out.print("Pays destination: ");
            String paysDestination1 = scanner.nextLine();

            Article a1 = new Article(marque1, prixHT1, paysDestination1);
            System.out.println("Prix transport a1: " + a1.prixTransport());
            System.out.println("Pays destination a1: " + a1.getPaysDestination());

            // Prompt for Fragile Article details
            System.out.println("\nEnter details for a Fragile Article:");
            System.out.print("Marque: ");
            String marque2 = scanner.nextLine();
            System.out.print("Prix HT: ");
            double prixHT2 = scanner.nextDouble();
            scanner.nextLine(); // consume newline
            System.out.print("Pays destination: ");
            String paysDestination2 = scanner.nextLine();
            System.out.print("Emballage (true/false): ");
            boolean emballage = scanner.nextBoolean();

            Article tv = new Fragile(marque2, prixHT2, paysDestination2, emballage);
            System.out.println("Prix transport tv: " + tv.prixTransport());
            System.out.println("Pays destination tv: " + tv.getPaysDestination());

            // Insert articles into the database using DAO
            ArticleDAO dao = new ArticleDAO();
            dao.insertArticle(a1);
            dao.insertArticle(tv);

            // Use local Magasin to display articles
            Magasin magasin = new Magasin();
            magasin.add(a1);
            magasin.add(tv);
            System.out.println("\nArticles from Magasin (local):");
            magasin.afficherArticles();

            // Display articles retrieved from the database
            System.out.println("\nArticles from the Database:");
            dao.afficherArticles();

        } catch (PRTInfA10Exception e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
