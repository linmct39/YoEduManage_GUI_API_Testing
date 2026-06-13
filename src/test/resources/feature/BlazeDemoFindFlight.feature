Feature: Blaze Demo
	 Background: Open Web Page
	 	Given I open web page

	@smokeTest @TestBlazeDemo
	Scenario Outline: Test Blaze Demo
#		Given I open web page
		Then I select flight from "<from>" to "<to>"
		And I verify title page is "BlazeDemo - reserve"
		And I click Choose This Flight
		And I verify title page is "BlazeDemo Purchase"
		And I enter detail information
		And I verify title page is "BlazeDemo Confirmation"

		Examples:
			| from 			| to 		|
			| Boston 		| London	|
#			| Philadelphia 	| Rome 		|


