package automation.experiments;


import automation.exception.PageNotFoundException;
import org.browser.manage.Browser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.selenium.PageBase;

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
    private static Logger LOG = LoggerFactory.getLogger(ActionsBase.class);
    Stack<Track> tracker = new Stack();
    protected String name;
    private String restoreUrl = "";

    public T getPage() {
        return (T) PageFactory.getPage(this.name);
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

    public <T1 extends ActionsBase<T>> T1 navigateToPage(Class<T1> clazz, boolean condition) throws PageNotFoundException {
        if(this.isOnPage()) {
            return (T1) clazz.cast(this);
        } else {
            if(!this.getRestoreUrl().isEmpty() && condition) {
                Browser.getInstance().get(this.getRestoreUrl());
                this.checkNavigation(clazz);
            } else {
                this.navigateTo();
            }

            return (T1) clazz.cast(this);
        }
    }

    public <T1 extends ActionsBase> void checkRedirection(int tries, int timeout, String action, Class<T1> clazz) {
        try {
            ActionsBase e = (ActionsBase)clazz.newInstance();
            String pageName = e.getPage().getName();
            LOG.debug("Verify it redirects to the " + pageName + " page");

            for(int i = 0; i < tries && !e.isOnPage(); ++i) {
                LOG.debug("It isn\'t redirecting to the " + pageName + " page, after " + action + "Waiting" + timeout + " seconds more...");
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

    public void navigateTo() {
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

    public void track(String actionName, String... values) {
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
