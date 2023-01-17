package Tests;
import Base.BaseTest;
import org.junit.Assert;
import org.junit.Test;


public class FavouritePageTests extends BaseTest {

    @Test //done /działa
    public void addProductsToFavouriteAndDelete() throws InterruptedException {

        //First
        mainpage.mouseHoverOnMenCategory();
        manufacturerPage= mainpage.clickOnSztybletyDropDownList();
        mainpage.clickOnSaleBanner();
        productPage= manufacturerPage.chooseProduct("2", "Value1");
      //  manufacturerPage.clickOnSaleBanner();
        mainpage.CloseNewsLetter();

        productPage.
                AddToFavourite().
                clickonFavouriteHeaders();

        //Second
        mainpage.mouseHoverOnMenCategory();
        manufacturerPage=  mainpage.clickOnSztybletyDropDownList();

        productPage= manufacturerPage.chooseProduct("4", "Value2");

        favouritePage= productPage.
                AddToFavourite().
                clickonFavouriteHeaders();

        favouritePage.RemoveFavourites();

        Assert.assertEquals(favouritePage.GetValueFromFavouriteLabel(),"Ulubione (0)");

    }




}
