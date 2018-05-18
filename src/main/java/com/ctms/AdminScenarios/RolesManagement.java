package com.ctms.AdminScenarios;

import java.io.FileInputStream;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.ctms.GlobalMethod.GlobalMethods;
import com.ctms.GlobalMethod.GlobalWait;

import jxl.Sheet;
import jxl.Workbook;

public class RolesManagement {

	public RolesManagement() {
		PageFactory.initElements(GlobalMethods.driver, this);
	}

	GlobalWait GWait = new GlobalWait(GlobalMethods.driver);
	Actions action = new Actions(GlobalMethods.driver);

	@FindBy(css = ".menu-ham > img:nth-child(1)")
	WebElement navig;

	public void RoleManagmnt() throws Exception {
		GlobalMethods.Admin_Login();

		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/DataFile.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("RoleManagement");

		WebElement navig = GWait.Wait_GetElementByCSS(".menu-ham > img:nth-child(1)");
		navig.click();

		WebElement AdminTaskNavig = GWait.Wait_GetElementByCSS("li.ng-star-inserted:nth-child(1) > a:nth-child(1)");
		AdminTaskNavig.click();

		WebElement Rolemngmt = GWait.Wait_GetElementByXpath("//nav/ul/li[1]/div/ul/li[1]/a");
		Rolemngmt.click();
		try {
			WebElement outclick = GWait.Wait_GetElementByCSS(".mat-drawer-content > div:nth-child(1)");
			outclick.click();

		} catch (Exception e) {
			e.getMessage();
		}
		WebElement shadow = GWait.Wait_GetElementByXpath("/html/body/app-root/mat-sidenav-container/div[1]");
		shadow.click();
		Thread.sleep(4000);
		WebElement AddRole_BTN = GWait
				.Wait_GetElementByXpath("//main/app-admin/app-roles-management/div/div[1]/div/button");
		AddRole_BTN.click();
		Thread.sleep(2000);
		WebElement name_TXT = GWait.Wait_GetElementById("name", 60);
		name_TXT.sendKeys("Test");

		WebElement Cancel_BTN = GWait.Wait_GetElementByCSS("button.blue-rnd-btn:nth-child(2)");
		Cancel_BTN.click();

		int RowCount = r1.getRows();
		System.out.println("No. of rows:  " + RowCount);
		for (int i = 3; i <= RowCount - 1; i++) {
			String RoleName_Data = r1.getCell(0, i).getContents();
			String ParentRole_Data = r1.getCell(1, i).getContents();

			String Test = ParentRole_Data;
			Thread.sleep(4000);
			WebElement AddRole_BTN1 = GWait
					.Wait_GetElementByXpath("//main/app-admin/app-roles-management/div/div[1]/div/button");
			AddRole_BTN1.click();
			WebElement name_TXT1 = GWait.Wait_GetElementById("name", 60);
			name_TXT1.sendKeys(RoleName_Data);
			if (Test != "") {
				List<WebElement> SelectRole1 = GlobalMethods.driver.findElements(By.xpath("//form/div[2]/div/select"));
				for (WebElement webElement : SelectRole1) {
					Select se1 = new Select(webElement);
					List<WebElement> PRole = se1.getOptions();
					System.out.println("Test@1 " + se1.getOptions());
					for (WebElement webElement2 : PRole) {
						System.out.println("Test Element1: " + webElement2.getText());
						System.out.println("Test Element2: " + Test);
						if (webElement2.getText().trim().equalsIgnoreCase(Test)) {

							System.out.println(webElement2);

							webElement2.click();

							WebElement Submit_BTN = GWait.Wait_GetElementByXpath("//button[@type='submit']");
							Submit_BTN.click();
							break;
						}

					}
				}
			} else {
				WebElement Submit_BTN = GWait.Wait_GetElementByXpath("//button[@type='submit']");
				Submit_BTN.click();
			}

		}
		Thread.sleep(8000);
		WebElement Logout_BTN = GWait.Wait_GetElementByXpath("//nav/div[2]/div[2]/button/span[2]");
		Logout_BTN.click();

		WebElement Logout = GWait.Wait_GetElementByXpath("//nav/div[2]/div[2]/ul/li[3]/a");
		Logout.click();

	}

}
