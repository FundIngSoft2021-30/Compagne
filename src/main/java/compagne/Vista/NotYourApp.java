package compagne.Vista;

import compagne.Entidades.Usuario;
import compagne.Negocio.VerInfoController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import java.util.HashSet;
import compagne.Entidades.Comentario;
import compagne.Entidades.Estudiante;

public class NotYourApp{

    public static Usuario getUsuario(){
        HashSet<Comentario> comentarios = new HashSet<>();
        comentarios.add(new Comentario(null, "Hola soy pepe"));
        comentarios.add(new Comentario("4.5", null));
        HashSet<String> intereses = new HashSet<>();
        intereses.add("Lunes 9-11");
        intereses.add("Miercoles 12-2");
        HashSet<String> logros = new HashSet<>();
        logros.add("Logro1");
        logros.add("Logro2");
        HashSet<String> materias = new HashSet<>();
        materias.add("PensamientoS");
        materias.add("PensamientoA");
        materias.add("TeoriaC");
        materias.add("Probabilidad");
        Usuario usuario = new Estudiante("Nicolas", "kingb@javeriana.edu.co", materias, comentarios, "Hol@", intereses,
                logros);
                return usuario;
    }
}