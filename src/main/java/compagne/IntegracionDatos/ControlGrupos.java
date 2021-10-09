package compagne.IntegracionDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import compagne.Entidades.Grupo;
import compagne.Entidades.Usuario;

public class ControlGrupos {

    final ConnectionClass CONNECTION_CLASS = new ConnectionClass();
    private Connection con;
    private PreparedStatement statement;
    private ResultSet result;

    public ResultSet executeQuery(String query) {
        try {
            this.statement = this.con.prepareStatement(query);
            this.result = this.statement.executeQuery();
        } catch (SQLException e) {
            this.result = null;
        }
        return this.result;
    }

    public int getGrupoID(String codigo) {
        /*
         * Este metodo retorna el ID de un grupo al cual busca por su codigo. Recibe :
         * email -> String que representa un email
         */
        int r = 0;
        int offset = 0;
        if (ConnectionClass.usingPSQL()) {
            offset = 1;
        }
        try {
            this.statement = this.con.prepareStatement("SELECT \"ID\" FROM " + ConnectionClass.getSchema()
                    + "\"GrupoEstudio\" WHERE \"Codigo\"=\'" + codigo + "\';");
            this.result = this.statement.executeQuery();
            if (this.result.next()) {
                r = this.result.getInt(0 + offset);
            } else {
                throw new Exception("No encontrado");
            }
        } catch (Exception e) {
            r = 0;
        }
        return r;
    }

    public boolean insertarUsuarioXGrupoEstudio(int grupoid, int idusuario, String admin) {
        /*
         * Inserta un usuario en un grupo. Recibe: grupoid-> int con el idusuario del
         * grupo, idusuario-> int con el id del usuario, admin -> String (S/N) que determina
         * si el usuario es admin
         */
        boolean b = true;
        String consulta = "INSERT INTO " + ConnectionClass.getSchema()
                + "\"UsuarioxGrupoEstudio\"(\"UsuarioRegistradoID\", \"GrupoEstudioID\", \"Admin\") VALUES (" + String.valueOf(idusuario) + ", "
                + String.valueOf(grupoid) + ",\'" + admin.toUpperCase() + "\');";
        try {
            this.statement = this.con.prepareStatement(consulta);
            this.statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    public boolean eliminarUsuarioXGrupoEstudio(int grupoid, int idusuario) {
        /*
         * Elimina un usuario en un grupo. Recibe: grupoid-> int con el idusuario del
         * grupo, idusuario-> int con el id del usuario, admin -> String (S/N) que determina
         * si el usuario es admin
         */
        boolean b = true;
        String consulta = "DELETE FROM " + ConnectionClass.getSchema()
                        + "\"UsuarioxGrupoEstudio\" WHERE \"UsuarioRegistradoID\"=" + 
                String.valueOf(idusuario) + " AND \"GrupoEstudioID\"= " + String.valueOf(grupoid) ;
        try {
            this.statement = this.con.prepareStatement(consulta);
            this.statement.executeQuery();
        } catch (Exception e) {
        }
        return b;
    }

    public boolean hacerAdminDeGrupo(int grupoid, int idusuario)
    {
         /*
         * Convierte a un usuario en administrador del grupo. Recibe: grupoid-> int con el idusuario del
         * grupo, idusuario-> int con el id del usuario
         */
         boolean b = true;
         String consulta = "UPDATE " + ConnectionClass.getSchema() + 
                 "\"UsuarioxGrupoEstudio\" SET \"Admin\"=\'S\' WHERE \"UsuarioRegistradoID\" = \""
                + idusuario+ "\" AND \"GrupoEstudioID\" =  \""+grupoid+"\" ;";
        try {
            this.statement = this.con.prepareStatement(consulta);
            this.statement.executeQuery();
        } catch (Exception e) {
        }
        return b;
        
    }
    public boolean crearGrupo(Grupo grupo, Usuario usuario) {
        /*
         * Este método guarda un grupo en la BD, recibe un objeto de la clase
         * grupo y hace que este persista en la BD . Recibe: grupo -> Instancia de
         * la clase Grupo, representa el grupo que quiere guardarse en la BD
         */
        boolean b = true; // Inicializo lo que retorno (Si el grupo se guardo bien)
        String sql = "INSERT INTO " + ConnectionClass.getSchema()
                + "\"GrupoEstudio\" (\"Nombre\",\"Publico\", \"Codigo\") VALUES ('"
                + grupo.getNombre() + "','"+grupo.isPublico()+"','" + grupo.getCodigo() + "');"; 
        // Consulta SQL para insertar
        // un grupo registrado en la BD

        try {
            this.statement = this.con.prepareStatement(sql); // Preparando una consulta SQL
            this.statement.execute(); // Ejecutando una consulta SQL
        } catch (Exception e) {
        }
        
        //Cada vez que se crea un grupo se debe agregar a la persona como administradora
        int usuarioid = this.getUsarioID(usuario.getEmail());// El ID del usuario que inserta el grupo
        int grupoid = this.getGrupoID(grupo.getCodigo());
        try {
            // Toca insertar el usuario y el grupo en UsuarioXGrupoEstudio
            this.insertarUsuarioXGrupoEstudio(grupoid, usuarioid, "S");
        } catch (Exception e) { // No pasa nada
        }
        // Fin de crear grupo
        return b;
    }

    public boolean eliminarGrupo(String codigo) {
        /*
         * Elimina un grupo de la BD. Recibe: codigo -> String que representa el codigo
         * de un grupo
         */
        boolean b = true;
        int id = this.getGrupoID(codigo);
        if (id == 0) {
            b = false;
        } else {
            try {
                //Borrar registros de grupo de UsuarioXGrupoEstudio
                String sql = "DELETE FROM " + ConnectionClass.getSchema()
                        + "\"UsuarioxGrupoEstudio\" WHERE \"GrupoEstudioID\"=" + String.valueOf(id);
                this.statement = this.con.prepareStatement(sql);
                this.result = this.statement.executeQuery();
            } catch (Exception e) {
            }       
            try {
                //Borrar grupo de GrupoEstudio
                String sql = "DELETE FROM " + ConnectionClass.getSchema() + "\"GrupoEstudio\" WHERE \"ID\"="
                        + String.valueOf(id);
                this.statement = this.con.prepareStatement(sql);
                this.result = this.statement.executeQuery();
            } catch (Exception e) {
            }
        }
        return b;
    }

    public boolean modificarGrupo(Grupo grupo) {
        /*
         * Modifica un grupo en la BD. Recibe: grupo -> Instancia de la clase
         * grupo que tiene los datos actualizados de un grupo, sin embargo, su
         * codigo no cambia
         */
        boolean b = true;
        String consulta = "UPDATE " + ConnectionClass.getSchema() + "\"GrupoEstudio\" SET \"Nombre\"=\'"
                + grupo.getNombre() + "\', \"Publico\"=\'" + grupo.isPublico()
                + "\'  WHERE \"Codigo\"=\'"
                + grupo.getCodigo()+ "\';";
        try {
            this.statement = this.con.prepareStatement(consulta);
            this.statement.executeQuery();
        } catch (Exception e) {
        }
        return b;
    }

    
    public int getUsarioID(String email) {
        /*
         * Este metodo retorna el ID de un usuario al cual busca por su mail. Recibe :
         * email -> String que representa un email
         */
        int r = 0;
        int offset = 0;
        if (ConnectionClass.usingPSQL())
            offset = 1;
        try {
            this.statement = this.con.prepareStatement("SELECT \"ID\" FROM " + ConnectionClass.getSchema()
                    + "\"UsuarioRegistrado\" WHERE \"Email\"=\'" + email + "\';");
            this.result = this.statement.executeQuery();
            if (this.result.next())
                r = this.result.getInt(0 + offset);
            else
                throw new Exception("No encontrado");
        } catch (Exception e) {
            r=0;
        }
        return r;
    }

    
    public ControlGrupos() {
        this.con = this.CONNECTION_CLASS.getCon();
        try {
            this.con.setAutoCommit(true);
        } catch (SQLException e) {
        }
    }

}
