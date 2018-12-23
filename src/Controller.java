import MyDashboard.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void launchDashboard(ActionEvent event) throws Exception {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("MyDashboard/Dashboard.fxml"));
        Scene scene = new Scene(root,1000,700);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void launchTimetable(ActionEvent event) throws Exception {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Timetable/Timetable.fxml"));
        Scene scene = new Scene(root,1000,700);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void launchToDoList(ActionEvent event) throws Exception {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ToDoList/ToDoList.fxml"));
        Scene scene = new Scene(root,1000,700);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void launchCalculator(ActionEvent event) throws Exception {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("CGPACalculator/CGPA.fxml"));
        Scene scene = new Scene(root,1000,700);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        scene.getStylesheets().add(getClass().getResource("CGPACalculator/css/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void launchAttendence(ActionEvent event) throws Exception {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AttendencePercentage/AttendencePercentage.fxml"));
        Scene scene = new Scene(root,1000,700);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        scene.getStylesheets().add(getClass().getResource("AttendencePercentage/css/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void close(ActionEvent event){
        System.exit(0);
    }
}
