**This is a template README.md.  Be sure to update this with project specific content that describes your ui test project.**
Project: OFGEM email checker
UI test suite for the `OFGEM email Checker` using WebDriver and `Maven/cucumber/Junit>`.

## Running the tests
Prior to executing the tests ensure you have the appropriate dependencies installed Maven and Java 
This requires Maven and Jdk to be installed and environmental variables/path set 

##Running the tests from terminal=
mvn clean test -Dtest=Runner.RunSuite -Dsurefire.useFile=false

execute the `run_tests.sh` script for Unix Environment to run tests

    ./run_tests.sh
    
 ##Running Tests out of the box without much of configuration 
 Open the project in an IDE (Intellij/Eclipse)   
 Run configuration for Junit test is implemented as part of the project configuraion
 Runner File for Junit tests is in the package Runner.
 
##Running tests on  browsers
Change the browser version in Config.properties file to enable test suite to run in Chrome or firefox



