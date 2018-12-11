package com.backbase.automation.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.backbase.automation.ui.util.TestData.CompanyName_1;
import static com.backbase.automation.ui.util.TestData.CompanyName_2;
import static com.backbase.automation.ui.util.TestData.DiscontinuedDate_1;
import static com.backbase.automation.ui.util.TestData.DiscontinuedDate_2;
import static com.backbase.automation.ui.util.TestData.IntroducedDate_1;
import static com.backbase.automation.ui.util.TestData.IntroducedDate_2;
import static com.backbase.automation.ui.util.TestData.computerName_1;
import static com.backbase.automation.ui.util.TestData.computerName_2;

import java.text.ParseException;

import com.backbase.automation.ui.basetest.TestBase;
import com.backbase.automation.ui.pages.AddComputerPage;
import com.backbase.automation.ui.pages.ApplicationMainPage;
import com.backbase.automation.ui.pages.EditComputerPage;

public class BackBaseCRUDTest extends TestBase{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BackBaseCRUDTest.class);
	
	ApplicationMainPage mainPage;
	AddComputerPage addComputerPage;
	EditComputerPage editComputerPage;

	private int updatedComputerCount;
	private int existingComputerCount ;


//	public CRUDTestCasesUI(){
//		super();
//
//	} 
//	@BeforeMethod
//	public void setUp() throws InterruptedException {
//		initialization();
//	}

	@Test(priority=1)
	// This test case will create data, read and delete the data.
	public void createReadAndDeleteComputerFromDatabase() throws InterruptedException, ParseException{ 
		mainPage = new ApplicationMainPage();
		addComputerPage = new AddComputerPage();
		
		LOGGER.info("Checking if the computer already exists in database and delete...");
		TestCasesHelper.checkAndDeleteExistingComputerData(computerName_1);
		
		LOGGER.info("Taking current computer counts...");
		this.existingComputerCount = mainPage.checkAndGetComputerCountDisplayed();
		
		LOGGER.info("Clicking on Add computer button...");
		mainPage.addComputerName();
		
		LOGGER.info("Adding new computer data to database...");
		addComputerPage.addComputerToDatabase(computerName_1, IntroducedDate_1, 
				DiscontinuedDate_1, CompanyName_1);
		
		LOGGER.info("Verifying success message after addition of new computer...");
		mainPage.verifySuccessMessage(computerName_1);
		
		LOGGER.info("Taking updated computer counts...");
		this.updatedComputerCount = mainPage.checkAndGetComputerCountDisplayed();
		Assert.assertTrue(updatedComputerCount > existingComputerCount, "Deletion of new computer "
					+ "has not increased computer count");
		
		LOGGER.info("Reading the computer from database...");
		TestCasesHelper.readUniqueComputerData(computerName_1, IntroducedDate_1, DiscontinuedDate_1, CompanyName_1);
		
		LOGGER.info("Deleting the computer from database...");
		if (updatedComputerCount>existingComputerCount)
			TestCasesHelper.deleteUniqueComputerData(computerName_1);

	} 
	
	@Test(priority=2)
	// This test case will update data, read and delete the data.
	public void updateAndReadComputerFromDatabase() throws InterruptedException, ParseException{ 
		mainPage = new ApplicationMainPage();
		addComputerPage = new AddComputerPage();
		
		LOGGER.info("Checking if the computer already exists in database and delete...");
		TestCasesHelper.checkAndDeleteExistingComputerData(computerName_1);
		TestCasesHelper.checkAndDeleteExistingComputerData(computerName_2);
		
		LOGGER.info("Taking current computer counts...");
		this.existingComputerCount = mainPage.checkAndGetComputerCountDisplayed();
		
		LOGGER.info("Clicking on Add computer button...");
		mainPage.addComputerName();
		
		LOGGER.info("Adding new computer data to database...");
		addComputerPage.addComputerToDatabase(computerName_1, IntroducedDate_1, 
				DiscontinuedDate_1, CompanyName_1);
		
		LOGGER.info("Verifying success message after addition of new computer...");
		mainPage.verifySuccessMessage(computerName_1);
		
		LOGGER.info("Taking updated computer counts...");
		this.updatedComputerCount = mainPage.checkAndGetComputerCountDisplayed();
		Assert.assertTrue(updatedComputerCount > existingComputerCount, "Deletion of new computer "
					+ "has not increased computer count");
		
		LOGGER.info("Navigating to particular computer data edit page...");
		mainPage.updateComputer(computerName_1);
		
		LOGGER.info("Updating existing computer data...");
		addComputerPage.addComputerToDatabase(computerName_2, IntroducedDate_2, 
				DiscontinuedDate_2, CompanyName_2);
		
		LOGGER.info("Searching previous computer name in database...");
		mainPage.searchUnavailableComputerName(computerName_1);
		
		LOGGER.info("Reading the computer from database...");
		TestCasesHelper.readUniqueComputerData(computerName_2, IntroducedDate_2, 
				DiscontinuedDate_2, CompanyName_2);
		
		LOGGER.info("Deleting the computer from database...");
		if (updatedComputerCount>existingComputerCount)
			TestCasesHelper.deleteUniqueComputerData(computerName_2);
	} 

	@Test(priority=3)
	// This test case will create, update, read and delete the data.
	public void creatReadUpdateAndDeleteComputerFromDatabase() throws InterruptedException, ParseException{ 
		mainPage = new ApplicationMainPage();
		addComputerPage = new AddComputerPage();
		
		LOGGER.info("Checking if the computer already exists in database and delete...");
		TestCasesHelper.checkAndDeleteExistingComputerData(computerName_1);
		TestCasesHelper.checkAndDeleteExistingComputerData(computerName_2);
		
		LOGGER.info("Taking current computer counts...");
		this.existingComputerCount = mainPage.checkAndGetComputerCountDisplayed();
		
		LOGGER.info("Clicking on Add computer button...");
		mainPage.addComputerName();
		
		LOGGER.info("Adding new computer data to database...");
		addComputerPage.addComputerToDatabase(computerName_1, IntroducedDate_1, 
				DiscontinuedDate_1, CompanyName_1);
		
		LOGGER.info("Verifying success message after addition of new computer...");
		mainPage.verifySuccessMessage(computerName_1);
		
		LOGGER.info("Taking updated computer counts...");
		this.updatedComputerCount = mainPage.checkAndGetComputerCountDisplayed();
		Assert.assertTrue(updatedComputerCount > existingComputerCount, "Deletion of new computer "
					+ "has not increased computer count");
		
		LOGGER.info("Navigating to particular computer data edit page...");
		mainPage.updateComputer(computerName_1);
		
		LOGGER.info("Updating existing computer data...");
		addComputerPage.addComputerToDatabase(computerName_2, IntroducedDate_2, 
				DiscontinuedDate_2, CompanyName_2);
		
		LOGGER.info("Searching previous computer name in database...");
		mainPage.searchUnavailableComputerName(computerName_1);
		
		LOGGER.info("Reading the computer from database...");
		TestCasesHelper.readUniqueComputerData(computerName_2, IntroducedDate_2, 
				DiscontinuedDate_2, CompanyName_2);
		
		LOGGER.info("Deleting the computer from database...");
		if (updatedComputerCount>existingComputerCount)
			TestCasesHelper.deleteUniqueComputerData(computerName_2);
	} 
	
/*	private void checkAndDeleteExistingComputerData(String computerName) throws InterruptedException, ParseException{
		mainPage = new ApplicationMainPage();
		editComputerPage = new EditComputerPage();
		if(mainPage.checkComputer(computerName)){
		editComputerPage.deleteComputerFromDatabase();
		mainPage.verifyDeleteMessage();
		this.latestComputerCount = mainPage.checkAndGetComputerCountDisplayed();
		Assert.assertTrue(latestComputerCount < updatedComputerCount,"Deletion of new computer "
				+ "has not decreased computer count");
		}
	}
	
	
	// This method checks for Unique or Duplicate 
	private void deleteUniqueComputerData(String computerName) throws InterruptedException, ParseException{
		mainPage = new ApplicationMainPage();
		editComputerPage = new EditComputerPage();
		mainPage.updateComputer(computerName);
		editComputerPage.deleteComputerFromDatabase();
		mainPage.verifyDeleteMessage();
		this.latestComputerCount = mainPage.checkAndGetComputerCountDisplayed();
		Assert.assertTrue(latestComputerCount < updatedComputerCount,"Deletion of new computer "
				+ "has not decreased computer count");
	}
	
	private void readUniqueComputerData(String computerName, String introducedDate, String discontinuedDate, 
			String company) throws InterruptedException, ParseException{
		mainPage = new ApplicationMainPage();
		mainPage.searchAvailableComputerName(computerName); 
		mainPage.readFirstComputerInfoFromDatabaseTable(computerName, introducedDate, 
					discontinuedDate, company);
	}
	
	private void readDuplicateComputerData(String computerName, String introducedDate, String discontinuedDate, 
			String company) throws InterruptedException, ParseException{
		mainPage = new ApplicationMainPage();
		mainPage.checkDuplicateComputersContainingSameData(computerName, introducedDate, 
				discontinuedDate, company);
	}
	
	private void readAndDeleteDuplicateComputerFromDatabase(String computerName, String introducedDate, String discontinuedDate, 
			String company) throws InterruptedException, ParseException{
		mainPage = new ApplicationMainPage();
		addComputerPage = new AddComputerPage();
		editComputerPage = new EditComputerPage();
		int count = mainPage.checkDuplicateComputersContainingSameData(computerName, introducedDate, 
				discontinuedDate, company);
		if (count > 1) {
			for (int i = 0; i < count; i++){
				this.updatedComputerCount = mainPage.checkAndGetComputerCountDisplayed();
				mainPage.updateComputer(computerName);
				editComputerPage.deleteComputerFromDatabase();
				mainPage.verifyDeleteMessage();
				this.latestComputerCount = mainPage.checkAndGetComputerCountDisplayed();
				Assert.assertTrue(latestComputerCount < updatedComputerCount,"Deletion of new computer "
						+ "has not decreased computer count");
			}
		} 
	} */

	
//	@AfterMethod
//	public void tearDown(){
//
//		driver.quit();
//	}

}
