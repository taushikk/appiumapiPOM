package com.qa.custom_listener;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.MalformedURLException;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.internal.ConstructorOrMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qa.util.*;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class TestExecution implements IInvokedMethodListener{
	static ExtentReports extent;
	static ExtentTest test;
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target ({ElementType.TYPE})
	public @interface DisableListener {
	}

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		AndroidDriver<MobileElement> driver;
		extent = ExtentReportUtility.GetExtent();
		test = ExtentReportUtility.createTest(method.getTestMethod().getMethodName(),method.getTestMethod().getMethodName());
		test.log(Status.INFO, "Started executing "+ method.getTestMethod().getMethodName());
		System.out.println("Started executing "+ method.getTestMethod().getMethodName());
		if(TestManager.getReportLogger()== null){
			TestManager.setReportLogger(test);
		}
		ConstructorOrMethod consOrMethod =
				method.getTestMethod().getConstructorOrMethod();
	        DisableListener disable = consOrMethod.getMethod().getDeclaringClass().getAnnotation(DisableListener.class);
	        if (disable != null) {
	            return;
	        }
		String deviceId = method.getTestMethod().getXmlTest().getLocalParameters().get("deviceId");
		String deviceName = method.getTestMethod().getXmlTest().getLocalParameters().get("deviceName");
		String portNumber = method.getTestMethod().getXmlTest().getLocalParameters().get("portNumber");
		try {
			driver = DriverFactory.getDriver(deviceId,deviceName,portNumber);
			if (driver!=null) {
				TestManager.setAndroidDriver(driver);
			}
			test.assignCategory(deviceName);
			System.out.println("Method Name is  "+ method.getTestMethod().getMethodName());

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		try {
			test.log(Status.INFO, "Sucessfully executed "+ method.getTestMethod().getMethodName());
			ConstructorOrMethod consOrMethod =
					method.getTestMethod().getConstructorOrMethod();
		        DisableListener disable = consOrMethod.getMethod().getDeclaringClass().getAnnotation(DisableListener.class);
		        if (disable != null) {
		            return;
		        }
			TestManager.tearDown();
			DriverFactory.closeApp();
			ExtentReportUtility.endReport();
			System.out.println("Sucessfully executed-  "+ method.getTestMethod().getMethodName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
