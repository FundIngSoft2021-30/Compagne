package compagne.Negocio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import compagne.Entidades.ChatG;
import compagne.Entidades.ChatP;
import compagne.Entidades.Comentario;
import compagne.Entidades.Estudiante;
import compagne.Entidades.Grupo;
import compagne.Entidades.Mensaje;
import compagne.Entidades.Profesor;
import compagne.Entidades.Usuario;
import compagne.IntegracionDatos.ControlProfesores;
import compagne.IntegracionDatos.ConnectionClass;
import compagne.IntegracionDatos.ControlChats;
import compagne.IntegracionDatos.ControlEstudiantes;
import compagne.IntegracionDatos.ControlGrupos;

public class FacadeCompagne {
    private ControlChats controlChats;
    private ControlProfesores controlProfesores;
    private ControlEstudiantes controlEstudiantes;
    private ControlGrupos controlGrupos;

    public ControlChats getControlChats() {
        return this.controlChats;
    }

    public ControlProfesores getControlProfesores() {
        return this.controlProfesores;
    }

    public ControlEstudiantes getControlEstudiantes() {
        return this.controlEstudiantes;
    }

    public ControlGrupos getControlGrupos() {
        return this.controlGrupos;
    }

    public static FacadeCompagne instance;
    public String usu;

    public FacadeCompagne() {
        this.controlEstudiantes = new ControlEstudiantes();
        this.controlProfesores = new ControlProfesores();
        this.controlGrupos = new ControlGrupos();
        this.controlChats = new ControlChats();
    }

    public static FacadeCompagne getInstance() {
        if (instance == null)
            instance = new FacadeCompagne();
        return instance;
    }

    public int getChatID(ChatP chat) {
        return controlChats.getChatID(chat);
    }

    public int getChatID(ChatG chat) {
        return controlChats.getChatID(chat);
    }

    public boolean eliminarChat(ChatP chat) {
        return controlChats.eliminarChat(chat);
    }

    public boolean eliminarChat(ChatG chat) {
        return controlChats.eliminarChat(chat);
    }

    public boolean redactarMensaje(int chat_id, Mensaje mensaje, int remitenteID) {
        return controlChats.insertarMensajeXChat(chat_id, mensaje, remitenteID);
    }

    public HashSet<Mensaje> buscarMensajesXChat(int chatID) {
        return this.controlChats.mensajesXChat(chatID);
    }

    public HashSet<ChatP> buscarChatsXUsuario(int usuarioID) {
        return controlChats.buscarChatsXUsuario(usuarioID);
    }

    public HashSet<ChatG> buscarChatsXGrupo(int grupoID) {
        return controlChats.buscarChatsXGrupo(grupoID);
    }

    public boolean crearChat(ChatG chat, int grupoID) {
        return controlChats.crearChat(chat, grupoID);
    }

    public boolean crearChat(ChatP chat, int usuario1ID, int usuario2ID) {
        return controlChats.crearChat(chat, usuario1ID, usuario2ID);
    }

    public Profesor crearPerfilProfesor(String nombre, String experiencia, String email, String contrasenia,
            HashSet<String> horariosAtencion, HashSet<String> materias, HashSet<Comentario> comentarios,
            HashSet<String> logros) {
        Profesor profesor = new Profesor(nombre, email, materias, comentarios, experiencia, contrasenia,
                horariosAtencion, logros);
        if (!this.controlProfesores.crearProfesor(profesor))
            profesor = null;
        return profesor;
    }

    public Profesor modificarPerfilProfesor(String nombre, String experiencia, String email, String contrasenia,
            HashSet<String> horariosAtencion, HashSet<String> materias, HashSet<Comentario> comentarios,
            HashSet<String> logros) {
        // Email no se puede cambiar
        Profesor profesor = new Profesor(nombre, email, materias, comentarios, experiencia, contrasenia,
                horariosAtencion, logros);
        this.controlProfesores.modificarProfesor(profesor);
        return profesor;
    }

    public boolean eliminarPerfilProfesor(String email) {
        return this.controlProfesores.eliminarProfesor(email);
    }

    public Estudiante crearPerfilEstudiante(String nombre, String email, String contrasenia, HashSet<String> materias,
            HashSet<Comentario> comentarios, HashSet<String> intereses, HashSet<String> logros) {
        Estudiante estudiante = new Estudiante(nombre, email, materias, comentarios, contrasenia, intereses, logros);
        if (!this.controlEstudiantes.crearEstudiante(estudiante))
            estudiante = null;
        return estudiante;
    }

 
    public Estudiante modificarPerfilEstudiante(String nombre, String email, String contrasenia, HashSet<Comentario> com, HashSet<String> Intereses, HashSet<String> logros, HashSet<String> materias) {
        Estudiante estudiante = new Estudiante(nombre, email, null, null, contrasenia, null, null);
        int ID = buscarIDUsuario(email);
        this.controlEstudiantes.modificarEstudiante(estudiante);
        for(String interes: Intereses){
            this.controlEstudiantes.insertarInteres(interes);
            this.controlEstudiantes.insertarInteresXEstudiante(ID, this.controlEstudiantes.getInteresID(interes));
        }
        for(String logro: logros){
            this.controlEstudiantes.insertarLogro(logro);
            this.controlEstudiantes.insertarLogroXEstudiante(ID, this.controlEstudiantes.getLogroID(logro));
        }
        for(String materia: materias){
            this.controlEstudiantes.insertarMateria(materia);
            this.controlEstudiantes.insertarMateriaXEstudiante(ID, this.controlEstudiantes.getMateriaID(materia));
        }
        return estudiante;
    }

    public boolean eliminarPerfilEstudiante(String email) {
        return this.controlEstudiantes.eliminarEstudiante(email);
    }

    public boolean calificarUsuario(Comentario calificacion, Usuario usuario) {
        boolean b = true;
        try {
            if (usuario instanceof Estudiante) {
                Estudiante estudiante = (Estudiante) (usuario);
                int user_id = this.controlEstudiantes.getEstudianteID(usuario.getEmail());
                int comentario_id = this.controlEstudiantes.getComentarioID(calificacion.getComentario(),
                        calificacion.getCalificacion());
                if (user_id == 0) {
                    this.controlEstudiantes.crearEstudiante(estudiante);
                    user_id = this.controlEstudiantes.getEstudianteID(estudiante.getEmail());
                }
                if (comentario_id == 0) {
                    this.controlEstudiantes.insertarComentario(calificacion.getComentario(),
                            calificacion.getCalificacion());
                    comentario_id = this.controlEstudiantes.getComentarioID(calificacion.getComentario(),
                            calificacion.getCalificacion());
                }
                this.controlEstudiantes.insertarComentarioXEstudiante(user_id, comentario_id);
            } else {
                Profesor profesor = (Profesor) (usuario);
                int user_id = this.controlProfesores.getProfesorID(usuario.getEmail());
                int comentario_id = this.controlEstudiantes.getComentarioID(calificacion.getComentario(),
                        calificacion.getCalificacion());
                if (user_id == 0) {
                    this.controlProfesores.crearProfesor(profesor);
                    user_id = this.controlProfesores.getProfesorID(profesor.getEmail());
                }
                if (comentario_id == 0) {
                    this.controlProfesores.insertarComentario(calificacion.getComentario(),
                            calificacion.getCalificacion());
                    comentario_id = this.controlProfesores.getComentarioID(calificacion.getComentario(),
                            calificacion.getCalificacion());
                }
                this.controlProfesores.insertarComentarioXProfesor(user_id, comentario_id);
            }
        } catch (Exception e) {
            b = false;
        }
        return b;
    }

    public Usuario iniciarSesion(String email, String contra) {
        Usuario usu = null;
        String nombre = "";
        String consulta = "";
        int offset = 0;
        if (ConnectionClass.usingPSQL())
            offset = 1;
        int estID = this.controlEstudiantes.getEstudianteID(email);
        int proID = this.controlProfesores.getProfesorID(email);
        boolean b = false;
        if (estID > 0) {
            consulta = "SELECT \"Nombre\" FROM " + ConnectionClass.getSchema()
                    + "\"UsuarioRegistrado\" WHERE \"Email\"=" + "\'" + email
                    + "\' AND \"Tipo\"=\'S\' AND \"Contrasenia\"=\'" + contra + "\';";
            ResultSet rs = this.controlEstudiantes.executeQuery(consulta);
            try {
                if (rs.next()) {
                    nombre = rs.getString(0 + offset);
                    b = true;
                }
            } catch (SQLException e) {
                b = false;
            }
            if (b) {
                // Si hay
                HashSet<String> logros = new HashSet<>();
                HashSet<Comentario> comentarios = new HashSet<>();
                HashSet<String> materias = new HashSet<>();
                HashSet<String> intereses = new HashSet<>();
                // Logros
                consulta = "SELECT LO.\"Texto\" FROM " + ConnectionClass.getSchema() + "\"Logro\" AS LO, "
                        + ConnectionClass.getSchema() + "\"UsuarioXLogros\" AS UXL WHERE UXL.\"Usuario RegistradoID\"="
                        + estID + " AND UXL.\"LogroID\"=LO.\"ID\";";
                rs = this.controlEstudiantes.executeQuery(consulta);
                try {
                    while (rs.next()) {
                        logros.add(rs.getString(0 + offset));
                    }
                } catch (Exception e) {
                    // Nada
                }
                // Comentarios
                consulta = "SELECT CO.\"Texto\", CO.\"Estrellas\" FROM " + ConnectionClass.getSchema()
                        + "\"Comentario\" AS CO, " + ConnectionClass.getSchema()
                        + "\"UsuarioXComentario\" AS UXC WHERE UXC.\"UsuarioRegistradoID\"=" + estID
                        + " AND UXC.\"ComentarioID\"=CO.\"ID\";";
                rs = this.controlEstudiantes.executeQuery(consulta);
                try {
                    while (rs.next()) {
                        comentarios.add(new Comentario(rs.getString(1 + offset), rs.getString(0 + offset)));
                    }
                } catch (Exception e) {
                    // Nada
                }
                // Materias
                consulta = "SELECT LO.\"Nombre\" FROM " + ConnectionClass.getSchema() + "\"Materia\" AS LO, "
                        + ConnectionClass.getSchema()
                        + "\"UsuarioXMaterias\" AS UXL WHERE UXL.\"Usuario RegistradoID\"=" + estID
                        + " AND UXL.\"MateriaID\"=LO.\"ID\";";
                rs = this.controlEstudiantes.executeQuery(consulta);
                try {
                    while (rs.next()) {
                        materias.add(rs.getString(0 + offset));
                    }
                } catch (Exception e) {
                    // Nada
                }
                // Intereses
                consulta = "SELECT LO.\"Nombre\" FROM " + ConnectionClass.getSchema() + "\"Interes\" AS LO, "
                        + ConnectionClass.getSchema()
                        + "\"UsuarioXIntereses\" AS UXL WHERE UXL.\"Usuario RegistradoID\"=" + estID
                        + " AND UXL.\"InteresID\"=LO.\"ID\";";
                rs = this.controlEstudiantes.executeQuery(consulta);
                try {
                    while (rs.next()) {
                        intereses.add(rs.getString(0 + offset));
                    }
                } catch (Exception e) {
                    // Nada
                }
                usu = new Estudiante(nombre, email, materias, comentarios, contra, intereses, logros);
            }
        } else if (proID > 0) {
            consulta = "SELECT \"Nombre\" FROM " + ConnectionClass.getSchema()
                    + "\"UsuarioRegistrado\" WHERE \"Email\"=" + "\'" + email
                    + "\' AND \"Tipo\"=\'T\' AND \"Contrasenia\"=\'" + contra + "\';";
            ResultSet rs = this.controlEstudiantes.executeQuery(consulta);
            try {
                if (rs.next()) {
                    nombre = rs.getString(0 + offset);
                    b = true;
                }
            } catch (SQLException e) {
                b = false;
            }
            if (b) {
                // Si hay
                HashSet<String> logros = new HashSet<>();
                HashSet<Comentario> comentarios = new HashSet<>();
                HashSet<String> materias = new HashSet<>();
                HashSet<String> horarioAtencion = new HashSet<>();
                String experiencia = "";

                // Logros
                consulta = "SELECT LO.\"Texto\" FROM " + ConnectionClass.getSchema() + "\"Logro\" AS LO, "
                        + ConnectionClass.getSchema() + "\"UsuarioXLogros\" AS UXL WHERE UXL.\"Usuario RegistradoID\"="
                        + proID + " AND UXL.\"LogroID\"=LO.\"ID\";";
                rs = this.controlProfesores.executeQuery(consulta);
                try {
                    while (rs.next()) {
                        logros.add(rs.getString(0 + offset));
                    }
                } catch (Exception e) {
                    // Nada
                }
                // Comentarios
                consulta = "SELECT CO.\"Texto\", CO.\"Estrellas\" FROM " + ConnectionClass.getSchema()
                        + "\"Comentario\" AS CO, " + ConnectionClass.getSchema()
                        + "\"UsuarioXComentario\" AS UXC WHERE UXC.\"UsuarioRegistradoID\"=" + proID
                        + " AND UXC.\"ComentarioID\"=CO.\"ID\";";
                rs = this.controlProfesores.executeQuery(consulta);
                try {
                    while (rs.next()) {
                        comentarios.add(new Comentario(rs.getString(1 + offset), rs.getString(0 + offset)));
                    }
                } catch (Exception e) {
                    // Nada
                }
                // Materias
                consulta = "SELECT LO.\"Experiencia\" FROM " + ConnectionClass.getSchema()
                        + "\"UsuarioRegistrado\" AS LO WHERE " + proID + "=LO.\"ID\";";
                rs = this.controlProfesores.executeQuery(consulta);
                try {
                    if (rs.next()) {
                        experiencia = rs.getString(0 + offset);
                    }
                } catch (Exception e) {
                    // Nada
                }
                // Experiencia
                consulta = "SELECT LO.\"Nombre\" FROM " + ConnectionClass.getSchema() + "\"Materia\" AS LO, "
                        + ConnectionClass.getSchema()
                        + "\"UsuarioXMaterias\" AS UXL WHERE UXL.\"Usuario RegistradoID\"=" + proID
                        + " AND UXL.\"MateriaID\"=LO.\"ID\";";
                rs = this.controlProfesores.executeQuery(consulta);
                try {
                    while (rs.next()) {
                        materias.add(rs.getString(0 + offset));
                    }
                } catch (Exception e) {
                    // Nada
                }
                usu = new Profesor(nombre, email, materias, comentarios, experiencia, contra, horarioAtencion, logros);
            }
        }
        return usu;
    }

    public Usuario informacionUsuario(String email) {
        Usuario usu = null;
        String nombre = "";
        String consulta = "";
        int offset = 0;
        if (ConnectionClass.usingPSQL())
            offset = 1;
        int estID = this.controlEstudiantes.getEstudianteID(email);
        int proID = this.controlProfesores.getProfesorID(email);
        boolean b = false;
        if (estID > 0) {
            consulta = "SELECT \"Nombre\" FROM " + ConnectionClass.getSchema()
                    + "\"UsuarioRegistrado\" WHERE \"Email\"=" + "\'" + email + "\' AND \"Tipo\"=\'S\';";
            ResultSet rs = this.controlEstudiantes.executeQuery(consulta);
            try {
                if (rs.next()) {
                    nombre = rs.getString(0 + offset);
                    b = true;
                }
            } catch (SQLException e) {
                b = false;
            }
            if (b) {
                // Si hay
                HashSet<String> logros = new HashSet<>();
                HashSet<Comentario> comentarios = new HashSet<>();
                HashSet<String> materias = new HashSet<>();
                HashSet<String> intereses = new HashSet<>();
                // Logros
                consulta = "SELECT LO.\"Texto\" FROM " + ConnectionClass.getSchema() + "\"Logro\" AS LO, "
                        + ConnectionClass.getSchema() + "\"UsuarioXLogros\" AS UXL WHERE UXL.\"Usuario RegistradoID\"="
                        + estID + " AND UXL.\"LogroID\"=LO.\"ID\";";
                rs = this.controlEstudiantes.executeQuery(consulta);
                try {
                    while (rs.next()) {
                        logros.add(rs.getString(0 + offset));
                    }
                } catch (Exception e) {
                    // Nada
                }
                // Comentarios
                consulta = "SELECT CO.\"Texto\", CO.\"Estrellas\" FROM " + ConnectionClass.getSchema()
                        + "\"Comentario\" AS CO, " + ConnectionClass.getSchema()
                        + "\"UsuarioXComentario\" AS UXC WHERE UXC.\"UsuarioRegistradoID\"=" + estID
                        + " AND UXC.\"ComentarioID\"=CO.\"ID\";";
                rs = this.controlEstudiantes.executeQuery(consulta);
                try {
                    while (rs.next()) {
                        comentarios.add(new Comentario(rs.getString(1 + offset), rs.getString(0 + offset)));
                    }
                } catch (Exception e) {
                    // Nada
                }
                // Materias
                consulta = "SELECT LO.\"Nombre\" FROM " + ConnectionClass.getSchema() + "\"Materia\" AS LO, "
                        + ConnectionClass.getSchema()
                        + "\"UsuarioXMaterias\" AS UXL WHERE UXL.\"Usuario RegistradoID\"=" + estID
                        + " AND UXL.\"MateriaID\"=LO.\"ID\";";
                rs = this.controlEstudiantes.executeQuery(consulta);
                try {
                    while (rs.next()) {
                        materias.add(rs.getString(0 + offset));
                    }
                } catch (Exception e) {
                    // Nada
                }
                // Intereses
                consulta = "SELECT LO.\"Nombre\" FROM " + ConnectionClass.getSchema() + "\"Interes\" AS LO, "
                        + ConnectionClass.getSchema()
                        + "\"UsuarioXIntereses\" AS UXL WHERE UXL.\"Usuario RegistradoID\"=" + estID
                        + " AND UXL.\"InteresID\"=LO.\"ID\";";
                rs = this.controlEstudiantes.executeQuery(consulta);
                try {
                    while (rs.next()) {
                        intereses.add(rs.getString(0 + offset));
                    }
                } catch (Exception e) {
                    // Nada
                }
                usu = new Estudiante(nombre, email, materias, comentarios, "*******", intereses, logros);
            }
        } else if (proID > 0) {
            consulta = "SELECT \"Nombre\" FROM " + ConnectionClass.getSchema()
                    + "\"UsuarioRegistrado\" WHERE \"Email\"=" + "\'" + email + "\' AND \"Tipo\"=\'T\';";
            ResultSet rs = this.controlEstudiantes.executeQuery(consulta);
            try {
                if (rs.next()) {
                    nombre = rs.getString(0 + offset);
                    b = true;
                }
            } catch (SQLException e) {
                b = false;
            }
            if (b) {
                // Si hay
                HashSet<String> logros = new HashSet<>();
                HashSet<Comentario> comentarios = new HashSet<>();
                HashSet<String> materias = new HashSet<>();
                HashSet<String> horarioAtencion = new HashSet<>();
                String experiencia = "";

                // Logros
                consulta = "SELECT LO.\"Texto\" FROM " + ConnectionClass.getSchema() + "\"Logro\" AS LO, "
                        + ConnectionClass.getSchema() + "\"UsuarioXLogros\" AS UXL WHERE UXL.\"Usuario RegistradoID\"="
                        + proID + " AND UXL.\"LogroID\"=LO.\"ID\";";
                rs = this.controlProfesores.executeQuery(consulta);
                try {
                    while (rs.next()) {
                        logros.add(rs.getString(0 + offset));
                    }
                } catch (Exception e) {
                    // Nada
                }
                // Comentarios
                consulta = "SELECT CO.\"Texto\", CO.\"Estrellas\" FROM " + ConnectionClass.getSchema()
                        + "\"Comentario\" AS CO, " + ConnectionClass.getSchema()
                        + "\"UsuarioXComentario\" AS UXC WHERE UXC.\"UsuarioRegistradoID\"=" + proID
                        + " AND UXC.\"ComentarioID\"=CO.\"ID\";";
                rs = this.controlProfesores.executeQuery(consulta);
                try {
                    while (rs.next()) {
                        comentarios.add(new Comentario(rs.getString(1 + offset), rs.getString(0 + offset)));
                    }
                } catch (Exception e) {
                    // Nada
                }
                // Materias
                consulta = "SELECT LO.\"Experiencia\" FROM " + ConnectionClass.getSchema()
                        + "\"UsuarioRegistrado\" AS LO WHERE " + proID + "=LO.\"ID\";";
                rs = this.controlProfesores.executeQuery(consulta);
                try {
                    if (rs.next()) {
                        experiencia = rs.getString(0 + offset);
                    }
                } catch (Exception e) {
                    // Nada
                }
                // Experiencia
                consulta = "SELECT LO.\"Nombre\" FROM " + ConnectionClass.getSchema() + "\"Materia\" AS LO, "
                        + ConnectionClass.getSchema()
                        + "\"UsuarioXMaterias\" AS UXL WHERE UXL.\"Usuario RegistradoID\"=" + proID
                        + " AND UXL.\"MateriaID\"=LO.\"ID\";";
                rs = this.controlProfesores.executeQuery(consulta);
                try {
                    while (rs.next()) {
                        materias.add(rs.getString(0 + offset));
                    }
                } catch (Exception e) {
                    // Nada
                }
                usu = new Profesor(nombre, email, materias, comentarios, experiencia, "***********", horarioAtencion,
                        logros);
            }
        }
        return usu;
    }

    public HashSet<String> buscarCompaNombre(String nombre) {

        HashSet<String> emails = new HashSet<>();
        String consulta = "";
        int offset = 0;
        if (ConnectionClass.usingPSQL())
            offset = 1;

        consulta = "SELECT \"Email\" FROM " + ConnectionClass.getSchema() + "\"UsuarioRegistrado\" WHERE \"Nombre\" LIKE"
                + " \'" + nombre + "%\' AND \"Tipo\"=\'S" + "\';";
        //System.out.println("La consulta "+ consulta);
        ResultSet rs = this.controlEstudiantes.executeQuery(consulta);
        try {
            while (rs.next()) {
                //System.out.println("Agregando email");
                emails.add(rs.getString(0 + offset));
            }
        } catch (Exception e) {
            //e.printStackTrace();
            // Nada
        }
        return emails;

    }

    public Grupo informacionGrupo(String grupocod) {
        Grupo g = null;
        HashSet<Integer> ids = new HashSet<>();
        HashSet<String> emails = new HashSet<>();
        HashSet<Usuario> miembros = new HashSet<>();
        String consulta = "";
        int offset = 0;
        if (ConnectionClass.usingPSQL())
            offset = 1;

        consulta = "SELECT \"Nombre\", \"Publico\" FROM " + ConnectionClass.getSchema()
                + "\"GrupoEstudio\" WHERE \"Codigo\"=" + "\'" + grupocod + "\';";
        ResultSet rs = this.controlGrupos.executeQuery(consulta);
        try {
            if (rs.next()) {
                g = new Grupo(rs.getString(0 + offset), grupocod, rs.getString(1 + offset));
            }
        } catch (Exception e) {
            // Nada
        }
        if (g != null) {
            consulta = "SELECT \"UsuarioRegistradoID\", \"Admin\" FROM " + ConnectionClass.getSchema()
                    + "\"UsuarioxGrupoEstudio\" WHERE \"GrupoEstudioID\"=" + "\';" + grupocod + "\';";
            rs = this.controlGrupos.executeQuery(consulta);
            try {
                while (rs.next()) {
                    ids.add(rs.getInt(0 + offset));
                }
            } catch (Exception e) {
                // Nada
            }
        }
        if (!ids.isEmpty()) {
            for (Integer i : ids) {
                emails.add(this.controlEstudiantes.getEstudianteEmail(i));
            }
        }
        if (!emails.isEmpty()) {
            for (String e : emails) {
                miembros.add(informacionUsuario(e));
            }
        }
        if (!miembros.isEmpty())
            g.setMiembros(miembros);
        return g;
    }

    public Grupo crearGrupo(String nombre, String codigo, String publico, String usuario) {
        Grupo grupo = new Grupo(nombre, codigo, publico);
        if (!this.controlGrupos.crearGrupo(grupo, usuario))
            grupo = null;
        return grupo;
    }

    public Grupo modificarGrupo(String nombre, String codigo, String publico) {
        Grupo grupo = new Grupo(nombre, codigo, publico);
        if (!this.controlGrupos.modificarGrupo(grupo))
            grupo = null;
        return grupo;
    }

    public boolean eliminarGrupo(String codigo) {
        return this.controlGrupos.eliminarGrupo(codigo);
    }

    public boolean agregarUsuarioAGrupo(String grupocod, String email, String admin) {
        return this.controlGrupos.insertarUsuarioXGrupoEstudio(grupocod, email, admin);
    }

    public boolean eliminarUsuarioDeGrupo(String grupocod, String email) {
        return this.controlGrupos.eliminarUsuarioXGrupoEstudio(grupocod, email);
    }

    public boolean hacerAdminDeGrupo(int grupoid, int idusuario) {
        return this.controlGrupos.hacerAdminDeGrupo(grupoid, idusuario);
    }

    public HashSet<Usuario> listarCompasGrupo(String codigo) {
        HashSet<Usuario> losU = this.controlEstudiantes.listarCompasGrupo(codigo);
        return losU;
    }

    // BUSQUEDA
    public int buscarIDUsuario(String email) {
        return this.controlGrupos.getUsarioID(email);
    }

    public int buscarIDGrupo(String codigo) {
        return this.controlGrupos.getGrupoID(codigo);
    }

    public boolean isEstudiante(String email) {
        return (this.controlEstudiantes.getEstudianteID(email) > 0);
    }

    public HashSet<Grupo> listarGruposPublicos() {
        return this.controlGrupos.getGruposPublicos();
    }

    public HashSet<Usuario> listarCompas() {
        return this.controlEstudiantes.listarCompas();
    }

    public HashSet<Usuario> listarCompas(String nombre) {
        return this.controlEstudiantes.listarCompas(nombre);
    }

    protected void finalize() {
        this.controlGrupos.finalize();
        this.controlEstudiantes.finalize();
        this.controlChats.finalize();
        this.controlProfesores.finalize();
    }
}
