@wip
Feature: Events

  Background:
    When I get all events information

  Scenario: Verifying that all the events returned are football events
    Then Status code should be 200
    Then Verify that all class names are "Football"


  Scenario: Verifying home and away games
    Then Status code should be 200
    Then Verify that there is a ‘home’ and an ‘away’ competitor for each of the events
