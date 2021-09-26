package compagne.model;

import java.util.Objects;

public class UsuarioRegistrado{
    private int ID;
    private String name;
    private int infoAdicionalID;
    private char tipo;

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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

    public UsuarioRegistrado(int ID, String name, int infoAdicionalID, char tipo) {
        this.ID = ID;
        this.name = name;
        this.infoAdicionalID = infoAdicionalID;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "\n\t{" + "\n\t\tID='" + ID + "'" + "\n\t\tname='" + name + "'" + "\n\t\tinfoAdicionalID='" + infoAdicionalID + "'"
                + "\n\t\ttipo='" + tipo + "'" + "\n\t}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UsuarioRegistrado)) {
            return false;
        }
        UsuarioRegistrado usuarioRegistrado = (UsuarioRegistrado) o;
        return ID == usuarioRegistrado.ID && Objects.equals(name, usuarioRegistrado.name)
                && infoAdicionalID == usuarioRegistrado.infoAdicionalID && tipo == usuarioRegistrado.tipo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
