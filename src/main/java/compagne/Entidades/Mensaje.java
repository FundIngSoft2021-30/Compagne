package compagne.Entidades;
import java.util.Date;
import java.util.Objects;
public class Mensaje {
    private Date hora;
    private String mensaje;

    public Date getHora() {
        return this.hora;
    }

    public String getMensaje() {
        return this.mensaje;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Mensaje)) {
            return false;
        }
        Mensaje mensaje = (Mensaje) o;
        return Objects.equals(hora, mensaje.hora) && Objects.equals(mensaje, mensaje.mensaje);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hora, mensaje);
    }

    public Mensaje(Date hora, String mensaje) {
        this.hora = hora;
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "{" +
            " hora='" + getHora() + "'" +
            ", mensaje='" + getMensaje() + "'" +
            "}";
    }

}
