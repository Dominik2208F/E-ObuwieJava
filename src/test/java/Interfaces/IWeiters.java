package Interfaces;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public interface IWeiters  {

    // Interface to implement waiting methods when it is required
    default void elementIsVisible(WebElement element) {
        try {
            new WebDriverWait(GetDriver(),3).until(ExpectedConditions.visibilityOf(element));
        } catch (Exception te) {
            te.printStackTrace();
        } finally {
            Assert.assertTrue("Element: " + element + " is not visible", element.isDisplayed());
            System.out.println("Positive verification - element is visible: " + element);
        }
    }
    default void elementsAreNotEmpty(List<WebElement> elements) {
        new WebDriverWait(GetDriver(), 3).until(ExpectedConditions.visibilityOfAllElements(elements));
        for (WebElement element : elements) {
            Assert.assertFalse("Element: " + element + " has no value", element.getText().trim().isEmpty());
            System.out.println("Positive verification - element is not empty and has value: " + element.getText() + ". Element: " + element);
        }
    }
    default boolean textOfElementIsEquals(WebElement element, String text) {
        new WebDriverWait(GetDriver(), 3).until(ExpectedConditions.visibilityOf(element));
        return element.getText().equals(text);
    }
    default void waitforElementTextWillBeChanged(WebElement element,String textTobeChanged){

        while(!textOfElementIsEquals(element,textTobeChanged)){

            System.out.println("Waiting for element");
        }
        System.out.println("Element has been changed");
    }

    WebDriver GetDriver();
}
