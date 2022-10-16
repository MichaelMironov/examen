package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.ifellow.web.annotations.Name;
import ru.ifellow.web.page.WebPage;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@Name("главная страница")
public class MainPage extends WebPage {
    @Name("секция создания задачи")
    private final SelenideElement createTaskSection = $x("//section[@id='create-issue-dialog']");

    @Name("название задачи")
    private final SelenideElement taskTitle = $x("//input[@name='summary']");

    @Name("описание задачи")
    private final SelenideElement iframeTaskDescription = $x("//body[@id='tinymce']//p");

    @Name("кнопка [Создать]")
    private final SelenideElement createTaskButton = $x("//input[@id='create-issue-submit']");

    @Name("список задач")
    private final ElementsCollection elements = $$x("//*[@id='issuetable']/tbody");

    @Name("поиск")
    private final SelenideElement search = $x("//input[@name='searchString']");

    @Name("иконка профиля")
    private final SelenideElement avatar = $x("//span[@class='aui-avatar-inner']");

    @Name("Лента активности")
    private final SelenideElement lenta = $x("//h3[@id='gadget-10003-title']");
}
