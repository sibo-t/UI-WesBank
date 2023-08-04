@calculate
Feature: Search Git For User
  Background:
    Given the vehicle purchase price is R "900000"
    And the deposit is R "120000"
    And the payment term is "54" months
    And the balloon payment is "20" %
    And the interest rate is "11" %

  Scenario Outline: Validate that as a user can calculate monthly repayment
    When the user calculates the monthly repayment
    Then the monthly repayment is "<monthly_repayment>"

    Examples:
      | monthly_repayment |
      |13816.09           |
