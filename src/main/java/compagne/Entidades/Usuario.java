package compagne.Entidades;

import java.util.HashSet;
import java.util.Objects;

public class Usuario{
    private String nombre;
    private String email;
    private HashSet<String> materias;

    public Usuario(String nombre, String email, HashSet<String> materias) {
        this.nombre = nombre;
        this.email = email;
        this.materias = materias;
    }

    @Override
    public String toString() {
        return
            " nombre='" + getNombre() + "'" +
            ", email='" + getEmail() + "'" +
            ", materias='" + getMaterias() + "'";
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getEmail() {
        return this.email;
    }

    public HashSet<String> getMaterias() {
        return this.materias;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Usuario)) {
            return false;
        }
        Usuario usuario = (Usuario) o;
        return Objects.equals(nombre, usuario.nombre) && Objects.equals(email, usuario.email) && Objects.equals(materias, usuario.materias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, email, materias);
    }
    
}
