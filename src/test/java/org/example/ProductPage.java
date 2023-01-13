package org.example;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class ProductPage implements IHelper {

    public WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
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
    public void checkPriceOnProductCard(String keyToCompare){

        System.out.println("Get method buffer " + Buffer.GetValueBufferKey(keyToCompare));
        Assert.assertEquals(ProductPrice.getText().replace("zł"," "),Buffer.GetValueBufferKey(keyToCompare));
    }

    public void verifyLayoutOnProductCard(){
        FavouriteButton.isDisplayed();
        AddToBasket.isDisplayed();
        ProductAvailability.isDisplayed();
        FreeSendandReturnTooltip.isDisplayed();


    }
    public void clickOnAddToBasket(){

        AddToBasket.click();
    }
    public void chooseSizeFromRightList(String x){

        new WebDriverWait(driver,10)
                .until(ExpectedConditions.visibilityOf(SizeListPickerList));

        clickEqualsListElement(ListofSizePickerList,x);


    }

    public void gotoBasket(){

        GotoBasket.click();
    }

    public void AddToFavourite() throws InterruptedException {
        FavouriteButton.click();
        Thread.sleep(2000);

    }
    public void clickonFavouriteHeaders(){

        FavouriteLink.click();

        System.out.println(FavouriteLink.getText().trim());
        String Counter= String.format("Ulubione (%s)",Buffer.GetActualSize());
        Assert.assertEquals(FavouriteLink.getText().trim(),Counter);

    }

    public void clickonStoreAvailability(){

        StoreAvailability.click();
    }

    public void checkStoreAvailability(){

        Assert.assertTrue(StoreSlider.isDisplayed());
    }

    @Override
    public WebDriver GetDriver() {
        return this.driver;
    }
}
