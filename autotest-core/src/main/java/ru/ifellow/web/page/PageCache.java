package ru.ifellow.web.page;

import com.codeborne.selenide.Selenide;
import com.google.common.collect.Maps;

import java.util.Map;

public class PageCache {
    private Map<String, Class<? extends WebPage>> pages;

    public PageCache() {
        pages = Maps.newHashMap();
    }

    public WebPage get(String pageName) {
        return Selenide.page(getPageFromPagesByName(pageName)).initialize();
    }

    public <T extends WebPage> T get(Class<T> clazz, String name) {
        WebPage webPage = Selenide.page(getPageFromPagesByName(name)).initialize();
        if (!clazz.isInstance(webPage)) {
            throw new IllegalStateException(name + " не является инстансом " + clazz + ". Актуальная страница " + webPage);
        }
        return (T) webPage;
    }

    private Map<String, Class<? extends WebPage>> getPageMapInstanceInternal() {
        return pages;
    }

    private Class<? extends WebPage> getPageFromPagesByName(String pageName) throws IllegalArgumentException {
      Class<? extends  WebPage> page = getPageMapInstanceInternal().get(pageName);
        if (page == null) {
            throw new IllegalArgumentException("Страница с именем '" + pageName + "' не объявлена");
        }
        return page;
    }

    public void put(String pageName, Class<? extends WebPage> page) {
        if (page == null) {
            throw new IllegalArgumentException("Передана пустая страница");
        }
        pages.put(pageName, page);
    }

}
