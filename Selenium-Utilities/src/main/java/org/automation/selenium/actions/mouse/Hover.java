

public class Hover<T extends PageBase> extends MouseBase<T> 
{
 public void hoverOn(WebElement element) {// example only, no use
        action = new Actions(Browser.getInstance());
        action.moveToElement(element).perform();
    }
     public Hover(T t) {
        super(t);
    }
    @Override
    T on(WebElement element, int second) {//apply for each page... 
        action = new Actions(Browser.getInstance());
        action.moveToElement(element).perform();
        return page;
    }
    
      T byJS(WebElement element, int second) {
       //todo mouse hover by javaScript
        return page;
    }

}
