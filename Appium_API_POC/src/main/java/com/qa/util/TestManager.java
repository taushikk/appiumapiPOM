package com.qa.util;

import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class TestManager {
	private static final ThreadLocal<AndroidDriver<MobileElement>> AndroidDriver = new ThreadLocal<AndroidDriver<MobileElement>>();
	private static final ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();	
	
	public static AndroidDriver<MobileElement> getDriver() {
		return AndroidDriver.get();
	}

	public static void setAndroidDriver(AndroidDriver<MobileElement> driver) {
		if(TestManager.getDriver()== null) {
			AndroidDriver.set(driver);
		}else {
			System.out.println(TestManager.getDriver().hashCode()+"from TestMANAGER SET");
		};
	}


	public static void tearDown() {
		AndroidDriver.remove();
	}

	public static ExtentTest getReportLogger() {
		return test.get();
	}

	public static void setReportLogger(ExtentTest extentTest) {
		test.set(extentTest);
	}

}
