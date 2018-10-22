package com.ctms.execution;

import java.io.FileInputStream;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ctms.GlobalMethod.GlobalMethods;
import com.ctms.SuperadminScr.SuperadminClass;

import jxl.Sheet;
import jxl.Workbook;

public class SuperadminExecutionClass {

	@BeforeMethod
	public void beforeMethod() throws Exception, Exception {

		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/CTMS.xls");
		Workbook wb = Workbook.getWorkbook(fi);
		Sheet r1 = wb.getSheet("LoginDetails");

		String URL = r1.getCell(0, 1).getContents();
		String firefoxBrowser = r1.getCell(1, 2).getContents();
		GlobalMethods.LaunchBrowser(firefoxBrowser, URL);
	}

	@Test(priority = 0)
	public static void AdminTask_Rolesmangmnt() throws Exception {

		SuperadminClass sc = new SuperadminClass();
		sc.SuperadminM();
	}

	/*@AfterMethod
	public void close() {
		GlobalMethods.driver.close();
	}*/

}
