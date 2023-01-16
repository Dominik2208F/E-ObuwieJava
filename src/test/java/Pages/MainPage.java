package Pages;
import Base.BasePage;
import Interfaces.IHelper;
import jdk.jfr.internal.tool.Main;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;


public class MainPage extends BasePage implements IHelper {


    public MainPage(WebDriver driver) {
        super(driver);

    }

    public WebDriver GetDriver(){
        return driver;
    }

    @FindBy(xpath = "//button[contains(text(),'Zgoda')]")
    private WebElement AcceptCookiesElement;
    @FindBy(xpath = "//ul[@id='mega-menu-list']/li")
    private List<WebElement> ActualHeadersPath;
    @FindBy(xpath = "//a[@class='brands-section__slide']/child::img")
    private List<WebElement> TopMarksList;
    @FindBy(xpath = "//form[@class='header-search snr']/child::input")
    private WebElement Manufacturer;
    @FindBy(xpath="//form[@class='header-search snr']//button[@title='Szukaj']")
    private WebElement SearchButton;
    @FindBy(xpath="//ul[@class='sidebar-section__list filter-link__list']/child::li/a")
    private List<WebElement> LeftFilterCategory;
    @FindBy(xpath="//ul[@class='sidebar-section__list filter-link__list']/li/a")
    private List<WebElement> LeftFilterCategoryModel;
    @FindBy(xpath="//a[@class='e-mega-menu__item-link'][contains(text(),'Męskie')]")
    private WebElement MensLabelHeaders;
    @FindBy( xpath = "//div[@class='e-mega-menu-dropdown']//div[@data-gtm-label='Buty do kostki']//child::ul/li/a[@data-gtm-label='Trampki']")
    private WebElement TrampkiChooseDropDown;
    @FindBy(xpath= "//div[@data-gtm-label='Buty dziewczęce']/ul/li//a[@data-gtm-label='Sneakersy']")
    private WebElement SneakearsChild;
    @FindBy(xpath="//a[@class='e-mega-menu__item-link'][contains(text(),'Dziecięce')]")
    private WebElement ChildLabelHeaders;
    @FindBy(xpath="//a[contains(@href,'https://www.eobuwie.com.pl/meskie/kozaki-i-inne/sztyblety.html')]/parent::li")
    private WebElement SztybletyChooseDropDown;
    @FindBy(id="close-promo-popup")
    private WebElement popup;
    @FindBy(id = "newsletter_banner_exit_salomon")
    private WebElement Banner;

    public MainPage headersVerification(String... listaheaders) throws InterruptedException {
        Thread.sleep(5000);
        Arrays.asList( listaheaders);
        checkIfListContainsAllExpectedElements(ActualHeadersPath,Arrays.asList( listaheaders));
        return this;
    }
    public MainPage clickOnSaleBanner() {
        try {
            if (Banner.isEnabled()) {
                Banner.click();
            }
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Sales banner has not been displayed");
        }
        return this;
    }


    public void verifyList( String expectedValue){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfAllElements(ActualHeadersPath));
        verifyElementExistInList(ActualHeadersPath, expectedValue);
        clickEqualsListElement(ActualHeadersPath,expectedValue);
    }
    public void acceptCookies() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@id='mega-menu-list']/li")));
        clickIn(AcceptCookiesElement,5);
    }
    public ManufacturerPage selectTopMark(String mark)  {
     // Thread.sleep(5000);
        try {

            for (WebElement x : TopMarksList) {
                if (mark.equals(x.getAttribute("alt"))) {
                 //   ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", x);
                    Actions actions = new Actions(driver);
                    actions.moveToElement(x).click().perform();
                }
            }
        } catch (StaleElementReferenceException ex) {
            System.out.println("Top mark bar is not visible on page");

        }
        return new ManufacturerPage(driver);
    }
    public ManufacturerPage typeInSearchBox(String value)  {
        try{
        setValueInField(Manufacturer,value);
        clickIn(SearchButton);}
        catch(Exception e){

        }
        return new ManufacturerPage(driver);
    }
    public MainPage mouseHoverOnMenCategory() throws InterruptedException {
        Thread.sleep(3000);
        mouseHover(MensLabelHeaders);
        return this;
    }
    public ManufacturerPage clickOnTrampkiCategoryOnDropDownList(){
        clickOnDropDownList(TrampkiChooseDropDown);
        return new ManufacturerPage(driver);
    }
    public ManufacturerPage clickOnSneakearsCategoryOnDropDownList(){
        clickOnDropDownList(SneakearsChild);
        return new ManufacturerPage(driver);
    }
    public MainPage mouseHoveronChildCategory() throws InterruptedException {

        Thread.sleep(2000);
        mouseHover(ChildLabelHeaders);
        return this;
    }
    public ManufacturerPage clickOnSztybletyDropDownList() {

        clickOnDropDownList(SztybletyChooseDropDown);
     return new ManufacturerPage(driver);

    }

    public void CloseNewsLetter(){
        if(popup.isDisplayed()) {
            popup.click();
        }
    }

}








