package ru.netology.test;

import ru.netology.Data.DataGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.Data.RequestUserRegister;

import static org.junit.jupiter.api.Assertions.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class UserFormTest {

    @BeforeEach
    void setUpAll() {
        openSite();
    }

    void openSite() {
        open("http://localhost:9999");
    }

    @Test
    void shouldLoginWithActiveUser() {
        RequestUserRegister userRegister = DataGenerator.UserRequest.generateActiveUser("ru");

        $("input[name='login']").setValue(userRegister.getLogin());
        $("input[type='password']").setValue(userRegister.getPassword());
        $("[data-test-id=action-login]").click();

        String dashboardUrl = "http://localhost:9999/dashboard";

        assertEquals(dashboardUrl, url());

        $(".heading").shouldHave(text("Личный кабинет"));
    }

    @Test
    void shouldLoginWithInvalidLogin() {
        RequestUserRegister userRegister = DataGenerator.UserRequest.generateActiveUserLoginInvalid("ru");

        $("input[name='login']").setValue(userRegister.getLogin());
        $("input[type='password']").setValue(userRegister.getPassword());
        $("[data-test-id=action-login]").click();

        // Error
        $(".notification__title").shouldHave(text("Ошибка"));
        $(".notification__content").shouldHave(text("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    void shouldLoginWithInvalidPassword() {
        RequestUserRegister userRegister = DataGenerator.UserRequest.generateActiveUserPasswordInvalid("ru");

        $("input[name='login']").setValue(userRegister.getLogin());
        $("input[type='password']").setValue(userRegister.getPassword());
        $("[data-test-id=action-login]").click();

        // Error
        $(".notification__title").shouldHave(text("Ошибка"));
        $(".notification__content").shouldHave(text("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    void shouldLoginWithBlockedUser() {
        RequestUserRegister userRegister = DataGenerator.UserRequest.generateBlockedUser("ru");

        $("input[name='login']").setValue(userRegister.getLogin());
        $("input[type='password']").setValue(userRegister.getPassword());
        $("[data-test-id=action-login]").click();

        // Error
        $(".notification__title").shouldHave(text("Ошибка"));
        $(".notification__content").shouldHave(text("Ошибка! Пользователь заблокирован"));
    }

    @Test
    void shouldNotLoginWithInvalidLogin() {
        RequestUserRegister userRegister = DataGenerator.UserRequest.generateBlockedUserLoginInvalid("ru");

        $("input[name='login']").setValue(userRegister.getLogin());
        $("input[type='password']").setValue(userRegister.getPassword());
        $("[data-test-id=action-login]").click();

        // Error
        $(".notification__title").shouldHave(text("Ошибка"));
        $(".notification__content").shouldHave(text("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    void shouldNotLoginWithInvalidPassword() {
        RequestUserRegister userRegister = DataGenerator.UserRequest.generateBlockedUserPasswordInvalid("ru");

        $("input[name='login']").setValue(userRegister.getLogin());
        $("input[type='password']").setValue(userRegister.getPassword());
        $("[data-test-id=action-login]").click();

        // Error
        $(".notification__title").shouldHave(text("Ошибка"));
        $(".notification__content").shouldHave(text("Ошибка! Неверно указан логин или пароль"));
    }
}
