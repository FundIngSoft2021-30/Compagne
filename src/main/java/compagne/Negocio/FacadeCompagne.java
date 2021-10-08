package compagne.Negocio;

import java.util.HashSet;

import compagne.Entidades.Comentario;
import compagne.Entidades.Estudiante;
import compagne.Entidades.Profesor;
import compagne.IntegracionDatos.ControlProfesores;
import compagne.IntegracionDatos.ControlEstudiantes;

public class FacadeCompagne {
    private ControlProfesores controlProfesores;
    private ControlEstudiantes controlEstudiantes;

    public FacadeCompagne() {
        this.controlEstudiantes = new ControlEstudiantes();
        this.controlProfesores = new ControlProfesores();
    }

    public Profesor crearPerfilProfesor(String nombre, String experiencia, String email, String contrasenia,
            HashSet<String> horariosAtencion, HashSet<String> materias, HashSet<Comentario> comentarios,
            HashSet<String> logros) {
        Profesor profesor = new Profesor(nombre, email, materias, comentarios, experiencia, contrasenia,
                horariosAtencion, logros);
        this.controlProfesores.crearProfesor(profesor);
        return profesor;
    }

    public Profesor modificarPerfilProfesor(String nombre, String experiencia, String email, String contrasenia) {
        // Email no se puede cambiar
        Profesor profesor = new Profesor(nombre, email, null, null, experiencia, contrasenia, null, null);
        this.controlProfesores.modificarProfesor(profesor);
        return profesor;
    }

    public boolean eliminarPerfilProfesor(String email) {
        return this.controlProfesores.eliminarProfesor(email);
    }

    public Estudiante crearPerfilEstudiante(String nombre, String email, String contrasenia, HashSet<String> materias,
            HashSet<Comentario> comentarios, HashSet<String> intereses, HashSet<String> logros) {
        Estudiante estudiante = new Estudiante(nombre, email, materias, comentarios, contrasenia, intereses, logros);
        this.controlEstudiantes.crearEstudiante(estudiante);
        return estudiante;
    }

    public Estudiante modificarPerfilEstudiante(String nombre, String email, String contrasenia) {
        Estudiante estudiante = new Estudiante(nombre, email, null, null, contrasenia, null, null);
        this.controlEstudiantes.crearEstudiante(estudiante);
        return estudiante;
    }

    public boolean eliminarPerfilEstudiante(String email) {
        return this.controlEstudiantes.eliminarEstudiante(email);
    }
}
