package compagne.Vista;

import java.util.HashSet;
import java.util.Scanner;

import compagne.Entidades.Comentario;
import compagne.Entidades.Estudiante;
import compagne.Negocio.FacadeCompagne;

public class MainApp {
        public static void main(String[] args) {
                Scanner sc = new Scanner(System.in);
                FacadeCompagne facade = new FacadeCompagne();
                HashSet<Comentario> comentarios = new HashSet<>();
                comentarios.add(new Comentario(null, "Hola soy pepe"));
                comentarios.add(new Comentario("4.5", null));
                HashSet<String> h_aHashSet = new HashSet<>();
                h_aHashSet.add("Lunes 9-11");
                h_aHashSet.add("Miercoles 12-2");
                HashSet<String> logros = new HashSet<>();
                logros.add("Logro1");
                logros.add("Logro2");
                HashSet<String> materias = new HashSet<>();
                materias.add("PensamientoS");
                materias.add("PensamientoA");
                materias.add("TeoriaC");
                materias.add("Probabilidad");
                facade.crearPerfilProfesor("Federico Lopez", "Solo profes PT", "lopez.federico@javeriana.edu.co",
                                "JaveTE@cher123", h_aHashSet, materias, comentarios, logros);
                System.out.println("Creado");
                sc.nextLine();
                facade.modificarPerfilProfesor("Federico Lopez Triana", "Seriedad", "lopez.federico@javeriana.edu.co",
                                "JaveTE@cher123");
                System.out.println("Modificado ");
                sc.nextLine();
                comentarios.clear();
                comentarios.add(new Comentario(null, "Alcornoque"));
                comentarios.add(new Comentario(null, "Necio"));
                logros.add("No me desconcentro en clases virtuales");
                facade.crearPerfilProfesor("Albert Perilla", "Solo profes PT", "peri_al@javeriana.edu.co",
                                "la_contradefed$", h_aHashSet, materias, comentarios, logros);
                System.out.println("Agregado");
                sc.nextLine();
                System.out.println("-----------------------------\n-------------------------------");
                comentarios.clear();
                comentarios.add(new Comentario(null, "Pesimo servicio"));
                comentarios.add(new Comentario("0.2", null));
                logros.clear();
                logros.add("No me desconcentro en clases virtuales");
                HashSet<String> intereses = new HashSet<>();
                intereses.add("Cloud Computing");
                intereses.add("Las niñas");
                facade.crearPerfilEstudiante("Nicolas Bayona", "nclsbayona@javeriana.edu.co", "L@Contr@", materias,
                                comentarios, intereses, logros);
                System.out.println("Creado estudiante");
                sc.nextLine();
                facade.modificarPerfilEstudiante("Nicolas Bayona", "nclsbayona@javeriana.edu.co", "admin456");
                System.out.println("Modificado el estudiante");
                sc.nextLine();
                intereses.clear();
                intereses.add("Futbol");
                comentarios.clear();
                logros.clear();
                facade.crearPerfilEstudiante("Lucas Podolski", "podolskiS@javeriana.edu.co", "Contra", materias,
                                comentarios, intereses, logros);
                System.out.println("Agregado");
                sc.nextLine();
                facade.calificarUsuario(new Comentario("4.2", null), new Estudiante("nombre",
                                "podolskiS@javeriana.edu.co", null, null, "contrasenia", null, null));
                System.out.println("Calificado Podolski");
                sc.nextLine();
                System.out.println("Iniciar sesion como Nicolas Bayona "
                                +  facade.iniciarSesion("nclsbayona@javeriana.edu.co", "admin456"));
                sc.nextLine();
                System.out.println("Iniciar sesion como Nicolas Bayona (MAL)");
                try {
                        System.out.println(facade.iniciarSesion("nclsbayona@javeriana.edu.co", "adm6"));
                } catch (Exception e) {
                        System.out.println("Fallo");
                }
                sc.nextLine();
                System.out.println("Iniciar sesion como Albert Perilla "
                                + facade.iniciarSesion("peri_al@javeriana.edu.co",
                                "la_contradefed$"));
                sc.nextLine();
                System.out.println("Iniciar sesion como Albert Perilla (MAL)");
                try {
                        System.out.println(facade.iniciarSesion("peri_al@javeriana.edu.co",
                        "la_contradfed$"));
                } catch (Exception e) {
                        System.out.println("Fallo");
                }
                sc.nextLine();
                System.out.println("Los metodos de grupo y eso");
                System.out.println(facade.crearGrupo("5inc0+1", "aasa", "N", facade.iniciarSesion("nclsbayona@javeriana.edu.co", "admin456")));
                sc.nextLine();
                System.out.println(facade.modificarGrupo("5inc0+1", "aasa", "S"));
                sc.nextLine();
                System.out.println(facade.agregarUsuarioAGrupo("aasa", "peri_al@javeriana.edu.co", "N"));
                sc.nextLine();
                System.out.println(facade.eliminarUsuarioDeGrupo("aasa", "peri_al@javeriana.edu.co"));
                sc.nextLine();
                System.out.println(facade.eliminarGrupo("aasa"));
                sc.nextLine();
                facade.eliminarGrupo("aasa");
                facade.eliminarPerfilProfesor("lopez.federico@javeriana.edu.co");
                facade.eliminarPerfilProfesor("peri_al@javeriana.edu.co");
                facade.eliminarPerfilEstudiante("nclsbayona@javeriana.edu.co");
                facade.eliminarPerfilEstudiante("podolskiS@javeriana.edu.co");
                sc.close();
        }
}
