package compagne.Negocio;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import compagne.Entidades.Usuario;
import compagne.Vista.App;
import javafx.stage.Stage;

public class IniciarSesionController {

    private FacadeCompagne facadeCompagne = new FacadeCompagne();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane main;

    @FXML
    private PasswordField contra_field;

    @FXML
    private TextField nombre_field;

    @FXML
    private Button registrarse;

    @FXML
    private Button registrate_button;

    @FXML
    void click(ActionEvent event) {
        Usuario usu = this.facadeCompagne.iniciarSesion(this.nombre_field.getText(), this.contra_field.getText());
        if (usu != null) {
            /*
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correcto");
            alert.setHeaderText("Ingreso correctamente");
            alert.setContentText("Enhorabuena!");
            alert.showAndWait();
            */
            String nomFXML = "PantallaMenu.fxml";
            Parent root = null;
            try {
                FXMLLoader loader = new FXMLLoader(App.class.getResource(nomFXML));
                root = loader.load();
                PantallaMenuController pmc = loader.getController();
                pmc.start(((Usuario) (usu)).getEmail());
            } catch (Exception e) {

            }
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Menu");
            stage.setScene(scene);
            stage.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Credenciales incorrectas");
            alert.setContentText("Por favor intente nuevamente");
            alert.showAndWait();
        }
    }

    @FXML
    void registro(ActionEvent event) {
        String nomFXML = "RegistrarPantalla.fxml";
        Parent root = null;

        try {
            root = FXMLLoader.load(App.class.getResource(nomFXML));
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
    void initialize() {
        assert contra_field != null
                : "fx:id=\"contra_field\" was not injected: check your FXML file 'PantallaIniciarSesion.fxml'.";
        assert nombre_field != null
                : "fx:id=\"nombre_field\" was not injected: check your FXML file 'PantallaIniciarSesion.fxml'.";
        assert registrarse != null
                : "fx:id=\"registrarse\" was not injected: check your FXML file 'PantallaIniciarSesion.fxml'.";
        assert registrate_button != null
                : "fx:id=\"registrate_button\" was not injected: check your FXML file 'PantallaIniciarSesion.fxml'.";
        assert main != null : "fx:id=\"main\" was not injected: check your FXML file 'PantallaIniciarSesion.fxml'.";

    }

}
