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
    private WebElement ContinueShoopingButton;
    @FindBy(xpath = "//*[contains(text(),'Łącznie')]/parent::span/following-sibling::span/child::span")
    private WebElement summaryPriceLabel;

    public MainPage returnToMainPageFromBasket() {


        ContinueShoopingButton.click();
        return new MainPage(driver);
    }

    public static final double DELTA = 1e-15;

    public double getSummaryOfPriceProducts() {

        return Double.doubleToLongBits(Double.valueOf(summaryPriceLabel.getText().replace("zł", " ").replace(',', '.').replaceAll("\\s", "")));
    }

    public double bufferSummedValueOfProducts(List<String> bufferlist) {
        List<Double> DoubleValueFromBuffer = new ArrayList<>();
        for (String x : bufferlist) {
            DoubleValueFromBuffer.add(Double.parseDouble(x.replace(',', '.').replaceAll("\\s", "")));

        }
        return DoubleValueFromBuffer.stream().mapToDouble(Double::valueOf).sum();
    }

    public double getSummedValueOfProducts() {

        return bufferSummedValueOfProducts(Buffer.GetValue());
    }

    @Override
    public WebDriver GetDriver() {
        return this.driver;
    }
}
