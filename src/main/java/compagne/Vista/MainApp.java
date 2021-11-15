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
                int est1ID=ce.getEstudianteID(estudiante1.getEmail());
                int est2ID=ce.getEstudianteID(estudiante2.getEmail());
                ChatP chat1=new ChatP("14/11/21", 'P', null, estudiante1, estudiante2);
                ce.crearEstudiante(estudiante1);
                ce.crearEstudiante(estudiante2);
                cc.crearChat(chat1, est1ID, est2ID);
                cc.eliminarChat(chat1);
                ce.eliminarEstudiante(estudiante1.getEmail());
                ce.eliminarEstudiante(estudiante2.getEmail());
        }
}
