Feature: As a user I want o be able to add and remove tags to existing suggestions

    Scenario: I can add a new tag to an existing suggestion
        Given book Margarita with tag finlandia is saved
        When tag "kaunokirjallisuus" is added to book "Margarita"
        Then new tag is added

