# Lim Yan Ting Project Portfolio

### Overview - Kitchen Helper
Kitchen Helper is a command line interface (CLI) application that is mainly targeted for household users who have difficulty in remembering what they have in their kitchen. This application aids them in the tracking of ingredients that are currently in their kitchen more efficiently.

### Contributions
+ Responsibility: Issue Tracker

#### Enhancements implemented
+ Deletion of ingredients and recipes
    + What it does: It allows the user to delete any ingredient or recipe with regards to the list of ingredients or recipes in Kitchen Helper respectively. 
    + Justification: This feature improves the product slightly because a user may make a mistake and input the wrong parameters for an ingredient or a recipe. With this feature, the user will be able to delete this wrongly keyed ingredient or recipe. 
    + Highlights: This enhancement was done by deleting the ingredient based on their index so that the users can delete a specific ingredient that they want. However, for deletion of recipe, there is an additional enhancement of deletion by name as the recipe names are unique so the users can delete a specific recipe by deleting through the names.
+ Deduction of quantity of ingredients 
    + What it does: It allows the user to reduce the quantity of an ingredient.
    + Justification: This feature improves the product slightly because a user may just want to utilize a few quantities of the ingredient without cooking the recipe. Example, if a user wants to just eat an apple, the user can use this feature to deduct the quantity of apple by 1.
    + Highlights: This enhancement deletes the ingredient automatically if the final quantity after the deduction is zero for the ingredient. 
+ Added the <b>deduction of ingredients from list of ingredients</b> after a user has cooked a recipe
    + What it does: It automatically deducts the ingredients found in the recipe that the user has specified to cook if there are enough and non-expiring ingredients in the list of ingredients. 
    + Justification: This feature improves the product slightly because a user does not have to manually deduct the quantity of ingredients which increases convenience for the users in managing their ingredients’ inventory. 
    + Highlights: Similar to <b>Deduction of quantity of ingredients</b>

#### Code Contributions 
The link below showed my code contributions to Kitchen Helper:

[Functional and Testing Code](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#breakdown=true&search=yantingsanity&sort=groupTitle&sortWithin=title&since=2020-03-01&timeframe=commit&mergegroup=false&groupSelect=groupByRepos&tabOpen=true&tabType=authorship&tabAuthor=yantingsanity&tabRepo=AY1920S2-CS2113T-M16-2%2Ftp%5Bmaster%5D)

#### Other Contributions 
+ Project Management:
    + Managed the Issue Tracker and Milestones by creating and closing unnecessary issues to track the members' progress for Kitchen Helper with respect to User Stories and bugs
    + Helped to merge and review PR to maintain the repository

+ Documentation:
    + Added the architecture diagram and use cases of above implementation in Developer Guide
    + Added the documentation for DeleteIngredientCommand, DeleteRecipeCommand and CookRecipeCommand for both User Guide and Developer Guide 
    + Added the introduction and scope in Developer Guide
    
+ Community
    + PR reviewed (with non-trivial review comments): [#38](https://github.com/AY1920S2-CS2113T-M16-2/tp/pull/38), [#93](https://github.com/AY1920S2-CS2113T-M16-2/tp/pull/93), [#109](https://github.com/AY1920S2-CS2113T-M16-2/tp/pull/109), [#123](https://github.com/AY1920S2-CS2113T-M16-2/tp/pull/123), [#152](https://github.com/AY1920S2-CS2113T-M16-2/tp/pull/152), [#226](https://github.com/AY1920S2-CS2113T-M16-2/tp/pull/226), [#227](https://github.com/AY1920S2-CS2113T-M16-2/tp/pull/227), [#240](https://github.com/AY1920S2-CS2113T-M16-2/tp/pull/240), [#242](https://github.com/AY1920S2-CS2113T-M16-2/tp/pull/242), [#253](https://github.com/AY1920S2-CS2113T-M16-2/tp/pull/253), [#285](https://github.com/AY1920S2-CS2113T-M16-2/tp/pull/285)
    + Reported bugs and suggestions for other teams in class (examples: [#1](https://github.com/yantingsanity/ped/issues/1), [#2](https://github.com/yantingsanity/ped/issues/2), [#3](https://github.com/yantingsanity/ped/issues/3), [#4](https://github.com/yantingsanity/ped/issues/4), [#5](https://github.com/yantingsanity/ped/issues/5), [#6](https://github.com/yantingsanity/ped/issues/6), [#7](https://github.com/yantingsanity/ped/issues/7), [#8](https://github.com/yantingsanity/ped/issues/8), [#9](https://github.com/yantingsanity/ped/issues/9))
    + Did some testing and found bugs in teammates' codes 

#### Documentation
### Contributions to the User Guide
> Given below are sections I contributed to the User Guide. They showcase my ability to write documentation targeting end-users.

+ [Delete Ingredient](https://ay1920s2-cs2113t-m16-2.github.io/tp/UserGuide.html#333-delete-an-ingredient-deleteingredient-isabella-and-yan-ting)
+ [Delete Recipe](https://ay1920s2-cs2113t-m16-2.github.io/tp/UserGuide.html#343-delete-a-recipe-deleterecipe-yan-ting)
+ [Cook Recipe](https://ay1920s2-cs2113t-m16-2.github.io/tp/UserGuide.html#345-cooking-a-recipe-cookrecipe-hui-zhen-and-yan-ting) 
    
### Contributions to the Developer Guide 
> Given below are sections I contributed to the Developer Guide. They showcase my ability to write technical documentation and the technical depth of my contributions to the project.

+ [Architecture](https://ay1920s2-cs2113t-m16-2.github.io/tp/DeveloperGuide.html#31-architecture)
+ [Delete Ingredient](https://ay1920s2-cs2113t-m16-2.github.io/tp/DeveloperGuide.html#413-delete-specific-ingredientss)
+ [Delete Recipe](https://ay1920s2-cs2113t-m16-2.github.io/tp/DeveloperGuide.html#424-delete-a-specific-recipe)
+ [Cook Recipe](https://ay1920s2-cs2113t-m16-2.github.io/tp/DeveloperGuide.html#423-cooking-of-recipe)
+ Instructions for Manual Testing: [#Delete Ingredient](https://ay1920s2-cs2113t-m16-2.github.io/tp/DeveloperGuide.html#f4-delete-an-ingredient), [#Delete Recipe](https://ay1920s2-cs2113t-m16-2.github.io/tp/DeveloperGuide.html#f9-delete-a-recipe)
