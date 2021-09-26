package compagne.model.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import compagne.model.ConnectionClass;
import compagne.model.UsuarioRegistrado;

public class UsuarioRegistradoControl {
    final ConnectionClass CONNECTION_CLASS = new ConnectionClass();
    private Connection con;
    private PreparedStatement statement;
    private ResultSet result;

    public HashSet<UsuarioRegistrado> findAllUsuariosRegistrados() {
        HashSet<UsuarioRegistrado> data = new HashSet<>();
        String sql = "SELECT * FROM " + ConnectionClass.getSchema() + ".\"UsuarioRegistrado\"";
        try {
            this.statement = this.con.prepareStatement(sql);
            this.result = this.statement.executeQuery();
            int offset = 0;
            if (ConnectionClass.usingPSQL())
                offset = 1;
            while (this.result.next())
                data.add(new UsuarioRegistrado(this.result.getInt(0 + offset), this.result.getString(1 + offset),
                        this.result.getInt(2 + offset), this.result.getString(3 + offset).charAt(0)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public boolean insertUsuarioRegistrado(final String NAME, final char TIPO) {
        boolean b = false;
        String sql = "INSERT INTO " + ConnectionClass.getSchema()
                + ".\"UsuarioRegistrado\" (\"Name\",\"Tipo\") VALUES ('" + NAME + "', '" + TIPO + "')";
        try {
            this.statement = this.con.prepareStatement(sql);
            this.statement.execute();
            b=true;
        } catch (Exception e) {
            b = false;
        }
        return b;
    }

    public boolean deleteUsuarioRegistrado(final String ID) {
        boolean b = false;
        String sql = "DELETE FROM " + ConnectionClass.getSchema() + ".\"UsuarioRegistrado\" WHERE \"ID\"=" + ID;
        try {
            this.statement = this.con.prepareStatement(sql);
            this.statement.execute();
            b=true;
        } catch (Exception e) {
            b = false;
        }
        return b;
    }

    public boolean updateUsuarioRegistradoName(final String ID, final String NAME) {
        boolean b = false;
        String sql = "UPDATE " + ConnectionClass.getSchema() + ".\"UsuarioRegistrado\" SET \"Name\"="+NAME+" WHERE \"ID\"=" + ID;
        try {
            this.statement = this.con.prepareStatement(sql);
            this.statement.execute();
            b=true;
        } catch (Exception e) {
            b = false;
        }
        return b;
    }

    public boolean updateUsuarioRegistradoTipo(final String ID, final char TIPO) {
        boolean b = false;
        String sql = "UPDATE " + ConnectionClass.getSchema() + ".\"UsuarioRegistrado\" SET \"Tipo\"="+TIPO+" WHERE \"ID\"=" + ID;
        try {
            this.statement = this.con.prepareStatement(sql);
            this.statement.execute();
            b=true;
        } catch (Exception e) {
            b = false;
        }
        return b;
    }

    public boolean updateUsuarioRegistradoIndoAdicionalID(final String ID, final String ADICIONAL_ID) {
        boolean b = false;
        String sql = "UPDATE " + ConnectionClass.getSchema() + ".\"UsuarioRegistrado\" SET \"Info adicionalID\"="+ADICIONAL_ID+" WHERE \"ID\"=" + ID;
        try {
            this.statement = this.con.prepareStatement(sql);
            this.statement.execute();
            b=true;
        } catch (Exception e) {
            b = false;
        }
        return b;
    }

    public UsuarioRegistradoControl() {
        this.con = this.CONNECTION_CLASS.getCon();
    }

}
