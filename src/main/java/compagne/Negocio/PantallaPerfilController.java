
package compagne.Negocio;

import compagne.Entidades.Grupo;
import compagne.Entidades.Usuario;
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
public class PantallaPerfilController {

    private String usu;
    
    

    @FXML
    private Button ButtonDescription;

    @FXML
    private Button ButtonGoals;

    @FXML
    private Button ButtonGroups;

    @FXML
    private Button ButtonInterest;

    @FXML
    private Button ButtonThemes;

    @FXML
    private ListView<?> ListGroups;

    @FXML
    private TextField NombreEstudiante;

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
    private Line l5;

    @FXML
    private ListView<?> listGroups;

    @FXML
    private Polygon t1;

    @FXML
    private Polygon t2;

    @FXML
    private Polygon t3;

    @FXML
    private Polygon t4;

    @FXML
    private Polygon t5;
    
    @FXML
    void click(ActionEvent event) {
        if (event.getSource() == ButtonDescription && TextDescription.isVisible()) {
            l1.setLayoutY(l1.getLayoutY() - 62);
            l2.setLayoutY(l2.getLayoutY() - 62);
            l3.setLayoutY(l3.getLayoutY() - 62);
            l4.setLayoutY(l4.getLayoutY() - 62);
            l5.setLayoutY(l5.getLayoutY() - 62);
            ButtonGoals.setLayoutY(ButtonGoals.getLayoutY() - 62);
            ButtonInterest.setLayoutY(ButtonInterest.getLayoutY() - 62);
            ButtonThemes.setLayoutY(ButtonThemes.getLayoutY() - 62);
            ButtonGroups.setLayoutY(ButtonGroups.getLayoutY() - 62);
            t1.setRotate(0);
            t2.setLayoutY(t2.getLayoutY() - 62);
            t3.setLayoutY(t3.getLayoutY() - 62);
            t4.setLayoutY(t4.getLayoutY() - 62);
            t5.setLayoutY(t5.getLayoutY() - 62);
            TextDescription.setVisible(false);
        } else if (event.getSource() == ButtonDescription && !TextDescription.isVisible()) {
            l1.setLayoutY(l1.getLayoutY() + 62);
            l2.setLayoutY(l2.getLayoutY() + 62);
            l3.setLayoutY(l3.getLayoutY() + 62);
            l4.setLayoutY(l4.getLayoutY() + 62);
            l5.setLayoutY(l5.getLayoutY() + 62);
            ButtonGoals.setLayoutY(ButtonGoals.getLayoutY() + 62);
            ButtonInterest.setLayoutY(ButtonInterest.getLayoutY() + 62);
            ButtonThemes.setLayoutY(ButtonThemes.getLayoutY() + 62);
            ButtonGroups.setLayoutY(ButtonGroups.getLayoutY() + 62);
            t1.setRotate(-90);
            t2.setLayoutY(t2.getLayoutY() + 62);
            t3.setLayoutY(t3.getLayoutY() + 62);
            t4.setLayoutY(t4.getLayoutY() + 62);
            t5.setLayoutY(t5.getLayoutY() + 62);
            TextDescription.setVisible(true);
        }
        if (event.getSource() == ButtonGoals && TextGoals.isVisible()) {
            l2.setLayoutY(l2.getLayoutY() - 62);
            l3.setLayoutY(l3.getLayoutY() - 62);
            l4.setLayoutY(l4.getLayoutY() - 62);
            l5.setLayoutY(l5.getLayoutY() - 62);
            ButtonInterest.setLayoutY(ButtonInterest.getLayoutY() - 62);
            ButtonThemes.setLayoutY(ButtonThemes.getLayoutY() - 62);
            ButtonGroups.setLayoutY(ButtonGroups.getLayoutY() - 62);
            t2.setRotate(0);
            t3.setLayoutY(t3.getLayoutY() - 62);
            t4.setLayoutY(t4.getLayoutY() - 62);
            t5.setLayoutY(t5.getLayoutY() - 62);
            TextGoals.setVisible(false);
        } else if (event.getSource() == ButtonGoals && !TextGoals.isVisible()) {
            l2.setLayoutY(l2.getLayoutY() + 62);
            l3.setLayoutY(l3.getLayoutY() + 62);
            l4.setLayoutY(l4.getLayoutY() + 62);
            l5.setLayoutY(l5.getLayoutY() + 62);
            ButtonInterest.setLayoutY(ButtonInterest.getLayoutY() + 62);
            ButtonThemes.setLayoutY(ButtonThemes.getLayoutY() + 62);
            ButtonGroups.setLayoutY(ButtonGroups.getLayoutY() + 62);
            t2.setRotate(-90);
            t3.setLayoutY(t3.getLayoutY() + 62);
            t4.setLayoutY(t4.getLayoutY() + 62);
            t5.setLayoutY(t5.getLayoutY() + 62);
            TextGoals.setVisible(true);
        }
        if (event.getSource() == ButtonThemes && !TextThemes.isVisible()) {
            l3.setLayoutY(l3.getLayoutY() + 62);
            l4.setLayoutY(l4.getLayoutY() + 62);
            l5.setLayoutY(l5.getLayoutY() + 62);
            ButtonInterest.setLayoutY(ButtonInterest.getLayoutY() + 62);
            ButtonGroups.setLayoutY(ButtonGroups.getLayoutY() + 62);
            t3.setRotate(-90);
            t4.setLayoutY(t4.getLayoutY() + 62);
            t5.setLayoutY(t5.getLayoutY() + 62);
            TextThemes.setVisible(true);
        } else if (event.getSource() == ButtonThemes && TextThemes.isVisible()) {
            l3.setLayoutY(l3.getLayoutY() - 62);
            l4.setLayoutY(l4.getLayoutY() - 62);
            l5.setLayoutY(l5.getLayoutY() - 62);
            ButtonInterest.setLayoutY(ButtonInterest.getLayoutY() - 62);
            ButtonGroups.setLayoutY(ButtonGroups.getLayoutY() - 62);
            t3.setRotate(0);
            t4.setLayoutY(t4.getLayoutY() - 62);
            t5.setLayoutY(t5.getLayoutY() - 62);
            TextThemes.setVisible(false);
        }
        if (event.getSource() == ButtonInterest && !TextInterest.isVisible()) {
            l4.setLayoutY(l4.getLayoutY() + 62);
            l5.setLayoutY(l5.getLayoutY() + 62);
            ButtonGroups.setLayoutY(ButtonGroups.getLayoutY() + 62);
            t4.setRotate(-90);
            t5.setLayoutY(t5.getLayoutY() + 62);
            TextInterest.setVisible(true);
        } else if (event.getSource() == ButtonInterest && TextInterest.isVisible()) {
            l4.setLayoutY(l4.getLayoutY() - 62);
            l5.setLayoutY(l5.getLayoutY() - 62);
            ButtonGroups.setLayoutY(ButtonGroups.getLayoutY() - 62);
            t4.setRotate(0);
            t5.setLayoutY(t5.getLayoutY() - 62);
            TextInterest.setVisible(false);
        }
        if (event.getSource() == ButtonGroups && !listGroups.isVisible()) {
            l5.setLayoutY(l5.getLayoutY() + 62);
            t5.setRotate(-90);
            listGroups.setVisible(true);
        } else if (event.getSource() == ButtonGroups && listGroups.isVisible()) {
            l5.setLayoutY(l5.getLayoutY() - 62);
            t5.setRotate(0);
            listGroups.setVisible(false);
        }
        
    }

    public void start(String u) {
        this.usu = u;
        FacadeCompagne.getInstance().buscarIDUsuario(u);
        
        this.NombreEstudiante.setText(u);
    }
}