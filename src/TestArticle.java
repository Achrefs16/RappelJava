
import java.util.List;
import java.util.Scanner;

public class TestArticle {
    public static void main(String[] args) {
        MagasinDAO magasinDAO = new MagasinDAO();
        ArticleDAO articleDAO = new ArticleDAO();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Display all magasins");
            System.out.println("2. Insert a new article into a magasin");
            System.out.println("3. Get articles of a selected magasin");
            System.out.println("4. Create a new magasin");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    afficherMagasins(magasinDAO);
                    break;
                case 2:
                    insererArticle(magasinDAO, articleDAO, scanner);
                    break;
                case 3:
                    afficherArticlesDuMagasin(magasinDAO, scanner);
                    break;
                case 4:
                    creerMagasin(magasinDAO, scanner);
                    break;
                case 5:
                    System.out.println("Exiting the application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    // Method to display all magasins with their articles
    private static void afficherMagasins(MagasinDAO magasinDAO) {
        try {
            List<Magasin> magasins = magasinDAO.getAllMagasins();
            if (magasins.isEmpty()) {
                System.out.println("No magasins available.");
            } else {
                magasins.forEach(Magasin::afficherArticles);
            }
        } catch (Exception e) {
            System.err.println("Error while retrieving magasins: " + e.getMessage());
        }
    }

    // Method to insert a new article into a magasin
    private static void insererArticle(MagasinDAO magasinDAO, ArticleDAO articleDAO, Scanner scanner) {
        try {
            // Retrieve the list of magasins
            List<Magasin> magasins = magasinDAO.getAllMagasins();

            if (magasins.isEmpty()) {
                System.out.println("No magasins available. Cannot insert an article.");
                return;
            }

            // Display the list of magasins
            System.out.println("\nAvailable magasins:");
            for (int i = 0; i < magasins.size(); i++) {
                System.out.println((i + 1) + ". " + magasins.get(i).getName());
            }

            // Prompt the user to select a magasin
            System.out.print("Choose a magasin by entering its number: ");
            int choice = scanner.nextInt();

            // Validate the selection
            while (choice < 1 || choice > magasins.size()) {
                System.out.println("Invalid choice. Please try again:");
                choice = scanner.nextInt();
            }

            // Get the selected magasin
            Magasin selectedMagasin = magasins.get(choice - 1);

            // Collect article details
            System.out.println("Enter the new article details:");

            System.out.print("Marque: ");
            scanner.nextLine(); // Consume newline character
            String marque = scanner.nextLine();

            System.out.print("Prix HT: ");
            double prixHT = scanner.nextDouble();

            System.out.print("Pays destination: ");
            scanner.nextLine();
            String paysDestination = scanner.nextLine();

            System.out.print("Is the article fragile? (true/false): ");
            boolean fragile = scanner.nextBoolean();

            // Create the article based on user input
            Article newArticle = new Article(0, marque, prixHT, paysDestination, fragile);

            // Save the article to the database (linked to the magasin)
            articleDAO.insertArticle(newArticle, selectedMagasin.getId());

            System.out.println("Article added successfully to " + selectedMagasin.getName() + "!");
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    // Method to display articles of a selected magasin
    private static void afficherArticlesDuMagasin(MagasinDAO magasinDAO, Scanner scanner) {
        try {
            // Retrieve the list of magasins
            List<Magasin> magasins = magasinDAO.getAllMagasins();

            if (magasins.isEmpty()) {
                System.out.println("No magasins available.");
                return;
            }

            // Display the list of magasins
            System.out.println("\nAvailable magasins:");
            for (int i = 0; i < magasins.size(); i++) {
                System.out.println((i + 1) + ". " + magasins.get(i).getName());
            }

            // Prompt the user to select a magasin
            System.out.print("Choose a magasin by entering its number: ");
            int choice = scanner.nextInt();

            // Validate the selection
            while (choice < 1 || choice > magasins.size()) {
                System.out.println("Invalid choice. Please try again:");
                choice = scanner.nextInt();
            }

            // Get the selected magasin
            Magasin selectedMagasin = magasins.get(choice - 1);

            // Display the articles of the selected magasin
            System.out.println("\nArticles in " + selectedMagasin.getName() + ":");
            List<Article> articles = selectedMagasin.getArticles();
            if (articles.isEmpty()) {
                System.out.println("(No articles available in this magasin)");
            } else {
                for (Article article : articles) {
                    System.out.println(article);
                }
            }
        } catch (Exception e) {
            System.err.println("Error while retrieving articles: " + e.getMessage());
        }
    }

    // Method to create a new magasin
    private static void creerMagasin(MagasinDAO magasinDAO, Scanner scanner) {
        try {
            System.out.print("Enter the name of the new magasin: ");
            scanner.nextLine(); // Consume newline character
            String name = scanner.nextLine();

            magasinDAO.addMagasin(name); // Call DAO to add magasin
            System.out.println("New magasin \"" + name + "\" has been created.");
        } catch (Exception e) {
            System.err.println("Error while creating magasin: " + e.getMessage());
        }
    }
}