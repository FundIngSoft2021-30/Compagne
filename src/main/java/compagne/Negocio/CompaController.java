package compagne.Negocio;

import java.net.URL;
import java.util.ResourceBundle;
import compagne.Entidades.Usuario;
import compagne.Vista.App;
import java.util.Collection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CompaController {
    private FacadeCompagne facadeCompagne = new FacadeCompagne();

    
    @FXML
    private Button btn;
    
    @FXML
    private TextField buscar_field;
    
    @FXML
    private ListView<Usuario> lista_compa;
    @FXML
    private ImageView menu;

    public void actualizar() {
        this.lista_compa.getItems().clear();
        Collection<Usuario> col = this.facadeCompagne.listarCompas();
        for (Usuario e : col)
            this.lista_compa.getItems().add(e);
    }

    @FXML
    void buscarC(ActionEvent event) {
        if (this.buscar_field.getText().length() > 0) {
            this.lista_compa.getItems().clear();
            Collection<Usuario> col = this.facadeCompagne.listarCompas(buscar_field.getText());
            for (Usuario e : col)
            this.lista_compa.getItems().add(e);
        } else
            this.actualizar();
    }


    void initialize() {
        assert btn != null : "fx:id=\"btn\" was not injected: check your FXML file 'PantallaVerCompaneros.fxml'.";
        assert buscar_field != null
                : "fx:id=\"buscar_field\" was not injected: check your FXML file 'PantallaVerCompaneros.fxml'.";
        assert lista_compa != null
                : "fx:id=\"lista_compa\" was not injected: check your FXML file 'PantallaVerCompaneros.fxml'.";
    }

    @FXML
    private void verMenu(MouseEvent event) {
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
        stage.setTitle("Compañeros");
        stage.setScene(scene);
        stage.showAndWait();
    }

}
