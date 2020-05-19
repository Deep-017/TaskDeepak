
package Initialization;

import CommonParts.Path;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

public class BaseClass extends Path {

    protected static WebDriver driver;

    @BeforeSuite
    public static void setUp() {
        System.setProperty(Path.cDriverType, Path.cDriver);
/*      ChromeOptions options = new ChromeOptions(); //Can Enable if headless execution is needed
        options.addArguments("headless");
        driver = new ChromeDriver(options); */
        driver = new ChromeDriver(); //Need to comment this if headless is enabled
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.navigate().to(Path.urlToNavigate);
    }

    @AfterSuite
    public static void closeAll(){
        driver.quit();
    }
}
