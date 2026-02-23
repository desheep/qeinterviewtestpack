Feature: Login

  Scenario: Regular user can login and see the inventory page
    Given I have test data for the "regular" user
    When I log into the website as a regular user
    Then I can see the inventory page

  Scenario: Locked out user sees an error message on the landing page
    Given I have test data for the "locked_out" user
    When I try to log into the website as the locked out user
    Then I see an error message telling me I'm locked out
