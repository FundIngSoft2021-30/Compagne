/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compagne.Negocio;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author samue
 */
public class PantallaPerfilController implements Initializable {

    @FXML
    private TextField TextDescription;

    @FXML
    private TextField TextThemes;

    @FXML
    private TextField TextGoals;

    @FXML
    private Button ButtonDescription;

    @FXML
    private Line l1;

    @FXML
    private Button ButtonGoals;

    @FXML
    private Line l2;

    @FXML
    private Button ButtonThemes;

    @FXML
    private Line l3;

    @FXML
    private Button ButtonInterest;

    @FXML
    private Line l4;

    @FXML
    private Polygon t1;

    @FXML
    private Polygon t2;

    @FXML
    private Polygon t3;

    @FXML
    private Polygon t4;

    @FXML
    private TextField TextInterest;

    @FXML
    private Label NombrePersona;
    
    boolean abierto = false;
     @FXML
    void click(ActionEvent event) {
        
        if (event.getSource() == this.ButtonDescription && abierto == false) {
                l1.setLayoutY(l1.getLayoutY()+62);
                l2.setLayoutY(l2.getLayoutY()+62);
                l3.setLayoutY(l3.getLayoutY()+62);
                l4.setLayoutY(l4.getLayoutY()+62);
                ButtonGoals.setLayoutY(ButtonGoals.getLayoutY() + 62);
                ButtonInterest.setLayoutY(ButtonInterest.getLayoutY() + 62);
                ButtonThemes.setLayoutY(ButtonThemes.getLayoutY() + 62);
                t1.setRotate(-90);
                t2.setLayoutY(t2.getLayoutY() + 62);
                t3.setLayoutY(t3.getLayoutY() + 62);
                t4.setLayoutY(t4.getLayoutY() + 62);
                abierto = true;
        }else if (event.getSource() == this.ButtonDescription && abierto == true) {
                l1.setLayoutY(l1.getLayoutY()-62);
                l2.setLayoutY(l2.getLayoutY()-62);
                l3.setLayoutY(l3.getLayoutY()-62);
                l4.setLayoutY(l4.getLayoutY()-62);
                ButtonGoals.setLayoutY(ButtonGoals.getLayoutY() - 62);
                ButtonInterest.setLayoutY(ButtonInterest.getLayoutY() - 62);
                ButtonThemes.setLayoutY(ButtonThemes.getLayoutY() - 62);
                t1.setRotate(0);
                t2.setLayoutY(t2.getLayoutY() - 62);
                t3.setLayoutY(t3.getLayoutY() - 62);
                t4.setLayoutY(t4.getLayoutY() - 62);
                abierto = false;
        }
        if (event.getSource() == this.ButtonGoals && abierto == false) {
            l2.setLayoutY(l2.getLayoutY()+62);
            l3.setLayoutY(l3.getLayoutY()+62);
            l4.setLayoutY(l4.getLayoutY()+62);
            ButtonInterest.setLayoutY(ButtonInterest.getLayoutY() + 62);
            ButtonThemes.setLayoutY(ButtonThemes.getLayoutY() + 62);
            t2.setRotate(-90);
            t3.setLayoutY(t3.getLayoutY() + 62);
            t4.setLayoutY(t4.getLayoutY() + 62);
            abierto = true;
        } else if (event.getSource() == this.ButtonGoals && abierto == true) {
            l2.setLayoutY(l2.getLayoutY()-62);
            l3.setLayoutY(l3.getLayoutY()-62);
            l4.setLayoutY(l4.getLayoutY()-62);
            ButtonInterest.setLayoutY(ButtonInterest.getLayoutY() - 62);
            ButtonThemes.setLayoutY(ButtonThemes.getLayoutY() - 62);
            t2.setRotate(0);
            t3.setLayoutY(t3.getLayoutY() - 62);
            t4.setLayoutY(t4.getLayoutY() - 62);
            abierto = false;
        }
        if (event.getSource() == ButtonThemes && abierto == false) {
            l3.setLayoutY(l3.getLayoutY()+62);
            l4.setLayoutY(l4.getLayoutY()+62);
            ButtonInterest.setLayoutY(ButtonInterest.getLayoutY() + 62);
            t3.setRotate(-90);
            t4.setLayoutY(t4.getLayoutY() + 62);
            abierto = true;
        } else if (event.getSource() == ButtonThemes && abierto == true) {
            l3.setLayoutY(l3.getLayoutY()-62);
            l4.setLayoutY(l4.getLayoutY()-62);
            ButtonInterest.setLayoutY(ButtonInterest.getLayoutY() -62);
            t3.setRotate(0);
            t4.setLayoutY(t4.getLayoutY() - 62);
            abierto = false;
        }
        
        if (event.getSource() == ButtonInterest && abierto == true) {
            l4.setLayoutY(l4.getLayoutY()-62);
            TextInterest.setOpacity(0);
            t4.setRotate(0);
            abierto = false;
        }else if (event.getSource() == ButtonInterest && abierto == false) {
            l4.setLayoutY(l4.getLayoutY()+62);
            TextInterest.setOpacity(1);
            t4.setRotate(-90);
            abierto = true;
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
