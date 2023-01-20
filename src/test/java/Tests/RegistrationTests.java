package Tests;
import Base.BaseTest;
import Data.RegistrationData;
import Interfaces.IHelper;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;


public class RegistrationTests extends BaseTest implements IHelper {

    @Test
    public void RegisterNewUser() {
        mainpage.clickOnSaleBanner();
        registrationPage = mainpage.clickInRegistrationButton();

        registrationPage.
                setUserName(RegistrationData.USERNAME).
                setSurnameField(RegistrationData.USERSURNAME).
                setPasswordField(RegistrationData.PASSWORD).
                setConfirmationPasswordField(RegistrationData.PASSWORD).
                setEmailField(RegistrationData.EMAIL).
                clickInSubscriptionField().
                clickInStatementField();

        //check no error
        Assert.assertTrue(registrationPage.getErrorMessageList().isEmpty());


    }

    @Test
    public void InvalidCredentialEmptyNameField() {
        mainpage.clickOnSaleBanner();
        registrationPage = mainpage.clickInRegistrationButton();

        registrationPage.
                setSurnameField(RegistrationData.USERSURNAME).
                setPasswordField(RegistrationData.PASSWORD).
                setConfirmationPasswordField(RegistrationData.PASSWORD).
                setEmailField(RegistrationData.EMAIL).
                clickInSubscriptionField().
                clickInStatementField().
                createAccount();

        //check size of error messenge and value;
        Assert.assertEquals(registrationPage.getErrorMessageList().size(), 1);
        Assert.assertTrue(verifyElementExistInList(registrationPage.getErrorMessageList(), "To pole jest wymagane"));


    }

    @Test
    public void InvalidCredentialEmptySurnameField() {
        mainpage.clickOnSaleBanner();
        registrationPage = mainpage.clickInRegistrationButton();

        registrationPage.
                setNameField(RegistrationData.USERNAME).
                setPasswordField(RegistrationData.PASSWORD).
                setConfirmationPasswordField(RegistrationData.PASSWORD).
                setEmailField(RegistrationData.EMAIL).
                clickInSubscriptionField().
                clickInStatementField().
                createAccount();

        //check size of error messenge and value;
        Assert.assertEquals(registrationPage.getErrorMessageList().size(), 1);
        Assert.assertTrue(verifyElementExistInList(registrationPage.getErrorMessageList(), "To pole jest wymagane"));


    }

    @Test
    public void InvalidCredentialEmptyEmailField() {
        mainpage.clickOnSaleBanner();
        registrationPage = mainpage.clickInRegistrationButton();

        registrationPage.
                setNameField(RegistrationData.USERNAME).
                setSurnameField(RegistrationData.USERSURNAME).
                setPasswordField(RegistrationData.PASSWORD).
                setConfirmationPasswordField(RegistrationData.PASSWORD).
                clickInSubscriptionField().
                clickInStatementField().
                createAccount();

        //check size of error messenge and value;
        Assert.assertEquals(registrationPage.getErrorMessageList().size(), 1);
        Assert.assertTrue(verifyElementExistInList(registrationPage.getErrorMessageList(), "To pole jest wymagane"));



    }

    @Test
    public void InvalidCredentialWrongEmailField() {
        mainpage.clickOnSaleBanner();
        registrationPage = mainpage.clickInRegistrationButton();

        registrationPage.setNameField(RegistrationData.USERNAME).
                setSurnameField(RegistrationData.USERSURNAME).
                setPasswordField(RegistrationData.PASSWORD).
                setConfirmationPasswordField(RegistrationData.PASSWORD).
                setEmailField(RegistrationData.WRONGEMAIL).
                clickInSubscriptionField().
                clickInStatementField().
                createAccount();

        //check size of error messenge and value;
        Assert.assertEquals(registrationPage.getErrorMessageList().size(), 1);
        Assert.assertTrue(verifyElementExistInList(registrationPage.getErrorMessageList(), "Adres e-mail jest niepoprawny"));


    }


    @Test
    public void InvalidCredentialNotTheSamePasswordsFields() {
        mainpage.clickOnSaleBanner();
        registrationPage = mainpage.clickInRegistrationButton();

        registrationPage.setNameField(RegistrationData.USERNAME).
                setSurnameField(RegistrationData.USERSURNAME).
                setPasswordField(RegistrationData.PASSWORD).
                setConfirmationPasswordField(RegistrationData.PASSWORD + "123").
                setEmailField(RegistrationData.EMAIL).
                clickInSubscriptionField().
                clickInStatementField().
                createAccount();

        //check size of error messenge and value;
        Assert.assertEquals(1, registrationPage.getErrorMessageList().size());
        Assert.assertTrue(verifyElementExistInList(registrationPage.getErrorMessageList(), "Prosimy upewnić się, że hasła pasują do siebie."));


    }

    @Test
    public void InvalidCredentialPasswordToShortFields() {
        mainpage.clickOnSaleBanner();
        registrationPage = mainpage.clickInRegistrationButton();

        registrationPage.setNameField(RegistrationData.USERNAME).
                setSurnameField(RegistrationData.USERSURNAME).
                setPasswordField(RegistrationData.PASSWORD.substring(0, 3)).
                setConfirmationPasswordField(RegistrationData.PASSWORD).
                setEmailField(RegistrationData.EMAIL).
                clickInSubscriptionField().
                clickInStatementField().
                createAccount();

        //check size of error messenge and value;
        Assert.assertEquals(2, registrationPage.getErrorMessageList().size());
        Assert.assertTrue(verifyElementExistInList(registrationPage.getErrorMessageList(), "Hasło musi mieć co najmniej 6 znaków"));


    }

    @Test
    public void NoErrorMessageSubscriptionField() {
        mainpage.clickOnSaleBanner();
        registrationPage = mainpage.clickInRegistrationButton();

        registrationPage.
                setUserName(RegistrationData.USERNAME).
                setSurnameField(RegistrationData.USERSURNAME).
                setPasswordField(RegistrationData.PASSWORD).
                setConfirmationPasswordField(RegistrationData.PASSWORD).
                setEmailField(RegistrationData.EMAIL).
                clickInStatementField();

        //check no error
        Assert.assertTrue(registrationPage.getErrorMessageList().isEmpty());


    }


    @Override
    public WebDriver GetDriver() {
        return this.driver;
    }
}
