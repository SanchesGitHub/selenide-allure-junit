package org.selenide.examples;

import com.codeborne.selenide.ElementsCollection;
import org.junit.Test;

import java.io.File;
import java.time.Duration;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class YandexImageTest extends BaseTest {

    @Test
    public void searchImageTest() {
        open("https://yandex.ru/");
        $x(".//div[@class='home-logo__default']").shouldBe(visible);
        $x(".//a[@data-id='images']").click();
        switchTo().window(1);
        $x(".//button[@aria-label='Поиск по изображению']").shouldBe(enabled).click();
        $x(".//input[@type='file']").uploadFile(new File(prop.getProperty("image.path")));
        $(byText("Кажется, на изображении")).shouldBe(visible, Duration.ofSeconds(10));

        ElementsCollection bullets = $$x("//div[@class='CbirItem CbirTags']//span[contains(text()," + prop.getProperty("image.name") + ")]");
        assert bullets.size() > 0;
    }

}
