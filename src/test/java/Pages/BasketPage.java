package Pages;
import Base.BasePage;
import Interfaces.Buffer;
import Interfaces.IHelper;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.List;


public class BasketPage extends BasePage implements IHelper {

    public BasketPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[contains(@class,'cart__continue-shopping')]")
    private  WebElement ContinueShoopingButton;
    @FindBy(xpath = "//div[contains(normalize-space(@class),'cart-item__price--discounted')]")
    private List<WebElement> Reducedprice;
    @FindBy(xpath="//*[contains(text(),'Łącznie')]/parent::span/following-sibling::span/child::span")
    private WebElement summaryPriceLabel;
    public MainPage returnToMainPageFromBasket() {


        ContinueShoopingButton.click();
        return new MainPage(driver);
    }
    public void compareSumInBasketWithLabelPrice(){

        comparePricesBasedOnList(Buffer.GetValue(),Reducedprice);

    }
    public void comparePricesBasedOnList(List<String> bufferlist, List<WebElement> summarylist) {
        double summedvalueofWebElementInBasket = 0;
        List<Double> IntegerValueofWebElementPrice = new ArrayList<>();
        List<String> ValueofWebsiteElements = new ArrayList<>();
        List<Double> DoubleValueFromBuffer = new ArrayList<>();

        for (WebElement x : summarylist) {

            ValueofWebsiteElements.add(x.getText().replace("zł", " ").trim());

        }
        //1)
        Assert.assertTrue(bufferlist.equals(ValueofWebsiteElements));

        for (int i = 0; i < ValueofWebsiteElements.size(); i++) {

            IntegerValueofWebElementPrice.add(Double.parseDouble(ValueofWebsiteElements.get(i).replace(',', '.').replaceAll("\\s", "")));

        }
        summedvalueofWebElementInBasket = IntegerValueofWebElementPrice.stream().mapToDouble(Double::valueOf).sum();

        for (String x : bufferlist) {
            DoubleValueFromBuffer.add(Double.parseDouble(x.replace(',', '.').replaceAll("\\s", "")));

        }

        double BufferSumedValue = DoubleValueFromBuffer.stream().mapToDouble(Double::valueOf).sum();
        //2//3
        Assert.assertEquals(Double.doubleToLongBits(BufferSumedValue), Double.doubleToLongBits(summedvalueofWebElementInBasket));
        Assert.assertEquals(Double.doubleToLongBits(summedvalueofWebElementInBasket), Double.doubleToLongBits(Double.valueOf(summaryPriceLabel.getText().replace("zł", " ").replace(',', '.').replaceAll("\\s", ""))));

    }
    @Override
    public WebDriver GetDriver() {
        return this.driver;
    }
}
