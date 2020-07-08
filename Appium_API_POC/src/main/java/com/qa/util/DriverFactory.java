package com.qa.util;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class DriverFactory {
	static AndroidDriver<MobileElement> driver;

	public static  AndroidDriver<MobileElement> getDriver(String deviceId, String deviceName, String portNumber) throws MalformedURLException {
		String projectPath = System.getProperty("user.dir");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.PLATFORM_NAME, "ANDROID");
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		cap.setCapability(MobileCapabilityType.UDID, deviceId);
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
		cap.setCapability(MobileCapabilityType.APP, projectPath+"\\src\\test\\resources\\APP\\Fred_Hutch_POC_2.0.0.apk");
		URL url = new URL("http://127.0.0.1:"+portNumber+"/wd/hub");
		driver= new AndroidDriver<MobileElement> (url, cap);
		return driver;	
	}


	public static void closeApp() {
		driver.quit();
	}
}
