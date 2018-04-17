package com.ctms.AdminScenarios;

import java.io.FileInputStream;

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
		Sheet r1 = wb.getSheet("AdminTask_RoleManagement");

		

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
		Thread.sleep(1500);
		WebElement AddRole_BTN = GWait
				.Wait_GetElementByXpath("//main/app-admin/app-roles-management/div/div[1]/div/button");
		AddRole_BTN.click();

		WebElement SelectRole = GWait.Wait_GetElementByXpath("//form/div[2]/div/select");
		Select se = new Select(SelectRole);
		se.selectByIndex(1);

		WebElement Cancel_BTN = GWait.Wait_GetElementByCSS("button.blue-rnd-btn:nth-child(2)");
		Cancel_BTN.click();
		for (int i = 1; i <= 9; i++) {
			String RoleName_Data = r1.getCell(0, i).getContents();
			String ParentRole_Data = r1.getCell(1, i).getContents();
			
			WebElement AddRole_BTN1 = GWait
					.Wait_GetElementByXpath("//main/app-admin/app-roles-management/div/div[1]/div/button");
			AddRole_BTN1.click();
			WebElement name_TXT1 = GWait.Wait_GetElementById("name", 60);
			name_TXT1.sendKeys(RoleName_Data);

			WebElement SelectRole1 = GWait.Wait_GetElementByXpath("//form/div[2]/div/select");
			Select se1 = new Select(SelectRole1);
			se1.selectByVisibleText(ParentRole_Data);

			WebElement Submit_BTN = GWait.Wait_GetElementByXpath("//button[@type='submit']");
			Submit_BTN.click();
		}
		
		WebElement Logout_BTN = GWait.Wait_GetElementByXpath("//nav/div[2]/div[2]/button/span[2]");
		Logout_BTN.click();
		
		WebElement Logout = GWait.Wait_GetElementByXpath("//nav/div[2]/div[2]/ul/li[3]/a");
		Logout.click();

	}

}
