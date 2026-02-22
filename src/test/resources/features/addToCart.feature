Feature: Adding products to cart

  Background: Initialise Test Data
    Given I have test data for the "regular" user

  Scenario: Adding the backpack and bike light to cart
    Given I log into the website as a regular user
    When I add the "backpack" to my cart
    When I add the "bike light" to my cart
    Then My cart shows "2" items added
