package Tests;
import Base.BaseTest;
import org.junit.Assert;
import org.junit.Test;


public class FavouriteSectionTests extends BaseTest {

    @Test //done /działa
    public void deleteProductsFromFavourite() {

        //First
        mainpage.clickOnSaleBanner();
        mainpage.mouseHoverOnMenCategory();
        manufacturerPage= mainpage.clickOnSztybletyDropDownList();
        mainpage.clickOnSaleBanner();
        productPage= manufacturerPage.chooseProduct("2", "Value1");
        mainpage.closeNewsLetter();

        productPage.
                addToFavourite().
                clickOnFavouriteHeaders();

        //Second
        mainpage.mouseHoverOnMenCategory();
        manufacturerPage=  mainpage.clickOnSztybletyDropDownList();

        productPage= manufacturerPage.chooseProduct("4", "Value2");

        favouritePage= productPage.
                addToFavourite().
                clickOnFavouriteHeaders();

        favouritePage.removeFavourites();

        Assert.assertEquals(favouritePage.getValueFromFavouriteLabel(),"Ulubione (0)");

    }

    @Test
    public void addProductsFromFavouriteSection(){

        //First
        mainpage.clickOnSaleBanner();
        mainpage.mouseHoverOnMenCategory();
        manufacturerPage= mainpage.clickOnSztybletyDropDownList();
        mainpage.clickOnSaleBanner();
        productPage= manufacturerPage.chooseProduct("2", "Value1");
        mainpage.closeNewsLetter();

        productPage.
                addToFavourite().
                clickOnFavouriteHeaders();

        //Second
        mainpage.mouseHoverOnMenCategory();
        manufacturerPage=  mainpage.clickOnSztybletyDropDownList();

        productPage= manufacturerPage.chooseProduct("4", "Value2");

        favouritePage= productPage.
                addToFavourite().
                clickOnFavouriteHeaders();

        Assert.assertEquals(favouritePage.getValueFromFavouriteLabel(),"Ulubione (2)");

    }

}
