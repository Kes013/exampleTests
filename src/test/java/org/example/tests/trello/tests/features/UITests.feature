@UI
Feature: Tests for UI

  @createTeam
  Scenario: Creating a team
    When User creates a Team
    Then Team is valid after create

  @createCard
  Scenario: Creating a card
    Given Board is created by REST
    Given Column is created by REST
    When User creates a Card
    Then Card is valid after create

  @editCard
  Scenario: Editing a card
    Given Board is created by REST
    Given Column is created by REST
    Given Card is created by REST
    When User edits a card
    Then Card is valid after edit

  @copyCard
  Scenario Outline: Copying a card to another board
    Given Board is created by REST
    Given Column is created by REST
    Given Card is created by REST
    When User copies a card to board "<board>" and list "<list>" on position "<position>"
    Then Card is valid after copy
    Examples:
      | board                         | list           | position |
      | TestForCards                  | Doing          | 1        |
      | TestForCards                  | Done           | 1        |
    @fail
    Examples:
      | board                         | list           | position |
      | TestForCards                  | To Do          | 2        |


  @addChecklist
  Scenario: Adding checklist to card
    Given Board is created by REST
    Given Column is created by REST
    Given Card is created by REST
    When User adds checklist to the card
    Then Card is valid after addCheckList