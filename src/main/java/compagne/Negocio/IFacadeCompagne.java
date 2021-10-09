/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compagne.Negocio;

import compagne.Entidades.Comentario;
import compagne.Entidades.Estudiante;
import compagne.Entidades.Grupo;
import compagne.Entidades.Profesor;
import compagne.Entidades.Usuario;
import java.util.HashSet;

/**
 *
 * @author samue
 */
public interface IFacadeCompagne {
    
    //USUARIOS
    public Profesor crearPerfilProfesor(String nombre, String experiencia, String email, String contrasenia,
            HashSet<String> horariosAtencion, HashSet<String> materias, HashSet<Comentario> comentarios,
            HashSet<String> logros);
    public Profesor modificarPerfilProfesor(String nombre, String experiencia, String email, String contrasenia) ;
    public boolean eliminarPerfilProfesor(String email);
    public Estudiante crearPerfilEstudiante(String nombre, String email, String contrasenia, HashSet<String> materias,
            HashSet<Comentario> comentarios, HashSet<String> intereses, HashSet<String> logros);
    public Estudiante modificarPerfilEstudiante(String nombre, String email, String contrasenia);
    public boolean eliminarPerfilEstudiante(String email);
    public boolean calificarUsuario(Comentario calificacion, Usuario usuario);
    
    //GRUPOS
    public Grupo crearGrupo(String nombre, String codigo, String publico, Usuario usuario);
    public Grupo modificarGrupo(String nombre, String codigo, String publico);
    public boolean eliminarGrupo(String codigo);
    public boolean agregarUsuarioAGrupo(int grupoid, int idusuario, String admin);
    public boolean eliminarUsuarioDeGrupo(int grupoid, int idusuario);
    
    
    
}
