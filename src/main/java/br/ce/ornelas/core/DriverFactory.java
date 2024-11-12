package br.ce.ornelas.core;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

	//private static WebDriver driver; -- antigo sem paralelismo
	
	// criado para o parelelismo
	private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>() {
		@Override
		protected synchronized WebDriver initialValue() {
			return initDriver();
		}
	};

	private DriverFactory() {}
	
	/* execução nova para o paralelismo - cada thread precisa abrir seu próprio navegador*/
	public static WebDriver getDriver() {
		return threadDriver.get();
	}
	public static WebDriver initDriver() {
		WebDriver driver = null;
		switch (Properties.browser) {
			case FIREFOX:
				driver = new FirefoxDriver(); break;
			case CHROME:
				driver = new ChromeDriver(); break;
				}

			driver.manage().window().setSize(new Dimension(1200, 765));
			return driver;
		}	
	
	/* execução antiga de apenas 1 driver por vez - sem paralelismo
	public static WebDriver getDriver() {
		if (driver == null) {
			switch (Properties.browser) {
			case FIREFOX:
				driver = new FirefoxDriver(); break;
			case CHROME:
				driver = new ChromeDriver(); break;
			}

			driver.manage().window().setSize(new Dimension(1200, 765));
		}
		return driver;
	}*/

	
	public static void killThreadDriver() {
		WebDriver driver = getDriver();
		if (driver != null) {
			driver.quit();
			driver = null;
		}
		if(threadDriver != null) {
			threadDriver.remove();
		}
		
		/* Antigo sem paralelismo
	public static void killDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}*/
		
		
	}
}
