Feature: Reading progress for books is displayed as red for not started, yellow for in-progress and green for completed

    Scenario: An unstarted book displays read pages as red
        Given book "kirja" with author "kirjoittaja" and pages "100" is saved
        And command listaa is selected
        Then number of pages read are displayed in red

    Scenario: A started book displays read pages as yellow
        Given book "kirja" with author "kirjoittaja" and pages "100" is saved
        When  name of the book "kirja" and amount of pages read "50" is given
        And command listaa is selected
        Then number of pages read are displayed in yellow

    Scenario: A finished book displays read pages as green
        Given book "uusi" with author "kirjoittaja" and pages "100" is saved
        When  name of the book "uusi" and amount of pages read "100" is given
        And command listaa is selected
        Then number of pages read are displayed in green
