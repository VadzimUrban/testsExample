package utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * TestListener - обработчик событий для тестов.
 * Добавляет скриншоты при ошибках и сохраняет результаты в Allure.
 */
public class TestListener implements ITestListener {

    public static WebDriver driver;

    @Override
    public void onTestFailure(ITestResult result) {
        // Создаем скриншот, если тест провалился
        if (driver != null) {
            takeScreenshot();
        }
    }

    private void takeScreenshot() {
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

//            // Сохраняем скриншот в файл для проверки
//            Files.write(Paths.get("screenshot.png"), screenshot);

            // Прикрепляем скриншот к Allure
            Allure.addAttachment("Screenshot on Failure", new ByteArrayInputStream(screenshot));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Можно добавить логику при старте теста
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Можно добавить логику при успешном выполнении теста
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Можно добавить логику при пропуске теста
    }
}


