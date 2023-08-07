@calculate
Feature: Search Git For User
  Background:
    Given the vehicle purchase price is R "900000"
    And the deposit is R "120000"
    And the payment term is "54" months
    And the balloon payment is "20" %
    And the interest rate is "11" %

  Scenario Outline: Validate that as a user can calculate monthly repayment
    When the user calculates
    Then the monthly repayment is "<monthly_repayment>"

    Examples:
      | monthly_repayment |
      |13816.09           |

  Scenario Outline: Validate that as a user can calculate the amount to finace
    When the user calculates
    Then the amount to finace is "<amount_to_finace>"
    Examples:
      | amount_to_finace |
      |681 207.50        |

  Scenario Outline: Validate that as a user can calculate the interest amount
    When the user calculates
    Then the interest amount is "<interest_amount>"
    Examples:
      | interest_amount |
      |221 135.39       |

  Scenario Outline: Validate that as a user can calculate the cost of credit
    When the user calculates
    Then the cost of credit is "<cost_of_credit>"
    Examples:
      | cost_of_credit |
      |906 068.89      |