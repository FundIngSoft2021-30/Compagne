package compagne.IntegracionDatos;

import org.junit.*;

import compagne.Entidades.Mensaje;
import compagne.Entidades.ChatG;
import compagne.Entidades.ChatP;

import static org.junit.Assert.*;

import java.sql.ResultSet;

import java.util.HashSet;

public class ControlChatsTest {
        private static ControlChats controlChats;
        private static ControlGrupos controlGrupos;
        private static ControlEstudiantes controlEstudiantes;
        private static ControlProfesores controlProfesores;
        private static ChatP chatP;
        private static ChatG chatG;
        private static int usuario1ID;
        private static int usuario2ID;
        private static int perteneceID;

        @BeforeClass
        public static void setUpBeforeClass() {
                HashSet<Mensaje> mensajes = new HashSet<Mensaje>();
                HashSet<Mensaje> mensajes2 = new HashSet<Mensaje>();
                usuario1ID = 1;
                usuario2ID = 4;
                perteneceID = 1;
                controlChats = new ControlChats();
                controlGrupos = new ControlGrupos();
                controlEstudiantes = new ControlEstudiantes();
                controlProfesores = new ControlProfesores();
                chatP = new ChatP("Fecha1", 'P', mensajes, controlEstudiantes.getEstudianteByID(usuario1ID),
                                controlProfesores.getProfesorByID(usuario2ID));
                assertTrue(controlChats.crearChat(chatP, usuario1ID, usuario2ID));
                chatG = new ChatG("Fecha2", 'G', mensajes2, controlGrupos.getGrupoByID(perteneceID));
                assertTrue(controlChats.crearChat(chatG, perteneceID));
        }

        @Test
        public void enviarMSJtest() {
                int cid = controlChats.getChatID(chatP);
                Mensaje mensaje = new Mensaje("Fecha1", "Mensaje1", controlEstudiantes.getEstudianteByID(usuario1ID),
                                chatP);
                assertTrue(controlChats.insertarMensajeXChat(cid, mensaje, usuario1ID));
                int s1 = controlChats.mensajesXChat(cid).size();
                mensaje = new Mensaje("Fecha1", "Mensaje2", controlEstudiantes.getEstudianteByID(usuario2ID), chatP);
                assertTrue(controlChats.insertarMensajeXChat(cid, mensaje, usuario2ID));
                assertEquals(s1 + 1, controlChats.mensajesXChat(cid).size());
        }

        @Test
        public void getChatIDTest() {
                assertNotEquals(0, controlChats.getChatID(chatP));
                assertEquals(0, controlChats.getChatID(new ChatG("(Date)new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime()",
                                                'G', null, controlGrupos.getGrupoByID(perteneceID))));
        }

        @Test
        public void buscarChatsTest() {
                assertNotNull(controlChats.buscarChatsXGrupo(perteneceID));
                assertNotNull(controlChats.buscarChatsXUsuario(usuario1ID));
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
                assertTrue(controlChats.eliminarChat(chatP));
                assertTrue(controlChats.eliminarChat(chatG));
                controlChats.executeQuery("DELETE FROM " + ConnectionClass.getSchema()
                                + "\"Materia\" WHERE \"Nombre\"=\'"
                                + "Hola. Esta es una materia prueba, el texto que aquí se usa no debería de estar presente en la base de datos,  hablo enserio con este texto, nisiquiera debería saberse esto"
                                + "\';");
                controlChats.executeQuery("DELETE FROM " + ConnectionClass.getSchema()
                                + "\"Comentario\" WHERE \"Texto\"=\'"
                                + "Hola. Este es un comentario de prueba, esto no debería estar en la base de datos, puesto que si estuviera en la BD sería algo fatal :C"
                                + "\';");
                controlChats.executeQuery("DELETE FROM " + ConnectionClass.getSchema()
                                + "\"Interes\" WHERE \"Nombre\"=\'"
                                + "Hola. Este es un interes prueba, el texto que aquí se usa no debería de estar presente en la base de datos, este interes debe ser usado únicamente en la prueba que se eta llevando a cabo actualmente. Gracias"
                                + "\';");
                controlChats.executeQuery("DELETE FROM " + ConnectionClass.getSchema() + "\"Logro\" WHERE \"Texto\"=\'"
                                + "Hola. Este es un logro prueba, el texto que aquí se usa no debería de estar presente en la base de datos, este logro debe ser usado únicamente en la prueba que se eta llevando a cabo actualmente. Gracias"
                                + "\';");
                // Reset chat id serial
                controlChats.executeQuery("ALTER SEQUENCE \"Chat_ID_seq\" RESTART WITH "
                                + String.valueOf(resultSetSize(controlChats.executeQuery("SELECT * FROM \"Chat\";")))
                                + ";");
                // Reset comentario id serial
                controlChats.executeQuery("ALTER SEQUENCE \"Comentario_ID_seq\" RESTART WITH " + String.valueOf(
                                resultSetSize(controlChats.executeQuery("SELECT * FROM \"Comentario\";"))) + ";");
                // Reset destacable id serial
                controlChats.executeQuery("ALTER SEQUENCE \"Destacable_ID_seq\" RESTART WITH " + String.valueOf(
                                resultSetSize(controlChats.executeQuery("SELECT * FROM \"Destacable\";"))) + ";");
                // Reset grupo estudio id serial
                controlChats.executeQuery("ALTER SEQUENCE \"GrupoEstudio_ID_seq\" RESTART WITH " + String.valueOf(
                                resultSetSize(controlChats.executeQuery("SELECT * FROM \"GrupoEstudio\";"))) + ";");
                // Reset horario atencion id serial
                controlChats.executeQuery("ALTER SEQUENCE \"HorarioAtencion_ID_seq\" RESTART WITH "
                                + String.valueOf(resultSetSize(
                                                controlChats.executeQuery("SELECT * FROM \"HorarioAtencion\";")))
                                + ";");
                // Reset interes id serial
                controlChats.executeQuery("ALTER SEQUENCE \"Interes_ID_seq\" RESTART WITH "
                                + String.valueOf(resultSetSize(controlChats.executeQuery("SELECT * FROM \"Interes\";")))
                                + ";");
                // Reset logro id serial
                controlChats.executeQuery("ALTER SEQUENCE \"Logro_ID_seq\" RESTART WITH "
                                + String.valueOf(resultSetSize(controlChats.executeQuery("SELECT * FROM \"Logro\";")))
                                + ";");
                // Reset materia id serial
                controlChats.executeQuery("ALTER SEQUENCE \"Materia_ID_seq\" RESTART WITH "
                                + String.valueOf(resultSetSize(controlChats.executeQuery("SELECT * FROM \"Materia\";")))
                                + ";");
                // Reset mensaje id serial
                controlChats.executeQuery("ALTER SEQUENCE \"Mensaje_ID_seq\" RESTART WITH "
                                + String.valueOf(resultSetSize(controlChats.executeQuery("SELECT * FROM \"Mensaje\";")))
                                + ";");
                // Reset reunion id serial
                controlChats.executeQuery("ALTER SEQUENCE \"Reunion_ID_seq\" RESTART WITH "
                                + String.valueOf(resultSetSize(controlChats.executeQuery("SELECT * FROM \"Reunion\";")))
                                + ";");
                // Reset usuario registrado id serial
                controlChats.executeQuery("ALTER SEQUENCE \"UsuarioRegistrado_ID_seq\" RESTART WITH "
                                + String.valueOf(resultSetSize(
                                                controlChats.executeQuery("SELECT * FROM \"UsuarioRegistrado\";")))
                                + ";");
                controlChats.finalize();
                controlGrupos.finalize();
                controlEstudiantes.finalize();
                controlProfesores.finalize();
        }
}
