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

    public static void createAirplaneTable() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS airplanes (\n"
                + " id integer PRIMARY KEY,\n"
                + " name text NOT NULL,\n"
                + " range integer NOT NULL,\n"
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

    public static void createLocationsTable() {
        String sql = "CREATE TABLE IF NOT EXISTS locations (\n"
                + " id integer PRIMARY KEY,\n"
                + " name text NOT NULL,\n"
                + " latitude real NOT NULL,\n"
                + " longitude real NOT NULL\n"
                + ");";

        String url = SQLITE_CONN_STRING + "/" + DATABASE_NAME;
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void selectAllOrderedByName(String tableName) {
        String sql = "SELECT * FROM " + tableName + " ORDER BY name";
        try (Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getInt("range") + "\t" +
                        rs.getInt("fuel_capacity") + "\t" +
                        rs.getInt("fuel_consumption") + "\t" +
                        rs.getInt("speed"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertAirplane(String tableName, String name, int range, int fuel_capacity,
            int fuel_consumption,
            int speed) {
        String sql = "INSERT INTO " + tableName
                + "(name, range, fuel_capacity, fuel_consumption, speed) VALUES(?,?,?,?,?)";
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, range);
            pstmt.setInt(3, fuel_capacity);
            pstmt.setInt(4, fuel_consumption);
            pstmt.setInt(5, speed);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertIntoLocations(String name, double latitude, double longitude) {
        String sql = "INSERT INTO locations(name,latitude,longitude) VALUES(?,?,?)";

        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, latitude);
            pstmt.setDouble(3, longitude);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateAirplane(String tableName, String name, int range, int fuel_capacity, int id) {
        String sql = "UPDATE " + tableName + " SET name = ? , "
                + "range = ? , "
                + "fuel_capacity = ? "
                + "WHERE id = ?";
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, range);
            pstmt.setInt(3, fuel_capacity);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateLocation(String name, double latitude, double longitude, int id) {
        String sql = "UPDATE locations SET name = ? , "
                + "latitude = ? , "
                + "longitude = ? "
                + "WHERE id = ?";

        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, latitude);
            pstmt.setDouble(3, longitude);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void selectAirplanesForDestination(String originName, String destinationName) {
        String sqlLocations = "SELECT * FROM locations WHERE name IN (?, ?)";
        String sqlAirplanes = "SELECT * FROM airplanes";
        try (Connection conn = connect();
                PreparedStatement pstmtLocations = conn.prepareStatement(sqlLocations);
                Statement stmtAirplanes = conn.createStatement()) {
            // Get coordinates of origin and destination
            pstmtLocations.setString(1, originName);
            pstmtLocations.setString(2, destinationName);
            ResultSet rsLocations = pstmtLocations.executeQuery();
            double originLat = 0, originLon = 0, destinationLat = 0, destinationLon = 0;
            while (rsLocations.next()) {
                if (rsLocations.getString("name").equals(originName)) {
                    originLat = rsLocations.getDouble("latitude");
                    originLon = rsLocations.getDouble("longitude");
                } else if (rsLocations.getString("name").equals(destinationName)) {
                    destinationLat = rsLocations.getDouble("latitude");
                    destinationLon = rsLocations.getDouble("longitude");
                }
            }
            double distance = TravelCalculator.calculateDistance(originLat, originLon, destinationLat, destinationLon);
            // Get airplanes that can travel the distance
            ResultSet rsAirplanes = stmtAirplanes.executeQuery(sqlAirplanes);
            while (rsAirplanes.next()) {
                if (rsAirplanes.getDouble("range") >= distance) {
                    System.out.println(
                            rsAirplanes.getString("name") + " can reach " + destinationName + " from " + originName);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getAverageSpeed(String tableName) {
        String sql = "SELECT AVG(speed) AS avg_speed FROM " + tableName;
        try (Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("Average speed: " + rs.getInt("avg_speed"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getMaxSpeed(String tableName) {
        String sql = "SELECT MAX(speed) AS max_speed FROM " + tableName;
        try (Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("Max speed: " + rs.getInt("max_speed"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getMinSpeed(String tableName) {
        String sql = "SELECT MIN(speed) AS min_speed FROM " + tableName;
        try (Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("Min speed: " + rs.getInt("min_speed"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void countLocations(String tableName) {
        String sql = "SELECT COUNT(*) AS location_count FROM " + tableName;
        try (Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("Number of locations: " + rs.getInt("location_count"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
