package AttendencePercentage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("AttendencePercentage.fxml"));
        Scene scene = new Scene(root,1000,700);
        scene.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm());
        primaryStage.setTitle("Attendence Percentage Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
