package Tests;

import Base.BaseTest;
import Pages.ManufacturerPage;
import Pages.ProductPage;
import org.junit.Assert;
import org.junit.Test;

public class ProductPageTests extends BaseTest {




    @Test
    public void checkStoreAvailability() throws InterruptedException {


        mainpage.mouseHoveronChildCategory();
        manufacturerPage=mainpage.clickOnSneakearsCategoryOnDropDownList();
        mainpage.clickOnSaleBanner();
        productPage= manufacturerPage.chooseProduct("10","Value1");

        productPage.verifyLayoutOnProductCard();
        mainpage.CloseNewsLetter();

        productPage.
                clickonStoreAvailability().
                chooseSizeFromRightList("36");

        Assert.assertTrue("Elements is not displayed",productPage.GetStoreAvailability().isDisplayed());

    }
}
