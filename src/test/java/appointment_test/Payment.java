package appointment_test;

import helper.Helper;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import appointment.Appoints;
import appointment.CalenderData;
import appointment.Data;
import appointment.EditAppointment;
import appointment.PaymentReciept;
import appointment.RegularInvocing;
import appointment.SearchPatient;
import framework.Login;
import framework.ReadExcel;

public class Payment {
	WebDriver driver = new FirefoxDriver();
	private boolean acceptNextAlert = true;
	Helper help = new Helper(driver);
	Appoints app = new Appoints(driver);
	Data[] data = new Data[7];
	CalenderData[] dt = new CalenderData[5];
	SearchPatient st = new SearchPatient(driver);
	ArrayList<WebElement> ele = new ArrayList<WebElement>();
	RegularInvocing regular =new RegularInvocing(driver);
	PaymentReciept pay=new PaymentReciept(driver);
	EditAppointment edit=new EditAppointment(driver);
	
  @Test(priority=1)
  public void login() throws IOException, InterruptedException {
	  Logger logger = Logger.getLogger(appointment_test.Urgent.class);
		Login log = new Login(driver);
		Thread.sleep(1000);
		log.Logincredentials("974100232895", "infogistic@1");
		Thread.sleep(10000);
		help.maximize(driver);
		Thread.sleep(10000);
  }
  
  @Test(priority=2)
  public void checkAppointment() throws InterruptedException{
	    app.clickDisabledBookApp(4,10);
		Thread.sleep(10000);
		app.clickOnPayment(0);
		Thread.sleep(1000);
		regular.clickPaymentMode();
		Thread.sleep(1000);
		regular.selectPaymentValue("Cheque");
		Thread.sleep(1000);
		regular.insertDiscount("20");
		Thread.sleep(10000);
		regular.clickOnBalanceAmount();
		Thread.sleep(10000);
		Assert.assertTrue(regular.verifyNetAmount("500.0"));
		Thread.sleep(1000);
		Assert.assertTrue(regular.verifyMrNumber("974100301863"));
		Assert.assertTrue(regular.verifyRecievedAmount(480.0));
		Assert.assertTrue(regular.verifyName("Mr. test  user"));
		regular.clickSave();
		Thread.sleep(6000);
		pay.closePaymentReciept();
		
	}
  
  @Test(priority=3)
  public void editAppointment() throws InterruptedException{
	  app.clickOnEditAppointment(4);
	  Thread.sleep(1000);
	  edit.insertPurposeOfVisit("fever");
	  Thread.sleep(1000);
	  edit.clickProvider();
	  Thread.sleep(10000);
	  edit.selectProvider("Nurse Doctor");
	  Thread.sleep(1000);
	  edit.insertDuration("00:55");
	  Thread.sleep(1000);
	  edit.save();
	  Thread.sleep(1000);
	  edit.close();
	  Thread.sleep(3000);
	 Assert.assertTrue(app.getTime("11:00 AM - 11:55 AM",4));
	  
  }
  
  
   @Test(priority=4)
   public void register() throws InterruptedException, IOException{
	   app.bookAppointmentMenu(0,0);
	   Thread.sleep(7000);
	   st.clickClosePatient();
	   Thread.sleep(10000);
	   driver.navigate().refresh();
	   Thread.sleep(10000);
	   app.bookAppointmentMenu(0,0);
	   Thread.sleep(1000);
	   app.registerPatient();
	   ReadExcel excel=new ReadExcel(driver);
       excel.ReadData(2);
       Thread.sleep(10000);
       excel.callForRegister(0);
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
