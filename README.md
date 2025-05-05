This project contains automated UI tests written in Java using Selenium WebDriver and managed with Maven.

**Prerequisites**
Java 8 or above installed
Maven installed
Chrome and/or Firefox browsers installed
ChromeDriver and/or GeckoDriver set up in system path (or configured in the project)

**How to Run the Tests**
**Run all tests**
mvn clean test
**Run tests on a specific browser**
mvn clean test -Dbrowser=firefox
**Run tests in headless mode**
mvn clean test -Dheadless=true
**Run a specific test class**
mvn clean test -Dtest=ArithmeticOperationsTest

**Edit the configuration.properties file to set:**
Browser: chrome, firefox, or edge
Headless: true or false
