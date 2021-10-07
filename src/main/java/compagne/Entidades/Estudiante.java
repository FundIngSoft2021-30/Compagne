package compagne.Entidades;

import java.util.HashSet;
import java.util.Objects;

public class Estudiante extends Usuario {
    private String carrera;
    private String semestre;
    private HashSet<String> intereses;
    private HashSet<String> logros;

    public String getCarrera() {
        return this.carrera;
    }

    public String getSemestre() {
        return this.semestre;
    }

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
        return Objects.equals(carrera, estudiante.carrera) && Objects.equals(semestre, estudiante.semestre) && Objects.equals(intereses, estudiante.intereses) && Objects.equals(logros, estudiante.logros);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carrera, semestre, intereses, logros);
    }


    @Override
    public String toString() {
        return "{" +
            " carrera='" + getCarrera() + "'" +
            ", semestre='" + getSemestre() + "'" +
            ", intereses='" + getIntereses() + "'" +
            ", logros='" + getLogros() + "'" +
            "}";
    }

    public Estudiante(String nombre, String email, HashSet<String> materias, HashSet<Comentario> comentarios, String carrera, String semestre, String contrasenia,
            HashSet<String> intereses, HashSet<String> logros) {
        super(nombre, email, contrasenia, materias, comentarios);
        this.carrera = carrera;
        this.semestre = semestre;
        this.logros=logros;
        this.intereses = intereses;
    }
}
