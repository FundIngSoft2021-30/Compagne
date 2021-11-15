package compagne.Entidades;

import java.util.Objects;
import java.util.Date;

public class Reunion {
    private Date fecha;

    public Date getFecha() {
        return this.fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Reunion)) {
            return false;
        }
        Reunion reunion = (Reunion) o;
        return Objects.equals(fecha, reunion.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fecha);
    }


    @Override
    public String toString() {
        return "{" +
            " fecha='" + getFecha() + "'" +
            "}";
    }


    public Reunion(Date fecha) {
        this.fecha = fecha;
    }


}
