@GiftCardFeature
Feature: Get the error message while filling invalid email address
 Scenario Outline: User navigating to the gift cards section in website 
         Given user in homepage
         When user proceeds to close the popup
         Then user navigates to gift cards tab
         And user selects gift card "<card>" among cards 
         Then user fills the details "<senderName>" "<phnNo>" "<emailAddress>"
         Then user extracts the error message
         
          

