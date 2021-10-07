package compagne.Entidades;

import java.util.Date;
import java.util.Objects;
import java.util.HashSet;

public abstract class Chat {
    private Date fechaCreacion;
    private String codigo;
    private char tipo;
    private HashSet<Mensaje> mensajes;


    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public char getTipo() {
        return this.tipo;
    }

    public HashSet<Mensaje> getMensajes() {
        return this.mensajes;
    }

    public Chat(Date fechaCreacion, String codigo, char tipo, HashSet<Mensaje> mensajes) {
        this.fechaCreacion = fechaCreacion;
        this.codigo = codigo;
        this.tipo = tipo;
        this.mensajes = mensajes;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Chat)) {
            return false;
        }
        Chat chat = (Chat) o;
        return Objects.equals(fechaCreacion, chat.fechaCreacion) && Objects.equals(codigo, chat.codigo) && tipo == chat.tipo && Objects.equals(mensajes, chat.mensajes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaCreacion, codigo, tipo, mensajes);
    }


    @Override
    public String toString() {
        return "{" +
            " fechaCreacion='" + getFechaCreacion() + "'" +
            ", codigo='" + getCodigo() + "'" +
            ", tipo='" + getTipo() + "'" +
            ", mensajes='" + getMensajes() + "'" +
            "}";
    }
    

}
