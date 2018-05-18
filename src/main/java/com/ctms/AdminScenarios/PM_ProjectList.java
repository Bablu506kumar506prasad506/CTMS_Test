package com.ctms.AdminScenarios;

import java.io.FileInputStream;
import java.util.List;

import javax.xml.xpath.XPath;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ctms.GlobalMethod.GlobalMethods;
import com.ctms.GlobalMethod.GlobalWait;

import jxl.Sheet;
import jxl.Workbook;

public class PM_ProjectList extends PM_AssignCRA {

	public PM_ProjectList() {
		PageFactory.initElements(GlobalMethods.driver, this);
	}

	GlobalWait GWait = new GlobalWait(GlobalMethods.driver);
	Actions action = new Actions(GlobalMethods.driver);
	
	@FindBy(xpath = "//div[2]/datatable-body-cell[2]/div/a")
	List<WebElement> StudyNameList;

	public void ProjectListMethod() throws Exception {
		GlobalMethods.Admin_Login();

		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/DataFile.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("sitestudy");

		WebElement navig = GWait.Wait_GetElementByCSS(".menu-ham > img:nth-child(1)");
		navig.click();

		/*WebElement MenuNavig = GWait.Wait_GetElementByCSS("li.ng-star-inserted:nth-child(1) > a:nth-child(1)");
		MenuNavig.click();*/
		WebElement ProjectMNGMT_Link = GWait.Wait_GetElementByXpath("//nav/ul/li[6]");
		ProjectMNGMT_Link.click();
		WebElement ProjectList_Link = GWait.Wait_GetElementByXpath("//nav/ul/li[6]/div/ul/li[1]/a");
		ProjectList_Link.click();
		try {
			WebElement outclick = GWait.Wait_GetElementByCSS(".mat-drawer-content > div:nth-child(1)");
			outclick.click();

		} catch (Exception e) {
			e.getMessage();
		}
		WebElement shadow = GWait.Wait_GetElementByXpath("/html/body/app-root/mat-sidenav-container/div[1]");
		shadow.click();
		Thread.sleep(3000);
		int RowValue = r1.getRows();
		System.out.println("Test00 :"+RowValue);
		for (int i = 1; i <= RowValue-1; i++) {
			for (WebElement studyListElement : StudyNameList) {
				String StudyName_Data = r1.getCell(0, i).getContents();
				System.out.println("Test01 :"+studyListElement.getText());
				System.out.println("Test02 :"+StudyName_Data);
				if (studyListElement.getText().equalsIgnoreCase(StudyName_Data)) {
					studyListElement.click();
					
//					AssignCRAMethod();
					
					/*PM_MilestoneSetup mstp = new PM_MilestoneSetup();
					mstp.MilestoneSetupMethod();*/
					
					PM_MonitoringPlan mp = new PM_MonitoringPlan();
					mp.MonitoringPlanMethod();
					
					
				}
			}
		}
		
		
		
	}

}
