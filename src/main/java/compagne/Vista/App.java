package compagne.Vista;
import compagne.IntegracionDatos.UsuarioControl;
public class App 
{    public static void main( String[] args )
    {
        UsuarioControl usc=new UsuarioControl();
        System.out.println(usc.findAllUsuariosRegistrados());
        System.out.println(usc.deleteUsuarioRegistrado("5"));
    }
}
