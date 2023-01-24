package Pages;
import Base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;

public class ContactPage extends BasePage {
    public ContactPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }



    @FindBy(xpath="//button[@class='accordion']//span[contains(text(),'Jak zwrócić produkt')]")
    private WebElement HowToSendBackProduct;

    @FindBy(xpath="//button[@class='accordion']//span[contains(text(),'Termin dostawy')]")
    private WebElement TermOfDelivery;

    @FindBy(xpath="//button[@class='accordion']//span[contains(text(),'Zwrot środków')]")
    private WebElement Payback;

    @FindBy(xpath="//button[@class='accordion']//span[contains(text(),'Anulowanie zamówienia')]")
    private WebElement OrderCancel;

    @FindBy(xpath="//button[@class='accordion']//span[contains(text(),'Reklamacja')]")
    private WebElement Complain;

    @FindBy(xpath=" //button[@class='accordion']//span[contains(text(),'Odbiór zamówienia w sklepie stacjonarnym')]")
    private WebElement ShopPickUp;

    @FindBy(xpath="//div[@class='contact-wrapper-left']/section[1]/div[1]//div[@class='panel']")
    private List<WebElement> InformationPanels;

    @FindBy(xpath="//a[@onclick='startChatBot()']")
    private WebElement StartChatButton;
    @FindBy(xpath="//div[@id='zowieFloatingButton']")
    private WebElement ChatButtonIkon;


    public List<WebElement> getInformationPanels(){

        return InformationPanels;
    }
    public ContactPage clickOnSendProduct(){

        HowToSendBackProduct.click();

        return this;
    }
    public ContactPage clickOnTermOfDelivery(){

        TermOfDelivery.click();

        return this;
    }
    public ContactPage clickOnPayback(){

        Payback.click();

        return this;
    }
    public ContactPage clickOnOrderCancel(){

        OrderCancel.click();

        return this;
    }
    public ContactPage clickOnComplain(){

        Complain.click();

        return this;
    }
    public ContactPage clickOnShopPickUp(){

        ShopPickUp.click();

        return this;
    }
    public ContactPage clickOnChatButton(){

        StartChatButton.click();
        ChatButtonIkon.click();
        return this;
    }












}
