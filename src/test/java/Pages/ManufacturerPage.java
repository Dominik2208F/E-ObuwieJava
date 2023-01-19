package Pages;
import Base.BasePage;
import Interfaces.Buffer;
import Interfaces.IHelper;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;



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
    private WebElement PriceFilterButton;
    @FindBy(xpath = "//div[@class='products-list__regular-price' or @class='products-list__special-price']")
    private List<WebElement> PricesOnWebsiteRegularAndReduced;
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


    //String are used to choose element on website be argument in function(1,2,3). No need to do a path related to every product on ManufacturerPage.
    String DropdownList = "//button[@data-href-slug='%s']";
    String NumberOfProductOnList = "//a[@data-testid='category-product-item-link%s']";
    String CollectPriceToBuffer = "//a[@data-testid='category-product-item-link%s']//div[@class='products-list__price-box']//div[@class='products-list__special-price']";

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

    public List<WebElement> getFilterHomeTop() {

        return FilterHomeTop;
    }

    public String getSizeValue(String size) {
        System.out.println("Value from Website is " + SizeValueFilter.getText());
        return SizeValueFilter.getText();
    }

    public ManufacturerPage searchManufacturer(String x) {

        SearchManufacturer.sendKeys(x);
        WebElement FirstELementfromList = driver.findElement(ConvertStringToXpath(x, DropdownList));
        FirstELementfromList.click();
        Filter.click();
        return this;
    }

    public String getManufacturerTittle() {
        System.out.println(HeaderofManufacturer.getText());
        return HeaderofManufacturer.getText();
    }

    public ProductPage chooseProduct(String numberofproductToConvert, String KeyToBuffer) {

        WebElement Product = GetDriver().findElement(ConvertStringToXpath(numberofproductToConvert, NumberOfProductOnList));
        Buffer.SetValueInBuffer(KeyToBuffer, GetDriver().findElement(ConvertStringToXpath(numberofproductToConvert, CollectPriceToBuffer)).getText().replace("zł", " "));
        Product.click();
        return new ProductPage(driver);
    }

    public ManufacturerPage setMaxAndMinPrice(String value1, String value2) {

        MoveForward(value1,value2);
        return this;
    }
    public void MoveForward(String value1,String value2){

        for(int i=1; i<=2; i++){

                if(i==1) {
                    MinPrice.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
                    MinPrice.sendKeys(value1);
                    MinPrice.sendKeys(Keys.TAB);
                }
                else{
                    Maxprice.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
                    Maxprice.sendKeys(value2);
                    MinPrice.sendKeys(Keys.TAB);
                }


            }
        }

    public boolean checkPriceHandlerHasBeenMovedToRequestedPriceRange(String value1, String value2) {

        String PriceHandlerUpperValue = PriceHandlerUpper.getAttribute("aria-valuenow");
        String PricehandlerLowerValue = PricehandlerLower.getAttribute("aria-valuenow");

        PriceFilterButton.click();
        if (value2.equals(PriceHandlerUpperValue) && (value1.equals(PricehandlerLowerValue))) {
            return true;
        } else {
            System.out.println("Handler don't have required value");
            return false;
        }

    }

    public List<Double> GetPricesFromWebsite() {

        convertWebElementsListToDouble(PricesOnWebsiteRegularAndReduced);
        return convertWebElementsListToDouble(PricesOnWebsiteRegularAndReduced);
    }

    public boolean checkIfPAllRoductsPricesAreInRequestedRange(double min, double max, List<Double> listofdoubles) {

        for (Double currentwebsitevalue : listofdoubles) {

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

    public boolean featuresAreDisplayed() {

        for (WebElement we : NewsLebel) {

            if (!we.isDisplayed())
                return false;

        }
        System.out.println("Lebels has been updated");
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

