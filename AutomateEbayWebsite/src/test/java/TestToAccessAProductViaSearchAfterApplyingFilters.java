import actions.PageActions;
import enums.Url;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.DriverUtils;

import java.util.List;
import java.util.logging.Logger;

public class TestToAccessAProductViaSearchAfterApplyingFilters {

    /*  Test - 1 :
        Scenario 1 : Access a Product via category after applying multiple filters
             Go to www.ebay.com
             Navigate to Search by category > Electronics > Cell Phones & accessories
             After the page loads, click Cell Phones & Smartphones in the left hand side
            navigation section.
             Now, click – See All (appears under “Shop by brand” or “Shop by Network”).
             Add 3 filters - screen size, Price and Item location appearing in the pop-up and
            click apply.
             Verify that the filter tags are applied.
 */

    public WebDriver driver;

    private Logger logger = Logger.getLogger(String.valueOf(TestToAccessAProductViaSearchAfterApplyingFilters.class));
    @BeforeClass
    public void setUp(){
        logger.info("Start the Browser");
        driver = DriverUtils.getDriver();
    }

    @Test
    public void TestToAccessAProductViaSearchAfterApplyingFilters() throws InterruptedException {
        PageActions pageActions = new PageActions();

        logger.info("Opening the Ebay HomePage");
        pageActions.navigateToHomePage();
        Assert.assertEquals(Url.BASEURL.getURL(),DriverUtils.getDriver().getCurrentUrl());
        logger.info("-> Loaded the Ebay HomePage");


        logger.info("Navigate to Search by category");
        Assert.assertTrue(pageActions.ClickOnShopByCategory());
        logger.info("-> Navigated to Search by category");


        logger.info("Selecting the Sub Search category : Electronics > Cell Phones & accessories");
        Assert.assertTrue(pageActions.SelectCategoryUnderShopByCategory("Cell Phones, Smart Watches & Accessories"));
        logger.info("-> Selected the Sub Search category : Electronics > Cell Phones & accessories");


        logger.info("Select a category from left navigation section");
        Assert.assertTrue(pageActions.SelectCategoryOnLeftHandSide("Display Phones"));
        logger.info("-> Selected a category from left navigation section");


        logger.info("Select some filters randomly");
        Assert.assertTrue(pageActions.ClickOnAllFilters());
        logger.info("-> Selected some filters randomly");


        List<String> expectedFilters = null;
        try {
            expectedFilters = pageActions.SelectSubFilters();
            Assert.assertFalse(expectedFilters.isEmpty());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("Fetch the pre-applied filters from the filter application bar");
        List<String> appliedFilters = null;
        try {
            appliedFilters = pageActions.checkAppliedFilters();
            Assert.assertFalse(appliedFilters.isEmpty());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("-> Fetched the pre-applied filters from the filter application bar");

        logger.info("Verify if the filter applied is the one you chose initially");
        for (int i = 0; i < appliedFilters.size(); i++) {
            Assert.assertEquals(expectedFilters.get(i)
                            .replace("%26", "&"),
                    appliedFilters.get(i)
                            .replace("%26", "&"));
        }
        logger.info("-> Verified the filters applied");

    }

    @AfterClass
    public void tearDown(){
        driver.quit();
        logger.info("Quitting the Browser");

    }
}
