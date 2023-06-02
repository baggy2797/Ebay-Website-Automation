package actions;

import pages.HomePage;

import java.util.List;

public class PageActions {

    public Boolean navigateToHomePage() {
        HomePage homePage = new HomePage();
        return homePage.navigateToHomePage();
    }

    public Boolean ClickOnShopByCategory() {
        HomePage homePage = new HomePage();
        return homePage.ClickOnShopByCategory();
    }

    public Boolean ClickOnCategoryChoiceButton(String categoryChoice) {
        HomePage homePage = new HomePage();
        return homePage.ClickOnCategoryChoiceButton(categoryChoice);
    }

    public Boolean SelectCategoryUnderShopByCategory(String category){
        HomePage homePage = new HomePage();
        return homePage.SelectCategoryUnderShopByCategory(category);
    }

    public Boolean SelectCategoryOnLeftHandSide(String subCategory){
        HomePage homePage = new HomePage();
        return homePage.SelectCategoryOnLeftHandSide(subCategory);
    }

    public Boolean ClickOnAllFilters() {
        HomePage homePage = new HomePage();
        return homePage.ClickOnAllFilters();
    }

    public List<String> SelectSubFilters() throws InterruptedException {
        HomePage homePage = new HomePage();
        return homePage.SelectSubFilters();
    }

    public List<String> checkAppliedFilters() throws InterruptedException {
        HomePage homePage = new HomePage();
        return homePage.checkAppliedFilters();
    }

    public Boolean EnterASearchText(String searchText) {
        HomePage homePage = new HomePage();
        return homePage.EnterASearchText(searchText);
    }

    public Boolean ClickOnSearchButton(){
        HomePage homePage = new HomePage();
        return homePage.ClickOnSearchButton();
    }

    public Boolean VerifyTheSearchTextWithProduct(String searchText){
        HomePage homePage = new HomePage();
        return homePage.VerifyTheSearchTextWithProduct(searchText);
    }

    public Boolean waitForPageLoad(){
        HomePage homePage = new HomePage();
        return homePage.waitForPageLoad();
    }
}
