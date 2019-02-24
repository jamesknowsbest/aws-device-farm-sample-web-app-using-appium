/*
 * Copyright 2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package Tests;

import java.io.File;
import java.util.List;

import Tests.AbstractBaseTests.TestBase;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.testng.annotations.Test;

/**
 * Test.
 */
public class ScreenshotTest extends TestBase {

    private final String TEST_URL = "http://docs.aws.amazon.com/devicefarm/latest/developerguide/welcome.html";
	private static final int MAX_WEBSITE_LOAD_TIME = 100;
	private final String TEST_LATENCY_URL = "http://speedtest.googlefiber.net/";
    @Test
    public void testScreenshot() throws InterruptedException {

		Thread.sleep(5000);
		driver.get(TEST_URL);
		Thread.sleep(5000);
		// This will store the screenshot under /tmp on your local machine
		String screenshotDir = System.getProperty("appium.screenshots.dir", System.getProperty("java.io.tmpdir", ""));
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		screenshot.renameTo(new File(screenshotDir, "device_farm.png"));

	}
	
	@Test  public void testLatency() throws InterruptedException{
		Thread.sleep(5000);
		driver.get(TEST_LATENCY_URL);
		Thread.sleep(5000);
		
        // This will store the screenshot under /tmp on your local machine
        String screenshotDir = System.getProperty("appium.screenshots.dir", System.getProperty("java.io.tmpdir", ""));
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        screenshot.renameTo(new File(screenshotDir, "test_starting.png"));

        //create the web driver
        Object[] contextHandles = driver.getContextHandles().toArray();
        String webViewContent = (String) contextHandles[contextHandles.length - 1];
        WebDriver webDriver = driver.context(webViewContent);

        WebDriverWait wait = new WebDriverWait(webDriver, MAX_WEBSITE_LOAD_TIME);

        // wait for page to load fully
        wait.until(ExpectedConditions.elementToBeClickable(By.id("run-test")));
        //click on start btn
        WebElement runTestBtn = webDriver.findElement(By.id("run-test"));
        runTestBtn.click();

        // click on continue button if it's there
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("view39")));
        List<WebElement> continueBtn = webDriver.findElements(By.id("view39"));
        for (WebElement btn : continueBtn) {
            btn.click();
        }

        //wait for the test to stop
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("view32")));

        //take a new screen shot
        screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        screenshot.renameTo(new File(screenshotDir, "test_finished.png"));
    }
}
