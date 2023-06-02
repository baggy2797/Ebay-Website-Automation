import actions.PageActions;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.DriverUtils;

import java.util.logging.Logger;

public class TestToVerifyAProductViaSearch {

    /* Test - 2:
        Scenario 2: Access a Product via Search
             Go to www.ebay.com
             Type any search string in the search bar. For example: MacBook.
             Change the Search category. For example: Computers/Tablets & Networking
            and click Search.
             Verify that the page loads completely.
             Verify that the first result name matches with the search string.
     */

    public WebDriver driver;

    private Logger logger = Logger.getLogger(String.valueOf(TestToVerifyAProductViaSearch.class));

    @BeforeClass
    public void setUp(){
        logger.info("Start the Browser");
        driver = DriverUtils.getDriver();
    }

    @Test
    public void TestToVerifyAProductViaSearch() {

        PageActions pageActions = new PageActions();

        logger.info("Opening the Ebay HomePage");
        Assert.assertTrue(pageActions.navigateToHomePage());
        logger.info("-> Loaded the Ebay HomePage");


        logger.info("Selecting the category choice from Category dropdown");
        Assert.assertTrue(pageActions.ClickOnCategoryChoiceButton("Books"));
        logger.info("-> Selected the required category choice from Category dropdown");


        logger.info("Entering the text in the search bar");
        Assert.assertTrue(pageActions.EnterASearchText("Autobiography"));
        logger.info("-> Entered the text in the search bar");


        logger.info("Click on the Search Button");
        Assert.assertTrue(pageActions.ClickOnSearchButton());
        logger.info("-> Clicked on the Search Button");


        logger.info("Waiting for the Search Results page to be loaded");
        Assert.assertTrue(pageActions.waitForPageLoad());
        logger.info("-> Loaded for the Search Results page to be loaded");


        logger.info("Verify the search text with the first search result");
        Assert.assertTrue(pageActions.VerifyTheSearchTextWithProduct("Autobiography"));
        logger.info("-> Verified the search text with the first search result");

    }

    @AfterClass
    public void tearDown(){
        driver.quit();
        logger.info("Quitting the Browser");
    }

}
