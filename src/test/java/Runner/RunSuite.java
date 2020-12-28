package Runner;
import MainClass.Base;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = ("src/test/resources/Features"),
        glue = ("cucumber/Stepdefs"),
        plugin =  {"pretty", "html:target/cucumber-html-report","html:target/cucumber", "json:target/cucumber.json"},
        tags = ("@suite"),
        publish = true
)
public class RunSuite extends Base {
    @AfterClass
    public static void DriverClose()
    {
        driver.quit();
    }
}
