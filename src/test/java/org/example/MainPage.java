package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;


public class MainPage implements IHelper {


    public WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
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

    public void headersVerification() throws InterruptedException {
        Thread.sleep(5000);
        List<String> ExpectedHeaders = Arrays.asList("NOWOŚCI", "DAMSKIE", "MĘSKIE", "DZIECIĘCE", "SPORT", "AKCESORIA", "PREMIUM", "TOREBKI", "WYPRZEDAŻ");

        checkIfListContainsAllExpectedElements(ActualHeadersPath,ExpectedHeaders);
    }
    public void verifyList( String expectedValue){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfAllElements(ActualHeadersPath));
        verifyElementsInListEquals(ActualHeadersPath, expectedValue);
        clickEqualsListElement(ActualHeadersPath,expectedValue);
    }
    public void acceptCookies() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@id='mega-menu-list']/li")));
        clickIn(AcceptCookiesElement,5);
    }
    public void selectTopMark(String mark)  {
     // Thread.sleep(5000);
        try {

            for (WebElement x : TopMarksList) {
                if (mark.equals(x.getAttribute("alt"))) {
                    Actions actions = new Actions(driver);
                    actions.moveToElement(x).click().perform();
                }
            }
        } catch (StaleElementReferenceException ex) {
            System.out.println("Exception Złapany");

        }
    }
    public void typeInSearchBox(String value)  {
        try{
        setValueInField(Manufacturer,value);
        clickIn(SearchButton);}
        catch(Exception e){

        }
    }
    public void mouseHoverOnMenCategory() throws InterruptedException {
        Thread.sleep(3000);
        MouseHover(MensLabelHeaders);
    }
    public void clickOnTrampkiCategoryOnDropDownList(){
        ClickOnDropDownList(TrampkiChooseDropDown);
    }
    public void clickOnSneakearsCategoryOnDropDownList(){
        ClickOnDropDownList(SneakearsChild);
    }
    public void mouseHoveronChildCategory() throws InterruptedException {

        Thread.sleep(2000);
        MouseHover(ChildLabelHeaders);
    }
    public void clickOnSztybletyDropDownList() {

        ClickOnDropDownList(SztybletyChooseDropDown);


    }

    public void CloseNewsLetter(){
        if(popup.isDisplayed()) {
            popup.click();
        }
    }

}








