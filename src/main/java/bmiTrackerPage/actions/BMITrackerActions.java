package bmiTrackerPage.actions;

import bmiTrackerPage.bmiFormula.BMICalculationFormula;
import bmiTrackerPage.pageObject.BMITrackerElements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import utils.SeleniumTestUtils;
import utils.Waits;

public class BMITrackerActions extends BMITrackerElements {

    private Waits waits;
    private SoftAssert softAssert;
    WebDriver driver;
    public BMITrackerActions(WebDriver driver, WebDriverWait wait, SoftAssert softAssert) {
        super(driver);
        this.driver = driver;
        waits = new Waits(wait);
        this.softAssert = softAssert;
    }

    public void calculateBMI(String weight, String height) throws InterruptedException {
       sendKeyHeight(height);
       sendKeyWeight(weight);
       clickCalculateBMIButton();
       String currentResult = getBMIResult();
       String expectedResult = BMICalculationFormula.bmiResultCalculation(weight,height);
       softAssert.assertEquals(currentResult,expectedResult);
    }


    private void sendKeyWeight(String weight) throws InterruptedException {
        weightElement = waits.waitUntilVisibilityOfElement(weightElement);
        if (weightElement != null) {
            SeleniumTestUtils.highlight(driver,weightElement);
            weightElement.sendKeys(weight);
        }else{
            softAssert.fail("The weightElement input is not visible on the page.");
        }

    }

    private void sendKeyHeight(String height) throws InterruptedException {
        heightElement = waits.waitUntilVisibilityOfElement(heightElement);
        if (heightElement != null) {
            SeleniumTestUtils.highlight(driver,heightElement);
            heightElement.sendKeys(height);
        }else{
            softAssert.fail("The heightElement input is not visible on the page.");
        }

    }

    private void clickCalculateBMIButton() throws InterruptedException {
        calculateBmi = waits.waitElementToBeClickable(calculateBmi);
        if (calculateBmi != null) {
            SeleniumTestUtils.highlight(driver,calculateBmi);
            calculateBmi.click();
        }else{
            softAssert.fail("The calculateBmi button is not clickable on the page.");
        }
    }

    private String getBMIResult() throws InterruptedException {
        bmiResult = waits.waitUntilVisibilityOfElement(bmiResult);
        if (bmiResult != null) {
            SeleniumTestUtils.highlight(driver,bmiResult);
            String result = bmiResult.getText();
            System.out.println("Application BMI Result: " + result);
            return result;
        }else{
            softAssert.fail("The bmiResult input is not visible on the page.");
        }
        return "noBMIResult";
    }


}
