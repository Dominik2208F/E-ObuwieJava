package Tests;
import Base.BaseTest;
import Interfaces.IHelper;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductPageTests extends BaseTest implements IHelper {

    @Test
    public void checkStoreAvailability() throws InterruptedException {


        mainpage.mouseHoveronChildCategory();
        manufacturerPage=mainpage.clickOnSneakearsCategoryOnDropDownList();
        mainpage.clickOnSaleBanner();
        productPage= manufacturerPage.chooseProduct("10","Value1");
        mainpage.CloseNewsLetter();

        productPage.
                clickonStoreAvailability().
                chooseSizeFromRightList("36");

        Assert.assertTrue("Elements is not displayed",productPage.GetStoreAvailability().isDisplayed());

    }

    @Test
    public void checkLayoutOnProductCard() throws InterruptedException {

        mainpage.mouseHoveronChildCategory();
        manufacturerPage=mainpage.clickOnSneakearsCategoryOnDropDownList();
        mainpage.clickOnSaleBanner();
        productPage= manufacturerPage.chooseProduct("15","Value1");
        mainpage.CloseNewsLetter();

        Assert.assertTrue(productPage.verifyElementsAreDisplayed(productPage.getProductAvailability(), productPage.getFavouriteButton(), productPage.getAddToBasketButton(), productPage.getFreeSendandReturnTooltip()));

    }

    @Test
    public void checkIfImagesAreDisplayedOnProductPage() throws InterruptedException {

        mainpage.mouseHoveronChildCategory();
        manufacturerPage=mainpage.clickOnSneakearsCategoryOnDropDownList();
        mainpage.clickOnSaleBanner();
        productPage= manufacturerPage.chooseProduct("15","Value1");
        mainpage.CloseNewsLetter();
        Assert.assertTrue(checkifElementsSizeIsMoreThan0(productPage.getImage()));
    }


    @Override
    public boolean checkifElementsSizeIsMoreThan0(List<WebElement> x) {
        return IHelper.super.checkifElementsSizeIsMoreThan0(x);
    }

    @Override
    public WebDriver GetDriver() {
        return null;
    }
}
