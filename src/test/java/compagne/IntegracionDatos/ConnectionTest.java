package compagne.IntegracionDatos;

import static org.junit.Assert.*;

import org.junit.*;

public class ConnectionTest {
    @Test
    public void testConnectionNotNull() {
        ConnectionClass connection = new ConnectionClass();
        assertNotNull(connection);
    }

    @Test
    public void testConnectionNull() {
        ConnectionClass connection = new ConnectionClass(ConnectionClass.HOST, ConnectionClass.USER,
                ConnectionClass.DATABASE);
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
        connection1.finalize();
        connection2.finalize();
    }

    @Test
    public void testConnectionEmpty() {
        ConnectionClass connection = new ConnectionClass(ConnectionClass.URL, ConnectionClass.USER, "");
        assertNull(connection.getCon());
    }

    @Test
    public void testConnectionNEmpty() {
        ConnectionClass connection = new ConnectionClass(ConnectionClass.URL, ConnectionClass.USER, ConnectionClass.PASS);
        assertNotNull(connection.getCon());
        connection.finalize();
    }

    @Test
    public void testConnectionClosed() {
        ConnectionClass connection = new ConnectionClass();
        try {
            connection.closeConnection();
            assertTrue(connection.getCon().isClosed());
        } catch (Exception e) {
            assertNull(connection.getCon());
        }
    }

    @Test
    public void testClosedConnection() {
        ConnectionClass connection = new ConnectionClass();
        try {
            assertFalse(connection.getCon().isClosed());
            connection.finalize();
        } catch (Exception e) {
            assertNull(connection.getCon());
        }
    }

    @Test
    public void testConnectionSchema() {
        assertNotEquals("", ConnectionClass.getSchema());
    }

    @Test
    public void testConnectionPSQL() {
        assertNotNull(ConnectionClass.usingPSQL());
    }
}
