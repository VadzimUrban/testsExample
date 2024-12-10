package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import utils.Constants;
import utils.TestListener;


public class BaseTest {
    public WebDriver driver;
    protected Constants constants = new Constants();
    protected final Logger logger = LoggerFactory.getLogger(BaseTest.class);


    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        TestListener.driver = driver;
    }


//    @AfterMethod
//    public void takeFailedResultScreenshot(ITestResult testResult) throws IOException {
//
//        if (testResult.getStatus() == ITestResult.FAILURE) {
//            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//            FileUtils.copyFile(screenshot, new File("screenshots/" + testResult.getName() + " " + java.time.LocalDateTime.now() + ".png"));
//        }
//    }


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    // Вспомогательный метод для перехода на страницу
    protected void navigateTo(String url) {
        driver.get(url);
    }
}
