import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MagasinDAO {

    // Retrieve all magasins along with their associated articles
    public List<Magasin> getAllMagasins() {
        List<Magasin> magasins = new ArrayList<>();
        String sql = "SELECT m.id as magasin_id, m.name as magasin_name, a.id as article_id, a.marque, a.prixHT, a.paysDestination, a.fragile, a.emballage "
                + "FROM Magasin m "
                + "LEFT JOIN Articles a ON m.id = a.magasin_id";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            Magasin currentMagasin = null;

            while (rs.next()) {
                int magasinId = rs.getInt("magasin_id");
                String magasinName = rs.getString("magasin_name");

                // Create new magasin if it doesn't exist yet
                if (currentMagasin == null || currentMagasin.getId() != magasinId) {
                    if (currentMagasin != null) {
                        magasins.add(currentMagasin); // Add the previous magasin to the list
                    }
                    currentMagasin = new Magasin(magasinId, magasinName); // Create a new magasin
                }

                // Add articles to the current magasin
                int articleId = rs.getInt("article_id");
                if (articleId > 0) { // If there's an article
                    String marque = rs.getString("marque");
                    double prixHT = rs.getDouble("prixHT");
                    String paysDestination = rs.getString("paysDestination");
                    boolean fragile = rs.getBoolean("fragile");
                    boolean emballage = rs.getBoolean("emballage");

                    Article article;
                    if (fragile) {
                        article = new Fragile(marque, prixHT, paysDestination, emballage);
                    } else {
                        article = new Article(marque, prixHT, paysDestination);
                    }
                    currentMagasin.add(article); // Add article to the current magasin
                }
            }

            // Add the last magasin to the list
            if (currentMagasin != null) {
                magasins.add(currentMagasin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving magasins with articles", e);
        }
        return magasins;
    }
    public void addMagasin(String name) {
        String sql = "INSERT INTO Magasin (name) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            System.out.println("Magasin added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding magasin to the database", e);
        }
    }

}
