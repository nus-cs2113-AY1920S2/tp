# Developer Guide

## Design & Implementation
1. Architecture
The Duke class is the main class of the product.It is responsible for,

	At app launch: Initializes the components in the correct sequence, and connects them up with each other.

	At shut down: Shuts down the components and invokes storage method where necessary.	

The product also contains the following components as well:
cards: Holds the data in the type of Card and relative operations
subjects: Holds the data in the type of Subject and relative operations
score: Holds the data in the type of Score and relative operations
commands: Deals with user input and communicate CLI to relative methods
exceptions: Deals with illegal inputs
parser: Convert CLI inputs into command keywords

In these components, cards and subjects have similar structure. Both of them contains a Card/Subject class and CardList/SubjectList class. 





## Product Scope
### Target user profile
The product is intended for students preparing for exams. Students can store practice question and model answers in the product.
Also, the product offers students to quiz themselves to practice for exams

### Value proposition
The product aims to provide students with more convient way of doing revision. By using the product students can categorize questions into different subjects and practice more effectively.

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|user|add new cards|view cards and answers to revise|
|v1.0|user|delete cards|organize cards better|
|v1.0|user|quiz myself|practice the questions|
|v1.0|user|list cards|organize the cards|
|v2.0|user|add cards with subjects|categorize the cards better|
|v2.0|user|list cards by subjects|organize the cards|
|v2.0|user|delete subjects|organize subjects better|
|v2.0|user|view my score|see how I performed|
|v2.0|user|view my test history|see how I performed|

## Non-Functional Requirements
<br />1.The product should be able to run on any platform that has JDK11
<br />2.The product should be able to hold up to 1000 cards

## Glossary

* *glossary item* - Definition

## Instructions for Manual Testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
