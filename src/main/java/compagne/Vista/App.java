package compagne.Vista;

import java.util.HashSet;
import java.util.Scanner;

import compagne.Entidades.Comentario;
import compagne.Entidades.Profesor;
import compagne.IntegracionDatos.ControlProfesores;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ControlProfesores usc = new ControlProfesores();
        HashSet<Comentario> comentarios = new HashSet<>();
        comentarios.add(new Comentario(null, "Hola soy pepe"));
        comentarios.add(new Comentario("4.5", null));
        HashSet<String> h_aHashSet = new HashSet<>();
        h_aHashSet.add("Lunes 9-11");
        h_aHashSet.add("Miercoles 12-2");
        HashSet<String> logros = new HashSet<>();
        logros.add("Logro1");
        logros.add("Logro2");
        HashSet<String> materias = new HashSet<>();
        materias.add("PensamientoS");
        materias.add("PensamientoA");
        materias.add("TeoriaC");
        materias.add("Probabilidad");
        Profesor profe = new Profesor("Federico Lopez", "lopez.federico@javeriana.edu.co", materias, comentarios,
                "Solo profes PT", "la_contradefed$", h_aHashSet, logros);
        usc.crearProfesor(profe);
        System.out.println("Creado " + profe);
        sc.nextLine();
        profe.setExperiencia("Pase GTA a la primera");
        usc.modificarProfesor(profe);
        System.out.println("Modificado " + profe);
        sc.nextLine();
        comentarios.clear();
        comentarios.add(new Comentario(null, "Alcornoque"));
        comentarios.add(new Comentario(null, "Necio"));
        logros.add("No me desconcentro en clases virtuales");
        Profesor profe2 = new Profesor("Albert Perilla", "peri_al@javeriana.edu.co", materias, comentarios,
                "Solo profes PT", "la_contradefed$", h_aHashSet, logros);
        usc.crearProfesor(profe2);
        System.out.println("Agregado" + profe2);
        sc.nextLine();
        usc.eliminarProfesor(profe.getEmail());
        usc.eliminarProfesor(profe2.getEmail());
        System.out.println("Eliminado" + profe + '\n' + profe2);
        sc.close();
    }
}
