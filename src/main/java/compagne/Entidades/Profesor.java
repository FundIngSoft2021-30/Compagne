package compagne.Entidades;

import java.util.HashSet;

public class Profesor extends Usuario {
    private String experiencia;
    private HashSet<String> horarioAtencion;
    private HashSet<String> logros;
    
    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }
    public void setHorarioAtencion(HashSet<String> horarioAtencion) {
        this.horarioAtencion = horarioAtencion;
    }
    public void setLogros(HashSet<String> logros) {
        this.logros = logros;
    }

    public String getExperiencia() {
        return this.experiencia;
    }

    public HashSet<String> getHorarioAtencion() {
        return this.horarioAtencion;
    }

    public HashSet<String> getLogros() {
        return this.logros;
    }

    @Override
    public String toString() {
        return "{" +
        super.toString()+
            ", experiencia='" + getExperiencia() + "'" +
            ", horarioAtencion='" + getHorarioAtencion() + "'" +
            ", logros='" + getLogros() + "'" +
            "}";
    }

    public Profesor(String nombre, String email, HashSet<String> materias, HashSet<Comentario> comentarios, String experiencia, String contrasenia,
            HashSet<String> horarioAtencion, HashSet<String> logros) {
        super(nombre, email, contrasenia, materias, comentarios);
        this.experiencia = experiencia;
        this.horarioAtencion = horarioAtencion;
        this.logros=logros;
    }
}
