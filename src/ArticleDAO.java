import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ArticleDAO {
    private Connection connection;

    public ArticleDAO() {
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    public void insertArticle(Article article, int magasinId) {
        String sql = "INSERT INTO Articles (marque, prixHT, paysDestination, fragile, magasin_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, article.getMarque());
            pstmt.setDouble(2, article.getPrixHT());
            pstmt.setString(3, article.getPaysDestination());
            pstmt.setBoolean(4, article.isFragile());
            pstmt.setInt(5, magasinId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error inserting article into database", e);
        }
    }
}
