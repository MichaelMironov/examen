package ru.ifellow.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.ifellow.web.annotations.Name;
import java.lang.reflect.Field;
import java.util.*;

import static java.lang.String.format;
import static java.util.stream.Collectors.toMap;
import static ru.ifellow.utils.ReflectionUtil.extractValue;

public class WebPage {

    private Map<String, Object> pageElements;

    public WebPage initialize(){
        pageElements = getPageElements();
        return this;
    }

    public SelenideElement getElement(String name) {
        Object instance = pageElements.get(name);

        if (instance != null && !(instance instanceof SelenideElement))
            throw new ClassCastException(format("Тип элемента [%s] должен иметь тип 'SelenideElement'", name));
        return (SelenideElement) Optional.ofNullable(pageElements.get(name))
                .orElseThrow(() -> new IllegalArgumentException(format("Элемент [%s] отсутсвует", name)));
    }

    public ElementsCollection getElements(String name){
        Object instance = pageElements.get(name);
        if(instance != null && !(instance instanceof ElementsCollection))
            throw new ClassCastException(format("Элемент [%s] должен иметь тип 'ElementsCollection'", name));
        return (ElementsCollection) Optional.ofNullable(pageElements.get(name))
                .orElseThrow(()->new IllegalArgumentException(format("Элемент [%s] отсутствует", name)));
    }

    public String name() {
        return this.getClass().getAnnotation(Name.class).value();
    }

    private Map<String, Object> getPageElements() {
        return Arrays.stream(getClass().getDeclaredFields())
                .filter(e -> e.getDeclaredAnnotation(Name.class) != null)
                .collect(toMap(e -> e.getDeclaredAnnotation(Name.class).value(), this::extractFieldValue));
    }

    private Object extractFieldValue(Field field) {
        return extractValue(field, this);
    }

}
