package Tests;
import Base.BaseTest;
import Interfaces.Buffer;
import Interfaces.IHelper;
import Pages.BasketPage;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class BasketTests extends BaseTest implements IHelper {

    @Test //done
    public void TwoProductToBasketCheckSummary() {
        mainpage.clickOnSaleBanner();
        mainpage.clickOnSaleBanner();
        mainpage.
                mouseHoverOnMenCategory();

        manufacturerPage= mainpage.clickOnSztybletyDropDownList();

        mainpage.clickOnSaleBanner();
        manufacturerPage.chooseSize("42");
        mainpage.clickOnSaleBanner();

        mainpage.closeNewsLetter();

        productPage= manufacturerPage.
                chooseProduct("1","Value1");


        Assert.assertTrue(productPage.getCurrentProductPrice("1").equals(Buffer.GetValueBufferKey("Value1Regular")) || productPage.getCurrentProductPrice("1").equals(Buffer.GetValueBufferKey("Value1Special")));
        Assert.assertTrue(verifyElementsAreDisplayed(productPage.getProductAvailability(), productPage.getFavouriteButton(), productPage.getAddToBasketButton(), productPage.getFreeSendAndReturnTooltip()));

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


        Assert.assertTrue(productPage.getCurrentProductPrice("2").equals(Buffer.GetValueBufferKey("Value2Regular"))|| productPage.getCurrentProductPrice("2").equals(Buffer.GetValueBufferKey("Value2Special")));
        Assert.assertTrue(verifyElementsAreDisplayed(productPage.getProductAvailability(), productPage.getFavouriteButton(), productPage.getAddToBasketButton(), productPage.getFreeSendAndReturnTooltip()));

        productPage.clickOnAddToBasket().
                chooseSizeFromRightList("42");

        basketPage=productPage.goToBasket();

        Assert.assertEquals(Double.doubleToLongBits(basketPage.getSummedValueOfProducts()), basketPage.getSummaryOfPriceProducts(),BasketPage.DELTA);

    }
    @Test //done
    public void checkAddingProductToBasket() {
        mainpage.clickOnSaleBanner();
        mainpage.
                mouseHoverOnChildCategory();
        manufacturerPage=mainpage.clickOnSneakearsCategoryOnDropDownList();
        mainpage.clickOnSaleBanner();
        productPage =manufacturerPage.chooseProduct("3","Value1");

        Assert.assertTrue(productPage.getCurrentProductPrice("1").equals(Buffer.GetValueBufferKey("Value1Regular")) ||productPage.getCurrentProductPrice("1").equals(Buffer.GetValueBufferKey("Value1Special")));
        Assert.assertTrue(verifyElementsAreDisplayed(productPage.getProductAvailability(), productPage.getFavouriteButton(), productPage.getAddToBasketButton(), productPage.getFreeSendAndReturnTooltip()));

        mainpage.closeNewsLetter();

        productPage.
                clickOnAddToBasket().clickRandomSize();
                basketPage= productPage.goToBasket();


    }


    @Override
    public WebDriver GetDriver() {
        return this.driver;
    }
}
