package com.qa.xyz.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CommonUtil {

	public static void takeScreenShot(WebDriver driver) {
		// 1. convert webdriver ref to TakeScreenshot:
		TakesScreenshot scrShot = (TakesScreenshot) driver;

		// 2. call getScreenshotAs method to create a file:
		File src = scrShot.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File("/Users/NaveenKhunteta/Documents/workspace/"
					+ "AugBatchPOMFW/screenshots/" + "Test" + ".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
