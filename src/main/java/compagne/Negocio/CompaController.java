package compagne.Negocio;

import compagne.Entidades.Estudiante;
import java.net.URL;
import java.util.ResourceBundle;
import compagne.Entidades.Usuario;
import compagne.Vista.App;

import java.util.Collection;
import java.util.Iterator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
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
    
    @FXML
    private Button VerC;
    
    @FXML
    private TextField Compa3;

    @FXML
    private Button VerC3;

    @FXML
    private TextField Compa1;

    @FXML
    private Button VerC1;

    @FXML
    private TextField Compa2;

    @FXML
    private Button VerC2;

    @FXML
    void Ver(ActionEvent event) {
        Estudiante u = (Estudiante) this.lista_compa.getSelectionModel().getSelectedItem();
                String nomFXML = "VerPerfil.fxml";
                Parent root = null;
                try {
                        FXMLLoader loader = new FXMLLoader(App.class.getResource(nomFXML));
                        root = loader.load();
                        VerPerfilController vpc = loader.getController();
                        vpc.start(u);
                } catch (Exception e) {

                }
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Ver Perfil");
                stage.setScene(scene);
                stage.showAndWait();
    }

    public void actualizar() {
        this.lista_compa.getItems().clear();
        Collection<Usuario> col = this.facadeCompagne.listarCompas();
        for (Usuario e : col)
            this.lista_compa.getItems().add(e);
        
        Iterator<Usuario> it = col.iterator();
        
        Compa1.setText(it.next().getNombre());
        Compa2.setText(it.next().getNombre());
        Compa3.setText(it.next().getNombre());

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
