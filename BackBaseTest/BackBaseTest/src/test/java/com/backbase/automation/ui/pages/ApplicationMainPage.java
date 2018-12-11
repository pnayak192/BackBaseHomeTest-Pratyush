package com.backbase.automation.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.backbase.automation.testcases.BackBaseCRUDTest;
import com.backbase.automation.ui.basetest.TestBase;

import static com.backbase.automation.ui.util.TestData.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ApplicationMainPage extends TestBase {

	@FindBy(xpath = "//a[@href='/']")
	WebElement pageHeader; 
	
	@FindBy(xpath="//section[@id='main']/h1")
	WebElement numberOfComputersFound;
	
	@FindBy(id="searchbox")
	WebElement computerSearchBox;
	
	@FindBy(id="searchsubmit")
	WebElement computerSearchButton;
	
	@FindBy(id = "add")
	WebElement computerAddButton;
	
	@FindBy(xpath = "//section[@id='main']/table/thead/tr/th[1]/a")
	WebElement firstColumnHeader;
	
	@FindBy(xpath = "//section[@id='main']/table/thead/tr/th[2]/a")
	WebElement secondColumnHeader;
	
	@FindBy(xpath = "//section[@id='main']/table/thead/tr/th[3]/a")
	WebElement thirdColumnHeader;
	
	@FindBy(xpath = "//section[@id='main']/table/thead/tr/th[4]/a")
	WebElement fourthColumnHeader;
	
	@FindBy(xpath = "//section[@id='main']/table/thead/tr/th[5]/a")
	WebElement fifthColumnHeader;
	
	@FindBy(xpath = "//section[@id='main']/table/tbody/tr[1]/td[1]")
	WebElement firstComputerName;
	
	@FindBy(xpath = "//section[@id='main']/table/tbody/tr[1]/td[1]/a")
	WebElement firstComputerLink;
	
	@FindBy(xpath = "//section[@id='main']/table/tbody/tr[2]/td[1]")
	WebElement secondComputerName;
	
	@FindBy(xpath = "//section[@id='main']/table/tbody/tr[2]/td[1]/a")
	WebElement secondComputerLink;
	
	@FindBy(xpath = "//section[@id='main']/table/tbody/tr[1]/td[2]")
	WebElement firstIntroducedDate;
	
	@FindBy(xpath = "//section[@id='main']/table/tbody/tr[2]/td[2]")
	WebElement secondIntroducedDate;
	
	@FindBy(xpath = "//section[@id='main']/table/tbody/tr[1]/td[3]")
	WebElement firstDiscontinuedDate;
	
	@FindBy(xpath = "//section[@id='main']/table/tbody/tr[2]/td[3]")
	WebElement secondDiscontinuedDate;
	
	@FindBy(xpath = "//section[@id='main']/table/tbody/tr[1]/td[4]")
	WebElement firstCompanyName;
	
	@FindBy(xpath = "//section[@id='main']/table/tbody/tr[2]/td[4]")
	WebElement secondCompanyName; 
	
	@FindBy(xpath = "//div[@id='pagination']/ul/li[2]/a")
	WebElement paginationText; 
	
	@FindBy(xpath = "//section[@id='main']/div[2]/em")
	WebElement noComputerFound;
	
	@FindBy(xpath = "//section[@id='main']/table/tbody")
	WebElement table;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BackBaseCRUDTest.class);

	
	// Initializing the Page Objects:
	public ApplicationMainPage() {
		LOGGER.info("Loading all webelements... "); 
		PageFactory.initElements(driver, this);
	}

	// Checking application header
	public void checkIfApplicationHeaderIsVisibleAndEnabled(){
		LOGGER.info("Checking application header... ");
		Assert.assertTrue(pageHeader.isDisplayed()); 
		Assert.assertEquals(pageHeader.getText(), Application_Header);
	}
	
	// Fetching computer count from main page
	public int checkAndGetComputerCountDisplayed() {
		Assert.assertTrue(numberOfComputersFound.isDisplayed()); 
		LOGGER.info("Fetching computer count... ");
		String computerText = numberOfComputersFound.getText();
		int computerCount = Integer.parseInt(computerText.substring(0, computerText.indexOf(" ")));
		return computerCount;
	}
	
	// Checking availability of all button in main page
	public void checkAvailabilityOfAllButtons(){
		LOGGER.info("Checking availability of all button in main page... ");
		Assert.assertTrue(computerSearchButton.isDisplayed()); 
		Assert.assertTrue(computerSearchButton.isEnabled());
		Assert.assertTrue(computerAddButton.isDisplayed()); 
		Assert.assertTrue(computerAddButton.isEnabled());
		
	}
	
	// Checking availability of search text box in main page
	public void checkAvailabilityOfSearchTextBox(){
		LOGGER.info("Checking availability of search text box in main page... ");
		Assert.assertTrue(computerSearchBox.isEnabled());
	}
	
	// Checking availability of table elements.
	public void checkTableElements() {
		LOGGER.info("Checking availability of table elements... ");
		Assert.assertTrue(firstColumnHeader.isDisplayed()); 
		Assert.assertTrue(secondColumnHeader.isDisplayed()); 
		Assert.assertTrue(thirdColumnHeader.isDisplayed()); 
		Assert.assertTrue(fourthColumnHeader.isDisplayed()); 
		Assert.assertTrue(firstComputerName.isDisplayed());
		Assert.assertTrue(firstComputerLink.isEnabled());
		Assert.assertTrue(firstIntroducedDate.isDisplayed()); 
		Assert.assertTrue(firstDiscontinuedDate.isDisplayed()); 
		Assert.assertTrue(firstCompanyName.isDisplayed()); 
		
	}
	
	// Searching available computer name
	public void searchAvailableComputerName(String computerName) throws InterruptedException {
		LOGGER.info("Checking availability of search box... ");
		checkAvailabilityOfSearchTextBox();
		
		LOGGER.info("Entering computer name in search box... ");
		computerSearchBox.sendKeys(computerName);
		Assert.assertEquals(computerSearchBox.getAttribute("value"), computerName);
		
		LOGGER.info("Searching for computer... ");
		computerSearchButton.click();
		Thread.sleep(2000);
		Assert.assertTrue(firstComputerName.isDisplayed());
		Assert.assertEquals(firstComputerName.getText(), computerName);
		computerSearchBox.clear();
	}
	
	// Searching unavailable computer name
	public void searchUnavailableComputerName(String computerName) throws InterruptedException {
		LOGGER.info("Checking availability of search box... ");
		checkAvailabilityOfSearchTextBox();
		
		LOGGER.info("Entering computer name in search box... ");
		computerSearchBox.sendKeys(computerName);
		Assert.assertEquals(computerSearchBox.getAttribute("value"), computerName);
		
		LOGGER.info("Searching for computer... ");
		computerSearchButton.click();
		Thread.sleep(2000);
		Assert.assertEquals(noComputerFound.getText(), NoComputer_Message); 
		computerSearchBox.clear();
	}
	
	// Clicking add computer button
	public void addComputerName() throws InterruptedException { 
		Assert.assertTrue(computerAddButton.isEnabled());
		LOGGER.info("Clicking add computer button... ");
		computerAddButton.click();
		Thread.sleep(2000); 
	}
	
	// Verifying success message
	public void verifySuccessMessage(String computerName) throws InterruptedException {
		LOGGER.info("Searching for success message after computer addition... ");
		WebElement successMessage = driver.findElement(By.cssSelector("div.alert-message.warning")); 
		Assert.assertEquals(successMessage.getText(), Success_Message_Part1+computerName+Success_Message_Part2);
		Thread.sleep(2000); 
	}
	
	// Verifying delete message
	public void verifyDeleteMessage() throws InterruptedException { 
		LOGGER.info("Searching for delete message after computer deletion... ");
		WebElement deleteMessage = driver.findElement(By.cssSelector("div.alert-message.warning")); 
		Assert.assertEquals(deleteMessage.getText(), Delete_Message_Part);
		Thread.sleep(2000); 
	}
	
	// Verifying update message
	public void verifyUpdateMessage(String computerName) throws InterruptedException {
		LOGGER.info("Searching for delete message after computer deletion... ");
		WebElement updateMessage = driver.findElement(By.cssSelector("div.alert-message.warning")); 
		Assert.assertEquals(updateMessage.getText(), Update_Message_Part1+computerName+Update_Message_Part2);
		Thread.sleep(2000); 
	}
	
	// Read computer data from first row of table
	public void readFirstComputerInfoFromDatabaseTable(String computerName, String introducedDate, String discontinuedDate, 
			String companyName) throws InterruptedException, ParseException { 
		LOGGER.info("Reading all data from first row of the table... ");
		Assert.assertEquals(firstComputerName.getText(), computerName);
		if(introducedDate != null){ 
			Assert.assertEquals(convertStringToDate(firstIntroducedDate.getText()), introducedDate);
		}
		else {
			Assert.assertEquals(firstIntroducedDate.getText(), IntroducedDate_Nill);
		}
		if(discontinuedDate != null){
			Assert.assertEquals(convertStringToDate(firstDiscontinuedDate.getText()), discontinuedDate);
		}
		else {
			Assert.assertEquals(firstDiscontinuedDate.getText(), DiscontinuedDate_Nill);
		}
		if(companyName != null){
			Assert.assertEquals(firstCompanyName.getText(), companyName); 
		}
		else {
			Assert.assertEquals(firstDiscontinuedDate.getText(), CompanyName_Nill);
		}
	}
	
	// Read computer data from second row of table
	public void readSecondComputerInfoFromDatabaseTable(String computerName, String introducedDate, String discontinuedDate, 
			String companyName) throws InterruptedException, ParseException { 
		LOGGER.info("Reading all data from second row of the table... ");
		Assert.assertEquals(secondComputerName.getText(), computerName);
		if(introducedDate != null){ 
			Assert.assertEquals(convertStringToDate(secondIntroducedDate.getText()), introducedDate);
		}
		else {
			Assert.assertEquals(secondIntroducedDate.getText(), IntroducedDate_Nill);
		}
		if(discontinuedDate != null){
			Assert.assertEquals(convertStringToDate(secondDiscontinuedDate.getText()), discontinuedDate);
		}
		else {
			Assert.assertEquals(secondDiscontinuedDate.getText(), DiscontinuedDate_Nill);
		}
		if(companyName != null){
			Assert.assertEquals(secondCompanyName.getText(), companyName); 
		}
		else {
			Assert.assertEquals(secondDiscontinuedDate.getText(), CompanyName_Nill);
		}
	}
	
	// Navigate to update page for  existing computer in database
	public void updateComputer(String computerName) throws InterruptedException {
		checkAvailabilityOfSearchTextBox();
		computerSearchBox.sendKeys(computerName);
		Assert.assertEquals(computerSearchBox.getAttribute("value"), computerName);
		computerSearchButton.click();
		Thread.sleep(2000);
		Assert.assertTrue(firstComputerName.isDisplayed());
		Assert.assertEquals(firstComputerName.getText(), computerName);
		computerSearchBox.clear();
		LOGGER.info("Navigating to update page of the computer... ");
		firstComputerLink.click();
		Thread.sleep(2000);
	}
	
	// Check if the computer exists in database
	public boolean checkComputer(String computerName) throws InterruptedException {
		boolean computerPresent;
		checkAvailabilityOfSearchTextBox();
		computerSearchBox.sendKeys(computerName);
		Assert.assertEquals(computerSearchBox.getAttribute("value"), computerName);
		computerSearchButton.click();
		LOGGER.info("Checking if the computer exists in database... ");
		try{
			driver.findElement(By.xpath("//section[@id='main']/table/tbody")); 
			computerPresent = true;
		}
		catch (NoSuchElementException e){
			computerPresent = false;
			computerSearchBox.clear();
			pageHeader.click();
		}
		return computerPresent;
	}
	
	// Check and Read computer data of duplicate computers
	public int checkDuplicateComputersContainingSameData (String computerName, String introducedDate, String discontinuedDate, 
			String companyName) throws InterruptedException {
		checkAvailabilityOfSearchTextBox();
		computerSearchBox.sendKeys(computerName);
		Assert.assertEquals(computerSearchBox.getAttribute("value"), computerName);
		computerSearchButton.click();
		Thread.sleep(2000); 
		LOGGER.info("Checking duplicate computer data ... ");
		int count = driver.findElements(By.xpath("//section[@id='main']/table/tbody/tr")).size();
		Assert.assertTrue(count > 1); 
		try {
			readFirstComputerInfoFromDatabaseTable(computerName, introducedDate, 
					discontinuedDate, companyName);
			readSecondComputerInfoFromDatabaseTable(computerName, introducedDate, 
					discontinuedDate, companyName);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		computerSearchBox.clear();
		pageHeader.click();
		return count;
	}
	
	public void navigateToMainPage() {
		try{
			pageHeader.click();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void navigateToEditPage(){
		if(firstComputerLink.isEnabled())
			firstComputerLink.click();
	}
	
	// Formatting date type from UI to TestData type
	private String convertStringToDate(String dateString)
	{
	    Date date = null;
	    String formatteddate = null; 
	    SimpleDateFormat sdfmt1= new SimpleDateFormat("dd MMM yyyy");
	    SimpleDateFormat sdfmt2 = new SimpleDateFormat("yyyy-MM-dd");
	    try{
	        date = sdfmt1.parse(dateString);
	        formatteddate = sdfmt2.format(date);
	    }
	    catch ( Exception ex ){
	        System.out.println(ex);
	    }
	    return formatteddate;
	}
	
}