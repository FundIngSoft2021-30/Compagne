package compagne.Entidades;

import java.util.HashSet;
import java.util.Objects;

public class ChatG extends Chat{
    private Grupo pertenece;


    public Grupo getPertenece() {
        return this.pertenece;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ChatG)) {
            return false;
        }
        ChatG chatG = (ChatG) o;
        return Objects.equals(pertenece, chatG.pertenece);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(pertenece);
    }

    public ChatG(String fechaCreacion, char tipo, HashSet<Mensaje> mensajes, Grupo pertenece) {
        super(fechaCreacion, tipo, mensajes);
        this.pertenece=pertenece;
    }


    @Override
    public String toString() {
        return "{" +
            " pertenece='" + getPertenece() + "'" +
            "}";
    }

    
}
