package tests;

import configs.ApiConfig;
import models.RegistrationRequestModel;
import models.RegistrationResponceModel;
import models.UserUniversalModel;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static specifications.Specificatios.*;
import static specifications.Specificatios.updateUserRespSpec;

@Tag("ApiPrime")
public class ApiTests extends BaseTest {
    ApiConfig config = ConfigFactory.create(ApiConfig.class, System.getProperties());

    @Test
    @DisplayName("Регистрация пользователя по email и password методом POST")
    void apiRegisterEmailTest() {
        RegistrationRequestModel regData = new RegistrationRequestModel();
        regData.setEmail(config.setEmail());
        regData.setPassword(config.setPassword());

        RegistrationResponceModel registrationModel =
                step("Регистрация пользователя методом POST , используя тестовые email и password", () ->
                        given(registrationReqSpec)
                                .body(regData)
                                .when()
                                .post()
                                .then()
                                .assertThat()
                                .spec(registrationRespSpec)
                                .extract().as(RegistrationResponceModel.class
                                ));

        step("Проверяем успешна ли регистрация пользователя по наличию id и токена", () -> {
            assertEquals(config.setID(), registrationModel.getId());
            assertNotNull(registrationModel.getToken());
        });
    }

    @Test
    @DisplayName("Создание нового юзера методом POST при помощи тестовых профессии и имени")
    void apiCreateNewUserTest() {
        UserUniversalModel authData = new UserUniversalModel();
        authData.setName(config.setName());
        authData.setJob(config.setJob());
        UserUniversalModel userModel = step("Отправляем запрос на создание нового пользователя", () ->
                given(createUserReqSpec)
                        .body(authData)
                        .when()
                        .post()
                        .then()
                        .assertThat()
                        .spec(createUserRespSpec)
                        .extract().as(UserUniversalModel.class
                        ));
        step("Проверяем имя и парофессию в ответе ", () -> {
            assertEquals(config.setName(), userModel.getName());
            assertEquals(config.setJob(), userModel.getJob());
        });
    }

    @Test
    @DisplayName("Удаление пользователя методом DELETE")
    void apiDeleteUserTest() {
        step("Удаление пользователя и проверка кода 204 в ответе", () ->
                given(deleteUserReqSpec)
                        .when()
                        .log().all()
                        .delete()
                        .then()
                        .assertThat()
                        .spec(deleteuserRespSpec)
        );
    }

    @Test
    @DisplayName("Проверка выдачи кода 404 на запрос о несуществующем пользователе методом GET")
    void apiCheckErrorCodeTest() {
        step("Делаем запрос на несуществующего пользоватея и оживаем код 404", () ->
                given(getUnknownUserReq)
                        .when()
                        .get()
                        .then()
                        .assertThat()
                        .spec(getUnknownUserResp));
    }


    @Test
    @DisplayName("Обновление данных пользователя (имени и работы) методом PUT")
    void apiUpdateUserTest() {
        UserUniversalModel updateData = new UserUniversalModel();
        updateData.setName(config.setFullName());
        updateData.setJob(config.getFullJob());
        UserUniversalModel userModel = step("Отправляем запрос на изменеие данных пользователя ", () ->
                given(updateUserReqSpec)
                        .body(updateData)
                        .when()
                        .put()
                        .then()
                        .assertThat()
                        .spec(updateUserRespSpec)
                        .extract().as(UserUniversalModel.class
                        ));
        step("Проверяем данные пользователя в ответе на совпадение с посланным запросом", () -> {
            assertEquals(config.setFullName(), userModel.getName());
            assertEquals(config.getFullJob(), userModel.getJob());
        });
    }
}
