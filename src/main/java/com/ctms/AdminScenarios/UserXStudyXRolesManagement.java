package com.ctms.AdminScenarios;

import java.io.FileInputStream;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.ctms.GlobalMethod.GlobalMethods;
import com.ctms.GlobalMethod.GlobalWait;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import jxl.Sheet;
import jxl.Workbook;

public class UserXStudyXRolesManagement {

	public UserXStudyXRolesManagement() {
		PageFactory.initElements(GlobalMethods.driver, this);
	}

	GlobalWait GWait = new GlobalWait(GlobalMethods.driver);
	Actions action = new Actions(GlobalMethods.driver);
	
	JavascriptExecutor js;

	public void UsrXStdyXRlsMNGMNT() throws Exception {
		GlobalMethods.Admin_Login();

		WebElement navig = GWait.Wait_GetElementByCSS(".menu-ham > img:nth-child(1)");
		navig.click();

		WebElement AdminTaskNavig = GWait.Wait_GetElementByCSS("li.ng-star-inserted:nth-child(1) > a:nth-child(1)");
		AdminTaskNavig.click();

		WebElement UsrXStdyXRolemngmt = GWait.Wait_GetElementByXpath("//nav/ul/li[1]/div/ul/li[5]/a");
		UsrXStdyXRolemngmt.click();
		
		Thread.sleep(1500);

		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/DataFile.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("Roles Management");
		int RowCount = r1.getRows();
		System.out.println(RowCount);
		try {
			for (int i = 1; i <= RowCount - 1; i++) {

				String UserName_Data = r1.getCell(0, i).getContents();
				String RoleName_Data = r1.getCell(1, i).getContents();
				String Study_Data = r1.getCell(2, i).getContents();
				Thread.sleep(2000);
				WebElement SelectUser = GWait.Wait_GetElementByXpath("//form/div[1]/div/select");
				Select se = new Select(SelectUser);
				se.selectByVisibleText(UserName_Data);

				List<WebElement> AllStudy = GlobalMethods.driver.findElements(By.className("fixedWidth"));
				System.out.println("Test 0: " + AllStudy);
				for (WebElement webElement : AllStudy) {
					System.out.println("Test 1: " + Study_Data);
					String Test = Study_Data;
					if (webElement.getText().equalsIgnoreCase(Study_Data)) {
						WebElement chebox = GlobalMethods.driver.findElement(By.name(Test));
						Thread.sleep(2500);
						// ---------Click the study check box------//
						js = (JavascriptExecutor) GlobalMethods.driver;
						js.executeScript("arguments[0].scrollIntoView(true);", chebox);
						chebox.click();
						System.out.println("Test 2: " + chebox.getAttribute("name"));
						List<WebElement> SelectRoles = GlobalMethods.driver
								.findElements(By.xpath("//td[3]/div/select"));
						for (WebElement webElement2 : SelectRoles) {
							System.out.println("Test 3: " + webElement2.getAttribute("name"));
							if (webElement2.getAttribute("name").equalsIgnoreCase(Study_Data)) {
								// -----Select the Roles-----//
								Select se1 = new Select(webElement2);
								se1.selectByVisibleText(RoleName_Data);
								Thread.sleep(1500);
								WebElement Submit_BTN = GWait.Wait_GetElementByXpath("//button[@type='submit']");
								js = (JavascriptExecutor) GlobalMethods.driver;
								js.executeScript("arguments[0].scrollIntoView(true);", Submit_BTN);
								Submit_BTN.click();
								break;
							}

						}

					}
				}

			}
		} catch (Exception e) {
			e.getMessage();
		}

		Thread.sleep(10000);
		WebElement Logout_BTN = GWait.Wait_GetElementByXpath("//nav/div[2]/div[2]/button/span[2]");
		js = (JavascriptExecutor) GlobalMethods.driver;
		js.executeScript("arguments[0].scrollIntoView(true);", Logout_BTN);
		Logout_BTN.click();

		WebElement Logout = GWait.Wait_GetElementByXpath("//nav/div[2]/div[2]/ul/li[3]/a");
		Logout.click();

	}

}
