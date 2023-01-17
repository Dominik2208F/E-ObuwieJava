package Tests;
import Base.BaseTest;
import Interfaces.Buffer;
import org.junit.Assert;
import org.junit.Test;

public class BasketPageTests extends BaseTest {

    @Test //done
    public void addTwoRandomProductToBasketAndCheckSumValueInSummary() throws InterruptedException {
        mainpage.
                mouseHoverOnMenCategory();

        manufacturerPage= mainpage.clickOnSztybletyDropDownList();

        mainpage.clickOnSaleBanner();
        manufacturerPage.chooseSize("42");
        mainpage.clickOnSaleBanner();

        mainpage.CloseNewsLetter();

        productPage= manufacturerPage.
                chooseProduct("1","Value1");


        Assert.assertEquals(productPage.GetCurrentProductPrice(), Buffer.GetValueBufferKey("Value1"));

        productPage.verifyLayoutOnProductCard().
                    clickOnAddToBasket().
                    chooseSizeFromRightList("42");

        basketPage= productPage.gotoBasket();
        mainpage =basketPage.returnToMainpageFromBasket();
        // mainpage.CloseNewsLetter();

        //First Product in Basket

        mainpage.
                mouseHoverOnMenCategory().
                clickOnSztybletyDropDownList();
        manufacturerPage.chooseSize("42");
        mainpage.clickOnSaleBanner();

        productPage= manufacturerPage.
                chooseProduct("2","Value2");


        Assert.assertEquals(productPage.GetCurrentProductPrice(), Buffer.GetValueBufferKey("Value2"));

           productPage.verifyLayoutOnProductCard().
                clickOnAddToBasket().
                chooseSizeFromRightList("42");

        basketPage=productPage.gotoBasket();
        basketPage.compareSumInBasketWithLabelPrice();

    }
    @Test //done
    public void checkAddingProductToBasket() throws InterruptedException {
        mainpage.
                mouseHoveronChildCategory();

        manufacturerPage=mainpage.clickOnSneakearsCategoryOnDropDownList();

        productPage =manufacturerPage.chooseProduct("3","Value1");

        Assert.assertEquals(productPage.GetCurrentProductPrice(), Buffer.GetValueBufferKey("Value1"));
        productPage.
                verifyLayoutOnProductCard();

        mainpage.CloseNewsLetter();

        productPage.
                clickOnAddToBasket().
                chooseSizeFromRightList("32");
        basketPage= productPage.gotoBasket();


    }


}
