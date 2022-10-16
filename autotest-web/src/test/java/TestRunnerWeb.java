import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "summary"},
        monochrome = true,
        glue = {"steps", "hooks"},
        features = "classpath:features")
public class TestRunnerWeb {
}
