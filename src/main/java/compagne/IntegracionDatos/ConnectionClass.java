package compagne.IntegracionDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
    private Connection con = null;
    final static String HOST = "ec2-34-233-105-94.compute-1.amazonaws.com";
    final static String PORT = "5432";
    final static String DRIVER_NAME = "postgresql";
    final static String DATABASE = "d5406v0odvk1bo";
    final static String SCHEMA = "public";
    final static String URL = "jdbc:" + DRIVER_NAME + "://" + HOST + ":" + PORT + "/" + DATABASE;
    final static String USER = "rnyrjrmcxwiylc";
    final static String PASS = "039149865843c41f53758d306e7eefd9c02ca68d09b37c29c31fb010b9c63b85";

    public Connection getCon() {
        return this.con;
    }

    public static String getSchema() {
        return SCHEMA+'.';
    }

    public static boolean usingPSQL() {
        return DRIVER_NAME.equals("postgresql");
    }

    public void startConnection() throws SQLException {

        this.con = DriverManager.getConnection(URL, USER, PASS);
    }

    public void startConnection(final String URL, final String USER, final String PASS) throws SQLException {
        this.con = DriverManager.getConnection(URL, USER, PASS);

    }

    public void closeConnection() throws SQLException {
        this.con.close();
    }

    public ConnectionClass() {
        try {
            this.startConnection();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public ConnectionClass(final String URL, final String USER, final String PASS) {
        try {
            this.startConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

}
