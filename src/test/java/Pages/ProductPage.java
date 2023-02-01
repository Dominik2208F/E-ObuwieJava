package Pages;
import Base.BasePage;
import Interfaces.Buffer;
import Interfaces.IHelper;
import Interfaces.IWeiters;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.util.List;

public class ProductPage extends BasePage implements IHelper, IWeiters {


    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='e-product-price__special']")
    private WebElement ProductPrice;
    @FindBy(xpath="//div[@class='e-product-price__normal']")
    private WebElement ProductPriceRegular;
    @FindBy(xpath = "//div[@class='product-right__group']//button[@data-testid='product-add-favourites-button']")
    private WebElement FavouriteButton;
    @FindBy(xpath = "//button[@data-testid='product-add-to-cart-button']")
    private WebElement AddToBasket;
    @FindBy(xpath = "//button[@data-testid='product-availability-tooltip-button']")
    private WebElement ProductAvailability;
    @FindBy(xpath = "//button[@class='e-tooltip-button']")
    private WebElement FreeSendandReturnTooltip;
    @FindBy(xpath = "//div[@class='e-size-picker__content']")
    private WebElement SizeListPickerList;
    @FindBy(xpath = "//span[@class='e-size-picker-option__label']")
    private List<WebElement> ListofSizePickerList;
    @FindBy(xpath = "//a[@data-testid='product-go-to-cart-link']")
    private WebElement GotoBasket;
    @FindBy(xpath = "//a[@data-testid='header-favourites-link']")
    static WebElement FavouriteLink;
    @FindBy(xpath = "//button[@class='availability-sidebar__trigger']")
    private WebElement StoreAvailability;
    @FindBy(xpath = "//div[@class='availability-sidebar__wrapper']")
    private WebElement StoreSlider;
    @FindBy(xpath = "(//div[@class='product-image-gallery__main-item']/img)[1]")
    private List<WebElement> Image;
    @FindBy(xpath = "//button[@class='e-tooltip-button product-right__availability-methods']/span")
    private WebElement PaymentTooltip;
    @FindBy(xpath = "(//div[@class='content-tooltip-extra__content'])[1]/div/following-sibling::div")
    private List<WebElement> PaymentAvailableMethods;
    @FindBy(xpath = "//ul[@id='customer-reviews']/li/p[@class='product-review-item__review']")
    private List<WebElement> CommentsFromCustomers;
    @FindBy(id="automated_belka_exit")
    private List<WebElement> BelkaOnTop;
    @FindBy(xpath="//button[@class='e-size-picker__option e-size-picker-option'][not(contains(class,'e-size-picker-option--disabled'))]/span/span[@class='e-size-picker-option__label']")
    private List<WebElement> ListOfAvailabeSize;
    String CounterBuffer = String.format("Ulubione (%s)", Buffer.GetActualSize());

    public String getCurrentProductPrice(String numberofproduct) {

       if(Buffer.Buffer.containsKey("Value"+numberofproduct+"Regular")){
           return ProductPriceRegular.getText().replace("zł", " ");
       }
       else {
           return ProductPrice.getText().replace("zł", " ");
       }

    }

    public WebElement getFavouriteButton() {

        return FavouriteButton;
    }

    public WebElement getAddToBasketButton() {

        return AddToBasket;
    }

    public WebElement getProductAvailability() {
        return ProductAvailability;
    }

    public WebElement getFreeSendAndReturnTooltip() {
        return FreeSendandReturnTooltip;
    }

    public List<WebElement> getImage() {

        return Image;
    }

    public ProductPage clickOnAddToBasket() {

        AddToBasket.click();
        return this;
    }

    public ProductPage chooseSizeFromRightList(String x) {
        wait.until(ExpectedConditions.visibilityOf(SizeListPickerList));
        clickEqualsListElement(ListofSizePickerList, x);
        return this;
    }

    public BasketPage goToBasket() {

        GotoBasket.click();
        return new BasketPage(driver);
    }

    public ProductPage addToFavourite() {
        FavouriteButton.click();
        waitforElementTextWillBeChanged(FavouriteLink, CounterBuffer);
        return this;
    }

    public FavouritePage clickOnFavouriteHeaders() {

        elementIsVisible(FavouriteLink);
        if(checkIfElementsSizeIsMoreThan0(BelkaOnTop)) {

            for(WebElement x : BelkaOnTop){
                x.click();
            }
        }
        FavouriteLink.sendKeys(Keys.UP);
        FavouriteLink.click();
        return new FavouritePage(driver);
    }

    public ProductPage clickOnStoreAvailability() {

        StoreAvailability.click();
        return this;
    }

    public WebElement getStoreAvailability() {
        return StoreSlider;
    }

    public ProductPage clickOnPaymentTooltipButton() {

        PaymentTooltip.click();
        return this;
    }

    public List<WebElement> getListOfPaymentMethods() {

        return PaymentAvailableMethods;
    }

    public List<WebElement> getListOfCommnents() {

        return CommentsFromCustomers;

    }

    public ProductPage clickRandomSize() {

        String randomSize=returnRandomSize(ListOfAvailabeSize);
        clickEqualsListElement(ListOfAvailabeSize, randomSize);
        return this;
    }

    @Override
    public WebDriver GetDriver() {
        return this.driver;
    }
}
