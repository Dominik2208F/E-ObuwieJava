package org.example;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;

public class TestClass {

   MainPage mainpage;
   ManufacturerPage manufacturerPage;
   ProductPage productPage;
   BasketPage basketPage;
   FavouritePage favouritePage;

    WebDriver driver;
    @Before
    public void setUp(){

        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().deleteAllCookies();
        driver.get("https://www.eobuwie.pl");
        driver.manage().window().maximize();
        // Page init
        mainpage= new MainPage(driver);
        manufacturerPage= new ManufacturerPage(driver);
        productPage= new ProductPage(driver);
        basketPage = new BasketPage(driver);
        favouritePage = new FavouritePage(driver);
        //
        mainpage.acceptCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        Buffer.ClearBuffer();


    }
   @Test
    public void checkTittle(){
       driver.get("https://www.eobuwie.pl");
       assertEquals("Sklep internetowy eobuwie.pl • Modne buty, akcesoria i torebki online | eobuwie.pl",driver.getTitle());
   }
   @Test
   public void checkUrl(){

        assertEquals("https://www.eobuwie.com.pl/",driver.getCurrentUrl());

   }
@Test
   public void checkHeaders() throws InterruptedException {

        mainpage.headersVerification();
        mainpage.verifyList("WYPRZEDAŻ");


   }
@Test
   public void lookForManVansNumber40ByTopMarkTab()  {
        manufacturerPage.clickOnSaleBanner();
        mainpage.selectTopMark("Vans");
        manufacturerPage.chooseSexCategory("Damskie");
        manufacturerPage.clickOnSaleBanner();
        manufacturerPage.chooseModel("Sportowe");
        manufacturerPage.chooseStyle("Lifestyle");
        manufacturerPage.chooseSize("40");
        manufacturerPage.verifyFilterLabel("Damskie","Sportowe","Lifestyle");
        manufacturerPage.verifySizeValueFIlter("40");
   }

   @Test
   public void lookForManVansNumber42BySearchBox()  {
        manufacturerPage.clickOnSaleBanner();
        mainpage.typeInSearchBox("Vans");
        manufacturerPage.chooseSexCategory("Męskie");
        manufacturerPage.clickOnSaleBanner();
        manufacturerPage.chooseModel("Sportowe");
        manufacturerPage.clickOnSaleBanner();
        manufacturerPage.chooseStyle("Trampki");
        manufacturerPage.chooseSize("42");
        manufacturerPage.verifyFilterLabel("Męskie","Sportowe","Trampki");
        manufacturerPage.verifySizeValueFIlter("42");

   }
   @Test
   public void lookForManVansNumber42ByHeaders() throws InterruptedException {
      mainpage.mouseHoverOnMenCategory();
      manufacturerPage.clickOnSaleBanner();
      mainpage.clickOnTrampkiCategoryOnDropDownList();
      manufacturerPage.chooseSize("42");
      manufacturerPage.clickOnSaleBanner();
      manufacturerPage.searchManufacturer("vans");
      manufacturerPage.verifyFilterLabel("Męskie","Półbuty","Trampki");
      manufacturerPage.verifySizeValueFIlter("42");
      manufacturerPage.verifyTitlleOfManufacturer();
   }

   @Test
   public void checkAddingProductToBasket() throws InterruptedException {
       mainpage.mouseHoveronChildCategory();
       mainpage.clickOnSneakearsCategoryOnDropDownList();
       manufacturerPage.chooseProduct("3","Value1");
       productPage.checkPriceOnProductCard("Value1");
       productPage.verifyLayoutOnProductCard();
       mainpage.CloseNewsLetter();
       productPage.clickOnAddToBasket();
       productPage.chooseSizeFromRightList("36");
       productPage.gotoBasket();


   }


   @Test
   public void addTwoRandomProductToBasketAndCheckSumValueInSummary() throws InterruptedException {
       mainpage.mouseHoverOnMenCategory();
       mainpage.clickOnSztybletyDropDownList();
       manufacturerPage.clickOnSaleBanner();
       manufacturerPage.chooseSize("42");
       manufacturerPage.clickOnSaleBanner();
       manufacturerPage.chooseProduct("1","Value1");
       productPage.checkPriceOnProductCard("Value1");
       productPage.verifyLayoutOnProductCard();
       productPage.clickOnAddToBasket();
       productPage.chooseSizeFromRightList("42");
       productPage.gotoBasket();
       basketPage.returnToMainpageFromBasket();
       mainpage.CloseNewsLetter();

       //First Product in Basket

       mainpage.mouseHoverOnMenCategory();
       mainpage.clickOnSztybletyDropDownList();
       manufacturerPage.chooseSize("42");
       manufacturerPage.clickOnSaleBanner();
       manufacturerPage.chooseProduct("2","Value2");
       productPage.checkPriceOnProductCard("Value2");
       productPage.verifyLayoutOnProductCard();
       productPage.clickOnAddToBasket();
       productPage.chooseSizeFromRightList("42");
       productPage.gotoBasket();
       basketPage.compareSumInBasketWithLabelPrice();

   }


   @Test
   public void setPriceRangeBarOnLeftSide() throws InterruptedException {

       mainpage.mouseHoverOnMenCategory();
       mainpage.clickOnTrampkiCategoryOnDropDownList();
       manufacturerPage.clickOnSaleBanner();
       manufacturerPage.setMaxAndMinPrice("200","300");
       manufacturerPage.checkPriceHandlerHasBeenMovedToRequestedPriceRange("200.0","300.0");
       manufacturerPage.PriceFilterButton.click();
       manufacturerPage.checkIfPricesHaveBeenNarowedAfterFilter(200.0,300.0);


   }

   @Test
   public void setkWidthRangeBaronLeftSide() throws InterruptedException {
       mainpage.mouseHoverOnMenCategory();
       mainpage.clickOnTrampkiCategoryOnDropDownList();
       manufacturerPage.clickOnSaleBanner();
       manufacturerPage.setWidthOfShoes("Standardowy","Szeroki");

   }

  @Test

  public void setNewLebel() throws InterruptedException {
      mainpage.mouseHoverOnMenCategory();
      mainpage.clickOnTrampkiCategoryOnDropDownList();
      manufacturerPage.clickOnSaleBanner();
      manufacturerPage.clickOnNewLebel();
      manufacturerPage.checkifNewLebelisDisplayedOnEveryProduct();
  }

  @Test
  public void addProductsToFavouriteAndDelete() throws InterruptedException {

      //First
      mainpage.mouseHoverOnMenCategory();
      mainpage.clickOnSztybletyDropDownList();
      manufacturerPage.clickOnSaleBanner();
      manufacturerPage.chooseProduct("1", "Value1");
      mainpage.CloseNewsLetter();
      productPage.AddToFavourite();
      productPage.clickonFavouriteHeaders();

      //Second
      mainpage.mouseHoverOnMenCategory();
      mainpage.clickOnSztybletyDropDownList();
      manufacturerPage.chooseProduct("4", "Value2");
      productPage.AddToFavourite();
      productPage.clickonFavouriteHeaders();

      favouritePage.RemoveFavourites();
      favouritePage.checkifAllDelated();

  }
@Test
  public void checkStoreAvailability() throws InterruptedException {

      manufacturerPage.clickOnSaleBanner();
      mainpage.mouseHoveronChildCategory();
      mainpage.clickOnSneakearsCategoryOnDropDownList();
      manufacturerPage.chooseProduct("10","Value1");
      productPage.verifyLayoutOnProductCard();
      mainpage.CloseNewsLetter();
      productPage.clickonStoreAvailability();
      productPage.chooseSizeFromRightList("36");
      productPage.checkStoreAvailability();
  }



   @After
    public void Close(){
        driver.quit();

   }

}
