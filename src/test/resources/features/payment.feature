Feature: Payment Service
  Scenario: Getting payment transactions of driver by existing id
    Given Database contains transactions of driver with id 1
    When Get request with id 1 passed to getDriverTransactionsById method
    Then The response should contain transaction with id 1

  Scenario: Getting payment transactions of driver by non-existing id
    Given Database does not contain transactions of driver with id 5
    When Get request with id 5 passed to getDriverTransactionsById method
    Then The TransactionNotFoundException for driver should be thrown

  Scenario: Getting payment transactions of passenger by existing id
    Given Database contains transactions of passenger with id 1
    When Get request with id 1 passed to getPassengerTransactionsById method
    Then The response should contain transaction with id 1

  Scenario: Getting payment transactions of passenger by non-existing id
    Given Database does not contain transactions of passenger with id 5
    When Get request with id 5 passed to getPassengerTransactionsById method
    Then The TransactionNotFoundException for passenger should be thrown