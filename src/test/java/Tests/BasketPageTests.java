package Tests;
import Base.BaseTest;
import Pages.ManufacturerPage;
import org.junit.Test;

public class BasketPageTests extends BaseTest {

    @Test //done
    public void addTwoRandomProductToBasketAndCheckSumValueInSummary() throws InterruptedException {
        mainpage.
                mouseHoverOnMenCategory();

        manufacturerPage= mainpage.clickOnSztybletyDropDownList();

        manufacturerPage.
                clickOnSaleBanner().
                chooseSize("42").
                clickOnSaleBanner();

        mainpage.CloseNewsLetter();

        productPage= manufacturerPage.
                chooseProduct("1","Value1").
                checkPriceOnProductCard("Value1").
                verifyLayoutOnProductCard().
                clickOnAddToBasket().
                chooseSizeFromRightList("42");

        basketPage= productPage.gotoBasket();
        mainpage =basketPage.returnToMainpageFromBasket();
        // mainpage.CloseNewsLetter();

        //First Product in Basket

        mainpage.
                mouseHoverOnMenCategory().
                clickOnSztybletyDropDownList();
        manufacturerPage.
                chooseSize("42").
                clickOnSaleBanner();

        productPage= manufacturerPage.
                chooseProduct("2","Value2").
                checkPriceOnProductCard("Value2").
                verifyLayoutOnProductCard().
                clickOnAddToBasket().
                chooseSizeFromRightList("42");

        basketPage=productPage.gotoBasket();
        basketPage.compareSumInBasketWithLabelPrice();

    }
    @Test //done
    public void checkAddingProductToBasket() throws InterruptedException {
        mainpage.
                mouseHoveronChildCategory();

        manufacturerPage=mainpage. clickOnSneakearsCategoryOnDropDownList();

        productPage =manufacturerPage.chooseProduct("3","Value1");
        productPage.
                checkPriceOnProductCard("Value1").
                verifyLayoutOnProductCard();

        mainpage.CloseNewsLetter();

        productPage.
                clickOnAddToBasket().
                chooseSizeFromRightList("32");
        basketPage= productPage.gotoBasket();


    }


}
