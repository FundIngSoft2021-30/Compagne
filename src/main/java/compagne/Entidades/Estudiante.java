package compagne.Entidades;

import java.util.HashSet;
import java.util.Objects;

public class Estudiante extends Usuario {
    private HashSet<String> intereses;
    private HashSet<String> logros;
    
    

    public HashSet<String> getIntereses() {
        return this.intereses;
    }

    public HashSet<String> getLogros() {
        return this.logros;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Estudiante)) {
            return false;
        }
        Estudiante estudiante = (Estudiante) o;
        return Objects.equals(intereses, estudiante.intereses) && Objects.equals(logros, estudiante.logros);
    }

    @Override
    public int hashCode() {
        return Objects.hash(intereses, logros);
    }


    @Override
    public String toString() {
        return "{" +
            super.toString()+", intereses='" + getIntereses() + "'" +
            ", logros='" + getLogros() + "'" +
            "}";
    }

    public Estudiante(String nombre, String email, HashSet<String> materias, HashSet<Comentario> comentarios, String contrasenia,
            HashSet<String> intereses, HashSet<String> logros) {
        super(nombre, email, contrasenia, materias, comentarios);
        this.logros=logros;
        this.intereses = intereses;
    }
}
