Feature: As a user I want to be able to search suggestions by tag

    Scenario: When there is a book tagged, search returns it
        Given book Margarita with tag finlandia is saved
        And command hae is selected
        When tag "finlandia" is entered
        Then info for Margarita is displayed

    Scenario: When there is an article tagges, search returns it
        Given article Ohtumateriaali with tag ohjelmistotuotanto is saved
        And command hae is selected
        When tag "ohjelmistotuotanto" is entered
        Then info for Ohtumateriaali is displayed
