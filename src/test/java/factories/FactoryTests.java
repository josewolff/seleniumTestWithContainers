package factories;

import meetupDic2019.BMITrackerCalculation;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Factory;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.ArrayList;

public class FactoryTests {


    @Factory
    @Parameters({"browsers","runOn"})
    public Object[] factoryMethod(@Optional("none") String browserToTest, @Optional("chrome") String runOn) {
        ArrayList<Object> result = new ArrayList<>();
        if(browserToTest.equalsIgnoreCase("none") || !runOn.equalsIgnoreCase("grid")){
            result = getClasses(result,browserToTest);
        }else{
            result = initClasses(browserToTest);
        }

        return result.toArray();
    }

    private ArrayList<Object> initClasses(String browserToTest) {
        final String [] browsers = browserToTest.split(",");
        ArrayList<Object> result = new ArrayList<>();
        for(int i = 0; i < browsers.length; i++){
            result = getClasses(result,browsers[i]);
        }
        return result;
    }

    private ArrayList<Object> getClasses( ArrayList<Object> result, String browser){
        result.add(new BMITrackerCalculation(browser));
        //result.add(new hitest(browser));
        //result.add(new hitest2(browser));
        //result.add(new hitest3(browser));
        return  result;
    }
}
