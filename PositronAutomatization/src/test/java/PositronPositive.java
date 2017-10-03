import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class PositronPositive {

    /*Валидные данные для ввода в поля регистрации шага 1*/
    String s = "Surname";//Фамилия
    String n = "Name";//Имя
    String m = "MiddleName";//Отчество
    String e1 = "TestMail";//Первая часть е-mail
    int e2 = 29;//Нумерация тестового е-mail
    String e3 = "@mail";//Вторая часть е-mail
    String e4 = ".com";//Третья часть е-mail
    String p = "123456";//Пароль
    String t = "9999999999";//Номер телефона

    @Test//Тест валидной регистрации
    public void PositronRegistrationStepOne(){

        /*ПЕРВЫЙ ШАГ РЕГИСТРАЦИИ*/
        /*Вывод статуса тестирования в консоль*/
        System.out.println(" ");
        System.out.println("_______________________________________________");
        System.out.println("TESTING REGISTRATION STEP ONE - START!");

        /*Запуск SeleniumWebDriver и открытие окна браузера*/
        System.setProperty("webdriver.chrome.driver", "C:\\SeleniumDrivers\\chromedriver.exe"); //путь к ChromeDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize(); //Раскрытие окна браузера во весь экран
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); //Ожидание раскрытия окна
        driver.navigate().to("http://positron.dektry.com/"); //Ссылка
        System.out.println("_______________________________________________");
        System.out.println("Open page - Done");

        /*Поиск и нажатие на кнопку Регистрации*/
        WebElement searchRegistrationButton = driver.findElement(By.xpath(".//*[@id='navbar']/div/a"));
        searchRegistrationButton.click();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        System.out.println("Redirect to Registration Step One - Done");

        /*Поиск и ввод данных в поле "Фамилия"*/
        WebElement inputSurname = driver.findElement(By.xpath("html/body/sd-app/div/ng-component/div/div[1]/form/div[1]/input"));
        inputSurname.sendKeys(s + e2);
        System.out.println("Surname - Done");

        /*Поиск и ввод данных в поле "Имя"*/
        WebElement inputName = driver.findElement(By.xpath("html/body/sd-app/div/ng-component/div/div[1]/form/div[2]/div[1]/input"));
        inputName.sendKeys(n + e2);
        System.out.println("Name - Done");

        /*Поиск и ввод данных в поле "Отчество"*/
        WebElement inputMiddleName = driver.findElement(By.xpath("html/body/sd-app/div/ng-component/div/div[1]/form/div[2]/div[2]/input"));
        inputMiddleName.sendKeys(m + e2);
        System.out.println("Middle name - Done");

        /*Поиск и ввод данных в поле "Электронная почта"*/
        WebElement inputEmail = driver.findElement(By.xpath("html/body/sd-app/div/ng-component/div/div[1]/form/div[3]/input"));
        inputEmail.sendKeys(e1 + e2 + e3 + e4);
        System.out.println("E-mail - Done");

        /*Поиск и ввод данных в поле "Пароль"*/
        WebElement inputPassword = driver.findElement(By.xpath("html/body/sd-app/div/ng-component/div/div[1]/form/div[4]/input"));
        inputPassword.sendKeys(p);
        System.out.println("Password - Done");

        /*Поиск и отображение пароля без маски*/
        WebElement buttonHidenPassword = driver.findElement(By.xpath("html/body/sd-app/div/ng-component/div/div[1]/form/div[4]/i"));
        buttonHidenPassword.click();
        System.out.println("Open password - Done");

        /*Поиск и ввод данных в поле "Номер телефона"*/
        WebElement inputTelephone = driver.findElement(By.xpath("html/body/sd-app/div/ng-component/div/div[1]/form/div[5]/p-inputmask/input"));
        inputTelephone.sendKeys(t);
        System.out.println("Telephone - Done");

        /*Поиск и актичация чекбокса "Политика Конфиденциальности"*/
        JavascriptExecutor js = (JavascriptExecutor)driver;//Скрипт нажатия на скрытый элемент(Строка1)!!!
        js.executeScript("arguments[0].click();", driver.findElement(By.id("privacyPolicy")));//Скрипт нажатия на скрытый элемент(Строка2)!!!
        System.out.println("Privacy policy - Done");

        /*Поиск и подтверждение регистрации*/
        WebElement buttonConfirm = driver.findElement(By.xpath("html/body/sd-app/div/ng-component/div/div[1]/form/button"));
        buttonConfirm.click();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        /*Переход к зваершению первого шага регистрации*/
        WebElement textHeader = driver.findElement(By.xpath("html/body/sd-app/div/ng-component/div/div[1]/p[1]"));
        textHeader.click();//Проверка появления элементов на странице

        /*Поиск и завершение первого шага регистрации*/
        WebElement buttonEndRegistration = driver.findElement(By.xpath("html/body/sd-app/div/ng-component/div/div[1]/form/a"));
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        buttonEndRegistration.click();
        System.out.println("End Registration - Done");

        /*Подтверждение открытия страницы второго шага регистрации*/
        WebElement buttonNumber = driver.findElement(By.xpath(".//*[@id='progress1']/li[1]/div"));
        buttonNumber.click();
        System.out.println("Open page Registration step two - Done");

        driver.close();//Закрытие окна
        System.out.println("Close the window - Done");

        System.out.println("***********************************************");
        System.out.println("REGISTRATION STEP ONE - DONE");
        System.out.println("***********************************************");
        System.out.println("TEST COMPLETED");
        System.out.println("_______________________________________________");
    }
}