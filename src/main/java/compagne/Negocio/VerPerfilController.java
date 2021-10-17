/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compagne.Negocio;

import compagne.Entidades.Comentario;
import compagne.Entidades.Estudiante;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class VerPerfilController implements Initializable {

    private FacadeCompagne facade = FacadeCompagne.getInstance();
    private Estudiante u;
    
    @FXML
    private ListView<String> TextThemes;

    @FXML
    private ListView<String> TextGoals;

    @FXML
    private ListView<String> TextInterest;

    @FXML
    private Button ButtonDescription;

    @FXML
    private TextField TextDescription;

    @FXML
    private Line l1;

    @FXML
    private Button ButtonGoals;

    @FXML
    private Line l2;

    @FXML
    private Button ButtonThemes;

    @FXML
    private Line l3;

    @FXML
    private Button ButtonInterest;

    @FXML
    private Polygon t1;

    @FXML
    private Polygon t2;

    @FXML
    private Polygon t3;

    @FXML
    private Polygon t4;

    @FXML
    private ListView<Comentario> listGroups;

    @FXML
    private TextField NombreEstudiante;

    @FXML
    private Line l4;

    @FXML
    private Button ButtonGroups;

    @FXML
    private Polygon t5;

    @FXML
    private Line l5;

    @FXML
    private Button menu;
    
    @FXML
    private Button EnviarCom;
    
     @FXML
    private TextField Comentario;
    
    @FXML
    private TextField Calificacion;

    @FXML
    private Label LComent;

    @FXML
    private Label LCali;

    @FXML
    void click(ActionEvent event) {
        int tam = 62;
        if (event.getSource() == ButtonDescription && TextDescription.isVisible()) {
            l1.setLayoutY(l1.getLayoutY() - tam);
            l2.setLayoutY(l2.getLayoutY() - tam);
            l3.setLayoutY(l3.getLayoutY() - tam);
            l4.setLayoutY(l4.getLayoutY() - tam);
            l5.setLayoutY(l5.getLayoutY() - tam);
            ButtonGoals.setLayoutY(ButtonGoals.getLayoutY() - tam);
            ButtonInterest.setLayoutY(ButtonInterest.getLayoutY() - tam);
            ButtonThemes.setLayoutY(ButtonThemes.getLayoutY() - tam);
            ButtonGroups.setLayoutY(ButtonGroups.getLayoutY() - tam);
            t1.setRotate(0);
            t2.setLayoutY(t2.getLayoutY() - tam);
            t3.setLayoutY(t3.getLayoutY() - tam);
            t4.setLayoutY(t4.getLayoutY() - tam);
            t5.setLayoutY(t5.getLayoutY() - tam);
            TextDescription.setVisible(false);
        } else if (event.getSource() == ButtonDescription && !TextDescription.isVisible()) {
            l1.setLayoutY(l1.getLayoutY() + tam);
            l2.setLayoutY(l2.getLayoutY() + tam);
            l3.setLayoutY(l3.getLayoutY() + tam);
            l4.setLayoutY(l4.getLayoutY() + tam);
            l5.setLayoutY(l5.getLayoutY() + tam);
            ButtonGoals.setLayoutY(ButtonGoals.getLayoutY() + tam);
            ButtonInterest.setLayoutY(ButtonInterest.getLayoutY() + tam);
            ButtonThemes.setLayoutY(ButtonThemes.getLayoutY() + tam);
            ButtonGroups.setLayoutY(ButtonGroups.getLayoutY() + tam);
            t1.setRotate(-90);
            t2.setLayoutY(t2.getLayoutY() + tam);
            t3.setLayoutY(t3.getLayoutY() + tam);
            t4.setLayoutY(t4.getLayoutY() + tam);
            t5.setLayoutY(t5.getLayoutY() + tam);
            TextDescription.setVisible(true);
        }
        if (event.getSource() == ButtonGoals && TextGoals.isVisible()) {
            l2.setLayoutY(l2.getLayoutY() - tam);
            l3.setLayoutY(l3.getLayoutY() - tam);
            l4.setLayoutY(l4.getLayoutY() - tam);
            l5.setLayoutY(l5.getLayoutY() - tam);
            ButtonInterest.setLayoutY(ButtonInterest.getLayoutY() - tam);
            ButtonThemes.setLayoutY(ButtonThemes.getLayoutY() - tam);
            ButtonGroups.setLayoutY(ButtonGroups.getLayoutY() - tam);
            t2.setRotate(0);
            t3.setLayoutY(t3.getLayoutY() - tam);
            t4.setLayoutY(t4.getLayoutY() - tam);
            t5.setLayoutY(t5.getLayoutY() - tam);
            TextGoals.setVisible(false);
        } else if (event.getSource() == ButtonGoals && !TextGoals.isVisible()) {
            l2.setLayoutY(l2.getLayoutY() + tam);
            l3.setLayoutY(l3.getLayoutY() + tam);
            l4.setLayoutY(l4.getLayoutY() + tam);
            l5.setLayoutY(l5.getLayoutY() + tam);
            ButtonInterest.setLayoutY(ButtonInterest.getLayoutY() + tam);
            ButtonThemes.setLayoutY(ButtonThemes.getLayoutY() + tam);
            ButtonGroups.setLayoutY(ButtonGroups.getLayoutY() + tam);
            t2.setRotate(-90);
            t3.setLayoutY(t3.getLayoutY() + tam);
            t4.setLayoutY(t4.getLayoutY() + tam);
            t5.setLayoutY(t5.getLayoutY() + tam);
            TextGoals.setVisible(true);
        }
        if (event.getSource() == ButtonInterest && !TextInterest.isVisible()) {
            l3.setLayoutY(l3.getLayoutY() + tam);
            l4.setLayoutY(l4.getLayoutY() + tam);
            l5.setLayoutY(l5.getLayoutY() + tam);
            ButtonThemes.setLayoutY(ButtonThemes.getLayoutY() + tam);
            ButtonGroups.setLayoutY(ButtonGroups.getLayoutY() + tam);
            t3.setRotate(-90);
            t4.setLayoutY(t4.getLayoutY() + tam);
            t5.setLayoutY(t5.getLayoutY() + tam);
            TextInterest.setVisible(true);
        } else if (event.getSource() == ButtonInterest && TextInterest.isVisible()) {
            l3.setLayoutY(l3.getLayoutY() - tam);
            l4.setLayoutY(l4.getLayoutY() - tam);
            l5.setLayoutY(l5.getLayoutY() - tam);
            ButtonThemes.setLayoutY(ButtonThemes.getLayoutY() - tam);
            ButtonGroups.setLayoutY(ButtonGroups.getLayoutY() - tam);
            t3.setRotate(0);
            t4.setLayoutY(t4.getLayoutY() - tam);
            t5.setLayoutY(t5.getLayoutY() - tam);
            TextInterest.setVisible(false);
        }
        if (event.getSource() == ButtonThemes && !TextThemes.isVisible()) {
            l4.setLayoutY(l4.getLayoutY() + tam);
            l5.setLayoutY(l5.getLayoutY() + tam);
            ButtonGroups.setLayoutY(ButtonGroups.getLayoutY() + tam);
            t4.setRotate(-90);
            t5.setLayoutY(t5.getLayoutY() + tam);
            TextThemes.setVisible(true);
        } else if (event.getSource() == ButtonThemes && TextThemes.isVisible()) {
            l4.setLayoutY(l4.getLayoutY() - tam);
            l5.setLayoutY(l5.getLayoutY() - tam);
            ButtonGroups.setLayoutY(ButtonGroups.getLayoutY() - tam);
            t4.setRotate(0);
            t5.setLayoutY(t5.getLayoutY() - tam);
            TextThemes.setVisible(false);
        }
        if (event.getSource() == ButtonGroups && !listGroups.isVisible()) {
            l5.setLayoutY(l5.getLayoutY() + tam);
            t5.setRotate(-90);
            listGroups.setVisible(true);
            Calificacion.setVisible(true);
            Comentario.setVisible(true);
            EnviarCom.setVisible(true);
            LCali.setVisible(true);
            LComent.setVisible(true);
        } else if (event.getSource() == ButtonGroups && listGroups.isVisible()) {
            l5.setLayoutY(l5.getLayoutY() - tam);
            t5.setRotate(0);
            listGroups.setVisible(false);
            Calificacion.setVisible(false);
            Comentario.setVisible(false);
            EnviarCom.setVisible(false);
            LCali.setVisible(false);
            LComent.setVisible(false);
        }
    }
    
    public void actualizar(){
        this.listGroups.getItems().clear();
        this.u = (Estudiante) facade.informacionUsuario(u.getEmail());
        this.listGroups.getItems().addAll(u.getComentarios());
    }

    public void start(Estudiante usu) {
        this.u = usu;
        this.NombreEstudiante.setText(u.getNombre());
        TextDescription.setText(u.getNombre());
        TextGoals.setItems(FXCollections.observableArrayList(u.getLogros()));
        TextInterest.setItems(FXCollections.observableArrayList(u.getIntereses()));
        TextThemes.setItems(FXCollections.observableArrayList(u.getMaterias()));
        this.actualizar();
    }

    @FXML
    void desplegarMenu(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    void enviar(ActionEvent event) {
        Comentario com = new Comentario(this.Calificacion.getText(),this.Comentario.getText());
        facade.calificarUsuario(com, u);
        this.actualizar();
        this.Calificacion.setText("");
        this.Comentario.setText("");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
