package ru.ifellow.web.configuration;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:config/${browser}.properties",
        "classpath:config/chrome.properties",
        "system:properties",
        "system:env"
})
public interface WebConfiguration extends Config {

    @Key("webdriver.browser.size")
    @DefaultValue("1920x1080")
    String webDriverBrowserSize();

    @Key("pages.package")
    @DefaultValue("pages")
    String pagesPackage();

    @Key("webdriver.timeoutSeconds")
    @DefaultValue("10")
    int webDriverTimeoutSeconds();

    @Key("polling.timeoutMs")
    @DefaultValue("200")
    int pollingTimeoutMs();

}