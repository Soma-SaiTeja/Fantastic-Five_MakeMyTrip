@TravelInsranceFeature

Feature: Retreive student insurance plan name and price
Scenario Outline: User navigates to travel insurance to fetch the plan name and price

Given user opens homepage
When user handles the popup
And clicks on travel insurance tab
And fills the details
Then fetches plan details