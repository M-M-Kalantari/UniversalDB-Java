package UniversalDB.simpleDB;

import java.sql.Connection;
import java.sql.DriverManager;

public class Class_DB_Connector {
    private static final String db_driver = "org.postgresql.Driver";
    private static final String db_url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String db_user = "postgres";
    private static final String db_password = "2564";

    public static Connection getConnection() {
        Connection connection;
        try {
            Class.forName(db_driver);
            connection = DriverManager.getConnection(db_url, db_user, db_password);
            System.out.println("***(Connected to database successfully)***");
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
