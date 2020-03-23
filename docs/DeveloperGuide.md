# Developer Guide

By: `CS2113T-T12-2` Since: `2020`


## Table of Contents


1. [Introduction](#introduction)
2. [Setting up](#setting-up)
3. [Design](#design)
4. [Implementation](#implementation)

    4.1. User Prompting feature
          
      - 4.1.1. Description
      
      - 4.1.2. Implementation
      
      - 4.1.3. Design Considerations
    
    4.2. Appointment Scheduling
    
      - 4.2.1. Description
      
      - 4.2.2. Implementation 
      
      - 4.2.3. Design Consideration 
    
    4.3.    
5. [Documentation](#documentation)
6. [Testing](#testing)
7. [DevOps](#devops)
[Appendix A: Product Scope](#)
[Appendix B: User Stories](#)





## Design & Implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

###4.2. Appointment Scheduling 
    
####4.2.1 Description

Users are able to schedule appointments made by their patients on the program, ensuring that the 
appointments do not clash and are within the opening hours of the clinic. 

####4.2.2 Implementation 
   
Users can manage their appointment schedule using these commands:
   
   - `add appt d/[DATE] t/[TIME] ic/[NRIC] r/[REASON]`
   
     - The appointment object takes in the date and time of the appointment as well as the patient appointment is 
     scheduled for and the reason for the appointment. This appointment must be tied to a patient that exists 
     within the program. The program will display the appointment ID that is assigned to the appointment should 
     the adding of appointment be successful.
     
   - `find appt [NRIC]`
   
     - Users can easily find all the appointments tied to a certain patient within the program. This command will 
     list down all the details of the appointment, along with the appointment ID that was assigned to the appointment 
     when it was created. 
     
   - `edit [APPT ID] d/[NEW DATE] t/[NEW TIME] r/[NEW REASON]`
   
     - If the user made a mistake while adding an appointment, it is possible to edit the appointment details via 
     the ID of the appointment.
     
     - Users are able to update the date, time and reason of the existing appointment but the parameters must be 
     used in the given order. 
     
   - `done [APPT ID]`
   
     - An appointment can be marked as done after the patient has showed up for the appointment.
     
   - `list appt [START DATE] - [END DATE]` 
   
     - Users can list all the appointments that has been scheduled within the program.
     
     - [OPTIONAL] Users can include the range of dates of the appointments that is to be listed down.

####4.2.3 Design Consideration
   
*Aspect: Storing of Appointment Schedule*
   
   - **Alternative 1: Use of ArrayList of Appointments**
   
Pros: 

Cons: 
   
   - **Alternative 2: Use of ArrayList of Appointment Date/Time and Hashmap of Appointments**  

Pros:

Cons:

## Appendix A: Product Scope

Our product is targeted at users who:
  
   - has a need to record significant number of patients' information
   
   - want to keep patients' information organised
   
   - prefere desktop apps over other types
   
   - can type fast
   
   - prefer typing over mouse input
   
   - prefer CLI apps over GUI apps
  
Value proposition: Note taking application built for doctors to manage notes faster than a typical mouse/GUI driven app

## Appendix B: User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|Doctor|add Patient's details|view their information in their subsequent visits.|
|v1.0|Doctor|have a quick overview of a list of all patients|check for their detailed information.|
|v1.0|Doctor|retrieve my patient's detailed information|check for his/her allergies and provide a more accurate diagnosis|
|v1.0|Doctor|edit a particular patient's information|the most up-to-date details in our patient records|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

1. Should work on any mainstream OS as long as it has Java 11 or above installed.

2. A user with above average typing speed should be able to accomplish most of the tasks faster using commands than using the mouse.

3. Should be able to hold up to 1000??? patients' information without a noticeable sluggishness in performance for typical usage.

4. Should be able to display large amount of inforamtion quickly. 


## Glossary

*Mainstream OS*

Windows, Linux, Unix, OS-X

## Instructions for Manual Testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
