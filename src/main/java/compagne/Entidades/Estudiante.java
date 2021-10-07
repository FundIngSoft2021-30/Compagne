package compagne.Entidades;

import java.util.HashSet;
import java.util.Objects;

public class Estudiante extends Usuario {
    private String carrera;
    private String semestre;
    private HashSet<String> intereses;

    public String getCarrera() {
        return this.carrera;
    }

    public String getSemestre() {
        return this.semestre;
    }

    public HashSet<String> getIntereses() {
        return this.intereses;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Estudiante)) {
            return false;
        }
        Estudiante estudiante = (Estudiante) o;
        return Objects.equals(carrera, estudiante.carrera) && Objects.equals(semestre, estudiante.semestre)
                && Objects.equals(intereses, estudiante.intereses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carrera, semestre, intereses);
    }

    @Override
    public String toString() {
        return "{" + super.toString() + ", carrera='" + getCarrera() + "'" + ", semestre='" + getSemestre() + "'"
                + ", intereses='" + getIntereses() + "'" + "}";
    }

    public Estudiante(String nombre, String email, HashSet<String> materias, HashSet<Comentario> comentarios, String carrera, String semestre, String contrasenia,
            HashSet<String> intereses) {
        super(nombre, email, contrasenia, materias, comentarios);
        this.carrera = carrera;
        this.semestre = semestre;
        this.intereses = intereses;
    }
}
