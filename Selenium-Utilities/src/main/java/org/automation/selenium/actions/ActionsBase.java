package org.automation.selenium.actions;



import org.automation.selenium.browser.Browser;
import org.automation.selenium.error.PageNotFoundException;
import org.automation.selenium.page.MyPageFactory;
import org.automation.selenium.page.PageBase;
import org.automation.utils.tracking.Track;


import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by shantonu on 4/14/16.
 * from very old class, need major refactoring
 * Main usages is to track each action that we perform
 * This was used in old time,but allure gives supports on tracking
 *
 */
public abstract class ActionsBase<T extends PageBase> {

    Stack<Track> tracker = new Stack();
    protected String name;
    private String restoreUrl = "";

    public T getPage() {
        return (T) MyPageFactory.getPage(this.name);
    }

    protected ActionsBase(String name) {
        this.name = name;
    }

    public abstract Track trackSpecial(Track var1);

    public void restoreUrlToGetThisPage() {
        this.restoreUrl = Browser.getInstance().getCurrentUrl();
    }

    public String getRestoreUrl() {
        return this.restoreUrl;
    }

    public boolean isOnPage() {

        return this.getPage().getName().contains(Browser.getInstance().getTitle().trim());
    }



    public <T1 extends ActionsBase> void checkRedirection(int tries, int timeout, String action, Class<T1> clazz) {
        try {
            ActionsBase e = (ActionsBase)clazz.newInstance();
            String pageName = e.getPage().getName();
            for(int i = 0; i < tries && !e.isOnPage(); ++i) {
                Browser.setWebDriverWait(timeout);
            }

            if(!e.isOnPage()) {
                throw new IllegalArgumentException(action + " failed");
            }
        } catch (InstantiationException var8) {
            var8.printStackTrace();
        } catch (IllegalAccessException var9) {
            var9.printStackTrace();
        }

    }



    public <T1 extends ActionsBase<T>> T1 checkNavigation(Class<T1> clazz) throws PageNotFoundException {
        if(this.isOnPage()) {
//need a replaceing irem, more smarter             this.getPage().assertPageLoaded();
            this.restoreUrlToGetThisPage();
            return (T1) clazz.cast(this);
        } else {
            throw new PageNotFoundException(this.getPage().getName());
        }
    }

    public void trackItem(String actionName, String... values) {
        ArrayList valueList = new ArrayList();
        for(String s:values){
            valueList.add(s);
        }
        this.tracker.push(this.trackSpecial(new Track(actionName, valueList)));
    }

    public Track rollback() {
        return (Track)this.tracker.pop();
    }

    public Track getLatestAction() {
        return (Track)this.tracker.peek();
    }


}
