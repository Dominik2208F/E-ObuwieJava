package Pages;

import Base.BasePage;
import Base.BaseTest;
import Interfaces.Buffer;
import Interfaces.IHelper;
import Interfaces.IWeiters;
import jdk.jfr.internal.tool.Main;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
