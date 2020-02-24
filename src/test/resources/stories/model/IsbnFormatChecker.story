A ISBN Format checker

Narrative:
In order to have a uniform presentation of ISBN numbers
As a user 
I want to have a formatter for ISBN numbers 


Scenario: An good ISBN number is formatted correctly

Given an ISBN format checker
When ISBN "123-4-567-89012-8" is formatted
Then ISBN equals "1234567890128"


Scenario: An bad ISBN number is formatted correctly

Given an ISBN format checker
When ISBN "123-4-567-89012-8-x" is formatted
Then ISBN is empty


Scenario: An empty ISBN number is formatted correctly

Given an ISBN format checker
When empty ISBN is formatted
Then ISBN is empty


Scenario: A already correctly formatted ISBN is still formatted correctly

Given an ISBN format checker
When ISBN "1234567890128" is formatted
Then ISBN equals "1234567890128"