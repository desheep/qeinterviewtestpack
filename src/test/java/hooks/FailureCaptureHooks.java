package hooks;

import context.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.*;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FailureCaptureHooks {

    private final ScenarioContext scenarioContext;

    public FailureCaptureHooks(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @After(order = 1) // runs before DriverHooks @After(order=0)
    public void captureOnFailure(Scenario scenario) {
        if (!scenario.isFailed()) return;

        WebDriver driver = scenarioContext.getDriver();
        if (driver == null) return;

        String safeName = scenario.getName().replaceAll("[^a-zA-Z0-9-_\\.]", "_");
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        Path outDir = Paths.get("target", "failure-artifacts", safeName + "_" + timestamp);

        try {
            Files.createDirectories(outDir);

            try {
                Files.write(outDir.resolve("url.txt"), driver.getCurrentUrl().getBytes());
            } catch (Exception ignored) { }

            if (driver instanceof TakesScreenshot) {
                byte[] png = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Files.write(outDir.resolve("screenshot.png"), png);
            }

            try {
                Files.write(outDir.resolve("page.html"), driver.getPageSource().getBytes());
            } catch (Exception ignored) { }

            try {
                Files.write(outDir.resolve("title.txt"), driver.getTitle().getBytes());
            } catch (Exception ignored) { }

            System.out.println("Saved failure artifacts to: " + outDir.toAbsolutePath());

        } catch (IOException e) {
            System.out.println("Failed to write failure artifacts: " + e.getMessage());
        }
    }
}