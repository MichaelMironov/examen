package pages;

import com.codeborne.selenide.SelenideElement;
import ru.ifellow.web.annotations.Name;
import ru.ifellow.web.page.WebPage;

import static com.codeborne.selenide.Selenide.$x;

@Name(value = "Страница авторизации Jira")
public class AuthorizationPage extends WebPage {

    @Name("поле логина")
    private final SelenideElement loginField = $x("//input[@id='login-form-username']");

    @Name("поле пароля")
    private final SelenideElement passwordField = $x("//input[@id='login-form-password']");

    @Name("кнопка [вход]")
    private final SelenideElement buttonLogIn = $x("//input[@type='submit' and @name='login']");

    @Name("ошибка авторизации")
    private final SelenideElement usernameError = $x("//div[@id='usernameerror']");

}
