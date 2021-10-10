/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compagne.Negocio;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class PantallaGruposController implements Initializable {

    private static IFacadeCompagne facade = FacadeCompagne.getInstance();
    
    @FXML
    private Button crearGrupo;
    @FXML
    private Button Unirme;
    @FXML
    private TextField codigoGrupo;
    @FXML
    private ListView<String> listaGrupos;
    @FXML
    private Button Unirme2;
    @FXML
    private Text Agregado1;
    @FXML
    private Text Agregado2;
    @FXML
    private Button menu;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        assert crearGrupo != null
                : "fx:id=\"crearGrupo\" was not injected: check your FXML file 'RegistrarPantalla.fxml'.";
        assert Unirme != null
                : "fx:id=\"Unirme\" was not injected: check your FXML file 'RegistrarPantalla.fxml'.";
        assert codigoGrupo != null
                : "fx:id=\"codigoGrupo\" was not injected: check your FXML file 'RegistrarPantalla.fxml'.";
        assert listaGrupos != null
                : "fx:id=\"Unirme\" was not injected: check your FXML file 'RegistrarPantalla.fxml'.";
    }    

    @FXML
    private void crearGrupo(MouseEvent event) {
        //if(crearGrupo.is)
    }

    @FXML
    private void Unirme(MouseEvent event) {
    }

    @FXML
    private void Unirme2(MouseEvent event) {
    }

    @FXML
    private void desplegarMenu(MouseEvent event) {
    }
    
}
