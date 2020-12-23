package ru.netology.Data;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DataGenerator {
    DataGenerator() {}

    public static class UserRequest {
        private UserRequest() {
        }

        private static RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(9999)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        private static void sendRegisterRequest(RequestUserRegister request) {
            given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(request) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200); // код 200 OK
        }

        public static RequestUserRegister generateActiveUser(String locale) {
            Faker faker = new Faker(new Locale(locale));

            RequestUserRegister userRegisterRequest = new RequestUserRegister(
                    faker.internet().emailAddress(),
                    faker.internet().password(),
                    "active"
            );

            sendRegisterRequest(userRegisterRequest);

            return userRegisterRequest;
        }

        public static RequestUserRegister generateBlockedUser(String locale) {
            Faker faker = new Faker(new Locale(locale));

            RequestUserRegister userRegisterRequest = new RequestUserRegister(
                    faker.internet().emailAddress(),
                    faker.internet().password(),
                    "blocked"
            );

            sendRegisterRequest(userRegisterRequest);

            return userRegisterRequest;
        }

        public static RequestUserRegister generateActiveUserLoginInvalid(String locale) {
            Faker faker = new Faker(new Locale(locale));

            String password = faker.internet().password();

            RequestUserRegister userRegisterRequest = new RequestUserRegister(
                    faker.internet().emailAddress(),
                    password,
                    "active"
            );

            sendRegisterRequest(userRegisterRequest);

            RequestUserRegister userRegisterRequestInvalid = new RequestUserRegister(
                    faker.internet().emailAddress(),
                    password,
                    "active"
            );

            return userRegisterRequestInvalid;
        }

        public static RequestUserRegister generateActiveUserPasswordInvalid(String locale) {
            Faker faker = new Faker(new Locale(locale));

            String emailAddress = faker.internet().emailAddress();

            RequestUserRegister userRegisterRequest = new RequestUserRegister(
                    emailAddress,
                    faker.internet().password(),
                    "active"
            );

            sendRegisterRequest(userRegisterRequest);

            RequestUserRegister userRegisterRequestInvalid = new RequestUserRegister(
                    emailAddress,
                    faker.internet().password(),
                    "active"
            );

            return userRegisterRequestInvalid;
        }

        public static RequestUserRegister generateBlockedUserLoginInvalid(String locale) {
            Faker faker = new Faker(new Locale(locale));

            String password = faker.internet().password();

            RequestUserRegister userRegisterRequest = new RequestUserRegister(
                    faker.internet().emailAddress(),
                    password,
                    "blocked"
            );

            sendRegisterRequest(userRegisterRequest);

            RequestUserRegister userRegisterRequestInvalid = new RequestUserRegister(
                    faker.internet().emailAddress(),
                    password,
                    "blocked"
            );

            return userRegisterRequestInvalid;
        }

        public static RequestUserRegister generateBlockedUserPasswordInvalid(String locale) {
            Faker faker = new Faker(new Locale(locale));

            String emailAddress = faker.internet().emailAddress();

            RequestUserRegister userRegisterRequest = new RequestUserRegister(
                    emailAddress,
                    faker.internet().password(),
                    "blocked"
            );

            sendRegisterRequest(userRegisterRequest);

            RequestUserRegister userRegisterRequestInvalid = new RequestUserRegister(
                    emailAddress,
                    faker.internet().password(),
                    "blocked"
            );

            return userRegisterRequestInvalid;
        }
    }
}
