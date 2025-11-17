package UniversalDB.simpleDB;

import java.sql.*;

public class Class_DB_Function {

    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS member (" +
                "id SERIAL PRIMARY KEY, " +
                "name VARCHAR(50), " +
                "family VARCHAR(50)" +
                ");";

        try (Connection conn = Class_DB_Connector.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("Table 'member' created successfully!\n");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error creating table: " + e.getMessage());
        }
    }

    public static void insert(String name, String family) {
        String sql = "INSERT INTO member (name, family) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = Class_DB_Connector.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, family);
            preparedStatement.executeUpdate();
            System.out.println("Record inserted successfully!\n");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error inserting record: " + e.getMessage());
        }
    }

    public static void update(int id, String newName, String newFamily) {
        String sql = "UPDATE member SET name = ?, family = ? WHERE id = ?";

        try (Connection conn = Class_DB_Connector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newName);
            ps.setString(2, newFamily);
            ps.setInt(3, id);
            int rows = ps.executeUpdate();
            System.out.println("Updated rows: " + rows);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating record: " + e.getMessage());
        }
    }

    public static String[] select(int id) {
        String sql = "SELECT * FROM member WHERE id = ?";
        String[] record = null;

        try (Connection conn = Class_DB_Connector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String family = rs.getString("family");
                    record = new String[]{String.valueOf(id), name, family};
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error selecting record by id: " + e.getMessage());
        }

        return record; // برمی‌گرداند null اگر رکورد پیدا نشد
    }

    public static void selectAll() {
        String sql = "SELECT * FROM member";

        try (Connection conn = Class_DB_Connector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("All members in database:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String family = rs.getString("family");
                System.out.println(id + " | " + name + " | " + family);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error reading records: " + e.getMessage());
        }
    }


    public static void delete(int id) {
        String sql = "DELETE FROM member WHERE id = ?";

        try (Connection conn = Class_DB_Connector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println("Deleted rows: " + rows);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error deleting record: " + e.getMessage());
        }
    }

    public static void deleteAll() {
        String sql = "DELETE FROM member";

        try (Connection conn = Class_DB_Connector.getConnection();
             Statement stmt = conn.createStatement()) {

            int rows = stmt.executeUpdate(sql);
            System.out.println("Deleted all records, rows affected: " + rows);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error deleting all records: " + e.getMessage());
        }
    }


    public static void searchByNameOrFamily(String keyword) {
        String sql = "SELECT * FROM member WHERE name LIKE ? OR family LIKE ?";

        try (Connection conn = Class_DB_Connector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");

            try (ResultSet rs = ps.executeQuery()) {
                System.out.println("Search results for: " + keyword);
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String family = rs.getString("family");
                    System.out.println(id + " | " + name + " | " + family);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error searching records: " + e.getMessage());
        }
    }

    public static int count() {
        String sql = "SELECT COUNT(*) FROM member";
        int total = 0;

        try (Connection conn = Class_DB_Connector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                total = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error counting records: " + e.getMessage());
        }

        return total;
    }
}