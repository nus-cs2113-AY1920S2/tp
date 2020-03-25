# MeetingOrganizer - Developer Guide
By: `AY1920S2-CS2113T-T12-1`

## Table of Contents
* [1. Setting up](#1-setting-up)
* [2. Design](#2-design)
	* [2.1. Architecture](#21-architecture)
	* [2.2. UI component](#22-ui-component)
	* [2.3. API system component](#23-api-system-component)
	* [2.4. Member component](#24-member-component)
	* [2.5. Meeting component](#25-meeting-component)
	* [2.6. Exception classes](#26-exception-classes)
	* [2.7. Common classes](#27-common-classes)
* [3. Implementation](#3-implementation)
* [4. Documentation](#4-documentation)
* [5. Testing](#5-testing)
* [6. Dev Ops](#6-dev-ops)
* [Appendix A: Product Scope](#appendix-a-product-scope)
	* [A.1. Target user profile](#a1-target-user-profile)
	* [A.2. Value proposition](#a2-value-proposition)
* [Appendix B: User Stories](#appendix-b-user-stories)
* [Appendix C: Use Cases](#appendix-c-use-cases)
* [Appendix D: Non-Functional Requirements](#appendix-d-non-functional-requirements)
* [Appendix E: Glossary](#appendix-e-glossary)
* [Appendix F: Product Survey](#appendix-f-product-survey)
* [Appendix G: Instructions for Manual Testing](#appendix-g-instructions-for-manual-testing)
	* [G.1. Launch and Shutdown](#g1-launch-and-shutdown)
	* [G.2. Saving data](#g2-saving-data)

## 1. Setting up

## 2. Design

### 2.1. Architecture

### 2.2. UI component

### 2.3. API system component
The The API system of our application consists of 4 classes: ```TimetableParser ModuleApiParser ModuleHandler LessonsGenerator```
<br>
1. ```TimetableParser``` makes use of regex to sift through timetable link given by a ```String``` object and stores
the semester and the user's module information according to the timetable link provided. It depends on the ```common.Messages``` class to provide the exception message when an incorrect link is being parsed.
2. ```ModuleApiParser``` uses a HTTP GET request to fetch a Json object from the open-sourced NUSMOD API server.
It takes in a ```String```object as module name, and returns a Json object of the module information from method ```.parse()```.

3. ```ModuleHandler```  
4. ```LessonsGenerator```

### 2.4. Member component

### 2.5. Meeting component
The Meeting component of our application consists of 2 classes: ```Meeting MeetingList```
<br>

### 2.6. Exception classes

### 2.7. Common classes


## 3. Implementation
This section describes some noteworthy details of how our application works in the backend.

## 4. Documentation

## 5. Testing

## 6. Dev Ops

## Appendix A: Product Scope
### A.1. Target user profile

{Describe the target user profile}

### A.2. Value proposition

{Describe the value proposition: what problem does it solve?}

## Appendix B: User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Appendix C: Use Cases

## Appendix D: Non-Functional Requirements

{Give non-functional requirements}

## Appendix E: Glossary

* *glossary item* - Definition

## Appendix F: Product Survey

## Appendix G: Instructions for Manual Testing

Given below are instructions to test the app manually.

> :information_source: These instructions only provide a starting point for testers to work on; testers are expected to do more _exploratory_ testing. 

### G.1. Launch and Shutdown

### G.2. Saving data