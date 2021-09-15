# AutomationISS
Prototype Application to show ISS Automation, Technology choices, Approaches and Structures

*  Ubuntu/Mac System (Preferred OS)
*  Java 11.0.11
*  Apache Maven 3.6.0

# Tools used for Testing:
*  RestAssured
*  TestNG

# Tool used for Reporting:
*  ExtentReport

# Approach used:
* BDD
* Data driven(using apache POI API)

To run the automation scripts from command prompt, please use the following command from the terminal where the pom.xml file is present

**mvn test**

To view the reports, once the test is completed. Visit the following files

* target/surefire-reports/index.html
* target/surefire-reports/ExtentReportsTestNG.html

**ExtentReportsTestNG.html** is the extent report which gives the graphical represenation of overall test pass/fails<br/>
**index.html** will give the report with failure reasons.




