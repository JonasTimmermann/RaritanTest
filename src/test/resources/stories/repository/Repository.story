An author and book repository

Narrative:
In order to persists authors and books
As a user 
I want to have an author and book repository 


Scenario: Books of an author are persisted with the author

Given an author
When author is persisted
Then books are persisted with author