package MainClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {
    public static WebDriver driver;
    public static Properties CONFIG; // initialise config file

    public Base(){
        try {

            // initialize CONFIG to corresponding env
            CONFIG = new Properties();
            FileInputStream fs = new FileInputStream("src/main/java/ConfigProperties/config.properties");
            CONFIG.load(fs);

        } catch (Exception e) {
            System.out.println("Error on intializing config.properties files");
        }
    }

    public void initiate() {

        String browserType = CONFIG.getProperty("browser");
        switch (browserType) {
            case "chrome": {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/Drivers/chromedriver.exe");
                driver = new ChromeDriver();
            }
            break;
            case "firefox": {
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/main/Drivers/geckodriver.exe");
                driver = new FirefoxDriver();
            }
            break;
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();

        driver.get(CONFIG.getProperty("portalUrl"));
    }
}
