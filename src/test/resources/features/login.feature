Feature: SauceDemo Login Functionality

  Scenario: Login with empty credentials
    Given I am on the login page
    # Original approach that didn't work:
     When I enter "test_user" in the username field
     And I enter "test_password" in the password field
     And I clear the username field
     And I clear the password field
     Then I refresh the page because the DOM is not updating the value
    # And I click the login button expecting error
    When I click the login button expecting error
    Then I should see the error message "Epic sadface: Username is required"

  Scenario: Login with empty password
    Given I am on the login page
    When I enter "standard_user" in the username field
    # Original approach that didn't work:
   # And I enter "secret_sauce" in the password field
   # And I clear the password field
    And I click the login button expecting error
    Then I should see the error message "Epic sadface: Password is required"

  Scenario Outline: Login with valid credentials
    Given I am on the login page
    When I enter "<username>" in the username field
    And I enter "<password>" in the password field
    And I click the login button
    Then I should be redirected to the dashboard
    And I should see the title "Swag Labs"

    Examples:
      | username               | password     |
      | standard_user          | secret_sauce |
      | problem_user           | secret_sauce |
      | performance_glitch_user| secret_sauce |
      | error_user             | secret_sauce |
      | visual_user            | secret_sauce | 