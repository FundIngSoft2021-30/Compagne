package compagne.view;
import compagne.model.control.UsuarioRegistradoControl;

public class App 
{    public static void main( String[] args )
    {
        UsuarioRegistradoControl usc=new UsuarioRegistradoControl();
        System.out.println(usc.findAllUsuariosRegistrados());
        System.out.println(usc.deleteUsuarioRegistrado("5"));
    }
}
