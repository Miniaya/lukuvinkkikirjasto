Feature: As a user I want to be able to search suggestions by tag

    Scenario: When there is a book tagged, search returns it
        Given book Margarita with tag finlandia is saved
        And command hae is selected
        When tag "finlandia" is entered
        Then info for Margarita is displayed

    Scenario: When there is an article tagged, search returns it
        Given article Ohtumateriaali with tag ohjelmistotuotanto is saved
        And command hae is selected
        When tag "ohjelmistotuotanto" is entered
        Then info for Ohtumateriaali is displayed

    Scenario: When there is no suggestions tagged, message is shown
        Given command hae is selected
        When tag "eimuutenvarmaanootagi" is entered
        Then error message "Tagilla eimuutenvarmaanootagi ei löytynyt vinkkejä. Tarkista kirjoitusasu." is shown

    Scenario: When there are multiple suggestions tagged, they are all shown
        Given book Margarita with tag finlandia is saved
        And article Finlandia-hymni is saved
        And command hae is selected
        When tag "finlandia" is entered
        Then info for both Margarita and Finlandia are displayed

    Scenario: Searching by tag does not return matches from names
        Given book Margarita with tag finlandia is saved
        And command hae is selected
        When tag "Margarita" is entered
        Then error message "Tagilla margarita ei löytynyt vinkkejä. Tarkista kirjoitusasu." is shown