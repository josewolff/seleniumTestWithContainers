package bmiTrackerPage.bmiFormula;

import java.text.DecimalFormat;

public class BMICalculationFormula {

    public static String bmiResultCalculation(String weight, String height){
        double weghtVal = Double.parseDouble(weight);
        double heightVal = Double.parseDouble(height) / 100.0;
        double result =  weghtVal / (heightVal * heightVal);
        DecimalFormat format = new DecimalFormat("#.##");
        String resultString = format.format(result);
        return  resultString;
    }
}
