package compagne.Entidades;

import java.util.Date;
import java.util.Objects;

public class Mensaje {
    private Date hora;
    private String mensaje;
    private Usuario remitente;

    public Usuario getRemitente() {
        return this.remitente;
    }

    public Date getHora() {
        return this.hora;
    }

    public String getMensaje() {
        return this.mensaje;
    }

    public Mensaje(Date hora, String mensaje, Usuario remitente) {
        this.hora = hora;
        this.mensaje = mensaje;
        this.remitente = remitente;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Mensaje)) {
            return false;
        }
        Mensaje mensaje = (Mensaje) o;
        return Objects.equals(hora, mensaje.hora) && Objects.equals(mensaje, mensaje.mensaje)
                && Objects.equals(remitente, mensaje.remitente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hora, mensaje, remitente);
    }

    @Override
    public String toString() {
        return "{" + " hora='" + getHora() + "'" + ", mensaje='" + getMensaje() + "'" + ", remitente='" + getRemitente()
                + "'" + "}";
    }

}
