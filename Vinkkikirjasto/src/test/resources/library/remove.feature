Feature: As a user I want to be able to remove a book or an article from library

    Scenario: Remove a non-existing book
        Given book "kirja" with author "kirjoittaja" and pages "100" is saved
        And command poista is selected
        When  incorrect book title is given
        And I don't want to try again
        Then  error message "Virhe. Tarkista, että kirjoitit nimen oikein." is shown

    Scenario: Remove an existing book
        Given book "kirja" with author "kirjoittaja" and pages "200" is saved
        And command poista is selected
        When correct book title is given
        Then book is removed from library

    Scenario: Remove an existing article
        Given article "artikkeli" with url "www.testi.com" is saved
        And command poista is selected
        When correct article title is given
        Then article is removed from library

    Scenario: Remove a non-existing article
        Given article "artikkeli" with url "www.testi.com" is saved
        And command poista is selected
        When incorrect article title is given
        And I don't want to try again
        Then error message "Virhe. Tarkista, että kirjoitit otsikon oikein." is shown

    Scenario: Removing a correct book after giving wrong title
        Given book "kirja" with author "kirjoittaja" and pages "100" is saved
        And command poista is selected
        When incorrect book title is given
        And I want to try again
        And correct book title is given
        Then error message "Virhe. Tarkista, että kirjoitit nimen oikein." is shown
        And book is removed from library

    Scenario: Removing a correct article after giving wrong title
        Given article "artikkeli" with url "www.testi.com" is saved
        And command poista is selected
        When incorrect article title is given
        And I want to try again
        And correct article title is given
        Then error message "Virhe. Tarkista, että kirjoitit otsikon oikein." is shown
        And article is removed from library