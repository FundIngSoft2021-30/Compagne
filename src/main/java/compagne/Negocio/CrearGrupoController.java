package compagne.Negocio;

import compagne.Entidades.Usuario;
import javafx.scene.control.Alert;
import compagne.Entidades.Grupo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class CrearGrupoController {

    private FacadeCompagne facade = new FacadeCompagne();
    private String emai;
    private Grupo g;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button agregarIntegrante_btn;

    @FXML
    private CheckBox checkEstudiante;

    @FXML
    private CheckBox checkProfesor;

    @FXML
    private Button crear_btn;

    @FXML
    private TextField email;

    @FXML
    private TextField email1;

    @FXML
    private ListView<Usuario> integrantes_list;

    @FXML
    private TextField nombre;

    @FXML
    void addIntegrante(ActionEvent event) {
        if (this.email1.getText().length() > 0) {
            if (this.facade.agregarUsuarioAGrupo(this.g.getCodigo(), this.emai, "N")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Correcto");
                alert.setHeaderText("Ingreso correctamente");
                alert.setContentText("Enhorabuena!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No se pudo añadir");
                alert.setContentText("Intentelo nuevamente...");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo añadir");
            alert.setContentText("Intentelo nuevamente...");
            alert.showAndWait();
        }

    }

    @FXML
    void click(ActionEvent event) {
        if (event.getSource() == this.checkProfesor) {
            checkEstudiante.setSelected(false);
        } else if (event.getSource() == this.checkEstudiante) {
            checkProfesor.setSelected(false);
        }

    }

    public void actualizar() {
        // TODO
        // this.integrantes_list.getItems().addAll(this.facade(this.g.getCodigo()));
    }

    @FXML
    void crearGrupo(ActionEvent event) {
        if (this.nombre.getText().length() > 0 && this.email.getText().length() > 0
                && (this.checkEstudiante.isSelected() || this.checkProfesor.isSelected())) {
            String si = "N";
            if (checkEstudiante.isSelected())
                si = "S";
            this.g = this.facade.crearGrupo(this.nombre.getText(), this.email.getText(), si, this.emai);
            if (this.g != null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Correcto");
                alert.setHeaderText("Ingreso correctamente");
                alert.setContentText("Enhorabuena!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No se pudo registrar");
                alert.setContentText("Intentelo nuevamente...");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo registrar");
            alert.setContentText("Intentelo nuevamente...");
            alert.showAndWait();
        }

    }

    public void start(String email) {
        this.emai = email;
    }

    @FXML
    void initialize() {
        assert agregarIntegrante_btn != null
                : "fx:id=\"agregarIntegrante_btn\" was not injected: check your FXML file 'crearGrupo.fxml'.";
        assert checkEstudiante != null
                : "fx:id=\"checkEstudiante\" was not injected: check your FXML file 'crearGrupo.fxml'.";
        assert checkProfesor != null
                : "fx:id=\"checkProfesor\" was not injected: check your FXML file 'crearGrupo.fxml'.";
        assert crear_btn != null : "fx:id=\"crear_btn\" was not injected: check your FXML file 'crearGrupo.fxml'.";
        assert email != null : "fx:id=\"email\" was not injected: check your FXML file 'crearGrupo.fxml'.";
        assert email1 != null : "fx:id=\"email1\" was not injected: check your FXML file 'crearGrupo.fxml'.";
        assert integrantes_list != null
                : "fx:id=\"integrantes_list\" was not injected: check your FXML file 'crearGrupo.fxml'.";
        assert nombre != null : "fx:id=\"nombre\" was not injected: check your FXML file 'crearGrupo.fxml'.";

    }

}
