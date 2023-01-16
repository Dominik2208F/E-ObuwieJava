package Pages;

import Base.BasePage;
import Interfaces.IHelper;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static Pages.ProductPage.FavouriteLink;

public class FavouritePage extends BasePage implements IHelper {


    @FindBy(xpath="//button[@class='favourites__remove-button']")
    private List<WebElement> RemoveButton;

    public FavouritePage(WebDriver driver) {
        super(driver);

    }


    public FavouritePage RemoveFavourites() throws InterruptedException {

        int counter=0;
        for(WebElement we : RemoveButton){

            if(we.isDisplayed()) {
                we.click();
                counter++;
                Thread.sleep(2000);
            }
        }
        return this;
    }

    public FavouritePage checkifAllDelated(){

        Assert.assertEquals(FavouriteLink.getText().trim(),"Ulubione (0)");
        return this;
    }
    public String GetValueFromFavouriteLabel(){

        return FavouriteLink.getText().trim();
    }





    @Override
    public WebDriver GetDriver() {
        return null;
    }
}
