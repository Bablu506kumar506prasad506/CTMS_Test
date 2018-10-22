package com.ctms.execution;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

public class TestGmail {

	static WebDriver driver;

	@Test
	public void quest() throws Exception {

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		System.setProperty("webdriver.chrome.driver", "E:\\Automation Files\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(capabilities);

		driver.get(
				"https://accounts.google.com/signin/v2/identifier?service=mail&passive=true&rm=false&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F%3Ftab%3Dwm&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
		boolean s = driver.findElements(By.cssSelector("svg > path")).size() > 0;
		if (s == true) {
			driver.findElement(By.cssSelector("svg > path")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@id='identifierLink']/div[2]/p")).click();

		}
		Thread.sleep(5000);
		driver.findElement(By.id("identifierId")).sendKeys("qualeetee112@gmail.com");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@id='identifierNext']/content/span")).click();
		Thread.sleep(3000);
		driver.findElement(By.name("password")).sendKeys("qa@123456");

		Thread.sleep(10000);
		driver.findElement(By.xpath(".//*[@id='passwordNext']/content/span")).click();
		// Thread.sleep(30000);

		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".gb_8a.gbii")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Sign out")).click();
		Thread.sleep(2000);
		driver.close();

	}
}