package compagne.Negocio;

import javafx.stage.Stage;
import compagne.Entidades.Comentario;
import compagne.Vista.App;
import javafx.scene.Node;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.stage.Modality;

public class PantallaPerfilProfesorController {

    private String usu;


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
    private ListView<Comentario> listGroups;

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
        int tam = 62;
        if (event.getSource() == ButtonExperiencia && TextDescription.isVisible()) {
            l1.setLayoutY(l1.getLayoutY() - tam);
            l2.setLayoutY(l2.getLayoutY() - tam);
            l3.setLayoutY(l3.getLayoutY() - tam);
            l4.setLayoutY(l4.getLayoutY() - tam);
            l5.setLayoutY(l5.getLayoutY() - tam);
            ButtonLogros.setLayoutY(ButtonLogros.getLayoutY() - tam);
            ButtonHorarios.setLayoutY(ButtonHorarios.getLayoutY() - tam);
            ButtonMaterias.setLayoutY(ButtonMaterias.getLayoutY() - tam);
            ButtonComentarios.setLayoutY(ButtonComentarios.getLayoutY() - tam);
            t1.setRotate(0);
            t2.setLayoutY(t2.getLayoutY() - tam);
            t3.setLayoutY(t3.getLayoutY() - tam);
            t4.setLayoutY(t4.getLayoutY() - tam);
            t5.setLayoutY(t5.getLayoutY() - tam);
            TextDescription.setVisible(false);
        } else if (event.getSource() == ButtonExperiencia && !TextDescription.isVisible()) {
            l1.setLayoutY(l1.getLayoutY() + tam);
            l2.setLayoutY(l2.getLayoutY() + tam);
            l3.setLayoutY(l3.getLayoutY() + tam);
            l4.setLayoutY(l4.getLayoutY() + tam);
            l5.setLayoutY(l5.getLayoutY() + tam);
            ButtonLogros.setLayoutY(ButtonLogros.getLayoutY() + tam);
            ButtonHorarios.setLayoutY(ButtonHorarios.getLayoutY() + tam);
            ButtonMaterias.setLayoutY(ButtonMaterias.getLayoutY() + tam);
            ButtonComentarios.setLayoutY(ButtonComentarios.getLayoutY() + tam);
            t1.setRotate(-90);
            t2.setLayoutY(t2.getLayoutY() + tam);
            t3.setLayoutY(t3.getLayoutY() + tam);
            t4.setLayoutY(t4.getLayoutY() + tam);
            t5.setLayoutY(t5.getLayoutY() + tam);
            TextDescription.setVisible(true);
        }
        if (event.getSource() == ButtonLogros && TextGoals.isVisible()) {
            l2.setLayoutY(l2.getLayoutY() - tam);
            l3.setLayoutY(l3.getLayoutY() - tam);
            l4.setLayoutY(l4.getLayoutY() - tam);
            l5.setLayoutY(l5.getLayoutY() - tam);
            ButtonHorarios.setLayoutY(ButtonHorarios.getLayoutY() - tam);
            ButtonMaterias.setLayoutY(ButtonMaterias.getLayoutY() - tam);
            ButtonComentarios.setLayoutY(ButtonComentarios.getLayoutY() - tam);
            t2.setRotate(0);
            t3.setLayoutY(t3.getLayoutY() - tam);
            t4.setLayoutY(t4.getLayoutY() - tam);
            t5.setLayoutY(t5.getLayoutY() - tam);
            TextGoals.setVisible(false);
        } else if (event.getSource() == ButtonLogros && !TextGoals.isVisible()) {
            l2.setLayoutY(l2.getLayoutY() + tam);
            l3.setLayoutY(l3.getLayoutY() + tam);
            l4.setLayoutY(l4.getLayoutY() + tam);
            l5.setLayoutY(l5.getLayoutY() + tam);
            ButtonHorarios.setLayoutY(ButtonHorarios.getLayoutY() + tam);
            ButtonMaterias.setLayoutY(ButtonMaterias.getLayoutY() + tam);
            ButtonComentarios.setLayoutY(ButtonComentarios.getLayoutY() + tam);
            t2.setRotate(-90);
            t3.setLayoutY(t3.getLayoutY() + tam);
            t4.setLayoutY(t4.getLayoutY() + tam);
            t5.setLayoutY(t5.getLayoutY() + tam);
            TextGoals.setVisible(true);
        }
        if (event.getSource() == ButtonHorarios && !TextInterest.isVisible()) {
            l3.setLayoutY(l3.getLayoutY() + tam);
            l4.setLayoutY(l4.getLayoutY() + tam);
            l5.setLayoutY(l5.getLayoutY() + tam);
            ButtonMaterias.setLayoutY(ButtonMaterias.getLayoutY() + tam);
            ButtonComentarios.setLayoutY(ButtonComentarios.getLayoutY() + tam);
            t3.setRotate(-90);
            t4.setLayoutY(t4.getLayoutY() + tam);
            t5.setLayoutY(t5.getLayoutY() + tam);
            TextInterest.setVisible(true);
        } else if (event.getSource() == ButtonHorarios && TextInterest.isVisible()) {
            l3.setLayoutY(l3.getLayoutY() - tam);
            l4.setLayoutY(l4.getLayoutY() - tam);
            l5.setLayoutY(l5.getLayoutY() - tam);
            ButtonMaterias.setLayoutY(ButtonMaterias.getLayoutY() - tam);
            ButtonComentarios.setLayoutY(ButtonComentarios.getLayoutY() - tam);
            t3.setRotate(0);
            t4.setLayoutY(t4.getLayoutY() - tam);
            t5.setLayoutY(t5.getLayoutY() - tam);
            TextInterest.setVisible(false);
        }
        if (event.getSource() == ButtonMaterias && !TextThemes.isVisible()) {
            l4.setLayoutY(l4.getLayoutY() + tam);
            l5.setLayoutY(l5.getLayoutY() + tam);
            ButtonComentarios.setLayoutY(ButtonComentarios.getLayoutY() + tam);
            t4.setRotate(-90);
            t5.setLayoutY(t5.getLayoutY() + tam);
            TextThemes.setVisible(true);
        } else if (event.getSource() == ButtonMaterias && TextThemes.isVisible()) {
            l4.setLayoutY(l4.getLayoutY() - tam);
            l5.setLayoutY(l5.getLayoutY() - tam);
            ButtonComentarios.setLayoutY(ButtonComentarios.getLayoutY() - tam);
            t4.setRotate(0);
            t5.setLayoutY(t5.getLayoutY() - tam);
            TextThemes.setVisible(false);
        }
        if (event.getSource() == ButtonComentarios && !listGroups.isVisible()) {
            l5.setLayoutY(l5.getLayoutY() + tam);
            t5.setRotate(-90);
            listGroups.setVisible(true);
        } else if (event.getSource() == ButtonComentarios && listGroups.isVisible()) {
            l5.setLayoutY(l5.getLayoutY() - tam);
            t5.setRotate(0);
            listGroups.setVisible(false);
        }

    }

    public void start(String u) {
        this.usu = u;
        this.NombreEstudiante.setText(u);
    }

    @FXML
    public void desplegarMenu(ActionEvent event) {
        String nomFXML = "PantallaMenu.fxml";
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource(nomFXML));
            root = loader.load();
            CompaController cc = loader.getController();
            cc.actualizar();
        } catch (Exception e) {
        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Perfil");
        stage.setScene(scene);
        stage.showAndWait();
    }

    void initialize() {
        assert ButtonComentarios != null : "fx:id=\"ButtonComentarios\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert ButtonExperiencia != null : "fx:id=\"ButtonExperiencia\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert ButtonHorarios != null : "fx:id=\"ButtonHorarios\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert ButtonLogros != null : "fx:id=\"ButtonLogros\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert ButtonMaterias != null : "fx:id=\"ButtonMaterias\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert NombreEstudiante != null : "fx:id=\"NombreEstudiante\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert TextDescription != null : "fx:id=\"TextDescription\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert TextGoals != null : "fx:id=\"TextGoals\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert TextInterest != null : "fx:id=\"TextInterest\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert TextThemes != null : "fx:id=\"TextThemes\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert l1 != null : "fx:id=\"l1\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert l2 != null : "fx:id=\"l2\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert l3 != null : "fx:id=\"l3\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert l4 != null : "fx:id=\"l4\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert l5 != null : "fx:id=\"l5\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert listGroups != null : "fx:id=\"listGroups\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert menu != null : "fx:id=\"menu\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert t1 != null : "fx:id=\"t1\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert t2 != null : "fx:id=\"t2\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert t3 != null : "fx:id=\"t3\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert t4 != null : "fx:id=\"t4\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";
        assert t5 != null : "fx:id=\"t5\" was not injected: check your FXML file 'PantallaPefilProfesor.fxml'.";

    }

}
