package com.ctms.execution;

import java.io.FileInputStream;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;

import com.ctms.AdminScenarios.CreateProject;
import com.ctms.AdminScenarios.RolesManagement;
import com.ctms.AdminScenarios.RolesXActionManagement;
import com.ctms.AdminScenarios.RolesXFeaturesManagement;
import com.ctms.AdminScenarios.SiteManagement;
import com.ctms.AdminScenarios.SitePersonnelManagement;
import com.ctms.AdminScenarios.StudyXSite;
import com.ctms.AdminScenarios.UserManagement;
import com.ctms.AdminScenarios.UserXStudyXRolesManagement;
import com.ctms.GlobalMethod.GlobalMethods;

public class AdminTask_TS_01 {

	@BeforeMethod
	public void beforeMethod() throws Exception, Exception {

		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/DataFile.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("Login");

		String URL = r1.getCell(3, 0).getContents();
		String firefoxBrowser = r1.getCell(1, 2).getContents();
		GlobalMethods.LaunchBrowser(firefoxBrowser, URL);
	}

	@Test(priority = 0)
	public static void AdminTask_Rolesmangmnt() throws Exception {

		RolesManagement rm = new RolesManagement();
		rm.RoleManagmnt();
	}

	@Test(priority = 1)
	public static void AdminTask_UserManagemnt() throws Exception {
		UserManagement UM = new UserManagement();
		UM.UserMngmnt();
	}

	@Test(priority = 2)
	public static void RolesXFeatureMNGMNT() throws Exception {
		RolesXFeaturesManagement RXFM = new RolesXFeaturesManagement();

		RXFM.RoleXFeturMngmnt();
	}

	@Test(priority = 3)
	public static void RoleXActonMANGMNT() throws Exception {
		RolesXActionManagement RXAM = new RolesXActionManagement();
		RXAM.RoleXActionMngmnt();
	}

	@Test(priority = 4)
	public static void CreatePrjct() throws Exception {
		CreateProject CP = new CreateProject();
		CP.CratPrjct();
	}

	@Test(priority = 5)
	public static void UserXStudyXRolesMNG() throws Exception {
		UserXStudyXRolesManagement USRM = new UserXStudyXRolesManagement();
		USRM.UsrXStdyXRlsMNGMNT();
	}

	@Test(priority = 6)
	public static void SiteMNGMT() throws Exception {
		SiteManagement SM = new SiteManagement();
		SM.CratSite();
	}

	@Test(priority = 7)
	public static void SitePersnlMNGMT() throws Exception {
		SitePersonnelManagement SPM = new SitePersonnelManagement();
		SPM.AddSitePersnlMNGMNT();
	}

	@Test(priority = 8)
	public static void SiteXStudyMethod() throws Exception {
		StudyXSite SXS = new StudyXSite();
		SXS.AssingSiteXStudy();
	}

	@AfterMethod
	public static void closedriver() {
		GlobalMethods.driver.close();
	}

}
