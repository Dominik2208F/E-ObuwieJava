package Pages;
import Base.BasePage;
import Interfaces.Buffer;
import Interfaces.IHelper;
import Interfaces.IWeiters;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductPage extends BasePage implements IHelper, IWeiters {


    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='e-product-price__special']")
    private WebElement ProductPrice;
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
    private List<WebElement>Image;

    String CounterBuffer = String.format("Ulubione (%s)", Buffer.GetActualSize());

    public String GetCurrentProductPrice() {

        return ProductPrice.getText().replace("zł", " ");
    }

    public ProductPage verifyLayoutOnProductCard() {
        FavouriteButton.isDisplayed();
        AddToBasket.isDisplayed();
        ProductAvailability.isDisplayed();
        FreeSendandReturnTooltip.isDisplayed();
        return this;

    }

    public boolean verifyElementsAreDisplayed(WebElement...x) {

        List<WebElement> list = Arrays.asList(x);

        for (WebElement el : list) {

            if(!el.isDisplayed()){
               return false;
            }
        }
    return true;
    }

    public WebElement getFavouriteButton(){

        return FavouriteButton;
    }

    public WebElement getAddToBasketButton(){

        return AddToBasket;
    }

    public WebElement getProductAvailability(){
        return ProductAvailability;
    }

    public WebElement getFreeSendandReturnTooltip(){
        return FreeSendandReturnTooltip;
    }

    public List<WebElement> getImage(){

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

    public BasketPage gotoBasket() {

        GotoBasket.click();
        return new BasketPage(driver);
    }

    public ProductPage AddToFavourite() {
        FavouriteButton.click();
        waitforElementTextWillBeChanged(FavouriteLink, CounterBuffer);
        return this;
    }

    public FavouritePage clickonFavouriteHeaders() {

        elementIsVisible(FavouriteLink);
        Assert.assertEquals(FavouriteLink.getText().trim(), CounterBuffer = String.format("Ulubione (%s)", Buffer.GetActualSize()));
        FavouriteLink.sendKeys(Keys.DOWN);
        FavouriteLink.click();

        return new FavouritePage(driver);
    }

    public ProductPage clickonStoreAvailability() {

        StoreAvailability.click();
        return this;
    }

    public WebElement GetStoreAvailability() {
        return StoreSlider;
    }

    @Override
    public WebDriver GetDriver() {
        return this.driver;
    }
}
