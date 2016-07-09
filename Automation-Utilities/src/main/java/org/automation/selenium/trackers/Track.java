package org.automation.selenium.trackers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shantonu on 6/13/16.
 */
public class Track {
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