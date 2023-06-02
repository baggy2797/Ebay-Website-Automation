package pages;

import enums.Url;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.CommonUtils;
import utils.DriverUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class HomePage extends CommonUtils {

    public HomePage() {
    }

    public Boolean navigateToHomePage() {
        String url = Url.BASEURL.getURL();
        System.out.println("Navigating to Ebay.com: " + url);
        try{
            navigateToURL(url);
            return true;
        }
        catch (Exception e){
            throw new NoSuchElementException(e);
        }
    }

    public Boolean ClickOnShopByCategory(){
        try {
            click(By.xpath("//*[@id=\"gh-shop-a\"]"));
            return true;
        } catch (Exception e) {
            throw new NoSuchElementException(e);
        }

    }

    public Boolean EnterASearchText(String searchText){
        try{
            sendKeys(By.xpath("/html/body/header/table/tbody/tr/td[5]/form/table/tbody/tr/td[1]/div[1]/div/input[1]"),searchText);
            return true;
        }catch (Exception e){
            throw new NoSuchElementException(e);
        }
    }

    public Boolean ClickOnSearchButton(){
        try{
            click(By.xpath("/html/body/header/table/tbody/tr/td[5]/form/table/tbody/tr/td[3]/input"));
            return true;
        }catch (Exception e){
            throw new NoSuchElementException(e);
        }
    }

    public Boolean VerifyTheSearchTextWithProduct(String searchText){
        WebElement element = null;
        try{
            element = getElement(By.xpath("//*[@id=\"item3b42a61295\"]/div/div[2]/a/div/span"));
        } catch (Exception e){
            throw new NoSuchElementException(e);
        }
        return element.getAccessibleName().contains(searchText);
    }

    public Boolean ClickOnCategoryChoiceButton(String categoryChoice) {
        try{
            List<WebElement> categoryChoices = getElements(By.xpath("/html/body/header/table/tbody/tr/td[5]/form/table/tbody/tr/td[2]/div/select/option"));
            for(WebElement match: categoryChoices){
                if(categoryChoice.equals(match.getAccessibleName())){
                    click(match);
                    return true;
                }
            }
        }catch (Exception e){
            throw new NoSuchElementException(e);
        }
        return false;
    }

    public Boolean SelectCategoryUnderShopByCategory(String category){
        try {
            click(By.linkText(category));
            return true;
        } catch (Exception e) {
            throw new NoSuchElementException(e);
        }
    }



    public Boolean waitForPageLoad() {
        String pageLoadStatus = null;

        JavascriptExecutor js;
        do {

            js = (JavascriptExecutor) driver;

            pageLoadStatus = (String)js.executeScript("return document.readyState");

        } while ( !pageLoadStatus.equals("complete") );

        System.out.println("Page Loaded.");
        return pageLoadStatus!=null;
    }

    public Boolean SelectCategoryOnLeftHandSide(String subCategory){
        List<WebElement> leftSideResults = null;
        try {
            leftSideResults = getElements(By.xpath("//*[@id=\"s0-16-12-0-1[0]-0-0\"]/ul/li"));
        } catch (Exception e) {
            throw new NoSuchElementException(e);
        }
        try {
            leftSideResults.get(3).click();
            return true;
        }
        catch (Exception e){
            throw new NoSuchElementException(e);
        }

    }

    public Boolean ClickOnAllFilters() {
        try {
            getElement(By.xpath("//*[@id=\"s0-27_1-9-0-1[1]-0-0\"]/section/ul[1]/li[7]/button"))
                    .click();
            return true;
        } catch (Exception e) {
            throw new NoSuchElementException(e);
        }

    }

    public List<String> SelectSubFilters() {
        List<WebElement> subFilters = null;
        try {
            subFilters = getElements(By.xpath("/html/body/div[14]/div[2]/div/form/div[1]/div[1]/div/div"));
        } catch (Exception e) {
            throw new NoSuchElementException(e);
        }
        click(subFilters.get(0));

        List<WebElement> ssf1 = null;
        try {
            ssf1 = getElements(By.xpath("/html/body/div[14]/div[2]/div/form/div[1]/div[2]/div[1]/div/fieldset/div[1]/div"));
        } catch (Exception e) {
            throw new NoSuchElementException(e);
        }

        List<String> FiltersApplied = new ArrayList<>();
        String[] array = new String[100];
        click(ssf1.get(0));

        array = (ssf1.get(0)
                .getAttribute("id")
                .split("-"));
        FiltersApplied.add(array[array.length-1]
                .stripTrailing()
                .stripLeading());

        click(ssf1.get(1));
        array = (ssf1.get(1).getAttribute("id").split("-"));
        FiltersApplied.add(array[array.length-1]
                .stripTrailing()
                .stripLeading());

        click(ssf1.get(2));
        array = (ssf1.get(2).getAttribute("id").split("-"));
        FiltersApplied.add(array[array.length-1]
                .stripTrailing()
                .stripLeading());

        try {
            click(getElement(By.cssSelector("#x-overlay__form > div.x-overlay-footer > div.x-overlay-footer__apply > button")));
        } catch (Exception e) {
            throw new NoSuchElementException(e);
        }

        return FiltersApplied;
    }

    public List<String> checkAppliedFilters() throws InterruptedException{
        List<String> checkAppliedFilters = new ArrayList<>();
        WebDriver driver1 = DriverUtils.getDriver();
        driver1.manage().window();
        WebElement element = null;
        try {
            element = driver1.findElement(By.xpath("/html/body/div[4]/div[4]/div[3]/section/section/ul[1]/li[1]/div/button"));
        } catch (Exception e) {
            throw new NoSuchElementException(e);
        }

        element.click();
        List<WebElement> appliedFilters = getElements(By.xpath("/html/body/div[4]/div[4]/div[3]/section/section/ul[1]/li[1]/div/div/ul/li/a"));
        for(WebElement af: appliedFilters){
            String temp = af.getAccessibleName();
            temp = temp.
                    replace(" filter applied"," ")
                    .replace(": ","_")
                    .stripTrailing()
                    .stripLeading();

            checkAppliedFilters.add(temp);
        }

        return checkAppliedFilters;
    }
}
