package Pages;
import Base.BasePage;
import Interfaces.Buffer;
import Interfaces.IHelper;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.List;


public class BasketPage extends BasePage implements IHelper {

    public BasketPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[contains(@class,'cart__continue-shopping')]")
    private  WebElement ContinueShoopingButton;


   // @FindAll
   //         ({
  //                  @FindBy(xpath="//div[contains(normalize-space(@class),'cart-item__price--discounted')]"),
  //                  @FindBy(xpath= "(//div[contains(normalize-space(@class),'cart-item__price')])[2]"),
  //          })
   // private List<WebElement> Reducedprice;

    @FindBy(xpath="//*[contains(text(),'Łącznie')]/parent::span/following-sibling::span/child::span")
    private WebElement summaryPriceLabel;
    public MainPage returnToMainPageFromBasket() {


        ContinueShoopingButton.click();
        return new MainPage(driver);
    }
    public void compareSumInBasketWithLabelPrice(){

        comparePricesBasedOnList(Buffer.GetValue(),summaryPriceLabel);

    }
    public void comparePricesBasedOnList(List<String> bufferlist, WebElement summarylist) {
        List<Double> DoubleValueFromBuffer = new ArrayList<>();

        for (String x : bufferlist) {
            DoubleValueFromBuffer.add(Double.parseDouble(x.replace(',', '.').replaceAll("\\s", "")));

        }
        double BufferSumedValue = DoubleValueFromBuffer.stream().mapToDouble(Double::valueOf).sum();
        Assert.assertEquals(Double.doubleToLongBits(BufferSumedValue), Double.doubleToLongBits(Double.valueOf(summaryPriceLabel.getText().replace("zł", " ").replace(',', '.').replaceAll("\\s", ""))));

    }

    @Override
    public WebDriver GetDriver() {
        return this.driver;
    }
}
