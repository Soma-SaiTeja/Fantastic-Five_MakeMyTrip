# MakeMyTrip Hackathon - Automated Testing Framework
 
This repository contains a comprehensive automated testing framework developed for validating the functionalities of the MakeMyTrip web application. The project is built using industry-standard practices and modern testing tools to ensure high reliability, maintainability, and extensibility.
 
## Project Objectives
 
- Conduct end-to-end automated UI testing of the MakeMyTrip website.
- Facilitate cross-browser and sequential testing using Selenium Grid.
- Implement data-driven testing for extensive input coverage.
- Generate structured logs and reports for better traceability.
- Capture automated screenshots for better understanding.
- Provide a reusable and scalable testing architecture.
 
## Technologies and Tools Used
 
Technologies and Tools Used
Programming Language: Java 21
 
Testing Framework: TestNG, Cucumber (for BDD)
 
Automation Tool: Selenium WebDriver
 
Design Pattern: Page Object Model (with PageFactory)
 
Build Tool: Maven
 
Logging: Log4j2
 
Reporting: Allure Reports
 
Retry Mechanism: TestNG RetryAnalyzer
 
sequential Execution: Selenium Grid
 
Test Data Management:
 
Excel (using Apache POI)
 
JSON
 
.properties files
 
## Project Structure
 
```
MakeMyTrip/
└── src/
|    ├── main/
|    │   ├── java/
|    │   │   └── com.MakeMyTrip/
|    │   │			 ├── base/                 # Browser setup, WebDriver config
|    │   │			 ├── pages/                # Page Object Model classes
|    │   │			 └── Utilities/			   # Reusable utilities (WebDriver, readers)
|    │   └── resources/
|    │       └── config.properties          # Environment and test configuration
|    │
|    └── test/
|        ├── java/
|        │   └── com.MakeMyTrip/
|        │       ├── Hooks/                  # Cucumber hooks (@Before, @After)
|        │       ├── reRun/                  # Retry logic or failed test reruns
|        │       ├── StepDefinitions/        # Step definitions for feature files
|        │       └── TestRunner/             # TestNG or Cucumber runner classes
|        │	
|        └── resources/
|             ├── features/                  # Gherkin feature files
|             ├── output/                    # Screenshots, logs, or custom artifacts
|             └── log4j2.xml                 # Logging configuration
|			           
|
├── pom.xml                                 # Maven build configuration
├── testng.xml                              # TestNG suite configuration
├── README.md                               # Project overview and setup instructions
├── target/                                 # Build and report output
│   ├── allure-report/                      # Final Allure HTML report
│   ├── allure-results/                     # Raw Allure test results (JSON, attachments)
│   └── cucumber-report.html                # Cucumber HTML report
│   
├── test-output/                            # Default TestNG output folder
│   ├── screenshots/                        # Stores captured screenshots
│   └── JsonOutput/                         # Test Results files
```
 
## Getting Started
 
### Prerequisites
 
- Java JDK 21 or higher
- Apache Maven 3.6 or higher
- Chrome and/or Edge browsers
- Allure Command Line Tool (for reports)
- Selenium Grid (for remote execution)
 
### Build and Run the Project
 
To clean and build the project:
```
mvn clean install
```
 
To execute tests using the default suite:
```
mvn test
```
 
To run a specific suite:
```
mvn test -DsuiteXmlFile=testng.xml
```
 
To generate and open Allure report:
```
allure serve target/allure-results
```
 
## Framework Features
 
### Page Object Model (POM)
 
The framework uses the Page Object Model with PageFactory to ensure separation of concerns and improve reusability and readability.
 
### Cross-Browser Testing
 
Browser support is parameterized via TestNG. The framework supports execution on both Chrome and Edge.
 
### Cucumber Integration
 
Supports BDD-style test development using Gherkin feature files and step definitions.
 
### Data-Driven Testing
 
Supports dynamic input through external data sources:
- Excel files using Apache POI
- JSON files for structured data
- `.properties` files for configuration
 
### Retry Mechanism
 
Includes a custom implementation of TestNG's `IRetryAnalyzer` to rerun failed tests and handle flaky test cases.
 
### Logging
 
Logs test execution using Log4j2 with timestamps, log levels, and error details. Logs are saved under `logs/automation.log`.
 
### Screenshot Capture
 
Automatically captures a screenshots. Files are stored under the `screenshots/` directory.
 
### Selenium Grid Support
 
Can run tests in sequential across nodes using Selenium Grid for distributed execution.
 
### Allure Reporting
 
Provides advanced test reporting using Allure, including detailed step execution and failure analysis.
 
## Output Locations
 
- Logs: `logs/automation.log`
- Screenshots: `test-output/screenshots/`
- Reports: `target/allure-results/`
 
## Contribution
 
This project was developed as part of a hackathon for educational and demonstration purposes. Contributions in the form of improvements, additional test cases, and architectural enhancements are welcome.
 
## License
 
This code is intended for non-commercial, educational use only. It is not licensed for production deployments or commercial distribution.
 
## Contact
 
For queries or collaboration, please reach out via your team’s communication channels.
 
## How to Run Locally
 
1. Clone the repo: `git clone https://github.com/saiphani2k3/Fantastic-Five_MakeMyTrip.git`  
2. Open the project in Eclipse or IntelliJ  
3. Install dependencies via Maven: `mvn clean install`  
4. Run tests using TestNG or Cucumber runner  
5. Generate reports: `mvn allure:serve`
 
 
