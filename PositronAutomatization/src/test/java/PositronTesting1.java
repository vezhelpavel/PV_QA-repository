import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.internal.Streams;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class PositronTesting1 {

    /*Переменные для заполнения формы Login*/
    String login = "testmail25@mail.com";
    String password = "123456";

    /*Переменные для заполнения форм второго шага регистрации*/
    String inn = "999999998877";
    String bic = "044525593";
    String file = "C:/Users/sergei/Desktop/09.jpg";
    String docFile = "C:/Users/sergei/Desktop/Шимун Врочек - Питер.doc";

    /*Переменные для сравноения*/
    int lenghtInn = inn.length();//Определение количества символов в строке "inn"
    int ipInn = 12;//12 символов в номере ИНН для ИП
    int oaoInn = 10;//10 символов в номере ИНН для ОАО

    /*МЕТОДЫ РАБОТЫ С ПОЛЯМИ И КНОПКАМИ*/
    //Метод поиска и заполнения поля
    private void fieldSearchAndFill(WebDriver driver, String locator, String data, String massage){
        WebElement field = driver.findElement(By.xpath(locator));
        field.sendKeys(data);
        System.out.println(massage);
    }

    //Метод ожидания появления элемента на странице
    private WebElement getWebElement(WebDriver driver, String locator){
        WebDriverWait waitWebElement = new WebDriverWait(driver, 30);//Ожидание кликабельного элемента(Строка 1)
        WebElement result = waitWebElement.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));//Ожидание кликабельного элемента(Строка 2)
        return result;
    }

    //Метод ожидания кликабельного элемента и клика на него
    private void waitTime(WebDriver driver, String locator){
        WebElement button = getWebElement(driver, locator);
        button.click();
    }

    //Исправленный метод нажатия на кнопку
    private void buttonClick(WebDriver driver, String locatorName, String locator, String massage){
        JavascriptExecutor buttonClickJS = (JavascriptExecutor) driver;//Скрипт нажатия на скрытый элемент(Строка1)
        if(locatorName == "id"){
            buttonClickJS.executeScript("arguments[0].click();", driver.findElement(By.id(locator)));//Скрипт нажатия на скрытый элемент(Строка2)
        }
        else if (locatorName == "xpath"){
            buttonClickJS.executeScript("arguments[0].click();", driver.findElement(By.xpath(locator)));//Скрипт нажатия на скрытый элемент(Строка2)
        }
        System.out.println(massage);
    }

    private void autoFill(WebDriver driver, String backButtonLocator, String autoFillButtonLocator, String nextButtonLocator){
        //Возврат к предыдущей форме и проверка автозаполнения
        buttonClick(driver, "xpath", backButtonLocator, "Back to form Confidant - Done");
        //Активация кнопки автозаполнения
        buttonClick(driver, "id", autoFillButtonLocator, "Director check-box - Done");
        //Нажатие кнопки "Далее" для проверки заполненности формы
        buttonClick(driver, "xpath", nextButtonLocator, "Confidant form autofill(director) - Done");
    }

    /*МЕТОДЫ ЗАПОЛНЕНИЯ ФОРМ*/
    //Заполнение Формы "ИНН"
    private void innForm(WebDriver driver){
        fieldSearchAndFill(driver, ".//*[@id='step1']/div/div/form/div/div/div[1]/div[2]/input", inn, "INN - Done");
        waitTime(driver, "//*[@id=\"inn\"]");
        buttonClick(driver, "id", "inn", "INN Form - Done");
    }

    private void firstStep(WebDriver driver, String numbers){
        WebElement innForm = getWebElement(driver, ".//*[@id='step1']/div");
        if(innForm.isDisplayed()) {
            System.out.println("_______________________________________________");
            System.out.println("[INN Form - Visible]");
            System.out.println("_______________________________________________");
            System.out.println("[STAGE_1]");
            System.out.println("...............................................");
            System.out.println("Inn IP - " + numbers + " characters.");
            //Используем метод для заполнения формы "ИНН"
            innForm(driver);
            System.out.println("[STAGE_1 - Done]");
        }
        else{
            System.out.println("[INN Form - Not Visible]");
        }
    }

    //Заполнение формы "Информация о предприятии"
    private void companyInformation(WebDriver driver){
        //Поле "Краткое название"
        fieldSearchAndFill(driver, ".//*[@id='shortName']","  Просто предприятие","Short name - Done");
        //Поле "Средняя численность"
        fieldSearchAndFill(driver, ".//*[@id='stateCount']","1000","Quantity - Done");
        //Поле "Вид деятельности"
        fieldSearchAndFill(driver, ".//*[@id='step2']/div/div/form/div[2]/div/div[1]/div/p-autocomplete/span/input", "Производство пищевых продуктов", "Activities - Done");
        //Чекбокс деятельность подлежит лицензированию
        buttonClick(driver, "id", "is-license", "License - Done");
        //Поле "Орган гос.регистрации"
        fieldSearchAndFill(driver, ".//*[@id='registrationAuthority']","Государственный орган","G.Registration - Done");
        //Поле "Дата регистрации"
        fieldSearchAndFill(driver, ".//*[@id='step2']/div/div/form/div[3]/div/div[2]/div/p-inputmask/input", " 09102011", "Registration date - Done");
        //Поле "ОГРН"
        fieldSearchAndFill(driver, ".//*[@id='ogrn']", "123456789012345", "OGRN - Done");
        //Поле "Наименование налогового органа"
        fieldSearchAndFill(driver, ".//*[@id='taxAuthority']", "Налоговый орган", "Tax authority - Done");
    }

    //Заполнение дополнительных полей в форме "Информация о предприятии" для ОАО
    private void additionalCompanyInformationFields(WebDriver driver){
        //Поле "Величина уставного капитала"
        fieldSearchAndFill(driver, ".//*[@id='capitalSize']", "1000", "Capital size - Done");
        //Поле "КПП"
        fieldSearchAndFill(driver, ".//*[@id='kpp']", "123456789", "KPP - Done");
    }

    //Заполнение адресной части формы
    private void addressForm(WebDriver driver, String company, String dash, String number){
        //Поле "Страна"
        fieldSearchAndFill(driver, ".//*[@id='" + company + dash + "country" + number + "']", "Россия" + number, company + "Country" + number + " - Done");
        //Поле "Регион"
        fieldSearchAndFill(driver, ".//*[@id='" + company + dash + "region" + number + "']", "Москва"  +number, company + "Region" + number + " - Done");
        //Поле "Индекс"
        fieldSearchAndFill(driver, ".//*[@id='" + company + dash + "zip" + number + "']", "220022", company + "Zip" + number + " - Done");
        //Поле "Улица"
        fieldSearchAndFill(driver, ".//*[@id='" + company + dash + "street" + number + "']", "Местная" + number, company + "Street" + number + " - Done");
        //Поле "Дом"
        fieldSearchAndFill(driver, ".//*[@id='" + company + dash + "house" + number + "']", "22", company + "House" + number + " - Done");
        //Поле "Корпус"
        fieldSearchAndFill(driver, ".//*[@id='" + company + dash + "housing" + number + "']", "22", company + "Housing" + number + " - Done");
    }

    //Заполнение формы "О банке"
    private void bankForm(WebDriver driver){
        //Поле "Корреспондентский счет"
        fieldSearchAndFill(driver, ".//*[@id='correspondentAccount']", "11111111111111111111", "Correspondent account - Done");
        //Поле "Расчетный счет"
        fieldSearchAndFill(driver, ".//*[@id='checkingAccount']", "11111111111111111111", "Checking account - Done");
        //Поле "БИК"
        fieldSearchAndFill(driver, ".//*[@id='bik']", bic, "BIC - Done");
        //Поле "Банк"
        fieldSearchAndFill(driver, ".//*[@id='bank']", "Московский Банк", "Bank - Done");
    }

    //Заполнение части "ФИО"
    private void fio(WebDriver driver, String formName, String personNumber){
        //Поле "Фамилия"
        fieldSearchAndFill(driver,".//*[@id='" + formName + "LastName']", "Фамилия" + personNumber, formName + " surname - Done");
        //Поле "Имя"
        fieldSearchAndFill(driver,".//*[@id='" + formName + "FirstName']", "Имя" + personNumber, formName + " name - Done");
        //Поле "Отчество"
        fieldSearchAndFill(driver,".//*[@id='" + formName + "Patronymic']", "Имя" + personNumber, formName + " middle name - Done");
    }

    //Заполнение подробной информации "О ФИО"
    private void infoFio(WebDriver driver, String formName, String stepNumber, String firstLocatorNumber){
        //Поле "Номер и серия паспорта"
        fieldSearchAndFill(driver, ".//*[@id='" + formName + "PassportNumber']", "123456789012345", formName + " passport - Done");
        //Поле "Когда выдан"
        fieldSearchAndFill(driver, ".//*[@id='step" + stepNumber + "']/div/div/form/div[" + firstLocatorNumber + "]/div[1]/div/div[2]/div/p-inputmask/input", " 09102011", formName + ". When issued passport - Don");
        //Поле "Кем выдан"
        fieldSearchAndFill(driver, ".//*[@id='" + formName + "PassportAuthor']", " Государственный орган", formName + ". Who issued passport - Done");
        //Поле "Дата Рождения"
        fieldSearchAndFill(driver, ".//*[@id='step" + stepNumber + "']/div/div/form/div[" + firstLocatorNumber + "]/div[2]/div/div/div/p-inputmask/input", " 09111980", formName + " birthday - Done");
    }

    //Заполение "Доли владения"
    private void partConfidant(WebDriver driver, String nameForm, String namePerson){
        //Поле "Доля"
        fieldSearchAndFill(driver, ".//*[@id='part" + nameForm + "']", "50", "director part - Done");
        //Чекбокс "Единственный владелец"
        buttonClick(driver,"id", "is" + namePerson + "Owner", "Director is owner - Done");
    }

    //Заполнение "Контактов"
    private void partContacts(WebDriver driver, String stepNumber, String firstLocatorNumber, String nameForm){
        //Поле "Номер телефона"
        fieldSearchAndFill(driver,".//*[@id='step" + stepNumber + "']/div/div/form/div[" + firstLocatorNumber + "]/div/div[1]/div/p-inputmask/input", "+1234567890", nameForm + " telephone - Done");
        //Поле "Электронный адрес"
        fieldSearchAndFill(driver,".//*[@id='" + nameForm + "Email']", "mail"+stepNumber+"@mail.com", nameForm + " e-mail - Done");
    }

    //Добавление файлов к плтиткам
    private void fileUpload(WebDriver driver, String fileId){
        /*Прикрепление файла к плитке*/
        WebElement buttonFile = driver.findElement(By.id(fileId));
        buttonFile.sendKeys(file);
        System.out.println("File " + fileId + " Upload - Done");
    }

    //Заполнение набора плиток для ИП
    private void fileIPForm(WebDriver driver){
        fileUpload(driver, "license");
        fileUpload(driver, "registration_certificate");
        fileUpload(driver, "beneficial_owner");
        fileUpload(driver, "passport_copy");
        fileUpload(driver, "personal_inn");
        fileUpload(driver, "lease_agreement");
        fileUpload(driver, "covering_letter");
        fileUpload(driver, "application");
        //Ожидание загрузки файлов и нажатие на кнопку "Далее"
        waitTime(driver, "//*[@id=\"step10\"]/div/div/form/div[2]/div/div[2]/button");
        buttonClick(driver, "xpath", "//*[@id=\"step10\"]/div/div/form/div[2]/div/div[2]/button", "Files uploaded - Done");
    }

    //Заполнение набора плиток для ОАО
    private void fileOaoForm(WebDriver driver){
        fileUpload(driver, "license");
        fileUpload(driver, "trusted");
        fileUpload(driver, "document");
        fileUpload(driver, "registration_certificate");
        fileUpload(driver, "bankcard");
        fileUpload(driver, "business_reputation");
        fileUpload(driver, "corporate_agreement");
        fileUpload(driver, "beneficial_owner");
        fileUpload(driver, "association_memorandum");
        fileUpload(driver, "charter");
        fileUpload(driver, "tax_authority_certificate");
        fileUpload(driver, "changes_additions");
        fileUpload(driver, "covering_letter");
        fileUpload(driver, "application");

        //Проверка на успешную загрузку
        /*try {
            List<WebElement> blockDelPointOao = driver.findElements(By.className("p-b-25"));
            blockDelPointOao.size();
            int blockQuantity = blockDelPointOao.size();
            int countElementsUpload = 1;
            for (int i = 1; i < countElementsUpload; i++){
                List<WebElement> countElementsUploaded = driver.findElements(By.className("close-thik"));
                countElementsUploaded.size();
                int elementQuantity = countElementsUploaded.size();
                if(elementQuantity == blockQuantity){
                    buttonClick(driver, "xpath", ".//*[@id='step10']/div/div/form/div[2]/div/div[2]/button", "Next");
                    System.out.println("Test - 14 numbers in INN");
                    break;
                }
                else{
                    countElementsUpload++;
                }
            }
        }
        catch (NoSuchElementException e) {
            System.out.println("Test - Crashed ");
        }*/
        //Ожидание загрузки файлов и нажатие на кнопку "Далее"
        waitTime(driver, "//*[@id=\"step10\"]/div/div/form/div[2]/div/div[2]/button");
        buttonClick(driver, "xpath", "//*[@id=\"step10\"]/div/div/form/div[2]/div/div[2]/button", "Files uploaded - Done");
    }

    //Заполнение одинаковой части форм для ИП и ОАО (ЭТАПЫ 2.2-4.2)
    private void sempleForms(WebDriver driver){
        
        //ЭТАП-2.2 - Информация о банке
        WebElement bankInfoForm = getWebElement(driver, ".//*[@id='step3']/div/div/form");
        if(bankInfoForm.isDisplayed()) {
            System.out.println("_______________________________________________");
            System.out.println("Bank Info Form - Visible");
            System.out.println("_______________________________________________");
            System.out.println("[STAGE_2.2]");
            System.out.println("...............................................");
            //Используем метод для заполнения формы "Банк"
            bankForm(driver);
            //Автозаполнение через "Загрузить по БИК"
            buttonClick(driver, "xpath", ".//*[@id='step3']/div/div/form/div[1]/div/div[4]/button", "Load for BIK");
            //Нажатие кнопки "Далее" для проверки заполненности формы
            buttonClick(driver, "xpath", ".//*[@id='step3']/div/div/form/div[2]/div/div[2]/button", "Bank form - Done");
            System.out.println("[STAGE_2.2 - Done]");
        }
        else{
            System.out.println("_______________________________________________");
            System.out.println("Bank Info Form - Not Visible");
        }

        //ЭТАП-3.1 - Информация о директоре
        WebElement directorInfoForm = getWebElement(driver, ".//*[@id='step4']/div/div/form");
        if(directorInfoForm.isDisplayed()) {
            System.out.println("_______________________________________________");
            System.out.println("Director Info Form - Visible");
            System.out.println("_______________________________________________");
            System.out.println("[STAGE_3.1]");
            System.out.println("...............................................");
            //Используем метод для заполнения частиформы "ФИО" директора
            fio(driver, "director", "1");
            //Используем метод для заполнения части формы подробной информации "О ФИО"
            infoFio(driver, "director", "4", "2");
            //Используем метод для заполнения части "Доля владения"
            partConfidant(driver, "Director", "");
            //Используем метод для заполнения части "Контактов"
            partContacts(driver, "4", "4", "director");
            //Нажатие кнопки "Далее" для проверки заполненности формы
            buttonClick(driver, "xpath", ".//*[@id='step4']/div/div/form/div[5]/div/div[2]/button", "Director form - Done");
            System.out.println("[STAGE_3.1 - Done]");
        }
        else{
            System.out.println("_______________________________________________");
            System.out.println("Director Info Form - Not Visible");
        }

        //ЭТАП-3.2 - Информация о доверенном лице
        WebElement confidantInfoForm = getWebElement(driver, ".//*[@id='step5']/div/div/form");
        if(confidantInfoForm.isDisplayed()) {
            System.out.println("_______________________________________________");
            System.out.println("Confidant Info Form - Visible");
            System.out.println("_______________________________________________");
            System.out.println("[STAGE_3.2]");
            System.out.println("...............................................");
            //Используем метод для заполнения части "ФИО" Доверенного лица
            fio(driver, "confidant", "2");
            //Используем метод для заполнения подробной информации "О ФИО"
            infoFio(driver, "confidant", "5", "3");
            //Используем метод для заполнения части "Доля владения"
            partConfidant(driver, "Confidant", "Confidant");
            //Используем метод для заполнения части "Контактов"
            partContacts(driver, "5", "5", "confidant");
            //Нажатие кнопки "Далее" для проверки заполненности формы
            buttonClick(driver, "xpath", ".//*[@id='step5']/div/div/form/div[6]/div/div[2]/button", "Confidant form - Done");
            /*АВТОЗАПОЛНЕНИЕ*/
            //Автозаполнение формы "Довереннное" лицо данными из формы "Директор"
            autoFill(driver, ".//*[@id='step6']/div/div/form/div[5]/div/div[1]/button", "confidant-type-0", ".//*[@id='step5']/div/div/form/div[6]/div/div[2]/button");
        }
        else {
            System.out.println("_______________________________________________");
            System.out.println("Confidant Info Form - Not Visible");
        }

        //ЭТАП-3.3 - Информация о бухгалтере
        WebElement accountantInfoForm = getWebElement(driver, ".//*[@id='step6']/div/div/form");
        if(accountantInfoForm.isDisplayed()) {
            System.out.println("_______________________________________________");
            System.out.println("Accountant Info Form - Visible");
            System.out.println("_______________________________________________");
            System.out.println("[STAGE_3.3]");
            System.out.println("...............................................");
            //Используем метод для заполнения части "ФИО" Бухгалтера
            fio(driver, "accountant", "3");
            //Используем метод для заполнения подробной информации "О ФИО"
            infoFio(driver, "accountant", "6", "3");
            //Используем метод для заполнения части "Контактов"
            partContacts(driver, "6", "4", "accountant");
            //Нажатие кнопки "Далее" для проверки заполненности формы
            buttonClick(driver, "xpath", ".//*[@id='step5']/div/div/form/div[6]/div/div[2]/button", "Confidant form - Done");
            //АВТОЗАПОЛНЕНИЕ-1
            //Автозаполнение формы "Бухгалтер" лицо данными из формы "Доверенное лицо"
            autoFill(driver, ".//*[@id='step7']/div/div/form/div[4]/div/div[1]/button", "accountant-type-1", ".//*[@id='step6']/div/div/form/div[5]/div/div[2]/button");
            //АВТОЗАПОЛНЕНИЕ-2
            //Автозаполнение формы "Бухгалтер" лицо данными из формы "Директор"
            autoFill(driver, ".//*[@id='step7']/div/div/form/div[4]/div/div[1]/button", "accountant-type-0", ".//*[@id='step6']/div/div/form/div[5]/div/div[2]/button");
        }
        else {
            System.out.println("_______________________________________________");
            System.out.println("Accountant Info Form - Not Visible");
        }

        //ЭТАП-3.4 - Информация о контактном лице
        WebElement contactPersonInfoForm = getWebElement(driver, ".//*[@id='step7']/div/div/form");
        if(contactPersonInfoForm.isDisplayed()) {
            System.out.println("_______________________________________________");
            System.out.println("Contact Person Info Form - Visible");
            System.out.println("_______________________________________________");
            System.out.println("[STAGE_3.4]");
            System.out.println("...............................................");
            //Используем метод для заполнения части "ФИО" Контактного лица
            fio(driver, "contactPerson", "4");
            //Используем метод для заполнения части "Контактов"
            partContacts(driver, "7", "3", "contactPerson");
            //Нажатие кнопки "Далее" для проверки заполненности формы
            buttonClick(driver, "xpath", ".//*[@id='step5']/div/div/form/div[6]/div/div[2]/button", "Confidant form - Done");
            //АВТОЗАПОЛНЕНИЕ-1
            //Автозаполнение формы "Контактоное лицо" лицо данными из формы "Бухгалтер"
            autoFill(driver, ".//*[@id='step8']/div/div/form/div[4]/div/div[1]/button", "2", ".//*[@id='step7']/div/div/form/div[4]/div/div[2]/button");
            //АВТОЗАПОЛНЕНИЕ-2
            //Автозаполнение формы "Контактоное лицо" лицо данными из формы "Доверенное лицо"
            autoFill(driver, ".//*[@id='step8']/div/div/form/div[4]/div/div[1]/button", "1", ".//*[@id='step7']/div/div/form/div[4]/div/div[2]/button");
            //АВТОЗАПОЛНЕНИЕ-3
            //Автозаполнение формы "Контактоное лицо" лицо данными из формы "Директор"
            autoFill(driver, ".//*[@id='step8']/div/div/form/div[4]/div/div[1]/button", "0", ".//*[@id='step7']/div/div/form/div[4]/div/div[2]/button");
            System.out.println("[STAGE_3.4 - Done]");
        }
        else {
            System.out.println("_______________________________________________");
            System.out.println("Contact Person Info Form - Not Visible");
        }

        //ЭТАП-4.1 - Фактический адрес
        WebElement addressForm = getWebElement(driver, ".//*[@id='step8']/div/div/form");
        if(addressForm.isDisplayed()) {
            System.out.println("_______________________________________________");
            System.out.println("Address Form - Visible");
            System.out.println("_______________________________________________");
            System.out.println("[STAGE_4.1]");
            System.out.println("...............................................");
            //Используем метод заполнения формы "Адреса"
            addressForm(driver, "", "", "");
            //Нажатие кнопки "Далее" для проверки заполненности формы
            buttonClick(driver, "xpath", ".//*[@id='step8']/div/div/form/div[4]/div/div[2]/button", "Address form - Done");
            //АВТОЗАПОЛНЕНИЕ-"Фактический адрес совпадает с юридическим"
            autoFill(driver, ".//*[@id='step9']/div/div/form/div[2]/div/div[1]/button", "isSameAddress", ".//*[@id='step8']/div/div/form/div[4]/div/div[2]/button");
            System.out.println("[STAGE_4.1 - Done]");
        }
        else {
            System.out.println("_______________________________________________");
            System.out.println("Address Form - Not Visible");
        }

        //ЭТАП-4.2 - Точки
        WebElement pointForm = getWebElement(driver, ".//*[@id='step9']/div/div/form");
        if(pointForm.isDisplayed()) {
            System.out.println("_______________________________________________");
            System.out.println("Point Form - Visible");
            System.out.println("_______________________________________________");
            System.out.println("[STAGE_4.2]");
            System.out.println("...............................................");
            //Используем метод для заполнения первой точки
            addressForm(driver, "", "", "-0");
            //Добавляем ещё одну точку
            buttonClick(driver, "xpath", ".//*[@id='step9']/div/div/form/div[2]/div/div[2]/a", "Ddd point 2 - Done");
            //Используем метод для заполнения второй точки
            addressForm(driver, "", "", "-1");
            //Добавляем ещё одну точку
            buttonClick(driver, "xpath", ".//*[@id='step9']/div/div/form/div[2]/div/div[2]/a", "Ddd point 3 - Done");
            //Используем метод для заполнения третей точки
            addressForm(driver, "", "", "-2");
            //Удаляем вторую точку
            buttonClick(driver, "xpath", ".//*[@id='step9']/div/div/form/div[1]/div[2]/div/div[1]/div/div/a", "Delete point 2 - Done");
            //Нажатие кнопки "Далее" для проверки заполненности формы
            buttonClick(driver, "xpath", ".//*[@id='step9']/div/div/form/div[2]/div/div[2]/button", "Points forms - Done");
            System.out.println("[STAGE_4.2 - Done]");
        }
        else {
            System.out.println("_______________________________________________");
            System.out.println("Point Form - Not Visible");
        }
    }

    //Завершение регистрации
    private void endRegistration(WebDriver driver){
        WebElement endRegistrationForm = getWebElement(driver, ".//*[@id='step10']/div/div/form");
        if(endRegistrationForm.isDisplayed()) {
            System.out.println("_______________________________________________");
            System.out.println("End Registration Form - Visible");
            System.out.println("_______________________________________________");
            System.out.println("[STAGE_6]");
            System.out.println("...............................................");
            //Активация чекбокса подписи на рассылку
            buttonClick(driver, "xpath", ".//*[@id='step11']/div/div/form/div/div/div/form/div[2]/label", "Subscribe to the newsletter - Done");
            //Нажатие на кнопку "Завершить регистрацию"
            /*Закоменчено чтобы не нажималасть кнопка завершения регистрации
            //buttonClick(driver, ".//*[@id='step11']/div/div/form/div/div/div/form/div[1]/button[2]", "End registration - Done");
            //System.out.println("[STAGE_6 - Done]"); */
        }
        else {
            System.out.println("_______________________________________________");
            System.out.println("End Registration Form - Not Visible");
        }
    }


    /*Тест валидной регистрации*/
    @Test
    public void PositronRegistrationStepTwo() {

        /*ЗАПУСК ТЕСТИРОВАНИЯ*/
        System.out.println(" ");
        System.out.println("_______________________________________________");
        System.out.println("TESTING - START!");
        System.out.println("...............................................");

        //Запуск SeleniumWebDriver и открытие окна браузера
        System.setProperty("webdriver.chrome.driver", "C:\\SeleniumDrivers\\chromedriver.exe");//путь к ChromeDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();//Раскрытие окна браузера во весь экран
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);//Ожидание раскрытия окна
        driver.navigate().to("http://positron.dektry.com/"); //Ссылка

        /*ВХОД В СИСТЕМУ*/
        WebElement homePage = getWebElement(driver, "//*[@id=\"navbar\"]/a[2]");
        if(homePage.isDisplayed()) {
            System.out.println("_______________________________________________");
            System.out.println("Home Page - Visible");
            System.out.println("_______________________________________________");
            System.out.println("ENTRACE");
            System.out.println("...............................................");
            //Нажатие на кнопку "Войти"
            buttonClick(driver, "xpath", ".//*[@id='navbar']/a[2]", "Entrance - Done");
        }
        else {
            System.out.println("Home Page - Not Visible");
        }

        /*LOGIN*/
        WebElement loginPage = getWebElement(driver, "/html/body/sd-app/div/ng-component/div/div[1]/form");
        if (loginPage.isDisplayed()) {
            System.out.println("_______________________________________________");
            System.out.println("Login Page - Visible");
            System.out.println("_______________________________________________");
            System.out.println("LOGIN");
            System.out.println("...............................................");
            //Ввод электронной почты
            fieldSearchAndFill(driver, "html/body/sd-app/div/ng-component/div/div[1]/form/div[1]/input", login, "Login e-mail - Done");
            //Отображение пароля без маски
            buttonClick(driver, "xpath", "html/body/sd-app/div/ng-component/div/div[1]/form/div[2]/i", "Login open password - Done");
            //Ввод пароля
            fieldSearchAndFill(driver, "html/body/sd-app/div/ng-component/div/div[1]/form/div[2]/input", password, "Login password - Done");
            //Нажатие на кнопку "Войти"
            buttonClick(driver, "xpath", "html/body/sd-app/div/ng-component/div/div[1]/form/button", "LOGIN - DONE");
        }
        else{
            System.out.println("Login Page - Not Visible");
        }

        /*ВЫБОР ПОЛЕЙ ФОРМ ДЛЯ ЗАПОЛНЕНИЯ ИСХОДЯ ИЗ НОМЕРА ИНН*/
        //Набор полей форм для заполнения ИП
        if(lenghtInn == ipInn) {

            //ЭТАП-1 - Ввод ИНН
            firstStep(driver, "12");

            //ЭТАП-2.1 - Информация о предприятии
            WebElement businessInfoForm = getWebElement(driver, ".//*[@id='step2']/div/div/form");
            if(businessInfoForm.isDisplayed()) {
                System.out.println("_______________________________________________");
                System.out.println("Business Info Form - Visible");
                System.out.println("_______________________________________________");
                System.out.println("[STAGE_2.1]");
                System.out.println("...............................................");
                //Используем метод для заполнения формы "Информации о Предприятии"
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //Ожидание раскрытия окна
                companyInformation(driver);

                //Проверка на появления поля КПП
                try {
                    WebElement inputKpp = driver.findElement(By.xpath(".//*[@id='kpp']"));

                    if (inputKpp.isDisplayed()) {
                        inputKpp.sendKeys("111111111");
                        System.out.println("BUG!!! KPP - Done");
                    } else {
                        System.out.println("  No KPP field");
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("  No KPP field!");
                }

                //Используем метод заполнения формы "Адреса"
                addressForm(driver, "company", "-", "");
                //Используем метод нажатия на скрытую кнопку
                buttonClick(driver, "xpath", ".//*[@id='step2']/div/div/form/div[6]/div/div[2]/button", "Company form - Done");
                System.out.println("[STAGE_2.1 - Done]");
            }
            else{
                System.out.println("_______________________________________________");
                System.out.println("Business Info Form - Not Visible");
            }

            //ИСПОЛЬЗОВАНИЕ МЕТОДА ЗАПОЛНЕНИЯ ОДИНАКОВЫХ ПОЛЕЙ ФОРМ ДЛЯ ИП И ОАО(Этапы 2.2-4.2)
            sempleForms(driver);

            //ЭТАП-5 - Документы
            WebElement documentsForm = getWebElement(driver, ".//*[@id='step10']/div/div/form");
            if(documentsForm.isDisplayed()) {
                System.out.println("_______________________________________________");
                System.out.println("Documents Form - Visible");
                //Прикрепление файлов к набору плиток ОАО
                System.out.println("_______________________________________________");
                System.out.println("[STAGE_5]");
                System.out.println("...............................................");
                fileIPForm(driver);
                System.out.println("[STAGE_5 - Done]");
            }
            else {
                System.out.println("_______________________________________________");
                System.out.println("Documents Form - Not Visible");
            }

            //ЭТАП-6 - Завершение регистрации
            endRegistration(driver);
        }

        //Набор полей форм для заполнения ОАО
        else if(lenghtInn == oaoInn){

            //ЭТАП-1 - Ввод ИНН
            firstStep(driver, "10");

            //ЭТАП-2.1 - Информация о предприятии
            System.out.println("_______________________________________________");
            System.out.println("[STAGE_2.1]");
            System.out.println("...............................................");
            //Используем метод для заполнения формы "Информации о Предприятии"
            companyInformation(driver);
            //Используем метод для заполнения дополнительных полей
            additionalCompanyInformationFields(driver);
            //Используем метод заполнения формы "Адреса"
            addressForm(driver, "company", "-", "");//передаём параметры для формирования локаторов
            //Используем метод нажатия на скрытую кнопку
            buttonClick(driver, "xpath", ".//*[@id='step2']/div/div/form/div[6]/div/div[2]/button", "Company form - Done");
            System.out.println("[STAGE_2.1 - Done]");

            //ИСПОЛЬЗОВАНИЕ МЕТОДА ЗАПОЛНЕНИЯ ОДИНАКОВЫХ ПОЛЕЙ ФОРМ ДЛЯ ИП И ОАО(Этапы 2.2-4.2)
            sempleForms(driver);

            //ЭТАП-5 - Документы
            WebElement documentsForm = getWebElement(driver, ".//*[@id='step10']/div/div/form");
            if(documentsForm.isDisplayed()) {
                System.out.println("_______________________________________________");
                System.out.println("Documents Form - Visible");
                //Прикрепление файлов к набору плиток ОАО
                System.out.println("_______________________________________________");
                System.out.println("[STAGE_5]");
                System.out.println("...............................................");
                fileOaoForm(driver);
                System.out.println("[STAGE_5 - Done]");
            }
            else {
                System.out.println("_______________________________________________");
                System.out.println("Documents Form - Not Visible");
            }

            //ЭТАП-6 - Завершение регистрации
            endRegistration(driver);
        }

        //Неверный ИНН
        else{
            System.out.println("Not valid INN");
            System.out.println("_______________________________________________");
            System.out.println("TESTING - FINISH!");
        }
    }
}