Feature: As a user I want to be able to update information on reading suggestions

    Scenario: update amount of pages read on a book
        Given book "kirja" with author "kirjoittaja" and pages "100" is saved
        When  name of the book "kirja" and amount of pages read "50" is given
        Then  pages read is updated with the new value
