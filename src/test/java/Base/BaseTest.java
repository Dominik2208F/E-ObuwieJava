package Base;
import Interfaces.Buffer;
import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;

public class BaseTest {

   protected  MainPage mainpage;
   protected  ManufacturerPage manufacturerPage;
   protected ProductPage productPage;
   protected BasketPage basketPage;
   protected FavouritePage favouritePage;

   protected WebDriver driver;
   protected BasePage basepage;

    @Before
    public void setUp(){

        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().deleteAllCookies();
        driver.get("https://www.eobuwie.pl");
        driver.manage().window().maximize();
        // Page init
        basepage =new BasePage(driver);
        mainpage= new MainPage(driver);
        //
        mainpage.acceptCookies();
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        Buffer.ClearBuffer();
    }

    @After
    public void Close(){
        driver.quit();

    }


}
