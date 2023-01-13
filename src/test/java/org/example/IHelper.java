package org.example;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public interface IHelper {

    default void verifyElementsInListEquals(List<WebElement> elementsList, String expectedValue) {

        ArrayList<String> TextList =new ArrayList<>();

        for( WebElement x :elementsList){
            TextList.add(x.getText());
        }

        if(TextList.contains(expectedValue)){
            System.out.println("Wartość jest");
        }
        else{
            System.out.println("Wartości nie ma");
            assertTrue(false);
        }
    }
    default void checkIfListContainsAllExpectedElements(List<WebElement> listtoSearch, List<String> listaexpected) {
        List<String> listaOfText = new ArrayList<String>();
        for (WebElement x : listtoSearch) {
            listaOfText.add(x.getText());
        }

        for (int i = 0; i < listaexpected.size(); i++) {
            assertEquals("Actual Headers are not correct", listaexpected.get(i), listaOfText.get(i));
        }
        System.out.println("Actual Values are correct");
    }
    default boolean clickEqualsListElement(List<WebElement> list, String value) {

        for(WebElement we : list) {
            if(we.getText().equals(value)) {
                we.click();
                return true;
            }
        }
        System.out.println("Nie znaleziono pasującego elementu. Pozostanie domyślny.");
        return false;
    }

    default void clickIn(WebElement element, Integer... time) {
        Integer periodOfTime = time.length > 0 ? time[0] : 30;
        new WebDriverWait(GetDriver(),30)
                .until(ExpectedConditions.elementToBeClickable(element));
        for (int i = 0; i <= 2; i++) {
            try {
                element.click();
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    default void setValueInField(WebElement element, String value) {

      new WebDriverWait(GetDriver(),30)
                .until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        element.sendKeys(value);
        System.out.println("input value = " + String.valueOf(value));

    }
    default void setValueInField(WebElement element, CharSequence... value) {
        new WebDriverWait(GetDriver(),30)
                .until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(value);
    }

   default void MouseHover(WebElement x){
        //Creating object of an Actions class
        Actions action = new Actions(GetDriver());

//Performing the mouse hover action on the target element.
        action.moveToElement(x).perform();
    }

    default void ClickOnDropDownList(WebElement element) {
        try {

            element.click();

        } catch (Exception e) {

        }
    }
    WebDriver GetDriver();



}
