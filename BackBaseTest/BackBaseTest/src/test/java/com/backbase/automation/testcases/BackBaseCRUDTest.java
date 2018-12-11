package com.backbase.automation.testcases;

import org.testng.Assert;
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


	public BackBaseCRUDTest(){
		super();

	} 

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
}
