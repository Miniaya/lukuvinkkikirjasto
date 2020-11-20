   Feature: A book suggestion can be added to the library

    Scenario: A book can be added when correct information is given
        Given command uusi is selected
        When  correct book information is given
        Then  book is saved to library
    
     