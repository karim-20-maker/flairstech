package Common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static Common.ReadProperties.URL;

public class Hooks {
    public static WebDriver driver;
    private static final String remote = System.getProperty("remote");

    public void startDriverSession() {
        System.out.println("Initializing driver...");
        String browser = System.getProperty("browser", "chrome");
        try {
            WebDriver webDriver = initializeDriver(browser);
            configureDriver(webDriver);
            System.out.println("Driver initialized: " + webDriver);
        } catch (Exception e) {
            throw new RuntimeException("Failed to start WebDriver session", e);
        }
    }


    private WebDriver initializeDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                driver = initializeChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        return driver;
    }

    private WebDriver initializeChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        if (Objects.equals(remote, "true")) {
            options.addArguments("--headless=new");
        }
        driver = new ChromeDriver(options);
        if (Objects.equals(remote, "true")) {
            setGeolocationOverride(driver);
        }
        return driver;
    }

    private void setGeolocationOverride(WebDriver driver) {
        Map<String, Object> coordinates = new HashMap<>();
        coordinates.put("latitude", 31.2156);
        coordinates.put("longitude", 29.9553);
        coordinates.put("accuracy", 100);
        ((ChromeDriver) driver).executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
    }

    private void configureDriver(WebDriver driver) {
        driver.manage().window().maximize();
        driver.get(URL);
    }

    public void tearDownDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

}
