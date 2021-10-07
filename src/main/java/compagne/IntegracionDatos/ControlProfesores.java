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

    public boolean crearProfesor(Profesor profesor) {
        /* 
        Este método guarda un profesor en la BD, recibe un objeto de la clase profesor y hace que este persista en la BD . Recibe:
            profesor -> Instancia de la clase Profesor, representa el profesor que quiere guardarse en la BD 
        */
        int offset = 0; //Variable necesario, porque algunas BD manejan columnas desde 0 y otras desde 1
        if (ConnectionClass.usingPSQL())
            offset = 1;
        boolean b = false; //Inicializo lo que retorno (Si el profesor se guardo bien)
        String sql = "INSERT INTO " + ConnectionClass.getSchema()
                + "\"UsuarioRegistrado\" (\"Nombre\",\"Tipo\", \"Email\",\"Contrasenia\", \"Experiencia\") VALUES ('"
                + profesor.getNombre() + "', '" + "T" + "', '" + profesor.getEmail() + "', '"
                + profesor.getContrasenia() + "', '" + profesor.getExperiencia() + "');"; //Consulta SQL para insertar un usuario registrado en la BD de tipo 'T' es decir teacher en la BD
        try {
            this.statement = this.con.prepareStatement(sql);
            this.statement.execute();
            b = true;
        } catch (Exception e) {
            e.printStackTrace();
            b = false;
        }
        try {
            if (profesor.getComentarios().size() > 0) {
                this.result = this.con
                        .prepareStatement("SELECT \"ID\" FROM " + ConnectionClass.getSchema()
                                + "\"UsuarioRegistrado\" WHERE \"Email\"=\'" + profesor.getEmail() + "\';")
                        .executeQuery();
                System.out.println(this.result.next());
                int tid = this.result.getInt(0 + offset);
                for (Comentario comentario : profesor.getComentarios()) {
                    this.result = null;
                    String consulta = "";
                    if (comentario.getCalificacion() != null && comentario.getComentario() != null)
                        consulta = "SELECT \"ID\" FROM " + ConnectionClass.getSchema()
                                + "\"Comentario\" WHERE \"Texto\"=\'" + comentario.getComentario()
                                + "\' AND \"Estrellas\"=" + comentario.getCalificacion();
                    else if (comentario.getCalificacion() != null)
                        consulta = "SELECT \"ID\" FROM " + ConnectionClass.getSchema()
                                + "\"Comentario\" WHERE \"Estrellas\"=" + comentario.getCalificacion();
                    else
                        consulta = "SELECT \"ID\" FROM " + ConnectionClass.getSchema()
                                + "\"Comentario\" WHERE \"Texto\"=\'" + comentario.getComentario() + "\';";
                    this.statement = this.con.prepareStatement(consulta);
                    this.result = this.statement.executeQuery();
                    int id = 0;
                    if (this.result.next()) {
                        id = this.result.getInt(0 + offset);

                    } else {
                        try {
                            if (comentario.getCalificacion() != null && comentario.getComentario() != null)
                                consulta = "INSERT INTO " + ConnectionClass.getSchema()
                                        + "\"Comentario\" (\"Texto\", \"Estrellas\") VALUES (\'"
                                        + comentario.getComentario() + "\', " + comentario.getCalificacion() + ");";
                            else if (comentario.getCalificacion() != null)
                                consulta = "INSERT INTO " + ConnectionClass.getSchema()
                                        + "\"Comentario\" (\"Estrellas\") VALUES (" + comentario.getCalificacion()
                                        + ");";
                            else
                                consulta = "INSERT INTO " + ConnectionClass.getSchema()
                                        + "\"Comentario\" (\"Texto\") VALUES (\'" + comentario.getComentario() + "\');";
                            this.statement = this.con.prepareStatement(consulta);
                            this.statement.executeQuery();
                        } catch (Exception e) {
                        }
                        if (comentario.getCalificacion() != null && comentario.getComentario() != null)
                            consulta = "SELECT \"ID\" FROM " + ConnectionClass.getSchema()
                                    + "\"Comentario\" WHERE \"Texto\"=\'" + comentario.getComentario()
                                    + "\' AND \"Estrellas\"=" + comentario.getCalificacion();
                        else if (comentario.getCalificacion() != null)
                            consulta = "SELECT \"ID\" FROM " + ConnectionClass.getSchema()
                                    + "\"Comentario\" WHERE \"Estrellas\"=" + comentario.getCalificacion();
                        else
                            consulta = "SELECT \"ID\" FROM " + ConnectionClass.getSchema()
                                    + "\"Comentario\" WHERE \"Texto\"=\'" + comentario.getComentario() + "\';";
                        this.statement = this.con.prepareStatement(consulta);
                        this.result = this.statement.executeQuery();
                        this.result.next();
                        id = this.result.getInt(0 + offset);
                    }
                    consulta = "INSERT INTO " + ConnectionClass.getSchema()
                            + "\"UsuarioXComentario\" (\"UsuarioRegistradoID\", \"ComentarioID\") VALUES ("
                            + String.valueOf(tid) + ", " + String.valueOf(id) + ");";
                    try {
                        this.statement = this.con.prepareStatement(consulta);
                        this.statement.executeQuery();
                    } catch (Exception e) {
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
