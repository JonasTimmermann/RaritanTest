A ISBN Format checker

Narrative:
As a user 
In order to have a uniform presentation of ISBN numbers
I want to have a formatter for ISBN numbers 


Scenario: A given ISBN is formatted correctly, here defined by examples

Given an ISBN format checker
When ISBN "<isbn>" is formatted
Then ISBN equals "<formatted_isbn>"


Examples:     
| isbn                | formatted_isbn 
| 123-4-567-89012-8   | 1234567890128 
| 842-2-543-84931-1   | 8422543849311 
| 842-2-543-84932-8   | 8422543849328 
| 521-7-123-01732-6   | 5217123017326 
|                     |
| 456                 |
| 123-4-567-89012-x   |
| abc-4-567-89012-3   |
| 12-34-567-89012-8   |
| 521-7-123-0173-0    | 
