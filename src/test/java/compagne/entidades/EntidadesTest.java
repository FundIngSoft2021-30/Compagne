package compagne.entidades;

import static org.junit.Assert.*;

import org.junit.*;

import compagne.Entidades.*;

public class EntidadesTest{
    private ChatG chatG;
    private ChatP chatP;
    private Comentario comentario;
    private Estudiante estudiante;
    private Grupo grupo;
    private Mensaje mensaje;
    private Profesor profesor;
    private Reunion reunion;
    @Before
    public void setUp(){
        estudiante = new Estudiante(null, null, null, null, null, null, null);
        grupo = new Grupo(null, null, null);
        chatG = new ChatG(null, null, (char) 0, null, grupo);
        chatP = new ChatP(null, null, (char) 0, null, estudiante, estudiante);
        comentario = new Comentario(null, null);
        mensaje = new Mensaje(null, null);
        profesor = new Profesor(null, null, null, null, null, null, null, null);
        reunion = new Reunion();
    }
    @Test
    public void ChatGTest()
    {
        assertNotNull( chatG );
    }
    @Test
    public void ChatPTest()
    {
        assertNotNull( chatP );
    }
    @Test
    public void ComentarioTest()
    {
        assertNotNull( comentario );
    }
    @Test
    public void EstudianteTest()
    {
        assertNotNull( estudiante );
    }
    @Test
    public void GrupoTest()
    {
        assertNotNull( grupo );
    }
    @Test
    public void MensajeTest()
    {
        assertNotNull( mensaje );
    }
    @Test
    public void ProfesorTest()
    {
        assertNotNull( profesor );
    }
    @Test
    public void ReunionTest()
    {
        assertNotNull( reunion );
    }
}
