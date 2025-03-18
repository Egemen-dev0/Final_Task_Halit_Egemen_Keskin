# SauceDemo Automation Test Project

## Task Description
Launch URL: https://www.saucedemo.com/

### UC-1 Test Login form with empty credentials:
- Type any credentials into "Username" and "Password" fields.
- Clear the inputs.
- Hit the "Login" button.
- Check the error messages: "Username is required".

### UC-2 Test Login form with credentials by passing Username:
- Type any credentials in username.
- Enter password.
- Clear the "Password" input.
- Hit the "Login" button.
- Check the error messages: "Password is required".

### UC-3 Test Login form with credentials by passing Username & Password:
- Type credentials in username which are under Accepted username are sections.
- Enter password as secret sauce.
- Click on Login and validate the title "Swag Labs" in the dashboard.

## Implementation Details
- Test Automation tool: Selenium WebDriver
- Project Builder: Maven
- Browsers: 1) Edge; 2) Chrome
- Locators: XPath
- Test Runner: JUnit
- Patterns: Abstract Factory, Adapter, Decorator
- Test automation approach: BDD
- Assertions: Hamcrest
- Loggers: Log4j

## Features
- Parallel execution
- Logging for tests
- Data Provider to parametrize tests 