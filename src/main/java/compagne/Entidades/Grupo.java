package compagne.Entidades;

import java.util.HashSet;

public class Grupo {
    private String nombre;
    private String codigo;
    private String publico;
    private HashSet<Rol> roles;
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

    public HashSet<Rol> getRoles() {
        return roles;
    }

    public void setRoles(HashSet<Rol> roles) {
        this.roles = roles;
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
    
    
    
    
}
