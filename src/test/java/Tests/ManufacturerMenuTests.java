package Tests;
import Base.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;


public class ManufacturerMenuTests extends BaseTest {
    // działa wszystko na 1 run

    @Test //Działa
    public void SortingPriceAscending() throws InterruptedException {
        mainpage.clickOnSaleBanner();
        mainpage.mouseHoverOnChildCategory();
        manufacturerPage=mainpage.clickOnSneakearsCategoryOnDropDownList();
        manufacturerPage.clickPriceFilter();
        manufacturerPage.chooseWayOfSort("Najniższa cena");
        Thread.sleep(2000);
        mainpage.closeNewsLetter();
        Assert.assertTrue(manufacturerPage.ascendingCheck(manufacturerPage.getSortedPrices()));

    }
   @Test //Działa
    public void SortingPriceDescending() throws InterruptedException {
        mainpage.clickOnSaleBanner();
        mainpage.mouseHoverOnChildCategory();
        manufacturerPage=mainpage.clickOnSneakearsCategoryOnDropDownList();
        manufacturerPage.clickPriceFilter();
        manufacturerPage.chooseWayOfSort("Najwyższa cena");
        Thread.sleep(2000);
        mainpage.closeNewsLetter();
        Assert.assertTrue(manufacturerPage.descendingCheck(manufacturerPage.getSortedPrices()));

    }


    @Test //działa
    public void setPriceRangeBarOnLeftSide() {
        mainpage.clickOnSaleBanner();
        mainpage.mouseHoverOnMenCategory();
        manufacturerPage = mainpage.clickOnTrampkiCategoryOnDropDownList();
        mainpage.clickOnSaleBanner();

        manufacturerPage.setMaxAndMinPrice("200", "300");
        mainpage.clickOnSaleBanner();

        Assert.assertTrue(manufacturerPage.checkPriceHandlerHasBeenMovedToRequestedPriceRange("200.0", "300.0"));
        mainpage.closeNewsLetter();

        manufacturerPage.clickPriceFilterButton();
        Assert.assertTrue("Prices after handler changes are not correct", manufacturerPage.checkIfPAllProductsPricesAreInRequestedRange(200.0, 300.0, manufacturerPage.getPricesFromWebsite()));
    }

    @Test //done
    public void setWidthRangeBatonLeftSide()  {
        mainpage.clickOnSaleBanner();
        mainpage.mouseHoverOnMenCategory();
        manufacturerPage = mainpage.clickOnTrampkiCategoryOnDropDownList();

        mainpage.clickOnSaleBanner();
        manufacturerPage.setWidthOfShoes("Standardowy", "Szeroki");

        Assert.assertTrue(manufacturerPage.verifyElementExistInList(manufacturerPage.getListOfFilterSummarry(),"Standardowy"));
        Assert.assertTrue(manufacturerPage.verifyElementExistInList(manufacturerPage.getListOfFilterSummarry(),"Szeroki"));

    }

    @Test //działa

    public void setNewLabels()  {
        mainpage.clickOnSaleBanner();
        mainpage.mouseHoverOnMenCategory();
        manufacturerPage = mainpage.clickOnTrampkiCategoryOnDropDownList();

        mainpage.clickOnSaleBanner();
        manufacturerPage.clickOnNewLebel();

        Assert.assertTrue("New lebel is not available on every product", manufacturerPage.featuresAreDisplayed());

    }


    @Test //done //działa
    public void SearchingVansNumber40ByTopMarkTabWomen() {

        mainpage.clickOnSaleBanner();
        manufacturerPage = mainpage.selectTopMark("Vans");
        manufacturerPage.chooseSexCategory("Damskie");

        mainpage.closeNewsLetter();

        manufacturerPage.
                chooseModel("Sportowe").
                chooseStyle("Lifestyle").
                chooseSize("40");

        Assert.assertTrue(manufacturerPage.ListAreEqual(Arrays.asList("Damskie", "Sportowe", "Lifestyle"), manufacturerPage.convertWebElementsListToString(manufacturerPage.getFilterHomeTop())));
        Assert.assertEquals("value filter is not correct", "40", manufacturerPage.getSizeValue("40"));
    }

    @Test //działa
    public void SearchingVansNumber42BySearchBoxMen() {
        mainpage.clickOnSaleBanner();
        manufacturerPage = mainpage.typeInSearchBox("Vans");
        manufacturerPage.chooseSexCategory("Męskie");
        mainpage.clickOnSaleBanner();
        mainpage.closeNewsLetter();
        manufacturerPage.chooseModel("Sportowe");
        mainpage.clickOnSaleBanner();
        manufacturerPage.
                chooseStyle("Trampki").
                chooseSize("42");

        Assert.assertTrue(manufacturerPage.ListAreEqual(Arrays.asList("Męskie", "Sportowe", "Trampki"), manufacturerPage.convertWebElementsListToString(manufacturerPage.getFilterHomeTop())));
        Assert.assertEquals("value filter is not correct", "42", manufacturerPage.getSizeValue("42"));

    }

    @Test //działa
    public void SearchingVansNumber42ByHeaders() {
        mainpage.clickOnSaleBanner();
        mainpage.mouseHoverOnMenCategory();
        manufacturerPage = mainpage.clickOnTrampkiCategoryOnDropDownList();
        mainpage.clickOnSaleBanner();
        manufacturerPage.
                chooseSize("42");
        mainpage.closeNewsLetter();
        manufacturerPage.searchManufacturer("vans");

        Assert.assertTrue(manufacturerPage.ListAreEqual(Arrays.asList("Męskie", "Półbuty", "Trampki"), manufacturerPage.convertWebElementsListToString(manufacturerPage.getFilterHomeTop())));
        Assert.assertEquals("value filter is not correct", "42", manufacturerPage.getSizeValue("42"));
        Assert.assertEquals("Vans - buty i akcesoria", manufacturerPage.getManufacturerTittle());

    }


}
