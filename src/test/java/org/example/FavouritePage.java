package org.example;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.example.ProductPage.FavouriteLink;

public class FavouritePage implements IHelper {

    public WebDriver driver;

    @FindBy(xpath="//button[@class='favourites__remove-button']")
    private List<WebElement> RemoveButton;

    public FavouritePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void RemoveFavourites() throws InterruptedException {

        int counter=0;
        for(WebElement we : RemoveButton){

            if(we.isDisplayed()) {
                we.click();
                counter++;
                Thread.sleep(2000);
            }
        }

    }

    public void checkifAllDelated(){

        Assert.assertEquals(FavouriteLink.getText().trim(),"Ulubione (0)");
    }





    @Override
    public WebDriver GetDriver() {
        return null;
    }
}
