package compagne.IntegracionDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import compagne.Entidades.Chat;
import compagne.Entidades.ChatG;
import compagne.Entidades.Grupo;
import compagne.Entidades.Mensaje;
import compagne.Entidades.Reunion;
import compagne.Entidades.Usuario;

public class ControlGrupos {

    final ConnectionClass CONNECTION_CLASS = new ConnectionClass();
    private Connection con;
    private PreparedStatement statement;
    private ResultSet result;

    public void finalize() {
        try {
            this.con.close();
        } catch (SQLException e) {
        }
    }

    public ResultSet executeQuery(String query) {
        try {
            this.statement = this.con.prepareStatement(query);
            this.result = this.statement.executeQuery();
        } catch (SQLException e) {
            this.result = null;
        }
        return this.result;
    }

    public HashSet<Grupo> getGruposPublicos() {
        int offset = 0;
        if (ConnectionClass.usingPSQL()) {
            offset = 1;
        }
        HashSet<Grupo> grupos = new HashSet<>();
        Grupo grupo;
        String query = "SELECT \"Nombre\",\"Codigo\", \"Publico\" FROM " + ConnectionClass.getSchema()
                + "\"GrupoEstudio\" WHERE \"Publico\"=\'S\';";
        try {
            this.statement = this.con.prepareStatement(query);
            this.result = this.statement.executeQuery();
            while (this.result.next()) {
                grupo = new Grupo(this.result.getString(0 + offset), this.result.getString(1 + offset),
                        this.result.getString(2 + offset));
                grupos.add(grupo);
            }
        } catch (SQLException e) {
            this.result = null;
        }
        return grupos;
    }

    public Grupo getGrupoByID(int id) {
        int offset = 0;
        if (ConnectionClass.usingPSQL()) {
            offset = 1;
        }
        Grupo grupo = null;
        String query = "SELECT \"Nombre\",\"Codigo\", \"Publico\" FROM " + ConnectionClass.getSchema()
                + "\"GrupoEstudio\" WHERE \"ID\"=" + id + ";";
        try {
            this.statement = this.con.prepareStatement(query);
            this.result = this.statement.executeQuery();
            if (this.result.next()) {
                ControlEstudiantes controlEstudiantes = new ControlEstudiantes();
                ControlProfesores controlProfesores = new ControlProfesores();
                String nombre = this.result.getString(0 + offset);
                String codigo = this.result.getString(1 + offset);
                String publico = this.result.getString(2 + offset);
                HashSet<Usuario> usuarios = new HashSet<>();
                HashSet<Reunion> reuniones = new HashSet<>();
                HashSet<ChatG> chats = new HashSet<>();
                // Sacar Miembros
                query = "SELECT \"UsuarioRegistradoID\" FROM " + ConnectionClass.getSchema()
                        + "\"UsuarioxGrupoEstudio\" WHERE \"GrupoEstudioID\"=" + id + ";";
                try {
                    this.statement = this.con.prepareStatement(query);
                    this.result = this.statement.executeQuery();
                    while (this.result.next()) {
                        Usuario usuario = controlEstudiantes.getEstudianteByID(this.result.getInt(0 + offset));
                        if (usuario == null)
                            usuario = controlProfesores.getProfesorByID(this.result.getInt(0 + offset));
                        usuarios.add(usuario);
                    }
                } catch (Exception e) {
                    // Error sacando miembros del grupo
                }
                // Sacar Reuniones
                query = "SELECT \"ReunionID\" FROM " + ConnectionClass.getSchema()
                        + "\"ReunionXGrupoEstudio\" WHERE \"GrupoEstudioID\"=" + id + ";";
                try {
                    this.statement = this.con.prepareStatement(query);
                    this.result = this.statement.executeQuery();
                    while (this.result.next()) {
                        ResultSet rs;
                        query = "SELECT \"Fecha\" FROM " + ConnectionClass.getSchema() + "\"Reunion\" WHERE \"ID\"="
                                + this.result.getInt(0 + offset) + ";";
                        try {
                            this.statement = this.con.prepareStatement(query);
                            rs = this.statement.executeQuery();
                            reuniones.add(new Reunion(rs.getDate(0 + offset)));
                        } catch (Exception e) {
                            // Error sacando reuniones del grupo
                        }
                    }
                } catch (Exception e) {
                    // Error sacando reuniones del grupo
                }
                // Sacar Chats
                query = "SELECT \"ID\" FROM " + ConnectionClass.getSchema() + "\"Chat\" WHERE \"PerteneceID\"=" + id
                        + ";";
                try {
                    this.statement = this.con.prepareStatement(query);
                    this.result = this.statement.executeQuery();
                    while (this.result.next()) {
                        int chatID = this.result.getInt(0 + offset);
                        ResultSet rs;
                        query = "SELECT ME.\"Mensaje\",ME.\"Fecha\",ME.\"RemitenteID\" FROM "
                                + ConnectionClass.getSchema() + "\"Mensaje\" AS ME, " + ConnectionClass.getSchema()
                                + "\"ChatXMensaje\" AS CME WHERE CME.\"ChatID\"=" + chatID
                                + "AND ME.\"ID\"=CME.\"MensajeID\"" + ";";
                        try {
                            this.statement = this.con.prepareStatement(query);
                            rs = this.statement.executeQuery();
                            Chat chat = null;
                            HashSet<Mensaje> mensajes = new HashSet<>();
                            while (rs.next()) {
                                Usuario user=controlEstudiantes.getEstudianteByID(rs.getInt(2 + offset));
                                if (user==null)
                                    user=controlProfesores.getProfesorByID(rs.getInt(2 + offset));
                                Mensaje mensaje = new Mensaje(rs.getString(1 + offset), rs.getString(0 + offset),
                                        user, chat);
                                mensajes.add(mensaje);
                            }
                            chats.add(new ChatG(null, 'G', mensajes, null));
                        } catch (Exception e) {
                            // Error sacando chats del grupo
                        }
                    }
                } catch (Exception e) {
                    // Error sacando chats del grupo
                }
                grupo = new Grupo(nombre, codigo, publico, usuarios, reuniones, chats);
            }
        } catch (SQLException e) {
            this.result = null;
        }
        return grupo;
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

    public boolean insertarUsuarioXGrupoEstudio(String cod, String email, String admin) {
        /*
         * Inserta un usuario en un grupo. Recibe: grupoid-> int con el idusuario del
         * grupo, idusuario-> int con el id del usuario, admin -> String (S/N) que
         * determina si el usuario es admin
         */
        boolean b = true;
        int idusuario = this.getUsarioID(email);
        int grupoid = this.getGrupoID(cod);
        String consulta = "INSERT INTO " + ConnectionClass.getSchema()
                + "\"UsuarioxGrupoEstudio\"(\"UsuarioRegistradoID\", \"GrupoEstudioID\", \"Admin\") VALUES ("
                + String.valueOf(idusuario) + ", " + String.valueOf(grupoid) + ",\'" + admin.toUpperCase() + "\');";
        try {
            this.statement = this.con.prepareStatement(consulta);
            this.statement.executeQuery();
        } catch (Exception e) {
        }
        return b;
    }

    public boolean eliminarUsuarioXGrupoEstudio(String cod, String email) {
        /*
         * Elimina un usuario en un grupo. Recibe: grupoid-> int con el idusuario del
         * grupo, idusuario-> int con el id del usuario, admin -> String (S/N) que
         * determina si el usuario es admin
         */
        boolean b = true;
        int idusuario = this.getUsarioID(email);
        int grupoid = this.getGrupoID(cod);
        String consulta = "DELETE FROM " + ConnectionClass.getSchema()
                + "\"UsuarioxGrupoEstudio\" WHERE \"UsuarioRegistradoID\"=" + String.valueOf(idusuario)
                + " AND \"GrupoEstudioID\"= " + String.valueOf(grupoid);
        try {
            this.statement = this.con.prepareStatement(consulta);
            this.statement.executeQuery();
        } catch (Exception e) {
        }
        return b;
    }

    public boolean hacerAdminDeGrupo(int grupoid, int idusuario) {
        /*
         * Convierte a un usuario en administrador del grupo. Recibe: grupoid-> int con
         * el idusuario del grupo, idusuario-> int con el id del usuario
         */
        boolean b = true;
        String consulta = "UPDATE " + ConnectionClass.getSchema()
                + "\"UsuarioxGrupoEstudio\" SET \"Admin\"=\'S\' WHERE \"UsuarioRegistradoID\" = \"" + idusuario
                + "\" AND \"GrupoEstudioID\" =  \"" + grupoid + "\" ;";
        try {
            this.statement = this.con.prepareStatement(consulta);
            this.statement.executeQuery();
        } catch (Exception e) {
        }
        return b;

    }

    public boolean crearGrupo(Grupo grupo, String usuario) {
        /*
         * Este método guarda un grupo en la BD, recibe un objeto de la clase grupo y
         * hace que este persista en la BD . Recibe: grupo -> Instancia de la clase
         * Grupo, representa el grupo que quiere guardarse en la BD
         */
        boolean b = true; // Inicializo lo que retorno (Si el grupo se guardo bien)
        String sql = "INSERT INTO " + ConnectionClass.getSchema()
                + "\"GrupoEstudio\" (\"Nombre\",\"Publico\", \"Codigo\") VALUES ('" + grupo.getNombre() + "','"
                + grupo.isPublico() + "','" + grupo.getCodigo() + "');";
        // Consulta SQL para insertar
        // un grupo registrado en la BD

        try {
            this.statement = this.con.prepareStatement(sql); // Preparando una consulta SQL
            this.statement.execute(); // Ejecutando una consulta SQL
        } catch (Exception e) {
        }
        try {
            // Toca insertar el usuario y el grupo en UsuarioXGrupoEstudio
            this.insertarUsuarioXGrupoEstudio(grupo.getCodigo(), usuario, "S");
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
                // Borrar registros de grupo de UsuarioXGrupoEstudio
                String sql = "DELETE FROM " + ConnectionClass.getSchema()
                        + "\"UsuarioxGrupoEstudio\" WHERE \"GrupoEstudioID\"=" + String.valueOf(id);
                this.statement = this.con.prepareStatement(sql);
                this.result = this.statement.executeQuery();
            } catch (Exception e) {
            }
            try {
                // Borrar grupo de GrupoEstudio
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
         * Modifica un grupo en la BD. Recibe: grupo -> Instancia de la clase grupo que
         * tiene los datos actualizados de un grupo, sin embargo, su codigo no cambia
         */
        boolean b = true;
        String consulta = "UPDATE " + ConnectionClass.getSchema() + "\"GrupoEstudio\" SET \"Nombre\"=\'"
                + grupo.getNombre() + "\', \"Publico\"=\'" + grupo.isPublico() + "\'  WHERE \"Codigo\"=\'"
                + grupo.getCodigo() + "\';";
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
            r = 0;
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
