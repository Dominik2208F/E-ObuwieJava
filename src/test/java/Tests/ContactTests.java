package Tests;
import Interfaces.IHelper;
import org.junit.Assert;
import org.junit.Test;
import Base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ContactTests extends BaseTest implements IHelper {


// works
    @Test
    public void contactDetailsVisibility() throws InterruptedException {

        mainpage.clickOnSaleBanner();
        contactPage=mainpage.clickOnContactButton();
        mainpage.clickOnSaleBanner();
        contactPage.
                clickOnSendProduct().
                clickOnTermOfDelivery().
                clickOnPayback().
                clickOnOrderCancel().
                clickOnComplain().
                clickOnShopPickUp();

        //verify if text under each dropdown list is displayed based on element attribute.
        Assert.assertTrue("Elements attributes haven't been changed to required state",verifyElementAttribute(contactPage.getInformationPanels(),"Style","max-height: 1000px;"));
    }

    @Test
    public void chatInteraction()  {

        mainpage.clickOnSaleBanner();
        contactPage=mainpage.clickOnContactButton();
        mainpage.clickOnSaleBanner();
        contactPage.
                clickOnChatButton().
                switchToIframe("chatbotize-iframe").
                writeTextInChat("Hello");
        Assert.assertEquals("Elements are not equal -actual should be Hello,but is " + contactPage.getChatPlaceholder().getText(),"Hello",contactPage.getChatPlaceholder().getText());

    }



    @Override
    public WebDriver GetDriver() {
        return this.driver;
    }
}
