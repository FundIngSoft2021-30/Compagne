/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compagne.Negocio;

import java.net.URL;
import java.util.ResourceBundle;

import compagne.Entidades.Grupo;
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
        private Button unirme_button;
        @FXML
        private TextField codigoGrupo;
        @FXML
        private ListView<Grupo> listaGrupos;
        @FXML
        private Button unirme2_button;
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
                assert unirme_button != null
                                : "fx:id=\"Unirme\" was not injected: check your FXML file 'RegistrarPantalla.fxml'.";
                assert codigoGrupo != null
                                : "fx:id=\"codigoGrupo\" was not injected: check your FXML file 'RegistrarPantalla.fxml'.";
                assert listaGrupos != null
                                : "fx:id=\"Unirme\" was not injected: check your FXML file 'RegistrarPantalla.fxml'.";
                this.poblarGrupos();
        }

        private void poblarGrupos(){
                this.listaGrupos.getItems().addAll(this.facade.listarGruposPublicos());
        }

        @FXML
        private void crearGrupo(ActionEvent event) {
                // TODO linkear a otra pantalla
        }

        @FXML
        private void Unirme(ActionEvent event) {
                if (this.codigoGrupo.getText().length() == 0 || this.email==null) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("No se pudo Unir");
                        alert.setContentText("Por favor revise el codigo ingresado");
                        alert.showAndWait();
                } else if (this.facade.agregarUsuarioAGrupo(this.codigoGrupo.getText(), email, "N")) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("No se pudo Unir");
                        alert.setContentText("Por favor revise el codigo ingresado");
                        alert.showAndWait();
                } else {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Correcto");
                        alert.setHeaderText("Se unio correctamente");
                        alert.setContentText("Enhorabuena!");
                        alert.showAndWait();
                }
        }

        @FXML
        private void Unirme2(ActionEvent event) {
        }

        @FXML
        private void desplegarMenu(ActionEvent event) {
                //TODO
        }

        private void start(String email) {
                this.email = email;
        }
}
