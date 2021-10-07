package compagne.Entidades;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;

public class ChatP extends Chat{

    private Usuario usuario1;
    private Usuario usuario2;

    public Usuario getUsuario1() {
        return this.usuario1;
    }

    public Usuario getUsuario2() {
        return this.usuario2;
    }

    public ChatP(Date fechaCreacion, String codigo, char tipo, HashSet<Mensaje> mensajes, Usuario usuario1, Usuario usuario2) {
        super(fechaCreacion, codigo, tipo, mensajes);
        this.usuario1=usuario1;
        this.usuario2=usuario2;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ChatP)) {
            return false;
        }
        ChatP chatP = (ChatP) o;
        return Objects.equals(usuario1, chatP.usuario1) && Objects.equals(usuario2, chatP.usuario2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario1, usuario2);
    }

    @Override
    public String toString() {
        return "{" +
            " usuario1='" + getUsuario1() + "'" +
            ", usuario2='" + getUsuario2() + "'" +
            "}";
    }
    
}
