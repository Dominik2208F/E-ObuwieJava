package Pages;
import Base.BasePage;
import Interfaces.Buffer;
import Interfaces.IHelper;
import com.sun.media.sound.SoftMidiAudioFileReader;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ManufacturerPage extends BasePage implements IHelper {


    public ManufacturerPage(WebDriver driver) {
        super(driver);

    }

    @FindBy(xpath = "//ul[@class='sidebar-section__list filter-link__list']/child::li/a")
    private List<WebElement> LeftFilterCategory;
    @FindBy(xpath = "//ul[@class='sidebar-section__list filter-link__list']/li/a")
    private List<WebElement> LeftFilterCategoryModel;
    @FindBy(xpath = "//li[@class='sidebar-section__list-item filter-link__list-item']/a")
    private List<WebElement> LeftFilterCategoryStyle;
    @FindBy(xpath = "//ul[@class='filter-grid__list']/li/a")
    private List<WebElement> LeftFiiferSize;
    @FindBy(xpath = "//nav[@aria-label='Breadcrumbs']/ol/li/following-sibling::li/a")
    private List<WebElement> FilterHomeTop;
    @FindBy(xpath = "//li[@class='current-filters__item']/child::span[2]")
    private WebElement SizeValueFilter;
    @FindBy(id = "search-manufacturer")
    private WebElement SearchManufacturer;
    @FindBy(xpath = "//a[@type='button']")
    private WebElement Filter;
    @FindBy(xpath = "//h1[@class='category-grid__title-header']")
    private WebElement HeaderofManufacturer;
    @FindBy(xpath = "//input[@id='min_price']")
    private WebElement MinPrice;
    @FindBy(xpath = "//input[@id='max_price']")
    private WebElement Maxprice;
    @FindBy(xpath = "//div[@class='e-range__handle e-range__handle-lower']")
    private WebElement PricehandlerLower;
    @FindBy(xpath = "//div[@class='e-range__handle e-range__handle-upper']")
    private WebElement PriceHandlerUpper;
    @FindBy(xpath = "//a[not(contains(@type,'button'))][contains(text(),'Filtruj')]")
    public
    WebElement PriceFilterButton;
    @FindBy(xpath = "//div[@class='products-list__regular-price' or @class='products-list__special-price']")
    List<WebElement> PricesOnWebsiteRegularAndReduced;
    @FindBy(xpath = "//div[contains(normalize-space(@class),'e-range--pips')]//div[@class='e-range__handle e-range__handle-upper']")
    private WebElement WidthShoesSliderUpper;
    @FindBy(xpath = "//div[contains(normalize-space(@class),'e-range--pips')]//div[@class='e-range__handle e-range__handle-lower']")
    private WebElement WidthShoesSliderLower;
    @FindBy(xpath = "(//a[not(contains(@type,'button'))][contains(text(),'Filtruj')])[2]")
    private WebElement FilterWidth;
    @FindBy(xpath = "//div[@class='e-badge e-badge--color-brand']")
    private List<WebElement> NewsLebel;

    @FindBy(xpath = "(//a[contains(text(),'Nowość')])[7]")
    private WebElement NewButton;


    public ManufacturerPage chooseSexCategory(String value) {

        clickEqualsListElement(LeftFilterCategory, value);
        return this;
    }

    public ManufacturerPage chooseModel(String value) {

        clickEqualsListElement(LeftFilterCategoryModel, value);
        return this;
    }


    public ManufacturerPage chooseStyle(String value) {

        clickEqualsListElement(LeftFilterCategoryStyle, value);
        return this;
    }

    public ManufacturerPage chooseSize(String value) {

        clickEqualsListElement(LeftFiiferSize, value);
        return this;
    }
/*
    public ManufacturerPage verifyFilterLabel(String... x) {
        convertWebElementsListToString(getFilterHomeTop());
        for (int i = 0; i < convertWebElementsListToString(FilterHomeTop).size(); i++)
            assertEquals("Filter option are not correct", convertWebElementsListToString(getFilterHomeTop()).get(i), x[i]);
        return this;
    }

*/

    public List<WebElement> getFilterHomeTop() {

        return FilterHomeTop;
    }

    public String getSizeValue(String size) {
        System.out.println("Value from Website is " + SizeValueFilter.getText());
        return SizeValueFilter.getText();
    }

    public ManufacturerPage searchManufacturer(String x) {

        SearchManufacturer.sendKeys(x);
        String DropdownList = String.format("//button[@data-href-slug='%s']", x);
        WebElement FirstELementfromList = driver.findElement(By.xpath(DropdownList));
        FirstELementfromList.click();
        Filter.click();
        return this;
    }

    public String getManufacturerTittle() {
        System.out.println(HeaderofManufacturer.getText());
        return HeaderofManufacturer.getText();
    }

    public ProductPage chooseProduct(String number, String KeyToBuffer) {

        String NumberofProduct = String.format("//a[@data-testid='category-product-item-link%s']", number);
        WebElement Number = GetDriver().findElement(By.xpath(NumberofProduct));
        String CollectPrice = String.format("//a[@data-testid='category-product-item-link%s']//div[@class='products-list__price-box']//div[@class='products-list__special-price']", number);
        Buffer.SetValueInBuffer(KeyToBuffer, GetDriver().findElement(By.xpath(CollectPrice)).getText().replace("zł", " "));
        Number.click();
        return new ProductPage(driver);
    }

    public ManufacturerPage setMaxAndMinPrice(String value1, String value2) throws InterruptedException {


        MinPrice.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        Thread.sleep(1000);
        MinPrice.sendKeys(value1);
        MinPrice.sendKeys(Keys.TAB);
        Thread.sleep(1000);
        Maxprice.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        Thread.sleep(1000);
        Maxprice.sendKeys(value2);
        MinPrice.sendKeys(Keys.TAB);
        return this;
    }

    public boolean checkPriceHandlerHasBeenMovedToRequestedPriceRange(String value1, String value2) {

        String PriceHandlerUpperValue = PriceHandlerUpper.getAttribute("aria-valuenow");
        System.out.println(PriceHandlerUpperValue);
        String PricehandlerLowerValue = PricehandlerLower.getAttribute("aria-valuenow");
        System.out.println(PricehandlerLowerValue);
        PriceFilterButton.click();
        if (value2.equals(PriceHandlerUpperValue) && (value1.equals(PricehandlerLowerValue))) {
            return true;
        }
        else{
            System.out.println("Handler don't have required value");
            return false;

        }

    }

    public List<Double> GetPricesFromWebsite() {

        convertWebElementsLisToDouble(PricesOnWebsiteRegularAndReduced);

      //  for (Double x : convertWebElementsLisToDouble(PricesOnWebsiteRegularAndReduced)) {
//
      //      Assert.assertTrue(checkifPriceisInArequestedRange(range1, range2, x));
//
     //   }
        return convertWebElementsLisToDouble(PricesOnWebsiteRegularAndReduced);
    }

    public boolean checkifPriceisInArequestedRange(double min, double max, List<Double> listofdoubles) {

        for(Double currentwebsitevalue: listofdoubles) {

            if (!(currentwebsitevalue > min && currentwebsitevalue < max)) {
                System.out.println(currentwebsitevalue + "Value is not in a range");
                return false;

            }
        }
        return true;
    }

    public ManufacturerPage setWidthOfShoes(String min, String Max) {

        if (min.equals("Wąski")) {

            WidthShoesSliderLower.sendKeys(Keys.LEFT);
        }
        if (Max.equals("Szeroki")) {
            WidthShoesSliderUpper.sendKeys(Keys.RIGHT);
        }
        if (min.equals("Standardowy")) {
            WidthShoesSliderLower.sendKeys(Keys.RIGHT);
        }
        FilterWidth.click();
        return this;
    }

    public ManufacturerPage checkifNewLebelisDisplayedOnEveryProduct() {

        for (WebElement we : NewsLebel) {

            Assert.assertTrue(we.isDisplayed());
        }
        return this;
    }

    public boolean featuresAreDisplayed(){

        for (WebElement we : NewsLebel) {

            if(!we.isDisplayed())
                return false;
        }
        return true;
    }


    public ManufacturerPage clickOnNewLebel() {

        NewButton.click();
        return this;
    }

    public ManufacturerPage clickPriceFilterButton() {

        PriceFilterButton.click();
        return this;
    }


    @Override
    public WebDriver GetDriver() {
        return this.driver;
    }
}

