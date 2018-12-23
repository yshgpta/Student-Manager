package Timetable;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private JFXComboBox<String> comboBox;

    @FXML
    private Label semlabel;

    @FXML
    private ImageView imgView;


    ObservableList<String> list = FXCollections.observableArrayList("Semester 1","Semester 2","Semester 3","Semester 4",
            "Semester 5","Semester 6","Semester 7");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBox.setItems(list);
    }

    public void comboChange(ActionEvent event){
        semlabel.setText(comboBox.getValue());
        String value = comboBox.getValue();
        switch (value){
            case "Semester 1":
                imgView.setImage(new Image("img/semester/sem1.png"));
                break;
            case "Semester 2":
                imgView.setImage(new Image("img/semester/sem2.png"));
                break;
            case "Semester 3":
                imgView.setImage(new Image("img/semester/sem3.png"));
                break;
            case "Semester 4":
                imgView.setImage(new Image("img/semester/sem4.png"));
                break;
            case "Semester 5":
                imgView.setImage(new Image("img/semester/sem5.png"));
                break;
            case "Semester 6":
                imgView.setImage(new Image("img/semester/sem6.png"));
                break;
            case "Semester 7":
                imgView.setImage(new Image("img/semester/sem7.png"));
                break;
            default:
                break;
        }
    }

    public void launchHome(ActionEvent event) throws Exception {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../MainApp.fxml"));
        Scene scene = new Scene(root,1000,700);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
