package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import configs.BrowserDriverConfig;
import configs.ApiConfig;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    @BeforeAll
    public static void setUp() {
        BrowserDriverConfig config = ConfigFactory.create(BrowserDriverConfig.class, System.getProperties());
        ApiConfig apiConfig = ConfigFactory.create(ApiConfig.class, System.getProperties());

        Configuration.baseUrl = "https://reqres.in/";
        RestAssured.baseURI = apiConfig.getBaseURI();
        Configuration.browser = config.setBrowser();
        Configuration.browserVersion = config.browserVersion();
        if (config.isRemote() == true) {
            Configuration.remote = config.setRemoteUrl();
        }
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }
}
