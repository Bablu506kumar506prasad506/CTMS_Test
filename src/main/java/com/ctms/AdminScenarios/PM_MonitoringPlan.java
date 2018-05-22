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

public class PM_MonitoringPlan {

	public PM_MonitoringPlan() {
		PageFactory.initElements(GlobalMethods.driver, this);
	}

	GlobalWait GWait = new GlobalWait(GlobalMethods.driver);
	Actions action = new Actions(GlobalMethods.driver);

	@FindBy(xpath = "//app-monitoring-plan/div/div/div/div/div/select")
	List<WebElement> SiteNameList;

	@FindBy(xpath = "//div[1]/div[2]/div/div[1]/select")
	List<WebElement> MonthList;
	
	

	@FindBy(css = ".col>div")
	List<WebElement> DateList;

	public void MonitoringPlanMethod() throws Exception {

		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/DataFile.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("MonitoringPlan");

		WebElement navig = GWait.Wait_GetElementByCSS(".menu-ham > img:nth-child(1)");
		navig.click();

		WebElement ProjectMNGMT_Link = GWait.Wait_GetElementByXpath("//nav/ul/li[6]");
		ProjectMNGMT_Link.click();
		WebElement MonitoringPlanList_Link = GWait.Wait_GetElementByXpath("//nav/ul/li[6]/div/ul/li[4]/a");
		MonitoringPlanList_Link.click();

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
		for (int i = 1; i <= rowval-1; i++) {
			String SiteName_Data = r1.getCell(0, i).getContents();
			String[] SiteList = SiteName_Data.split(",");

			for (String site : SiteList) {
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

							String Month_Data = r1.getCell(1, i).getContents();

							for (WebElement months : MonthList) {

								Select se1 = new Select(months);
								List<WebElement> month = se1.getOptions();
								for (WebElement MonthElement : month) {
									System.out.println("Testmonth: " + MonthElement.getText());
									if (MonthElement.getText().equalsIgnoreCase(Month_Data)) {
										MonthElement.click();
										
										String Year_Data = r1.getCell(2, i).getContents();
										
										Select drpYear = new Select(GlobalMethods.driver.findElement(By.xpath("//div[1]/div[2]/div/div[2]/select")));
										drpYear.selectByValue(Year_Data);
										
										String Date_Data = r1.getCell(3, i).getContents();
										String[] Date = Date_Data.split("/");
										System.out.println("Test00: " + Date[1]);
										for (WebElement dateElement : DateList) {

											String ele1 = dateElement.getText();
											if (ele1 != null && !ele1.isEmpty()) {
												String ele2 = ele1.substring(0, ele1.length() - 1);
												System.out.println("Test01: " + ele2);

												System.out.println("Test@1: " + dateElement.getText());
												System.out.println(
														"Test@2: " + ele2.trim().equalsIgnoreCase(Date[1].trim()));
												if (ele2.trim().equalsIgnoreCase(Date[1].trim())) {

													dateElement.findElement(By.xpath(".//span")).click();
													
													String VisitName_Data = r1.getCell(4, i).getContents();
													String FromDate_Data = r1.getCell(5, i).getContents();
													String ToDate_Data = r1.getCell(6, i).getContents();
													String VisitType_Data = r1.getCell(7, i).getContents();
													String AssignCRA_Data = r1.getCell(8, i).getContents();
													
													WebElement VisitName = GWait.Wait_GetElementByCSS("input[formControlName=Name]");
													VisitName.sendKeys(VisitName_Data);
													WebElement FromDate = GWait.Wait_GetElementByCSS("input[formControlName=FromDate]");
													FromDate.clear();
													FromDate.sendKeys(FromDate_Data);
													WebElement ToDate = GWait.Wait_GetElementByCSS("input[formControlName=ToDate]");
													ToDate.sendKeys(ToDate_Data);
													
													Select drpDwnVisitType = new Select(GlobalMethods.driver.findElement(By.xpath("//form/div[4]/div/select")));
													drpDwnVisitType.selectByVisibleText(VisitType_Data);
													Select drpDwnAssignCRA = new Select(GlobalMethods.driver.findElement(By.xpath("//form/div[5]/div/select")));
													drpDwnAssignCRA.selectByVisibleText(AssignCRA_Data);
													
													Thread.sleep(2000);
													WebElement Assign_BTN = GWait.Wait_GetElementByXpath("//button[@type='submit']");
													Assign_BTN.click();
													break;
												}
											}

										}
										break;
									}
								}

							}
							
						}
					}
				}
			}
		}
	}

}
