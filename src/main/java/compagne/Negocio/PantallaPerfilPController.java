package compagne.Negocio;

import javafx.stage.Stage;

import javafx.scene.Node;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class PantallaPerfilPController {
    private String usu;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ButtonComentarios;

    @FXML
    private Button ButtonExperiencia;

    @FXML
    private Button ButtonHorarios;

    @FXML
    private Button ButtonLogros;

    @FXML
    private Button ButtonMaterias;

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
    private Button menu;

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

    }

    @FXML
    public void desplegarMenu(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {
        assert ButtonComentarios != null
                : "fx:id=\"ButtonComentarios\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert ButtonExperiencia != null
                : "fx:id=\"ButtonExperiencia\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert ButtonHorarios != null
                : "fx:id=\"ButtonHorarios\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert ButtonLogros != null
                : "fx:id=\"ButtonLogros\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert ButtonMaterias != null
                : "fx:id=\"ButtonMaterias\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert NombreEstudiante != null
                : "fx:id=\"NombreEstudiante\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert TextDescription != null
                : "fx:id=\"TextDescription\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert TextGoals != null
                : "fx:id=\"TextGoals\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert TextInterest != null
                : "fx:id=\"TextInterest\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert TextThemes != null
                : "fx:id=\"TextThemes\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert l1 != null : "fx:id=\"l1\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert l2 != null : "fx:id=\"l2\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert l3 != null : "fx:id=\"l3\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert l4 != null : "fx:id=\"l4\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert l5 != null : "fx:id=\"l5\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert listGroups != null
                : "fx:id=\"listGroups\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert menu != null : "fx:id=\"menu\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert t1 != null : "fx:id=\"t1\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert t2 != null : "fx:id=\"t2\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert t3 != null : "fx:id=\"t3\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert t4 != null : "fx:id=\"t4\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert t5 != null : "fx:id=\"t5\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";

    }

    public void start(String u) {
        this.usu = u;
        this.NombreEstudiante.setText(u);
    }

}
