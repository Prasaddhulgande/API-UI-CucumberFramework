Feature: API Automation

@GetAPI
Scenario Outline: <vTCName>
Given endpoint url 
When user call get method
Then user can see the response
And Status line should be matched
And validate response data
Examples:
|vTCName|
|TC01_validate Get API List Users page 2|
|TC02_validate Get API List Users page 1|
|TC03_validate Get API List Users|

@postcall
Scenario: TC04_Creat User
Given endpoint url 
When user create the request and call the request method
Then user can see the response
And Status line should be matched
And validate response data

@postcall
Scenario: TC05_Update User
Given endpoint url 
When user create the request and call the request method
Then user can see the response
And Status line should be matched
And validate response data

@postcall
Scenario: TC06_UpdatePatch User
Given endpoint url 
When user create the request and call the request method
Then user can see the response
And Status line should be matched
And validate response data

