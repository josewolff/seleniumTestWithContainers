package meetupDic2019.init;

import bmiTrackerPage.actions.BMITrackerActions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import setUpBrowsers.InitDriver;
import utils.GlobalVariables;
import utils.SeleniumTestUtils;

/**
 * Created by josea.wolff on 6/22/19.
 */
public class InitTests extends InitDriver {

    protected BMITrackerActions bmiTrackerActions;

    public InitTests(String browser){
        super(browser);
    }
    @BeforeClass(alwaysRun = true)
    public void initPageFactories(){
        bmiTrackerActions =  new BMITrackerActions(getDriver(),wait, softAssert);
    }

    @BeforeMethod(alwaysRun = true)
    public void goToPage(){
        getDriver().get(GlobalVariables.host);
        SeleniumTestUtils.waitForPageLoad(getDriver());
    }
}
