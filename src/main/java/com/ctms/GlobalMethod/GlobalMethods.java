package com.ctms.GlobalMethod;

import java.io.FileInputStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import com.ctms.GlobalMethod.GlobalWait;

import jxl.Sheet;
import jxl.Workbook;


public class GlobalMethods {
	
	public static WebDriver driver;
	
	
	
	public static void LaunchBrowser(String browserName, String Url) {
		if (browserName.equals("firefox")) {
			System.setProperty("webdriver.firefox.driver",
					System.getProperty("user.dir") + "/src/main/resources/win/geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/src/main/resources/win/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("ie")) {
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "/src/main/resources/win/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}

		driver.manage().window().maximize();
		driver.get(Url);
	}
	
	
	public static void Admin_Login() throws Exception {
		
		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/DataFile.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("LoginData");

		String UserName_Data = r1.getCell(1,1).getContents();
		String Password_Data = r1.getCell(2,1).getContents();
		
		driver.findElement(By.xpath("//main/app-login/div/div/mat-card/form/div[1]/div/input")).sendKeys(UserName_Data);
		WebElement sas =driver.findElement(By.cssSelector("form > div:nth-child(2) > div > input"));
		sas.sendKeys(Password_Data);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
	}
	
}
