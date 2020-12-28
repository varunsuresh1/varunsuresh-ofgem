@suite
Feature: Of gem mail checker

    Scenario: User access mail account and deletes unnecessary mails
      Given I am a Of gem customer
      When  I access my Mail Id and find ofgem mail I delete it
      Then  I delete my cookies



