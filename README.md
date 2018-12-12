# BackBaseHomeTest-Pratyush

BackBaseHomeTest UI tests

## DESCRIPTION/ GETTING STARTED

This a TestNG testing framework based on Page Object Model design pattern to perform Automation UI test on the Sample UI Application provided by BackBase.
Sample Application - `http://computer-database.herokuapp.com/computers`

## Prerequisites

Things to be installed before importing the project

```
JAVA
MAVEN
```

### Installing

In addition, you will also need some form of web driver interface. See the documentation section below for more information on setting one up.
.
After cloning the _ui_tests_ repository to your machine, issue the following commands within the _ui-tests_ directory:

FOR ECLIPSE USERS
```  
mvn eclipse:eclipse 
```
FOR INTELLIJ USERS
...
mvn idea:idea
...

### Running the tests

The tests assume there is a web server running and that the root contains the *ui-tests* directory.
Hence the we would have to mention the browser, we wish to execute the test cases in `config.properties` file.
The file is located in the package `com.backbase.automation.config` inside `src/test/java` folder

```
ex- browser = CHROME ( For Chrome Browser ) / 
ex- browser = FF ( For FireFox Browser )
```

### Run tests on one browser via Terminal

```
mvn clean verify
```
If you want to run the local tests through selenium then you'll need to be sure to have selenium running.
Framework already contains updated Chrome driver, Gecko driver and IE driver.
Hence the pom.xl has all the required dependencies mentioned. 

### Test cases explanation

There are 2 classes containing different test cases inside `com.backbase.automation.testcases` under `src/test/java` folder.

  * `BackBaseCRUDTest` - Contains all test cases covering end to end flow. Where new computer data is created > read > updated > deleted.
  * `BackBaseCRUDTest` - Contains all EDGE test cases scenarios.
  
There is 1 helper class containg promise chain methods. 

  * `TestCaseHelper` - Contains all re-usable methods. 
  
### Framework Documentation

All the codes are written under src/test/java.

## Package Level

* `com.backbase.automation.config`       - Contains browser configration file.

* `com.backbase.automation.ui.basetest`  - Contains 'TestBase' class.

    * `TestBase`  - contains browser initialization, @Before and @After methods.
    
* `com.backbase.automation.ui.util`      - Contains `TestData` , `TestUtil` and `WebEventListner` classes.

    * `TestData`        - Contains all necessary test datas. 
    * `TestUtil`        - Contains all methods to take screenshots whenever a test case fails.
    * `WebEventListner` - It override all the methods and define certain useful Log statements which would be displayed/logged as the application under test is being run.

* `com.backbase.automation.ui.pages`     - Contains `ApplicationMainPage` , `AddComputerPage` and `EditComputerPage` classes.

     * `ApplicationMainPage` - Contains all WebElements available in the Main page of the application. 
     * `AddComputerPage`     - Contains all WebElements available in the computer Add page of the application. 
     * `EditComputerPage`    - Contains all WebElements available in the computer Edit page of the application. 

* `com.backbase.automation.ui.pages` - Contains `BackBaseCRUDTest` , `BackBaseEdgeTest` and `TestCasesHelper` classes. 

     * `BackBaseCRUDTest` - Contains all test cases covering end to end flow. Where new computer data is created > read > updated > deleted.
     * `BackBaseCRUDTest` - Contains all EDGE test cases scenarios. 
     * `TestCasesHelper`  - Contains all re-usable methods.  

## Resource and Suite Level

All the drivers and TestNG test suites are listed under `src/main/resources` folder

## Framework Flow

* `TestCases` makes a call to `TestBase`              - To Initialize Browser settings and setup Test environment. 
* `TestBase`  makes a call to `WebDriverEventListner` - To fire event driver and capture necessary logs. 
* `TestCases` makes call to `TestCasesHelper`         - To trigger helper methods.
* `TestCasesHelper` makes call to necesary classes in `com.backbase.automation.ui.pages` - To load all webelements available in specifc pages.
* `WebDriverEventListner`  makes a call to `TestUtil`  - To capture screenshots, in case of exceptions 
