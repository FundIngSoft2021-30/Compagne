package compagne.Entidades;

import java.util.Objects;

public class Comentario {

    private String calificacion;
    private String comentario;

    public String getCalificacion() {
        return this.calificacion;
    }

    public String getComentario() {
        return this.comentario;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Comentario)) {
            return false;
        }
        Comentario comentario = (Comentario) o;
        return Objects.equals(calificacion, comentario.calificacion) && Objects.equals(comentario, comentario.comentario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(calificacion, comentario);
    }

    @Override
    public String toString() {
        return "{" +
            " calificacion='" + getCalificacion() + "'" +
            ", comentario='" + getComentario() + "'" +
            "}";
    }


    public Comentario(String calificacion, String comentario) {
        this.calificacion = calificacion;
        this.comentario = comentario;
    }

    
}
