# Tan Kok Joon - Project Portfolio Page

## PROJECT: SHOCO v2.1

## Overview

SHOCO is a command-line interface (CLI) application written in Java that is used for managing and planning shopping lists
and budgets, mainly targeting the inconveniences of unplanned grocery shopping.

## Summary of Contributions

- Major enhancements:
   1. Added the delete functionality
      
      - What it does: Allows the user to remove an existing item from the shopping list.
      - Justification: This feature makes the application more complete as the user might have added an item to the shopping
      list that he now feels is no longer required and the application should allow the user to rectify it easily, by simply
      providing the index of the item in the shopping list.
      - Highlights: To properly implement this feature, much consideration had to be given to the various types of invalid user input that should be 
      rejected and handled appropriately by the application. These included invalid indices like negative numbers or non-numerical input.

   2. Added the set budget functionality
     
      - What it does: Allows the user to specify a budget amount for his shopping list.
      - Justification: This is a key feature of the application as one of the key objectives of the product is to help the user stay within budget when adding items
      to his shopping list.
      - Highlights: Careful consideration had to be given as to when to display error messages to the user so as not to restrict the user's freedom, such as when he keys in a negative
      amount for the budget which is definitely not an acceptable value.
      
   3. Added the search functionality
     
      - What it does: Allows the user to find items in a long list by specifying keywords.
      - Justification: This is a feature that will really improve user experience as it can be tedious to try to find something manually in a
      long list.
      - Highlights: To further improve user accessibility, a minor improvement that was implemented was to allow for case-insensitivity for the
      keywords. This would mean the user does not have to remember if the description of the item he added was in lowercase or uppercase.

- Minor enhancements: Added a feature to display a warning message to the user if the total cost of the items in his list has exceeded
his budget
     
- Code contributed: [[Functional and test code](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#search=kokjoon97&sort=groupTitle&sortWithin=title&since=2020-03-01&timeframe=commit&mergegroup=false&groupSelect=groupByRepos&breakdown=false)]

- Other contributions:

  - Documentation:
  
    - Wrote the sections outlining how to use the find, delete and set budget features (Pull request [#107](https://github.com/AY1920S2-CS2113T-T13-1/tp/pull/107))
    - Wrote the introduction to describe the product and the benefits it can bring to potential users (Pull request [#132](https://github.com/AY1920S2-CS2113T-T13-1/tp/pull/132))
    - Added more specific instructions on how to run the application (Pull request [#132](https://github.com/AY1920S2-CS2113T-T13-1/tp/pull/132))
    - Wrote additional information on loading and saving of data and warning message displayed when exceeding budget (Pull request [#132](https://github.com/AY1920S2-CS2113T-T13-1/tp/pull/132))
    - Added an FAQ on restoring lost data (Pull request [#132](https://github.com/AY1920S2-CS2113T-T13-1/tp/pull/132))

  - Developer Guide:
  
    - Wrote appendices A and C on product scope and non-functional requirements (Pull request [#93](https://github.com/AY1920S2-CS2113T-T13-1/tp/pull/93))
    - Added a description of the class diagram to explain the main components of the application (Pull request [#165](https://github.com/AY1920S2-CS2113T-T13-1/tp/pull/165/files))
    - Wrote the sections outlining the interactions between different classes for the find, delete and set budget features, including design considerations for each feature (Pull request [#165](https://github.com/AY1920S2-CS2113T-T13-1/tp/pull/165/files))
    - Added sequence diagrams for the find, delete and set budget features (Pull request [#165](https://github.com/AY1920S2-CS2113T-T13-1/tp/pull/165/files))
    
  - Team-based tasks:
  
    - Managed release `v1.0` on Github
    - Resolved CheckStyle violations in some parts of the code (Pull request [#44](https://github.com/AY1920S2-CS2113T-T13-1/tp/pull/44))
    - Fixed general issue with reading of multiple lines of user input (Pull request[#66](https://github.com/AY1920S2-CS2113T-T13-1/tp/pull/66))
    - Fixed major issue with failing of CI tests by making changes to runtest file (Pull request[#68](https://github.com/AY1920S2-CS2113T-T13-1/tp/pull/68))
    - Set up a Logger object with the appropriate file and console handlers (Pull request[#70](https://github.com/AY1920S2-CS2113T-T13-1/tp/pull/70))
    
  - Review/mentoring contributions:
   
    - PRs reviewed (with changes suggested): [#35](https://github.com/AY1920S2-CS2113T-T13-1/tp/pull/35), [#38](https://github.com/AY1920S2-CS2113T-T13-1/tp/pull/38), [#39](https://github.com/AY1920S2-CS2113T-T13-1/tp/pull/39), [#40](https://github.com/AY1920S2-CS2113T-T13-1/tp/pull/40), [#42](https://github.com/AY1920S2-CS2113T-T13-1/tp/pull/42) ,[#139](https://github.com/AY1920S2-CS2113T-T13-1/tp/pull/139), [#163](https://github.com/AY1920S2-CS2113T-T13-1/tp/pull/163)

  - Beyond the project team:
  
    - Reported bugs and suggestions for other project teams: [Inaccurate documentation](https://github.com/kokjoon97/ped/issues/4), [broken link in UG](https://github.com/kokjoon97/ped/issues/13), [duplication bug](https://github.com/kokjoon97/ped/issues/5), [saving issue](https://github.com/kokjoon97/ped/issues/12)
    
