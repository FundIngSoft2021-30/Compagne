package compagne.Entidades;

import java.util.Objects;
import java.util.HashSet;

public class Grupo {
    private String nombre;
    private String descripcion;
    private String codigo;
    private boolean publico;
    private HashSet<Rol> roles;
    private HashSet <Usuario> miembros;
    
    //Reuniones
    private HashSet<Reunion> reuniones;
    private HashSet<ChatG> chats;

    public Grupo(String nombre, String descripcion, String codigo, boolean publico) {
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean isPublico() {
        return publico;
    }

    public void setPublico(boolean publico) {
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
