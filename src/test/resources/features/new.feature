Feature: Subscription Packages


  @Test
  Scenario: Validate home screen
    Given the user navigate to open source demo
    When user login with valid creds
    Then dashboard should displayed successfully
    And user navigating to admin screen
    When user try to create record with valid data
    Then record should created successfully
    And when user try to delete recode
    Then record should be deleted successfully

    @Test
    Scenario: validate create and delete user from apis
      Given user should be able to create user from apis
      And can delete user too from apis