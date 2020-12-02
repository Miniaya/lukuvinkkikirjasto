Feature: An article suggestion can be added to the library

    Scenario: An article can be added when correct information is given
        Given command uusi is selected
        When  correct article information is given
        Then  article is saved to library
