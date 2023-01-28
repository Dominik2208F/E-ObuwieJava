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
        mainpage.CloseNewsLetter();

        productPage.
                AddToFavourite().
                clickOnFavouriteHeaders();

        //Second
        mainpage.mouseHoverOnMenCategory();
        manufacturerPage=  mainpage.clickOnSztybletyDropDownList();

        productPage= manufacturerPage.chooseProduct("4", "Value2");

        favouritePage= productPage.
                AddToFavourite().
                clickOnFavouriteHeaders();

        favouritePage.RemoveFavourites();

        Assert.assertEquals(favouritePage.GetValueFromFavouriteLabel(),"Ulubione (0)");

    }

    @Test
    public void addProductsFromFavouriteSection(){

        //First
        mainpage.clickOnSaleBanner();
        mainpage.mouseHoverOnMenCategory();
        manufacturerPage= mainpage.clickOnSztybletyDropDownList();
        mainpage.clickOnSaleBanner();
        productPage= manufacturerPage.chooseProduct("2", "Value1");
        mainpage.CloseNewsLetter();

        productPage.
                AddToFavourite().
                clickOnFavouriteHeaders();

        //Second
        mainpage.mouseHoverOnMenCategory();
        manufacturerPage=  mainpage.clickOnSztybletyDropDownList();

        productPage= manufacturerPage.chooseProduct("4", "Value2");

        favouritePage= productPage.
                AddToFavourite().
                clickOnFavouriteHeaders();

        Assert.assertEquals(favouritePage.GetValueFromFavouriteLabel(),"Ulubione (2)");

    }

}
