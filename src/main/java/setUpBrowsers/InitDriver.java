package setUpBrowsers;

import org.json.JSONObject;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import utils.GlobalVariables;

import java.net.URL;

public class InitDriver {


    private WebDriver driver;
    protected WebDriverWait wait;
    private String geckoDriver = "";
    private String chromeDriver = "";
    public SoftAssert softAssert;
    private String browser = "";

    public InitDriver(String browser){
        this.browser = browser;
    }

    @Parameters({"runOn"})
    @BeforeClass(alwaysRun = true)
    public void openDriver(@Optional("safari") String browser, ITestContext testContext) {
        initDriver(browser);
    }

    public WebDriver getDriver(){
        return driver;
    }


    @AfterClass(alwaysRun = true)
    public void tearDown(ITestContext testContext) {
        try {
            driver.quit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private WebDriver initDriver(String browser) {
        try {
            selectDriverBasedOnOs();
            if (browser.equals("firefox")) {
                System.setProperty("webdriver.gecko.driver", geckoDriver);

                driver = new FirefoxDriver();
                driver.manage().window().maximize();
            } else {
                if (browser.equals("chrome")) {
                    System.setProperty("webdriver.chrome.driver", chromeDriver);
                    DesiredCapabilities caps = DesiredCapabilities.chrome();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments(new String[]{"--start-maximized"});
                    options.merge(caps);
                    driver = new ChromeDriver(options);
                    driver.manage().window().maximize();
                } else if (browser.equals("ie")) {
                    System.setProperty("webdriver.ie.driver", "drivers/windows/IEDriverServer.exe");
                    driver = new InternetExplorerDriver();
                    driver.manage().window().maximize();
                } else if (browser.equals("safari")) {
                    driver = new SafariDriver();
                    driver.manage().window().maximize();
                }else if (browser.equalsIgnoreCase("grid")){
                    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), getCapabilities());
                }
            }

            wait = new WebDriverWait(driver, 15, 100);
            softAssert =  new SoftAssert();
            return driver;
        } catch (Exception ex) {
            ex.printStackTrace();
            Assert.fail("fail to init the browser " + ex.getMessage());
            return null;
        }
    }

    private DesiredCapabilities getCapabilities(){
        DesiredCapabilities capabillities = new DesiredCapabilities();
        if (GlobalVariables.browsersConfigurations.has(browser)) {
            JSONObject configuration = GlobalVariables.browsersConfigurations.getJSONObject(browser);
            if (browser.contains("firefox")){
                capabillities =  DesiredCapabilities.firefox();
                capabillities.setPlatform(Platform.LINUX);
                capabillities.setVersion(configuration.getString("version"));
                capabillities.setBrowserName(configuration.getString("browserName"));
            }else if (browser.contains("chrome")){
                capabillities =  DesiredCapabilities.chrome();
                capabillities.setPlatform(Platform.LINUX);
                capabillities.setVersion(configuration.getString("version"));
                capabillities.setBrowserName(configuration.getString("browserName"));
            }
        }
        return capabillities;
    }


    private void selectDriverBasedOnOs(){
        String operativeSystem = System.getProperty("os.name").toLowerCase();
        if(operativeSystem.contains("mac")){
           geckoDriver = "drivers/macOsx/geckodriver";
           chromeDriver = "drivers/macOsx/chromedriver";
        }else if(operativeSystem.contains("windows")){
            geckoDriver = "drivers/windows/geckodriver.exe";
            chromeDriver = "drivers/windows/chromedriver.exe";
        }
    }
}
