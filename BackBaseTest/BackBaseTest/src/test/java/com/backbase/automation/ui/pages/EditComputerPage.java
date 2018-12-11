package com.backbase.automation.ui.pages;

import static com.backbase.automation.ui.util.TestData.*;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.backbase.automation.testcases.BackBaseCRUDTest;
import com.backbase.automation.ui.basetest.TestBase;


public class EditComputerPage extends TestBase {

	@FindBy(xpath = "//section[@id='main']/h1")
	WebElement editPageHeader; 
	
	@FindBy(id="name")
	WebElement computerTextBox;
	
	@FindBy(id="introduced")
	WebElement introducedTextBox;
	
	@FindBy(id="discontinued")
	WebElement discontinuedTextBox;
	
	@FindBy(id = "company")
	WebElement companyDropDown;
	
	@FindBy(css = "input.btn.primary")
	WebElement saveComputerButton;
	
	@FindBy(css = "a.btn")
	WebElement cancelButton;
	
	@FindBy(css = "input.btn.danger")
	WebElement deleteComputerButton;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BackBaseCRUDTest.class);
	
	// Initializing the Page Objects:
	public EditComputerPage() {
		LOGGER.info("Loading all webelements... ");
		PageFactory.initElements(driver, this);
	}

	// Check page header
	public void checkIfEditPageHeaderIsVisible(){
		LOGGER.info("Checking edit page header... ");
		Assert.assertTrue(editPageHeader.isDisplayed()); 
		Assert.assertEquals(editPageHeader.getText(), EditPage_Header);
	}
	
	// Check availability of all input elements and button on edit page
	public void checkAvailabilityOfAllInputElementsAndButtons(){
		LOGGER.info("Checking availability of edit page input elements and buttons... ");
		Assert.assertTrue(computerTextBox.isEnabled()); 
		Assert.assertTrue(introducedTextBox.isEnabled());
		Assert.assertTrue(discontinuedTextBox.isEnabled()); 
		Assert.assertTrue(companyDropDown.isEnabled());
		Assert.assertTrue(saveComputerButton.isEnabled());
		Assert.assertTrue(cancelButton.isEnabled());
		Assert.assertTrue(deleteComputerButton.isEnabled());
	}
	
	public void enterComputerName(String computerName) throws InterruptedException {
		checkAvailabilityOfAllInputElementsAndButtons();
		computerTextBox.sendKeys(computerName);
		Thread.sleep(2000);
		Assert.assertEquals(computerTextBox.getAttribute("value"), computerName);  
	}
	
	public void enterIntroducedDate(String introducedDate) throws InterruptedException {
		checkAvailabilityOfAllInputElementsAndButtons();
		introducedTextBox.sendKeys(introducedDate);
		Thread.sleep(2000);
		Assert.assertEquals(computerTextBox.getAttribute("value"), introducedDate);  
	}
	
	public void enterDiscontinuedDate(String discontinuedDate) throws InterruptedException {
		checkAvailabilityOfAllInputElementsAndButtons();
		discontinuedTextBox.sendKeys(discontinuedDate);
		Thread.sleep(2000);
		Assert.assertEquals(discontinuedTextBox.getAttribute("value"), discontinuedDate);  
	}
	
	public void selectCompanyName(String companyName) throws InterruptedException {
		checkAvailabilityOfAllInputElementsAndButtons();
		Select companyList = new Select (companyDropDown);
		companyList.selectByVisibleText(companyName); 
		Thread.sleep(2000);
		Assert.assertEquals(companyDropDown.getAttribute("value"), companyName);  
	}
	
	// Save updated computer data
	public void updateComputerToDatabase(String computerName, String introducedDate, String discontinuedDate, 
			String companyName ) throws InterruptedException { 
		enterComputerName(computerName);
		enterIntroducedDate(introducedDate);
		enterDiscontinuedDate(discontinuedDate);
		selectCompanyName(companyName);
		LOGGER.info("Saving updated computer data... ");
		saveComputerButton.click();
		Thread.sleep(2000); 
	}
	
	// Cancel updated computer data
	public void cancelUpdateComputerToDatabase(String computerName, String introducedDate, String discontinuedDate, 
			String companyName ) throws InterruptedException { 
		enterComputerName(computerName);
		enterIntroducedDate(introducedDate);
		enterDiscontinuedDate(discontinuedDate);
		selectCompanyName(companyName);
		LOGGER.info("Cancelling updated computer data... ");
		cancelButton.click();
		Thread.sleep(2000); 
	}
	
	// Delete computer from database
	public void deleteComputerFromDatabase() throws InterruptedException { 
		checkAvailabilityOfAllInputElementsAndButtons();
		LOGGER.info("Deleting computer data... ");
		deleteComputerButton.click();
		Thread.sleep(2000); 
	}
}