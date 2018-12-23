package MyDashboard;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class Controller implements Initializable {

    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private BorderPane border_pane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //makeStageDrageable();
        try {
            Parent contentarea = FXMLLoader.load(getClass().getResource("ContentArea.fxml"));
            border_pane.setCenter(contentarea);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void makeStageDrageable() {
        border_pane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        border_pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.stage.setX(event.getScreenX() - xOffset);
                Main.stage.setY(event.getScreenY() - yOffset);
                Main.stage.setOpacity(0.7f);
            }
        });
        border_pane.setOnDragDone((e) -> {
            Main.stage.setOpacity(1.0f);
        });
        border_pane.setOnMouseReleased((e) -> {
            Main.stage.setOpacity(1.0f);
        });

    }
}