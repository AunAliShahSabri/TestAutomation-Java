Feature: Account Overview

  Scenario Outline: Overseeing the Account
    Given User is on Accounts Overview page
    When User clicks on the Account Number
    And Selects "<Activity Period>" and "<Type>" from the dropdowns
    Then Upon clicking GO transaction detail should be returned

    Examples:
    |Activity Period|Type|
    |May|Credit|