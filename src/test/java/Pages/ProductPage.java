package Pages;
import Base.BasePage;
import Base.BaseTest;
import Interfaces.Buffer;
import Interfaces.IHelper;
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

public class ProductPage extends BasePage implements IHelper {


    public ProductPage(WebDriver driver) {
       super(driver);
    }

    @FindBy(xpath="//div[@class='e-product-price__special']")
    private WebElement ProductPrice;
    @FindBy(xpath="//div[@class='product-right__group']//button[@data-testid='product-add-favourites-button']")
    private WebElement FavouriteButton;
    @FindBy(xpath="//button[@data-testid='product-add-to-cart-button']")
    private WebElement AddToBasket;
    @FindBy(xpath="//button[@data-testid='product-availability-tooltip-button']")
    private WebElement ProductAvailability;
    @FindBy(xpath="//button[@class='e-tooltip-button']")
    private WebElement FreeSendandReturnTooltip;
    @FindBy(xpath="//div[@class='e-size-picker__content']")
    private WebElement SizeListPickerList;
    @FindBy(xpath="//span[@class='e-size-picker-option__label']")
    private List<WebElement> ListofSizePickerList;
    @FindBy(xpath="//a[@data-testid='product-go-to-cart-link']")
    private WebElement GotoBasket;
    @FindBy(xpath="//a[@data-testid='header-favourites-link']")
    static WebElement FavouriteLink;
    @FindBy(xpath="//button[@class='availability-sidebar__trigger']")
    private WebElement StoreAvailability;
    @FindBy(xpath="//div[@class='availability-sidebar__wrapper']")
    private WebElement StoreSlider;
    public ProductPage checkPriceOnProductCard(String keyToCompare){

        System.out.println("Get method buffer " + Buffer.GetValueBufferKey(keyToCompare));
        Assert.assertEquals(ProductPrice.getText().replace("zł"," "),Buffer.GetValueBufferKey(keyToCompare));
        return this;
    }

    public ProductPage verifyLayoutOnProductCard(){
        FavouriteButton.isDisplayed();
        AddToBasket.isDisplayed();
        ProductAvailability.isDisplayed();
        FreeSendandReturnTooltip.isDisplayed();
     return this;

    }
    public ProductPage clickOnAddToBasket(){

        AddToBasket.click();
        return this;
    }
    public ProductPage chooseSizeFromRightList(String x){

        new WebDriverWait(driver,10)
                .until(ExpectedConditions.visibilityOf(SizeListPickerList));

        clickEqualsListElement(ListofSizePickerList,x);
        return this;

    }

    public BasketPage gotoBasket(){

        GotoBasket.click();
        return new BasketPage(driver);
    }

    public ProductPage AddToFavourite() throws InterruptedException {
        FavouriteButton.click();
        Thread.sleep(2000);
        return this;
    }
    public FavouritePage clickonFavouriteHeaders(){



        System.out.println(FavouriteLink.getText().trim());
        String Counter= String.format("Ulubione (%s)",Buffer.GetActualSize());
        Assert.assertEquals(FavouriteLink.getText().trim(),Counter);
        FavouriteLink.sendKeys(Keys.DOWN);
        FavouriteLink.click();


      return new FavouritePage(driver);
    }

    public ProductPage clickonStoreAvailability(){

        StoreAvailability.click();
        return this;
    }

    public WebElement GetStoreAvailability(){

        Assert.assertTrue(StoreSlider.isDisplayed());
        return StoreSlider;
    }

    @Override
    public WebDriver GetDriver() {
        return this.driver;
    }
}
