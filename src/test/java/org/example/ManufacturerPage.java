package org.example;
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

public class ManufacturerPage implements IHelper {

    public WebDriver driver;

    public ManufacturerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//ul[@class='sidebar-section__list filter-link__list']/child::li/a")
    private List<WebElement> LeftFilterCategory;
    @FindBy(xpath="//ul[@class='sidebar-section__list filter-link__list']/li/a")
    private List<WebElement> LeftFilterCategoryModel;
    @FindBy(xpath="//li[@class='sidebar-section__list-item filter-link__list-item']/a")
    private List<WebElement> LeftFilterCategoryStyle;
    @FindBy(id="newsletter_banner_exit_salomon")
    private WebElement Banner;
    @FindBy(xpath="//ul[@class='filter-grid__list']/li/a")
    private List<WebElement> LeftFiiferSize;
    @FindBy(xpath="//nav[@aria-label='Breadcrumbs']/ol/li/following-sibling::li/a")
    private List<WebElement> FilterHomeTop;
    @FindBy(xpath="//li[@class='current-filters__item']/child::span[2]")
    private WebElement SizeValueFilter;
    @FindBy(id="search-manufacturer")
    private WebElement SearchManufacturer;
    @FindBy(xpath="//a[@type='button']")
    private WebElement Filter;
    @FindBy(xpath="//h1[@class='category-grid__title-header']")
    private WebElement HeaderofManufacturer;
    @FindBy(xpath="//input[@id='min_price']")
    private WebElement MinPrice;
    @FindBy (xpath="//input[@id='max_price']")
    private WebElement Maxprice;

    @FindBy(xpath="//div[@class='e-range__handle e-range__handle-lower']")
    private WebElement PricehandlerLower;

    @FindBy(xpath="//div[@class='e-range__handle e-range__handle-upper']")
    private WebElement PriceHandlerUpper;
    @FindBy(xpath="//a[not(contains(@type,'button'))][contains(text(),'Filtruj')]")
    WebElement PriceFilterButton;

    @FindBy(xpath="//div[@class='products-list__regular-price' or @class='products-list__special-price']")
    List<WebElement> PricesOnWebsiteRegularAndReduced;

    @FindBy(xpath="//div[contains(normalize-space(@class),'e-range--pips')]//div[@class='e-range__handle e-range__handle-upper']")
    private WebElement WidthShoesSliderUpper;

    @FindBy(xpath="//div[contains(normalize-space(@class),'e-range--pips')]//div[@class='e-range__handle e-range__handle-lower']")
    private WebElement WidthShoesSliderLower;
    @FindBy(xpath="(//a[not(contains(@type,'button'))][contains(text(),'Filtruj')])[2]")
    private WebElement FilterWidth;
    @FindBy(xpath="//div[@class='e-badge e-badge--color-brand']")
    private List<WebElement> NewsLebel;

    @FindBy(xpath="(//a[contains(text(),'Nowość')])[7]")
    private WebElement NewButton;


    public void chooseSexCategory(String value){

        clickEqualsListElement(LeftFilterCategory,value);
    }
    public void chooseModel(String value){

        clickEqualsListElement(LeftFilterCategoryModel,value);
    }
    public void clickOnSaleBanner(){
     try {
         if (Banner.isEnabled()) {
             Banner.click();
         }
         Thread.sleep(2000);
     }
     catch(Exception e) {
         System.out.println("Sales banner nie został wyświetlony");
     }
      }
    public void chooseStyle(String value){

        clickEqualsListElement(LeftFilterCategoryStyle,value);
    }
    public void chooseSize(String value){

        clickEqualsListElement(LeftFiiferSize,value);
    }
    public void verifyFilterLabel(String... x){

        List<String> FilterTextList= new ArrayList<>();

        for( WebElement we : FilterHomeTop){
            System.out.println(we.getText());
            FilterTextList.add(we.getText());
        }
        for(int i=0;i<FilterTextList.size();i++)
        assertEquals("Filter option are not correct", FilterTextList.get(i), x[i]);

    }
    public void verifySizeValueFIlter(String sieze){
        System.out.println("Value from Website is " +SizeValueFilter.getText());
        assertEquals("value filter is not correct",sieze,SizeValueFilter.getText());
    }
    public void searchManufacturer( String x){

        SearchManufacturer.sendKeys(x);
        String DropdownList= String.format( "//button[@data-href-slug='%s']",x);
        WebElement FirstELementfromList =driver.findElement(By.xpath(DropdownList));
        FirstELementfromList.click();
        Filter.click();
    }
    public void verifyTitlleOfManufacturer(){

        Assert.assertEquals("Vans - buty i akcesoria",HeaderofManufacturer.getText());
    }
    public void chooseProduct(String number,String KeyToBuffer) {

        String NumberofProduct= String.format("//a[@data-testid='category-product-item-link%s']",number);
        WebElement Number=GetDriver().findElement(By.xpath(NumberofProduct));
        String CollectPrice= String.format("//a[@data-testid='category-product-item-link%s']//div[@class='products-list__price-box']//div[@class='products-list__special-price']",number);
        Buffer.SetValueInBuffer(KeyToBuffer,GetDriver().findElement(By.xpath(CollectPrice)).getText().replace("zł"," "));
        Number.click();

    }

    public void setMaxAndMinPrice(String value1, String value2) throws InterruptedException {


         MinPrice.sendKeys(Keys.CONTROL + "a",Keys.DELETE);
         Thread.sleep(1000);
         MinPrice.sendKeys(value1);
         MinPrice.sendKeys(Keys.TAB);
         Thread.sleep(1000);
        Maxprice.sendKeys(Keys.CONTROL + "a",Keys.DELETE);
        Thread.sleep(1000);
        Maxprice.sendKeys(value2);
        MinPrice.sendKeys(Keys.TAB);

    }

    public void checkPriceHandlerHasBeenMovedToRequestedPriceRange(String value1, String value2)  {

       String Value1= PriceHandlerUpper.getAttribute("aria-valuenow");
       System.out.println(Value1);
       String Value2=PricehandlerLower.getAttribute("aria-valuenow");
        System.out.println(Value2);
       PriceFilterButton.click();
       Assert.assertEquals(value2,Value1);
       Assert.assertEquals(value1,Value2);

    }

    public void checkIfPricesHaveBeenNarowedAfterFilter(double range1, double range2) throws InterruptedException {

        List<Double> ListofAllPrices = new ArrayList<>();
         for( WebElement x: PricesOnWebsiteRegularAndReduced){
             if(x.isDisplayed()) {
                 ListofAllPrices.add((Double.valueOf(x.getText().replace(",", ".").replace(" zł", "").replaceAll("\\s", ""))));
             }
         }

         for(Double x : ListofAllPrices){

             Assert.assertTrue(checkifPriceisInArequestedRange(range1,range2,x));

         }
    }
    public boolean checkifPriceisInArequestedRange(double min, double max,double z){

        if(z>min && z<max){

            return true;
        }
        else{

            System.out.println(z + " Value is not in a range");
            return false;
        }
    }

    public void setWidthOfShoes(String min, String Max){

        if(min.equals("Wąski")){

            WidthShoesSliderLower.sendKeys(Keys.LEFT);
        }
        if(Max.equals("Szeroki")){
            WidthShoesSliderUpper.sendKeys(Keys.RIGHT);
        }
        if(min.equals("Standardowy")){
            WidthShoesSliderLower.sendKeys(Keys.RIGHT);
        }
      FilterWidth.click();

    }

    public void checkifNewLebelisDisplayedOnEveryProduct(){

        for(WebElement we : NewsLebel){

            Assert.assertTrue(we.isDisplayed());
            }

        }

        public void clickOnNewLebel()
        {

            NewButton.click();
        }



    @Override
    public WebDriver GetDriver() {
        return this.driver;
    }
}

