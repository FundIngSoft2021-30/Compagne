package compagne.model;

import java.util.Objects;

public class UsuarioRegistrado{
    private int ID;
    private String nombre;
    private String email;
    private char tipo;
    private int infoAdicionalID;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }


    public int getInfoAdicionalID() {
        return this.infoAdicionalID;
    }

    public void setInfoAdicionalID(int infoAdicionalID) {
        this.infoAdicionalID = infoAdicionalID;
    }

    public char getTipo() {
        return this.tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public UsuarioRegistrado() {
    }

    public UsuarioRegistrado(int ID, String name, int infoAdicionalID, char tipo, String email) {
        this.ID = ID;
        this.nombre = name;
        this.infoAdicionalID = infoAdicionalID;
        this.tipo = tipo;
        this.email=email;
    }


    @Override
    public String toString() {
        return "{" +
            " ID='" + getID() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", email='" + getEmail() + "'" +
            ", tipo='" + getTipo() + "'" +
            ", infoAdicionalID='" + getInfoAdicionalID() + "'" +
            "}";
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UsuarioRegistrado)) {
            return false;
        }
        UsuarioRegistrado usuarioRegistrado = (UsuarioRegistrado) o;
        return ID == usuarioRegistrado.ID && Objects.equals(nombre, usuarioRegistrado.nombre)
                && infoAdicionalID == usuarioRegistrado.infoAdicionalID && tipo == usuarioRegistrado.tipo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
