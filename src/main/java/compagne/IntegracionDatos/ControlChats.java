package compagne.IntegracionDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

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

    protected void finalize() {
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

    public int getChatID(Chat chat) {
        int id = 0;
        int offset = 0;
        if (ConnectionClass.usingPSQL())
            offset = 1;
        try {
            this.statement = this.con.prepareStatement("SELECT \"ID\" FROM " + ConnectionClass.getSchema()
                    + "\"Chat\" WHERE \"Tipo\"=\'" + chat.getTipo() + "\' AND \"FechaCreacion\"=\'"+chat.getFechaCreacion().toString() + "\';");
            this.result = this.statement.executeQuery();
            if (this.result.next())
                id = this.result.getInt(0 + offset);
            else
                throw new Exception("No encontrado");
        } catch (Exception e) {
            id = 0;
        }
        return id;
    }

    public boolean eliminarChat(Chat chat) {
        boolean result = true;
        int offset = 0;
        if (ConnectionClass.usingPSQL())
            offset = 1;
        int chatID = this.getChatID(chat);
        String query = "SELECT \"MensajeID\" FROM " + ConnectionClass.getSchema() + "\"ChatXMensaje\" WHERE \"ChatID\"="
                + chatID + ";";
        ResultSet rs = this.executeQuery(query);
        try {
            while (rs.next()) {
                try {
                    int mensajeID = rs.getInt(0 + offset);
                    query = "DELETE FROM " + ConnectionClass.getSchema() + "\"ChatXMensaje\" WHERE \"MensajeID\"="
                            + mensajeID + ";";
                    this.executeQuery(query);
                    query = "DELETE FROM " + ConnectionClass.getSchema() + "\"Mensaje\" WHERE \"ID\"=" + mensajeID
                            + ";";
                    this.executeQuery(query);
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
        }
        query = "DELETE FROM \"Chats\" WHERE \"ID\"=" + chatID + ";";
        return result;
    }

    private int insertarMensaje(Mensaje mensaje, int remitenteID) {
        int id = 0;
        int offset = 0;
        if (ConnectionClass.usingPSQL())
            offset = 1;
        try {
            String query = "INSERT INTO " + ConnectionClass.getSchema() + "\"Mensaje\" (\"Mensaje\", \"Fecha\", \"RemitenteID\") VALUES ('"
                + mensaje.getMensaje() + "', '"+mensaje.getHora()+"'," +  remitenteID+ ");";
            this.executeQuery(query);
            query = "SELECT \"ID\" FROM " + ConnectionClass.getSchema() + "\"Mensaje\" WHERE \"Mensaje\"='"
                + mensaje.getMensaje() + "' AND \"Fecha\"='"+mensaje.getHora()+"' AND \"RemitenteID\"=" + remitenteID + ";";
            ResultSet rs = this.executeQuery(query);
            if (rs.next())
                id = rs.getInt(0 + offset);
            else
                throw new Exception("No encontrado");
        }catch(Exception e){
            id=0;
        }
        return id;
    }

    public boolean insertarMensajeXChat(int chat_id, Mensaje mensaje, int remitenteID){
        boolean result = true;
        String query = "INSERT INTO " + ConnectionClass.getSchema() + "\"ChatXMensaje\" (\"ChatID\", \"MensajeID\") VALUES ("
                + chat_id + ", " +  this.insertarMensaje(mensaje, remitenteID)+ ");";
        try {
            this.executeQuery(query);
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    public HashSet<Mensaje> mensajesXChat(int chatID){
        ControlEstudiantes cEstudiantes=new ControlEstudiantes();
        ControlProfesores cProfesores=new ControlProfesores();
        HashSet<Mensaje> mensajes = new HashSet<Mensaje>();
        int offset = 0;
        if (ConnectionClass.usingPSQL())
            offset = 1;
        String query = "SELECT ME.\"Mensaje\", ME.\"Fecha\", ME.\"RemitenteID\" FROM " + ConnectionClass.getSchema() + "\"ChatXMensaje\" AS CME, "+ConnectionClass.getSchema()+"\"Mensaje\" AS ME WHERE CME.\"ChatID\"="
                + chatID +"AND CME.\"MensajeID\"=ME.\"ID\""+ ";";
        ResultSet rs = this.executeQuery(query);
        try {
            while (rs.next()) {
                try {
                    String texto=rs.getString(0 + offset);
                    String fecha=rs.getString(1 + offset);
                    int remitenteID=rs.getInt(2 + offset);
                    Usuario remitente=cEstudiantes.getEstudianteByID(remitenteID);
                    if(remitente==null)
                        remitente=cProfesores.getProfesorByID(remitenteID);
                    Mensaje mensaje = new Mensaje(fecha, texto, remitente, null);
                    mensajes.add(mensaje);
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
        }
        return mensajes;
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
                + "\"Chat\" (\"FechaCreacion\", \"Tipo\", \"PerteneceID\") VALUES ('" + chat.getFechaCreacion().toString() + "','"
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
                + "\"Chat\" (\"FechaCreacion\", \"Tipo\", \"Usuario1ID\", \"Usuario2ID\") VALUES ('"+chat.getFechaCreacion().toString() + "','" + chat.getTipo()
                + "'," + usuario1ID + "," + usuario2ID + ");";
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
        String sql = "SELECT \"FechaCreacion\",\"Usuario1ID\", \"Usuario2ID\" FROM "
                + ConnectionClass.getSchema() + "\"Chat\" WHERE \"ID\" = " + chatID + ";";
        // Consulta SQL para buscar chats por grupo en la BD
        ResultSet rs = this.executeQuery(sql);
        try {
            if (!rs.next())
                throw new Exception("No se encontró ningún registro...");
            int u1ID = rs.getInt("Usuario1ID");
            int u2ID = rs.getInt("Usuario2ID");
            // Buscar un usuario por id
            sql = "SELECT \"Nombre\",\"Email\", \"Tipo\" FROM " + ConnectionClass.getSchema()
                    + "\"UsuarioRegistrado\" WHERE \"ID\" = " + u1ID + ";";
            ResultSet rs2 = this.executeQuery(sql);
            sql = "SELECT \"Nombre\",\"Email\", \"Tipo\" FROM " + ConnectionClass.getSchema()
                    + "\"UsuarioRegistrado\" WHERE \"ID\" = " + u2ID + ";";
            ResultSet rs3 = this.executeQuery(sql);
            if (rs2.next() && rs3.next()) {
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
                chat = new ChatP(rs.getString("FechaCreacion"), 'G',
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
        String sql = "SELECT \"FechaCreacion\",\"PerteneceID\" FROM " + ConnectionClass.getSchema()
                + "\"Chat\" WHERE \"ID\" = " + chatID + ";";
        // Consulta SQL para buscar chats por grupo en la BD
        ResultSet rs = this.executeQuery(sql);
        try {
            if (!rs.next())
                throw new Exception("No se encontró ningún registro...");
            int grupoID = rs.getInt("PerteneceID");
            // Buscar un grupo por id
            sql = "SELECT \"Nombre\",\"Codigo\",\"Publico\" FROM " + ConnectionClass.getSchema()
                    + "\"GrupoEstudio\" WHERE \"ID\" = " + grupoID + ";";
            ResultSet rs2 = this.executeQuery(sql);
            if (rs2.next()) {
                String nombre = rs2.getString("Nombre");
                String codigo = rs2.getString("Codigo");
                String publico = (rs2.getBoolean("Publico")) ? "S" : "N";
                chat = new ChatG(rs.getString("FechaCreacion"), 'G',
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
