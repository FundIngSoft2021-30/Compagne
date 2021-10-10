package compagne.Negocio;

import java.net.URL;
import java.util.ResourceBundle;

import compagne.Entidades.Comentario;
import compagne.Entidades.Estudiante;
import compagne.Entidades.Profesor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import compagne.Entidades.Usuario;
import compagne.Vista.NotYourApp;

public class VerInfoController {

    private FacadeCompagne facadeCompagne;

    private Usuario usuario;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cambiar_btn;

    @FXML
    private ListView<Comentario> comentarios_list;

    @FXML
    private Text email_text;

    @FXML
    private ListView<String> materias_list;

    @FXML
    private Text nombre_text;

    @FXML
    private PasswordField nueva_password_field;

    @FXML
    void cambiarContrasenia(ActionEvent event) {
        if (this.nueva_password_field.getText().length() > 0
                && !this.nueva_password_field.getText().equals(this.usuario.getContrasenia())) {
            if (this.usuario instanceof Estudiante)
                this.facadeCompagne.modificarPerfilEstudiante(this.usuario.getNombre(), this.usuario.getEmail(),
                        this.nueva_password_field.getText());
            else if (this.usuario instanceof Profesor)
                this.facadeCompagne.modificarPerfilProfesor(usuario.getNombre(),
                        ((Profesor) this.usuario).getExperiencia(), this.usuario.getEmail(),
                        this.nueva_password_field.getText());

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo hacer el cambio");
            alert.setContentText("Revise nuevamente la contraseña");
            alert.showAndWait();
        }
    }

    @FXML
    void initialize() {
        this.start(NotYourApp.getUsuario());
        assert cambiar_btn != null
                : "fx:id=\"cambiar_btn\" was not injected: check your FXML file 'VerInfoUsuario.fxml'.";
        assert comentarios_list != null
                : "fx:id=\"comentarios_list\" was not injected: check your FXML file 'VerInfoUsuario.fxml'.";
        assert email_text != null
                : "fx:id=\"email_text\" was not injected: check your FXML file 'VerInfoUsuario.fxml'.";
        assert materias_list != null
                : "fx:id=\"materias_list\" was not injected: check your FXML file 'VerInfoUsuario.fxml'.";
        assert nombre_text != null
                : "fx:id=\"nombre_text\" was not injected: check your FXML file 'VerInfoUsuario.fxml'.";
        assert nueva_password_field != null
                : "fx:id=\"nueva_password_field\" was not injected: check your FXML file 'VerInfoUsuario.fxml'.";
    }

    public void start(Usuario usuario) {
        this.usuario = usuario;
        this.facadeCompagne = new FacadeCompagne();
        this.nombre_text.setText(usuario.getNombre());
        this.email_text.setText(usuario.getEmail());
        this.comentarios_list.getItems().addAll(usuario.getComentarios());
        this.materias_list.getItems().addAll(usuario.getMaterias());
    }
}
