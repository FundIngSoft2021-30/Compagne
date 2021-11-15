package compagne.Entidades;

import java.util.Date;
import java.util.Objects;

public class Mensaje {
    
    private String hora;
    private String mensaje;
    private Usuario remitente;
    private Chat chat;

    public Usuario getRemitente() {
        return this.remitente;
    }

    public String getHora() {
        return this.hora;
    }

    public String getMensaje() {
        return this.mensaje;
    }

    public Chat getChat() {
        return this.chat;
    }

    public Mensaje(String hora, String mensaje, Usuario remitente, Chat chat) {
        this.hora = hora;
        this.mensaje = mensaje;
        this.remitente = remitente;
        this.chat=chat;
    }

    @Override
    public String toString() {
        return "{" +
            " hora='" + getHora() + "'" +
            ", mensaje='" + getMensaje() + "'" +
            ", remitente='" + getRemitente() + "'" +
            ", chat='" + getChat() + "'" +
            "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Mensaje)) {
            return false;
        }
        Mensaje mensaje = (Mensaje) o;
        return Objects.equals(hora, mensaje.hora) && Objects.equals(mensaje, mensaje.mensaje) && Objects.equals(remitente, mensaje.remitente) && Objects.equals(chat, mensaje.chat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hora, mensaje, remitente, chat);
    }

}
