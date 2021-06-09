package bpc.webtest;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class App {

    private static WebDriver driver;
    private static final String LOGIN_PAGE_URL = "https://crm.geekbrains.space/user/login";
    private static final String USER_LOGIN = "user";
    private static final String USER_PASSWORD = "1234";
    private static final String expensesMenu = "//ul[contains(@class,'nav-multilevel')]/li[contains(., 'Контрагенты')]";
    private static final String expensesSubmenu = "//li[@data-route='crm_contact_index']/a";
    private static final String createButton = "//a[@title='Создать контактное лицо']";
    private static final String orgChosen = "//span[@class='select2-arrow']";
    private static final String orgInput = "//input[contains (@class, 'select2-input')]";
    private static final String orgResult = "//div[@class='select2-result-label']";
    private static final String saveButton = "//button[contains(.,'Сохранить и закрыть')]";
    private static final String message = "//div[contains (@class, 'alert-success')]";


    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        login();

       //Контрагенты-Контактные лица
       Actions actionContractor = new Actions(driver);
       WebElement contractorMenu = driver.findElement(By.xpath(expensesMenu));
       actionContractor.moveToElement(contractorMenu).perform();

       driver.findElement(By.xpath(expensesSubmenu)).click();

       //click button Создать контактное лицо
       driver.findElement(By.xpath(createButton)).click();


       //Заполнение Фамилия, Проверка
       WebElement fieldLastName = driver.findElement(By.name("crm_contract[lastName]"));
       fieldLastName.sendKeys("Павлов");
       System.out.println("Поле Фамилия заполнено: " + !fieldLastName.getAttribute("value").isEmpty());
       System.out.println("------------------------");

       //Заполнение Имя, Проверка
       WebElement fieldFirstName = driver.findElement(By.name("crm_contract[firstName]"));
       fieldFirstName.sendKeys("Павел");
       System.out.println("Поле Имя заполнено: " + !fieldFirstName.getAttribute("value").isEmpty());
       System.out.println("------------------------");

       //Заполнение Организация, Проверка
       WebElement fieldOrganisation = driver.findElement(By.name("crm_contact[company]"));

       driver.findElement(By.xpath(orgChosen)).click();
       driver.findElement(By.xpath(orgInput)).sendKeys("104");
       new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(orgResult))));
       driver.findElement(By.xpath(orgInput)).sendKeys(Keys.ENTER);

       System.out.println("Поле организации заполнено: " + !fieldOrganisation.getAttribute("value").isEmpty());
       System.out.println("------------------------");

       //Заполнение Должность, Проверка
        WebElement fieldJobTitle = driver.findElement(By.name("crm_contact[jobTitle]"));
        fieldJobTitle.sendKeys("менеджер");
        System.out.println("Поле Должность заполнено: " + !fieldJobTitle.getAttribute("value").isEmpty());
        System.out.println("------------------------");

        //click button Сохранить и Закрыть
        driver.findElement(By.xpath(saveButton)).click();












        driver.get("https://google.com");






    }

    private static void login() {

        driver.get(LOGIN_PAGE_URL);

        driver.manage().window().maximize();

        WebElement loginInput = driver.findElement(By.name("_username"));
        loginInput.sendKeys(USER_LOGIN);

        WebElement passwordInput = driver.findElement(By.name("_password"));
        loginInput.sendKeys(USER_PASSWORD);

        WebElement loginButton = driver.findElement(By.xpath(".//button[@name='_submit']"));
        loginInput.click();


    }






}