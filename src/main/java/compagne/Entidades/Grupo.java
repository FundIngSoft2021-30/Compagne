package compagne.Entidades;

import java.util.Objects;
import java.util.HashSet;

public class Grupo {
    private String nombre;
    private String descripcion;
    private String codigo;
    private boolean privado;
    // private HashSet<Rol> roles;
    private HashSet<Reunion> reuniones;
    private HashSet<Chat> chats;
    // private HashSet <Usuario> miembros;
}
