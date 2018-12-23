package ToDoList;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button addEvent;
    @FXML
    private JFXTextField descriptionText;
    @FXML
    private ListView<LocalEvent> eventList;
    @FXML
    private DatePicker datePicker;

    ObservableList<LocalEvent> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        datePicker.setValue(LocalDate.now());
    }

    public void addEvent(ActionEvent event){
        if(descriptionText.getText().equals(""))
            return;
        list.add(new LocalEvent(descriptionText.getText(),datePicker.getValue()));
        eventList.setItems(list);
        refresh();
    }
    public void refresh(){
        datePicker.setValue(LocalDate.now());
        descriptionText.setText("");
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
