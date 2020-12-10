Feature: As a user I want o be able to add and remove tags to existing suggestions

    Scenario: I can add a new tag to an existing suggestion
        Given book Margarita with tag finlandia is saved
        When tag "kaunokirjallisuus" is added to "kirja" named "Margarita"
        Then new tag "kaunokirjallisuus" is added

    Scenario: I can remove an existing tag from an existing suggestion
        Given book Margarita with tag finlandia is saved
        When tag "finlandia" is removed from "kirja" named "Margarita"
        Then tag "finlandia" is deleted