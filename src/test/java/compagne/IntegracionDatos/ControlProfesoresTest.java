package compagne.IntegracionDatos;

import static org.junit.Assert.*;

import java.sql.ResultSet;

import org.junit.*;
import java.util.HashSet;

import compagne.Entidades.Comentario;
import compagne.Entidades.Profesor;

public class ControlProfesoresTest {
        private static ControlProfesores controlProfesores;
        private static Profesor profesor;

        @BeforeClass
        public static void setUpBeforeClass() {
                controlProfesores = new ControlProfesores();
                HashSet<String> mSet = new HashSet<String>();
                mSet.add("Hola. Esta es una materia prueba, el texto que aquí se usa no debería de estar presente en la base de datos,  hablo enserio con este texto, nisiquiera debería saberse esto");
                mSet.add("Analisis Numerico");
                HashSet<Comentario> cSet = new HashSet<Comentario>();
                cSet.add(new Comentario(
                                "Hola. Este es un comentario de prueba, esto no debería estar en la base de datos, puesto que si estuviera en la BD sería algo fatal :C",
                                null));
                cSet.add(new Comentario(null, "3.0"));
                String contrasenia = "12345";
                HashSet<String> horariosA = new HashSet<String>();
                horariosA.add("Hola. Este es un horario de atencion prueba, el texto que aquí se usa no debería de estar presente en la base de datos, este logro debe ser usado únicamente en la prueba que se eta llevando a cabo actualmente. Gracias");
                horariosA.add("Lunes 7-9");
                HashSet<String> logros = new HashSet<String>();
                logros.add("Hola. Este es un logro prueba, el texto que aquí se usa no debería de estar presente en la base de datos, este logro debe ser usado únicamente en la prueba que se eta llevando a cabo actualmente. Gracias");
                logros.add("Excelencia Academica");
                profesor = new Profesor("NombreGene", "Un3m@1lGen$rico", mSet, cSet,
                                "Mi experiencia es la experiencia que no debe de aparecer en ningun momento en la base de datos, puesto que esta es únicamente una prueba",
                                contrasenia, horariosA, logros);
        }

        @Test
        public void testGetProfesorEmail() {
                assertNotEquals(0, controlProfesores.getProfesorID("anmontero@javeriana.edu.co"));
                assertEquals(0, controlProfesores.getProfesorID("UncorreoQuedeberíadenoexistirLOSKSLKHDJBF"));
        }

        @Test
        public void testCrearProfesor() {
                assertNotNull(controlProfesores.crearProfesor(profesor));
        }

        @Test
        public void testModificarProfesor() {
                profesor.setNombre("NombreModificado");
                assertNotNull(controlProfesores.modificarProfesor(profesor));
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
                assertNotNull(controlProfesores.eliminarProfesor(profesor.getEmail()));
                controlProfesores.executeQuery("DELETE FROM " + ConnectionClass.getSchema()
                                + "\"Materia\" WHERE \"Nombre\"=\'"
                                + "Hola. Esta es una materia prueba, el texto que aquí se usa no debería de estar presente en la base de datos,  hablo enserio con este texto, nisiquiera debería saberse esto"
                                + "\';");
                controlProfesores.executeQuery("DELETE FROM " + ConnectionClass.getSchema()
                                + "\"Comentario\" WHERE \"Texto\"=\'"
                                + "Hola. Este es un comentario de prueba, esto no debería estar en la base de datos, puesto que si estuviera en la BD sería algo fatal :C"
                                + "\';");
                controlProfesores.executeQuery("DELETE FROM " + ConnectionClass.getSchema()
                                + "\"Logro\" WHERE \"Texto\"=\'"
                                + "Hola. Este es un logro prueba, el texto que aquí se usa no debería de estar presente en la base de datos, este logro debe ser usado únicamente en la prueba que se eta llevando a cabo actualmente. Gracias"
                                + "\';");
                controlProfesores.executeQuery("DELETE FROM " + ConnectionClass.getSchema()
                                + "\"HorarioAtencion\" WHERE \"Franja\"=\'"
                                + "Hola. Este es un horario de atencion prueba, el texto que aquí se usa no debería de estar presente en la base de datos, este logro debe ser usado únicamente en la prueba que se eta llevando a cabo actualmente. Gracias"
                                + "\';");
                // Reset chat id serial
                controlProfesores.executeQuery("ALTER SEQUENCE \"Chat_ID_seq\" RESTART WITH " + String.valueOf(
                                resultSetSize(controlProfesores.executeQuery("SELECT * FROM \"Chat\";"))) + ";");
                // Reset comentario id serial
                controlProfesores.executeQuery("ALTER SEQUENCE \"Comentario_ID_seq\" RESTART WITH "
                                + String.valueOf(resultSetSize(
                                                controlProfesores.executeQuery("SELECT * FROM \"Comentario\";")))
                                + ";");
                // Reset destacable id serial
                controlProfesores.executeQuery("ALTER SEQUENCE \"Destacable_ID_seq\" RESTART WITH "
                                + String.valueOf(resultSetSize(
                                                controlProfesores.executeQuery("SELECT * FROM \"Destacable\";")))
                                + ";");
                // Reset grupo estudio id serial
                controlProfesores.executeQuery("ALTER SEQUENCE \"GrupoEstudio_ID_seq\" RESTART WITH "
                                + String.valueOf(resultSetSize(
                                                controlProfesores.executeQuery("SELECT * FROM \"GrupoEstudio\";")))
                                + ";");
                // Reset horario atencion id serial
                controlProfesores.executeQuery("ALTER SEQUENCE \"HorarioAtencion_ID_seq\" RESTART WITH "
                                + String.valueOf(resultSetSize(
                                                controlProfesores.executeQuery("SELECT * FROM \"HorarioAtencion\";")))
                                + ";");
                // Reset interes id serial
                controlProfesores.executeQuery("ALTER SEQUENCE \"Interes_ID_seq\" RESTART WITH " + String.valueOf(
                                resultSetSize(controlProfesores.executeQuery("SELECT * FROM \"Interes\";"))) + ";");
                // Reset logro id serial
                controlProfesores.executeQuery("ALTER SEQUENCE \"Logro_ID_seq\" RESTART WITH " + String.valueOf(
                                resultSetSize(controlProfesores.executeQuery("SELECT * FROM \"Logro\";"))) + ";");
                // Reset materia id serial
                controlProfesores.executeQuery("ALTER SEQUENCE \"Materia_ID_seq\" RESTART WITH " + String.valueOf(
                                resultSetSize(controlProfesores.executeQuery("SELECT * FROM \"Materia\";"))) + ";");
                // Reset mensaje id serial
                controlProfesores.executeQuery("ALTER SEQUENCE \"Mensaje_ID_seq\" RESTART WITH " + String.valueOf(
                                resultSetSize(controlProfesores.executeQuery("SELECT * FROM \"Mensaje\";"))) + ";");
                // Reset reunion id serial
                controlProfesores.executeQuery("ALTER SEQUENCE \"Reunion_ID_seq\" RESTART WITH " + String.valueOf(
                                resultSetSize(controlProfesores.executeQuery("SELECT * FROM \"Reunion\";"))) + ";");
                // Reset usuario registrado id serial
                controlProfesores.executeQuery("ALTER SEQUENCE \"UsuarioRegistrado_ID_seq\" RESTART WITH "
                                + String.valueOf(resultSetSize(
                                                controlProfesores.executeQuery("SELECT * FROM \"UsuarioRegistrado\";")))
                                + ";");
        }
}
