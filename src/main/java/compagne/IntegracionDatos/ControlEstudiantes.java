package compagne.IntegracionDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;


import compagne.Entidades.Comentario;
import compagne.Entidades.Estudiante;
import compagne.Entidades.Usuario;

public class ControlEstudiantes {
    final ConnectionClass CONNECTION_CLASS = new ConnectionClass();
    private Connection con;
    private PreparedStatement statement;
    private ResultSet result;

    public void instanceOf() {

    }

    public ResultSet executeQuery(String query) {
        ResultSet rs;
        try {
            this.statement = this.con.prepareStatement(query);
            rs = this.statement.executeQuery();
        } catch (SQLException e) {
            rs = null;
        }
        return rs;
    }

    public int getEstudianteID(String email) {
        /*
         * Este metodo retorna el ID de un estudiante al cual busca por su mail. Recibe
         * : email -> String que representa un email
         */
        int r = 0;
        int offset = 0;
        if (ConnectionClass.usingPSQL())
            offset = 1;
        try {
            this.statement = this.con.prepareStatement("SELECT \"ID\" FROM " + ConnectionClass.getSchema()
                    + "\"UsuarioRegistrado\" WHERE \"Email\"=\'" + email + "\' AND \"Tipo\"=\'S\';");
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
    
    public String getEstudianteEmail(Integer id) {
        /*
         * Este metodo retorna el Email  de un estudiante al cual busca por su id. Recibe
         * : id -> Integer que representa un codigo
         */
        String email = "";
        int offset = 0;
        if (ConnectionClass.usingPSQL())
            offset = 1;
        try {
            this.statement = this.con.prepareStatement("SELECT \"Email\" FROM " + ConnectionClass.getSchema()
                    + "\"UsuarioRegistrado\" WHERE \"ID\"=\'" + id + "\' AND \"Tipo\"=\'S\';");
            this.result = this.statement.executeQuery();
            if (this.result.next())
                email = this.result.getString(0 + offset);
            else
                throw new Exception("No encontrado");
        } catch (Exception e) {
            email = null;
        }
        return email;
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

    public boolean insertarMateria(String materia) {
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

    public int getMateriaID(String materia) {
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

    public boolean insertarLogro(String logro) {
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

    public int getLogroID(String logro) {
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

    public boolean insertarInteres(String interes) {
        /*
         * Inserta un interes en la BD. Recibe: interes -> String que tiene el nombre
         * del interes
         */
        boolean b = false;
        String consulta = "";
        try {
            consulta = "INSERT INTO " + ConnectionClass.getSchema() + "\"Interes\"(\"Nombre\") VALUES (\'" + interes
                    + "\');";
            this.statement = this.con.prepareStatement(consulta);
            this.statement.executeQuery();
            b = true;
        } catch (Exception e) {
            b = false;
        }
        return b;
    }

    public int getInteresID(String interes) {
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
                    + "\"Interes\" WHERE \"Nombre\"=\'" + interes + "\';");
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

    public boolean insertarInteresXEstudiante(int tid, int id) {
        /*
         * Inserta una materia para un estudiante. Recibe: tid-> int con el id del
         * estudiante, id-> int con el id del logro
         */
        boolean b = true;
        String consulta = "INSERT INTO " + ConnectionClass.getSchema()
                + "\"UsuarioXIntereses\"(\"Usuario RegistradoID\", \"InteresID\") VALUES (" + String.valueOf(tid) + ", "
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
         * estudiante y hace que este persista en la BD . Recibe: estudiante ->
         * Instancia de la clase estudiante, representa el estudiante que quiere
         * guardarse en la BD
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
        try {
            // Toca insertar los intereses si hay, si no hay, saltara un
            // error o no se
            // ejecutara lo siguiente.
            if (estudiante.getIntereses().size() > 0) {
                for (String interes : estudiante.getIntereses()) {
                    // Trato de encontrar un comentario para no repetir en la BD
                    int id = this.getInteresID(interes);
                    // Si el ID es 0, entonces no existe y hay que crearlo
                    if (id == 0) {
                        this.insertarInteres(interes);
                        id = this.getInteresID(interes);
                    }
                    // Inserto un comentario para el usuario
                    this.insertarInteresXEstudiante(tid, id);
                }
            }
        } catch (Exception e) { // No pasa nada
        }
        return b;
    }

    public boolean eliminarEstudiante(String email) {
        /*
         * Elimina un estudiante de la BD. Recibe: email -> String que representa el
         * email de un estudiante
         */
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
                String sql = "DELETE FROM " + ConnectionClass.getSchema()
                        + "\"UsuarioXIntereses\" WHERE \"Usuario RegistradoID\"=" + String.valueOf(id);
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
        /*
         * Modifica un estudiante en la BD. Recibe: estudiante -> Instancia de la clase
         * estudiante que tiene los datos actualizados de un estudiante, sin embargo, su
         * e-mail no cambia
         */
        boolean b = true;
        String consulta = "UPDATE " + ConnectionClass.getSchema() + "\"UsuarioRegistrado\" SET \"Nombre\"=\'"
                + estudiante.getNombre() + "\', \"Contrasenia\"=\'" + estudiante.getContrasenia()
                + "\', \"Tipo\"=\'S\' WHERE \"Email\"=\'" + estudiante.getEmail() + "\';";
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

    public HashSet<Usuario> listarCompas() {
        int offset = 0;
        if (ConnectionClass.usingPSQL()) {
            offset = 1;
        }
        HashSet<Usuario> compas = new HashSet<>();
        Estudiante usu;
        String consulta;
        String nombre;
        String email;
        int estID;
        consulta = "SELECT \"Nombre\", \"Email\", \"ID\" FROM " + ConnectionClass.getSchema()
                + "\"UsuarioRegistrado\" WHERE \"Tipo\"=\'S\';";
        ResultSet rs = this.executeQuery(consulta);
        ResultSet rs2;
        try {
            while (rs.next()) {
                nombre = rs.getString(0 + offset);
                estID = rs.getInt(2 + offset);
                email = rs.getString(1 + offset);
                // Si hay
                HashSet<String> logros = new HashSet<>();
                HashSet<Comentario> comentarios = new HashSet<>();
                HashSet<String> materias = new HashSet<>();
                HashSet<String> intereses = new HashSet<>();
                // Logros
                consulta = "SELECT LO.\"Texto\" FROM " + ConnectionClass.getSchema() + "\"Logro\" AS LO, "
                        + ConnectionClass.getSchema() + "\"UsuarioXLogros\" AS UXL WHERE UXL.\"Usuario RegistradoID\"="
                        + estID + " AND UXL.\"LogroID\"=LO.\"ID\";";
                rs2 = this.executeQuery(consulta);
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
                        + "\"UsuarioXComentario\" AS UXC WHERE UXC.\"UsuarioRegistradoID\"=" + estID
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
                        + "\"UsuarioXMaterias\" AS UXL WHERE UXL.\"Usuario RegistradoID\"=" + estID
                        + " AND UXL.\"MateriaID\"=LO.\"ID\";";
                rs2 = this.executeQuery(consulta);
                try {
                    while (rs2.next()) {
                        materias.add(rs2.getString(0 + offset));
                    }
                } catch (Exception e) {
                    // Nada
                }
                // Intereses
                consulta = "SELECT LO.\"Nombre\" FROM " + ConnectionClass.getSchema() + "\"Interes\" AS LO, "
                        + ConnectionClass.getSchema()
                        + "\"UsuarioXIntereses\" AS UXL WHERE UXL.\"Usuario RegistradoID\"=" + estID
                        + " AND UXL.\"InteresID\"=LO.\"ID\";";
                rs2 = this.executeQuery(consulta);
                try {
                    while (rs2.next()) {
                        intereses.add(rs2.getString(0 + offset));
                    }
                } catch (Exception e) {
                    // Nada
                }
                usu = new Estudiante(nombre, email, materias, comentarios, "", intereses, logros);
                compas.add(usu);
            }
        } catch (Exception e) {
        }
        return compas;
    }

    public HashSet<Usuario> listarCompas(String nombre) {
        int offset = 0;
        if (ConnectionClass.usingPSQL()) {
            offset = 1;
        }
        HashSet<Usuario> compas = new HashSet<>();
        Estudiante usu;
        String consulta;
        String email;
        int estID;
        consulta = "SELECT \"Email\", \"ID\" FROM " + ConnectionClass.getSchema()
                + "\"UsuarioRegistrado\" WHERE \"Tipo\"=\'S\' AND \"Nombre\"=\'"+nombre+"\';";
        ResultSet rs = this.executeQuery(consulta);
        ResultSet rs2;
        try {
            while (rs.next()) {
                estID = rs.getInt(1 + offset);
                email = rs.getString(0 + offset);
                // Si hay
                HashSet<String> logros = new HashSet<>();
                HashSet<Comentario> comentarios = new HashSet<>();
                HashSet<String> materias = new HashSet<>();
                HashSet<String> intereses = new HashSet<>();
                // Logros
                consulta = "SELECT LO.\"Texto\" FROM " + ConnectionClass.getSchema() + "\"Logro\" AS LO, "
                        + ConnectionClass.getSchema() + "\"UsuarioXLogros\" AS UXL WHERE UXL.\"Usuario RegistradoID\"="
                        + estID + " AND UXL.\"LogroID\"=LO.\"ID\";";
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
                        + "\"UsuarioXComentario\" AS UXC WHERE UXC.\"UsuarioRegistradoID\"=" + estID
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
                        + "\"UsuarioXMaterias\" AS UXL WHERE UXL.\"Usuario RegistradoID\"=" + estID
                        + " AND UXL.\"MateriaID\"=LO.\"ID\";";
                rs2 = this.executeQuery(consulta);
                try {
                    while (rs2.next()) {
                        materias.add(rs2.getString(0 + offset));
                    }
                } catch (Exception e) {
                    // Nada
                }
                // Intereses
                consulta = "SELECT LO.\"Nombre\" FROM " + ConnectionClass.getSchema() + "\"Interes\" AS LO, "
                        + ConnectionClass.getSchema()
                        + "\"UsuarioXIntereses\" AS UXL WHERE UXL.\"Usuario RegistradoID\"=" + estID
                        + " AND UXL.\"InteresID\"=LO.\"ID\";";
                rs2 = this.executeQuery(consulta);
                try {
                    while (rs2.next()) {
                        intereses.add(rs2.getString(0 + offset));
                    }
                } catch (Exception e) {
                    // Nada
                }
                usu = new Estudiante(nombre, email, materias, comentarios, "", intereses, logros);
                compas.add(usu);
            }
        } catch (Exception e) {
        }
        return compas;
    }

    public Estudiante getEstudianteByID(int id) {
        int offset = 0;
        if (ConnectionClass.usingPSQL()) {
            offset = 1;
        }
        Estudiante usu = null;
        String consulta = "SELECT \"Email\", \"Nombre\" FROM " + ConnectionClass.getSchema()
        + "\"UsuarioRegistrado\" WHERE \"Tipo\"=\'S\' AND \"ID\"="+id+";";
        ResultSet rs = this.executeQuery(consulta);
        ResultSet rs2;
        try {
            while (rs.next()) {
                String email = rs.getString(0 + offset);
                String nombre = rs.getString(1 + offset);
                // Si hay
                HashSet<String> logros = new HashSet<>();
                HashSet<Comentario> comentarios = new HashSet<>();
                HashSet<String> materias = new HashSet<>();
                HashSet<String> intereses = new HashSet<>();
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
                // Intereses
                consulta = "SELECT LO.\"Nombre\" FROM " + ConnectionClass.getSchema() + "\"Interes\" AS LO, "
                        + ConnectionClass.getSchema()
                        + "\"UsuarioXIntereses\" AS UXL WHERE UXL.\"Usuario RegistradoID\"=" + id
                        + " AND UXL.\"InteresID\"=LO.\"ID\";";
                rs2 = this.executeQuery(consulta);
                try {
                    while (rs2.next()) {
                        intereses.add(rs2.getString(0 + offset));
                    }
                } catch (Exception e) {
                    // Nada
                }
                usu = new Estudiante(nombre, email, materias, comentarios, "", intereses, logros);
            }
        } catch (Exception e) {
        }
        return usu;
    }

    public HashSet<Usuario> listarCompasGrupo(String codigo) {
        int offset = 0;
        if (ConnectionClass.usingPSQL()) {
            offset = 1;
        }
        HashSet<Usuario> compas = new HashSet<>();
        Estudiante usu;
        String consulta;
        String nombre;
        String email;
        int estID;
        consulta = "SELECT UR.\"Nombre\", UR.\"Email\", UR.\"ID\" FROM " + ConnectionClass.getSchema()
                + "\"UsuarioRegistrado\" AS UR, \"GrupoEstudio\" AS GE, \"UsuarioxGrupoEstudio\" AS UGE WHERE UGE.\"GrupoEstudioID\"=GE.\"ID\" AND GE.\"Codigo\"=\'"+codigo+"\' AND UGE.\"UsuarioRegistradoID\"=UR.\"ID\";";
        ResultSet rs = this.executeQuery(consulta);
        ResultSet rs2;
        try {
            while (rs.next()) {
                nombre = rs.getString(0 + offset);
                estID = rs.getInt(2 + offset);
                email = rs.getString(1 + offset);
                // Si hay
                HashSet<String> logros = new HashSet<>();
                HashSet<Comentario> comentarios = new HashSet<>();
                HashSet<String> materias = new HashSet<>();
                HashSet<String> intereses = new HashSet<>();
                // Logros
                consulta = "SELECT LO.\"Texto\" FROM " + ConnectionClass.getSchema() + "\"Logro\" AS LO, "
                        + ConnectionClass.getSchema() + "\"UsuarioXLogros\" AS UXL WHERE UXL.\"Usuario RegistradoID\"="
                        + estID + " AND UXL.\"LogroID\"=LO.\"ID\";";
                rs2 = this.executeQuery(consulta);
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
                        + "\"UsuarioXComentario\" AS UXC WHERE UXC.\"UsuarioRegistradoID\"=" + estID
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
                        + "\"UsuarioXMaterias\" AS UXL WHERE UXL.\"Usuario RegistradoID\"=" + estID
                        + " AND UXL.\"MateriaID\"=LO.\"ID\";";
                rs2 = this.executeQuery(consulta);
                try {
                    while (rs2.next()) {
                        materias.add(rs2.getString(0 + offset));
                    }
                } catch (Exception e) {
                    // Nada
                }
                // Intereses
                consulta = "SELECT LO.\"Nombre\" FROM " + ConnectionClass.getSchema() + "\"Interes\" AS LO, "
                        + ConnectionClass.getSchema()
                        + "\"UsuarioXIntereses\" AS UXL WHERE UXL.\"Usuario RegistradoID\"=" + estID
                        + " AND UXL.\"InteresID\"=LO.\"ID\";";
                rs2 = this.executeQuery(consulta);
                try {
                    while (rs2.next()) {
                        intereses.add(rs2.getString(0 + offset));
                    }
                } catch (Exception e) {
                    // Nada
                }
                usu = new Estudiante(nombre, email, materias, comentarios, "", intereses, logros);
                compas.add(usu);
            }
        } catch (Exception e) {
        }
        return compas;
    }
}
