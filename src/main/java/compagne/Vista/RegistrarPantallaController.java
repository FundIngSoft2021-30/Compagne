/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compagne.Vista;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import compagne.Negocio.FacadeCompagne;

/**
 * FXML Controller class
 *
 * @author samue
 */
public class RegistrarPantallaController {

    private FacadeCompagne facadeCompagne;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private CheckBox checkAcuerdo;

    @FXML
    private CheckBox checkEstudiante;

    @FXML
    private CheckBox checkProfesor;

    @FXML
    private PasswordField confirmarContraseña;

    @FXML
    private PasswordField contraseña;

    @FXML
    private TextField email;

    @FXML
    private TextField nombre;

    @FXML
    private Button registrarse;

    @FXML
    void registroAction(ActionEvent event) {
        try {
            boolean checkAcuerdo = this.checkAcuerdo.isSelected();
            boolean checkProfesor = this.checkProfesor.isSelected();
            boolean checkEstudiante = this.checkEstudiante.isSelected();
            String contra = this.contraseña.getText();
            String confirmacion = this.confirmarContraseña.getText();
            String email = this.email.getText();
            String nombre = this.nombre.getText();
            // TODO faltan confirmaciones
            if ((contra.equals(confirmacion))&&((checkAcuerdo && checkEstudiante
                    && this.facadeCompagne.crearPerfilEstudiante(nombre, email, contra, null, null, null, null)
                            .getContrasenia() == null)
                    || (checkAcuerdo && checkProfesor
                            && this.facadeCompagne
                                    .crearPerfilProfesor(nombre, null, email, contra, null, null, null, null)
                                    .getContrasenia() == null)))
                throw new Exception("Error");
        } catch (Exception e) {
            // Aquí ocurrió algún error
        }
    }

    @FXML
    void initialize() {
        this.facadeCompagne = new FacadeCompagne();
        assert checkAcuerdo != null
                : "fx:id=\"checkAcuerdo\" was not injected: check your FXML file 'RegistrarPantalla.fxml'.";
        assert checkEstudiante != null
                : "fx:id=\"checkEstudiante\" was not injected: check your FXML file 'RegistrarPantalla.fxml'.";
        assert checkProfesor != null
                : "fx:id=\"checkProfesor\" was not injected: check your FXML file 'RegistrarPantalla.fxml'.";
        assert confirmarContraseña != null
                : "fx:id=\"confirmarContraseña\" was not injected: check your FXML file 'RegistrarPantalla.fxml'.";
        assert contraseña != null
                : "fx:id=\"contraseña\" was not injected: check your FXML file 'RegistrarPantalla.fxml'.";
        assert email != null : "fx:id=\"email\" was not injected: check your FXML file 'RegistrarPantalla.fxml'.";
        assert nombre != null : "fx:id=\"nombre\" was not injected: check your FXML file 'RegistrarPantalla.fxml'.";
        assert registrarse != null
                : "fx:id=\"registrarse\" was not injected: check your FXML file 'RegistrarPantalla.fxml'.";
    }
}
