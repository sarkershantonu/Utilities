package org.automation.angular;

/**
  * @author Carlos Alexandro Becker (caarlos0@gmail.com)
  * @author Serguei Kouzmine (kouzmine_serguei@yahoo.com)
  */


import org.automation.angular.scripts.*;
import org.openqa.selenium.By;

public final class NgBy {
    private NgBy() {
    }

    public static By binding(final String binding) {
        return new JavaScriptBy(new FindBindings(), binding);
    }

    public static By buttonText(final String text) {
        return new JavaScriptBy(new FindButtonText(), text);
    }

    public static By input(final String input) {
        return new JavaScriptBy(new FindModel(), input);
    }

    public static By model(final String model) {
        return new JavaScriptBy(new FindModel(), model);
    }

    public static By partialButtonText(final String text) {
        return new JavaScriptBy(new FindPartialButtonText(), text);
    }

    public static By repeater(final String repeat) {
        return new JavaScriptBy(new FindAllRepeaterRows(), repeat);
    }

    public static By repeaterColumn(final String repeat, String binding) {
        return new JavaScriptBy(new FindAllRepeaterColumns(), repeat, binding);
    }

	public static By repeaterElement(final String repeat, Integer index, String binding){
		return new JavaScriptBy(new FindRepeaterElements(), repeat, index, binding);
	}
	
    public static By options(final String options) {
        return new JavaScriptBy(new FindOptions(), options);
    }

    public static By selectedOption(final String model) {
        return new JavaScriptBy(new FindSelectedOption(), model);
    }
}
