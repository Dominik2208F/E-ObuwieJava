package Pages;
import Base.BasePage;
import Interfaces.IHelper;
import Interfaces.IWeiters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import static Pages.ProductPage.FavouriteLink;

public class FavouritePage extends BasePage implements IHelper, IWeiters {


    @FindBy(xpath = "//button[@class='favourites__remove-button']")
    private List<WebElement> RemoveButton;
    public FavouritePage(WebDriver driver) {
        super(driver);

    }
    public FavouritePage removeFavourites() {
        int favouriteCounterReduction=2;
        for (WebElement we : RemoveButton) {

            if (we.isDisplayed()) {
                we.click();
                favouriteCounterReduction--;
                String CounterReduction = String.format("Ulubione (%s)",favouriteCounterReduction);
               wait.until(ExpectedConditions.invisibilityOf(we));
                waitforElementTextWillBeChanged(FavouriteLink,CounterReduction);
            }
        }
        return this;
    }
    public String getValueFromFavouriteLabel() {

        return FavouriteLink.getText().trim();
    }
    @Override
    public WebDriver GetDriver() {
        return this.driver;
    }
}
