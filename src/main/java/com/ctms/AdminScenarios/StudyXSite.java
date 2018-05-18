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

public class StudyXSite {

	public StudyXSite() {
		PageFactory.initElements(GlobalMethods.driver, this);
	}

	GlobalWait GWait = new GlobalWait(GlobalMethods.driver);
	Actions action = new Actions(GlobalMethods.driver);

	public void AssingSiteXStudy() throws Exception {
		GlobalMethods.Admin_Login();

		WebElement navig = GWait.Wait_GetElementByCSS(".menu-ham > img:nth-child(1)");
		navig.click();

		WebElement MenuNavig = GWait.Wait_GetElementByCSS("li.ng-star-inserted:nth-child(1) > a:nth-child(1)");
		MenuNavig.click();

		WebElement StudyXSitemngmt = GWait.Wait_GetElementByXpath("//nav/ul/li[5]");
		StudyXSitemngmt.click();
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
		Sheet r1 = wb.getSheet("sitestudy");
		int RowCount = r1.getRows();
		System.out.println(RowCount);
		for (int i = 1; i <= RowCount - 1; i++) {

			String Study_Data = r1.getCell(0, i).getContents();
			String Site_Data = r1.getCell(1, i).getContents();

			WebElement AddSite_BTN = GWait.Wait_GetElementByXpath("//div/div[1]/div/button");
			AddSite_BTN.click();
			Thread.sleep(2000);
			WebElement SelectStudy = GWait.Wait_GetElementByXpath("//form/div[1]/div/select");
			Select se = new Select(SelectStudy);
			se.selectByVisibleText(Study_Data);
			WebElement SelectSite = GWait.Wait_GetElementByXpath("//form/div[2]/div/select");
			Select se1 = new Select(SelectSite);
			se1.selectByVisibleText(Site_Data);

			WebElement Submit_BTN = GWait.Wait_GetElementByXpath("//button[@type='submit']");
			Submit_BTN.click();
			Thread.sleep(4000);

		}
		Thread.sleep(8000);
		WebElement Logout_BTN = GWait.Wait_GetElementByXpath("//nav/div[2]/div[2]/button/span[2]");
		Logout_BTN.click();

		WebElement Logout = GWait.Wait_GetElementByXpath("//nav/div[2]/div[2]/ul/li[3]/a");
		Logout.click();

	}

}
