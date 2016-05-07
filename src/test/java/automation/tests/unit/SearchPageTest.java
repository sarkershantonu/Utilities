package automation.tests.unit;


import automation.workflows.ProductSearching;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.jetty.html.Page;
import project.selenium.PageBase;
import project.selenium.pages.SearchResultPage;

/**
 * Created by shantonu on 5/7/16.
 */
public class SearchPageTest {
    private ProductSearching search;

    @Before
    public void init(){
        search = new ProductSearching();

    }

    @Test
    public void searchKeyWordTest(){
        SearchResultPage result =  search.searchAProduct("Laptop");

        Assert.assertTrue(result.searchButton.isEnabled());
        Assert.assertEquals("Search",result.searchButton.getText());
        result.searchButton.getAttribute("Class");//todo , see from firebug and validate
        result.searchButton.getAttribute("id");//todo , see from firebug and validate
        result.searchButton.getAttribute("type");//todo , see from firebug and validate
        result.searchButton.getAttribute("value");//todo , see from firebug and validate
    }

}
