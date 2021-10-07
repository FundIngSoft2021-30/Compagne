package compagne.Entidades;

import java.util.Objects;

public class Rol {
    private String nombre;
    private String descripcion;

    public String getNombre() {
        return this.nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Rol)) {
            return false;
        }
        Rol rol = (Rol) o;
        return Objects.equals(nombre, rol.nombre) && Objects.equals(descripcion, rol.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, descripcion);
    }

    public Rol(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "{" +
            " nombre='" + getNombre() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            "}";
    }

}
