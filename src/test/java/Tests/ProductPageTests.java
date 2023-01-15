package Tests;

import Base.BaseTest;
import org.junit.Test;

public class ProductPageTests extends BaseTest {


    @Test //działa
    public void checkStoreAvailability() throws InterruptedException {


        mainpage.mouseHoveronChildCategory();
        manufacturerPage=mainpage.clickOnSneakearsCategoryOnDropDownList();
        manufacturerPage.clickOnSaleBanner();
        productPage= manufacturerPage.chooseProduct("10","Value1");
        productPage.verifyLayoutOnProductCard();
        mainpage.CloseNewsLetter();

        productPage.
                clickonStoreAvailability().
                chooseSizeFromRightList("36").
                checkStoreAvailability();
    }
}
