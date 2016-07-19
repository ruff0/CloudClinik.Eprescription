package testcasses_dentalcare;
import helper.Helper;

import java.io.IOException;

import junit.framework.Assert;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import prescription.Epres;
import dentalcare.Dentalcare;
import dentalcare.OrderSet;

public class FNFX {

		WebDriver driver = new FirefoxDriver();
		Epres sr = new Epres(driver);
		private boolean acceptNextAlert = true;
		Helper help = new Helper(driver);
		Dentalcare dental = new Dentalcare(driver);

		@Test(priority = 1)
		public void orderSet() throws IOException, InterruptedException {
			sr.Searching("974100232886", "infogistic@1", " 974100301865");
			Helper.maximize(driver);
			Thread.sleep(1000);
			dental.setComplaints("nauzia");
			Thread.sleep(10000);

		}

		@Test(priority = 2)
		public void setDoctorNotes() throws InterruptedException {
			dental.applyTemplates();
			Thread.sleep(1000);
			dental.setNoteTemplate("test");
			Thread.sleep(10000);
			dental.setDoctor("testing notes");
		}

		@Test(priority = 3)
		public void setPrimaryDiease() throws InterruptedException {
			Thread.sleep(10000);
			dental.clickAdd();
			Thread.sleep(1000);
			dental.setPrimaryDisease("E67.3", 0);

		}

		@Test(priority = 4)
		public void setProcedure() throws InterruptedException {
			Thread.sleep(1000);
			dental.setProcedure("GEN02", 0);
			Thread.sleep(10000);
		}


		@Test(priority = 5)
		public void setAdditionalDiagnosis() throws InterruptedException {
			Thread.sleep(1000);
			dental.setAdditional("S97.1", 0);
		}

		@Test(priority = 6)
		public void setTeeth() throws InterruptedException {
			Thread.sleep(1000);
			dental.setTeeth("MX", 0);
		}

		@Test(priority = 7)
		public void selectTeeth() throws InterruptedException {
			Thread.sleep(10000);
			dental.clickToothNumber(0);
			Thread.sleep(10000);
			dental.setSingleTeeth(10);
			Thread.sleep(10000);
			dental.clickToothNumber(0);
			Thread.sleep(10000);
			dental.clickOnFullMouth();
			Thread.sleep(1000);
			dental.save();
			Thread.sleep(1000);
			Assert.assertTrue(dental
					.FMMMessage("No other tooth can be added with FM, MX or MN. Please review tooth numbers in your Prescription."));
		}

		private boolean isElementPresent(By by) {
			try {
				driver.findElement(by);
				return true;
			} catch (NoSuchElementException e) {
				return false;
			}
		}

		private boolean isAlertPresent() {
			try {
				driver.switchTo().alert();
				return true;
			} catch (NoAlertPresentException e) {
				return false;
			}
		}

		private String closeAlertAndGetItsText() {
			try {
				Alert alert = driver.switchTo().alert();
				String alertText = alert.getText();
				if (acceptNextAlert) {
					alert.accept();
				} else {
					alert.dismiss();
				}
				return alertText;
			} finally {
				acceptNextAlert = true;
			}
		}

	}


