Feature: User Registration

  Scenario: Registration successful
    Given I am on the Registration page
    When I complete the form with valid data
    And I submit the registration
    Then I should see a success message

#  For Parameterization and Data Driven Testing
#  Scenario Outline: Registration successful
#    Given I am on the Registration page
#    When I complete the form with valid "<username>","<password>","<confirmpassword>"
#    And I submit the registration
#    Then I should see a success message
#
#    Examples:
#      |username|password|confirmpassword|
#      |qwerty|aaaaaaaa|aaaaaaaa       |
#      |asdfgh|ssssssss|ssssssss       |
#      |zxcvbn|dddddddd|dddddddd       |
#      |qazxsw|ffffffff|ffffffff       |
