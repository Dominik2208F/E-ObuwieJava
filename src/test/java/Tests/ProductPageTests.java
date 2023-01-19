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


        mainpage.mouseHoveronChildCategory();
        manufacturerPage=mainpage.clickOnSneakearsCategoryOnDropDownList();
        mainpage.clickOnSaleBanner();
        productPage= manufacturerPage.chooseProduct("11","Value1");
        mainpage.CloseNewsLetter();

        productPage.
                clickOnStoreAvailability().
                chooseSizeFromRightList("36");

        Assert.assertTrue("Elements is not displayed",productPage.GetStoreAvailability().isDisplayed());

    }

    @Test
    public void checkVisibilityOfButtonsOnProductCard(){

        mainpage.mouseHoveronChildCategory();
        manufacturerPage=mainpage.clickOnSneakearsCategoryOnDropDownList();
        mainpage.clickOnSaleBanner();
        productPage= manufacturerPage.chooseProduct("15","Value1");
        mainpage.CloseNewsLetter();

        Assert.assertTrue(verifyElementsAreDisplayed(productPage.getProductAvailability(), productPage.getFavouriteButton(), productPage.getAddToBasketButton(), productPage.getFreeSendandReturnTooltip()));

    }

    @Test
    public void checkImagesDisplayedOnProductPage()  {

        mainpage.mouseHoveronChildCategory();
        manufacturerPage=mainpage.clickOnSneakearsCategoryOnDropDownList();
        mainpage.clickOnSaleBanner();
        productPage= manufacturerPage.chooseProduct("15","Value1");
        mainpage.CloseNewsLetter();
        Assert.assertTrue(checkifElementsSizeIsMoreThan0(productPage.getImage()));
    }

    @Test
    public void checkPaymentMethodToolTip() {

        mainpage.mouseHoveronChildCategory();
        manufacturerPage=mainpage.clickOnSneakearsCategoryOnDropDownList();
        mainpage.clickOnSaleBanner();
        productPage= manufacturerPage.chooseProduct("26","Value1");
        mainpage.CloseNewsLetter();
        productPage.clickOnPaymentTooltipButton();

        Assert.assertTrue(mainpage.checkIfListContainsAllExpectedElements(productPage.getListOfPaymentMethods(), Arrays.asList("Szybki przelew", "Blik", "Płatne przy odbiorze", "PayPal", "PayPo", "Przelew bankowy")));
    }


    @Test
    public void checkCommentsSectionIsDisplayed(){

        mainpage.mouseHoveronChildCategory();
        manufacturerPage=mainpage.clickOnSneakearsCategoryOnDropDownList();
        mainpage.clickOnSaleBanner();
        productPage= manufacturerPage.chooseProduct("26","Value1");
        mainpage.CloseNewsLetter();
        Assert.assertTrue(verifyElementsFromListAreDisplayed(productPage.getListOfCommnents()));
    }



//interface override to methods
    @Override
    public boolean checkifElementsSizeIsMoreThan0(List<WebElement> x) {
        return IHelper.super.checkifElementsSizeIsMoreThan0(x);
    }

    @Override
    public WebDriver GetDriver() {
        return super.driver;
    }
}
