package compagne.Negocio;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class IniciarSesionController {

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

    }

    @FXML
    void registro(ActionEvent event) {
        String nomFXML = "RegistrarPantalla.fxml";
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource(nomFXML));
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
