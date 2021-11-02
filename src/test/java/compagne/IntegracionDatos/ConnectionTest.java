package compagne.IntegracionDatos;
import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.*;
public class ConnectionTest {
    /*@Test
    public void testConnectionNotNull() {
        ConnectionClass connection = new ConnectionClass();
        assertNotNull(connection);
    }

    @Test
    public void testConnectionNotEmpty() {
        assertNotEquals("", ConnectionClass.HOST);
        assertNotEquals("", ConnectionClass.PORT);
        assertNotEquals("", ConnectionClass.PASS);
        assertNotEquals("", ConnectionClass.DRIVER_NAME);
        assertNotEquals("", ConnectionClass.DATABASE);
        assertNotEquals("", ConnectionClass.SCHEMA);
        assertNotEquals("", ConnectionClass.URL);
        assertNotEquals("", ConnectionClass.USER);
    }

    @Test
    public void testConnectionNotEqual() {
        ConnectionClass connection1 = new ConnectionClass();
        ConnectionClass connection2 = new ConnectionClass();
        assertNotEquals(connection1, connection2);
    }

    @Test
    public void testConnectionEmpty() {
        ConnectionClass connection = new ConnectionClass(ConnectionClass.URL, ConnectionClass.USER, "");
        assertNull(connection.getCon());
    }

    @Test
    public void testConnectionClosed() {
        ConnectionClass connection = new ConnectionClass();
        try {
            connection.closeConnection();
            assertTrue(connection.getCon().isClosed());
        } catch (SQLException e) {
            assertNull(connection.getCon());
        }
    }*/
}
