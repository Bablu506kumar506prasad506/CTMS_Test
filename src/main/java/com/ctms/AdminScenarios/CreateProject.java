package com.ctms.AdminScenarios;

import java.io.FileInputStream;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.ctms.GlobalMethod.GlobalMethods;
import com.ctms.GlobalMethod.GlobalWait;

import jxl.Sheet;
import jxl.Workbook;

public class CreateProject {

	public CreateProject() {
		PageFactory.initElements(GlobalMethods.driver, this);
	}

	GlobalWait GWait = new GlobalWait(GlobalMethods.driver);
	Actions action = new Actions(GlobalMethods.driver);

	public void CratPrjct() throws Exception {
		GlobalMethods.Admin_Login();

		WebElement navig = GWait.Wait_GetElementByCSS(".menu-ham > img:nth-child(1)");
		navig.click();

		WebElement AdminTaskNavig = GWait.Wait_GetElementByCSS("li.ng-star-inserted:nth-child(1) > a:nth-child(1)");
		AdminTaskNavig.click();

		WebElement Rolemngmt = GWait.Wait_GetElementByXpath("//nav/ul/li[2]");
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

		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/DataFile.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("CreateProject");
		int rowCount = r1.getRows();
		System.out.println(rowCount);
		for (int i = 1; i <= rowCount-1; i++) {

			String StudyName_Data = r1.getCell(0, i).getContents();
			String ProtocolId_Data = r1.getCell(1, i).getContents();
			String SponsorName_Data = r1.getCell(2, i).getContents();
			String SponsorAddress_Data = r1.getCell(3, i).getContents();
			String ProjctServce_Data = r1.getCell(4, i).getContents();
			String PBCurncy_Data = r1.getCell(5, i).getContents();
			String PBCost_Data = r1.getCell(6, i).getContents();
			String ProfnlCurrency_Data = r1.getCell(7, i).getContents();
			String ProfessionalCost_Data = r1.getCell(8, i).getContents();
			String PTCurrency_Data = r1.getCell(9, i).getContents();
			String PassThroughCost_Data = r1.getCell(10, i).getContents();
			String TotalIp_Data = r1.getCell(11, i).getContents();
			String projectmanager_Data = r1.getCell(12, i).getContents();
			Thread.sleep(8000);
			WebElement CretPRJT_BTN = GWait.Wait_GetElementByXpath("//div/div[1]/div/button");
			CretPRJT_BTN.click();
			WebElement StudyName_Link = GWait.Wait_GetElementByCSS("input[formControlName=StudyName]");
			StudyName_Link.sendKeys(StudyName_Data);
			WebElement ProtocolID_Link = GWait.Wait_GetElementByCSS("input[formControlName=ProtocolId]");
			ProtocolID_Link.sendKeys(ProtocolId_Data);
			WebElement SponsorName_Link = GWait.Wait_GetElementByCSS("input[formControlName=SponsorName]");
			SponsorName_Link.sendKeys(SponsorName_Data);
			WebElement SponsorAddress_Link = GWait.Wait_GetElementByCSS("textarea[formControlName=SponsorAddress]");
			SponsorAddress_Link.sendKeys(SponsorAddress_Data);

			List<WebElement> ProjctServce_Link = GlobalMethods.driver
					.findElements(By.xpath("//input[@type='checkbox']"));
			for (WebElement webElement : ProjctServce_Link) {
				System.out.println("Project Seravice Data: " + webElement.getAttribute("value"));
				if (webElement.getAttribute("value").equalsIgnoreCase(ProjctServce_Data)) {
					webElement.click();
					break;
				}
				
			}

			// --------Project Budget-------//
			WebElement SelectPBC = GWait.Wait_GetElementByXpath("//form/div[6]/div/select");
			Select se = new Select(SelectPBC);
			se.selectByVisibleText(PBCurncy_Data);
			WebElement PBCost_Link = GWait.Wait_GetElementByCSS("input[formControlName=ProjectBudget]");
			PBCost_Link.sendKeys(PBCost_Data);
			// -------Professional Cost------//
			WebElement SelectProfessionalCOST = GWait.Wait_GetElementByXpath("//form/div[7]/div/select");
			Select se1 = new Select(SelectProfessionalCOST);
			se1.selectByVisibleText(ProfnlCurrency_Data);
			WebElement ProfessionalCost_Link = GWait.Wait_GetElementByCSS("input[formControlName=ProfessionalCost]");
			ProfessionalCost_Link.sendKeys(ProfessionalCost_Data);
			// -------Pass Through Cost------//
			WebElement PassThroughCost = GWait.Wait_GetElementByXpath("//form/div[8]/div/select");
			Select se11 = new Select(PassThroughCost);
			se11.selectByVisibleText(PTCurrency_Data);
			WebElement PassThroughCost_Link = GWait.Wait_GetElementByCSS("input[formControlName=PassThroughCost]");
			PassThroughCost_Link.sendKeys(PassThroughCost_Data);
			WebElement TotalIp_Link = GWait.Wait_GetElementByCSS("input[formControlName=TotalIp]");
			TotalIp_Link.sendKeys(TotalIp_Data);

			// -------Assign Project Manager--------//
			WebElement projectmanager = GWait.Wait_GetElementByXpath("//div/form/div[10]/div/select");
			Select se111 = new Select(projectmanager);
			se111.selectByVisibleText(projectmanager_Data);
			
			WebElement Submit_BTN = GWait.Wait_GetElementByXpath("//button[@type='submit']");
			Submit_BTN.click();
		}
		
		Thread.sleep(8000);
		WebElement Logout_BTN = GWait.Wait_GetElementByXpath("//nav/div[2]/div[2]/button/span[2]");
		Logout_BTN.click();

		WebElement Logout = GWait.Wait_GetElementByXpath("//nav/div[2]/div[2]/ul/li[3]/a");
		Logout.click();

	}

}
