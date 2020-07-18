package ru.netology.domain;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {

    @Test
    void shouldCardDeliverySubmit() {

        DateDelivery manager = new DateDelivery();
        String date = manager.dateDeliveryCalculate(3);
        String replanDate = manager.dateDeliveryCalculate(5);

        UserInfo generator = UserInfoGenerator.generateUserInfo("ru");

        open("http://localhost:9999/");
        SelenideElement form = $("form");
        form.$("[data-test-id=city] input").setValue(generator.getCity());
        form.$("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a");
        form.$("[data-test-id=date] input").sendKeys(Keys.DELETE);
        form.$("[data-test-id=date] input").setValue(date);
        form.$("[data-test-id=name] input").setValue(generator.getName());
        form.$("[data-test-id=phone] input").setValue(generator.getPhone());
        form.$("[data-test-id=agreement]").click();
        form.$("button.button").click();
        $("[data-test-id=success-notification]").waitUntil(visible, 15000);
        $("[data-test-id=success-notification]  .notification__title").shouldHave(exactText("Успешно!"));
        $("[data-test-id=success-notification]  .notification__content").shouldHave(exactText("Встреча успешно запланирована на " + date));

        form.$("[data-test-id=date] input").sendKeys(Keys.CONTROL + "a");
        form.$("[data-test-id=date] input").sendKeys(Keys.DELETE);
        form.$("[data-test-id=date] input").setValue(replanDate);
        form.$("button.button").click();
        $("[data-test-id=replan-notification]").waitUntil(visible, 15000);
        $("[data-test-id=replan-notification]  .notification__title").shouldHave(exactText("Необходимо подтверждение"));
        $("[data-test-id=replan-notification]  .notification__content").find(String.valueOf(exactText("У вас уже запланирована встреча на другую дату. Перепланировать?")));

        $("[data-test-id=replan-notification] button.button").click();
        $("[data-test-id=success-notification]").waitUntil(visible, 15000);
        $("[data-test-id=success-notification]  .notification__title").shouldHave(exactText("Успешно!"));
        $("[data-test-id=success-notification]  .notification__content").shouldHave(exactText("Встреча успешно запланирована на " + replanDate));

    }
}
