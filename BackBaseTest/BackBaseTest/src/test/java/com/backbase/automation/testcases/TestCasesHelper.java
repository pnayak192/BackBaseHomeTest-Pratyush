package com.backbase.automation.testcases;

import java.text.ParseException;

import org.testng.Assert;

import com.backbase.automation.ui.pages.AddComputerPage;
import com.backbase.automation.ui.pages.ApplicationMainPage;
import com.backbase.automation.ui.pages.EditComputerPage;

public class TestCasesHelper {
	
	static ApplicationMainPage mainPage;
	static AddComputerPage addComputerPage;
	static EditComputerPage editComputerPage;
	
	private static int updatedComputerCount;
	private static int latestComputerCount;
	
	// This method checks for any existing computer data and deletes it
	public static void checkAndDeleteExistingComputerData(String computerName) throws InterruptedException, ParseException{
		mainPage = new ApplicationMainPage();
		editComputerPage = new EditComputerPage();
		mainPage.navigateToMainPage();
		updatedComputerCount = mainPage.checkAndGetComputerCountDisplayed();
		if(mainPage.checkComputer(computerName)){
		mainPage.navigateToEditPage();
		editComputerPage.deleteComputerFromDatabase();
		mainPage.verifyDeleteMessage();
		latestComputerCount = mainPage.checkAndGetComputerCountDisplayed();
		Assert.assertTrue(latestComputerCount < updatedComputerCount,"Deletion of new computer "
				+ "has not decreased computer count");
		}
	}
	
	
	// This method checks for Unique or Duplicate 
	public static void deleteUniqueComputerData(String computerName) throws InterruptedException, ParseException{
		mainPage = new ApplicationMainPage();
		editComputerPage = new EditComputerPage();
		mainPage.navigateToMainPage();
		updatedComputerCount = mainPage.checkAndGetComputerCountDisplayed();
		mainPage.updateComputer(computerName);
		editComputerPage.deleteComputerFromDatabase();
		mainPage.verifyDeleteMessage();
		latestComputerCount = mainPage.checkAndGetComputerCountDisplayed();
		Assert.assertTrue(latestComputerCount < updatedComputerCount,"Deletion of new computer "
				+ "has not decreased computer count");
	}
	
	// This method reads unique computer data
	public static void readUniqueComputerData(String computerName, String introducedDate, String discontinuedDate, 
			String company) throws InterruptedException, ParseException{
		mainPage = new ApplicationMainPage();
		mainPage.searchAvailableComputerName(computerName); 
		mainPage.readFirstComputerInfoFromDatabaseTable(computerName, introducedDate, 
					discontinuedDate, company);
	}
	
	// This method reads duplicate computer data
	public void readDuplicateComputerData(String computerName, String introducedDate, String discontinuedDate, 
			String company) throws InterruptedException, ParseException{
		mainPage = new ApplicationMainPage();
		mainPage.checkDuplicateComputersContainingSameData(computerName, introducedDate, 
				discontinuedDate, company);
	}
	
	// This method reads and deletes duplicate computer data
	public static void readAndDeleteDuplicateComputerFromDatabase(String computerName, String introducedDate, String discontinuedDate, 
			String company) throws InterruptedException, ParseException{
		mainPage = new ApplicationMainPage();
		addComputerPage = new AddComputerPage();
		editComputerPage = new EditComputerPage();
		int count = mainPage.checkDuplicateComputersContainingSameData(computerName, introducedDate, 
				discontinuedDate, company);
		if (count > 1) {
			for (int i = 0; i < count; i++){
				updatedComputerCount = mainPage.checkAndGetComputerCountDisplayed();
				mainPage.updateComputer(computerName);
				editComputerPage.deleteComputerFromDatabase();
				mainPage.verifyDeleteMessage();
				latestComputerCount = mainPage.checkAndGetComputerCountDisplayed();
				Assert.assertTrue(latestComputerCount < updatedComputerCount,"Deletion of new computer "
						+ "has not decreased computer count");
			}
		}
	}

}
