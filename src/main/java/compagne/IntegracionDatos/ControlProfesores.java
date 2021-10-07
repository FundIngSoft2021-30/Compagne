package compagne.IntegracionDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import compagne.Entidades.Comentario;
import compagne.Entidades.Profesor;

public class ControlProfesores {
    final ConnectionClass CONNECTION_CLASS = new ConnectionClass();
    private Connection con;
    private PreparedStatement statement;
    private ResultSet result;

    public int getProfesorID(String email) {
        int r = 0;
        int offset = 0;
        if (ConnectionClass.usingPSQL())
            offset = 1;
        try {
            this.statement = this.con.prepareStatement("SELECT \"ID\" FROM " + ConnectionClass.getSchema()
                    + "\"UsuarioRegistrado\" WHERE \"Email\"=\'" + email + "\';");
            this.result=this.statement.executeQuery();
            if (this.result.next())
                r = this.result.getInt(0 + offset);
            else
                throw new Exception("No encontrado");
        } catch (Exception e) {
            r = 0;
        }
        return r;
    }

    public boolean insertarComentario(String comentario, String calificacion) {
        boolean b = false;
        String consulta = "";
        try {
            if (calificacion != null && comentario != null)
                consulta = "INSERT INTO " + ConnectionClass.getSchema()
                        + "\"Comentario\" (\"Texto\", \"Estrellas\") VALUES (\'" + comentario + "\', " + calificacion
                        + ");";
            else if (calificacion != null)
                consulta = "INSERT INTO " + ConnectionClass.getSchema() + "\"Comentario\" (\"Estrellas\") VALUES ("
                        + calificacion + ");";
            else
                consulta = "INSERT INTO " + ConnectionClass.getSchema() + "\"Comentario\" (\"Texto\") VALUES (\'"
                        + comentario + "\');";
            this.statement = this.con.prepareStatement(consulta);
            this.statement.executeQuery();
            b = true;
        } catch (Exception e) {
            b = false;
        }
        return b;
    }

    public int getComentarioID(String comentario, String calificacion) {
        String consulta = "";
        int comID = 0;
        int offset = 0;
        if (ConnectionClass.usingPSQL())
            offset = 1;
        if (calificacion != null && comentario != null)
            consulta = "SELECT \"ID\" FROM " + ConnectionClass.getSchema() + "\"Comentario\" WHERE \"Texto\"=\'"
                    + comentario + "\' AND \"Estrellas\"=" + calificacion;
        else if (calificacion != null)
            consulta = "SELECT \"ID\" FROM " + ConnectionClass.getSchema() + "\"Comentario\" WHERE \"Estrellas\"="
                    + calificacion;
        else
            consulta = "SELECT \"ID\" FROM " + ConnectionClass.getSchema() + "\"Comentario\" WHERE \"Texto\"=\'"
                    + comentario + "\';";
        try {
            this.statement = this.con.prepareStatement(consulta);
            this.result = this.statement.executeQuery();
            if (this.result.next())
                comID = this.result.getInt(0 + offset);
            else
                throw new Exception("No encontrado");
        } catch (Exception e) {
            comID = 0;
        }
        return comID;
    }

    public boolean insertarComentarioXProfesor(int tid, int id) {
        boolean b = true;
        String consulta = "INSERT INTO " + ConnectionClass.getSchema()
                + "\"UsuarioXComentario\"(\"UsuarioRegistradoID\", \"ComentarioID\") VALUES (" + String.valueOf(tid)
                + ", " + String.valueOf(id) + ");";
        try {
            this.statement = this.con.prepareStatement(consulta);
            this.statement.executeQuery();
        } catch (Exception e) {
        }
        return b;
    }

    public boolean crearProfesor(Profesor profesor) {
        /*
         * Este método guarda un profesor en la BD, recibe un objeto de la clase
         * profesor y hace que este persista en la BD . 
         * Recibe: profesor -> Instancia de la clase Profesor, representa el profesor que quiere guardarse en la BD
         */
        boolean b = false; // Inicializo lo que retorno (Si el profesor se guardo bien)
        String sql = "INSERT INTO " + ConnectionClass.getSchema()
                + "\"UsuarioRegistrado\" (\"Nombre\",\"Tipo\", \"Email\",\"Contrasenia\", \"Experiencia\") VALUES ('"
                + profesor.getNombre() + "', '" + "T" + "', '" + profesor.getEmail() + "', '"
                + profesor.getContrasenia() + "', '" + profesor.getExperiencia() + "');"; // Consulta SQL para insertar
                                                                                          // un usuario registrado en la
                                                                                          // BD de tipo 'T' es decir
                                                                                          // profesor en la BD
        try {
            this.statement = this.con.prepareStatement(sql); // Preparando una consulta SQL
            this.statement.execute(); // Ejecutando una consulta SQL
            b = true; // No hubo problemas
        } catch (Exception e) {
            b = false; // Si hubo problemas
        }
        try {
            // Toca insertar los comentarios si hay, si no hay, saltara un error o no se
            // ejecutara lo siguiente.
            if (profesor.getComentarios().size() > 0) {
                int tid = this.getProfesorID(profesor.getEmail()); // El ID del profesor que acabo de insertar
                for (Comentario comentario : profesor.getComentarios()) {
                    // Trato de encontrar un comentario para no repetir en la BD
                    int id = this.getComentarioID(comentario.getComentario(), comentario.getCalificacion());
                    // Si el ID es 0, entonces no existe y hay que crearlo
                    if (id == 0) {
                        this.insertarComentario(comentario.getComentario(), comentario.getCalificacion());
                        id = this.getComentarioID(comentario.getComentario(), comentario.getCalificacion());
                    }
                    // Inserto un comentario para el usuario
                    this.insertarComentarioXProfesor(tid, id);
                }
            }
        } catch (Exception e) { // No pasa nada
        }
        return b;
    }

    public boolean eliminarProfesor(final String ID) {
        boolean b = false;
        String sql = "";
        try {
            this.statement = this.con.prepareStatement(sql);
            this.statement.execute();
            b = true;
        } catch (Exception e) {
            b = false;
        }
        return b;
    }

    public boolean updateUsuarioRegistradoName(final String ID, final String NAME) {
        boolean b = false;
        String sql = "";
        try {
            this.statement = this.con.prepareStatement(sql);
            this.statement.execute();
            b = true;
        } catch (Exception e) {
            b = false;
        }
        return b;
    }

    public boolean updateUsuarioRegistradoTipo(final String ID, final char TIPO) {
        boolean b = false;
        String sql = "";
        try {
            this.statement = this.con.prepareStatement(sql);
            this.statement.execute();
            b = true;
        } catch (Exception e) {
            b = false;
        }
        return b;
    }

    public ControlProfesores() {
        this.con = this.CONNECTION_CLASS.getCon();
        try {
            this.con.setAutoCommit(true);
        } catch (SQLException e) {
        }
    }

}
