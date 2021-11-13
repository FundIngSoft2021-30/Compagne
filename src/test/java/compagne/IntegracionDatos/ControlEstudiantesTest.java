package compagne.IntegracionDatos;

import org.junit.*;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.util.HashSet;

import compagne.Entidades.Estudiante;
import compagne.Entidades.Usuario;

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
                String comentario = "Hola. Este es un comentario prueba, el texto que aquí se usa no debería de estar presente en la base de datos";
                String calificacion = "5.0";
                assertFalse(ce.insertarComentario(comentario, calificacion));
                consulta = "DELETE FROM " + ConnectionClass.getSchema() + "\"Comentario\" WHERE \"ID\"="
                                + ce.getComentarioID(comentario, calificacion) + ";";
                assertNotNull(ce.getComentarioID(comentario, calificacion));
                assertEquals(ce.getComentarioID("null", calificacion), 0);
                assertEquals(ce.getComentarioID(comentario, "null"), 0);
                assertNull(ce.executeQuery(consulta));
                assertFalse(ce.insertarComentario(comentario, null));
                consulta = "DELETE FROM " + ConnectionClass.getSchema() + "\"Comentario\" WHERE \"Texto\"=\'"
                                + comentario + "\';";

                assertNull(ce.executeQuery(consulta));
                assertFalse(ce.insertarComentario(null, calificacion));
                consulta = "DELETE FROM " + ConnectionClass.getSchema() + "\"Comentario\" WHERE \"Estrellas\"=\'"
                                + calificacion + "\';";
                assertNotNull(ce.getComentarioID(null, calificacion));
                assertEquals(ce.getComentarioID(comentario, calificacion), 0);
                assertNull(ce.executeQuery(consulta));
        }

        @Test
        public void testComentarioXEstudiante() {
                String consulta = "";
                String comentario = "Hola. Este es un comentario prueba, el texto que aquí se usa no debería de estar presente en la base de datos";
                String calificacion = "5.0";
                assertFalse(ce.insertarComentario(comentario, calificacion));
                int cID = ce.getComentarioID(comentario, calificacion);
                consulta = "DELETE FROM " + ConnectionClass.getSchema()
                                + "\"UsuarioXComentario\" WHERE \"ComentarioID\"=" + cID + ";";
                assertTrue(ce.insertarComentarioXEstudiante(1, cID));
                assertNull(ce.executeQuery(consulta));
                consulta = "DELETE FROM " + ConnectionClass.getSchema() + "\"Comentario\" WHERE \"ID\"=" + cID + ";";
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

        @Test
        public void testMateria() {
                String consulta = "";
                String materia = "Hola. Esta es una materia prueba, el texto que aquí se usa no debería de estar presente en la base de datos";
                assertNotNull(ce.insertarMateria(materia));
                int mID = ce.getMateriaID(materia);
                assertNotEquals(0, mID);
                assertEquals(0, ce.getMateriaID(materia.repeat(2)));
                consulta = "DELETE FROM " + ConnectionClass.getSchema() + "\"Materia\" WHERE \"Nombre\"=\'" + materia
                                + "\';";
                assertNull(ce.executeQuery(consulta));
        }

        @Test
        public void testMateriaXEstudiante() {
                String consulta = "";
                String materia = "Hola. Esta es una materia prueba, el texto que aquí se usa no debería de estar presente en la base de datos";
                assertNotNull(ce.insertarMateria(materia));
                int mID = ce.getMateriaID(materia);
                assertNotEquals(0, mID);
                assertNotNull(ce.insertarMateriaXEstudiante(1, mID));
                consulta = "DELETE FROM " + ConnectionClass.getSchema() + "\"MateriaXEstudiante\" WHERE \"MateriaID\"="
                                + mID + ";";
                assertNull(ce.executeQuery(consulta));
                consulta = "DELETE FROM " + ConnectionClass.getSchema() + "\"Materia\" WHERE \"Nombre\"=\'" + materia
                                + "\';";
                assertNull(ce.executeQuery(consulta));
        }

        @Test
        public void tetsLogro() {
                String consulta = "";
                String logro = "Hola. Este es un logro prueba, el texto que aquí se usa no debería de estar presente en la base de datos";
                assertNotNull(ce.insertarLogro(logro));
                int lID = ce.getLogroID(logro);
                assertNotEquals(0, lID);
                assertEquals(0, ce.getMateriaID(logro.repeat(2)));
                consulta = "DELETE FROM " + ConnectionClass.getSchema() + "\"Logro\" WHERE \"Texto\"=\'" + logro
                                + "\';";
                assertNull(ce.executeQuery(consulta));
        }

        @Test
        public void testLogroXEstudiante() {
                String consulta = "";
                String logro = "Hola. Este es un logro prueba, el texto que aquí se usa no debería de estar presente en la base de datos";
                assertNotNull(ce.insertarLogro(logro));
                int lID = ce.getLogroID(logro);
                assertNotEquals(0, lID);
                assertNotNull(ce.insertarLogroXEstudiante(1, lID));
                consulta = "DELETE FROM " + ConnectionClass.getSchema() + "\"UsuarioXLogros\" WHERE \"LogroID\"=" + lID
                                + ";";

                assertNull(ce.executeQuery(consulta));
                consulta = "DELETE FROM " + ConnectionClass.getSchema() + "\"Logro\" WHERE \"Texto\"=\'" + logro
                                + "\';";
                assertNull(ce.executeQuery(consulta));
        }

        @Test
        public void tetsInteres() {
                String consulta = "";
                String interes = "Hola. Este es un interes prueba, el texto que aquí se usa no debería de estar presente en la base de datos";
                assertNotNull(ce.insertarInteres(interes));
                int inIDID = ce.getInteresID(interes);
                assertNotEquals(0, inIDID);
                assertEquals(0, ce.getMateriaID(interes.repeat(2)));
                consulta = "DELETE FROM " + ConnectionClass.getSchema() + "\"Interes\" WHERE \"Nombre\"=\'" + interes
                                + "\';";
                assertNull(ce.executeQuery(consulta));
        }

        @Test
        public void testInteresXEstudiante() {
                String consulta = "";
                String interes = "Hola. Este es un interes prueba, el texto que aquí se usa no debería de estar presente en la base de datos";
                assertNotNull(ce.insertarInteres(interes));
                int inIDID = ce.getInteresID(interes);
                assertNotEquals(0, inIDID);
                assertNotNull(ce.insertarInteresXEstudiante(1, inIDID));
                consulta = "DELETE FROM " + ConnectionClass.getSchema() + "\"UsuarioXIntereses\" WHERE \"InteresID\"=" + inIDID
                                + ";";
                assertNull(ce.executeQuery(consulta));
                consulta = "DELETE FROM " + ConnectionClass.getSchema() + "\"Interes\" WHERE \"Nombre\"=\'" + interes
                                + "\';";
                assertNull(ce.executeQuery(consulta));
        }

        @Test
        public void testListarCompas() {
                HashSet<Usuario> usuarios = ce.listarCompas();
                assertNotNull(usuarios);
                Usuario u = usuarios.iterator().next();
                assertNotNull(u);
                assertNotNull(ce.listarCompas(u.getNombre()));
        }

        @Test
        public void testListarCompasGrupo() {
                assertNotNull(ce.listarCompasGrupo("abril800q"));
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
