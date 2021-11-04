package compagne.Entidades;

import java.util.Objects;
import java.util.Date;

public class Reunion {
    private Date fecha;
    private String codigo;

    public Date getFecha() {
        return this.fecha;
    }

    public String getCodigo() {
        return this.codigo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Reunion)) {
            return false;
        }
        Reunion reunion = (Reunion) o;
        return Objects.equals(fecha, reunion.fecha) && Objects.equals(codigo, reunion.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fecha, codigo);
    }


    @Override
    public String toString() {
        return "{" +
            " fecha='" + getFecha() + "'" +
            ", codigo='" + getCodigo() + "'" +
            "}";
    }


}
