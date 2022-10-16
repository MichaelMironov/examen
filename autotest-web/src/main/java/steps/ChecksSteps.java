package steps;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.ru.Тогда;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ifellow.web.page.PageBuilder;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class ChecksSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChecksSteps.class);

    private PageBuilder pageBuilder;

    public ChecksSteps(PageBuilder pageBuilder) {
        this.pageBuilder = pageBuilder;
    }

    @Тогда("на странице присутствует текст {string}")
    public void textVisibleOnPage(String text) {
        $(Selectors.byText(text)).shouldBe(visible);
    }

    @Тогда("элемент {string} содержит текст {string}")
    public void elementContainTest(String elementName, String expectedText){
        SelenideElement element = pageBuilder.getCurrentPage().getElement(elementName).shouldBe(visible);
        String actualText = element.shouldHave(text(expectedText)).innerText();
        LOGGER.info("Ожидаемый текст: {}. Актульный текст: {}",expectedText,actualText);
    }

    @Тогда("элемент {string} не обнаружен")
    public void elementNotExistsOnPage(String elementName){
       SelenideElement element = pageBuilder.getCurrentPage().getElement(elementName).shouldNotBe(visible);
    }

    @Тогда("на странице появился элемент {string}")
    public void goInElement(String elementName){
        SelenideElement element = pageBuilder.getCurrentPage().getElement(elementName).shouldBe(appear);
    }
}
