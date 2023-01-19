package Tests;
import Base.BaseTest;
import Interfaces.Buffer;
import Interfaces.IHelper;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class BasketTests extends BaseTest implements IHelper {

    @Test //done
    public void TwoProductToBasketCheckSummary() {
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
        Assert.assertTrue(verifyElementsAreDisplayed(productPage.getProductAvailability(), productPage.getFavouriteButton(), productPage.getAddToBasketButton(), productPage.getFreeSendandReturnTooltip()));

                   productPage.clickOnAddToBasket().
                    chooseSizeFromRightList("42");

        basketPage= productPage.goToBasket();
        mainpage =basketPage.returnToMainPageFromBasket();

        //First Product in Basket

        mainpage.
                mouseHoverOnMenCategory().
                clickOnSztybletyDropDownList();
        manufacturerPage.chooseSize("42");
        mainpage.clickOnSaleBanner();

        productPage= manufacturerPage.
                chooseProduct("2","Value2");


        Assert.assertEquals(productPage.GetCurrentProductPrice(), Buffer.GetValueBufferKey("Value2"));
        Assert.assertTrue(verifyElementsAreDisplayed(productPage.getProductAvailability(), productPage.getFavouriteButton(), productPage.getAddToBasketButton(), productPage.getFreeSendandReturnTooltip()));

        productPage.clickOnAddToBasket().
                chooseSizeFromRightList("42");

        basketPage=productPage.goToBasket();
        basketPage.compareSumInBasketWithLabelPrice();

    }
    @Test //done
    public void checkAddingProductToBasket() {
        mainpage.
                mouseHoverOnChildCategory();

        manufacturerPage=mainpage.clickOnSneakearsCategoryOnDropDownList();

        productPage =manufacturerPage.chooseProduct("3","Value1");

        Assert.assertEquals(productPage.GetCurrentProductPrice(), Buffer.GetValueBufferKey("Value1"));
        Assert.assertTrue(verifyElementsAreDisplayed(productPage.getProductAvailability(), productPage.getFavouriteButton(), productPage.getAddToBasketButton(), productPage.getFreeSendandReturnTooltip()));

        mainpage.CloseNewsLetter();

        productPage.
                clickOnAddToBasket().
                chooseSizeFromRightList("32");
        basketPage= productPage.goToBasket();


    }


    @Override
    public WebDriver GetDriver() {
        return this.driver;
    }
}
