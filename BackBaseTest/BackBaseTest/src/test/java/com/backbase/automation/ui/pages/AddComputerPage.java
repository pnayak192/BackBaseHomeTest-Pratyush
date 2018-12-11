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


public class AddComputerPage extends TestBase {

	@FindBy(xpath = "//section[@id='main']/h1")
	WebElement addPageHeader; 

	@FindBy(css = "div.error")
	WebElement errorHighlight;

	@FindBy(id="name")
	WebElement computerTextBox;

	@FindBy(id="introduced")
	WebElement introducedTextBox;

	@FindBy(id="discontinued")
	WebElement discontinuedTextBox;

	@FindBy(id = "company")
	WebElement companyDropDown;

	@FindBy(css = "input.btn.primary")
	WebElement createComputerButton;

	@FindBy(css = "a.btn")
	WebElement cancelButton; 

	private static final Logger LOGGER = LoggerFactory.getLogger(BackBaseCRUDTest.class);

	// Initializing the Page Objects:
	public AddComputerPage() {
		LOGGER.info("Loading all webelements... "); 
		PageFactory.initElements(driver, this);
	}

	// Checking page header:
	public void checkIfAddPageHeaderIsVisible(){
		LOGGER.info("Checking page header... "); 
		Assert.assertTrue(addPageHeader.isDisplayed()); 
		Assert.assertEquals(addPageHeader.getText(), AddPage_Header);
	}

	// Checking availability of all elements and buttons in add computer page:
	public void checkAvailabilityOfAllInputElementsAndButtons(){
		LOGGER.info("Checking all input elements and buttons... "); 
		Assert.assertTrue(computerTextBox.isEnabled()); 
		Assert.assertTrue(introducedTextBox.isEnabled());
		Assert.assertTrue(discontinuedTextBox.isEnabled()); 
		Assert.assertTrue(companyDropDown.isEnabled());
		Assert.assertTrue(createComputerButton.isEnabled());
		Assert.assertTrue(cancelButton.isEnabled());

	}

	// Enter name into computer text box
	public void enterComputerName(String computerName) throws InterruptedException {
		checkAvailabilityOfAllInputElementsAndButtons();
		computerTextBox.clear();
		if(computerTextBox != null) {
			LOGGER.info("Entering computer name in computer text box... "); 
			computerTextBox.sendKeys(computerName);
			Assert.assertEquals(computerTextBox.getAttribute("value"), computerName);  
		}
	}

	// Clear name from computer text box
	public void clearComputerName() throws InterruptedException {
		checkAvailabilityOfAllInputElementsAndButtons();
		LOGGER.info("Clearing computer name in computer text box... "); 
		computerTextBox.clear();
		Assert.assertEquals(computerTextBox.getAttribute("value"), "");  
	}

	// Enter date into introduced date text box
	public void enterIntroducedDate(String introducedDate) throws InterruptedException {
		checkAvailabilityOfAllInputElementsAndButtons();
		introducedTextBox.clear();
		if(introducedDate != null) {
			LOGGER.info("Entering date into introduced date text box... "); 
			introducedTextBox.sendKeys(introducedDate);
			Assert.assertEquals(introducedTextBox.getAttribute("value"), introducedDate);  
		}
	}

	// Enter date into discontinued date text box
	public void enterDiscontinuedDate(String discontinuedDate) throws InterruptedException {
		checkAvailabilityOfAllInputElementsAndButtons();
		discontinuedTextBox.clear();
		if(discontinuedDate != null) {
			LOGGER.info("Entering date into introduced date text box... "); 
			discontinuedTextBox.sendKeys(discontinuedDate);
			Assert.assertEquals(discontinuedTextBox.getAttribute("value"), discontinuedDate);  
		}
	}

	// Select company name
	public void selectCompanyName(String companyName) throws InterruptedException {
		checkAvailabilityOfAllInputElementsAndButtons();
		if(companyName != null){
			LOGGER.info("Selecting name from company drop down... "); 
			Select companyList = new Select (companyDropDown);
			companyList.selectByVisibleText(companyName); 
			Assert.assertEquals(companyList.getFirstSelectedOption().getText(), companyName);  
		}
	}

	// Add computer to database
	public void addComputerToDatabase(String computerName, String introducedDate, String discontinuedDate, 
			String companyName ) throws InterruptedException { 
		enterComputerName(computerName);
		enterIntroducedDate(introducedDate);
		enterDiscontinuedDate(discontinuedDate);
		selectCompanyName(companyName);
		LOGGER.info("Creating new computer to database... "); 
		createComputerButton.click();
		Thread.sleep(2000); 
	}

	// Cancel addition of computer to database
	public void cancelAddingComputerToDatabase(String computerName, String introducedDate, String discontinuedDate, 
			String companyName ) throws InterruptedException { 
		enterComputerName(computerName);
		enterIntroducedDate(introducedDate);
		enterDiscontinuedDate(discontinuedDate);
		selectCompanyName(companyName);
		LOGGER.info("Canceling computer update to database... "); 
		cancelButton.click();
		Thread.sleep(2000); 
	}

	// Add computer without computer name to database.
	public void addComputerWithoutComputerNameToDatabase(String introducedDate, String discontinuedDate, 
			String companyName) throws InterruptedException{ 
		enterIntroducedDate(introducedDate);
		enterDiscontinuedDate(discontinuedDate);
		selectCompanyName(companyName);
		LOGGER.info("Trying to create computer without computer name to database... "); 
		createComputerButton.click();
		Assert.assertEquals(errorHighlight.getCssValue("background-color"), errorHiglight_Color);
		cancelButton.click();
		Thread.sleep(2000);
	}
}