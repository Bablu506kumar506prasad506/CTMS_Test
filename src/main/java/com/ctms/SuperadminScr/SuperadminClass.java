package com.ctms.SuperadminScr;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.ctms.GlobalMethod.GlobalMethods;
import com.ctms.GlobalMethod.GlobalWait;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import jxl.Sheet;
import jxl.Workbook;

public class SuperadminClass {
	
	public SuperadminClass(){
		PageFactory.initElements(GlobalMethods.driver, this);
	}
	
	GlobalWait GWait = new GlobalWait(GlobalMethods.driver);
	Actions action = new Actions(GlobalMethods.driver);
	JavascriptExecutor js;
	
	public void SuperadminM() throws Exception {
		
		GlobalMethods.Superadmin_Login();
		Thread.sleep(1500);
		WebElement RegisterCOMP = GWait.Wait_GetElementByXpath("//div/div/div/button");
		RegisterCOMP.click();
		
		File fs = new File(System.getProperty("user.dir") + "/src/main/resources/CTMS.xls");
		
		FileInputStream fins = new FileInputStream(fs);
		
		/*HSSFWorkbook wb1 = new HSSFWorkbook(fins);
		HSSFSheet st1 = wb1.getSheet("SuperadminRegsterComp");*/
		
		Workbook wb = Workbook.getWorkbook(fins);
		Sheet st = wb.getSheet("SuperadminRegsterComp");
		
		int Rowcount = st.getRows();
		System.out.println("No. of rowa: "+ Rowcount);
		for (int i = 1; i <= Rowcount; i++) {
			
			List<String> PageControlId = new ArrayList<String>();
			List<WebElement> elements = GlobalMethods.driver.findElements(By.xpath("//*[@class='col-md-8']//*"));
			for (WebElement item : elements) {/*

				String controlType = item.getAttribute("type");
				String controlSelect = item.getTagName();
				if (controlType == null || controlType.trim() == ""|| controlSelect == null || controlSelect.trim()== "") {
					continue;
				}
				String controlName = item.getAttribute("name");
//				String controlformcntrlName = item.getAttribute("formcontrolname");

				if (controlType.equalsIgnoreCase("text") || controlType.equalsIgnoreCase("radio")
						|| controlSelect.equalsIgnoreCase("select") || controlType.equalsIgnoreCase("checkbox")) {
					System.out.println(controlName);
					// System.out.println(controlType);
					if (controlType.equalsIgnoreCase("radio")) {
						WebElement parent = item.findElement(By.xpath("../../../.."));
						String controlIdAndType = parent.getAttribute("id") + "~" + controlType;
						boolean controlExists = false;
						for (String existingControlId : PageControlId) {
							if (existingControlId.equalsIgnoreCase(controlIdAndType)) {
								controlExists = true;
							}
						}
						if (!controlExists) {

							PageControlId.add(controlIdAndType);
						}
					} else if (controlType.equalsIgnoreCase("checkbox")) {
						WebElement parentcheckbox = item.findElement(By.xpath("../../../../.."));
						String controlIdAndTypecheck = parentcheckbox.getAttribute("id") + "~" + controlType;
						boolean controlExistscheck = false;
						for (String existingControlIdcheck : PageControlId) {

							if (existingControlIdcheck.equalsIgnoreCase(controlIdAndTypecheck)) {
								controlExistscheck = true;
							}
						}
						if (!controlExistscheck) {

							PageControlId.add(controlIdAndTypecheck);
						}
					} else {
						PageControlId.add(controlName + "~" + controlType);
						
					}
				}
			*/}
			
//			int ColCount = st1.getRow(i).getLastCellNum();
			int ColCount = st.getColumns();
			for (int j = 0; j <= ColCount; j++) {/*
				
				try {

					
					int controlIndex = j;
					String controlData = st.getCell(j, i).getContents();
					String controlIdAndType = PageControlId.get(controlIndex);
					String controlId = controlIdAndType.split("~")[0];
					String controlType = controlIdAndType.split("~")[1];
					WebElement element = GWait.Wait_GetElementByName(controlId);
					System.out.println("Data "+controlData);
					
					switch (controlType) {
					case "text":
						try {
							
								element.sendKeys(controlData);
								
						} catch (Exception e) {
							e.getMessage();
						}

						break;
					case "radio":
						try {
							List<WebElement> RadioButtonList = element.findElements(By.xpath(".//*"));
							for (int radio = 0; radio < RadioButtonList.size(); radio++) {
								if (RadioButtonList.get(radio).getAttribute("type") != null
										&& RadioButtonList.get(radio).getAttribute("type").equalsIgnoreCase("radio")) {
									System.out.println(
											"Radio value: " + RadioButtonList.get(radio).getAttribute("value"));
									if (RadioButtonList.get(radio).getAttribute("value").trim()
											.equalsIgnoreCase(controlData)) {
										System.out.println("Excel Data " +controlData);
										RadioButtonList.get(radio).click();
									}
								}
							}

						} catch (Exception e) {
							e.getMessage();
						}
						break;
					case "checkbox":
						try {
							List<WebElement> CheckBoxsList = element
									.findElements(By.xpath("//input[@type='checkbox']"));
							System.out.println(CheckBoxsList);
							List<String> list = Lists.newArrayList(Splitter.on(" , ").trimResults().split(controlData));
							System.out.println(list);
							for (int checkBox = 0; checkBox < CheckBoxsList.size(); checkBox++) {
								System.out.println(CheckBoxsList.size());
								System.out.println(CheckBoxsList.get(checkBox).getAttribute("type"));
								System.out.println(
										CheckBoxsList.get(checkBox).getAttribute("type").equalsIgnoreCase("checkbox"));
								if (CheckBoxsList.get(checkBox).getAttribute("type").equalsIgnoreCase("checkbox")) {
									System.out.println("Iam in");
									String xpathForInput = generateXPATH(CheckBoxsList.get(checkBox), "")
											.replaceAll("input", "label");
									System.out.println(xpathForInput);
									WebElement labelElement = GWait.Wait_GetElementByXpath(xpathForInput);
									System.out.println(labelElement);
									System.out.println(labelElement.getText());
									if (list.contains(labelElement.getText())) {
										System.out.println("Excel Data " +controlData);
										CheckBoxsList.get(checkBox).click();
									}
								}
							}
						} catch (Exception e) {
							e.getMessage();
						}
						break;
					case "select-one":
						try {
							System.out.println("DropDown: "+element.getAttribute("type"));
							if (element.getAttribute("type").equalsIgnoreCase("select-one")) {
								Thread.sleep(500);
								Select dropdown = new Select(element);
								System.out.println("Dropdown data: "+controlData.toLowerCase());
								System.out.println("Excel Data " +controlData);
								Thread.sleep(500);
								dropdown.selectByVisibleText(controlData);
							} else {
								String checkOtherTextBox = element.getAttribute("style");
								if (!checkOtherTextBox.equalsIgnoreCase("display: none;")) {
									element.sendKeys(controlData);
								}
							}

						} catch (Exception e) {
							e.getStackTrace();
						}
						break;
					}
					
				
				} catch (Exception e) {
					e.getStackTrace();
				}
			*/}
			Thread.sleep(500);
			/*WebElement TypSub = GWait.Wait_GetElementByXpath("//button[@type='submit']");
			GlobalMethods.scrollToElement(TypSub);
			Thread.sleep(1000);
			TypSub.click();*/
			
			Thread.sleep(4000);
			GlobalMethods.CompanyCreationMailFunctionality();
		}
	}
	
	private static String generateXPATH(WebElement childElement, String current) {
		String childTag = childElement.getTagName();
		if (childTag.equals("html")) {
			return "/html[1]" + current;
		}
		WebElement parentElement = childElement.findElement(By.xpath(".."));
		List<WebElement> childrenElements = parentElement.findElements(By.xpath("*"));
		int count = 0;
		for (int i = 0; i < childrenElements.size(); i++) {
			WebElement childrenElement = childrenElements.get(i);
			String childrenElementTag = childrenElement.getTagName();
			if (childTag.equals(childrenElementTag)) {
				count++;
			}
			if (childElement.equals(childrenElement)) {
				return generateXPATH(parentElement, "/" + childTag + "[" + count + "]" + current);
			}
		}
		return null;
	}
	
}
