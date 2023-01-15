package Tests;

import Base.BaseTest;
import org.junit.Test;

public class ManufacturerMenuTests extends BaseTest {

    @Test //done //działa ale baton coś nie klika
    public void setPriceRangeBarOnLeftSide() throws InterruptedException {

        mainpage.mouseHoverOnMenCategory();
        manufacturerPage= mainpage.clickOnTrampkiCategoryOnDropDownList();
        manufacturerPage.clickOnSaleBanner().
                setMaxAndMinPrice("200","300").
                clickOnSaleBanner().
                checkPriceHandlerHasBeenMovedToRequestedPriceRange("200.0","300.0").
                clickPriceFilterButton().
                checkIfPricesHaveBeenNarowedAfterFilter(200.0,300.0);
    }

    @Test //done
    public void setkWidthRangeBaronLeftSide() throws InterruptedException {

        mainpage.mouseHoverOnMenCategory();
        manufacturerPage= mainpage.clickOnTrampkiCategoryOnDropDownList();

        manufacturerPage.
                clickOnSaleBanner().
                setWidthOfShoes("Standardowy","Szeroki");

    }

    @Test //działa

    public void setNewLebel() throws InterruptedException {
        mainpage.mouseHoverOnMenCategory();
        manufacturerPage= mainpage.clickOnTrampkiCategoryOnDropDownList();

        manufacturerPage.
                clickOnSaleBanner().
                clickOnNewLebel().
                checkifNewLebelisDisplayedOnEveryProduct();
    }



    @Test //done // nie działą z innego powodu
    public void lookForManVansNumber40ByTopMarkTab()  {

       manufacturerPage= mainpage.selectTopMark("Vans");

        manufacturerPage.
                clickOnSaleBanner().
                chooseSexCategory("Damskie");

        mainpage.CloseNewsLetter();

        manufacturerPage.
                chooseModel("Sportowe").
                chooseStyle("Lifestyle").
                chooseSize("40").
                verifyFilterLabel("Damskie","Sportowe","Lifestyle").
                verifySizeValueFIlter("40");
    }

    @Test //działa
    public void lookForManVansNumber42BySearchBox()  {

        manufacturerPage= mainpage.typeInSearchBox("Vans");
        manufacturerPage.clickOnSaleBanner();
        manufacturerPage.chooseSexCategory("Męskie");
        // manufacturerPage.clickOnSaleBanner();
        mainpage.CloseNewsLetter();
        manufacturerPage.chooseModel("Sportowe");
        manufacturerPage.clickOnSaleBanner();
        manufacturerPage.chooseStyle("Trampki").
                chooseSize("42").
                verifyFilterLabel("Męskie","Sportowe","Trampki").
                verifySizeValueFIlter("42");

    }
    @Test //działa
    public void lookForManVansNumber42ByHeaders() throws InterruptedException {

        mainpage.mouseHoverOnMenCategory();
        manufacturerPage=mainpage.clickOnTrampkiCategoryOnDropDownList();
        manufacturerPage.clickOnSaleBanner();
        manufacturerPage.
                chooseSize("42");
        mainpage.CloseNewsLetter();
        manufacturerPage.searchManufacturer("vans").
                verifyFilterLabel("Męskie","Półbuty","Trampki").
                verifySizeValueFIlter("42").
                verifyTitlleOfManufacturer();
    }


}
