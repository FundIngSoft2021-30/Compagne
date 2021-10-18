package compagne.Negocio;

import javafx.scene.control.Alert;
import compagne.Entidades.Grupo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import compagne.Entidades.Usuario;
import compagne.Vista.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class VerInfoGrupoController {

    private FacadeCompagne facade = new FacadeCompagne();
    private Grupo g;

    @FXML
    private ListView<Usuario> integrantes_list;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button agregar_button;

    @FXML
    private TextField codigo;

    @FXML
    private TextField email1;

    @FXML
    private TextField nombre;

    @FXML
    private TextField pubPriv;

    @FXML
    private Button menu;

    @FXML
    void addIntegrante(ActionEvent event) {
        if (this.email1.getText().length() > 0) {
            if (this.facade.agregarUsuarioAGrupo(this.g.getCodigo(), this.email1.getText(), "N")) {
                this.email1.setText("");
                    String nomFXML = "PantallaExito.fxml";
                    Parent root = null;
                    try {
                            FXMLLoader loader = new FXMLLoader(App.class.getResource(nomFXML));
                            root = loader.load();
                            PantallaExitoController pec = loader.getController();
                            pec.start();
                    } catch (Exception e) {

                    }
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setTitle("Exito");
                    stage.setScene(scene);
                    stage.showAndWait();
                this.actualizar();
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
    void initialize() {
        assert agregar_button != null
                : "fx:id=\"agregar_button\" was not injected: check your FXML file 'PVerInfoGrupo.fxml'.";
        assert codigo != null : "fx:id=\"codigo\" was not injected: check your FXML file 'PVerInfoGrupo.fxml'.";
        assert email1 != null : "fx:id=\"email1\" was not injected: check your FXML file 'PVerInfoGrupo.fxml'.";
        assert nombre != null : "fx:id=\"nombre\" was not injected: check your FXML file 'PVerInfoGrupo.fxml'.";
        assert pubPriv != null : "fx:id=\"pubPriv\" was not injected: check your FXML file 'PVerInfoGrupo.fxml'.";

    }

    public void actualizar(){
        this.integrantes_list.getItems().clear();
        this.integrantes_list.getItems().addAll(this.facade.listarCompasGrupo(this.g.getCodigo()));
    }

    public void start(Grupo g) {
        this.g = g;
        this.nombre.setText(g.getNombre());
        this.codigo.setText(g.getCodigo());
        this.pubPriv.setText((g.isPublico().equals("S")?"Publico":"Privado"));
        this.actualizar();
    }
    
    @FXML
    void desplegarMenu(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
