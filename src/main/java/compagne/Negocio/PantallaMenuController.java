package compagne.Negocio;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import compagne.Vista.App;
import javafx.scene.Node;

public class PantallaMenuController {

    private String email;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cerrar_boton;

    @FXML
    private Button companeros_boton;

    @FXML
    private Button grupos_boton;

    @FXML
    private Button mensajes_boton;

    @FXML
    private Button miPerfil_boton;

    @FXML
    void cambiarACompanieros(ActionEvent event) {
        String nomFXML ="PantallaVerCompaneros.fxml";
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource(nomFXML));
            root = loader.load();
            CompaController cc=loader.getController();
            cc.actualizar();
        } catch (Exception e) {
        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Registrarse");
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    void cambiarAGrupos(ActionEvent event) {
        String nomFXML = "PantallaGrupos.fxml";
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource(nomFXML));
            root = loader.load();
            PantallaGruposController pgc = loader.getController();
            pgc.start(this.email);
        } catch (Exception e) {

        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Registrarse");
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    void cambiarAMensajes(ActionEvent event) {
        // TODO Mensajes
    }

    @FXML
    void cambiarAMiPerfil(ActionEvent event) {
        String nomFXML = "PantallaPerfil.fxml";
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource(nomFXML));
            root = loader.load();
            PantallaPerfilController pfc = loader.getController();
            pfc.start(this.email);
        } catch (Exception e) {

        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Registrarse");
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    void cerrarSesion(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void start(String usu) {
        this.email = usu;
    }

    @FXML
    void initialize() {
        assert cerrar_boton != null
                : "fx:id=\"cerrar_boton\" was not injected: check your FXML file 'PantallaMenu.fxml'.";
        assert companeros_boton != null
                : "fx:id=\"companeros_boton\" was not injected: check your FXML file 'PantallaMenu.fxml'.";
        assert grupos_boton != null
                : "fx:id=\"grupos_boton\" was not injected: check your FXML file 'PantallaMenu.fxml'.";
        assert mensajes_boton != null
                : "fx:id=\"mensajes_boton\" was not injected: check your FXML file 'PantallaMenu.fxml'.";
        assert miPerfil_boton != null
                : "fx:id=\"miPerfil_boton\" was not injected: check your FXML file 'PantallaMenu.fxml'.";

    }

}
