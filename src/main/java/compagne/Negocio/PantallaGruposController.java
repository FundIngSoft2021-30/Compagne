package compagne.Negocio;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.Node;
import compagne.Entidades.Grupo;
import compagne.Vista.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class PantallaGruposController implements Initializable {

        private FacadeCompagne facade = FacadeCompagne.getInstance();
        private String email;
        
    @FXML
    private Button crearGrupo;

    @FXML
    private TextField codigoGrupo;

    @FXML
    private Button unirme_button;

    @FXML
    private Text Agregado1;

    @FXML
    private Button unirme2_button;

    @FXML
    private Text Agregado2;

    @FXML
    private Button menu;

    @FXML
    private ListView<Grupo> listaGrupos;

        @Override
        public void initialize(URL url, ResourceBundle rb) {
                assert crearGrupo != null
                                : "fx:id=\"crearGrupo\" was not injected: check your FXML file 'RegistrarPantalla.fxml'.";
                assert unirme_button != null
                                : "fx:id=\"Unirme\" was not injected: check your FXML file 'RegistrarPantalla.fxml'.";
                assert codigoGrupo != null
                                : "fx:id=\"codigoGrupo\" was not injected: check your FXML file 'RegistrarPantalla.fxml'.";
                assert listaGrupos != null
                                : "fx:id=\"Unirme\" was not injected: check your FXML file 'RegistrarPantalla.fxml'.";
        }

        public void poblarGrupos() {
                this.listaGrupos.getItems().addAll(this.facade.listarGruposPublicos());
        }

        @FXML
        public void crearGrupo(ActionEvent event) {
                String nomFXML = "crearGrupo.fxml";
                Parent root = null;
                try {
                        FXMLLoader loader = new FXMLLoader(App.class.getResource(nomFXML));
                        root = loader.load();
                        CrearGrupoController pgc = loader.getController();
                        pgc.start(this.email);
                } catch (Exception e) {

                }
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Crear Grupo");
                stage.setScene(scene);
                stage.showAndWait();
        }

        @FXML
        public void Unirme(ActionEvent event) {
                if (this.codigoGrupo.getText().length() == 0 || this.email == null) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("No se pudo Unir");
                        alert.setContentText("Por favor revise el codigo ingresado");
                        alert.showAndWait();
                } else if (!this.facade.agregarUsuarioAGrupo(this.codigoGrupo.getText(), email, "N")) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("No se pudo unir");
                        alert.setContentText("Por favor revise el codigo ingresado");
                        alert.showAndWait();
                } else { 
                    this.codigoGrupo.setText("");
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
                }
        }

        @FXML
        public void Unirme2(ActionEvent event) {
                Grupo g = this.listaGrupos.getSelectionModel().getSelectedItem();
                String nomFXML = "PVerInfoGrupo.fxml";
                Parent root = null;
                try {
                        FXMLLoader loader = new FXMLLoader(App.class.getResource(nomFXML));
                        root = loader.load();
                        VerInfoGrupoController pgc = loader.getController();
                        pgc.start(g);
                } catch (Exception e) {

                }
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Ver info grupo");
                stage.setScene(scene);
                stage.showAndWait();
        }

        @FXML
        public void desplegarMenu(ActionEvent event) {
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
        }

        public void start(String email) {
                this.email = email;
                this.poblarGrupos();
        }
}
