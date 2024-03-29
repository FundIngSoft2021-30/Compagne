package compagne.Negocio;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.util.HashSet;

import org.junit.*;

import compagne.Entidades.ChatG;
import compagne.Entidades.ChatP;
import compagne.Entidades.Comentario;
import compagne.Entidades.Grupo;
import compagne.Entidades.Mensaje;
import compagne.Entidades.Usuario;
import compagne.IntegracionDatos.ConnectionClass;

public class FacadeTest {
        private static FacadeCompagne facade;

        @BeforeClass
        public static void setUpBeforeClass() {
                facade = new FacadeCompagne();
        }

        @Test
        public void testGetCompas() {
                assertNotNull(facade.listarCompas());
                assertNotNull(facade.listarCompas("No name"));
        }

        @Test
        public void testlistarGrupos() {
                assertNotNull(facade.listarGruposPublicos());
        }

        @Test
        public void testGetters() {
                assertNotNull(facade.getControlChats());
                assertNotNull(facade.getControlEstudiantes());
                assertNotNull(facade.getControlGrupos());
                assertNotNull(facade.getControlProfesores());
                assertNotNull(FacadeCompagne.getInstance());
        }

        @Test
        public void testIniciarSesion_testInfoUsuarioGrupo_testIsEstudiante() {
                assertNotNull(facade.informacionUsuario("abril@cano.com"));
                assertNotNull(facade.informacionUsuario("anmontero@javeriana.edu.co"));
                assertNotNull(facade.iniciarSesion("abril@cano.com", "****"));
                assertNotNull(facade.iniciarSesion("anmontero@javeriana.edu.co", "@Nab3l"));
                assertNull(facade.iniciarSesion("anmontero@javeriana.edu.co", "@Nabel"));
                assertNotNull(facade.informacionGrupo("abril800q"));
                assertTrue(facade.isEstudiante("abril@cano.com"));
                assertFalse(facade.isEstudiante("anmontero@javeriana.edu.co"));
        }

        @Test
        public void testEnviarMsj() {
                HashSet<Mensaje> mensajes = new HashSet<Mensaje>();
                HashSet<Mensaje> mensajes2 = new HashSet<Mensaje>();
                int usuario1ID = 1;
                int usuario2ID = 4;
                int perteneceID = 1;
                ChatP chatP = new ChatP("Fecha1", 'P', mensajes,
                                facade.getControlEstudiantes().getEstudianteByID(usuario1ID),
                                facade.getControlProfesores().getProfesorByID(usuario2ID));
                assertTrue(facade.crearChat(chatP, usuario1ID, usuario2ID));
                ChatG chatG = new ChatG("Fecha2", 'G', mensajes2, facade.getControlGrupos().getGrupoByID(perteneceID));
                assertTrue(facade.crearChat(chatG, perteneceID));
                assertNotNull(facade.getChatID(chatP));
                assertNotNull(facade.getChatID(chatG));
                Mensaje mensaje = new Mensaje("Fecha1_0", "Mensaje1",
                                facade.getControlEstudiantes().getEstudianteByID(usuario1ID), chatP);
                assertNotNull(facade.redactarMensaje(facade.getChatID(chatP), mensaje, usuario2ID));
                assertTrue(facade.eliminarChat(chatP));
                assertTrue(facade.eliminarChat(chatG));
        }

        @Test
        public void testsBuscar() {
                int usuario1ID = 1;
                int usuario2ID = 4;
                int perteneceID = 1;
                assertNotNull(facade.buscarIDUsuario("abril@cano.com"));
                assertNotNull(facade.buscarIDGrupo("abril800q"));
                ChatP chatP = new ChatP("Fecha1.0", 'P', null,
                                facade.getControlEstudiantes().getEstudianteByID(usuario1ID),
                                facade.getControlProfesores().getProfesorByID(usuario2ID));
                assertTrue(facade.crearChat(chatP, usuario1ID, usuario2ID));
                assertNotNull(facade.buscarMensajesXChat(facade.getControlChats().getChatID(chatP)));
                ChatG chatG = new ChatG("Fecha2.0", 'G', null, facade.getControlGrupos().getGrupoByID(perteneceID));
                assertTrue(facade.crearChat(chatG, perteneceID));
                assertNotNull(facade.buscarChatsXGrupo(perteneceID));
                assertNotNull(facade.buscarChatsXUsuario(usuario1ID));
                assertTrue(facade.getControlChats().eliminarChat(chatP));
                assertTrue(facade.getControlChats().eliminarChat(chatG));
        }

        @Test
        public void testBuscarXNombre() {
                assertNotEquals(0, facade.buscarCompaNombre("Jose Torres").size());
                assertEquals(0, facade.buscarCompaNombre("b").size());
        }

        @Test
        public void testCrear() {
                HashSet<String> mSet = new HashSet<String>();
                mSet.add("Hola. Esta es una materia prueba, el texto que aquí se usa no debería de estar presente en la base de datos,  hablo enserio con este texto, nisiquiera debería saberse esto");
                mSet.add("Analisis Numerico");
                HashSet<Comentario> cSet = new HashSet<Comentario>();
                cSet.add(new Comentario(
                                "Hola. Este es un comentario de prueba, esto no debería estar en la base de datos, puesto que si estuviera en la BD sería algo fatal :C",
                                null));
                cSet.add(new Comentario(null, "3.0"));
                HashSet<String> horariosA = new HashSet<String>();
                horariosA.add("Hola. Este es un horario de atencion prueba, el texto que aquí se usa no debería de estar presente en la base de datos, este logro debe ser usado únicamente en la prueba que se eta llevando a cabo actualmente. Gracias");
                horariosA.add("Lunes 7-9");
                HashSet<String> logros = new HashSet<String>();
                logros.add("Hola. Este es un logro prueba, el texto que aquí se usa no debería de estar presente en la base de datos, este logro debe ser usado únicamente en la prueba que se eta llevando a cabo actualmente. Gracias");
                logros.add("Excelencia Academica");
                HashSet<String> intereses = new HashSet<String>();
                intereses.add("Hola. Este es un interes prueba, el texto que aquí se usa no debería de estar presente en la base de datos, este interes debe ser usado únicamente en la prueba que se eta llevando a cabo actualmente. Gracias");
                intereses.add("Futbol");
                assertNotNull(facade.crearPerfilProfesor("nombre", "experiencia", "emailP", "contrasenia", horariosA,
                                mSet, cSet, logros));
                Usuario u = facade.modificarPerfilProfesor("NewN", "experiencia", "emailP", "contrasenia", horariosA,
                                mSet, cSet, logros);
                assertNotNull(u);
                assertNotNull(facade.crearPerfilEstudiante("nombre", "emailE", "contrasenia", mSet, cSet, intereses,
                                logros));
                Usuario a = facade.modificarPerfilEstudiante("NewN", "emailE", "contrasenia",  cSet, mSet,intereses,
                                logros);
                assertNotNull(u);
                Comentario comentario = new Comentario("4.5", "Una persona muy comprometida con todas sus actividades");
                assertNotNull(facade.calificarUsuario(comentario, u));
                assertNotNull(facade.calificarUsuario(comentario, a));
                Grupo gr = facade.crearGrupo("NoProm&s$S", "NoDEbe3d33x1st1r3st03nl4()codigo", "S", u.getEmail());
                assertNotNull(gr);
                gr = facade.modificarGrupo("UnGRUP=", "NoDEbe3d33x1st1r3st03nl4()codigo", "S");
                assertNotNull(facade.agregarUsuarioAGrupo("NoDEbe3d33x1st1r3st03nl4()codigo", "abril@cano.com", "N"));
                assertNotNull(facade.hacerAdminDeGrupo(
                                facade.getControlGrupos().getGrupoID("NoDEbe3d33x1st1r3st03nl4()codigo"), 1));
                assertNotNull(facade.listarCompasGrupo("NoDEbe3d33x1st1r3st03nl4()codigo"));
                assertNotNull(facade.eliminarUsuarioDeGrupo("NoDEbe3d33x1st1r3st03nl4()codigo", "abril@cano.com"));
                // Para eliminar
                facade.eliminarGrupo("NoDEbe3d33x1st1r3st03nl4()codigo");
                facade.eliminarPerfilProfesor("emailP");
                facade.eliminarPerfilEstudiante("emailE");
                facade.getControlProfesores().executeQuery("DELETE FROM " + ConnectionClass.getSchema()
                                + "\"Materia\" WHERE \"Nombre\"=\'"
                                + "Hola. Esta es una materia prueba, el texto que aquí se usa no debería de estar presente en la base de datos,  hablo enserio con este texto, nisiquiera debería saberse esto"
                                + "\';");
                facade.getControlProfesores().executeQuery("DELETE FROM " + ConnectionClass.getSchema()
                                + "\"Comentario\" WHERE \"Texto\"=\'"
                                + "Hola. Este es un comentario de prueba, esto no debería estar en la base de datos, puesto que si estuviera en la BD sería algo fatal :C"
                                + "\';");
                facade.getControlProfesores().executeQuery("DELETE FROM " + ConnectionClass.getSchema()
                                + "\"Logro\" WHERE \"Texto\"=\'"
                                + "Hola. Este es un logro prueba, el texto que aquí se usa no debería de estar presente en la base de datos, este logro debe ser usado únicamente en la prueba que se eta llevando a cabo actualmente. Gracias"
                                + "\';");
                facade.getControlProfesores().executeQuery("DELETE FROM " + ConnectionClass.getSchema()
                                + "\"HorarioAtencion\" WHERE \"Franja\"=\'"
                                + "Hola. Este es un horario de atencion prueba, el texto que aquí se usa no debería de estar presente en la base de datos, este logro debe ser usado únicamente en la prueba que se eta llevando a cabo actualmente. Gracias"
                                + "\';");
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
                // Reset chat id serial
                facade.getControlEstudiantes().executeQuery("ALTER SEQUENCE \"Chat_ID_seq\" RESTART WITH "
                                + String.valueOf(resultSetSize(
                                                facade.getControlEstudiantes().executeQuery("SELECT * FROM \"Chat\";")))
                                + ";");
                // Reset comentario id serial
                facade.getControlEstudiantes()
                                .executeQuery("ALTER SEQUENCE \"Comentario_ID_seq\" RESTART WITH "
                                                + String.valueOf(resultSetSize(facade.getControlEstudiantes()
                                                                .executeQuery("SELECT * FROM \"Comentario\";")))
                                                + ";");
                // Reset destacable id serial
                facade.getControlEstudiantes()
                                .executeQuery("ALTER SEQUENCE \"Destacable_ID_seq\" RESTART WITH "
                                                + String.valueOf(resultSetSize(facade.getControlEstudiantes()
                                                                .executeQuery("SELECT * FROM \"Destacable\";")))
                                                + ";");
                // Reset grupo estudio id serial
                facade.getControlEstudiantes()
                                .executeQuery("ALTER SEQUENCE \"GrupoEstudio_ID_seq\" RESTART WITH "
                                                + String.valueOf(resultSetSize(facade.getControlEstudiantes()
                                                                .executeQuery("SELECT * FROM \"GrupoEstudio\";")))
                                                + ";");
                // Reset horario atencion id serial
                facade.getControlEstudiantes()
                                .executeQuery("ALTER SEQUENCE \"HorarioAtencion_ID_seq\" RESTART WITH "
                                                + String.valueOf(resultSetSize(facade.getControlEstudiantes()
                                                                .executeQuery("SELECT * FROM \"HorarioAtencion\";")))
                                                + ";");
                // Reset interes id serial
                facade.getControlEstudiantes()
                                .executeQuery("ALTER SEQUENCE \"Interes_ID_seq\" RESTART WITH "
                                                + String.valueOf(resultSetSize(facade.getControlEstudiantes()
                                                                .executeQuery("SELECT * FROM \"Interes\";")))
                                                + ";");
                // Reset logro id serial
                facade.getControlEstudiantes()
                                .executeQuery("ALTER SEQUENCE \"Logro_ID_seq\" RESTART WITH "
                                                + String.valueOf(resultSetSize(facade.getControlEstudiantes()
                                                                .executeQuery("SELECT * FROM \"Logro\";")))
                                                + ";");
                // Reset materia id serial
                facade.getControlEstudiantes()
                                .executeQuery("ALTER SEQUENCE \"Materia_ID_seq\" RESTART WITH "
                                                + String.valueOf(resultSetSize(facade.getControlEstudiantes()
                                                                .executeQuery("SELECT * FROM \"Materia\";")))
                                                + ";");
                // Reset mensaje id serial
                facade.getControlEstudiantes()
                                .executeQuery("ALTER SEQUENCE \"Mensaje_ID_seq\" RESTART WITH "
                                                + String.valueOf(resultSetSize(facade.getControlEstudiantes()
                                                                .executeQuery("SELECT * FROM \"Mensaje\";")))
                                                + ";");
                // Reset reunion id serial
                facade.getControlEstudiantes()
                                .executeQuery("ALTER SEQUENCE \"Reunion_ID_seq\" RESTART WITH "
                                                + String.valueOf(resultSetSize(facade.getControlEstudiantes()
                                                                .executeQuery("SELECT * FROM \"Reunion\";")))
                                                + ";");
                // Reset usuario registrado id serial
                facade.getControlEstudiantes()
                                .executeQuery("ALTER SEQUENCE \"UsuarioRegistrado_ID_seq\" RESTART WITH "
                                                + String.valueOf(resultSetSize(facade.getControlEstudiantes()
                                                                .executeQuery("SELECT * FROM \"UsuarioRegistrado\";")))
                                                + ";");
                facade.finalize();
        }
}
