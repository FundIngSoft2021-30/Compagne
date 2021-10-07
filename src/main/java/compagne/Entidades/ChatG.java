package compagne.Entidades;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;

public class ChatG extends Chat{
    private Grupo pertenece;

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

    public ChatG(Date fechaCreacion, String codigo, char tipo, HashSet<Mensaje> mensajes) {
        super(fechaCreacion, codigo, tipo, mensajes);
        //TODO Auto-generated constructor stub
    }
    
}
