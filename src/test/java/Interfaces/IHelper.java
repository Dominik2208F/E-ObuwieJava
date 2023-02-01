package Interfaces;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public interface IHelper   {

    // Interface to implement methods when it is required on TestPage
    default boolean verifyElementExistInList(List<WebElement> elementsList, String expectedValue) {

        if (convertWebElementsListToString(elementsList).contains(expectedValue)) {
            System.out.println("value " + expectedValue + "is in the list of elements");
            return true;
        } else {
            System.out.println("no value " + expectedValue + " in the list of elements");
            return false;
        }
    }

    default boolean checkIfListContainsAllExpectedElements(List<WebElement> listtoSearch, List<String> listaexpected) {

        for (int repeat = 0; repeat <= 3; repeat++) {  //stale reference exception

            try {
                List<String> convertedlist = convertWebElementsListToString(listtoSearch);
                if (convertedlist.equals(listaexpected)) {
                    System.out.println("Actual Values are the same");
                    return true;


                } else {
                    convertedlist.removeAll(listaexpected);
                    System.out.println("Value are not the same - Values from website that are different than expected " + convertedlist);
                    return false;
                }

            } catch (Exception x) {
                System.out.println("Elements haven't been displayed");
            }
        }
        return true;
    }

    default boolean clickEqualsListElement(List<WebElement> list, String value) {

        for (WebElement we : list) {
            if (we.getText().equals(value)) {
                we.click();
                return true;
            }
        }
        System.out.println("Element hasn't been found in the list of provided elements.");
        return false;
    }

    default String returnRandomSize(List<WebElement> webElementList) {

        List<String> convertedList = convertWebElementsListToString(webElementList);
        Random r = new Random();
        int randomItem = r.nextInt(convertedList.size());
        return convertedList.get(randomItem);
    }

    default void clickIn(WebElement element, Integer... time) {
        Integer periodOfTime = time.length > 0 ? time[0] : 30;
        new WebDriverWait(GetDriver(), 30)
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

        new WebDriverWait(GetDriver(), 30)
                .until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        element.sendKeys(value);
        System.out.println("input value = " + (value));

    }

    default void mouseHover(WebElement x) {
        Actions action = new Actions(GetDriver());
        action.moveToElement(x).perform();
    }

    default void clickOnDropDownList(WebElement element) {
        for (int i = 0; i < 2; i++) {
            try {
                element.click();
                break;
            } catch (Exception e) {
                System.out.println("Exception caught");

            }
        }
    }

    default List<String> convertWebElementsListToString(List<WebElement> listOfWebElements) {

        List<String> FilterTextList = new ArrayList<>();

        for (WebElement we : listOfWebElements) {
            FilterTextList.add(we.getText());
        }
        return FilterTextList;
    }

    default List<Double> convertWebElementsListToDouble(List<WebElement> listOfWebElements) {

        List<Double> ListofAllPrices = new ArrayList<>();
        for (WebElement x : listOfWebElements) {

            if (x.isDisplayed()) {
                ListofAllPrices.add((Double.valueOf(x.getText().replace(",", ".").replace(" zł", "").replaceAll("\\s", ""))));
            }
        }
        return ListofAllPrices;
    }

    default boolean ListAreEqual(List<String> l1, List<String> l2) {

        if (l2.equals(l1)) {
            System.out.println("Elements are equal");
            return true;
        } else {
            System.out.println("Elements are not equal");
            return false;
        }
    }

    default boolean checkIfElementsSizeIsMoreThan0(List<WebElement> elements) {

        if (elements.size() == 0) {

            System.out.println("Size of element is 0");
            return false;
        }
        return true;
    }

    default By ConvertStringToXpath(String elementToFullfillXpath, String xpathInstring) {

        return By.xpath((String.format(xpathInstring, elementToFullfillXpath)));
    }

    default boolean verifyElementsFromListAreDisplayed(List<WebElement> elements) {
        int counter = 1;
        for (WebElement el : elements) {
            counter++;
            new Actions(GetDriver())
                    .moveToElement(el).perform();
            if (!el.isDisplayed()) {

                System.out.println(" Element number " + counter + "with text " + el.getText() + " hasn't been displayed");
                return false;
            }
        }
        System.out.println("Comments have been displayed");
        return true;
    }

    default boolean verifyElementsAreDisplayed(WebElement... elements) {

        List<WebElement> list = Arrays.asList(elements);

        for (WebElement el : list) {

            if (!el.isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    default boolean verifyElementAttribute(List<WebElement> elements, String attribute, String expectedValue) {

        for (WebElement x : elements) {
            if (x.isDisplayed()) {
                String attributevalue = x.getAttribute(attribute);
                System.out.println("Element attribute value is" + attributevalue);
                new WebDriverWait(GetDriver(), 30)
                        .until(ExpectedConditions.visibilityOf(x));
                if (!attributevalue.equals(expectedValue)) {
                    return false;

                }
            }
        }
        return true;
    }

    default void swichToIframe(String iframe) {

        GetDriver().switchTo().frame(iframe);
        System.out.print("Script has jumped into Iframe");
    }

    WebDriver GetDriver();
}
