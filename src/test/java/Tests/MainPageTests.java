package Tests;

import Base.BaseTest;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class MainPageTests extends BaseTest {


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
    public void checkHeaders() throws InterruptedException {
        mainpage.headersVerification( "NOWOŚCI", "DAMSKIE", "MĘSKIE", "DZIECIĘCE", "SPORT", "AKCESORIA", "PREMIUM", "TOREBKI", "WYPRZEDAŻ");
        mainpage.verifyList("WYPRZEDAŻ");


    }

}
