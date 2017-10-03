import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class PositronPositive2 {

    /*Валидные данные для ввода в поля регистрации из шага 1*/
    String surname = "Surname";//Фамилия
    String name = "Name";//Имя
    String middleName = "MiddleName";//Отчество
    String mailBody = "TestMail";//Первая часть е-mail
    int e2 = 25;//Нумерация тестового е-mail
    String e3 = "@mail";//Вторая часть е-mail
    String e4 = ".com";//Третья часть е-mail
    String password = "123456";//Пароль
    String telephonNumber = "9999999999";//Номер телефона

    /*Валидные данные для ввода в поля регистрации шага 2*/
    String inn = "1111111119";//ИНН, Средняя штатная численность
    String company = "Компания";//Краткое название
    String activities = "Производство пищевых продуктов ";//Вид деятельности
    String governmentAgency = "Государственный орган";//Орган гос. регистрации, Кем выдан
    String registrationDate = " 11112011";//Дата регистрации, Когда выдан
    String birthDate = " 11111980";//Дата рождения, Дата рождения
    String ogrn = "111111111111111";//ОГРН
    String taxAuthority = "Налоговый орган";//Налоговый орган
    String country = "Россия";//Страна
    String region = "Московский";//Регион
    String zip = "220022";//Индекс
    String street = "Местная";//Улица
    String c5 = "22";//Дом, корпус, Доля директора
    String account = "11111111111111111111";//Корреспондентский счёт
    String bank = "Сбербанк";//Банк
    String bik = "044525593";//Бик
    String passportNumber = "КН123456789";//Номер распорта
    String file = "C:/Users/sergei/Desktop/09.jpg";
    String err = "Поля КПП нет";

    /*Тест валидной регистрации*/
    @Test
    public void PositronRegistrationStepTwo() {

        /*ВХОД В СИСТЕМУ*/
        /*Вывод статуса тестирования в консоль*/
        System.out.println(" ");
        System.out.println("_______________________________________________");
        System.out.println("TESTING LOGIN - START!");

        /*Запуск SeleniumWebDriver и открытие окна браузера*/
        System.setProperty("webdriver.chrome.driver", "C:\\SeleniumDrivers\\chromedriver.exe"); //путь к ChromeDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize(); //Раскрытие окна браузера во весь экран
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); //Ожидание раскрытия окна
        driver.navigate().to("http://positron.dektry.com/"); //Ссылка
        System.out.println("_______________________________________________");
        System.out.println("Open page - Done");

        /*Поиск и нажатие на кнопку "Логин(Войти)"*/
        WebElement buttonLogin = driver.findElement(By.xpath(".//*[@id='navbar']/a[2]"));
        buttonLogin.click();
        System.out.println("Login - Done");

        /*Поиск и ввод данных в поле "Электронная адрес"*/
        WebElement inputEmail = driver.findElement(By.xpath("html/body/sd-app/div/ng-component/div/div[1]/form/div[1]/input"));
        inputEmail.sendKeys(mailBody + e2 + e3 + e4);
        System.out.println("Login e-mail - Done");

        /*Поиск и ввод данных в поле "Пароль"*/
        WebElement inputPassword = driver.findElement(By.xpath("html/body/sd-app/div/ng-component/div/div[1]/form/div[2]/input"));
        inputPassword.sendKeys(password);
        System.out.println("Login password - Done");

        /*Поиск и отображение пароля без маски*/
        WebElement buttonHidenPassword = driver.findElement(By.xpath("html/body/sd-app/div/ng-component/div/div[1]/form/div[2]/i"));
        buttonHidenPassword.click();
        System.out.println("Login open password - Done");

        /*Поиск и нажатие на кнопку "Войти"*/
        WebElement buttonEnter = driver.findElement(By.xpath("html/body/sd-app/div/ng-component/div/div[1]/form/button"));
        buttonEnter.click();
        System.out.println("LOGIN - DONE");

        /*ВТОРОЙ ШАГ РЕГИСТРАЦИИ*/
        /*Вывод статуса тестирования в консоль*/
        System.out.println("_______________________________________________");
        System.out.println("TESTING REGISTRATION STEP TWO - START!");
        System.out.println(" ");

        /*ЭТАП 1*/
        /*Поиск и ввод ИНН*/
        System.out.println("[STAGE_1]");
        WebElement inputInnNumber = driver.findElement(By.xpath(".//*[@id='step1']/div/div/form/div/div/div[1]/div[2]/input"));
        inputInnNumber.sendKeys(inn + e2);
        WebDriverWait wait = new WebDriverWait(driver, 15);//Ожидание кликабельного элемента(Строка 1)!!!!!!
        wait.until(ExpectedConditions.elementToBeClickable(By.id("inn")));//Ожидание кликабельного элемента(Строка 2)!!!!!!
        JavascriptExecutor js1 = (JavascriptExecutor) driver;//Скрипт нажатия на скрытый элемент(Строка1)!!!
        js1.executeScript("arguments[0].click();", driver.findElement(By.id("inn")));//Скрипт нажатия на скрытый элемент(Строка2)!!!
        System.out.println("INN - Done");

        System.out.println("[STAGE_1] - Done");
        System.out.println("...............................................");

        /*ЭТАП 2-1*/
        System.out.println("[STAGE_2.1]");

        /*Краткое название - компании*/
        WebElement inputShortName = driver.findElement(By.xpath(".//*[@id='shortName']"));
        inputShortName.sendKeys(company + e2);
        System.out.println("Short name - Done");

        /*Средняя численность - компании*/
        WebElement inputQuant = driver.findElement(By.xpath(".//*[@id='stateCount']"));
        inputQuant.sendKeys(inn);
        System.out.println("Quantity - Done");

        /*Вид деятельности - компании*/
        WebElement inputActivities = driver.findElement(By.xpath(".//*[@id='step2']/div/div/form/div[2]/div/div[1]/div[1]/p-autocomplete/span/input"));
        inputActivities.sendKeys(activities);
        System.out.println("Activities - Done");

        /*Деятельность подлежит лицензированию - компании*/
        JavascriptExecutor js2 = (JavascriptExecutor) driver;//Скрипт нажатия на скрытый элемент(Строка1)!!!
        js2.executeScript("arguments[0].click();", driver.findElement(By.id("is-license")));//Скрипт нажатия на скрытый элемент(Строка2)!!!
        System.out.println("License - Done");

        /*Орган государственной регистрации - компании*/
        WebElement inputGos = driver.findElement(By.xpath(".//*[@id='registrationAuthority']"));
        inputGos.sendKeys(governmentAgency);
        System.out.println("Gos-vo - Done");

        /*Дата регистрации - компании*/
        WebElement inputRegistrationDate = driver.findElement(By.xpath(".//*[@id='step2']/div/div/form/div[3]/div/div[2]/div/p-inputmask/input"));
        inputRegistrationDate.sendKeys(registrationDate);
        System.out.println("Reg.Date - Done");

        /*ОГРН - компании*/
        WebElement inputOgrn = driver.findElement(By.xpath(".//*[@id='ogrn']"));
        inputOgrn.sendKeys(ogrn);
        System.out.println("OGRN - Done");

        /*Налоговый орган - компании*/
        WebElement inputTaxAuthority = driver.findElement(By.xpath(".//*[@id='taxAuthority']"));
        inputTaxAuthority.sendKeys(taxAuthority);
        System.out.println("Tax authority - Done");

        /*Страна - компании*/
        WebElement inputCountry1 = driver.findElement(By.xpath(".//*[@id='company-country']"));
        inputCountry1.sendKeys(country + 1);
        System.out.println("Country - Done");

        /*Регион - компании*/
        WebElement inputRegion1 = driver.findElement(By.xpath(".//*[@id='company-region']"));
        inputRegion1.sendKeys(region + 1);
        System.out.println("Region - Done");

        /*Индекс - компании*/
        WebElement inputZip1 = driver.findElement(By.xpath(".//*[@id='company-zip']"));
        inputZip1.sendKeys(zip);
        System.out.println("Zip - Done");

        /*Улица - компании*/
        WebElement inputStreet1 = driver.findElement(By.xpath(".//*[@id='company-street']"));
        inputStreet1.sendKeys(street + 1);
        System.out.println("Street - Done");

        /*Дом - компании*/
        WebElement inputHouse1 = driver.findElement(By.xpath(".//*[@id='company-house']"));
        inputHouse1.sendKeys(c5);
        System.out.println("House - Done");

        /*Корпус - компании*/
        WebElement inputHouseBlock = driver.findElement(By.xpath(".//*[@id='company-housing']"));
        inputHouseBlock.sendKeys(c5);
        inputHouseBlock.clear();
        System.out.println("House block - Done");

        /*Поиск и проверка наличия поля КПП и Капитал*/
        try {
            WebElement inputKpp = driver.findElement(By.xpath(".//*[@id='kpp']"));
            WebElement inputCapitalSize = driver.findElement(By.xpath(".//*[@id='capitalSize']"));

            if (inputKpp.isDisplayed()) {
                inputKpp.sendKeys("111111111");
                System.out.println("KPP - Done");
            }
            else {
                System.out.println("No KPP field");
            }

            if (inputCapitalSize.isDisplayed()){
                inputCapitalSize.sendKeys("111111111");
                System.out.println("CapitalSize - Done");
            }
            else{
                System.out.println("No CapitalSize field");
            }
        }
        catch (NoSuchElementException e) {
            System.out.println(" ");
        }

        /*Поиск и нажатие на кнопку "Далее"*/
        JavascriptExecutor js3 = (JavascriptExecutor) driver;//Скрипт нажатия на скрытый элемент(Строка1)!!!
        js3.executeScript("arguments[0].click();", driver.findElement(By.xpath(".//*[@id='step2']/div/div/form/div[6]/div/div[2]/button")));//Скрипт нажатия на скрытый элемент(Строка2)!!!

        System.out.println("[STAGE_2.1] - Done");
        System.out.println("...............................................");

        /*ЭТАП 2-2*/
        System.out.println("[STAGE_2.2]");

        /*Корреспондентский счет - компании*/
        WebElement inputCorrespondentAccount = driver.findElement(By.xpath(".//*[@id='correspondentAccount']"));
        inputCorrespondentAccount.sendKeys(account);
        System.out.println("Correspondent account - Done");

        /*Расчетный счет - компании*/
        WebElement inputCheckingAccount = driver.findElement(By.xpath(".//*[@id='checkingAccount']"));
        inputCheckingAccount.sendKeys(account);
        System.out.println("Checking account - Done");

        /*Банк - компании*/
        WebElement inputBank = driver.findElement(By.xpath(".//*[@id='bank']"));
        inputBank.sendKeys(bank);
        System.out.println("Bank - Done");

        /*БИК - компании*/
        WebElement inputBik = driver.findElement(By.xpath(".//*[@id='bik']"));
        inputBik.sendKeys(bik);
        System.out.println("Bik - Done");

        /*Загрузить по Бик(Автозаполнение)*/
        WebElement buttonBik = driver.findElement(By.xpath(".//*[@id='step3']/div/div/form/div[1]/div/div[4]/button"));
        buttonBik.click();
        System.out.println("Autocomplete - Done");

        /*Поиск и нажатие на кнопку "Далее"*/
        JavascriptExecutor js4 = (JavascriptExecutor) driver;//Скрипт нажатия на скрытый элемент(Строка1)!!!
        js4.executeScript("arguments[0].click();", driver.findElement(By.xpath(".//*[@id='step3']/div/div/form/div[2]/div/div[2]/button")));//Скрипт нажатия на скрытый элемент(Строка2)!!!

        System.out.println("[STAGE_2.2] - Done");
        System.out.println("...............................................");

        /*ЭТАП 3-1*/
        /*Форма информация "О директоре"*/
        System.out.println("[STAGE_3.1]");

        /*Фамилия - Директор*/
        WebElement inputSurname1 = driver.findElement(By.xpath(".//*[@id='directorLastName']"));
        inputSurname1.sendKeys(surname + 1);
        System.out.println("Director surname - Done");

        /*Имя - Директор*/
        WebElement inputName1 = driver.findElement(By.xpath(".//*[@id='directorFirstName']"));
        inputName1.sendKeys(name + 1);
        System.out.println("Director name - Done");

        /*Отчество - Директор*/
        WebElement inputMiddleName1 = driver.findElement(By.xpath(".//*[@id='directorPatronymic']"));
        inputMiddleName1.sendKeys(middleName + 1);
        System.out.println("Director middle name - Done");

        /*Паспорт - Директор*/
        WebElement inputPassport1 = driver.findElement(By.xpath(".//*[@id='directorPassportNumber']"));
        inputPassport1.sendKeys(passportNumber);
        System.out.println("Director passport - Done");

        /*Когда выдан*/
        WebElement inputWhen1 = driver.findElement(By.xpath(".//*[@id='step4']/div/div/form/div[2]/div[1]/div/div[2]/div/p-inputmask/input"));
        inputWhen1.sendKeys(registrationDate);
        System.out.println("Director. When issued passport - Done");

        /*Кем выдан - Директор*/
        WebElement inputWho1 = driver.findElement(By.xpath(".//*[@id='directorPassportAuthor']"));
        inputWho1.sendKeys(governmentAgency);
        System.out.println("Director. Who issued passport - Done");

        /*Дата рождения - Директор*/
        WebElement inputBirthday1 = driver.findElement(By.xpath(".//*[@id='step4']/div/div/form/div[2]/div[2]/div/div/div/p-inputmask/input"));
        inputBirthday1.sendKeys(birthDate);
        System.out.println("Director birthday - Done");

        /*Доля - Директор*/
        WebElement inputDirectorPart1 = driver.findElement(By.xpath(".//*[@id='partDirector']"));
        inputDirectorPart1.sendKeys(c5);
        System.out.println("Director part - Done");

        /*Чекбокс единственный владелец - Директор*/
        JavascriptExecutor js5 = (JavascriptExecutor) driver;//Скрипт нажатия на скрытый элемент(Строка1)!!!
        js5.executeScript("arguments[0].click();", driver.findElement(By.id("isOwner")));//Скрипт нажатия на скрытый элемент(Строка2)!!!
        System.out.println("Director is owner - Done");

        /*Контактный телефон - Директор*/
        WebElement inputDirectorTelephone1 = driver.findElement(By.xpath(".//*[@id='step4']/div/div/form/div[4]/div/div[1]/div/p-inputmask/input"));
        inputDirectorTelephone1.sendKeys(telephonNumber);
        System.out.println("Director telephone - Done");

        /*Электронная почта - Директор*/
        WebElement inputDirectorEmail1 = driver.findElement(By.xpath(".//*[@id='directorEmail']"));
        inputDirectorEmail1.sendKeys(mailBody + 1 + e3 + e4);
        System.out.println("Director e-mail - Done");

        /*Поиск и нажатие на кнопку "Далее"*/
        JavascriptExecutor js6 = (JavascriptExecutor) driver;//Скрипт нажатия на скрытый элемент(Строка1)!!!
        js6.executeScript("arguments[0].click();", driver.findElement(By.xpath(".//*[@id='step4']/div/div/form/div[5]/div/div[2]/button")));//Скрипт нажатия на скрытый элемент(Строка2)!!!

        System.out.println("[STAGE_3.1] - Done");
        System.out.println("...............................................");

        /*ЭТАП 3-2*/
        /*Форма информация "О довереном лице"*/
        System.out.println("[STAGE_3.2]");

        /*Фамилия - Доверенное лицо*/
        WebElement inputSurname2 = driver.findElement(By.xpath(".//*[@id='confidantLastName']"));
        inputSurname2.sendKeys(surname + 2);
        System.out.println("Trustee surname - Done");

        /*Имя - Доверенное лицо*/
        WebElement inputName2 = driver.findElement(By.xpath(".//*[@id='confidantFirstName']"));
        inputName2.sendKeys(name + 2);
        System.out.println("Trustee name - Done");

        /*Отчество - Доверенное лицо*/
        WebElement inputMiddleName2 = driver.findElement(By.xpath(".//*[@id='confidantPatronymic']"));
        inputMiddleName2.sendKeys(middleName + 2);
        System.out.println("Trustee middle name - Done");

        /*Паспорт - Доверенное лицо*/
        WebElement inputPassport2 = driver.findElement(By.xpath(".//*[@id='confidantPassportNumber']"));
        inputPassport2.sendKeys(passportNumber);
        System.out.println("Trustee passport - Done");

        /*Когда выдан - Доверенное лицо*/
        WebElement inputWhen2 = driver.findElement(By.xpath(".//*[@id='step5']/div/div/form/div[3]/div[1]/div/div[2]/div/p-inputmask/input"));
        inputWhen2.sendKeys(registrationDate);
        System.out.println("Trustee. When issued passport - Done");

        /*Кем выдан - Доверенное лицо*/
        WebElement inputWho2 = driver.findElement(By.xpath(".//*[@id='confidantPassportAuthor']"));
        inputWho2.sendKeys(governmentAgency);
        System.out.println("Trustee. Who issued passport - Done");

        /*Дата рождения - Доверенное лицо*/
        WebElement inputBirthday2 = driver.findElement(By.xpath(".//*[@id='step5']/div/div/form/div[3]/div[2]/div/div/div/p-inputmask/input"));
        inputBirthday2.sendKeys(birthDate);
        System.out.println("Trustee birthday - Done");

        /*Доля - Директор*/
        WebElement inputDirectorPart2 = driver.findElement(By.xpath(".//*[@id='partConfidant']"));
        inputDirectorPart2.sendKeys(c5);
        System.out.println("Director part - Done");

        /*Чекбокс единственный владелец - Доверенное лицо*/
        JavascriptExecutor js7 = (JavascriptExecutor) driver;//Скрипт нажатия на скрытый элемент(Строка1)!!!
        js7.executeScript("arguments[0].click();", driver.findElement(By.id("isConfidantOwner")));//Скрипт нажатия на скрытый элемент(Строка2)!!!
        System.out.println("Director is owner - Done");

        /*Контактный телефон - Доверенное лицо*/
        WebElement inputTrusteeTelephone2 = driver.findElement(By.xpath(".//*[@id='step5']/div/div/form/div[5]/div/div[1]/div/p-inputmask/input"));
        inputTrusteeTelephone2.sendKeys(telephonNumber);
        System.out.println("Trustee telephone - Done");

        /*Электронная почта - Доверенное лицо*/
        WebElement inputTrusteeEmail2 = driver.findElement(By.xpath(".//*[@id='confidantEmail']"));
        inputTrusteeEmail2.sendKeys(mailBody + 1 + e3 + e4);
        System.out.println("Trustee e-mail - Done");

        /*Поиск и нажатие на кнопку "Далее"*/
        JavascriptExecutor js8 = (JavascriptExecutor) driver;//Скрипт нажатия на скрытый элемент(Строка1)!!!
        js8.executeScript("arguments[0].click();", driver.findElement(By.xpath(".//*[@id='step5']/div/div/form/div[6]/div/div[2]/button")));//Скрипт нажатия на скрытый элемент(Строка2)!!!
        System.out.println("Completed form - Done");

        /*Автозаполнение через радио кнопку "Директор"*/
        //Возврат к предыдущей форме "Доверенное лицо"
        JavascriptExecutor js9 = (JavascriptExecutor) driver;//Скрипт нажатия на скрытый элемент(Строка1)!!!
        js9.executeScript("arguments[0].click();", driver.findElement(By.xpath(".//*[@id='step6']/div/div/form/div[5]/div/div[1]/button")));//Скрипт нажатия на скрытый элемент(Строка2)!!!
        System.out.println("Back to form Trustee - Done");
        //Автозаполнение формы данными из формы "О директоре"
        JavascriptExecutor js10 = (JavascriptExecutor) driver;//Скрипт нажатия на скрытый элемент(Строка1)!!!
        js10.executeScript("arguments[0].click();", driver.findElement(By.id("confidant-type-0")));//Скрипт нажатия на скрытый элемент(Строка2)!!!
        System.out.println("Autocomplete form Trustee - Done");
        //Проверка автозаполнения нажатием на "Далее"
        JavascriptExecutor js11 = (JavascriptExecutor) driver;//Скрипт нажатия на скрытый элемент(Строка1)!!!
        js11.executeScript("arguments[0].click();", driver.findElement(By.xpath(".//*[@id='step5']/div/div/form/div[6]/div/div[2]/button")));//Скрипт нажатия на скрытый элемент(Строка2)!!!
        System.out.println("Next form - Done");

        System.out.println("[STAGE_3.2] - Done");
        System.out.println("...............................................");

        /*ЭТАП 3-3*/

        /*Форма информация "О Бухгалтере"*/
        System.out.println("[STAGE_3.3]");

        /*Фамилия - Бухгалтер*/
        WebElement inputSurname3 = driver.findElement(By.xpath(".//*[@id='accountantLastName']"));
        inputSurname3.sendKeys(surname + 3);
        System.out.println("Accountant surname - Done");

        /*Имя - Бухгалтер*/
        WebElement inputName3 = driver.findElement(By.xpath(".//*[@id='accountantFirstName']"));
        inputName3.sendKeys(name + 3);
        System.out.println("Accountant name - Done");

        /*Отчество - Бухгалтер*/
        WebElement inputMiddleName3 = driver.findElement(By.xpath(".//*[@id='accountantPatronymic']"));
        inputMiddleName3.sendKeys(middleName + 3);
        System.out.println("Accountant middle name - Done");

        /*Паспорт - Бухгалтер*/
        WebElement inputPassport3 = driver.findElement(By.xpath(".//*[@id='accountantPassportNumber']"));
        inputPassport3.sendKeys(passportNumber);
        System.out.println("Accountant passport - Done");

        /*Когда выдан - Бухгалтер*/
        WebElement inputWhen3 = driver.findElement(By.xpath(".//*[@id='step6']/div/div/form/div[3]/div[1]/div/div[2]/div/p-inputmask/input"));
        inputWhen3.sendKeys(registrationDate);
        System.out.println("Accountant. When issued passport - Done");

        /*Кем выдан - Бухгалтер*/
        WebElement inputWho3 = driver.findElement(By.xpath(".//*[@id='accountantPassportAuthor']"));
        inputWho3.sendKeys(governmentAgency);
        System.out.println("Accountant. Who issued passport - Done");

        /*Дата рождения - Бухгалтер*/
        WebElement inputBirthday3 = driver.findElement(By.xpath(".//*[@id='step6']/div/div/form/div[3]/div[2]/div/div/div/p-inputmask/input"));
        inputBirthday3.sendKeys(birthDate);
        System.out.println("Accountant birthday - Done");

        /*Контактный телефон - Бухгалтер*/
        WebElement inputAccoutantTelephone3 = driver.findElement(By.xpath(".//*[@id='step6']/div/div/form/div[4]/div/div[1]/div/p-inputmask/input"));
        inputAccoutantTelephone3.sendKeys(telephonNumber);
        System.out.println("Accountant telephone - Done");

        /*Электронная почта - Бухгалтер*/
        WebElement inputAccoutantEmail3 = driver.findElement(By.xpath(".//*[@id='accountantEmail']"));
        inputAccoutantEmail3.sendKeys(mailBody + 1 + e3 + e4);
        System.out.println("Accountant e-mail - Done");

        /*Поиск и нажатие на кнопку "Далее"*/
        JavascriptExecutor js12 = (JavascriptExecutor) driver;//Скрипт нажатия на скрытый элемент(Строка1)!!!
        js12.executeScript("arguments[0].click();", driver.findElement(By.xpath(".//*[@id='step6']/div/div/form/div[5]/div/div[2]/button")));//Скрипт нажатия на скрытый элемент(Строка2)!!!
        System.out.println("Next - Done");

        /*Автозаполнение через радио кнопку "Доверенное лицо"*/
        //Возврат к предыдущей форме "О бухгалтере"
        JavascriptExecutor js13 = (JavascriptExecutor) driver;//Скрипт нажатия на скрытый элемент(Строка1)!!!
        js13.executeScript("arguments[0].click();", driver.findElement(By.xpath(".//*[@id='step7']/div/div/form/div[4]/div/div[1]/button")));//Скрипт нажатия на скрытый элемент(Строка2)!!!
        System.out.println("Back to form Accountant - Done");
        //Автозаполнение формы данными из формы "Доверенное лицо"
        JavascriptExecutor js14 = (JavascriptExecutor) driver;//Скрипт нажатия на скрытый элемент(Строка1)!!!
        js14.executeScript("arguments[0].click();", driver.findElement(By.id("accountant-type-1")));//Скрипт нажатия на скрытый элемент(Строка2)!!!
        System.out.println("Autocomplete 1 form Accountant - Done");
        //Проверка автозаполнения нажатием на "Далее"
        JavascriptExecutor js15 = (JavascriptExecutor) driver;//Скрипт нажатия на скрытый элемент(Строка1)!!!
        js15.executeScript("arguments[0].click();", driver.findElement(By.xpath(".//*[@id='step6']/div/div/form/div[5]/div/div[2]/button")));//Скрипт нажатия на скрытый элемент(Строка2)!!!
        System.out.println("Next form - Done");

        /*Автозаполнение через радио кнопку "Директор"*/
        //Возврат к предыдущей форме "О бухгалтере"
        JavascriptExecutor js16 = (JavascriptExecutor) driver;//Скрипт нажатия на скрытый элемент(Строка1)!!!
        js16.executeScript("arguments[0].click();", driver.findElement(By.xpath(".//*[@id='step7']/div/div/form/div[4]/div/div[1]/button")));//Скрипт нажатия на скрытый элемент(Строка2)!!!
        System.out.println("Back to form Accountant - Done");
        //Автозаполнение формы данными из формы "Директор"
        JavascriptExecutor js17 = (JavascriptExecutor) driver;//Скрипт нажатия на скрытый элемент(Строка1)!!!
        js17.executeScript("arguments[0].click();", driver.findElement(By.id("accountant-type-0")));//Скрипт нажатия на скрытый элемент(Строка2)!!!
        System.out.println("Autocomplete 2 form Accountant - Done");
        //Проверка автозаполнения нажатием на "Далее"
        JavascriptExecutor js19 = (JavascriptExecutor) driver;//Скрипт нажатия на скрытый элемент(Строка1)!!!
        js19.executeScript("arguments[0].click();", driver.findElement(By.xpath(".//*[@id='step6']/div/div/form/div[5]/div/div[2]/button")));//Скрипт нажатия на скрытый элемент(Строка2)!!!
        System.out.println("Next form - Done");

        System.out.println("[STAGE_3.3] - Done");
        System.out.println("...............................................");

        /*ЭТАП 3-4*/

        /*Форма информация "О Контактном лице"*/
        System.out.println("[STAGE_3.4]");

        /*Фамилия - Контактное лицо*/
        WebElement inputSurname4 = driver.findElement(By.xpath(".//*[@id='contactPersonLastName']"));
        inputSurname4.sendKeys(surname + 4);
        System.out.println("Contact person surname - Done");

        /*Имя - Контактное лицо*/
        WebElement inputName4 = driver.findElement(By.xpath(".//*[@id='contactPersonFirstName']"));
        inputName4.sendKeys(name + 4);
        System.out.println("Contact person name - Done");

        /*Отчество - Контактное лицо*/
        WebElement inputMiddleName4 = driver.findElement(By.xpath(".//*[@id='contactPersonPatronymic']"));
        inputMiddleName4.sendKeys(middleName + 4);
        System.out.println("Contact person middle name - Done");

        /*Контактный телефон - Контактное лицо*/
        WebElement inputAccoutantTelephone4 = driver.findElement(By.xpath(".//*[@id='step7']/div/div/form/div[3]/div/div[1]/div/p-inputmask/input"));
        inputAccoutantTelephone4.sendKeys(telephonNumber);
        System.out.println("Contact person telephone - Done");

        /*Электронная почта - Контактное лицо*/
        WebElement inputAccoutantEmail4 = driver.findElement(By.xpath(".//*[@id='contactPersonEmail']"));
        inputAccoutantEmail4.sendKeys(mailBody + 1 + e3 + e4);
        System.out.println("Contact person e-mail - Done");

        //Проверка автозаполнения нажатием на "Далее"
        JavascriptExecutor js20 = (JavascriptExecutor) driver;//Скрипт нажатия на скрытый элемент(Строка1)!!!
        js20.executeScript("arguments[0].click();", driver.findElement(By.xpath(".//*[@id='step7']/div/div/form/div[4]/div/div[2]/button")));//Скрипт нажатия на скрытый элемент(Строка2)!!!
        System.out.println("Next form - Done");

        /*Автозаполнение через радио кнопку "Бухгалтер"*/
        //Возврат к предыдущей форме "О Контактном лице"
        JavascriptExecutor js21 = (JavascriptExecutor) driver;//Скрипт нажатия на скрытый элемент(Строка1)!!!
        js21.executeScript("arguments[0].click();", driver.findElement(By.xpath(".//*[@id='step8']/div/div/form/div[4]/div/div[1]/button")));//Скрипт нажатия на скрытый элемент(Строка2)!!!
        System.out.println("Back to form Contact person - Done");
        //Автозаполнение формы данными из формы "Бухгалтер"
        JavascriptExecutor js22 = (JavascriptExecutor) driver;//Скрипт нажатия на скрытый элемент(Строка1)!!!
        js22.executeScript("arguments[0].click();", driver.findElement(By.id("2")));//Скрипт нажатия на скрытый элемент(Строка2)!!!
        System.out.println("Autocomplete 2 form Contact person - Done");
        //Проверка автозаполнения нажатием на "Далее"
        JavascriptExecutor js23 = (JavascriptExecutor) driver;//Скрипт нажатия на скрытый элемент(Строка1)!!!
        js23.executeScript("arguments[0].click();", driver.findElement(By.xpath(".//*[@id='step7']/div/div/form/div[4]/div/div[2]/button")));//Скрипт нажатия на скрытый элемент(Строка2)!!!
        System.out.println("Next form - Done");

        /*Автозаполнение через радио кнопку "Довереное лицо"*/
        //Возврат к предыдущей форме "О Контактном лице"
        JavascriptExecutor js24 = (JavascriptExecutor) driver;//Скрипт нажатия на скрытый элемент(Строка1)!!!
        js24.executeScript("arguments[0].click();", driver.findElement(By.xpath(".//*[@id='step8']/div/div/form/div[4]/div/div[1]/button")));//Скрипт нажатия на скрытый элемент(Строка2)!!!
        System.out.println("Back to form Contact person - Done");
        //Автозаполнение формы данными из формы "Довереное лицо"
        JavascriptExecutor js26 = (JavascriptExecutor) driver;//Скрипт нажатия на скрытый элемент(Строка1)!!!
        js26.executeScript("arguments[0].click();", driver.findElement(By.id("1")));//Скрипт нажатия на скрытый элемент(Строка2)!!!
        System.out.println("Autocomplete 2 form Contact person - Done");
        //Проверка автозаполнения нажатием на "Далее"
        JavascriptExecutor js28 = (JavascriptExecutor) driver;//Скрипт нажатия на скрытый элемент(Строка1)!!!
        js28.executeScript("arguments[0].click();", driver.findElement(By.xpath(".//*[@id='step7']/div/div/form/div[4]/div/div[2]/button")));//Скрипт нажатия на скрытый элемент(Строка2)!!!
        System.out.println("Next form - Done");

        /*Автозаполнение через радио кнопку "Довереное лицо"*/
        //Возврат к предыдущей форме "О Директоре"
        JavascriptExecutor js29 = (JavascriptExecutor) driver;//Скрипт нажатия на скрытый элемент(Строка1)!!!
        js29.executeScript("arguments[0].click();", driver.findElement(By.xpath(".//*[@id='step8']/div/div/form/div[4]/div/div[1]/button")));//Скрипт нажатия на скрытый элемент(Строка2)!!!
        System.out.println("Back to form Contact person - Done");
        //Автозаполнение формы данными из формы "Директор"
        JavascriptExecutor js30 = (JavascriptExecutor) driver;//Скрипт нажатия на скрытый элемент(Строка1)!!!
        js30.executeScript("arguments[0].click();", driver.findElement(By.id("0")));//Скрипт нажатия на скрытый элемент(Строка2)!!!
        System.out.println("Autocomplete 2 form Contact person - Done");
        //Проверка автозаполнения нажатием на "Далее"
        JavascriptExecutor js31 = (JavascriptExecutor) driver;//Скрипт нажатия на скрытый элемент(Строка1)!!!
        js31.executeScript("arguments[0].click();", driver.findElement(By.xpath(".//*[@id='step7']/div/div/form/div[4]/div/div[2]/button")));//Скрипт нажатия на скрытый элемент(Строка2)!!!
        System.out.println("Next form - Done");

        System.out.println("[STAGE_3.4] - Done");
        System.out.println("...............................................");

        /*ЭТАП 4-1*/

        /*Форма информация "Фактический адрес"*/
        System.out.println("[STAGE_4.1]");

        /*Страна - компании*/
        WebElement inputCountry2 = driver.findElement(By.xpath(".//*[@id='country']"));
        inputCountry2.sendKeys(country + 2);
        System.out.println("Country2 - Done");

        /*Регион - компании*/
        WebElement inputRegion2 = driver.findElement(By.xpath(".//*[@id='region']"));
        inputRegion2.sendKeys(region + 2);
        System.out.println("Region2 - Done");

        /*Индекс - компании*/
        WebElement inputZip2 = driver.findElement(By.xpath(".//*[@id='zip']"));
        inputZip2.sendKeys(zip + 2);
        System.out.println("Zip2 - Done");

        /*Улица - компании*/
        WebElement inputStreet2 = driver.findElement(By.xpath(".//*[@id='street']"));
        inputStreet2.sendKeys(street + 2);
        System.out.println("Street2 - Done");

        /*Дом - компании*/
        WebElement inputHouse2 = driver.findElement(By.xpath(".//*[@id='house']"));
        inputHouse2.sendKeys(c5);
        System.out.println("House2 - Done");

        /*Корпус - компании*/
        WebElement inputHouseBlock2 = driver.findElement(By.xpath(".//*[@id='housing']"));
        inputHouseBlock2.sendKeys(c5);
        inputHouseBlock2.clear();
        System.out.println("Address2 - Done");

        /*Поиск и нажатие на кнопку "Далее"*/
        JavascriptExecutor js32 = (JavascriptExecutor) driver;//Скрипт нажатия на скрытый элемент(Строка1)!!!
        js32.executeScript("arguments[0].click();", driver.findElement(By.xpath(".//*[@id='step8']/div/div/form/div[4]/div/div[2]/button")));//Скрипт нажатия на скрытый элемент(Строка2)!!!

        /*Автозаполнение через радио кнопку "Фактический адрес совпадает с юридическим"*/
        //Возврат к предыдущей форме "О фактическом адресе"
        JavascriptExecutor js33 = (JavascriptExecutor) driver;//Скрипт нажатия на скрытый элемент(Строка1)!!!
        js33.executeScript("arguments[0].click();", driver.findElement(By.xpath(".//*[@id='step9']/div/div/form/div[2]/div/div[1]/button")));//Скрипт нажатия на скрытый элемент(Строка2)!!!
        System.out.println("Back to form Address - Done");
        //Автозаполнение формы данными из формы "О фактическом адресе"
        JavascriptExecutor js34 = (JavascriptExecutor) driver;//Скрипт нажатия на скрытый элемент(Строка1)!!!
        js34.executeScript("arguments[0].click();", driver.findElement(By.id("isSameAddress")));//Скрипт нажатия на скрытый элемент(Строка2)!!!
        System.out.println("Autocomplete 2 form Address - Done");
        //Проверка автозаполнения нажатием на "Далее"
        JavascriptExecutor js35 = (JavascriptExecutor) driver;//Скрипт нажатия на скрытый элемент(Строка1)!!!
        js35.executeScript("arguments[0].click();", driver.findElement(By.xpath(".//*[@id='step8']/div/div/form/div[4]/div/div[2]/button")));//Скрипт нажатия на скрытый элемент(Строка2)!!!
        System.out.println("Next form - Done");

        System.out.println("[STAGE_4.1] - Done");
        System.out.println("...............................................");

        /*Форма информация "О фактическом адресе точек продаж"*/
        System.out.println("[STAGE_4.2]");

        /*Точка 1*/
        /*Страна - Точка 1*/
        WebElement inputCountryPoint1 = driver.findElement(By.xpath(".//*[@id='country-0']"));
        inputCountryPoint1.sendKeys(country + 3);
        System.out.println("Country Point 1 - Done");

        /*Регион - Точка 1*/
        WebElement inputRegionPoint1 = driver.findElement(By.xpath(".//*[@id='region-0']"));
        inputRegionPoint1.sendKeys(region + 3);
        System.out.println("Region Point 1 - Done");

        /*Индекс - Точка 1*/
        WebElement inputZipPoint1 = driver.findElement(By.xpath(".//*[@id='zip-0']"));
        inputZipPoint1.sendKeys(zip + 3);
        System.out.println("Zip Point 1 - Done");

        /*Улица - Точка 1*/
        WebElement inputStreetPoint1 = driver.findElement(By.xpath(".//*[@id='street-0']"));
        inputStreetPoint1.sendKeys(street + 3);
        System.out.println("Street Point 1 - Done");

        /*Дом - Точка 1*/
        WebElement inputHousePoint1 = driver.findElement(By.xpath(".//*[@id='house-0']"));
        inputHousePoint1.sendKeys(c5);
        System.out.println("House Point 1 - Done");

        /*Корпус - Точка 1*/
        WebElement inputHouseBlockPoint1 = driver.findElement(By.xpath(".//*[@id='housing-0']"));
        inputHouseBlockPoint1.sendKeys(c5);
        inputHouseBlockPoint1.clear();
        System.out.println("Address Point 1 - Done");
        System.out.println(" ");

        /*Точка 2*/
        /*Добавление точки 2*/
        WebElement addNewPoint2 = driver.findElement(By.xpath(".//*[@id='step9']/div/div/form/div[2]/div/div[2]/a"));
        addNewPoint2.click();

        /*Страна - Точка 2*/
        WebElement inputCountryPoint2 = driver.findElement(By.xpath(".//*[@id='country-1']"));
        inputCountryPoint2.sendKeys(country + 4);
        System.out.println("Country Point 2 - Done");

        /*Регион - Точка 2*/
        WebElement inputRegionPoint2 = driver.findElement(By.xpath(".//*[@id='region-1']"));
        inputRegionPoint2.sendKeys(region + 4);
        System.out.println("Region Point 2 - Done");

        /*Индекс - Точка 2*/
        WebElement inputZipPoint2 = driver.findElement(By.xpath(".//*[@id='zip-1']"));
        inputZipPoint2.sendKeys(zip + 4);
        System.out.println("Zip Point 2 - Done");

        /*Улица - Точка 2*/
        WebElement inputStreetPoint2 = driver.findElement(By.xpath(".//*[@id='street-1']"));
        inputStreetPoint2.sendKeys(street + 4);
        System.out.println("Street Point 2 - Done");

        /*Дом - Точка 2*/
        WebElement inputHousePoint2 = driver.findElement(By.xpath(".//*[@id='house-1']"));
        inputHousePoint2.sendKeys(c5);
        System.out.println("House Point 2 - Done");

        /*Корпус - Точка 2*/
        WebElement inputHouseBlockPoint2 = driver.findElement(By.xpath(".//*[@id='housing-1']"));
        inputHouseBlockPoint2.sendKeys(c5);
        System.out.println("Address Point 2 - Done");
        System.out.println(" ");

        /*Точка 3*/
        /*Добавление точки 3*/
        WebElement addNewPoint3 = driver.findElement(By.xpath(".//*[@id='step9']/div/div/form/div[2]/div/div[2]/a"));
        addNewPoint3.click();

        /*Страна - Точка 2*/
        WebElement inputCountryPoint3 = driver.findElement(By.xpath(".//*[@id='country-2']"));
        inputCountryPoint3.sendKeys(country + 5);
        System.out.println("Country Point 3 - Done");

        /*Регион - Точка 2*/
        WebElement inputRegionPoint3 = driver.findElement(By.xpath(".//*[@id='region-2']"));
        inputRegionPoint3.sendKeys(region + 5);
        System.out.println("Region Point 3 - Done");

        /*Индекс - Точка 2*/
        WebElement inputZipPoint3 = driver.findElement(By.xpath(".//*[@id='zip-2']"));
        inputZipPoint3.sendKeys(zip + 5);
        System.out.println("Zip Point 3 - Done");

        /*Улица - Точка 2*/
        WebElement inputStreetPoint3 = driver.findElement(By.xpath(".//*[@id='street-2']"));
        inputStreetPoint3.sendKeys(street + 5);
        System.out.println("Street Point 3 - Done");

        /*Дом - Точка 3*/
        WebElement inputHousePoint3 = driver.findElement(By.xpath(".//*[@id='house-2']"));
        inputHousePoint3.sendKeys(c5);
        System.out.println("House Point 3 - Done");

        /*Корпус - Точка 3*/
        WebElement inputHouseBlockPoint3 = driver.findElement(By.xpath(".//*[@id='housing-2']"));
        inputHouseBlockPoint3.sendKeys(c5);
        System.out.println("Address Point 3 - Done");

        /*Удаление точки 2*/
        WebElement deletePoint2 = driver.findElement(By.xpath(".//*[@id='step9']/div/div/form/div[1]/div[2]/div/div[1]/div/div/a"));
        deletePoint2.click();

        //Нажатие на "Далее"
        JavascriptExecutor js36 = (JavascriptExecutor) driver;//Скрипт нажатия на скрытый элемент(Строка1)!!!
        js36.executeScript("arguments[0].click();", driver.findElement(By.xpath(".//*[@id='step9']/div/div/form/div[2]/div/div[2]/button")));//Скрипт нажатия на скрытый элемент(Строка2)!!!
        System.out.println("Next form - Done");

        System.out.println("[STAGE_4.2] - Done");
        System.out.println("...............................................");

        /*Форма информация "Документы"*/
        System.out.println("[STAGE_5]");

        /*Прикрепление файла к плитке "Лицензия"*/
        WebElement buttonLicense = driver.findElement(By.id("license"));
        buttonLicense.sendKeys(file);

        /*Прикрепление файла к плитке "Свидетельсто о Гос.регистрации"*/
        WebElement buttonRegistrationCertificate = driver.findElement(By.id("registration_certificate"));
        buttonRegistrationCertificate.sendKeys(file);

        /*Прикрепление файла к плитке "Анкета Бенефициарного владельца"*/
        WebElement buttonBeneficialOwner = driver.findElement(By.id("beneficial_owner"));
        buttonBeneficialOwner.sendKeys(file);

        /*Прикрепление файла к плитке "Копия паспорта"*/
        WebElement buttonPassportCopy = driver.findElement(By.id("passport_copy"));
        buttonPassportCopy.sendKeys(file);

        /*Прикрепление файла к плитке "Личный ИНН лиента как физ. лица"*/
        WebElement buttonPersonalInn = driver.findElement(By.id("personal_inn"));
        buttonPersonalInn.sendKeys(file);

        /*Прикрепление файла к плитке "Договор аренды с приложениями"*/
        WebElement buttonLeaseAgreement = driver.findElement(By.id("lease_agreement"));
        buttonLeaseAgreement.sendKeys(file);

        /*Прикрепление файла к плитке "Сопроводительное письмо"*/
        WebElement buttonCoveringLetter = driver.findElement(By.id("covering_letter"));
        buttonCoveringLetter.sendKeys(file);

        /*Прикрепление файла к плитке "Заявка на установку Терминала"*/
        WebElement buttonApplication = driver.findElement(By.id("application"));
        buttonApplication.sendKeys(file);

        WebElement buttonDell1 = driver.findElement(By.id("application"));

       /* if(){
            System.out.println("");
            return;
        }
        else{
            System.out.println("");
            return;
        }*/


    }
}