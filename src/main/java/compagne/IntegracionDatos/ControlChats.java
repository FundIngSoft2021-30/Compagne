package compagne.IntegracionDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;

import org.postgresql.jdbc.TimestampUtils;

import compagne.Entidades.Chat;
import compagne.Entidades.ChatG;
import compagne.Entidades.ChatP;
import compagne.Entidades.Comentario;
import compagne.Entidades.Estudiante;
import compagne.Entidades.Grupo;
import compagne.Entidades.Mensaje;
import compagne.Entidades.Profesor;
import compagne.Entidades.Usuario;

public class ControlChats {
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

    public boolean crearChat(Chat chat, int grupoID) {
        /*
         * Este método guarda un chat en la BD, recibe un objeto de la clase chat y un
         * id de un grupo y hace que este persista en la BD . Recibe: chat -> Instancia
         * de la clase Chat, representa el chat que quiere guardarse en la BD, grupoID
         * -> representa el ID del grupo al que pertenece el chat
         */
        boolean b = true; // Inicializo lo que retorno (Si el grupo se guardo bien)
        String sql = "INSERT INTO " + ConnectionClass.getSchema()
                + "\"Chat\" (\"Codigo\",\"FechaCreacion\", \"Tipo\", \"PerteneceID\") VALUES ('" + chat.getCodigo()
                + "','" + TimestampUtils.parseBackendTimeZone(chat.getFechaCreacion().toString()) + "','"
                + chat.getTipo() + "'," + grupoID + ");";
        // Consulta SQL para insertar
        // un chat en la BD
        try {
            this.statement = this.con.prepareStatement(sql); // Preparando una consulta SQL
            this.statement.execute(); // Ejecutando una consulta SQL
        } catch (Exception e) {
        }
        // Fin de crear grupo
        return b;
    }

    public boolean crearChat(Chat chat, int usuario1ID, int usuario2ID) {
        /*
         * Este método guarda un chat en la BD, recibe un objeto de la clase chat y un
         * dos id de usuarios y hace que este persista en la BD . Recibe: chat ->
         * Instancia de la clase Chat, representa el chat que quiere guardarse en la BD,
         * usuario1ID -> representa el ID del primerUsuario al que pertenece el chat,
         * usuario2ID -> representa el ID del segundoUsuario al que pertenece el chat
         */
        boolean b = true; // Inicializo lo que retorno (Si el grupo se guardo bien)
        String sql = "INSERT INTO " + ConnectionClass.getSchema()
                + "\"Chat\" (\"Codigo\",\"FechaCreacion\", \"Tipo\", \"Usuario1ID\", \"Usuario2ID\") VALUES ('"
                + chat.getCodigo() + "','" + TimestampUtils.parseBackendTimeZone(chat.getFechaCreacion().toString())
                + "','" + chat.getTipo() + "'," + usuario1ID + "," + usuario2ID + ");";
        // Consulta SQL para insertar
        // un chat en la BD
        try {
            this.statement = this.con.prepareStatement(sql); // Preparando una consulta SQL
            this.statement.execute(); // Ejecutando una consulta SQL
        } catch (Exception e) {
        }
        // Fin de crear grupo
        return b;
    }

    public HashSet<Integer> buscarChat(int grupoID) {
        HashSet<Integer> chats = new HashSet<>();
        /*
         * Este método busca chats por grupo, recibe un id de un grupo y devuelve un id
         * de varios chats
         */
        String sql = "SELECT \"ID\" FROM " + ConnectionClass.getSchema() + "\"Chat\" WHERE \"PerteneceID\" = " + grupoID
                + ";";
        // Consulta SQL para buscar chats por grupo en la BD
        ResultSet rs = this.executeQuery(sql);
        try {
            while (rs.next())
                chats.add(rs.getInt("ID"));
        } catch (Exception e) {
        }
        return chats;
    }

    public HashSet<ChatG> buscarChatsXGrupo(int grupoID) {
        HashSet<ChatG> chats = new HashSet<>();
        for (int chatID : this.buscarChat(grupoID)) {
            ChatG chat = this.buscarChatG(chatID);
            if (chat != null)
                chats.add(chat);
        }
        return chats;
    }

    public HashSet<ChatP> buscarChatsXUsuario(int usuarioID) {
        HashSet<ChatP> chats = new HashSet<>();
        for (int chatID : this.buscarChat(usuarioID, 'P')) {
            ChatP chat = this.buscarChatP(chatID);
            if (chat != null)
                chats.add(chat);
        }
        return chats;
    }

    private ChatP buscarChatP(int chatID) {
        ChatP chat = null;
        /*
         * Este método busca un chat grupal por id, recibe un id de un chat y devuelve
         * un objeto de la clase chat grupal
         */
        String sql = "SELECT \"FechaCreacion\",\"Codigo\",\"Usuario1ID\", \"Usuario2ID\" FROM "
                + ConnectionClass.getSchema() + "\"Chat\" WHERE \"ID\" = " + chatID + ";";
        // Consulta SQL para buscar chats por grupo en la BD
        ResultSet rs = this.executeQuery(sql);
        try {
            int u1ID = rs.getInt("Usuario1ID");
            int u2ID = rs.getInt("Usuario2ID");
            // Buscar un usuario por id
            sql = "SELECT \"Nombre\",\"Email\", \"Tipo\" FROM " + ConnectionClass.getSchema()
                    + "\"UsuarioRegistrado\" WHERE \"ID\" = " + u1ID + ";";
            ResultSet rs2 = this.executeQuery(sql);
            sql = "SELECT \"Nombre\",\"Email\", \"Tipo\" FROM " + ConnectionClass.getSchema()
                    + "\"UsuarioRegistrado\" WHERE \"ID\" = " + u2ID + ";";
            ResultSet rs3 = this.executeQuery(sql);
            if (rs.next() && rs2.next() && rs3.next()) {
                String nombre = rs2.getString("Nombre");
                String email = rs2.getString("Email");
                HashSet<String> materias = new HashSet<>();
                HashSet<Comentario> comentarios = new HashSet<>();
                Usuario u1 = (rs2.getString("Tipo").equals("S"))
                        ? new Estudiante(nombre, email, materias, comentarios, "", materias, materias)
                        : new Profesor(nombre, email, materias, comentarios, "", "", materias, materias);
                nombre = rs3.getString("Nombre");
                email = rs3.getString("Email");
                Usuario u2 = (rs3.getString("Tipo").equals("S"))
                        ? new Estudiante(nombre, email, materias, comentarios, "", materias, materias)
                        : new Profesor(nombre, email, materias, comentarios, "", "", materias, materias);
                ;
                chat = new ChatP(new Date(rs.getTimestamp("FechaCreacion").getTime()), rs.getString("Codigo"), 'G',
                        new HashSet<Mensaje>(), u1, u2);
            }
        } catch (Exception e) {
        }
        return chat;
    }

    private ChatG buscarChatG(int chatID) {
        ChatG chat = null;
        /*
         * Este método busca un chat grupal por id, recibe un id de un chat y devuelve
         * un objeto de la clase chat grupal
         */
        String sql = "SELECT \"FechaCreacion\",\"Codigo\",\"PerteneceID\" FROM " + ConnectionClass.getSchema()
                + "\"Chat\" WHERE \"ID\" = " + chatID + ";";
        // Consulta SQL para buscar chats por grupo en la BD
        ResultSet rs = this.executeQuery(sql);
        try {
            int grupoID = rs.getInt("PerteneceID");
            // Buscar un grupo por id
            sql = "SELECT \"Nombre\",\"Codigo\",\"Publico\" FROM " + ConnectionClass.getSchema()
                    + "\"GrupoEstudio\" WHERE \"ID\" = " + grupoID + ";";
            ResultSet rs2 = this.executeQuery(sql);
            if (rs.next() && rs2.next()) {
                String nombre = rs2.getString("Nombre");
                String codigo = rs2.getString("Codigo");
                String publico = (rs2.getBoolean("Publico")) ? "Si" : "No";
                chat = new ChatG(new Date(rs.getTimestamp("FechaCreacion").getTime()), rs.getString("Codigo"), 'G',
                        new HashSet<Mensaje>(), new Grupo(nombre, codigo, publico));
            }
        } catch (Exception e) {
        }
        return chat;
    }

    public HashSet<Integer> buscarChat(int usuarioID, char tipo) {
        HashSet<Integer> chats = new HashSet<>();
        /*
         * Este método busca chats por usuario, recibe un id de un usuario, un tipo para
         * diferenciarlo del metodo anterior y devuelve un id de varios chats
         */
        String sql = "SELECT \"ID\" FROM " + ConnectionClass.getSchema() + "\"Chat\" WHERE \"Usuario1ID\" = "
                + usuarioID + " OR \"Usuario2ID\" = " + usuarioID + ";";
        // Consulta SQL para buscar chats por grupo en la BD
        ResultSet rs = this.executeQuery(sql);
        try {
            while (rs.next())
                chats.add(rs.getInt("ID"));
        } catch (Exception e) {
        }
        return chats;
    }

    public ControlChats() {
        this.con = this.CONNECTION_CLASS.getCon();
        try {
            this.con.setAutoCommit(true);
        } catch (SQLException e) {
        }
    }

}