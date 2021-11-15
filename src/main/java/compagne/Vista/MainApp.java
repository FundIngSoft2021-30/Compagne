package compagne.Vista;

import java.util.HashSet;

import compagne.Entidades.ChatP;
import compagne.Entidades.Estudiante;
import compagne.Entidades.Mensaje;
import compagne.IntegracionDatos.ControlChats;
import compagne.IntegracionDatos.ControlEstudiantes;

public class MainApp {
        public static void main(String[] args) {
                Estudiante estudiante1=new Estudiante("Juan Carlos Caceres Tercero", "email", null, null, "contrasenia", null, null);
                Estudiante estudiante2=new Estudiante("Juan Carlos Caceres Cuarto", "email2", null, null, "contrasenia", null, null);
                ControlEstudiantes ce=new ControlEstudiantes();
                ControlChats cc=new ControlChats();
                ChatP chat1=new ChatP("14/11/21", 'P', null, estudiante1, estudiante2);
                ce.crearEstudiante(estudiante1);
                ce.crearEstudiante(estudiante2);
                int est1ID=ce.getEstudianteID(estudiante1.getEmail());
                int est2ID=ce.getEstudianteID(estudiante2.getEmail());
                cc.crearChat(chat1, est1ID, est2ID);
                Mensaje mensaje1=new Mensaje("14/11/21 13:26:25", "Hola", estudiante1, chat1);
                cc.insertarMensajeXChat(cc.getChatID(chat1), mensaje1, est1ID);
                Mensaje mensaje2=new Mensaje("14/11/21 13:26:30", "Hola", estudiante2, chat1);
                cc.insertarMensajeXChat(cc.getChatID(chat1), mensaje2, est2ID);
                HashSet<Mensaje> mensajes=cc.mensajesXChat(cc.getChatID(chat1));
                System.out.println("El tamaño es: "+mensajes.size());
                for (Mensaje m:mensajes)
                        System.out.println(m);
                cc.eliminarChat(chat1);
                ce.eliminarEstudiante(estudiante1.getEmail());
                ce.eliminarEstudiante(estudiante2.getEmail());
        }
}
