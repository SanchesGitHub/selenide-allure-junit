package org.selenide.examples;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.junit.TextReport;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class YandexImageTest {

    Properties prop = new Properties();

    @Rule
    public TextReport report = new TextReport();

    @Before
    public void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
    }

    @Before
    public void loadProperties(){
        try (InputStream input = new FileInputStream("config.properties")) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void searchImageTest() {
        open("https://yandex.ru/");
        $x(".//div[@class='home-logo__default']").shouldBe(visible);
        $x(".//a[@data-id='images']").click();
        switchTo().window(1);
        $x(".//button[@aria-label='Поиск по изображению']").shouldBe(enabled).click();
        $x(".//input[@type='file']").uploadFile(new File(prop.getProperty("image.path")));
        $(byText("Кажется, на изображении")).shouldBe(visible);

        ElementsCollection bullets = $$x("//div[@class='CbirItem CbirTags']//span[contains(text()," + prop.getProperty("image.name") + ")]");
        assert bullets.size() > 0;
    }

}
