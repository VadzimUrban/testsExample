package utils;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.*;

public class TokenManager {

    private WebDriver driver;

    public TokenManager(WebDriver driver) {
        this.driver = driver;
    }

    // Сохранение токена в файл
    public void saveTokenToFile(String tokenName, String filePath) throws IOException {
        Cookie tokenCookie = driver.manage().getCookieNamed(tokenName);
        if (tokenCookie != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write(tokenCookie.getValue());
            }
        } else {
            throw new RuntimeException("Cookie с именем " + tokenName + " не найден!");
        }
    }

    // Загрузка токена из файла
    public void loadTokenFromFile(String tokenName, String domain, String filePath) throws IOException {
        File file = new File(filePath);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String tokenValue = reader.readLine();
                Cookie tokenCookie = new Cookie.Builder(tokenName, tokenValue)
                        .domain(domain)
                        .path("/")
                        .build();
                driver.manage().addCookie(tokenCookie);
            }
        } else {
            throw new FileNotFoundException("Файл с токеном не найден: " + filePath);
        }
    }
}
