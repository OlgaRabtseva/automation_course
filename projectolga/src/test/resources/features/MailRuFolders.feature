# new feature
# Tags: optional
    
Feature: Test Mail.ru page

Scenario: Create new folder
    Given I login in mail ru page
    When I create new folder in main menu
    Then I see new folder in main menu

Scenario: Delete created folder
    Given I login in mail ru page
    When I delete new created folder
    Then folder is deleted





