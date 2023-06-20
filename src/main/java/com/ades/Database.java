package com.ades;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class Database {
    private static final String SQLITE_CONN_STRING = "jdbc:sqlite:C:/sqlite/database";
    private static final String DATABASE_NAME = "cs601-dingnan.db";

    public static void createNewDatabase() {
        String url = SQLITE_CONN_STRING + "/" + DATABASE_NAME;
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static Connection connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = SQLITE_CONN_STRING + "/" + DATABASE_NAME;
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return conn;
    }

    public static void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Connection to SQLite has been closed.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void createNewTable() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS airplanes (\n"
                + " id integer PRIMARY KEY,\n"
                + " name text NOT NULL,\n"
                + " fuel_capacity integer NOT NULL,\n"
                + " fuel_consumption integer NOT NULL,\n"
                + " speed integer NOT NULL\n"
                + ");";
        String url = SQLITE_CONN_STRING + "/" + DATABASE_NAME;
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void selectAll(String tableName) {
        String sql = "SELECT * FROM " + tableName;
        try (Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getInt("fuel_capacity") + "\t" +
                        rs.getInt("fuel_consumption") + "\t" +
                        rs.getInt("speed"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insert(String tableName, String name, int fuel_capacity, int fuel_consumption, int speed) {
        String sql = "INSERT INTO " + tableName + "(name,fuel_capacity,fuel_consumption,speed) VALUES(?,?,?,?)";
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, fuel_capacity);
            pstmt.setInt(3, fuel_consumption);
            pstmt.setInt(4, speed);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void update(String tableName, String name, int fuel_capacity, int id) {
        String sql = "UPDATE " + tableName + " SET name = ? , "
                + "fuel_capacity = ? "
                + "WHERE id = ?";
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, fuel_capacity);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
