package compagne.IntegracionDatos;

import org.junit.*;
import static org.junit.Assert.*;
import java.sql.ResultSet;

import compagne.Entidades.Estudiante;

public class ControlEstudiantesTest {
        private static ControlEstudiantes ce;
        private static Estudiante estudiante;

        @Test
        public void testInstance() {
                ce.instanceOf();
                assertNotNull(ce);
        }

        @Test
        public void testExecute() {
                assertNotNull(ce.executeQuery("SELECT * FROM \"UsuarioRegistrado\""));
        }

        @Test
        public void testGetEstudianteEmail() {
                assertEquals(ce.getEstudianteEmail(1), "abril@cano.com");
                assertNull(ce.getEstudianteEmail(0));
        }

        @Test
        public void testInsertarComentario() {
                String consulta = "";
                String comentario = "Hola";
                String calificacion = "3.2";
                assertFalse(ce.insertarComentario(comentario, calificacion));
                consulta = "DELETE FROM " + ConnectionClass.getSchema() + "\"Comentario\" WHERE \"Comentario\"=\'"
                                + comentario + "\' AND \"Calificacion\"=\'" + calificacion + "\';";

                assertNotNull(ce.getComentarioID(comentario, calificacion));
                assertNull(ce.executeQuery(consulta));
                assertFalse(ce.insertarComentario(comentario, null));
                consulta = "DELETE FROM " + ConnectionClass.getSchema() + "\"Comentario\" WHERE \"Comentario\"=\'"
                                + comentario + "\';";

                assertNull(ce.executeQuery(consulta));
                assertFalse(ce.insertarComentario(null, calificacion));
                consulta = "DELETE FROM " + ConnectionClass.getSchema() + "\"Calificacion\"=\'" + calificacion + "\';";
                assertNotNull(ce.getComentarioID(null, calificacion));
                assertNull(ce.executeQuery(consulta));
        }

        @Test
        public void testModificarEstudiante() {
                String nom = "Nombre";
                String nnom = estudiante.getNombre();
                estudiante.setNombre(nom);
                assertTrue(ce.modificarEstudiante(estudiante));
                estudiante.setNombre(nnom);
                assertTrue(ce.modificarEstudiante(estudiante));
        }

        @BeforeClass
        public static void setUpBeforeClass() {
                estudiante = new Estudiante("NombreG", "Un-EmailGenerico ParaM&", null, null, "", null, null);
                ce = new ControlEstudiantes();
                assertTrue(ce.crearEstudiante(estudiante));
        }

        private static int resultSetSize(ResultSet rs) {
                int size = 0;
                try {
                        rs.last();
                        size = rs.getRow();
                } catch (Exception e) {
                        size = 0;
                }
                return size;
        }

        @AfterClass
        public static void resetSerials() {
                assertTrue(ce.eliminarEstudiante(estudiante.getEmail()));
                // Reset chat id serial
                ce.executeQuery("ALTER SEQUENCE \"Chat_ID_seq\" RESTART WITH "
                                + String.valueOf(resultSetSize(ce.executeQuery("SELECT * FROM \"Chat\";"))) + ";");
                // Reset comentario id serial
                ce.executeQuery("ALTER SEQUENCE \"Comentario_ID_seq\" RESTART WITH "
                                + String.valueOf(resultSetSize(ce.executeQuery("SELECT * FROM \"Comentario\";")))
                                + ";");
                // Reset destacable id serial
                ce.executeQuery("ALTER SEQUENCE \"Destacable_ID_seq\" RESTART WITH "
                                + String.valueOf(resultSetSize(ce.executeQuery("SELECT * FROM \"Destacable\";")))
                                + ";");
                // Reset grupo estudio id serial
                ce.executeQuery("ALTER SEQUENCE \"GrupoEstudio_ID_seq\" RESTART WITH "
                                + String.valueOf(resultSetSize(ce.executeQuery("SELECT * FROM \"GrupoEstudio\";")))
                                + ";");
                // Reset horario atencion id serial
                ce.executeQuery("ALTER SEQUENCE \"HorarioAtencion_ID_seq\" RESTART WITH "
                                + String.valueOf(resultSetSize(ce.executeQuery("SELECT * FROM \"HorarioAtencion\";")))
                                + ";");
                // Reset interes id serial
                ce.executeQuery("ALTER SEQUENCE \"Interes_ID_seq\" RESTART WITH "
                                + String.valueOf(resultSetSize(ce.executeQuery("SELECT * FROM \"Interes\";"))) + ";");
                // Reset logro id serial
                ce.executeQuery("ALTER SEQUENCE \"Logro_ID_seq\" RESTART WITH "
                                + String.valueOf(resultSetSize(ce.executeQuery("SELECT * FROM \"Logro\";"))) + ";");
                // Reset materia id serial
                ce.executeQuery("ALTER SEQUENCE \"Materia_ID_seq\" RESTART WITH "
                                + String.valueOf(resultSetSize(ce.executeQuery("SELECT * FROM \"Materia\";"))) + ";");
                // Reset mensaje id serial
                ce.executeQuery("ALTER SEQUENCE \"Mensaje_ID_seq\" RESTART WITH "
                                + String.valueOf(resultSetSize(ce.executeQuery("SELECT * FROM \"Mensaje\";"))) + ";");
                // Reset reunion id serial
                ce.executeQuery("ALTER SEQUENCE \"Reunion_ID_seq\" RESTART WITH "
                                + String.valueOf(resultSetSize(ce.executeQuery("SELECT * FROM \"Reunion\";"))) + ";");
                // Reset usuario registrado id serial
                ce.executeQuery("ALTER SEQUENCE \"UsuarioRegistrado_ID_seq\" RESTART WITH "
                                + String.valueOf(resultSetSize(ce.executeQuery("SELECT * FROM \"UsuarioRegistrado\";")))
                                + ";");
        }
}
