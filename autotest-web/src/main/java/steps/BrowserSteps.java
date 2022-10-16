package steps;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import ru.ifellow.web.page.PageBuilder;
import ru.ifellow.web.page.WebPage;

import static ru.ifellow.web.page.Environment.getPage;


public class BrowserSteps {

    private PageBuilder pageBuilder;

    public BrowserSteps(PageBuilder pageBuilder) {
        this.pageBuilder = pageBuilder;
    }

    @Дано("открыта страница {string}")
    @Дано("открыть страницу по адресу {string}")
    public void openUrl(String url){
        Selenide.open(url);
    }

    @Тогда("закрыть страницу")
    public void closeDriver(){
        WebDriverRunner.getWebDriver().close();
    }

    @Если("пользователь на странице {string}")
    @Тогда("инициализация страницы {string}")
    @И("переход на страницу {string}")
    public void setPage(String pageName) {
        WebPage page = getPage(pageName);
        pageBuilder.setCurrentPage(page);
    }
}
