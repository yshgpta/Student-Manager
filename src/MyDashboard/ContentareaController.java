package MyDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ContentareaController implements Initializable {

    @FXML
    private VBox content_area;
    @FXML
    private HBox menubar;
    @FXML
    private JFXTextField txtField;
    @FXML
    private Label lbldate,currentDate;
    @FXML
    private ImageView imgView;
    @FXML
    private Label quotelbl,messagehead;

    Calendar calendar = Calendar.getInstance();
    int day,month,year;
    boolean flag = true;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        lbldate.setText(""+day+"/"+(month+1)+"/"+year+"");
        quotelbl.setText("\""+todaysMessage(day)+"\"");
    }

    @FXML
    private void open_sidebar(ActionEvent event) throws Exception {
        BorderPane border_pane = (BorderPane)((Node)event.getSource()).getScene().getRoot();
        if (flag == true) {
            Parent sidebar = FXMLLoader.load(getClass().getResource("Sidebar.fxml"));
            border_pane.setLeft(sidebar);
            menubar.setMaxWidth(700);
            content_area.setAlignment(Pos.TOP_CENTER);
            txtField.setMaxWidth(300);
            imgView.setImage(new Image("img/back.png"));
            lbldate.setText("");
            currentDate.setText("");
            messagehead.setText("");
            quotelbl.setText("");
            flag = false;
        } else {
            border_pane.setLeft(null);
            menubar.setMaxWidth(1000);
            txtField.setMaxWidth(580);
            imgView.setImage(new Image("img/hamburg.png"));
            lbldate.setText(""+day+"/"+(month+1)+"/"+year+"");
            currentDate.setText("CURRENT DATE");
            messagehead.setText("TODAY'S MESSAGE");
            quotelbl.setText("\""+todaysMessage(day)+"\"");
            flag = true;
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
     public String todaysMessage(int day){
        switch (day){
            case 1:
                return "Don’t downgrade your dream just to fit your reality, upgrade your conviction to match your destiny.";
            case 2:
                return "You are braver than you believe, stronger than you seem and smarter than you think.";
            case 3:
                return "You are confined only by the walls you build yourself.";
            case 4:
                return "The man who has confidence in himself gains the confidence of others";
            case 5:
                return "You attract what you are, not what you want. If you want great, then be great.";
            case 6:
                return "It’s not who you are that holds you behind, it’s who you think your are not.";
            case 7:
                return "Stop being afraid of what could go wrong and think of what could go right.";
            case 8:
                return "You should never regret anything in life. If it’s good, it’s wonderful. If it’s bad, it is experience.";
            case 9:
                return "Falling down is an accident, staying down is a choice.";
            case 10:
                return "If you have the power to make someone happy, do it. The world needs more of that.";
            case 11:
                return "Always believe that something wonderful is about to happen.";
            case 12:
                return "Don’t be afraid to give up the good and go for great.";
            case 13:
                return "Remember that life’s greatest lessons are usually learned from worst times and from the worst mistakes.";
            case 14:
                return "Don’t talk, just act. Don’t say, just show. Don’t promise, just prove.";
            case 15:
                return "Never stop doing great just because someone doesn’t give you credit.";
            case 16:
                return "Discipline is doing what needs to be done, even if you don’t want to.";
            case 17:
                return "Work while they sleep. Learn while they party. Save while they spend. Live like they dream.";
            case 18:
                return "The key to success is to focus our conscious mind on things we desire, not things we fear.";
            case 19:
                return "Never apologize for having high standards, people who really want to be in your life will rise to meet them.";
            case 20:
                return "Never give up on a dream just because of the time it will take to accomplish it, time will pass anyway.";
            case 21:
                return "Don’t fear failure. Fear being in the exact same place next year as you are today.";
            case 22:
                return "You didn’t come this far only to come this far.";
            case 23:
                return "You will never know your limits until you push yourself to them.";
            case 24:
                return "If you can’t handle stress, you won’t manage success.";
            case 25:
                return "Don’t be pushed by your problems, be led by your dreams.";
            case 26:
                return " If you don’t build your dreams, someone else will hire you to build theirs.";
            case 27:
                return "What comes easy won’t last, what lasts won’t come easy.";
            case 28:
                return "Don’t limit your challenges, challenge your limits.";
            case 29:
                return "Work until your idols become your rivals.";
            case 30:
                return "You Can Never Cross The Ocean Unless You Have The Courage To Lose Sight Of The Shore.";
            case 31:
                return "If You Want Something You Never Had, You Have To Do Something You’ve Never Done.";
            default:
                return "";
        }
     }
}

