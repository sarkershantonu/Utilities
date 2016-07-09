package org.automation.opencart.pages;

import lombok.Getter;
import org.automation.opencart.OpenCartPageBase;
import org.automation.opencart.pages.common.SearchPanel;
import org.automation.selenium.actions.mouse.Click;


public class HomePage extends OpenCartPageBase {

    private @Getter
    SearchPanel search;

    public void setPageLoadText(String pageLoadText) {
        // todo
    }
    public Click<HomePage> click = new Click<HomePage>(this);
}
