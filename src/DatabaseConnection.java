import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // PostgreSQL Connection Details
    private static final String URL = "jdbc:postgresql://ep-holy-glitter-a8bv8ezk-pooler.eastus2.azure.neon.tech/neondb";
    private static final String USER = "neondb_owner";
    private static final String PASSWORD = "npg_MGntKsI16HVq";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("✅ Successfully connected to PostgreSQL database!");
            } else {
                System.out.println("❌ Failed to connect to PostgreSQL database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
