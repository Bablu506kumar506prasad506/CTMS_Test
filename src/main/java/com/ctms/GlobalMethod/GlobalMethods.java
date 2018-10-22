package com.ctms.GlobalMethod;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import com.ctms.GlobalMethod.GlobalWait;
import com.ctms.AdminScenarios.UserManagement;

import jxl.Sheet;
import jxl.Workbook;

public class GlobalMethods<var> {

	public static WebDriver driver;

	public GlobalMethods() {
		PageFactory.initElements(driver, this);
	}

	public static String GmailURL = "https://accounts.google.com/signin/v2/identifier?service=mail&passive=true&rm=false&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F%3Ftab%3Dwm&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin";
	public static String CTMSURL = "http://ctmsweb.com:8010/#/login";
	public static String CTMSURL1 = "http://183.82.108.66:6262/ctms/#/login";
	public static int EmailValue = 1;

	static GlobalWait GWait = new GlobalWait(driver);
	Actions action = new Actions(driver);

	public static void LaunchBrowser(String browserName, String Url) {
		if (browserName.equals("firefox")) {
			/*
			 * System.setProperty("webdriver.firefox.driver",
			 * System.getProperty("user.dir") +
			 * "/src/main/resources/win/geckodriver.exe");
			 */
			driver = new FirefoxDriver();
		} else if (browserName.equals("chrome")) {
			/*
			 * DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			 * 
			 * ChromeOptions options = new ChromeOptions();
			 * 
			 * options.addArguments("--incognito");
			 * 
			 * capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			 */

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

	public static void Superadmin_Login() throws Exception {

		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/CTMS.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("LoginDetails");

		String UserName_Data = r1.getCell(3, 12).getContents();
		String Password_Data = r1.getCell(4, 12).getContents();

		driver.findElement(By.xpath("//main/app-login/div/div/mat-card/form/div[1]/div/input")).sendKeys(UserName_Data);
		WebElement sas = driver.findElement(By.cssSelector("form > div:nth-child(2) > div > input"));
		sas.sendKeys(Password_Data);
		driver.findElement(By.xpath("//button[@type='submit']")).click();

	}

	public static void Admin_Login() throws Exception {

		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/CTMS.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("LoginDetails");

		String UserName_Data = r1.getCell(3, 1).getContents();
		String Password_Data = r1.getCell(4, 1).getContents();

		driver.findElement(By.xpath("//main/app-login/div/div/mat-card/form/div[1]/div/input")).sendKeys(UserName_Data);
		WebElement sas = driver.findElement(By.cssSelector("form > div:nth-child(2) > div > input"));
		sas.sendKeys(Password_Data);
		driver.findElement(By.xpath("//button[@type='submit']")).click();

	}

	public static void openNewTabWithGmailURL() {
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.open()");

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get(GmailURL);
		/*
		 * String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
		 * driver.findElement(By.linkText(GmailURL)).sendKeys(
		 * selectLinkOpeninNewTab);
		 */
	}

	public static void openNewTabWithCTMSURL() {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.open()");

		ArrayList<String> tabs1 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs1.get(1));
		driver.navigate().to(CTMSURL);

	}

	public static void openOldTabL() {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));

	}

	public static void CloseNewTab() {
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "w");
		// Switch first tab
		driver.switchTo().defaultContent();
		driver.close();
	}

	public static String finalUsrNAM;
	public static String finalPass;

	public static void UserCreationMailFunctionality() throws Exception {

		openNewTabWithGmailURL();

		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/CTMS.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("UserMNGMT");

		String Emaillink_data = r1.getCell(2, 1).getContents();

		try {

			if (driver.findElement(By.id("identifierId")).isDisplayed()) {

				WebElement email_field11 = GWait.Wait_GetElementById("identifierId");
				email_field11.sendKeys(Emaillink_data);
				Thread.sleep(2500);
				WebElement nextbutton = GWait.Wait_GetElementByXpath("//div[@id='identifierNext']/content/span");
				nextbutton.click();
				WebElement pwd_field1 = GWait.Wait_GetElementByName("password");
				pwd_field1.sendKeys("qa@123456");
				Thread.sleep(2500);
				WebElement nextbutton1 = GWait.Wait_GetElementByXpath("//div[2]/div/div/content/span");
				nextbutton1.click();
				Thread.sleep(4500);
				WebElement link1 = GWait.Wait_GetElementByCSS(".asf.T-I-J3.J-J5-Ji");
				link1.click();
				List<WebElement> a = driver.findElements(By.xpath("//span/b[text()='CTMS - Login Details']"));
				System.out.println(a.size());

				if (a.get(0).getText().equalsIgnoreCase("CTMS - Login Details")) {
					a.get(0).click();
					WebElement pass1 = GWait.Wait_GetElementByXpath("//div[2]/div[3]/div[3]/div/p[3]");
					System.out.println(pass1.getText());
					String pass2 = pass1.getText();
					String[] passwordSplit = pass2.split(" ");
					System.out.println("1st: " + passwordSplit[0]);
					System.out.println("2nd: " + passwordSplit[1]);
					System.out.println("3rd: " + passwordSplit[2]);
					System.out.println("1st: " + passwordSplit[3]);
					System.out.println("2nd: " + passwordSplit[4]);
					System.out.println("3rd: " + passwordSplit[5]);
					System.out.println("1st: " + passwordSplit[6]);
					System.out.println("2nd: " + passwordSplit[7]);
					System.out.println("3rd: " + passwordSplit[8]);
					System.out.println("1st: " + passwordSplit[9]);
					System.out.println("2nd: " + passwordSplit[10]);
					System.out.println("3rd: " + passwordSplit[11]);
					System.out.println("1st: " + passwordSplit[12]);
					System.out.println("2nd: " + passwordSplit[13]);
					System.out.println("3rd: " + passwordSplit[14]);
					System.out.println("1st: " + passwordSplit[15]);
					System.out.println("2nd: " + passwordSplit[16]);
					System.out.println("3rd: " + passwordSplit[17]);
					System.out.println("1st: " + passwordSplit[18]);
					System.out.println("2nd: " + passwordSplit[19]);
					System.out.println("3rd: " + passwordSplit[20]);

					String UsernameSTR = passwordSplit[12];
					System.out.println("Username1: " + UsernameSTR);

					String PassWithClisk = passwordSplit[14];

					String UsrNAM = UsernameSTR.substring(0, UsernameSTR.length() - 9);
					System.out.println("Sub String: " + UsrNAM);
					finalUsrNAM = UsrNAM.substring(1);
					System.out.println("Final String: " + finalUsrNAM);

					finalPass = PassWithClisk.substring(0, PassWithClisk.length() - 6);
					System.out.println("Final String: " + finalPass);

					WebElement link11 = GWait.Wait_GetElementByCSS(".ar6.T-I-J3.J-J5-Ji");
					link11.click();
					/*
					 * Thread.sleep(3000);
					 * driver.findElement(By.cssSelector(".gb_8a.gbii")).click()
					 * ; WebElement signout=driver.findElement(By.id("gb_71"));
					 * Thread.sleep(1000); signout.click();
					 */

					Thread.sleep(2000);
					driver.close();

					// CloseNewTab();
					Thread.sleep(1000);

					openNewTabWithCTMSURL();

					// -----Login-----//

					GWait.Wait_GetElementByXpath("//main/app-login/div/div/mat-card/form/div[1]/div/input")
							.sendKeys(finalUsrNAM);
					WebElement sas = GWait.Wait_GetElementByCSS("form > div:nth-child(2) > div > input");
					sas.sendKeys(finalPass);
					driver.findElement(By.xpath("//button[@type='submit']")).click();

					// ----Reset Password----//
					Sheet r = wb.getSheet("LoginDetails");

					String SetNewPassword = r.getCell(4, 3).getContents();

					GWait.Wait_GetElementByCSS("input[formControlName=OldPassword]").sendKeys(finalPass);
					GWait.Wait_GetElementByCSS("input[formControlName=NewPassword]").sendKeys(SetNewPassword);
					GWait.Wait_GetElementByCSS("input[formControlName=ConfirmPassword]").sendKeys(SetNewPassword);
					driver.findElement(By.xpath("//button[@type='submit']")).click();
					Thread.sleep(10000);
					WebElement Logout_BTN = GWait.Wait_GetElementByXpath("//nav/div[2]/div[2]/button/span[2]");
					Logout_BTN.click();

					WebElement Logout = GWait.Wait_GetElementByXpath("//nav/div[2]/div[2]/ul/li[3]/a");
					Logout.click();
					Thread.sleep(1500);
					driver.close();
					openOldTabL();
					driver.navigate().refresh();
					Admin_Login();
					Thread.sleep(2000);
					WebElement navig = GWait.Wait_GetElementByCSS(".menu-ham > img:nth-child(1)");
					navig.click();

					WebElement Usermngmt = GWait.Wait_GetElementByLinkText("User Management");
					Usermngmt.click();
				}
			} // else {

			// }

		} catch (Exception ex) {

			Thread.sleep(4500);
			WebElement link11 = GWait.Wait_GetElementByCSS(".asf.T-I-J3.J-J5-Ji");
			link11.click();
			driver.navigate().refresh();
			List<WebElement> a111 = driver.findElements(By.xpath("//span/b[text()='CTMS - Login Details']"));
			System.out.println(a111.size());

			if (a111.get(0).getText().equalsIgnoreCase("CTMS - Login Details")) {
				a111.get(0).click();
				WebElement pass1 = GWait.Wait_GetElementByXpath("//div[2]/div[3]/div[3]/div/p[3]");
				System.out.println(pass1.getText());
				String pass2 = pass1.getText();
				String[] passwordSplit = pass2.split(" ");
				System.out.println("1st: " + passwordSplit[0]);
				System.out.println("2nd: " + passwordSplit[1]);
				System.out.println("3rd: " + passwordSplit[2]);
				System.out.println("1st: " + passwordSplit[3]);
				System.out.println("2nd: " + passwordSplit[4]);
				System.out.println("3rd: " + passwordSplit[5]);
				System.out.println("1st: " + passwordSplit[6]);
				System.out.println("2nd: " + passwordSplit[7]);
				System.out.println("3rd: " + passwordSplit[8]);
				System.out.println("1st: " + passwordSplit[9]);
				System.out.println("2nd: " + passwordSplit[10]);
				System.out.println("3rd: " + passwordSplit[11]);
				System.out.println("1st: " + passwordSplit[12]);
				System.out.println("2nd: " + passwordSplit[13]);
				System.out.println("3rd: " + passwordSplit[14]);
				System.out.println("1st: " + passwordSplit[15]);
				System.out.println("2nd: " + passwordSplit[16]);
				System.out.println("3rd: " + passwordSplit[17]);
				System.out.println("1st: " + passwordSplit[18]);
				System.out.println("2nd: " + passwordSplit[19]);
				System.out.println("3rd: " + passwordSplit[20]);

				String UsernameSTR = passwordSplit[12];
				System.out.println("Username1: " + UsernameSTR);

				String PassWithClisk = passwordSplit[14];

				String UsrNAM = UsernameSTR.substring(0, UsernameSTR.length() - 9);
				System.out.println("Sub String: " + UsrNAM);
				finalUsrNAM = UsrNAM.substring(1);
				System.out.println("Final String: " + finalUsrNAM);

				finalPass = PassWithClisk.substring(0, PassWithClisk.length() - 6);
				System.out.println("Final String: " + finalPass);

				Thread.sleep(2000);
				driver.close();

				// CloseNewTab();
				Thread.sleep(1000);

				openNewTabWithCTMSURL();

				// -----Login-----//

				GWait.Wait_GetElementByXpath("//main/app-login/div/div/mat-card/form/div[1]/div/input")
						.sendKeys(finalUsrNAM);
				WebElement sas = GWait.Wait_GetElementByCSS("form > div:nth-child(2) > div > input");
				sas.sendKeys(finalPass);
				driver.findElement(By.xpath("//button[@type='submit']")).click();

				// ----Reset Password----//
				Sheet r = wb.getSheet("LoginDetails");

				String SetNewPassword = r.getCell(4, 3).getContents();

				GWait.Wait_GetElementByCSS("input[formControlName=OldPassword]").sendKeys(finalPass);
				GWait.Wait_GetElementByCSS("input[formControlName=NewPassword]").sendKeys(SetNewPassword);
				GWait.Wait_GetElementByCSS("input[formControlName=ConfirmPassword]").sendKeys(SetNewPassword);
				driver.findElement(By.xpath("//button[@type='submit']")).click();
				Thread.sleep(10000);
				WebElement Logout_BTN = GWait.Wait_GetElementByXpath("//nav/div[2]/div[2]/button/span[2]");
				Logout_BTN.click();

				WebElement Logout = GWait.Wait_GetElementByXpath("//nav/div[2]/div[2]/ul/li[3]/a");
				Logout.click();
				Thread.sleep(1500);
				driver.close();
				openOldTabL();
				driver.navigate().refresh();
				Admin_Login();
				Thread.sleep(2000);
				WebElement navig = GWait.Wait_GetElementByCSS(".menu-ham > img:nth-child(1)");
				navig.click();

				WebElement Usermngmt = GWait.Wait_GetElementByLinkText("User Management");
				Usermngmt.click();

			}

		}

	}

	// ----Company Admin Password reset method-----//

	public static void CompanyCreationMailFunctionality() throws Exception {

		openNewTabWithGmailURL();

		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/CTMS.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("SuperadminRegsterComp");

		int rows = r1.getRows();
		System.out.println("No. of rowa: " + rows);
		// for (int i = 1; i <= rows; i++) {

		String Emaillink_data = r1.getCell(12, EmailValue).getContents();

		try {

			if (driver.findElement(By.id("identifierId")).isDisplayed()) {

				WebElement email_field11 = GWait.Wait_GetElementById("identifierId");
				email_field11.sendKeys(Emaillink_data);
				Thread.sleep(2500);
				WebElement nextbutton = GWait.Wait_GetElementByXpath("//div[@id='identifierNext']/content/span");
				nextbutton.click();
				WebElement pwd_field1 = GWait.Wait_GetElementByName("password");
				pwd_field1.sendKeys("qa@123456");
				Thread.sleep(2500);
				WebElement nextbutton1 = GWait.Wait_GetElementByXpath("//div/div/content/span");
				nextbutton1.click();
				Thread.sleep(4500);
				/*
				 * WebElement link1 =
				 * GWait.Wait_GetElementByCSS(".asf.T-I-J3.J-J5-Ji");
				 * link1.click();
				 */
				//// span/b
				List<WebElement> a = driver.findElements(By.xpath("//span/span[text()='Quad-CTMS - Login Details']"));
				System.out.println(a.size());

				for (int i1 = 0; i1 < a.size(); i1++) {
					System.out.println(a.get(i1).getText());
					if (a.get(i1).getText().equalsIgnoreCase("Quad-CTMS - Login Details")) {
						a.get(i1).click();
						WebElement pass1 = GWait.Wait_GetElementByXpath("//div[2]/div[3]/div[3]/div/p[3]");
						System.out.println(pass1.getText());
						String pass2 = pass1.getText();
						String[] passwordSplit = pass2.split(" ");
						System.out.println("1st: " + passwordSplit[0]);
						System.out.println("2nd: " + passwordSplit[1]);
						System.out.println("3rd: " + passwordSplit[2]);
						System.out.println("4st: " + passwordSplit[3]);
						System.out.println("5nd: " + passwordSplit[4]);
						System.out.println("6rd: " + passwordSplit[5]);
						System.out.println("7st: " + passwordSplit[6]);
						System.out.println("8nd: " + passwordSplit[7]);
						System.out.println("9rd: " + passwordSplit[8]);
						System.out.println("10st: " + passwordSplit[9]);
						System.out.println("11nd: " + passwordSplit[10]);
						System.out.println("12rd: " + passwordSplit[11]);
						System.out.println("13st: " + passwordSplit[12]);
						System.out.println("14nd: " + passwordSplit[13]);
						System.out.println("15rd: " + passwordSplit[14]);
						System.out.println("16st: " + passwordSplit[15]);
						System.out.println("17nd: " + passwordSplit[16]);
						System.out.println("18rd: " + passwordSplit[17]);
						System.out.println("19st: " + passwordSplit[18]);
						System.out.println("20nd: " + passwordSplit[19]);
						System.out.println("21rd: " + passwordSplit[20]);

						String UsernameSTR = passwordSplit[13];
						System.out.println("Username1: " + UsernameSTR);

						String PassWithClisk = passwordSplit[14];

						String UsrNAM = UsernameSTR.substring(0, UsernameSTR.length() - 9);
						System.out.println("Sub String: " + UsrNAM);
						finalUsrNAM = UsrNAM;
						System.out.println("Final String: " + finalUsrNAM);

						String Pass = PassWithClisk.substring(0, PassWithClisk.length() - 6);
						System.out.println("Sub String: " + Pass);
						finalPass = Pass.substring(1);
						System.out.println("Final String: " + finalPass);
						
						GWait.Wait_GetElementByCSS(".lS > div:nth-child(1)", 120).click();
						
						Thread.sleep(3000);
						GWait.Wait_GetElementByCSS("span.gb_9a.gbii").click();
						/*Thread.sleep(3000);
						WebElement signout = GWait.Wait_GetElementByXpath("//div[6]/div[4]/div[2]/a");
						Thread.sleep(1000);
						System.out.println(signout.getText());
						signout.click();*/
						
						Robot ro = new Robot();

						// A short pause, just to be sure that OK is selected
						Thread.sleep(3000);

						ro.keyPress(KeyEvent.VK_ALT);
						ro.keyPress(KeyEvent.VK_S);
						ro.keyPress(KeyEvent.VK_ENTER);
						ro.keyRelease(KeyEvent.VK_ENTER);


						Thread.sleep(2000);
						driver.close();

						// CloseNewTab();
						Thread.sleep(1000);

						openNewTabWithCTMSURL();

						// -----Login-----//

						GWait.Wait_GetElementByXpath("//main/app-login/div/div/mat-card/form/div[1]/div/input")
								.sendKeys(finalUsrNAM);
						WebElement sas = GWait.Wait_GetElementByCSS("form > div:nth-child(2) > div > input");
						sas.sendKeys(finalPass);
						driver.findElement(By.xpath("//button[@type='submit']")).click();

						// ----Reset Password----//
						Sheet r = wb.getSheet("LoginDetails");

						String SetNewPassword = r.getCell(4, 3).getContents();

						GWait.Wait_GetElementByCSS("input[formControlName=OldPassword]").sendKeys(finalPass);
						GWait.Wait_GetElementByCSS("input[formControlName=NewPassword]").sendKeys(SetNewPassword);
						GWait.Wait_GetElementByCSS("input[formControlName=ConfirmPassword]").sendKeys(SetNewPassword);
						driver.findElement(By.xpath("//button[@type='submit']")).click();
						Thread.sleep(10000);
						WebElement Logout_BTN = GWait.Wait_GetElementByXpath("//nav/div[2]/div[2]/button/span[2]");
						Logout_BTN.click();

						WebElement Logout = GWait.Wait_GetElementByCSS("a.dropdown-item.a-cursor");
						Logout.click();
						Thread.sleep(1500);
						driver.close();
						openOldTabL();
						driver.navigate().refresh();
						Superadmin_Login();
						Thread.sleep(2000);
						WebElement RegisterCOMP = GWait.Wait_GetElementByXpath("//div/div/div/button");
						RegisterCOMP.click();
					}
					EmailValue++;
				}

			} // else {

			// }

		} catch (Exception ex) {

			Thread.sleep(4500);
			/*
			 * WebElement link11 =
			 * GWait.Wait_GetElementByCSS(".asf.T-I-J3.J-J5-Ji");
			 * link11.click();
			 */
			driver.navigate().refresh();
			List<WebElement> a111 = driver.findElements(By.xpath("//span/span[text()='Quad-CTMS - Login Details']"));
			System.out.println(a111.size());

			for (int i1 = 0; i1 < a111.size(); i1++) {
				System.out.println(a111.get(i1).getText());
				if (a111.get(i1).getText().equalsIgnoreCase("Quad-CTMS - Login Details")) {
					a111.get(i1).click();
					WebElement pass1 = GWait.Wait_GetElementByXpath("//div[2]/div[3]/div[3]/div/p[3]");
					System.out.println(pass1.getText());
					String pass2 = pass1.getText();
					String[] passwordSplit = pass2.split(" ");
					System.out.println("1st: " + passwordSplit[0]);
					System.out.println("2nd: " + passwordSplit[1]);
					System.out.println("3rd: " + passwordSplit[2]);
					System.out.println("4st: " + passwordSplit[3]);
					System.out.println("5nd: " + passwordSplit[4]);
					System.out.println("6rd: " + passwordSplit[5]);
					System.out.println("7st: " + passwordSplit[6]);
					System.out.println("8nd: " + passwordSplit[7]);
					System.out.println("9rd: " + passwordSplit[8]);
					System.out.println("10st: " + passwordSplit[9]);
					System.out.println("11nd: " + passwordSplit[10]);
					System.out.println("12rd: " + passwordSplit[11]);
					System.out.println("13st: " + passwordSplit[12]);
					System.out.println("14nd: " + passwordSplit[13]);
					System.out.println("15rd: " + passwordSplit[14]);
					System.out.println("16st: " + passwordSplit[15]);
					System.out.println("17nd: " + passwordSplit[16]);
					System.out.println("18rd: " + passwordSplit[17]);
					System.out.println("19st: " + passwordSplit[18]);
					System.out.println("20nd: " + passwordSplit[19]);
					System.out.println("21rd: " + passwordSplit[20]);

					String UsernameSTR = passwordSplit[13];
					System.out.println("Username1: " + UsernameSTR);

					String PassWithClisk = passwordSplit[14];

					String UsrNAM = UsernameSTR.substring(0, UsernameSTR.length() - 9);
					System.out.println("Sub String: " + UsrNAM);
					finalUsrNAM = UsrNAM;
					System.out.println("Final String: " + finalUsrNAM);

					String Pass = PassWithClisk.substring(0, PassWithClisk.length() - 6);
					System.out.println("Sub String: " + Pass);
					finalPass = Pass.substring(1);
					System.out.println("Final String: " + finalPass);

					Thread.sleep(2000);
					driver.close();

					// CloseNewTab();
					Thread.sleep(1000);

					openNewTabWithCTMSURL();

					// -----Login-----//

					GWait.Wait_GetElementByXpath("//main/app-login/div/div/mat-card/form/div[1]/div/input")
							.sendKeys(finalUsrNAM);
					WebElement sas = GWait.Wait_GetElementByCSS("form > div:nth-child(2) > div > input");
					sas.sendKeys(finalPass);
					driver.findElement(By.xpath("//button[@type='submit']")).click();

					// ----Reset Password----//
					Sheet r = wb.getSheet("LoginDetails");

					String SetNewPassword = r.getCell(4, 3).getContents();

					GWait.Wait_GetElementByCSS("input[formControlName=OldPassword]").sendKeys(finalPass);
					GWait.Wait_GetElementByCSS("input[formControlName=NewPassword]").sendKeys(SetNewPassword);
					GWait.Wait_GetElementByCSS("input[formControlName=ConfirmPassword]").sendKeys(SetNewPassword);
					driver.findElement(By.xpath("//button[@type='submit']")).click();
					Thread.sleep(10000);
					WebElement Logout_BTN = GWait.Wait_GetElementByXpath("//nav/div[2]/div[2]/button/span[2]");
					Logout_BTN.click();

					WebElement Logout = GWait.Wait_GetElementByCSS("a.dropdown-item.a-cursor");
					Logout.click();
					Thread.sleep(1500);
					driver.close();
					openOldTabL();
					driver.navigate().refresh();
					Superadmin_Login();
					Thread.sleep(2000);
					WebElement RegisterCOMP = GWait.Wait_GetElementByXpath("//div/div/div/button");
					RegisterCOMP.click();

				}
				EmailValue++;
			}

		}

		// }

	}

	public static void scrollToElement(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
	}

}
