package Pages;

import Base.BasePage;
import Interfaces.IHelper;
import Interfaces.IWeiters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class RegistrationPage extends BasePage implements IHelper, IWeiters {


    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath="//input[@data-testid='register-first-name-field']")
    private WebElement NameField;
    @FindBy(xpath="//input[@data-testid='register-last-name-field']")
    private WebElement SurnameField;
    @FindBy(xpath="//input[@data-testid='register-email-field']")
    private WebElement EmailField;
    @FindBy(xpath="//input[@data-testid='register-password-field']")
    private WebElement PasswordField;
    @FindBy(xpath="//input[@data-testid='register-password-confirmation-field']")
    private WebElement ConfirmationPasswordField;
    @FindBy(xpath="//label[@for='is_subscribed']")
    private WebElement SubscriptionButton;
    @FindBy(xpath="//label[@for='statement']")
    private WebElement Statement;
    @FindBy(xpath="//span[@class='form-error']")
    private List<WebElement> ErrorMessage;
    @FindBy(xpath="//button[@data-testid='register-create-account-button']")
    private WebElement CreateAccountButton;
    @FindBy(xpath="//ul[@id='vs1__listbox']/li")
    private WebElement ListOfWayToSortPrice;


    public List<WebElement> getErrorMessageList(){

        return ErrorMessage;
    }

    public RegistrationPage setNameField(String nameField){
        wait.until(ExpectedConditions.elementToBeClickable(NameField));
        NameField.click();
        NameField.sendKeys(nameField);

        return this;
    }

    public RegistrationPage setSurnameField(String surnameField){
        wait.until(ExpectedConditions.elementToBeClickable(SurnameField));
        SurnameField.click();
        SurnameField.sendKeys(surnameField);

        return this;
    }

    public RegistrationPage setEmailField(String emailField){
        wait.until(ExpectedConditions.elementToBeClickable(EmailField));
        EmailField.click();
        EmailField.sendKeys(emailField);
        return this;
    }

    public RegistrationPage setPasswordField(String password){
        wait.until(ExpectedConditions.elementToBeClickable(PasswordField));
        PasswordField.click();
        PasswordField.sendKeys(password);

        return this;
    }

    public RegistrationPage setConfirmationPasswordField(String passwordconfirmation){
        wait.until(ExpectedConditions.elementToBeClickable(ConfirmationPasswordField));
        ConfirmationPasswordField.click();
        ConfirmationPasswordField.sendKeys(passwordconfirmation);

        return this;
    }
    public RegistrationPage clickInSubscriptionField(){
        wait.until(ExpectedConditions.elementToBeClickable(SubscriptionButton));
        SubscriptionButton.click();

        return this;
    }
    public RegistrationPage clickInStatementField(){
        wait.until(ExpectedConditions.elementToBeClickable(Statement));
        Statement.click();

        return this;
    }
    public RegistrationPage setUserName(String userName){

        this.NameField.click();
        this.NameField.sendKeys(userName);
        return this;
    } public RegistrationPage createAccount(){

        this.CreateAccountButton.click();

        return this;
    }

    @Override
    public WebDriver GetDriver() {
        return this.driver;
    }
}
