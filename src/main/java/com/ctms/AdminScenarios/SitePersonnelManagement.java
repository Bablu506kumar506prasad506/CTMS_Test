package com.ctms.AdminScenarios;

import java.io.FileInputStream;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.ctms.GlobalMethod.GlobalMethods;
import com.ctms.GlobalMethod.GlobalWait;

import jxl.Sheet;
import jxl.Workbook;

public class SitePersonnelManagement {
	
	public SitePersonnelManagement() {
		PageFactory.initElements(GlobalMethods.driver, this);
	}

	GlobalWait GWait = new GlobalWait(GlobalMethods.driver);
	Actions action = new Actions(GlobalMethods.driver);

	public void AddSitePersnlMNGMNT() throws Exception {
		GlobalMethods.Admin_Login();

		WebElement navig = GWait.Wait_GetElementByCSS(".menu-ham > img:nth-child(1)");
		navig.click();

		WebElement AdminTaskNavig = GWait.Wait_GetElementByCSS("li.ng-star-inserted:nth-child(1) > a:nth-child(1)");
		AdminTaskNavig.click();

		WebElement SitPrsnlmngmt = GWait.Wait_GetElementByXpath("//nav/ul/li[4]");
		SitPrsnlmngmt.click();
		
		Thread.sleep(1500);
		
		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/DataFile.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("SitePersonnlManagement1");
		int rowCount = r1.getRows();
		System.out.println(rowCount);
		for (int i = 1; i <= rowCount-1; i++) {

			String StudyName_Data = r1.getCell(0, i).getContents();
			String SiteName_Data = r1.getCell(1, i).getContents();
			String Salutation_Data = r1.getCell(2, i).getContents();
			String Name_Data = r1.getCell(3, i).getContents();
			String Role_Data = r1.getCell(4, i).getContents();
			String OrganizationName_Data = r1.getCell(5, i).getContents();
			String EmailId_Data = r1.getCell(6, i).getContents();
			String MobileNo_Data = r1.getCell(7, i).getContents();
			String Country_Data = r1.getCell(8, i).getContents();
																	
			WebElement AddSite_BTN = GWait.Wait_GetElementByXpath("//div/div[1]/div/button");
			AddSite_BTN.click();
			
			//----------Create Site Personnel----------//
			WebElement SelectStudy = GWait.Wait_GetElementByXpath("//div/form/div[1]/div/select");
			Select se = new Select(SelectStudy);
			se.selectByVisibleText(StudyName_Data);
			
			WebElement SelectSite = GWait.Wait_GetElementByXpath("//div/form/div[2]/div/select");
			Select se1 = new Select(SelectSite);
			se1.selectByVisibleText(SiteName_Data);
			
			WebElement SelectSalutation = GWait.Wait_GetElementByXpath("//div/form/div[3]/div/select");
			Select se2 = new Select(SelectSalutation);
			se2.selectByVisibleText(Salutation_Data);
			
			WebElement Name_Link = GWait.Wait_GetElementByCSS("input[formControlName=Name]");
			Name_Link.sendKeys(Name_Data);
			
			WebElement SelectRole = GWait.Wait_GetElementByXpath("//div/form/div[5]/div/select");
			Select se3 = new Select(SelectRole);
			se3.selectByVisibleText(Role_Data);
			
			WebElement OrganizationName_Link = GWait.Wait_GetElementByCSS("input[formControlName=OrganizationName]");
			OrganizationName_Link.sendKeys(OrganizationName_Data);
			
			WebElement EmailId_Link = GWait.Wait_GetElementByCSS("input[formControlName=EmailId]");
			EmailId_Link.sendKeys(EmailId_Data);
			
			WebElement MobileNo_Link = GWait.Wait_GetElementByCSS("input[formControlName=MobileNo]");
			MobileNo_Link.sendKeys(MobileNo_Data);
			
			WebElement SelectCountry = GWait.Wait_GetElementByXpath("//div/form/div[9]/div/select");
			Select se4 = new Select(SelectCountry);
			se4.selectByVisibleText(Country_Data);
			Thread.sleep(1500);
			WebElement Submit_BTN = GWait.Wait_GetElementByXpath("//button[@type='submit']");
			Submit_BTN.click();
			Thread.sleep(8000);
			
		}
		
		Thread.sleep(8000);
		WebElement Logout_BTN = GWait.Wait_GetElementByXpath("//nav/div[2]/div[2]/button/span[2]");
		Logout_BTN.click();

		WebElement Logout = GWait.Wait_GetElementByXpath("//nav/div[2]/div[2]/ul/li[3]/a");
		Logout.click();
	}

}
