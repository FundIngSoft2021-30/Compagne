package compagne.Entidades;

import java.util.HashSet;
import java.util.Objects;

public abstract class Usuario{
    private String nombre;
    private String email;
    private HashSet<String> materias;
    private HashSet<Comentario> comentarios;

    public String getNombre() {
        return this.nombre;
    }

    public String getEmail() {
        return this.email;
    }

    public HashSet<String> getMaterias() {
        return this.materias;
    }

    public HashSet<Comentario> getComentarios() {
        return this.comentarios;
    }

    public Usuario(String nombre, String email, HashSet<String> materias, HashSet<Comentario> comentarios) {
        this.nombre = nombre;
        this.email = email;
        this.materias = materias;
        this.comentarios = comentarios;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Usuario)) {
            return false;
        }
        Usuario usuario = (Usuario) o;
        return Objects.equals(nombre, usuario.nombre) && Objects.equals(email, usuario.email) && Objects.equals(materias, usuario.materias) && Objects.equals(comentarios, usuario.comentarios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, email, materias, comentarios);
    }

    @Override
    public String toString() {
        return "{" +
            " nombre='" + getNombre() + "'" +
            ", email='" + getEmail() + "'" +
            ", materias='" + getMaterias() + "'" +
            ", comentarios='" + getComentarios() + "'" +
            "}";
    }
    
    
}
