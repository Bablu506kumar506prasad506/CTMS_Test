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

public class SiteManagement {

	public SiteManagement() {
		PageFactory.initElements(GlobalMethods.driver, this);
	}

	GlobalWait GWait = new GlobalWait(GlobalMethods.driver);
	Actions action = new Actions(GlobalMethods.driver);

	public void CratSite() throws Exception {
		GlobalMethods.Admin_Login();
		Thread.sleep(1500);
		WebElement navig = GWait.Wait_GetElementByCSS(".menu-ham > img:nth-child(1)");
		navig.click();

		WebElement Sitemngmt = GWait.Wait_GetElementByLinkText("Site Management");
		Sitemngmt.click();
		
		Thread.sleep(1500);
		
		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/CTMS.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("SiteManagement");
		int rowCount = r1.getRows();
		System.out.println(rowCount);
		for (int i = 1; i <= rowCount-1; i++) {

			String SiteName_Data = r1.getCell(0, i).getContents();
			String SiteId_Data = r1.getCell(1, i).getContents();
			String Country_Data = r1.getCell(2, i).getContents();
			String State_Data = r1.getCell(3, i).getContents();
			String CityName_Data = r1.getCell(4, i).getContents();
			String SiteAddress_Data = r1.getCell(5, i).getContents();
			String SiteLocation_Data = r1.getCell(6, i).getContents();
			String ContactNo_Data = r1.getCell(7, i).getContents();
			
			WebElement AddSite_BTN = GWait.Wait_GetElementByXpath("//div/div[1]/div/button");
			AddSite_BTN.click();
			
			//----------Create Site----------//
			WebElement SiteName_Link = GWait.Wait_GetElementByCSS("input[formControlName=SiteName]");
			SiteName_Link.sendKeys(SiteName_Data);
			WebElement SiteId_Link = GWait.Wait_GetElementByCSS("input[formControlName=SiteId]");
			SiteId_Link.sendKeys(SiteId_Data);
			
			WebElement SelectCountry = GWait.Wait_GetElementByXpath("//div/form/div[3]/div/select");
			Select se = new Select(SelectCountry);
			se.selectByVisibleText(Country_Data);
			
			WebElement SelectState = GWait.Wait_GetElementByXpath("//div/form/div[4]/div/select");
			Select se1 = new Select(SelectState);
			se1.selectByVisibleText(State_Data);
			
			WebElement CityName_Link = GWait.Wait_GetElementByCSS("input[formControlName=CityName]");
			CityName_Link.sendKeys(CityName_Data);
			WebElement SiteAddress_Link = GWait.Wait_GetElementByCSS("input[formControlName=SiteAddress]");
			SiteAddress_Link.sendKeys(SiteAddress_Data);
			WebElement SiteLocation_Link = GWait.Wait_GetElementByCSS("input[formControlName=Location]");
			SiteLocation_Link.sendKeys(SiteLocation_Data);
			WebElement ContactNo_Link = GWait.Wait_GetElementByCSS("input[formControlName=ContactNo]");
			ContactNo_Link.sendKeys(ContactNo_Data);
			
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
