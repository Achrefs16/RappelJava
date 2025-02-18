// ArticleDAO.java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleDAO {

    // Insert an Article or Fragile into the database
    public void insertArticle(Article article) {
        String sql = "INSERT INTO articles (type, marque, prixHT, paysDestination, emballage) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Determine the type and set parameters accordingly
            if (article instanceof Fragile) {
                stmt.setString(1, "Fragile");
                stmt.setBoolean(5, ((Fragile) article).hasEmballage());
            } else {
                stmt.setString(1, "Article");
                stmt.setObject(5, null);  // Not applicable for base articles
            }
            stmt.setString(2, article.getMarque());
            stmt.setDouble(3, article.getPrixHT());
            stmt.setString(4, article.getPaysDestination());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve and display all articles from the database
    public void afficherArticles() {
        String sql = "SELECT id, type, marque, prixHT, paysDestination, emballage FROM articles";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String type = rs.getString("type");
                String marque = rs.getString("marque");
                double prixHT = rs.getDouble("prixHT");
                String paysDestination = rs.getString("paysDestination");
                Boolean emballage = (Boolean) rs.getObject("emballage");

                System.out.println("Article ID: " + id +
                        ", Type: " + type +
                        ", Marque: " + marque +
                        ", PrixHT: " + prixHT +
                        ", PaysDestination: " + paysDestination +
                        (emballage != null ? ", Emballage: " + emballage : ""));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
