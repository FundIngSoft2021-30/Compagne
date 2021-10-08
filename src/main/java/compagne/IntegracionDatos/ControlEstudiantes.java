package compagne.IntegracionDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import compagne.Entidades.Comentario;
import compagne.Entidades.Estudiante;

public class ControlEstudiantes {
    final ConnectionClass CONNECTION_CLASS = new ConnectionClass();
    private Connection con;
    private PreparedStatement statement;
    private ResultSet result;

    public int getEstudianteID(String email) {
        /* Este metodo retorna el ID de un estudiante al cual busca por su mail. Recibe : email -> String que representa un email */
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

    public boolean insertarComentario(String comentario, String calificacion) {
        /*
         * Inserta un comentario en la BD. Recibe: comentario -> String que tiene el
         * texto, Calificacion -> String que tiene un numero con la calificacion
         */
        boolean b = false;
        String consulta = "";
        try {
            if (calificacion != null && comentario != null)
                consulta = "INSERT INTO " + ConnectionClass.getSchema()
                        + "\"Comentario\" (\"Texto\", \"Estrellas\") VALUES (\'" + comentario + "\', \'" + calificacion
                        + "\');";
            else if (calificacion != null)
                consulta = "INSERT INTO " + ConnectionClass.getSchema() + "\"Comentario\" (\"Estrellas\") VALUES (\'"
                        + calificacion + "\');";
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
        /*
         * Retorna el ID de un comentario en la BD. Recibe: comentario -> String que
         * tiene el texto, Calificacion -> String que tiene un numero con la
         * calificacion
         */
        String consulta = "";
        int comID = 0;
        int offset = 0;
        if (ConnectionClass.usingPSQL())
            offset = 1;
        if (calificacion != null && comentario != null)
            consulta = "SELECT \"ID\" FROM " + ConnectionClass.getSchema() + "\"Comentario\" WHERE \"Texto\"=\'"
                    + comentario + "\' AND \"Estrellas\"=\'" + calificacion + "\'";
        else if (calificacion != null && comentario==null) {
            System.out.println("Valor de calificacion: " + Double.valueOf(calificacion));
            consulta = "SELECT \"ID\" FROM " + ConnectionClass.getSchema() + "\"Comentario\" WHERE \"Estrellas\"=\'" + calificacion + "\'";
        } else
            consulta = "SELECT \"ID\" FROM " + ConnectionClass.getSchema() + "\"Comentario\" WHERE \"Texto\"=\'"
                    + comentario + "\'";
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

    public boolean insertarComentarioXEstudiante(int tid, int id) {
        /*
         * Inserta un comentario para un estudiante. Recibe: tid-> int con el id del
         * estudiante, id-> int con el id del comentario
         */
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

    private boolean insertarMateria(String materia) {
        /*
         * Inserta una materia en la BD. Recibe: materia -> String que tiene el nombre
         * de la materia
         */
        boolean b = false;
        String consulta = "";
        try {
            consulta = "INSERT INTO " + ConnectionClass.getSchema() + "\"Materia\" (\"Nombre\") VALUES (\'" + materia
                    + "\');";

            this.statement = this.con.prepareStatement(consulta);
            this.statement.executeQuery();
            b = true;
        } catch (Exception e) {
            b = false;
        }
        return b;
    }

    private int getMateriaID(String materia) {
        /*
         * Retorna el id de una materia en la BD. Recibe: horario -> String que tiene el
         * nombre de la materia
         */
        int r = 0;
        int offset = 0;
        if (ConnectionClass.usingPSQL())
            offset = 1;
        try {
            this.statement = this.con.prepareStatement("SELECT \"ID\" FROM " + ConnectionClass.getSchema()
                    + "\"Materia\" WHERE \"Nombre\"=\'" + materia + "\';");
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

    public boolean insertarMateriaXEstudiante(int tid, int id) {
        /*
         * Inserta una materia para un estudiante. Recibe: tid-> int con el id del
         * estudiante, id-> int con el id de la materia
         */
        boolean b = true;
        String consulta = "INSERT INTO " + ConnectionClass.getSchema()
                + "\"UsuarioXMaterias\"(\"Usuario RegistradoID\", \"MateriaID\") VALUES (" + String.valueOf(tid) + ", "
                + String.valueOf(id) + ");";
        try {
            this.statement = this.con.prepareStatement(consulta);
            this.statement.executeQuery();
        } catch (Exception e) {
        }
        return b;
    }

    private boolean insertarLogro(String logro) {
        /*
         * Inserta una materia en la BD. Recibe: materia -> String que tiene el nombre
         * de la materia
         */
        boolean b = false;
        String consulta = "";
        try {
            consulta = "INSERT INTO " + ConnectionClass.getSchema() + "\"Logro\"(\"Texto\") VALUES (\'" + logro
                    + "\');";
            this.statement = this.con.prepareStatement(consulta);
            this.statement.executeQuery();
            b = true;
        } catch (Exception e) {
            b = false;
        }
        return b;
    }

    private int getLogroID(String logro) {
        /*
         * Retorna el id de una materia en la BD. Recibe: horario -> String que tiene el
         * el momento del horario
         */
        int r = 0;
        int offset = 0;
        if (ConnectionClass.usingPSQL())
            offset = 1;
        try {
            this.statement = this.con.prepareStatement("SELECT \"ID\" FROM " + ConnectionClass.getSchema()
                    + "\"Logro\" WHERE \"Texto\"=\'" + logro + "\';");
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

    public boolean insertarLogroXEstudiante(int tid, int id) {
        /*
         * Inserta una materia para un estudiante. Recibe: tid-> int con el id del
         * estudiante, id-> int con el id del logro
         */
        boolean b = true;
        String consulta = "INSERT INTO " + ConnectionClass.getSchema()
                + "\"UsuarioXLogros\"(\"Usuario RegistradoID\", \"LogroID\") VALUES (" + String.valueOf(tid) + ", "
                + String.valueOf(id) + ");";
        try {
            this.statement = this.con.prepareStatement(consulta);
            this.statement.executeQuery();
        } catch (Exception e) {
        }
        return b;
    }

    public boolean crearEstudiante(Estudiante estudiante) {
        /*
         * Este método guarda un estudiante en la BD, recibe un objeto de la clase
         * estudiante y hace que este persista en la BD . Recibe: estudiante -> Instancia de
         * la clase estudiante, representa el estudiante que quiere guardarse en la BD
         */
        boolean b = false; // Inicializo lo que retorno (Si el estudiante se guardo bien)
        String sql = "INSERT INTO " + ConnectionClass.getSchema()
                + "\"UsuarioRegistrado\" (\"Nombre\",\"Tipo\", \"Email\",\"Contrasenia\") VALUES ('"
                + estudiante.getNombre() + "', '" + "S" + "', '" + estudiante.getEmail() + "', '"
                + estudiante.getContrasenia() + "');"; // Consulta SQL para insertar
                                                                                          // un usuario registrado en la
                                                                                          // BD de tipo 'T' es decir
                                                                                          // estudiante en la BD
        try {
            this.statement = this.con.prepareStatement(sql); // Preparando una consulta SQL
            this.statement.execute(); // Ejecutando una consulta SQL
            b = true; // No hubo problemas
        } catch (Exception e) {
            b = false; // Si hubo problemas
        }
        int tid = this.getEstudianteID(estudiante.getEmail()); // El ID del estudiante que acabo de insertar
        try {
            // Toca insertar los logros si hay, si no hay, saltara un error o no se
            // ejecutara lo siguiente.
            if (estudiante.getLogros().size() > 0) {
                for (String logro : estudiante.getLogros()) {
                    // Trato de encontrar un comentario para no repetir en la BD
                    int id = this.getLogroID(logro);
                    // Si el ID es 0, entonces no existe y hay que crearlo
                    if (id == 0) {
                        this.insertarLogro(logro);
                        id = this.getLogroID(logro);
                    }
                    // Inserto un logro para el usuario
                    this.insertarLogroXEstudiante(tid, id);
                }
            }
        } catch (Exception e) { // No pasa nada
        }
        // Fin de insertar logros
        try {
            // Toca insertar las materias si hay, si no hay, saltara un error o no se
            // ejecutara lo siguiente.
            if (estudiante.getMaterias().size() > 0) {
                for (String materia : estudiante.getMaterias()) {
                    // Trato de encontrar un comentario para no repetir en la BD
                    int id = this.getMateriaID(materia);
                    // Si el ID es 0, entonces no existe y hay que crearlo
                    if (id == 0) {
                        this.insertarMateria(materia);
                        id = this.getMateriaID(materia);
                    }
                    // Inserto un comentario para el usuario
                    this.insertarMateriaXEstudiante(tid, id);
                }
            }
        } catch (Exception e) { // No pasa nada
        }
        try {
            // Toca insertar los comentarios y calificaciones si hay, si no hay, saltara un
            // error o no se
            // ejecutara lo siguiente.
            if (estudiante.getComentarios().size() > 0) {
                for (Comentario comentario : estudiante.getComentarios()) {
                    // Trato de encontrar un comentario para no repetir en la BD
                    int id = this.getComentarioID(comentario.getComentario(), comentario.getCalificacion());
                    // Si el ID es 0, entonces no existe y hay que crearlo
                    if (id == 0) {
                        this.insertarComentario(comentario.getComentario(), comentario.getCalificacion());
                        id = this.getComentarioID(comentario.getComentario(), comentario.getCalificacion());
                    }
                    // Inserto un comentario para el usuario
                    this.insertarComentarioXEstudiante(tid, id);
                }
            }
        } catch (Exception e) { // No pasa nada
        }
        return b;
    }

    public boolean eliminarEstudiante(String email) {
        /* Elimina un estudiante de la BD. Recibe: email -> String que representa el email de un estudiante*/
        boolean b = true;
        int id = this.getEstudianteID(email);
        if (id == 0)
            b = false;
        else {
            try {
                String sql = "DELETE FROM " + ConnectionClass.getSchema()
                        + "\"UsuarioXComentario\" WHERE \"UsuarioRegistradoID\"=" + String.valueOf(id);
                this.statement = this.con.prepareStatement(sql);
                this.result = this.statement.executeQuery();
            } catch (Exception e) {
            }
            try {
                String sql = "DELETE FROM " + ConnectionClass.getSchema()
                        + "\"UsuarioXMaterias\" WHERE \"Usuario RegistradoID\"=" + String.valueOf(id);
                this.statement = this.con.prepareStatement(sql);
                this.result = this.statement.executeQuery();
            } catch (Exception e) {
            }
            try {
                String sql = "DELETE FROM " + ConnectionClass.getSchema()
                        + "\"UsuarioXLogros\" WHERE \"Usuario RegistradoID\"=" + String.valueOf(id);
                this.statement = this.con.prepareStatement(sql);
                this.result = this.statement.executeQuery();
            } catch (Exception e) {
            }
            try {
                String sql = "DELETE FROM " + ConnectionClass.getSchema() + "\"UsuarioRegistrado\" WHERE \"ID\"="
                        + String.valueOf(id);
                this.statement = this.con.prepareStatement(sql);
                this.result = this.statement.executeQuery();
            } catch (Exception e) {
            }
        }
        return b;
    }

    public boolean modificarEstudiante(Estudiante estudiante) {
        /* Modifica un estudiante en la BD. Recibe: estudiante -> Instancia de la clase estudiante que tiene los datos actualizados de un estudiante, sin embargo, su e-mail no cambia*/
        boolean b = true;
        String consulta = "UPDATE "+ConnectionClass.getSchema()+"\"UsuarioRegistrado\" SET \"Nombre\"=\'"+estudiante.getNombre()+"\', \"Contrasenia\"=\'"+estudiante.getContrasenia()+"\', \"Tipo\"=\'S\' WHERE \"Email\"=\'"+estudiante.getEmail()+"\';";
        try {
            this.statement = this.con.prepareStatement(consulta);
            this.statement.executeQuery();
        } catch (Exception e) {
        }
        return b;
    }

    public ControlEstudiantes() {
        this.con = this.CONNECTION_CLASS.getCon();
        try {
            this.con.setAutoCommit(true);
        } catch (SQLException e) {
        }
    }

}
