@VisaProcessFeature
Feature: Get the error message while filling invalid email address
 Scenario Outline: User navigating to the visa section in website 
         Given user in the homepage
         Then closing popup
         When user navigates to visa tab
         And user double clicks on the destination to enter country "<cou>" 
         And user also selects MonthYear "<travelMonyear>" with date of departure "<travelDate>" and "<returnDate>"
         Then user fetched the steps of visa process