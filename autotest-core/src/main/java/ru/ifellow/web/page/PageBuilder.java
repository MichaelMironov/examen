package ru.ifellow.web.page;

public class PageBuilder {

   public PageBuilder() {
   }

   private WebPage currentWebPage;

   public WebPage getCurrentPage() {
      if (currentWebPage == null) {
         throw new IllegalStateException("Текущая страница не задана");
      }
      return currentWebPage.initialize();
   }

   public void setCurrentPage(WebPage webPage) {
      this.currentWebPage = webPage;
   }

}