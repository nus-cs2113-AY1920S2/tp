# Developer Guide

## Design & Implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Storage feature
The Storage and Storage Handler features handle the data back-end of the application. Storage Handler provides functions that can be used to manipulate the data file. Additionally, Storage supports multiple data files at the same time, allowing implementation of features like multiple sessions and multiple user profiles.

Jikan uses a `.csv` file to store its data, formatted in the following way:

`entry-name, start-time, end-time, tags`

All tags are saved in the same cell, separated by a white space; this design decision was taken to make sure that each entry occupies the same number of cells regardless of each entry’s number of tags. The tags are then separately parsed when the data is loaded.
Storage is the main interface between the application and the data file. Each Storage objects contains the path to the data file (`Storage.dataFilePath`), the File object representing the data file (`Storage.dataFile`), and an activityList populated with the data from the data file (`Storage.activityList`). Additionally, it provides functions that provide high-level services such as:
- Constructing a Storage object via `Storage(String dataFilePath)`, which takes in the path to the desired data file (or the path where the user wants to create the data file) as a String object.
- Creating a data file via `createDataFile`
- Writing to a data file via `writeToFile`. This function takes a single string as parameter and writes it to the data file. It is recommended to only pass single-line strings to keep the file nicely formatted. 
Loading a pre-existing data file via `loadFile`. If a data file already exists for the provided data file path, the function will return `true`; if the specified data file did not previously exist, this function will call the `createDataFile` method and returns `false`. The return value is useful so that the application knows whether or not this is the first session with a specific data file or if data already exists.
- Creating an ActivityList via `createActivityList`. This function calls `loadFile()` to check whether the file already existed or not; if the data file previously existed, it will construct an ActivityList object by passing the data from the data file to it, and return this populated ActivityList object; if the data file did not previously exist, it will return an empty activityList object.

### Storage handler
The storage handler provides features so that the main application can interact with the stored data file. The main features are:
- Removing an entry from the data file via `removeLine`. This function takes in the number of the line to remove.
- Replacing an entry in the data file via `replaceLine`. This function takes in the number of the line to replace, along with the String object that needs to be written to the data file in place of the replaced line.



## Product Scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for Manual Testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
