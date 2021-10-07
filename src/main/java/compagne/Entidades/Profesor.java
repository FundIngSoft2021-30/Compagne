package compagne.Entidades;

import java.util.HashSet;
import java.util.Objects;

public class Profesor extends Usuario {
    private String experiencia;
    private HashSet<String> horarioAtencion;

    public String getExperiencia() {
        return this.experiencia;
    }

    public HashSet<String> getHorarioAtencion() {
        return this.horarioAtencion;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Profesor)) {
            return false;
        }
        Profesor profesor = (Profesor) o;
        return Objects.equals(experiencia, profesor.experiencia)
                && Objects.equals(horarioAtencion, profesor.horarioAtencion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(experiencia, horarioAtencion);
    }

    @Override
    public String toString() {
        return "{" + super.toString() + ", experiencia='" + getExperiencia() + "'" + ", horarioAtencion='"
                + getHorarioAtencion() + "'" + "}";
    }

    public Profesor(String nombre, String email, HashSet<String> materias, HashSet<Comentario> comentarios, String experiencia, String contrasenia,
            HashSet<String> horarioAtencion) {
        super(nombre, email, contrasenia, materias, comentarios);
        this.experiencia = experiencia;
        this.horarioAtencion = horarioAtencion;
    }
}
