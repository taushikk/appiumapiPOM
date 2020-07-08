package com.qa.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

@SuppressWarnings("deprecation")
public class ExtentReportUtility {

	private static ExtentReports extent;
	private static ExtentTest test;
	private static ExtentHtmlReporter htmlReporter;
	private static String filePath = "./Reports/timestampextentreport.html";


	public static ExtentReports GetExtent(){
		if (extent != null)
			return extent; //avoid creating new instance of html file
		extent = new ExtentReports();		
		extent.attachReporter(getHtmlReporter());
		return extent;
	}

	private static ExtentHtmlReporter getHtmlReporter() {

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		htmlReporter = new ExtentHtmlReporter(filePath.replace("timestamp", timeStamp));
		htmlReporter.config().setDocumentTitle("Automation Test Report");
		htmlReporter.config().setReportName("Test Results for POC app");
		htmlReporter.config().setTheme(Theme.DARK);
		return htmlReporter;
	}

	public static ExtentTest createTest(String name, String description){
		test = extent.createTest(name, description);
		return test;
	}

	public static void endReport() {
		if(extent!=null){
			extent.flush();
		}
	}
}
