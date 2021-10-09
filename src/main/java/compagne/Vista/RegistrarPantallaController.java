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
import compagne.IntegracionDatos.ControlEstudiantes;

/**
 * FXML Controller class
 *
 * @author samue
 */
public class RegistrarPantallaController implements Initializable {

    
    @FXML
    private CheckBox checkAcuerdo;

    @FXML
    private CheckBox checkEstudiante;

    @FXML
    private CheckBox checkProfesor;

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
        
        if(event.getSource()==checkProfesor){
            checkEstudiante.setSelected(false);
        }
        if(event.getSource()==checkEstudiante){
            checkProfesor.setSelected(false);
        }
        if(event.getSource()==registrarse){
            
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
