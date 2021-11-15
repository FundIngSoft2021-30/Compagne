package compagne.Entidades;

import java.util.Objects;
import java.util.HashSet;

public abstract class Chat {
    private String fechaCreacion;
    private char tipo;
    private HashSet<Mensaje> mensajes;


    public String getFechaCreacion() {
        return this.fechaCreacion;
    }

    public char getTipo() {
        return this.tipo;
    }

    public HashSet<Mensaje> getMensajes() {
        return this.mensajes;
    }

    public Chat(String fechaCreacion, char tipo, HashSet<Mensaje> mensajes) {
        this.fechaCreacion = fechaCreacion;
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
        return Objects.equals(fechaCreacion, chat.fechaCreacion) && tipo == chat.tipo && Objects.equals(mensajes, chat.mensajes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaCreacion, tipo, mensajes);
    }


    @Override
    public String toString() {
        return "{" +
            " fechaCreacion='" + getFechaCreacion() + "'" +
            ", tipo='" + getTipo() + "'" +
            ", mensajes='" + getMensajes() + "'" +
            "}";
    }
    

}
