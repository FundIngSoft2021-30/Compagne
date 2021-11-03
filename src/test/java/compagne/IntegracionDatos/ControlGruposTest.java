package compagne.IntegracionDatos;
import static org.junit.Assert.*;

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
    public void testExecute(){
        ControlGrupos cg = new ControlGrupos();
        assertNotNull(cg.executeQuery("SELECT * FROM \"UsuarioRegistrado\""));
    }

    @Test
    public void testExecuteN(){
        ControlGrupos cg = new ControlGrupos();
        assertNull(cg.executeQuery("SELECT * FROM *"));
    }

    @Test
    public void testGrupoId(){
        ControlGrupos cg = new ControlGrupos();
        //No puede existir un grupo con codigo (@&-_-&@)
        assertEquals(cg.getGrupoID("(@&-_-&@)"),0);
    }

    @Test
    public void testUsuarioXGrupo(){
        assertTrue(new ControlGrupos().insertarUsuarioXGrupoEstudio("-1", "Este no es emmail", "N"));
    }

    @Test
    public void testDelUsuarioXGrupo(){
        assertTrue(new ControlGrupos().eliminarUsuarioXGrupoEstudio("-1", "Este no es emmail"));
    }

    @Test
    public void testHacerAdmin(){
        assertTrue(new ControlGrupos().hacerAdminDeGrupo(-1, -1));
    }

    @Test
    public void testCrearGrupo(){
        Grupo grupo=new Grupo("Grupo de prueba", "el_codigo-del.GrupoDEPru$b@S%$#", "S");
        Estudiante estuUsuario=new Estudiante("NombreG", "Un-EmailGenerico ParaM&", null, null, "", null, null);
        assertTrue(new ControlEstudiantes().crearEstudiante(estuUsuario));
        assertTrue(new ControlGrupos().crearGrupo(grupo, estuUsuario.getEmail()));
        assertTrue(new ControlGrupos().modificarGrupo(grupo));
        assertTrue(new ControlGrupos().eliminarGrupo(grupo.getCodigo()));
        assertTrue(new ControlEstudiantes().eliminarEstudiante(estuUsuario.getEmail()));
    }

    @Test
    public void testModificarGrupo(){
        Grupo grupo=new Grupo("Grupo de prueba", "el_codigo-del.GrupoDEPru$b@S%$#", "S");
        Estudiante estuUsuario=new Estudiante("NombreG", "Un-EmailGenerico ParaM&", null, null, "", null, null);
        assertTrue(new ControlEstudiantes().crearEstudiante(estuUsuario));
        assertTrue(new ControlGrupos().crearGrupo(grupo, estuUsuario.getEmail()));
        int expected=new ControlGrupos().getGrupoID(grupo.getCodigo());
        grupo.setNombre("Los Agrupados Bb");
        assertTrue(new ControlGrupos().modificarGrupo(grupo));
        int actual=new ControlGrupos().getGrupoID(grupo.getCodigo());
        assertEquals(expected, actual);
        assertTrue(new ControlGrupos().eliminarGrupo(grupo.getCodigo()));
        assertTrue(new ControlEstudiantes().eliminarEstudiante(estuUsuario.getEmail()));
    }
}
