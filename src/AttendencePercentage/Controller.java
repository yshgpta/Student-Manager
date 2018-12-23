package AttendencePercentage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TextField tc1,tc2,tc3,tc4,tc5,tc6,tc7;
    @FXML
    private TextField a1,a2,a3,a4,a5,a6,a7;
    @FXML
    private Label p1,p2,p3,p4,p5,p6,p7;
    @FXML
    private Label r1,r2,r3,r4,r5,r6,r7;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onButtonClick(ActionEvent event){
        checkAndDisplay(tc1,a1,p1,r1);
        checkAndDisplay(tc2,a2,p2,r2);
        checkAndDisplay(tc3,a3,p3,r3);
        checkAndDisplay(tc4,a4,p4,r4);
        checkAndDisplay(tc5,a5,p5,r5);
        checkAndDisplay(tc6,a6,p6,r6);
        checkAndDisplay(tc7,a7,p7,r7);
    }

    public void checkAndDisplay(TextField tc,TextField a,Label p,Label r){
        if(emptyChecker(tc,a)==-1){
            if(validation(tc,a)==-1){
                p.setText("");
                r.setText("Please Enter Valid Data");
            }else{
                r.setText("");
                p.setText(""+validation(tc,a)+"%");
            }
        }else{
            r.setText("");
            p.setText("");
        }
    }

    public int emptyChecker(TextField tc,TextField a){
        if(tc.getText().equals("") && a.getText().equals("")){
            return 1;
        }else{
            return -1;
        }
    }

    public double validation(TextField tc,TextField a){
        try{
            if(!tc.getText().equals("") && !a.getText().equals("")){
                if(Integer.parseInt(tc.getText())>=Integer.parseInt(a.getText())){
                    return calculateDaysPresents(Integer.parseInt(tc.getText()),Integer.parseInt(a.getText()));
                }
                else{
                    return -1;
                }
            }else{
                return -1;
            }
        }catch (Exception e){
            return -1;
        }
    }

    public double calculateDaysPresents(double totalClasses,double absents){
        double daysPresent = totalClasses-absents;
        return calculatePercentage(totalClasses,daysPresent);
    }

    public double calculatePercentage(double totalClasses,double daysPresent){
        double percentage = (daysPresent/totalClasses)*100;
        return percentage;
    }

    public void initiate(TextField tc,TextField a,Label p,Label r){
        tc.setText("");
        a.setText("");
        p.setText("");
        r.setText("");
    }
    public void refresh(ActionEvent event){
        initiate(tc1,a1,p1,r1);
        initiate(tc2,a2,p2,r2);
        initiate(tc3,a3,p3,r3);
        initiate(tc4,a4,p4,r4);
        initiate(tc5,a5,p5,r5);
        initiate(tc6,a6,p6,r6);
        initiate(tc7,a7,p7,r7);
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
