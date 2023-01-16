package Interfaces;
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

    default void verifyElementExistInList(List<WebElement> elementsList, String expectedValue) {

        ArrayList<String> TextList =new ArrayList<>();

        for( WebElement x :elementsList){
            TextList.add(x.getText());
        }

        if(TextList.contains(expectedValue)){
            System.out.println("value in the List of elements");
        }
        else{
            System.out.println("no value in the list of elements");
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
        System.out.println("Elements hasn't been found in the list of provided elements.");
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
    default void mouseHover(WebElement x){
        Actions action = new Actions(GetDriver());
        action.moveToElement(x).perform();
    }
    default void clickOnDropDownList(WebElement element) {
        try {
            element.click();

        } catch (Exception e) {
        }
    }

    default List<String> convertWebElementsListToString(List<WebElement> listaofwebelements){

        List<String> FilterTextList= new ArrayList<>();

        for( WebElement we : listaofwebelements){
            System.out.println(we.getText());
            FilterTextList.add(we.getText());
        }
        return  FilterTextList;
    }

    default List<Double>convertWebElementsLisToDouble(List<WebElement> listofwebelements){

        List<Double> ListofAllPrices = new ArrayList<>();
        for (WebElement x : listofwebelements) {
            if (x.isDisplayed()) {
                ListofAllPrices.add((Double.valueOf(x.getText().replace(",", ".").replace(" zł", "").replaceAll("\\s", ""))));
            }
        }
            return ListofAllPrices;
    }

    default boolean ListAreEqual(List<String>l1, List<String> l2) {

            if (l2.equals(l1)) {
                System.out.println("Elements are equal");
                return true;
            }
            else{
                System.out.println("Elements are not equal");
                return false;
            }
    }


    WebDriver GetDriver();
}
