# Chang Hui Zhen Project Portfolio

### Overview 
Kitchen Helper is a command-line application that was developed by a team of 6 Information Security students.
We aim to help housewives with the management of their kitchen-related activities. 
This application enable users to keep track of their ingredients as well as the chores to be completed.

### Contributions
#### Code Contributions 
[Code contribution dashboard](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#search=alwayshuizhen)

#### Enhancements implemented
1. Major
   1. Adding of recipe
     * Functionality: This is a representation of a collection of ingredients that are commonly cooked together. 
     * Justification: With this functionality, users will be able to check for the ingredients that are used for a certain dish and would be available for future reference.
    
     * Highlight: Users would not need to worry that their recipe or ingredient name has to be restricted (i.e. can contain whitespace, any category)
  1. Cooking a recipe (Logical checks before deduction)
     * Functionality: This is a batch deduction of ingredients that are found in the recipe specified. Before deduction, a series of checks were performed. If all the checks passed, the automatic deducted will be proceeded, an error message otherwise.
     * Justification: This feature ensure that the user will be notified when there is an expired ingredient before proceeding with the cooking of the recipe. This is important as the user might not have noticed that there is insufficient non-expired ingredient.
     * Highlight: The user is notified with specific error messages on several situations including sufficienct ingredients, insufficient ingredients and when there is sufficienct ingredients that included expired ingredients.
  1. Resetting the application
     * Functionality: All application data will be wiped and restarted from fresh.
     * Justification: User can choose to clear the inventory without having to run delete each of the recipes/ ingredients/ chores themselve.
     * Highlight: This provides automatic clearance to the application.
    
#### Documentation

* User Guide
  * Sections: 3.1.3. Resetting the application: `reset`, 3.4.1. Adding a recipe `addrecipe`, 3.4.5. Cooking a recipe `cookrecipe`, 
* Developer Guide
  * Sections: 1.1 Background, 3.2. Ui Component, 3.6. Common Classes, 4.2.1 Addition of Recipe, 4.2.3. Cooking a recipe (Design Considerations), 4.6. Logging, Apppendix D & E, Appendix F.6., F.8.


#### Team-based tasks

* Setting up the team repo
* Release Management: Managed `v1.0` to `v2.1` releases
* General code enhancements: adding missing JavaDocs, remove dead codes
* Integration of code snippets and maintaining repository

#### Review/mentoring contributions

* Pull Requests reviewed (with non-trivial comments): [#41](https://github.com/AY1920S2-CS2113T-M16-2/tp/pull/41), 
[#118](https://github.com/AY1920S2-CS2113T-M16-2/tp/pull/118),
[#122](https://github.com/AY1920S2-CS2113T-M16-2/tp/pull/122),
[#124](https://github.com/AY1920S2-CS2113T-M16-2/tp/pull/124),
[#125](https://github.com/AY1920S2-CS2113T-M16-2/tp/pull/125),
[#137](https://github.com/AY1920S2-CS2113T-M16-2/tp/pull/137),
[#141](https://github.com/AY1920S2-CS2113T-M16-2/tp/pull/141),
[#153](https://github.com/AY1920S2-CS2113T-M16-2/tp/pull/153),
[#156](https://github.com/AY1920S2-CS2113T-M16-2/tp/pull/156),
[#157](https://github.com/AY1920S2-CS2113T-M16-2/tp/pull/157),
[#165](https://github.com/AY1920S2-CS2113T-M16-2/tp/pull/165),
[#243](https://github.com/AY1920S2-CS2113T-M16-2/tp/pull/243),
[#246](https://github.com/AY1920S2-CS2113T-M16-2/tp/pull/246),
[#247](https://github.com/AY1920S2-CS2113T-M16-2/tp/pull/247),
[#254](https://github.com/AY1920S2-CS2113T-M16-2/tp/pull/254)

* Reported Bugs: [#1](https://github.com/alwayshuizhen/ped/issues/1), [#2](https://github.com/alwayshuizhen/ped/issues/2),
[#3](https://github.com/alwayshuizhen/ped/issues/3), [#4](https://github.com/alwayshuizhen/ped/issues/4), 
[#5](https://github.com/alwayshuizhen/ped/issues/5), [#6](https://github.com/alwayshuizhen/ped/issues/6),
[#7](https://github.com/alwayshuizhen/ped/issues/7), [#8](https://github.com/alwayshuizhen/ped/issues/8),
[#9](https://github.com/alwayshuizhen/ped/issues/9), [#10](https://github.com/alwayshuizhen/ped/issues/10),
[#11](https://github.com/alwayshuizhen/ped/issues/11), [#12](https://github.com/alwayshuizhen/ped/issues/12)

### Contributions to the User Guide

* [Resetting application](https://ay1920s2-cs2113t-m16-2.github.io/tp/UserGuide.html#313-resetting-the-application-reset)
* [Adding a recipe](https://ay1920s2-cs2113t-m16-2.github.io/tp/UserGuide.html#341-adding-a-recipe-addrecipe-hui-zhen)
* [Cooking a recipe](https://ay1920s2-cs2113t-m16-2.github.io/tp/UserGuide.html#345-cooking-a-recipe-cookrecipe-hui-zhen-and-yan-ting)

### Contributions to the Developer Guide 

* [Background](https://ay1920s2-cs2113t-m16-2.github.io/tp/DeveloperGuide.html#11--background)
* [Ui Component](https://ay1920s2-cs2113t-m16-2.github.io/tp/DeveloperGuide.html#32-ui-component)
* [Common Classes](https://ay1920s2-cs2113t-m16-2.github.io/tp/DeveloperGuide.html#36-common-classes)
* [Adding a recipe](https://ay1920s2-cs2113t-m16-2.github.io/tp/DeveloperGuide.html#421-addition-of-recipe)
* [Cooking a recipe design considerations](https://ay1920s2-cs2113t-m16-2.github.io/tp/DeveloperGuide.html#423-cooking-of-recipe)
* [Logging](https://ay1920s2-cs2113t-m16-2.github.io/tp/DeveloperGuide.html#46-logging)
* [Appendix E](https://ay1920s2-cs2113t-m16-2.github.io/tp/DeveloperGuide.html#appendix-e-glossary)
* [Appendix F.6](https://ay1920s2-cs2113t-m16-2.github.io/tp/DeveloperGuide.html#f6-add-a-recipe)
* [Appendix F.8](https://ay1920s2-cs2113t-m16-2.github.io/tp/DeveloperGuide.html#f8-cook-a-recipe)
