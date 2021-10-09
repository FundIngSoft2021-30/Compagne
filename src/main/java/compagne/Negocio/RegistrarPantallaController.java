/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    
package compagne.Negocio;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author samue
 */
public class RegistrarPantallaController implements Initializable {

    private static IFacadeCompagne facade = FacadeCompagne.getInstance();

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
    void click(ActionEvent event) {

        if (event.getSource() == this.checkProfesor) {
            checkEstudiante.setSelected(false);
        } else if (event.getSource() == this.checkEstudiante) {
            checkProfesor.setSelected(false);
        } else if (event.getSource() == this.registrarse) {
            if (checkAcuerdo.isSelected() && (checkEstudiante.isSelected() || checkProfesor.isSelected())) {
                if (!email.getText().equals("") && !nombre.getText().equals("") && !contraseña.getText().equals("")
                        && !confirmarContraseña.getText().equals("")
                        && confirmarContraseña.getText().equals(contraseña.getText())) {
                    if (checkEstudiante.isSelected()) {
                        facade.crearPerfilEstudiante(nombre.getText(), email.getText(), contraseña.getText(), null,
                                null, null, null);
                    } else if (checkProfesor.isSelected()) {
                        facade.crearPerfilProfesor(nombre.getText(), null, email.getText(), contraseña.getText(), null,
                                null, null, null);
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("No se pudo registrar");
                    alert.setContentText("Por favor llene todos los campos");
                    alert.showAndWait();
                }
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
