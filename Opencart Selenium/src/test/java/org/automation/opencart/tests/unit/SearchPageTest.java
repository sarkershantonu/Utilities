package org.automation.opencart.tests.unit;



import org.automation.opencart.workflows.ProductSearching;
import org.junit.Before;

/**
 * Created by shantonu on 5/7/16.
 */
public class SearchPageTest {
    private ProductSearching search;

    @Before
    public void init(){
        search = new ProductSearching();

    }
/*
    @Test
    public void searchKeyWordTest(){
        SearchResultPage result =  search.searchAProduct("Laptop");

        Assert.assertTrue(result.searchButton.isEnabled());
        Assert.assertEquals("Search",result.searchButton.getText());
        result.searchButton.getAttribute("Class");//todo , see from firebug and validate
        result.searchButton.getAttribute("id");//todo , see from firebug and validate
        result.searchButton.getAttribute("type");//todo , see from firebug and validate
        result.searchButton.getAttribute("value");//todo , see from firebug and validate
    }*/

}
