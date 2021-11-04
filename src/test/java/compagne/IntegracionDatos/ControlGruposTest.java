package compagne.IntegracionDatos;

import static org.junit.Assert.*;

import java.sql.ResultSet;

import org.junit.*;

import compagne.Entidades.Estudiante;
import compagne.Entidades.Grupo;

public class ControlGruposTest {

    @Test
    public void testGetGrupos() {
        ControlGrupos cg = new ControlGrupos();
        assertEquals(cg.getGruposPublicos().size(), cg.getGruposPublicos().size());
    }

    @Test
    public void testExecute() {
        ControlGrupos cg = new ControlGrupos();
        assertNotNull(cg.executeQuery("SELECT * FROM \"UsuarioRegistrado\""));
    }

    @Test
    public void testExecuteN() {
        ControlGrupos cg = new ControlGrupos();
        assertNull(cg.executeQuery("SELECT * FROM *"));
    }

    @Test
    public void testGrupoId() {
        ControlGrupos cg = new ControlGrupos();
        // No puede existir un grupo con codigo (@&-_-&@)
        assertEquals(cg.getGrupoID("(@&-_-&@)"), 0);
    }

    @Test
    public void testUsuarioXGrupo() {
        assertTrue(new ControlGrupos().insertarUsuarioXGrupoEstudio("-1", "Este no es emmail", "N"));
    }

    @Test
    public void testDelUsuarioXGrupo() {
        assertTrue(new ControlGrupos().eliminarUsuarioXGrupoEstudio("-1", "Este no es emmail"));
    }

    @Test
    public void testHacerAdmin() {
        boolean b = new ControlGrupos().hacerAdminDeGrupo(-1, -1);
        assertTrue(b);
    }

    /* private static int resultSetSize(ResultSet rs) {
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
        ControlGrupos cg = new ControlGrupos();
        // Reset chat id serial
        cg.executeQuery("ALTER SEQUENCE \"Chat_ID_seq\" RESTART WITH "
                + String.valueOf(resultSetSize(cg.executeQuery("SELECT * FROM \"Chat\";"))) + ";");
        // Reset comentario id serial
        cg.executeQuery("ALTER SEQUENCE \"Comentario_ID_seq\" RESTART WITH "
                + String.valueOf(resultSetSize(cg.executeQuery("SELECT * FROM \"Comentario\";"))) + ";");
        // Reset destacable id serial
        cg.executeQuery("ALTER SEQUENCE \"Destacable_ID_seq\" RESTART WITH "
                + String.valueOf(resultSetSize(cg.executeQuery("SELECT * FROM \"Destacable\";"))) + ";");
        // Reset grupo estudio id serial
        cg.executeQuery("ALTER SEQUENCE \"GrupoEstudio_ID_seq\" RESTART WITH "
                + String.valueOf(resultSetSize(cg.executeQuery("SELECT * FROM \"GrupoEstudio\";"))) + ";");
        // Reset horario atencion id serial
        cg.executeQuery("ALTER SEQUENCE \"HorarioAtencion_ID_seq\" RESTART WITH "
                + String.valueOf(resultSetSize(cg.executeQuery("SELECT * FROM \"HorarioAtencion\";"))) + ";");
        // Reset interes id serial
        cg.executeQuery("ALTER SEQUENCE \"Interes_ID_seq\" RESTART WITH "
                + String.valueOf(resultSetSize(cg.executeQuery("SELECT * FROM \"Interes\";"))) + ";");
        // Reset logro id serial
        cg.executeQuery("ALTER SEQUENCE \"Logro_ID_seq\" RESTART WITH "
                + String.valueOf(resultSetSize(cg.executeQuery("SELECT * FROM \"Logro\";"))) + ";");
        // Reset materia id serial
        cg.executeQuery("ALTER SEQUENCE \"Materia_ID_seq\" RESTART WITH "
                + String.valueOf(resultSetSize(cg.executeQuery("SELECT * FROM \"Materia\";"))) + ";");
        // Reset mensaje id serial
        cg.executeQuery("ALTER SEQUENCE \"Mensaje_ID_seq\" RESTART WITH "
                + String.valueOf(resultSetSize(cg.executeQuery("SELECT * FROM \"Mensaje\";"))) + ";");
        // Reset reunion id serial
        cg.executeQuery("ALTER SEQUENCE \"Reunion_ID_seq\" RESTART WITH "
                + String.valueOf(resultSetSize(cg.executeQuery("SELECT * FROM \"Reunion\";"))) + ";");
        // Reset usuario registrado id serial
        cg.executeQuery("ALTER SEQUENCE \"UsuarioRegistrado_ID_seq\" RESTART WITH "
                + String.valueOf(resultSetSize(cg.executeQuery("SELECT * FROM \"UsuarioRegistrado\";"))) + ";");
    }
    @Test
    public void testCrearGrupo() {
        Grupo grupo = new Grupo("Grupo de prueba", "el_codigo-del.GrupoDEPru$b@S%$#", "S");
        Estudiante estuUsuario = new Estudiante("NombreG", "Un-EmailGenerico ParaM&", null, null, "", null, null);
        assertTrue(new ControlEstudiantes().crearEstudiante(estuUsuario));
        assertTrue(new ControlGrupos().crearGrupo(grupo, estuUsuario.getEmail()));
        assertTrue(new ControlGrupos().modificarGrupo(grupo));
        assertTrue(new ControlGrupos().eliminarGrupo(grupo.getCodigo()));
        assertTrue(new ControlEstudiantes().eliminarEstudiante(estuUsuario.getEmail()));
    }

    @Test
    public void testModificarGrupo() {
        Grupo grupo = new Grupo("Grupo de prueba", "el_codigo-del.GrupoDEPru$b@S%$#", "S");
        Estudiante estuUsuario = new Estudiante("NombreG", "Un-EmailGenerico ParaM&", null, null, "", null, null);
        assertTrue(new ControlEstudiantes().crearEstudiante(estuUsuario));
        assertTrue(new ControlGrupos().crearGrupo(grupo, estuUsuario.getEmail()));
        int expected = new ControlGrupos().getGrupoID(grupo.getCodigo());
        grupo.setNombre("Los Agrupados Bb");
        assertTrue(new ControlGrupos().modificarGrupo(grupo));
        int actual = new ControlGrupos().getGrupoID(grupo.getCodigo());
        assertEquals(expected, actual);
        assertTrue(new ControlGrupos().eliminarGrupo(grupo.getCodigo()));
        assertTrue(new ControlEstudiantes().eliminarEstudiante(estuUsuario.getEmail()));
    } */
}
