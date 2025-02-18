// DatabaseConnection.java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // JDBC URL with credentials and SSL mode
    private static final String URL = "jdbc:postgresql://ep-holy-glitter-a8bv8ezk-pooler.eastus2.azure.neon.tech/neondb?user=neondb_owner&password=npg_MGntKsI16HVq&sslmode=require";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    // Optional test main method
    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Successfully connected to the PostgreSQL database!");
            } else {
                System.out.println("Failed to connect to the PostgreSQL database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
