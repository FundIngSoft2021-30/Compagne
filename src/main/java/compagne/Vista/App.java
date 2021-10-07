package compagne.Vista;
import java.util.HashSet;

import compagne.Entidades.Comentario;
import compagne.Entidades.Profesor;
import compagne.IntegracionDatos.ControlProfesores;
public class App 
{    public static void main( String[] args )
    {
        ControlProfesores usc=new ControlProfesores();
        HashSet <Comentario> comentarios=new HashSet<>();
        comentarios.add(new Comentario(null, "Hola soy pepe"));
        comentarios.add(new Comentario("4.5", null));
        Profesor profe=new Profesor("Federico Lopez", "lopez.federico@javeriana.edu.co", null, comentarios, "Ninguna", "la_contradefed$", null);
        System.out.println(usc.crearProfesor(profe));
    }
}
