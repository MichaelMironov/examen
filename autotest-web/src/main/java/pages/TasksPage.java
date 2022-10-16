package pages;

import com.codeborne.selenide.SelenideElement;
import ru.ifellow.web.annotations.Name;
import ru.ifellow.web.page.WebPage;

import static com.codeborne.selenide.Selenide.$x;

@Name("задачи проекта")
public class TasksPage extends WebPage {

    @Name("поиск задач")
    private final SelenideElement searcher = $x("//input[@id='searcher-query']");

    @Name("статус задачи")
    private final SelenideElement taskStatus = $x("//span[contains(@class, 'jira-issue-status')]");

    @Name("количество задач")
    private final SelenideElement tasksCount = $x("//span[contains(@class, 'results-count-total')]");
}
