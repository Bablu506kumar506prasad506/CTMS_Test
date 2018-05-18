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

//----------------Assign CRA class related to the Project List class--------//
public class PM_AssignCRA {

	public PM_AssignCRA() {
		PageFactory.initElements(GlobalMethods.driver, this);
	}

	GlobalWait GWait = new GlobalWait(GlobalMethods.driver);
	Actions action = new Actions(GlobalMethods.driver);

	@FindBy(xpath = "//mat-card/form/div[1]/div/select")
	List<WebElement> SiteNameList;

	@FindBy(xpath = "//div/div[2]/div[3]/ul/span/li")
	List<WebElement> AssignCRAList;

	public void AssignCRAMethod() throws Exception {
		// GlobalMethods.Admin_Login();

		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/DataFile.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("AssignCRA");

		WebElement navig = GWait.Wait_GetElementByCSS(".menu-ham > img:nth-child(1)");
		navig.click();

		WebElement ProjectMNGMT_Link = GWait.Wait_GetElementByXpath("//nav/ul/li[6]");
		ProjectMNGMT_Link.click();
		WebElement AssignCRAList_Link = GWait.Wait_GetElementByXpath("//nav/ul/li[6]/div/ul/li[2]/a");
		AssignCRAList_Link.click();

		try {
			WebElement outclick = GWait.Wait_GetElementByCSS(".mat-drawer-content > div:nth-child(1)");
			outclick.click();

		} catch (Exception e) {
			e.getMessage();
		}
		WebElement shadow = GWait.Wait_GetElementByXpath("/html/body/app-root/mat-sidenav-container/div[1]");
		shadow.click();

		Thread.sleep(3000);

		int rowval = r1.getRows();
		System.out.println("Test00 :" + rowval);
		for (int i = 1; i <= 1; i++) {

			
			String SiteName_Data = r1.getCell(1, i).getContents();
			String[] SiteList = SiteName_Data.split(",");
			// ---Select Site-----//
			for (String site : SiteList) {
				WebElement AssignCRA_BTN = GWait.Wait_GetElementByXpath("//div/div[1]/div/button");
				AssignCRA_BTN.click();
				for (WebElement SiteElement : SiteNameList) {
					System.out.println(SiteElement.getText());

					Select se = new Select(SiteElement);
					List<WebElement> PRole = se.getOptions();
					System.out.println("Test@1 " + se.getOptions());
					for (WebElement webElement2 : PRole) {
						System.out.println("Test Element1: " + webElement2.getText());
						System.out.println("Test Element2: " + site);
						if (webElement2.getText().trim().equalsIgnoreCase(site)) {
							System.out.println(webElement2);
							webElement2.click();

							// ----Assign CRA---//
							WebElement AssignCRA_dropDwn = GWait.Wait_GetElementByXpath("//div/div[1]/div/span[1]");
							AssignCRA_dropDwn.click();
							int colval = r1.getColumns();
							System.out.println("Test00 :" + colval);
							for (int j = 2; j <= colval - 1; j++) {
								String AssignCRA_Data = r1.getCell(j, i).getContents();
								String CRA = AssignCRA_Data;

								for (WebElement AssgnCRAElement : AssignCRAList) {
									System.out.println(AssgnCRAElement.getText());
									if (AssgnCRAElement.getText().equalsIgnoreCase(CRA)) {
										AssgnCRAElement.click();
										break;
									}
								}
							}

							WebElement outclick = GWait.Wait_GetElementByCSS(".mat-drawer-content > div:nth-child(1)");
							outclick.click();
							WebElement Assign_BTN = GWait.Wait_GetElementByXpath("//button[@type='submit']");
							Assign_BTN.click();
							break;
						}

					}

				}
			}

		}

	}
}
