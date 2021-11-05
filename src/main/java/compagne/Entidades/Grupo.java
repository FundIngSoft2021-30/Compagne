package compagne.Entidades;

import java.util.HashSet;
import java.util.Objects;

public class Grupo {
    private String nombre;
    private String codigo;
    private String publico;
    private HashSet <Usuario> miembros;
    
    //Reuniones
    private HashSet<Reunion> reuniones;
    private HashSet<ChatG> chats;

    public Grupo(String nombre, String codigo, String publico) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.publico = publico;
        this.miembros = new HashSet<>();
        this.reuniones = new HashSet<>();
        this.chats = new HashSet<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String isPublico() {
        return publico;
    }

    public void setPublico(String publico) {
        this.publico = publico;
    }

    public HashSet<Usuario> getMiembros() {
        return miembros;
    }

    public void setMiembros(HashSet<Usuario> miembros) {
        this.miembros = miembros;
    }

    public HashSet<Reunion> getReuniones() {
        return reuniones;
    }

    public void setReuniones(HashSet<Reunion> reuniones) {
        this.reuniones = reuniones;
    }

    public HashSet<ChatG> getChats() {
        return chats;
    }

    public void setChats(HashSet<ChatG> chats) {
        this.chats = chats;
    }
    

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Grupo)) {
            return false;
        }
        Grupo grupo = (Grupo) o;
        return Objects.equals(nombre, grupo.nombre) && Objects.equals(codigo, grupo.codigo) && Objects.equals(publico, grupo.publico)&& Objects.equals(miembros, grupo.miembros) && Objects.equals(reuniones, grupo.reuniones) && Objects.equals(chats, grupo.chats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, codigo, publico, miembros, reuniones, chats);
    }
    

    @Override
    public String toString() {
        return "{" +
            " nombre='" + getNombre() + "'" +
            ", codigo='" + getCodigo() + "'" +
            ", publico='" + isPublico() + "'" +
            ", miembros='" + getMiembros() + "'" +
            ", reuniones='" + getReuniones() + "'" +
            ", chats='" + getChats() + "'" +
            "}";
    }
    
    
}
