package tests;
import com.codeborne.selenide.logevents.SelenideLogger;
import configs.ApiConfig;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    @BeforeAll
    public static void setUp() {
        ApiConfig apiConfig = ConfigFactory.create(ApiConfig.class, System.getProperties());
        RestAssured.baseURI = apiConfig.getBaseURI();


    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }
}
