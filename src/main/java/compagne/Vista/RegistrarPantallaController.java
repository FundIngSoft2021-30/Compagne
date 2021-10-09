/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compagne.Vista;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
//<<<<<<< HEAD
import compagne.IntegracionDatos.ControlEstudiantes;
//=======
import compagne.Negocio.FacadeCompagne;
import compagne.Negocio.IFacadeCompagne;
import java.awt.Color;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.Background;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
///>>>>>>> 60a44a9f7d2519d87c29665bdb34fa070ea9e964

/**
 * FXML Controller class
 *
 * @author samue
 */
public class RegistrarPantallaController implements Initializable{    
    
    static IFacadeCompagne facade = FacadeCompagne.getInstance();
    
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

    
//<<<<<<< HEAD
    @FXML
    void click(ActionEvent event) {
        
        if(event.getSource()==checkProfesor){
            checkEstudiante.setSelected(false);
        }
        if(event.getSource()==checkEstudiante){
            checkProfesor.setSelected(false);
        }
        if(event.getSource()==registrarse){
            if(checkAcuerdo.isSelected() && (checkEstudiante.isSelected() || checkProfesor.isSelected())){
                if(email.getText()!=null && nombre.getText()!=null && contraseña.getText()!=null && confirmarContraseña.getText()!=null && confirmarContraseña.getText().equals(contraseña.getText())){
                    if(checkEstudiante.isSelected()){    
                        facade.crearPerfilEstudiante(nombre.getText(), email.getText(), contraseña.getText(), null, null, null, null);
                    }if(checkProfesor.isSelected()){
                        facade.crearPerfilProfesor(nombre.getText(), null, email.getText(), contraseña.getText(), null, null, null, null);
                    }
                }else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("No se pudo registrar");
                    alert.setContentText("Por favor llene todos los campos");
                    alert.showAndWait();
                }
            }
        }
    }
                    
                            
                
    
    
//=======
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
                    && this.facade.crearPerfilEstudiante(nombre, email, contra, null, null, null, null)
                            .getContrasenia() == null)
                    || (checkAcuerdo && checkProfesor
                            && this.facade
                                    .crearPerfilProfesor(nombre, null, email, contra, null, null, null, null)
                                    .getContrasenia() == null)))
                throw new Exception("Error");
        } catch (Exception e) {
            // Aquí ocurrió algún error
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
//>>>>>>> 60a44a9f7d2519d87c29665bdb34fa070ea9e964

}
