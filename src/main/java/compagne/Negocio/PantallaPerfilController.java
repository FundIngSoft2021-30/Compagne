/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compagne.Negocio;

import compagne.Entidades.Grupo;
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
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author samue
 */
public class PantallaPerfilController implements Initializable {

    private Button ButtonDescription;

    private Button ButtonGoals;

    private Button ButtonInterest;

    private Button ButtonThemes;
        
    private Button ButtonGroups;

    private TextField TextDescription;

    private TextField TextGoals;

    private TextField TextInterest;

    private TextField TextThemes;
    
    private TextField CarreraText;

    private TextField NombreEstudiante;

    private TextField SemestreText;

    private Line l1;

    private Line l2;

    private Line l3;

    private Line l4;
    
    private Line l5;

    private Polygon t1;

    private Polygon t2;

    private Polygon t3;

    private Polygon t4;
    

    private Polygon t5;

    private ListView<Grupo> listGroups;
    

     @FXML

    void click(ActionEvent event) {
        if (event.getSource() == ButtonDescription && !TextDescription.isVisible()) {
            l1.setEndY(l1.getEndY() - 62);
            l2.setEndY(l2.getEndY() - 62);
            l3.setEndY(l3.getEndY() - 62);
            l4.setEndY(l4.getEndY() - 62);
            l5.setEndY(l4.getEndY() - 62);
            ButtonGoals.setTranslateY(ButtonGoals.getTranslateY() - 62);
            ButtonInterest.setTranslateY(ButtonInterest.getTranslateY() - 62);
            ButtonThemes.setTranslateY(ButtonThemes.getTranslateY() - 62);
            ButtonGroups.setTranslateY(ButtonGroups.getTranslateY() - 62);
            t1.setRotate(t1.getRotate()-90);
            TextDescription.setVisible(true);
        }else if(event.getSource() == ButtonDescription && TextDescription.isVisible()){
            l1.setEndY(l1.getEndY() + 62);
            l2.setEndY(l2.getEndY() + 62);
            l3.setEndY(l3.getEndY() + 62);
            l4.setEndY(l4.getEndY() + 62);
            l5.setEndY(l4.getEndY() + 62);
            ButtonGoals.setTranslateY(ButtonGoals.getTranslateY() + 62);
            ButtonInterest.setTranslateY(ButtonInterest.getTranslateY() + 62);
            ButtonThemes.setTranslateY(ButtonThemes.getTranslateY() + 62);
            ButtonGroups.setTranslateY(ButtonGroups.getTranslateY() + 62);
            t1.setRotate(t1.getRotate()+90);
            TextDescription.setVisible(true);
        }
        if (event.getSource() == ButtonGoals && !TextGoals.isVisible()) {
            l2.setEndY(l2.getEndY() - 62);
            l3.setEndY(l3.getEndY() - 62);
            l4.setEndY(l4.getEndY() - 62);
            l5.setEndY(l5.getEndY() - 62);
            ButtonInterest.setLayoutY(ButtonInterest.getTranslateY() - 62);
            ButtonThemes.setLayoutY(ButtonThemes.getTranslateY() - 62);
            ButtonGroups.setTranslateY(ButtonGroups.getTranslateY() - 62);
            t2.setRotate(t2.getRotate()-90);
            TextGoals.setVisible(true);
        }else if (event.getSource() == ButtonGoals && TextGoals.isVisible()) {
            l2.setEndY(l2.getEndY() + 62);
            l3.setEndY(l3.getEndY() + 62);
            l4.setEndY(l4.getEndY() + 62);
            l5.setEndY(l5.getEndY() + 62);
            ButtonInterest.setLayoutY(ButtonInterest.getLayoutY() + 62);
            ButtonThemes.setLayoutY(ButtonThemes.getLayoutY() + 62);
            ButtonGroups.setTranslateY(ButtonGroups.getTranslateY() + 62);
            t2.setRotate(t2.getRotate()+90);            
            TextGoals.setVisible(false);
        }
        if (event.getSource() == ButtonThemes && !TextThemes.isVisible()) {
            l3.setEndY(l3.getEndY() - 62);
            l4.setEndY(l4.getEndY() - 62);
            l5.setEndY(l5.getEndY() - 62);
            ButtonThemes.setTranslateY(ButtonThemes.getTranslateY() - 62);
            ButtonGroups.setTranslateY(ButtonGroups.getTranslateY() - 62);
            t3.setRotate(t3.getRotate()-90);
            TextThemes.setVisible(true);
        }else if (event.getSource() == ButtonThemes && TextThemes.isVisible()) {
            l3.setEndY(l3.getEndY() + 62);
            l4.setEndY(l4.getEndY() + 62);
            l5.setEndY(l5.getEndY() + 62);
            ButtonThemes.setTranslateY(ButtonThemes.getTranslateY() + 62);
            ButtonGroups.setTranslateY(ButtonGroups.getTranslateY() + 62);
            t3.setRotate(t3.getRotate()-90);
            TextThemes.setVisible(false);
        }
        if (event.getSource() == ButtonInterest && !TextInterest.isVisible()) {
            l4.setEndY(l4.getEndY() - 62);
            l5.setEndY(l5.getEndY() - 62);
            ButtonGroups.setTranslateY(ButtonGroups.getTranslateY()-62);
            t4.setRotate(t4.getRotate()-90);
            TextInterest.setVisible(true);
        }else if (event.getSource() == ButtonInterest && TextInterest.isVisible()) {
            l4.setEndY(l4.getEndY() + 62);
            l5.setEndY(l5.getEndY() + 62);
            ButtonGroups.setTranslateY(ButtonGroups.getTranslateY()+62);
            t4.setRotate(t4.getRotate()+90);
            TextInterest.setVisible(false);
        }
        if (event.getSource() == ButtonGroups && !listGroups.isVisible()) {
            l5.setEndY(l5.getEndY() - 62);
            t5.setRotate(t4.getRotate()-90);
            listGroups.setVisible(true);
        }else if (event.getSource() == ButtonGroups && listGroups.isVisible()) {
            l5.setEndY(l5.getEndY() + 62);
            t5.setRotate(t4.getRotate()+90);
            listGroups.setVisible(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        NombreEstudiante.setText(FacadeCompagne.getInstance().usu);
    }
}
