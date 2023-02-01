package Pages;
import Base.BasePage;
import Interfaces.IHelper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ContactPage extends BasePage implements IHelper {
    public ContactPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }


    @FindBy(xpath = "//button[@class='accordion']//span[contains(text(),'Jak zwrócić produkt')]")
    private WebElement HowToSendBackProduct;

    @FindBy(xpath = "//button[@class='accordion']//span[contains(text(),'Termin dostawy')]")
    private WebElement TermOfDelivery;

    @FindBy(xpath = "//button[@class='accordion']//span[contains(text(),'Zwrot środków')]")
    private WebElement Payback;

    @FindBy(xpath = "//button[@class='accordion']//span[contains(text(),'Anulowanie zamówienia')]")
    private WebElement OrderCancel;

    @FindBy(xpath = "//button[@class='accordion']//span[contains(text(),'Reklamacja')]")
    private WebElement Complain;

    @FindBy(xpath = " //button[@class='accordion']//span[contains(text(),'Odbiór zamówienia w sklepie stacjonarnym')]")
    private WebElement ShopPickUp;

    @FindBy(xpath = "//div[@class='contact-wrapper-left']/section[1]/div[1]//div[@class='panel']")
    private List<WebElement> InformationPanels;

    @FindBy(xpath = "//a[@onclick='startChatBot()']")
    private WebElement StartChatButton;

    @FindBy(xpath = "//div[@id='zowieFloatingButton']")
    private WebElement ChatButtonIkon;

    @FindBy(xpath = "//textarea[@placeholder='Wpisz swoją wiadomość...']")
    private WebElement ChatPlaceholder;

    public List<WebElement> getInformationPanels() {

        return InformationPanels;
    }

    public ContactPage clickOnSendProduct() {

        HowToSendBackProduct.click();
        wait.until(ExpectedConditions.visibilityOf(TermOfDelivery));
        return this;
    }

    public ContactPage clickOnTermOfDelivery() {
        TermOfDelivery.click();
        wait.until(ExpectedConditions.visibilityOf(Payback));
        return this;
    }

    public ContactPage clickOnPayback() {

        Payback.click();
        wait.until(ExpectedConditions.visibilityOf(OrderCancel));
        return this;
    }

    public ContactPage clickOnOrderCancel() {
        OrderCancel.click();
        wait.until(ExpectedConditions.visibilityOf(Complain));
        return this;
    }

    public ContactPage clickOnComplain() {

        Complain.click();
        wait.until(ExpectedConditions.visibilityOf(ShopPickUp));
        return this;
    }

    public ContactPage clickOnShopPickUp() {

        ShopPickUp.click();
        return this;
    }

    public ContactPage clickOnChatButton() {
        StartChatButton.click();
        ChatButtonIkon.click();
        return this;
    }

    public ContactPage switchToIframe(String text) {

        swichToIframe(text);
        return this;
    }

    public ContactPage writeTextInChat(String text) {
        wait.until(ExpectedConditions.elementToBeClickable(ChatPlaceholder));
        ChatPlaceholder.sendKeys(text);
        return this;
    }

    public WebElement getChatPlaceholder() {

        return ChatPlaceholder;
    }

    @Override
    public WebDriver GetDriver() {
        return this.driver;
    }
}
