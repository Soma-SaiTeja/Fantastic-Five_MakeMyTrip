@CabFeature
Feature: Fetch low price suv cars
 Scenario Outline: User navigating to the MakeMyTrip website to fetch price of suv cars 
         Given user is on homepage
         When popup appears user proceeds to close the popup
         Then user navigates to cabs menu
        
         And user fills the details of from "<start>" and to "<destination>"
         Then user proceeds to select travel date "<TravelDate>"
         And user selects the required time "<Time>"
         Then user clicks on apply
         
         Given user now clicks on search to fetch results
         When user closes the secondPopup
         And user selects cab type as suv
         Then user fetches the lowest price cab available 
         
          

