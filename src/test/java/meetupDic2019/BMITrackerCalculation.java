package meetupDic2019;

import bmiTrackerPage.bmiFormula.BMICalculationFormula;
import meetupDic2019.init.InitTests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class BMITrackerCalculation extends InitTests {

    public BMITrackerCalculation(String browser){
        super(browser);
    }

    @Test(groups = {"BMITrackerCalculation"}, description = "Check if the application is able to calculate a BMI.")
    public void BMITrackerCalculationTest() throws InterruptedException {
        String weight = "45";
        String height = "123";
        bmiTrackerActions.calculateBMI(weight,height);
        Thread.sleep(10000);
        String result = BMICalculationFormula.bmiResultCalculation(weight,height);
        System.out.println("Expected result: " + result);
        softAssert.assertAll();

    }
}
