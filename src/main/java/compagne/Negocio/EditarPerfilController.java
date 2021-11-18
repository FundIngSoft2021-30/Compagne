package compagne.Negocio;

import compagne.Entidades.Estudiante;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class EditarPerfilController {
    private FacadeCompagne facade = FacadeCompagne.getInstance();
    private String usu;

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
    private ListView<String> listGroups;

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
    private Button Guardar;

    @FXML
    private TextField TextGoals;

    @FXML
    private TextField TextThemes;

    @FXML
    private TextField TextInterest;

    @FXML
    private Button menu;

    @FXML
    void Save(ActionEvent event) {
        String desc = TextDescription.getText();
        String logros = TextGoals.getText();
        String intereses = TextInterest.getText();
        String materias = TextThemes.getText();
        
        String[] log = logros.split(",");
        String[] inte = intereses.split(",");
        String[] mat = materias.split(",");
        
        HashSet<String> setLog = new HashSet<>(Arrays.asList(log));
        HashSet<String> setInt = new HashSet<>(Arrays.asList(inte));
        HashSet<String> setMat = new HashSet<>(Arrays.asList(mat));
        
        Estudiante user = (Estudiante) facade.informacionUsuario(usu);
        
        facade.modificarPerfilEstudiante(user.getNombre(),usu,user.getContrasenia(),user.getComentarios(),setInt, setLog, setMat);
       
        
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

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
        } else if (event.getSource() == ButtonGroups && listGroups.isVisible()) {
            l5.setLayoutY(l5.getLayoutY() - tam);
            t5.setRotate(0);
            listGroups.setVisible(false);
        }
    }

    @FXML
    void desplegarMenu(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
    public void start(String u) {
        
        this.usu = u;
        Estudiante user = (Estudiante) facade.informacionUsuario(u);
        this.NombreEstudiante.setText(user.getNombre());
        TextDescription.setText("Descripción");
        
        String intereses = "";
        String logros = "";
        String materias = "";
        
        for(String i: user.getIntereses()){
            intereses = i + "," + intereses;
        }
        for(String i: user.getLogros()){
            logros = i + "," + logros;
        }
        for(String i: user.getMaterias()){
            materias = i + "," + materias;
        }
        
        TextGoals.setText(logros);
        TextInterest.setText(intereses);
        TextThemes.setText(materias);  
        
    }

}
