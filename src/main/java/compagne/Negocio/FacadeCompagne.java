package compagne.Negocio;

import java.util.HashSet;

import compagne.Entidades.Comentario;
import compagne.Entidades.Estudiante;
import compagne.Entidades.Profesor;
import compagne.Entidades.Usuario;
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
        if (!this.controlProfesores.crearProfesor(profesor))
            profesor = new Profesor(null, null, null, null, null, null, null, null);
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
        if (!this.controlEstudiantes.crearEstudiante(estudiante))
            estudiante = new Estudiante(null, null, null, null, null, null, null);
        return estudiante;
    }

    public Estudiante modificarPerfilEstudiante(String nombre, String email, String contrasenia) {
        Estudiante estudiante = new Estudiante(nombre, email, null, null, contrasenia, null, null);
        this.controlEstudiantes.modificarEstudiante(estudiante);
        return estudiante;
    }

    public boolean eliminarPerfilEstudiante(String email) {
        return this.controlEstudiantes.eliminarEstudiante(email);
    }

    public boolean calificarUsuario(Comentario calificacion, Usuario usuario) {
        boolean b = true;
        try {
            if (usuario instanceof Estudiante) {
                Estudiante estudiante = (Estudiante) (usuario);
                int user_id = this.controlEstudiantes.getEstudianteID(usuario.getEmail());
                int comentario_id = this.controlEstudiantes.getComentarioID(calificacion.getComentario(),
                        calificacion.getCalificacion());
                if (user_id == 0) {
                    this.controlEstudiantes.crearEstudiante(estudiante);
                    user_id = this.controlEstudiantes.getEstudianteID(estudiante.getEmail());
                }
                if (comentario_id == 0) {
                    this.controlEstudiantes.insertarComentario(calificacion.getComentario(),
                            calificacion.getCalificacion());
                    comentario_id = this.controlEstudiantes.getComentarioID(calificacion.getComentario(),
                            calificacion.getCalificacion());
                }
                this.controlEstudiantes.insertarComentarioXEstudiante(user_id, comentario_id);
            } else {
                Profesor profesor = (Profesor) (usuario);
                int user_id = this.controlProfesores.getProfesorID(usuario.getEmail());
                int comentario_id = this.controlEstudiantes.getComentarioID(calificacion.getComentario(),
                        calificacion.getCalificacion());
                if (user_id == 0) {
                    this.controlProfesores.crearProfesor(profesor);
                    user_id = this.controlProfesores.getProfesorID(profesor.getEmail());
                }
                if (comentario_id == 0) {
                    this.controlProfesores.insertarComentario(calificacion.getComentario(),
                            calificacion.getCalificacion());
                    comentario_id = this.controlProfesores.getComentarioID(calificacion.getComentario(),
                            calificacion.getCalificacion());
                }
                this.controlProfesores.insertarComentarioXProfesor(user_id, comentario_id);
            }
        } catch (Exception e) {
            b = false;
        }
        return b;
    }
}
