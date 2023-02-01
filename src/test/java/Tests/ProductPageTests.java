package Tests;
import Base.BaseTest;
import Interfaces.IHelper;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

public class ProductPageTests extends BaseTest implements IHelper {

    @Test
    public void checkStoreAvailability(){

        mainpage.clickOnSaleBanner();
        mainpage.mouseHoverOnChildCategory();
        manufacturerPage=mainpage.clickOnSneakearsCategoryOnDropDownList();
        mainpage.clickOnSaleBanner();
        productPage= manufacturerPage.chooseProduct("11","Value1");
        mainpage.closeNewsLetter();

        productPage.
                clickOnStoreAvailability().clickRandomSize();

        Assert.assertTrue("Elements is not displayed",productPage.getStoreAvailability().isDisplayed());

    }

    @Test
    public void checkVisibilityOfButtonsOnProductCard(){
        mainpage.clickOnSaleBanner();
        mainpage.mouseHoverOnChildCategory();
        manufacturerPage=mainpage.clickOnSneakearsCategoryOnDropDownList();
        mainpage.clickOnSaleBanner();
        productPage= manufacturerPage.chooseProduct("15","Value1");
        mainpage.closeNewsLetter();

        Assert.assertTrue(verifyElementsAreDisplayed(productPage.getProductAvailability(), productPage.getFavouriteButton(), productPage.getAddToBasketButton(), productPage.getFreeSendAndReturnTooltip()));

    }

    @Test
    public void checkImagesDisplayedOnProductPage()  {
        mainpage.clickOnSaleBanner();
        mainpage.mouseHoverOnChildCategory();
        manufacturerPage=mainpage.clickOnSneakearsCategoryOnDropDownList();
        mainpage.clickOnSaleBanner();
        productPage= manufacturerPage.chooseProduct("15","Value1");
        mainpage.closeNewsLetter();
        Assert.assertTrue(checkIfElementsSizeIsMoreThan0(productPage.getImage()));
    }

    @Test
    public void checkPaymentMethodToolTip() {
        mainpage.clickOnSaleBanner();
        mainpage.mouseHoverOnChildCategory();
        manufacturerPage=mainpage.clickOnSneakearsCategoryOnDropDownList();
        mainpage.clickOnSaleBanner();
        productPage= manufacturerPage.chooseProduct("26","Value1");
        mainpage.closeNewsLetter();
        productPage.clickOnPaymentTooltipButton();

        Assert.assertTrue(mainpage.checkIfListContainsAllExpectedElements(productPage.getListOfPaymentMethods(), Arrays.asList("Szybki przelew", "Blik", "Płatne przy odbiorze", "PayPal", "PayPo", "Przelew bankowy")));
    }


    @Test
    public void checkCommentsSectionIsDisplayed(){
        mainpage.clickOnSaleBanner();
        mainpage.mouseHoverOnChildCategory();
        manufacturerPage=mainpage.clickOnSneakearsCategoryOnDropDownList();
        mainpage.clickOnSaleBanner();
        productPage= manufacturerPage.chooseProduct("26","Value1");
        mainpage.closeNewsLetter();
        Assert.assertTrue(verifyElementsFromListAreDisplayed(productPage.getListOfCommnents()));
    }



//interface override to methods
    @Override
    public boolean checkIfElementsSizeIsMoreThan0(List<WebElement> x) {
        return IHelper.super.checkIfElementsSizeIsMoreThan0(x);
    }

    @Override
    public WebDriver GetDriver() {
        return super.driver;
    }
}
