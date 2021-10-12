package compagne.Negocio;

import java.net.URL;
import java.util.ResourceBundle;
import compagne.Entidades.Usuario;
import java.util.Collection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CompaController {
    private FacadeCompagne facadeCompagne = new FacadeCompagne();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn;

    @FXML
    private TextField buscar_field;

    @FXML
    private ListView<Usuario> lista_compa;
    
    @FXML
    private Button menu;

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
    @FXML
        public void desplegarMenu(ActionEvent event) {
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
    }

    @FXML
    void initialize() {
        assert btn != null : "fx:id=\"btn\" was not injected: check your FXML file 'PantallaVerCompaneros.fxml'.";
        assert buscar_field != null
                : "fx:id=\"buscar_field\" was not injected: check your FXML file 'PantallaVerCompaneros.fxml'.";
        assert lista_compa != null
                : "fx:id=\"lista_compa\" was not injected: check your FXML file 'PantallaVerCompaneros.fxml'.";
    }

}
