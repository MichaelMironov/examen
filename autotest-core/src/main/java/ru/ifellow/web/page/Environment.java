package ru.ifellow.web.page;

import ru.ifellow.utils.ReflectionUtil;
import ru.ifellow.web.annotations.Name;

import java.util.Arrays;

public class Environment {

    private static PageCache pages;

    public static WebPage getPage(String name){
        return pages.get(name);
    }

    public static void initPages(String packageName) {
        pages = new PageCache();
        ReflectionUtil
                .getPagesAnnotatedWith(packageName, Name.class)
                .stream()
                .map(it -> {
                    if (WebPage.class.isAssignableFrom(it)) {
                        return (Class<? extends WebPage>) it;
                    } else {
                        throw new IllegalStateException("Класс " + it.getName() + " должен наследоваться от WebPage");
                    }
                })
                .forEach(clazz -> pages.put(getClassAnnotationValue(clazz), clazz));
    }

    private static String getClassAnnotationValue(Class<?> c) {
        return Arrays
                .stream(c.getAnnotationsByType(Name.class))
                .findFirst()
                .map(Name::value)
                .orElseThrow(() -> new AssertionError("Не найдены аннотации Name в классе " + c.getName()));
    }
}
