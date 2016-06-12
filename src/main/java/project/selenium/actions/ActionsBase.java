package project.selenium.actions;


import automation.exception.PageNotFoundException;
import automation.experiments.PageFactory;
import org.browser.manage.Browser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.selenium.PageBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by shantonu on 4/14/16.
 * from very old class, need major refactoring
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

    public abstract ActionsBase<T>.Track trackSpecial(ActionsBase<T>.Track var1);

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
        String[] arr$ = values;
        int len$ = values.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            String value = arr$[i$];
            valueList.add(value);
        }

        this.tracker.push(this.trackSpecial(new ActionsBase.Track(actionName, valueList)));
    }

    public ActionsBase<T>.Track rollback() {
        return (ActionsBase.Track)this.tracker.pop();
    }

    public ActionsBase<T>.Track getLatestAction() {
        return (ActionsBase.Track)this.tracker.peek();
    }

    protected class Track {
        String header;
        String actionName;
        List<String> trackValue = new ArrayList();

        public Track(String var1, List<String> actionName) {
            this.setActionName(var1);
            this.setTrackValue(trackValue);
        }

        public String getHeader() {
            return this.header;
        }

        public void setHeader(String header) {
            this.header = header;
        }

        public String getActionName() {
            return this.actionName;
        }

        public void setActionName(String actionName) {
            this.actionName = actionName;
        }

        public List<String> getTrackValue() {
            return this.trackValue;
        }

        public void setTrackValue(List<String> trackValue) {
            this.trackValue = trackValue;
        }

        public void addTrackSpecial(String... listSpecial) {
            String[] arr$ = listSpecial;
            int len$ = listSpecial.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                String value = arr$[i$];
                this.trackValue.add(value);
            }

        }
    }
}
