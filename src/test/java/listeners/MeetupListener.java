package listeners;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.*;
import setUpBrowsers.InitDriver;
import utils.FilesManager;
import utils.TakeScreenShots;
import utils.GlobalVariables;
import utils.Utils;

import java.io.IOException;

public class MeetupListener implements ISuiteListener, ITestListener {

    public void onStart(ISuite iSuite) {
        String browserConfContent = "";
        try {
            browserConfContent = FilesManager.readFile(System.getProperty("user.dir") + "/src/main/resources/browsersConfig.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        GlobalVariables.browsersConfigurations = new JSONObject(browserConfContent);
        GlobalVariables.env = iSuite.getParameter("env");
        GlobalVariables.host = iSuite.getParameter("host");
        System.out.println("############################### Set Global Variables #############################################\n");
        System.out.println("\t\t\tEnv: " + GlobalVariables.env);
        System.out.println("\t\t\tHost: " + GlobalVariables.host);
        System.out.println("\n################################################################################################\n");
    }

    public void onFinish(ISuite iSuite) {
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        Object currentClass = iTestResult.getInstance();
        WebDriver webDriver = ((InitDriver) currentClass).getDriver();
        /*WebDriver driver = (WebDriver) iTestResult.getTestContext().getAttribute("webDriver");
        SessionId session = ((RemoteWebDriver)driver).getSessionId();
        System.out.println("driver" + session.toString());

        System.out.println("sESSION "+iTestResult.getTestContext().getAttribute("sessionID"));*/
        String nameOfTest = iTestResult.getMethod().getMethodName();
        String randomGUID = Utils.generateGUID();
        String fileNameFail = nameOfTest.concat("-"+randomGUID+".png");
        TakeScreenShots screenShotsSuccess = new TakeScreenShots(webDriver);
        screenShotsSuccess.takeScreenShot(fileNameFail);

        System.out.println("ScreenShotName Success:" + fileNameFail);

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Object currentClass = iTestResult.getInstance();
        WebDriver webDriver = ((InitDriver) currentClass).getDriver();

        /*WebDriver driver = (WebDriver) iTestResult.getTestContext().getAttribute("webDriver");
        SessionId session = ((RemoteWebDriver)driver).getSessionId();
        System.out.println("driver" + session.toString());

        System.out.println("sESSION "+iTestResult.getTestContext().getAttribute("sessionID"));*/
        String nameOfTest = iTestResult.getMethod().getMethodName();
        String randomGUID = Utils.generateGUID();
        String fileNameFail = nameOfTest.concat("-"+randomGUID+".png");
        TakeScreenShots screenShotsFails = new TakeScreenShots(webDriver);
        screenShotsFails.takeScreenShot(fileNameFail);

        System.out.println("ScreenShotName Fail:" + fileNameFail);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
