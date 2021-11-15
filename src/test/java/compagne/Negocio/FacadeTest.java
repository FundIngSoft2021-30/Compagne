package compagne.Negocio;

import static org.junit.Assert.*;

import java.sql.ResultSet;

import org.junit.*;

public class FacadeTest {
    private static FacadeCompagne facade;

    @BeforeClass
    public static void setUpBeforeClass() {
        facade = new FacadeCompagne();
    }

    private static int resultSetSize(ResultSet rs) {
        int size = 0;
        try {
            rs.last();
            size = rs.getRow();
        } catch (Exception e) {
            size = 0;
        }
        return size;
    }

    @AfterClass
    public static void resetSerials() {
        // Reset chat id serial
        facade.getControlEstudiantes()
                .executeQuery("ALTER SEQUENCE \"Chat_ID_seq\" RESTART WITH "
                        + String.valueOf(
                                resultSetSize(facade.getControlEstudiantes().executeQuery("SELECT * FROM \"Chat\";")))
                        + ";");
        // Reset comentario id serial
        facade.getControlEstudiantes().executeQuery("ALTER SEQUENCE \"Comentario_ID_seq\" RESTART WITH "
                + String.valueOf(
                        resultSetSize(facade.getControlEstudiantes().executeQuery("SELECT * FROM \"Comentario\";")))
                + ";");
        // Reset destacable id serial
        facade.getControlEstudiantes().executeQuery("ALTER SEQUENCE \"Destacable_ID_seq\" RESTART WITH "
                + String.valueOf(
                        resultSetSize(facade.getControlEstudiantes().executeQuery("SELECT * FROM \"Destacable\";")))
                + ";");
        // Reset grupo estudio id serial
        facade.getControlEstudiantes().executeQuery("ALTER SEQUENCE \"GrupoEstudio_ID_seq\" RESTART WITH "
                + String.valueOf(
                        resultSetSize(facade.getControlEstudiantes().executeQuery("SELECT * FROM \"GrupoEstudio\";")))
                + ";");
        // Reset horario atencion id serial
        facade.getControlEstudiantes()
                .executeQuery("ALTER SEQUENCE \"HorarioAtencion_ID_seq\" RESTART WITH "
                        + String.valueOf(resultSetSize(
                                facade.getControlEstudiantes().executeQuery("SELECT * FROM \"HorarioAtencion\";")))
                        + ";");
        // Reset interes id serial
        facade.getControlEstudiantes().executeQuery("ALTER SEQUENCE \"Interes_ID_seq\" RESTART WITH "
                + String.valueOf(
                        resultSetSize(facade.getControlEstudiantes().executeQuery("SELECT * FROM \"Interes\";")))
                + ";");
        // Reset logro id serial
        facade.getControlEstudiantes()
                .executeQuery("ALTER SEQUENCE \"Logro_ID_seq\" RESTART WITH "
                        + String.valueOf(
                                resultSetSize(facade.getControlEstudiantes().executeQuery("SELECT * FROM \"Logro\";")))
                        + ";");
        // Reset materia id serial
        facade.getControlEstudiantes().executeQuery("ALTER SEQUENCE \"Materia_ID_seq\" RESTART WITH "
                + String.valueOf(
                        resultSetSize(facade.getControlEstudiantes().executeQuery("SELECT * FROM \"Materia\";")))
                + ";");
        // Reset mensaje id serial
        facade.getControlEstudiantes().executeQuery("ALTER SEQUENCE \"Mensaje_ID_seq\" RESTART WITH "
                + String.valueOf(
                        resultSetSize(facade.getControlEstudiantes().executeQuery("SELECT * FROM \"Mensaje\";")))
                + ";");
        // Reset reunion id serial
        facade.getControlEstudiantes().executeQuery("ALTER SEQUENCE \"Reunion_ID_seq\" RESTART WITH "
                + String.valueOf(
                        resultSetSize(facade.getControlEstudiantes().executeQuery("SELECT * FROM \"Reunion\";")))
                + ";");
        // Reset usuario registrado id serial
        facade.getControlEstudiantes()
                .executeQuery("ALTER SEQUENCE \"UsuarioRegistrado_ID_seq\" RESTART WITH "
                        + String.valueOf(resultSetSize(
                                facade.getControlEstudiantes().executeQuery("SELECT * FROM \"UsuarioRegistrado\";")))
                        + ";");
        
    }
}
