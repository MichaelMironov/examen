package steps;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ifellow.web.page.PageBuilder;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static io.qameta.allure.Allure.step;

public class ActionsSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActionsSteps.class);

    private PageBuilder pageBuilder;

    public ActionsSteps(PageBuilder webPageBuilder) {
        this.pageBuilder = webPageBuilder;
    }

    @Когда("кликнуть на элемент по тексту {string}")
    public void clickOnElementByText(String text){
        $(Selectors.byText(text)).shouldBe(visible).click();
        LOGGER.info("клик по элементу с текстом '{}'", text);
    }

    @Если("кликнуть на элемент {string}")
    public void clickOnElement(String elementName){
        SelenideElement element = pageBuilder.getCurrentPage().getElement(elementName);
        element.shouldBe(visible).click();
        LOGGER.info("клик по элементу'{}'", elementName);
    }

    @Когда("ввести в поле {string} значение {string}")
    public void fillTheField(String field, String value) {
        SelenideElement fieldElement = pageBuilder.getCurrentPage().getElement(field);
        fieldElement.shouldBe(visible).setValue(value);
        LOGGER.info("в поле '{}' введено значение '{}'", field, value);
    }

    @Если("очистить поле {string}")
    public void clearFiled(String elementName) {
        pageBuilder.getCurrentPage().getElement(elementName).shouldBe(visible).clear();
    }

    @Тогда("извлечь данные элемента {string}")
    public void extractValue(String elementName){
      String value = pageBuilder.getCurrentPage().getElement(elementName).shouldBe(visible,appear).getText();
      step("Количество задач проекта: " + value);
      LOGGER.info("Значение эелемента {} = {}",elementName, value);
    }

    @Тогда("выйти из элемента {string}")
    public void getOutFormElement(String elementName){
        SelenideElement element = pageBuilder.getCurrentPage().getElement(elementName).shouldBe(visible);
        switchTo().defaultContent();
        element.shouldBe(disappear);
    }

    @Тогда("перейти в элемент {string}")
    public void goInElement(String elementName){
        switchTo().frame(1);
        SelenideElement element = pageBuilder.getCurrentPage().getElement(elementName).shouldBe(visible);
    }

    @Тогда("кликнуть на {int}-й элемент списка {string}")
    public void getListItemByIndex(int index, String elementName){
        SelenideElement element = pageBuilder.getCurrentPage()
                .getElements(elementName).get(index-1).shouldBe(visible);
        element.click();
    }

    @Когда("подождать исчезновения текста {string}")
    public void waitUntilTextAbsentOnPage(String text) {
        $(Selectors.byPartialLinkText(text)).shouldBe(disappear);
        LOGGER.info("на странице '{}' отсутствует текст '{}'", pageBuilder.getCurrentPage().name(), text);
    }

}
