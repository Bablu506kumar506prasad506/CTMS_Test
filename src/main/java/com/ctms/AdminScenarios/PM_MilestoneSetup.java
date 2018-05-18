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

public class PM_MilestoneSetup {

	public PM_MilestoneSetup() {
		PageFactory.initElements(GlobalMethods.driver, this);
	}

	GlobalWait GWait = new GlobalWait(GlobalMethods.driver);
	Actions action = new Actions(GlobalMethods.driver);

	@FindBy(xpath = "//div/form/div[1]/div/select")
	List<WebElement> MilestoneList;
	@FindBy(css = ".mat-radio-label-content")
	List<WebElement> RadioTypeList;

	public void MilestoneSetupMethod() throws Exception {

		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/DataFile.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("AddMilestone");

		WebElement navig = GWait.Wait_GetElementByCSS(".menu-ham > img:nth-child(1)");
		navig.click();

		WebElement ProjectMNGMT_Link = GWait.Wait_GetElementByXpath("//nav/ul/li[6]");
		ProjectMNGMT_Link.click();
		WebElement MilestoneSetupList_Link = GWait.Wait_GetElementByXpath("//nav/ul/li[6]/div/ul/li[3]/a");
		MilestoneSetupList_Link.click();

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
		for (int i = 1; i <= rowval - 1; i++) {

			String MilestoneType_Data = r1.getCell(0, i).getContents();
			String MilestoneName_Data = r1.getCell(1, i).getContents();
			String CreditPeriod_Data = r1.getCell(2, i).getContents();
			String AmountType_Data = r1.getCell(3, i).getContents();
			String AmountTOBeRaised_Data = r1.getCell(4, i).getContents();

			WebElement AssignCRA_BTN = GWait.Wait_GetElementByXpath("//div/div[1]/div/button");
			AssignCRA_BTN.click();

			for (WebElement milestoneselement : MilestoneList) {

				System.out.println(milestoneselement.getText());

				Select se = new Select(milestoneselement);
				List<WebElement> milstones = se.getOptions();
				System.out.println("Test@1 " + se.getOptions());
				for (WebElement webElement2 : milstones) {
					System.out.println("Test Element1: " + webElement2.getText());
					System.out.println("Test Element2: " + MilestoneType_Data);
					if (webElement2.getText().trim().equalsIgnoreCase(MilestoneType_Data)) {
						System.out.println(webElement2);
						webElement2.click();
						WebElement MilestoneName = GWait.Wait_GetElementByCSS("input[formControlName=MilestoneName]");
						MilestoneName.sendKeys(MilestoneName_Data);
						WebElement CreditPeriod = GWait.Wait_GetElementByCSS("input[formControlName=CreditPeriod]");
						CreditPeriod.sendKeys(CreditPeriod_Data);
						for (WebElement radiobutton : RadioTypeList) {
							System.out.println(radiobutton.getText());
							if (AmountType_Data.equals("Amount")) {
								if (radiobutton.getText().equalsIgnoreCase(AmountType_Data)) {
									radiobutton.click();
									WebElement AmountTOBeRaised = GWait
											.Wait_GetElementByCSS("input[formControlName=Amount]");
									AmountTOBeRaised.sendKeys(AmountTOBeRaised_Data);
								}
 
							}else if(radiobutton.getText().equalsIgnoreCase(AmountType_Data)) {
								radiobutton.click();

								WebElement AmountTOBeRaised = GWait
										.Wait_GetElementByCSS("input[formControlName=Percentage]");
								AmountTOBeRaised.sendKeys(AmountTOBeRaised_Data);
							}
						}
						
						WebElement outclick = GWait.Wait_GetElementByCSS(".mat-drawer-content > div:nth-child(1)");
						outclick.click();
						Thread.sleep(2000);
						WebElement Assign_BTN = GWait.Wait_GetElementByXpath("//button[@type='submit']");
						Assign_BTN.click();
						break;
					}
					
				}
				
			}

		}

	}

}
