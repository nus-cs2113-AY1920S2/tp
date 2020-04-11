# Ned Ning Guan - Project Portfolio Page

## Overview
The Restaurant Daily Report is a CLI application is designed for restaurant owners, who need a simple and efficient way to manage the operation of their business.
Ideally, the owner would be proficient at using desktop apps and is a quick typer.

## Summary of Contributions
### Code Contributed
You can view my code contribution [here](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#breakdown=true&search=nguan1)

### Enhancements implemented
#### Command Parser
* Ensures that each command is passed through correctly and calls the correct command to be executed
* Provides an error when users enter the incorrect command

#### Report Writer
* Writes the dishes, stock and reservations to 'report.txt'

#### Sales class

##### Add sales
* Adds a dish that has been sold
* Includes error handling if the dish does not exist, command has incorrect formatting or if quantity is not an integer
* Informs the user if the dish has been successfully added or if there was an error

##### Calculate Profit
* Calculates the total profit of all the dishes sold
* Includes error handling if stock doesn't exist or the profit is negative
* Prints out the total profit for the day

##### Find most popular dish
* Determines the most popular dish out of all sold items
* Includes error handling if there are no dishes
* Prints out the most popular dish along with its sales

### Developer Guide
* Implementation and write up of generate profit of the day feature
* Product Scope Section
* Non-reservation related user stories
* Sales functionality for Appendix E

### User Guide
* Updated user guide with all sales features

### Team Contributions
* Actively participated in team discussion and brainstorming of ideas
* Assisted in troubleshooting issues



