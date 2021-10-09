/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compagne.Negocio;


import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private Button ButtonDescription;

    @FXML
    private Button ButtonGoals;

    @FXML
    private Button ButtonInterest;

    @FXML
    private Button ButtonThemes;

    @FXML
    private Label NombrePersona;

    @FXML
    private TextField TextDescription;

    @FXML
    private TextField TextGoals;

    @FXML
    private TextField TextInterest;

    @FXML
    private TextField TextThemes;

    @FXML
    private Line l1;

    @FXML
    private Line l2;

    @FXML
    private Line l3;

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
    void click(ActionEvent event) {
        if(event.getSource()==ButtonDescription){
            l1.setEndY(l1.getEndY()+62);
            l2.setEndY(l2.getEndY()+62);
            l3.setEndY(l3.getEndY()+62);
            l4.setEndY(l4.getEndY()+62);
            ButtonGoals.setLayoutY(ButtonGoals.getLayoutY()+62);
            ButtonInterest.setLayoutY(ButtonInterest.getLayoutY()+62);
            ButtonThemes.setLayoutY(ButtonThemes.getLayoutY()+62);
            t1.setRotate(-90);
        }if(event.getSource()==ButtonGoals){
            l2.setEndY(l2.getEndY()+62);
            l3.setEndY(l3.getEndY()+62);
            l4.setEndY(l4.getEndY()+62);
            ButtonInterest.setLayoutY(ButtonInterest.getLayoutY()+62);
            ButtonThemes.setLayoutY(ButtonThemes.getLayoutY()+62);
            t2.setRotate(-90);
        }if(event.getSource()==ButtonThemes){
            l3.setEndY(l3.getEndY()+62);
            l4.setEndY(l4.getEndY()+62);
            ButtonThemes.setLayoutY(ButtonThemes.getLayoutY()+62);
            t3.setRotate(-90);
        }if(event.getSource()==ButtonInterest){
            l4.setEndY(l4.getEndY()+62);
            TextInterest.setDisable(false);
            t4.setRotate(-90);
        }
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
