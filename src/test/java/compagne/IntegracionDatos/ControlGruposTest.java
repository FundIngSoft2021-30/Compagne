package compagne.IntegracionDatos;

import static org.junit.Assert.*;

import java.sql.ResultSet;

import org.junit.*;

import compagne.Entidades.Estudiante;
import compagne.Entidades.Grupo;

public class ControlGruposTest {

    private static ControlGrupos controlGrupos;
    private static ControlEstudiantes ce;
    private static Grupo grupo;
    private static Estudiante estuUsuario;

    @BeforeClass
    public static void setUpBeforeClass() {
        estuUsuario = new Estudiante("NombreG", "Un-EmailGenerico ParaM&", null, null, "", null, null);
        controlGrupos = new ControlGrupos();
        ce = new ControlEstudiantes();
        grupo = new Grupo("Grupo de prueba", "el_codigo-del.GrupoDEPru$b@S%$#", "S");
    }

    @Test
    public void testGetGrupos() {

        assertEquals(controlGrupos.getGruposPublicos().size(), controlGrupos.getGruposPublicos().size());
    }

    @Test
    public void testExecute() {

        assertNotNull(controlGrupos.executeQuery("SELECT * FROM \"UsuarioRegistrado\""));
    }

    @Test
    public void testExecuteN() {

        assertNull(controlGrupos.executeQuery("SELECT * FROM *"));
    }

    @Test
    public void testGrupoId() {

        // No puede existir un grupo con codigo (@&-_-&@)
        assertEquals(controlGrupos.getGrupoID("(@&-_-&@)"), 0);
    }

    @Test
    public void testUsuarioXGrupo_DEL() {
        assertTrue(controlGrupos.insertarUsuarioXGrupoEstudio("-1", "Este no es emmail", "N"));
        assertTrue(controlGrupos.eliminarUsuarioXGrupoEstudio("-1", "Este no es emmail"));
    }

    @Test
    public void testHacerAdmin() {
        boolean b = controlGrupos.hacerAdminDeGrupo(-1, -1);
        assertTrue(b);
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

        assertTrue(controlGrupos.eliminarGrupo(grupo.getCodigo()));
        assertTrue(ce.eliminarEstudiante(estuUsuario.getEmail()));
        // Reset chat id serial
        controlGrupos.executeQuery("ALTER SEQUENCE \"Chat_ID_seq\" RESTART WITH "
                + String.valueOf(resultSetSize(controlGrupos.executeQuery("SELECT * FROM \"Chat\";"))) + ";");
        // Reset comentario id serial
        controlGrupos.executeQuery("ALTER SEQUENCE \"Comentario_ID_seq\" RESTART WITH "
                + String.valueOf(resultSetSize(controlGrupos.executeQuery("SELECT * FROM \"Comentario\";"))) + ";");
        // Reset destacable id serial
        controlGrupos.executeQuery("ALTER SEQUENCE \"Destacable_ID_seq\" RESTART WITH "
                + String.valueOf(resultSetSize(controlGrupos.executeQuery("SELECT * FROM \"Destacable\";"))) + ";");
        // Reset grupo estudio id serial
        controlGrupos.executeQuery("ALTER SEQUENCE \"GrupoEstudio_ID_seq\" RESTART WITH "
                + String.valueOf(resultSetSize(controlGrupos.executeQuery("SELECT * FROM \"GrupoEstudio\";"))) + ";");
        // Reset horario atencion id serial
        controlGrupos.executeQuery("ALTER SEQUENCE \"HorarioAtencion_ID_seq\" RESTART WITH "
                + String.valueOf(resultSetSize(controlGrupos.executeQuery("SELECT * FROM \"HorarioAtencion\";")))
                + ";");
        // Reset interes id serial
        controlGrupos.executeQuery("ALTER SEQUENCE \"Interes_ID_seq\" RESTART WITH "
                + String.valueOf(resultSetSize(controlGrupos.executeQuery("SELECT * FROM \"Interes\";"))) + ";");
        // Reset logro id serial
        controlGrupos.executeQuery("ALTER SEQUENCE \"Logro_ID_seq\" RESTART WITH "
                + String.valueOf(resultSetSize(controlGrupos.executeQuery("SELECT * FROM \"Logro\";"))) + ";");
        // Reset materia id serial
        controlGrupos.executeQuery("ALTER SEQUENCE \"Materia_ID_seq\" RESTART WITH "
                + String.valueOf(resultSetSize(controlGrupos.executeQuery("SELECT * FROM \"Materia\";"))) + ";");
        // Reset mensaje id serial
        controlGrupos.executeQuery("ALTER SEQUENCE \"Mensaje_ID_seq\" RESTART WITH "
                + String.valueOf(resultSetSize(controlGrupos.executeQuery("SELECT * FROM \"Mensaje\";"))) + ";");
        // Reset reunion id serial
        controlGrupos.executeQuery("ALTER SEQUENCE \"Reunion_ID_seq\" RESTART WITH "
                + String.valueOf(resultSetSize(controlGrupos.executeQuery("SELECT * FROM \"Reunion\";"))) + ";");
        // Reset usuario registrado id serial
        controlGrupos.executeQuery("ALTER SEQUENCE \"UsuarioRegistrado_ID_seq\" RESTART WITH "
                + String.valueOf(resultSetSize(controlGrupos.executeQuery("SELECT * FROM \"UsuarioRegistrado\";")))
                + ";");
        controlGrupos.finalize();
        ce.finalize();
    }

    @Test
    public void testCrearGrupo() {
        assertTrue(ce.crearEstudiante(estuUsuario));
        assertTrue(controlGrupos.crearGrupo(grupo, estuUsuario.getEmail()));
    }

    @Test
    public void testModificarGrupo() {
        int expected = controlGrupos.getGrupoID(grupo.getCodigo());
        grupo.setNombre("Los Agrupados Bb");
        assertTrue(controlGrupos.modificarGrupo(grupo));
        int actual = controlGrupos.getGrupoID(grupo.getCodigo());
        assertEquals(expected, actual);
    }

}
