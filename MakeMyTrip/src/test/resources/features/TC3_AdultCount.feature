@AdultCountFeature

Feature: Retrieve the adult count in hotels tab of MakeMyTrip
  Scenario Outline: Retrieve maximum adults shown in Hotels guest selector
    Given user is on the homepage
    And the login popup is closed
    When the user navigates to the Hotels tab
    And the user opens the guests dropdown and selects the Adults option
    Then the maximum adults value should be "<ExpectedAdultCount>"
