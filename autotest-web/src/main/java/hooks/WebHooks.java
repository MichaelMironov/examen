package hooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import ru.ifellow.web.configuration.WebConfiguration;
import ru.ifellow.web.page.Environment;

import java.io.FileOutputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class WebHooks {

    @Before
    public void setup() {
        WebConfiguration cfg = ConfigFactory.create(WebConfiguration.class,
                System.getProperties(),
                System.getenv());

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
        );

        Configuration.browserSize = cfg.webDriverBrowserSize();
        Configuration.webdriverLogsEnabled = false;
        Configuration.pollingInterval = cfg.pollingTimeoutMs();
        Configuration.timeout = TimeUnit.SECONDS.toMillis(cfg.webDriverTimeoutSeconds());
        Environment.initPages(cfg.pagesPackage());

        String path = System.getProperty("allure.results.directory");
        try {
            Properties props = new Properties();
            FileOutputStream fos = new FileOutputStream(path + "/environment.properties");
            props.setProperty(System.getProperty("os.name"), System.getProperty("os.version"));
            props.setProperty("Architecture",System.getProperty("os.arch"));
            props.setProperty("JDK", System.getProperty("java.version"));
            props.setProperty("Cucumber plugin", System.getProperty("cucumber.plugin"));
            props.store(fos, "environment");
            fos.close();

        } catch (Exception e) {
            System.err.println("Создание файла");
        }
    }

    @After
    public void close() {
        WebDriverRunner.closeWebDriver();
    }

}
