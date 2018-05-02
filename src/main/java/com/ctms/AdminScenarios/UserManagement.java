package com.ctms.AdminScenarios;

import java.io.FileInputStream;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.ctms.GlobalMethod.GlobalMethods;
import com.ctms.GlobalMethod.GlobalWait;

import jxl.Sheet;
import jxl.Workbook;

public class UserManagement {

	public UserManagement() {
		PageFactory.initElements(GlobalMethods.driver, this);
	}

	GlobalWait GWait = new GlobalWait(GlobalMethods.driver);
	Actions action = new Actions(GlobalMethods.driver);

	@FindBy(xpath = "//div/form/div[1]/div/select/option")
	public static List<WebElement> slectRole;

	public void UserMngmnt() throws Exception {
		GlobalMethods.Admin_Login();

		WebElement navig = GWait.Wait_GetElementByCSS(".menu-ham > img:nth-child(1)");
		navig.click();

		WebElement AdminTaskNavig = GWait.Wait_GetElementByCSS("li.ng-star-inserted:nth-child(1) > a:nth-child(1)");
		AdminTaskNavig.click();

		WebElement Rolemngmt = GWait.Wait_GetElementByXpath("//nav/ul/li[1]/div/ul/li[2]/a");
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
		WebElement AddUser_BTN = GWait
				.Wait_GetElementByXpath("//main/app-admin/app-user-management/div/div[1]/div/button");
		AddUser_BTN.click();
		Thread.sleep(2000);
		WebElement SelectRole = GWait.Wait_GetElementByXpath("//form/div[1]/div/select");
		Select se = new Select(SelectRole);
		se.selectByIndex(1);

		WebElement Cancel_BTN = GWait.Wait_GetElementByCSS("button.blue-rnd-btn:nth-child(2)");
		Cancel_BTN.click();

		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/DataFile.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("UserManagement");
		System.out.println(slectRole.size());
		int RowCount = r1.getRows();
		System.out.println(RowCount);
		for (int i = 1; i <= RowCount-1; i++) {

			String RoleName_Data = r1.getCell(0, i).getContents();
			String Name_Data = r1.getCell(1, i).getContents();
			String EmailID_Data = r1.getCell(2, i).getContents();
			String MobileNumber_Data = r1.getCell(3, i).getContents();
			String UserName_Data = r1.getCell(4, i).getContents();
			Thread.sleep(4000);
			WebElement AddUser_BTN1 = GWait
					.Wait_GetElementByXpath("//main/app-admin/app-user-management/div/div[1]/div/button");
			AddUser_BTN1.click();

			WebElement SelectRole1 = GWait.Wait_GetElementByXpath("//form/div[1]/div/select");
			Select se1 = new Select(SelectRole1);
			se1.selectByVisibleText(RoleName_Data);

			WebElement Name_Field = GWait.Wait_GetElementByName("Name");
			Name_Field.sendKeys(Name_Data);
			WebElement EmailId_Field = GWait.Wait_GetElementByName("EmailId");
			EmailId_Field.sendKeys(EmailID_Data);
			WebElement ContactNo_Field = GWait.Wait_GetElementByName("ContactNo");
			ContactNo_Field.sendKeys(MobileNumber_Data);
			WebElement UserName_Field = GWait.Wait_GetElementByName("UserName");
			UserName_Field.sendKeys(UserName_Data);
			WebElement Submit_BTN = GWait.Wait_GetElementByXpath("//button[@type='submit']");
			Submit_BTN.click();
			Thread.sleep(3000);

		}
		
		Thread.sleep(10000);
		WebElement Logout_BTN = GWait.Wait_GetElementByXpath("//nav/div[2]/div[2]/button/span[2]");
		Logout_BTN.click();
		
		WebElement Logout = GWait.Wait_GetElementByXpath("//nav/div[2]/div[2]/ul/li[3]/a");
		Logout.click();

	}
}
