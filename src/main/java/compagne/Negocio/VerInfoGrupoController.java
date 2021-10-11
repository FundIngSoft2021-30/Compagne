package compagne.Negocio;

import compagne.Entidades.Grupo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class VerInfoGrupoController {

    private Grupo g;

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
    void addIntegrante(ActionEvent event) {
        //TODO
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
        //TODO
    }

    public void start(Grupo g) {
        this.g = g;
        this.nombre.setText(g.getNombre());
        this.codigo.setText(g.getCodigo());
        this.pubPriv.setText((g.isPublico().equals("S")?"Publico":"Privado"));
    }

}
