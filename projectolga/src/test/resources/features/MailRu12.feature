# new feature
# Tags: optional
    
Feature: Test Mail.ru functions
    
Scenario: Create email for group of users
    Given I login in mail ru page
    When I create new email
    Then I see email in 'Отправленные' folder

Scenario: Move email to spam
    Given I login in mail ru page
    When I move email to spam
    Then I don't see email in 'Входящие' folder
    Then I see email in 'Спам' folder

Scenario: Move email from spam
    Given I login in mail ru page
    When I move email from spam
    Then I don't see email in 'Спам' folder
    Then I see email in 'Входящие' folder

Scenario: Mark three emails with flag
    Given I login in mail ru page
    When I mark three emails
    Then three emails is marked

Scenario: Unmark all emails with flag
    Given I login in mail ru page
    When I unmark emails
    Then all email is unmarked







