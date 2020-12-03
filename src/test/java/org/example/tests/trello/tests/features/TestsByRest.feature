@Rest
Feature: Tests for Rest API

  @boardRest
  Scenario: Creating a board
    Given Board is created by REST
    Then Verify Board by REST
    Then Test Data is cleaned

  @cardRest
  Scenario: Updating a card
    Given Board is created by REST
    Given Column is created by REST
    Given Card is created by REST
    When Card is updating by REST
    Then Verify Card by REST
    Then Test Data is cleaned

  @columnRest
  Scenario: Archiving a column
    Given Board is created by REST
    Given Column is created by REST
    When Column is archiving by REST
    Then Verify Column by REST
    Then Test Data is cleaned