Feature: SIM Card Activation

  Scenario: Successful SIM card activation
    Given the SIM card acutator is running
    When I submit and activation request with ICCID "1255789453849037777"
    Then the activation should be successful
    And I can verify the activation status from the database

  Scenario: Failed SIM car activation
    Given the SIM card acutator is running
    When I submit and activation request with ICCID "8944500102198304826"
    Then the activation should be fail
