package automation.steps;

import project.selenium.pages.HomePage;

/**
 * Created by shantonu on 5/5/16.
 */
public class HomePageSteps {

    private HomePage page;

    public void emptySearchTextBox(){
        page.searchTextBox.clear();
    }

    public void insertTextInSerchTextBox(String input){
        page.searchButton.sendKeys(input);
    }

    public void clickSerchButton(){
        page.click.perform(page.searchTextBox);
    }

}
