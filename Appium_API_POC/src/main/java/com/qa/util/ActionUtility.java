package com.qa.util;


import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

public class ActionUtility {
	Actions action;

	public ActionUtility() {
		this.action = new Actions(TestManager.getDriver());

	}

	public void waitForElementClickable(MobileElement element,int time) throws TimeoutException{
		WebDriverWait wait = new WebDriverWait(TestManager.getDriver(), 300);
		wait.ignoring(Exception.class);
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	public boolean verifyIfElementIsDisplayed(MobileElement element, int... time) throws TimeoutException {
		//checks whether element is present in the web page
		boolean flag = false;
		int timeFactor = 5;
		//        driver = dri;
		if (time.length > 0){
			timeFactor = time[0];
		}
		try{
			waitForElementVisible(element,timeFactor);
			int attempt =0;

			while(attempt<3 && !flag){
				try {
					flag = element.isDisplayed();
				}catch (StaleElementReferenceException e){}
				attempt++;
			}


		}catch(NoSuchElementException e){
			//If the element is not present in the DOM then it will come here
			System.out.println("element not present");
		}
		return flag;
	}

	public void waitForElementVisible( MobileElement element, int time) throws TimeoutException{
		// wait till the element is visible
		WebDriverWait wait = new WebDriverWait(TestManager.getDriver(), time,250);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(Exception.class);
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public void scrollDown() {
		Dimension dim = TestManager.getDriver().manage().window().getSize();
		int height = dim.getHeight();
		int width = dim.getWidth();
		int X = width/2;
		int starty = (int) (height*.8);
		int endy = (int) (height*.2);


		@SuppressWarnings("rawtypes")
		TouchAction actions = new TouchAction(TestManager.getDriver());
		actions.press(PointOption.point(X,starty)).moveTo(PointOption.point(X,endy)).release().perform();
	}

}
