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

public class RolesXActionManagement {

	public RolesXActionManagement() {
		PageFactory.initElements(GlobalMethods.driver, this);
	}

	GlobalWait GWait = new GlobalWait(GlobalMethods.driver);
	Actions action = new Actions(GlobalMethods.driver);

	@FindBy(xpath = "//*[@class='panel ng-star-inserted']")
	List<WebElement> featursDataList;

	public void RoleXActionMngmnt() throws Exception {

		GlobalMethods.Admin_Login();

		WebElement navig = GWait.Wait_GetElementByCSS(".menu-ham > img:nth-child(1)");
		navig.click();

		WebElement AdminTaskNavig = GWait.Wait_GetElementByCSS("li.ng-star-inserted:nth-child(1) > a:nth-child(1)");
		AdminTaskNavig.click();

		WebElement Rolemngmt = GWait.Wait_GetElementByXpath("//nav/ul/li[1]/div/ul/li[4]/a");
		Rolemngmt.click();
		try {
			WebElement outclick = GWait.Wait_GetElementByCSS(".mat-drawer-content > div:nth-child(1)");
			outclick.click();

		} catch (Exception e) {
			e.getMessage();
		}
		WebElement shadow = GWait.Wait_GetElementByXpath("/html/body/app-root/mat-sidenav-container/div[1]");
		shadow.click();
		Thread.sleep(2000);

		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/DataFile.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("RoleXFeatureMNGMNT");

		int rowval = r1.getRows();
		System.out.println("No of rows: " + rowval);
		for (int i = 1; i <= rowval-1; i++) {

			String RoleName_Data = r1.getCell(0, i).getContents();
			Thread.sleep(2000);
			WebElement SelectRole = GWait.Wait_GetElementByXpath("//app-roles-actions-edit/div/div/div/select");
			SelectRole.click();
			Select se = new Select(SelectRole);
			se.selectByVisibleText(RoleName_Data);
			int colval = r1.getRow(i).length;
			int k = 0;
			System.out.println("No of Colms: " + colval);
			System.out.println(featursDataList.size());
			for (int j = 1; j <= featursDataList.size(); j++) {

				WebElement table_element = GWait.Wait_GetElementByXpath("//*[@id='feature" + k + "']");
				System.out.println(table_element.getText());
				table_element.click();
				Thread.sleep(1500);
				List<WebElement> table_Subelement = GlobalMethods.driver
						.findElements(By.xpath("//a[@class='table-btn text-center margin-auto']"));
				for (WebElement webElement : table_Subelement) {
					if (webElement.getText().equalsIgnoreCase("Disable")) {
						webElement.click();
					}
				}

				k++;
			}
		}
		Thread.sleep(8000);
		WebElement Logout_BTN = GWait.Wait_GetElementByXpath("//nav/div[2]/div[2]/button/span[2]");
		Logout_BTN.click();

		WebElement Logout = GWait.Wait_GetElementByXpath("//nav/div[2]/div[2]/ul/li[3]/a");
		Logout.click();
	}

}
