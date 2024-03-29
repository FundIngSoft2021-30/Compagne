package compagne.IntegracionDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import compagne.Entidades.Comentario;
import compagne.Entidades.Profesor;

public class ControlProfesores {
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

    public int getProfesorID(String email) {
        /*
         * Este metodo retorna el ID de un profesor al cual busca por su mail. Recibe :
         * email -> String que representa un email
         */
        int r = 0;
        int offset = 0;
        if (ConnectionClass.usingPSQL())
            offset = 1;
        try {
            this.statement = this.con.prepareStatement("SELECT \"ID\" FROM " + ConnectionClass.getSchema()
                    + "\"UsuarioRegistrado\" WHERE \"Email\"=\'" + email + "\' AND \"Tipo\"=\'T\';");
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
        else if (calificacion != null && comentario == null)
            consulta = "SELECT \"ID\" FROM " + ConnectionClass.getSchema() + "\"Comentario\" WHERE \"Estrellas\"=\'"
                    + calificacion + "\'";
        else
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

    public boolean insertarComentarioXProfesor(int tid, int id) {
        /*
         * Inserta un comentario para un profesor. Recibe: tid-> int con el id del
         * profesor, id-> int con el id del comentario
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

    private boolean insertarHorario(String horario) {
        /*
         * Inserta un horario en la BD. Recibe: horario -> String que tiene el el
         * momento del horario
         */
        boolean b = false;
        String consulta = "";
        try {
            consulta = "INSERT INTO " + ConnectionClass.getSchema() + "\"HorarioAtencion\" (\"Franja\") VALUES (\'"
                    + horario + "\');";

            this.statement = this.con.prepareStatement(consulta);
            this.statement.executeQuery();
            b = true;
        } catch (Exception e) {
            b = false;
        }
        return b;
    }

    private int getHorarioID(String horario) {
        /*
         * Retorna el id de un horario en la BD. Recibe: horario -> String que tiene el
         * el momento del horario
         */
        int r = 0;
        int offset = 0;
        if (ConnectionClass.usingPSQL())
            offset = 1;
        try {
            this.statement = this.con.prepareStatement("SELECT \"ID\" FROM " + ConnectionClass.getSchema()
                    + "\"HorarioAtencion\" WHERE \"Franja\"=\'" + horario + "\';");
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

    public boolean insertarHorarioXProfesor(int tid, int id) {
        /*
         * Inserta un horario para un profesor. Recibe: tid-> int con el id del
         * profesor, id-> int con el id del comentario
         */
        boolean b = true;
        String consulta = "INSERT INTO " + ConnectionClass.getSchema()
                + "\"UsuarioXHorarioAtencion\"(\"Usuario RegistradoID\", \"HorarioID\") VALUES (" + String.valueOf(tid)
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

    public boolean insertarMateriaXProfesor(int tid, int id) {
        /*
         * Inserta una materia para un profesor. Recibe: tid-> int con el id del
         * profesor, id-> int con el id de la materia
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

    public boolean insertarLogroXProfesor(int tid, int id) {
        /*
         * Inserta una materia para un profesor. Recibe: tid-> int con el id del
         * profesor, id-> int con el id del logro
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

    public boolean crearProfesor(Profesor profesor) {
        /*
         * Este método guarda un profesor en la BD, recibe un objeto de la clase
         * profesor y hace que este persista en la BD . Recibe: profesor -> Instancia de
         * la clase Profesor, representa el profesor que quiere guardarse en la BD
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
        int tid = this.getProfesorID(profesor.getEmail()); // El ID del profesor que acabo de insertar
        try {
            // Toca insertar los comentarios y calificaciones si hay, si no hay, saltara un
            // error o no se
            // ejecutara lo siguiente.
            if (profesor.getComentarios().size() > 0) {
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
        // Fin de insertar comentarios y calificaciones
        try {
            // Toca insertar los horarios de atencion si hay, si no hay, saltara un error o
            // no se
            // ejecutara lo siguiente.
            if (profesor.getHorarioAtencion().size() > 0) {
                for (String horario : profesor.getHorarioAtencion()) {
                    // Trato de encontrar un comentario para no repetir en la BD
                    int id = this.getHorarioID(horario);
                    // Si el ID es 0, entonces no existe y hay que crearlo
                    if (id == 0) {
                        this.insertarHorario(horario);
                        id = this.getHorarioID(horario);
                    }
                    // Inserto un comentario para el usuario
                    this.insertarHorarioXProfesor(tid, id);
                }
            }
        } catch (Exception e) { // No pasa nada
        }
        // Fin de insertar horarios
        try {
            // Toca insertar las materias si hay, si no hay, saltara un error o no se
            // ejecutara lo siguiente.
            if (profesor.getMaterias().size() > 0) {
                for (String materia : profesor.getMaterias()) {
                    // Trato de encontrar un comentario para no repetir en la BD
                    int id = this.getMateriaID(materia);
                    // Si el ID es 0, entonces no existe y hay que crearlo
                    if (id == 0) {
                        this.insertarMateria(materia);
                        id = this.getMateriaID(materia);
                    }
                    // Inserto un comentario para el usuario
                    this.insertarMateriaXProfesor(tid, id);
                }
            }
        } catch (Exception e) { // No pasa nada
        }
        // Fin de insertar materias
        try {
            // Toca insertar los logros si hay, si no hay, saltara un error o no se
            // ejecutara lo siguiente.
            if (profesor.getLogros().size() > 0) {
                for (String logro : profesor.getLogros()) {
                    // Trato de encontrar un comentario para no repetir en la BD
                    int id = this.getLogroID(logro);
                    // Si el ID es 0, entonces no existe y hay que crearlo
                    if (id == 0) {
                        this.insertarLogro(logro);
                        id = this.getLogroID(logro);
                    }
                    // Inserto un logro para el usuario
                    this.insertarLogroXProfesor(tid, id);
                }
            }
        } catch (Exception e) { // No pasa nada
        }
        return b;
    }

    public Profesor getProfesorByID(int id) {
        int offset = 0;
        if (ConnectionClass.usingPSQL()) {
            offset = 1;
        }
        Profesor usu = null;
        String consulta = "SELECT \"Email\", \"Nombre\", \"Experiencia\" FROM " + ConnectionClass.getSchema()
                + "\"UsuarioRegistrado\" WHERE \"Tipo\"=\'T\' AND \"ID\"=" + id + ";";
        ResultSet rs = this.executeQuery(consulta);
        ResultSet rs2;
        try {
            while (rs.next()) {
                String email = rs.getString(0 + offset);
                String nombre = rs.getString(1 + offset);
                String experiencia = rs.getString(2 + offset);
                // Si hay
                HashSet<String> logros = new HashSet<>();
                HashSet<Comentario> comentarios = new HashSet<>();
                HashSet<String> materias = new HashSet<>();
                HashSet<String> horariosHashSet = new HashSet<>();
                // Logros
                consulta = "SELECT LO.\"Texto\" FROM " + ConnectionClass.getSchema() + "\"Logro\" AS LO, "
                        + ConnectionClass.getSchema() + "\"UsuarioXLogros\" AS UXL WHERE UXL.\"Usuario RegistradoID\"="
                        + id + " AND UXL.\"LogroID\"=LO.\"ID\";";
                rs2 = executeQuery(consulta);
                try {
                    while (rs2.next()) {
                        logros.add(rs2.getString(0 + offset));
                    }
                } catch (Exception e) {
                    // Nada
                }
                // Comentarios
                consulta = "SELECT CO.\"Texto\", CO.\"Estrellas\" FROM " + ConnectionClass.getSchema()
                        + "\"Comentario\" AS CO, " + ConnectionClass.getSchema()
                        + "\"UsuarioXComentario\" AS UXC WHERE UXC.\"UsuarioRegistradoID\"=" + id
                        + " AND UXC.\"ComentarioID\"=CO.\"ID\";";
                rs2 = this.executeQuery(consulta);
                try {
                    while (rs2.next()) {
                        comentarios.add(new Comentario(rs2.getString(1 + offset), rs2.getString(0 + offset)));
                    }
                } catch (Exception e) {
                    // Nada
                }
                // Materias
                consulta = "SELECT LO.\"Nombre\" FROM " + ConnectionClass.getSchema() + "\"Materia\" AS LO, "
                        + ConnectionClass.getSchema()
                        + "\"UsuarioXMaterias\" AS UXL WHERE UXL.\"Usuario RegistradoID\"=" + id
                        + " AND UXL.\"MateriaID\"=LO.\"ID\";";
                rs2 = this.executeQuery(consulta);
                try {
                    while (rs2.next()) {
                        materias.add(rs2.getString(0 + offset));
                    }
                } catch (Exception e) {
                    // Nada
                }
                // Horarios
                consulta = "SELECT LO.\"Franja\" FROM " + ConnectionClass.getSchema() + "\"HorarioAtencion\" AS LO, "
                        + ConnectionClass.getSchema()
                        + "\"UsuarioXHorarioAtencion\" AS UXL WHERE UXL.\"Usuario RegistradoID\"=" + id
                        + " AND UXL.\"HorarioID\"=LO.\"ID\";";
                rs2 = this.executeQuery(consulta);
                try {
                    while (rs2.next()) {
                        horariosHashSet.add(rs2.getString(0 + offset));
                    }
                } catch (Exception e) {
                    // Nada
                }
                usu = new Profesor(nombre, email, materias, comentarios, experiencia, "", horariosHashSet, logros);
            }
        } catch (Exception e) {
        }
        return usu;
    }

    public boolean eliminarProfesor(String email) {
        /*
         * Elimina un profesor de la BD. Recibe: email -> String que representa el email
         * de un profesor
         */
        boolean b = true;
        int id = this.getProfesorID(email);
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
                        + "\"UsuarioXHorarioAtencion\" WHERE \"Usuario RegistradoID\"=" + String.valueOf(id);
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

    public boolean modificarProfesor(Profesor profesor) {
        /*
         * Modifica un profesor en la BD. Recibe: profesor -> Instancia de la clase
         * profesor que tiene los datos actualizados de un profesor, sin embargo, su
         * e-mail no cambia
         */
        boolean b = true;
        String consulta = "UPDATE " + ConnectionClass.getSchema() + "\"UsuarioRegistrado\" SET \"Nombre\"=\'"
                + profesor.getNombre() + "\', \"Contrasenia\"=\'" + profesor.getContrasenia()
                + "\', \"Tipo\"=\'T\', \"Experiencia\"=\'" + profesor.getExperiencia() + "\' WHERE \"Email\"=\'"
                + profesor.getEmail() + "\';";
        try {
            this.statement = this.con.prepareStatement(consulta);
            this.statement.executeQuery();
        } catch (Exception e) {
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
