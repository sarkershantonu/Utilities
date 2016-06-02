package project.selenium.pages;

import lombok.Getter;
import project.selenium.PageBase;
import project.selenium.actions.mouse.Click;
import project.selenium.pages.common.SearchPanel;


public class HomePage extends PageBase{

    private @Getter SearchPanel search;

    public void setPageLoadText(String pageLoadText) {
        // todo
    }
    public Click<HomePage> click = new Click<HomePage>(this);
}
