Feature: As a user I want o be able to add and remove tags to existing suggestions

    Scenario: I can add a new tag to an existing book
        Given book Margarita with tag finlandia is saved
        When tag "kaunokirjallisuus" is added to "kirja" named "Margarita"
        Then new tag "kaunokirjallisuus" is added

    Scenario: I can add a new tag to an existing article
        Given article Finlandia-hymni is saved
        When tag "musiikki" is added to "artikkeli" named "Finlandia (Youtube)"
        Then new tag "musiikki" is added

    Scenario: I can remove an existing tag from an existing book
        Given book Margarita with tag finlandia is saved
        When tag "finlandia" is removed from "kirja" named "Margarita"
        Then tag "finlandia" is deleted

    Scenario: I can remove an existing tag from an existing article
        Given article Ohtumateriaali with tag ohjelmistotuotanto is saved
        When tag "ohjelmistotuotanto" is removed from "artikkeli" named "Ohtumateriaali" 
        Then tag "ohjelmistotuotanto" is deleted

    Scenario: When I try add tag to nonexistent suggestion, I get error message
        When tag "tag" is added to "kirja" named "nonexistent"
        And I don't want to try again 
        Then error message "Virhe. Tarkista, että kirjoitit nimen oikein." is shown

    Scenario: When I try to add a tag that's there already, it isn't duplicated
        Given book Margarita with tag finlandia is saved
        When tag "finlandia" is added to "kirja" named "Margarita"
        Then suggestion has no duplicate tags