package CGPACalculator;

import java.net.URL;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.*;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.*;
import java.util.*;
import javafx.collections.*;
import javafx.stage.StageStyle;

import java.text.DecimalFormat;

public class Controller implements Initializable {
    private static final int DECIMAL_PRECISION = 2;		//how many decimals to round to
    private DecimalFormat numberFormatter = new DecimalFormat("0.00");

    private static final int WINDOW_WIDTH = 1000;

    private Model model = new Model();

    private ArrayList<String> gradesOptions = new ArrayList<String>();
    private ArrayList<Class> classes = new ArrayList<Class>();

    private final int INITIAL_NUMBER_OF_CLASSES = 8;
    private final int ROW_OFFSET = 1;
    private int nextFreeClassRow = ROW_OFFSET;
    @FXML private GridPane classesPane;

    @FXML private TextField currentGPA;
    @FXML private Label currentGPAError;
    @FXML private TextField currentCredits;
    @FXML private Label currentCreditsError;

    @FXML private JFXButton addClassButton;

    @FXML private Label gpaOverall;

    public Controller(){
        gradesOptions.add("A+");
        gradesOptions.add("A");
        gradesOptions.add("B+");
        gradesOptions.add("B");
        gradesOptions.add("C");
        gradesOptions.add("D");
        gradesOptions.add("F");
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        for(int row=0+ROW_OFFSET; row<INITIAL_NUMBER_OF_CLASSES+ROW_OFFSET; row++){
            Class newClass = addClass(row);
            registerEventHandlers(newClass);
        }

        currentGPA.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*(\\.\\d*)?")) {
                    currentGPA.setText(oldValue);
                }
            }
        });

        addClassButton.setFocusTraversable(false);

        currentCredits.textProperty().addListener(new PositiveIntegerTextFieldListener(currentCredits));

        currentGPA.setOnAction(e -> calcGpa());
        currentGPA.focusedProperty().addListener((observable, oldValue, newValue) -> {
            calcGpa();
        });

        currentCredits.setOnAction(e -> calcGpa());
        currentCredits.focusedProperty().addListener((observable, oldValue, newValue) -> {
            calcGpa();
        });


        Platform.runLater(() -> classes.get(0).credits.requestFocus());
    }

    private Class addClass(int row){
        nextFreeClassRow++;

        TextField title = new TextField();
        title.getStyleClass().addAll("titleColumn");

        Pane gradeContainer = new Pane();
        gradeContainer.getStyleClass().addAll("gradeColumn");

        ComboBox<String> gradeDropdown = new ComboBox<String>();
        gradeDropdown.setItems(FXCollections.observableArrayList(gradesOptions));
        gradeDropdown.setVisibleRowCount(gradesOptions.size());
        gradeDropdown.setValue(gradesOptions.get(0));
        gradeContainer.getChildren().addAll(gradeDropdown);

        TextField credits = new TextField();
        credits.getStyleClass().addAll("creditsColumn");

        Pane qualityPointsContainer = new Pane();
        qualityPointsContainer.getStyleClass().addAll("qualityPointsColumn");

        Label qualityPoints = new Label();
        qualityPointsContainer.getChildren().addAll(qualityPoints);

        JFXButton removeButton = new JFXButton("Remove");
        removeButton.getStyleClass().addAll("removeColumn");
        removeButton.setFocusTraversable(false);

        classesPane.add(title, 0, row);
        classesPane.add(gradeContainer, 1, row);
        classesPane.add(credits, 2, row);
        classesPane.add(qualityPointsContainer, 3, row);
        classesPane.add(removeButton, 4, row);

        Class newClass = new Class(row, title, gradeDropdown, credits, qualityPoints, removeButton);
        classes.add(newClass);

        return newClass;
    }

    private void registerEventHandlers(Class newClass){
        newClass.grade.setItems(FXCollections.observableArrayList(gradesOptions));
        newClass.grade.setValue(gradesOptions.get(0));

        newClass.grade.setOnAction(e -> validateAndCalculateClass(newClass));

        newClass.credits.focusedProperty().addListener(new ClassTextFieldListener(newClass));
        newClass.credits.setOnAction(e -> validateAndCalculateClass(newClass));

        newClass.credits.textProperty().addListener(new PositiveIntegerTextFieldListener(newClass.credits));

        newClass.removeButton.setOnAction(e -> removeClass(newClass.id));
    }

    private void removeClass(int row){
        Set<Node> deleteNodes = new HashSet<Node>();
        for (Node child : classesPane.getChildren()) {
            Integer rowIndex = GridPane.getRowIndex(child);


            int r = rowIndex == null ? 0 : rowIndex;

            if (r > row) {
                GridPane.setRowIndex(child, r-1);
            }
            else if (r == row) {
                deleteNodes.add(child);
            }
        }

        classesPane.getChildren().removeAll(deleteNodes);

        for(int i=row; i<classes.size(); i++){
            classes.get(i).id = classes.get(i).id -1;
        }

        classes.remove(row-1);

        nextFreeClassRow--;

        resizeWindow();

        calcGpa();
    }

    public void handleAddClassButton(ActionEvent event){
        Class newClass = addClass(nextFreeClassRow);
        registerEventHandlers(newClass);

        resizeWindow();
    }

    private void resizeWindow(){
        ((Stage)classesPane.getScene().getWindow()).sizeToScene();
        ((Stage)classesPane.getScene().getWindow()).setWidth(WINDOW_WIDTH);
    }


    public void validateAndCalculateClass(Class classObj){

        if(model.isClassValid(classObj)){
            model.setQualityPoints(classObj);
            classObj.qualityPointsLabel.getStyleClass().removeAll("errorLabel");
        }
        else{
            classObj.qualityPointsLabel.setText("Credits can't be empty");
            classObj.qualityPointsLabel.getStyleClass().addAll("errorLabel");
        }

        calcGpa();
    }

    public void calcGpa(){

        currentGPAError.setText("");
        currentCreditsError.setText("");


        if(Model.isEmptyString(currentGPA.getText()) && !Model.isEmptyString(currentCredits.getText())){
            currentGPAError.setText("Current GPA required");
        }
        else if(!Model.isEmptyString(currentGPA.getText()) && Model.isEmptyString(currentCredits.getText())){
            currentCreditsError.setText("Current Credits required");
        }
        else if(!Model.isEmptyString(currentGPA.getText())){
            currentGPAError.setText(model.getGPAErrorIfInvalid(currentGPA.getText()));
        }

        model.calcGpaOverall(classes, currentGPA, currentCredits);
        gpaOverall.setText(numberFormatter.format(roundDown(model.getGpaOverall(), DECIMAL_PRECISION)));
    }

    @FXML
    public void handleResetButton(ActionEvent event){
        model.reset();

        for(Class c : classes){
            c.title.setText("");
            c.grade.setValue(gradesOptions.get(0));
            c.credits.setText("");
            c.qualityPointsLabel.setText("");
        }

        currentGPA.setText("");
        currentGPAError.setText("");

        currentCredits.setText("");
        currentCreditsError.setText("");

        gpaOverall.setText("0.00");

        if(!classes.isEmpty()){
            classes.get(0).credits.requestFocus();
        }
    }


    class ClassTextFieldListener implements ChangeListener<Boolean> {
        private Class classObj;

        public ClassTextFieldListener(Class classObj) {
            this.classObj=classObj;
        }

        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if(!newValue){
                validateAndCalculateClass(classObj);
            }
        }
    }

    class PositiveIntegerTextFieldListener implements ChangeListener<String> {
        private TextField textField;

        public PositiveIntegerTextFieldListener(TextField textField){
            this.textField = textField;
        }

        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("\\D", ""));
            }
            if(newValue.matches("0+")){
                textField.setText(oldValue);
            }
        }
    }


    public static String numToText(double number){
        return numToText(number + "");
    }

    public static String numToText(String number){
        return number;
    }

    public static double round(double number, int decimals){
        double powerOfTen= Math.pow(10, decimals);
        return Math.round(number * powerOfTen ) / powerOfTen;
    }

    public static double roundDown(double number, int decimals){
        double powerOfTen= Math.pow(10, decimals);
        return Math.floor(number * powerOfTen) / powerOfTen;
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
