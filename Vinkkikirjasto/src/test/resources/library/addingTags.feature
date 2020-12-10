Feature: Tags can be added to suggestions

    Scenario: Tags can be added to books when adding a new suggestion and tags
        Given command uusi is selected
        When correct book information is given
        When tags "tag1", "tag2" and "tag3" are added
        Then suggestion has tags "tag1", "tag2" and "tag3"
        
    Scenario: Tags can be added to articles when adding a new suggestion
        Given command uusi is selected
        When correct article information is given
        When tags "tag1", "tag2" and "tag3" are added
        Then suggestion has tags "tag1", "tag2" and "tag3"

    Scenario: When tags are added, duplicate tags are ignored
        Given command uusi is selected
        When correct book information is given
        When tags "duplicate", "unique" and "duplicate" are added
        Then suggestion has no duplicate tags