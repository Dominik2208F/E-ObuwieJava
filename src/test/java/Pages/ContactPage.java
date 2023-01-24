package Pages;
import Base.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
    public ContactPage clickOnSendProduct() throws InterruptedException {

        HowToSendBackProduct.click();
        wait.until(ExpectedConditions.visibilityOf(TermOfDelivery));
        return this;
    }
    public ContactPage clickOnTermOfDelivery() throws InterruptedException {
        TermOfDelivery.click();
        wait.until(ExpectedConditions.visibilityOf(Payback));
        return this;
    }
    public ContactPage clickOnPayback() throws InterruptedException {

        Payback.click();
        wait.until(ExpectedConditions.visibilityOf(OrderCancel));
        return this;
    }
    public ContactPage clickOnOrderCancel() throws InterruptedException {
        OrderCancel.click();
        wait.until(ExpectedConditions.visibilityOf(Complain));
        return this;
    }
    public ContactPage clickOnComplain() throws InterruptedException {

        Complain.click();
        wait.until(ExpectedConditions.visibilityOf(ShopPickUp));
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
