package compagne.Negocio;

import static org.junit.Assert.*;

import java.sql.ResultSet;

import org.junit.*;

import compagne.Entidades.ChatG;
import compagne.Entidades.ChatP;

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
    public void testIniciarSesion() {
        assertNotNull(facade.iniciarSesion("abril@cano.com", "@bril"));
        assertNull(facade.iniciarSesion("abril@cano.com", "abril"));
    }

    @Test
    public void testsBuscar() {
        int usuario1ID = 1;
        int usuario2ID = 4;
        int perteneceID=1;
        assertNotNull(facade.buscarIDUsuario("abril@cano.com"));
        assertNotNull(facade.buscarIDGrupo("abril800q"));
        ChatP chatP = new ChatP("Fecha1", 'P', null, facade.getControlEstudiantes().getEstudianteByID(usuario1ID),
                facade.getControlProfesores().getProfesorByID(usuario2ID));
        assertTrue(facade.crearChat(chatP, usuario1ID, usuario2ID));
        assertNotNull(facade.buscarMensajesXChat(facade.getControlChats().getChatID(chatP)));
        ChatG chatG = new ChatG("Fecha2", 'G', null, facade.getControlGrupos().getGrupoByID(perteneceID));
        assertTrue(facade.crearChat(chatG, perteneceID));
        assertNotNull(facade.buscarChatsXGrupo(perteneceID));
        assertNotNull(facade.buscarChatsXUsuario(usuario1ID));
        assertTrue(facade.eliminarChat(chatP));
        assertTrue(facade.eliminarChat(chatG));
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
        facade.getControlEstudiantes()
                .executeQuery("ALTER SEQUENCE \"Chat_ID_seq\" RESTART WITH "
                        + String.valueOf(
                                resultSetSize(facade.getControlEstudiantes().executeQuery("SELECT * FROM \"Chat\";")))
                        + ";");
        // Reset comentario id serial
        facade.getControlEstudiantes().executeQuery("ALTER SEQUENCE \"Comentario_ID_seq\" RESTART WITH "
                + String.valueOf(
                        resultSetSize(facade.getControlEstudiantes().executeQuery("SELECT * FROM \"Comentario\";")))
                + ";");
        // Reset destacable id serial
        facade.getControlEstudiantes().executeQuery("ALTER SEQUENCE \"Destacable_ID_seq\" RESTART WITH "
                + String.valueOf(
                        resultSetSize(facade.getControlEstudiantes().executeQuery("SELECT * FROM \"Destacable\";")))
                + ";");
        // Reset grupo estudio id serial
        facade.getControlEstudiantes().executeQuery("ALTER SEQUENCE \"GrupoEstudio_ID_seq\" RESTART WITH "
                + String.valueOf(
                        resultSetSize(facade.getControlEstudiantes().executeQuery("SELECT * FROM \"GrupoEstudio\";")))
                + ";");
        // Reset horario atencion id serial
        facade.getControlEstudiantes()
                .executeQuery("ALTER SEQUENCE \"HorarioAtencion_ID_seq\" RESTART WITH "
                        + String.valueOf(resultSetSize(
                                facade.getControlEstudiantes().executeQuery("SELECT * FROM \"HorarioAtencion\";")))
                        + ";");
        // Reset interes id serial
        facade.getControlEstudiantes().executeQuery("ALTER SEQUENCE \"Interes_ID_seq\" RESTART WITH "
                + String.valueOf(
                        resultSetSize(facade.getControlEstudiantes().executeQuery("SELECT * FROM \"Interes\";")))
                + ";");
        // Reset logro id serial
        facade.getControlEstudiantes()
                .executeQuery("ALTER SEQUENCE \"Logro_ID_seq\" RESTART WITH "
                        + String.valueOf(
                                resultSetSize(facade.getControlEstudiantes().executeQuery("SELECT * FROM \"Logro\";")))
                        + ";");
        // Reset materia id serial
        facade.getControlEstudiantes().executeQuery("ALTER SEQUENCE \"Materia_ID_seq\" RESTART WITH "
                + String.valueOf(
                        resultSetSize(facade.getControlEstudiantes().executeQuery("SELECT * FROM \"Materia\";")))
                + ";");
        // Reset mensaje id serial
        facade.getControlEstudiantes().executeQuery("ALTER SEQUENCE \"Mensaje_ID_seq\" RESTART WITH "
                + String.valueOf(
                        resultSetSize(facade.getControlEstudiantes().executeQuery("SELECT * FROM \"Mensaje\";")))
                + ";");
        // Reset reunion id serial
        facade.getControlEstudiantes().executeQuery("ALTER SEQUENCE \"Reunion_ID_seq\" RESTART WITH "
                + String.valueOf(
                        resultSetSize(facade.getControlEstudiantes().executeQuery("SELECT * FROM \"Reunion\";")))
                + ";");
        // Reset usuario registrado id serial
        facade.getControlEstudiantes()
                .executeQuery("ALTER SEQUENCE \"UsuarioRegistrado_ID_seq\" RESTART WITH "
                        + String.valueOf(resultSetSize(
                                facade.getControlEstudiantes().executeQuery("SELECT * FROM \"UsuarioRegistrado\";")))
                        + ";");
        facade.finalize();
    }
}
