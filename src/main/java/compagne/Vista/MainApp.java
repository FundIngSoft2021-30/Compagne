package compagne.Vista;

import compagne.Entidades.ChatP;
import compagne.Entidades.Estudiante;
import compagne.IntegracionDatos.ControlChats;
import compagne.IntegracionDatos.ControlEstudiantes;

public class MainApp {
        public static void main(String[] args) {
                Estudiante estudiante1=new Estudiante("Juan Carlos Caceres Tercero", "email", null, null, "contrasenia", null, null);
                Estudiante estudiante2=new Estudiante("Juan Carlos Caceres Cuarto", "email2", null, null, "contrasenia", null, null);
                ControlEstudiantes ce=new ControlEstudiantes();
                ControlChats cc=new ControlChats();
                ChatP chat1=new ChatP(estudiante1, estudiante2);
                ce.crearEstudiante(estudiante1);
                ce.crearEstudiante(estudiante2);
                ce.eliminarEstudiante(estudiante1.getEmail());
                ce.eliminarEstudiante(estudiante2.getEmail());
        }
}
