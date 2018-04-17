package com.ctms.execution;

import java.io.FileInputStream;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;

import com.ctms.AdminScenarios.RolesManagement;
import com.ctms.AdminScenarios.RolesXActionManagement;
import com.ctms.AdminScenarios.RolesXFeaturesManagement;
import com.ctms.AdminScenarios.UserManagement;
import com.ctms.GlobalMethod.GlobalMethods;

public class AdminTask_TS_01 {

	@BeforeMethod
	public void beforeMethod() throws Exception, Exception {

		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/DataFile.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("Login");

		String URL = r1.getCell(2, 0).getContents();
		String firefoxBrowser = r1.getCell(1, 2).getContents();
		GlobalMethods.LaunchBrowser(firefoxBrowser, URL);
	}

	@Test(enabled = true)
	public static void AdminTask_Rolesmangmnt() throws Exception {

		RolesManagement rm = new RolesManagement();
		rm.RoleManagmnt();
	}

	@Test(enabled = true)
	public static void AdminTask_UserManagemnt() throws Exception {
		UserManagement UM = new UserManagement();
		UM.UserMngmnt();
	}

	@Test(enabled = true)
	public static void RolesXFeatureMNGMNT() throws Exception {
		RolesXFeaturesManagement RXFM = new RolesXFeaturesManagement();

		RXFM.RoleXFeturMngmnt();
	}

	@Test(enabled = true)
	public static void RoleXActonMANGMNT() throws Exception {
		RolesXActionManagement RXAM = new RolesXActionManagement();
		RXAM.RoleXActionMngmnt();
	}
}
