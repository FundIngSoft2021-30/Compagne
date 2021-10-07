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
        HashSet <String> h_aHashSet=new HashSet<>();
        h_aHashSet.add("Lunes 9-11");
        h_aHashSet.add("Miercoles 12-2");
        HashSet <String> logros=new HashSet<>();
        logros.add("Logro1");
        logros.add("Logro2");
        HashSet <String> materias=new HashSet<>();
        logros.add("PensamientoS");
        logros.add("PensamientoA");
        logros.add("TeoriaC");
        logros.add("Probabilidad");
        Profesor profe=new Profesor("Federico Lopez", "lopez.federico@javeriana.edu.co", materias, comentarios, "Solo profes PT", "la_contradefed$", h_aHashSet, logros);
        usc.crearProfesor(profe);
        profe.setExperiencia("Pase GTA a la primera");
        usc.modificarProfesor(profe);
        System.out.println("Modificado "+ profe);
        usc.eliminarProfesor(profe.getEmail());
        System.out.println("Eliminado" + profe);
    }
}
