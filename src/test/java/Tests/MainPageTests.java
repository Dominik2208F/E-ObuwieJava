package Tests;

import Base.BaseTest;
import Interfaces.IHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class MainPageTests extends BaseTest {

//działa wszystko na raz
    @Test
    public void checkTittle(){
        driver.get("https://www.eobuwie.pl");
        assertEquals("Sklep internetowy eobuwie.pl • Modne buty, akcesoria i torebki online | eobuwie.pl",driver.getTitle());
    }
    @Test
    public void checkUrl(){
        assertEquals("https://www.eobuwie.com.pl/",driver.getCurrentUrl());
    }
    @Test
    public void checkHeaders()  {

        Assert.assertTrue(mainpage.checkIfListContainsAllExpectedElements(mainpage.GetActualHeaders(),Arrays.asList("NOWOŚCI", "DAMSKIE", "MĘSKIE", "DZIECIĘCE", "SPORT", "AKCESORIA", "PREMIUM", "TOREBKI", "WYPRZEDAŻ")));
        Assert.assertTrue(mainpage.verifyElementExistInList(mainpage.GetActualHeaders(),"WYPRZEDAŻ"));
        }
    }


