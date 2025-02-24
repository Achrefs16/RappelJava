import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TestArticle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MagasinDAO magasinDAO = new MagasinDAO();
        ArticleDAO articleDAO = new ArticleDAO();

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1️⃣ View all magasins with their articles");
            System.out.println("2️⃣ Insert a new article into a magasin");
            System.out.println("3️⃣ Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    afficherMagasins(magasinDAO);
                    break;
                case 2:
                    insererArticle(magasinDAO, articleDAO, scanner);
                    break;
                case 3:
                    System.out.println("Exiting program. Goodbye! 👋");
                    scanner.close();
                    return;
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }

    // Method to display all magasins with their articles
    private static void afficherMagasins(MagasinDAO magasinDAO) {
        List<Magasin> magasins = magasinDAO.getAllMagasins();
        if (magasins.isEmpty()) {
            System.out.println("⚠️ No magasins found in the database.");
            return;
        }

        System.out.println("\n📋 All Magasins with Articles:");
        for (Magasin m : magasins) {
            System.out.println("🏪 Magasin: " + m.getName());
            if (m.getArticles().isEmpty()) {
                System.out.println("   (No articles in this magasin)");
            } else {
                System.out.println("   🛒 Articles:");
                for (Article a : m.getArticles()) {
                    System.out.println("     - " + a);
                }
            }
            System.out.println();
        }
    }

    // Method to insert a new article into a magasin
    private static void insererArticle(MagasinDAO magasinDAO, ArticleDAO articleDAO, Scanner scanner) {
        List<Magasin> magasins = magasinDAO.getAllMagasins();
        if (magasins.isEmpty()) {
            System.out.println("⚠️ No magasins found. Please add a magasin first.");
            return;
        }

        // Display magasins for selection
        System.out.println("\nAvailable magasins:");
        for (int i = 0; i < magasins.size(); i++) {
            System.out.println((i + 1) + ". " + magasins.get(i).getName());
        }

        System.out.print("Select a magasin (1-" + magasins.size() + "): ");
        int magasinIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        if (magasinIndex < 0 || magasinIndex >= magasins.size()) {
            System.out.println("❌ Invalid magasin choice.");
            return;
        }

        Magasin selectedMagasin = magasins.get(magasinIndex);

        // Collect article details
        System.out.println("\nAdding an article to " + selectedMagasin.getName());
        System.out.print("Enter marque: ");
        String marque = scanner.nextLine();

        System.out.print("Enter price (prixHT): ");
        double prixHT = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter pays de destination: ");
        String paysDestination = scanner.nextLine();

        System.out.print("Is it fragile? (true/false): ");
        boolean fragile = scanner.nextBoolean();
        scanner.nextLine(); // Consume newline

        Article article;
        if (fragile) {
            System.out.print("Does it have packaging? (true/false): ");
            boolean emballage = scanner.nextBoolean();
            scanner.nextLine(); // Consume newline
            article = new Fragile(marque, prixHT, paysDestination, emballage);
        } else {
            article = new Article(marque, prixHT, paysDestination);
        }

        // Insert article into the selected magasin
        articleDAO.insertArticle(article, selectedMagasin.getId());
        selectedMagasin.add(article);
        System.out.println("✅ Article added successfully!");

    }
}
