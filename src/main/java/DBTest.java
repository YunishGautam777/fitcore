import java.sql.*;
import util.DBUtil;

public class DBTest {
    public static void main(String[] args) {
        try {
            System.out.println("Testing database connection...");
            Connection conn = DBUtil.getConnection();
            System.out.println("✅ Database connection successful!");

            // Check if users table exists and has data
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as count FROM users");
            if (rs.next()) {
                int count = rs.getInt("count");
                System.out.println("✅ Users table exists with " + count + " records");
            }

            // Show sample users
            rs = stmt.executeQuery("SELECT email, role FROM users LIMIT 5");
            System.out.println("Sample users:");
            while (rs.next()) {
                System.out.println("  - " + rs.getString("email") + " (" + rs.getString("role") + ")");
            }

            rs.close();
            stmt.close();
            conn.close();
            System.out.println("✅ Test completed successfully!");

        } catch (Exception e) {
            System.out.println("❌ Database connection failed!");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
