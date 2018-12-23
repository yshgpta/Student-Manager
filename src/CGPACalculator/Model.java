package CGPACalculator;

import java.util.*;
import javafx.scene.control.TextField;
import java.text.DecimalFormat;

public class Model {
    private DecimalFormat numberFormatter = new DecimalFormat("0.00");

    private final int GPA_UPPER_BOUND = 10;

    private HashMap<String, Double> letterGradeToNumber = new HashMap<String, Double>();

    private double gpaOverall;


    public Model() {
        letterGradeToNumber.put("A+", 10.0);
        letterGradeToNumber.put("A", 9.0);
        letterGradeToNumber.put("B+", 8.0);
        letterGradeToNumber.put("B", 7.0);
        letterGradeToNumber.put("C", 6.0);
        letterGradeToNumber.put("D", 5.0);
        letterGradeToNumber.put("F", 0.0);
    }

    public String getGPAErrorIfInvalid(String number){
        String errorText="";
        if(Double.parseDouble(number) > GPA_UPPER_BOUND){
            errorText="Error: GPA can't be > 10.0";
        }
        return errorText;
    }

    public static boolean isEmptyString(String input){
        return input.trim().equals("");
    }


    public boolean isClassValid(Class classObj){
        if(isEmptyString(classObj.credits.getText())){
            return false;
        }
        return true;
    }

    public void setQualityPoints(Class classObj){
        double qualityPoints = Integer.parseInt(classObj.credits.getText()) * letterGradeToNumber.get(classObj.grade.getValue());
        classObj.qualityPointsLabel.setText(numberFormatter.format(Controller.round(qualityPoints, 2)));
    }

    public void calcGpaOverall(ArrayList<Class> classes, TextField currentGPA, TextField currentCredits) {
        double totalQualityPoints=0;
        int totalCredits=0;
        for(Class c : classes){
            if(isClassValid(c)){
                totalQualityPoints += Double.parseDouble(c.credits.getText()) * letterGradeToNumber.get(c.grade.getValue());
                totalCredits += Integer.parseInt(c.credits.getText());
            }
        }
        if(!isEmptyString(currentGPA.getText()) && !isEmptyString(currentCredits.getText()) && getGPAErrorIfInvalid(currentGPA.getText()).equals("")){
            totalQualityPoints += Double.parseDouble(currentGPA.getText()) * Integer.parseInt(currentCredits.getText());
            totalCredits += Integer.parseInt(currentCredits.getText());
        }

        if(totalCredits==0){
            gpaOverall=0;
        }else{
            gpaOverall = totalQualityPoints / totalCredits;
        }
    }

    public double getGpaOverall(){
        return gpaOverall;
    }

    public void reset(){
        gpaOverall=0;
    }

}
