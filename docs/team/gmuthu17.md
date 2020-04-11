# Ganesh Muthu - Project Portfolio Page

### 1. Restaurant daily report
Restaurant Daily Report is a CLI app that generates a whitepaper, summarizing the internals of a restaurant. It’s aim is to provide restaurant owners a quick overview of how their restaurant is performing daily so that restaurant owners can better manage their business operations.

### 2. Summary of contributions

#### 2.1 Code contributed
You can view my code contribution [here](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#search=gmuthu17&sort=groupTitle&sortWithin=title&since=2020-03-01&timeframe=commit&mergegroup=false&groupSelect=groupByRepos&breakdown=false)

#### 2.2 Enhancements implemented

Add dish
1. Basic add dish functionality.
2. Informs user if they attempt to add a dish that already exists. Custom message: `Dish [dish name] already exists!`
3. Informs the user if dish name, ingredients, or price is missing.
4. Informs the user if inputted price was not a number.
5. Allows flexible ordering of parameters. Examples: 
    + add dish; n/NAME; i/[INGREDIENT 1], [INGREDIENT 2],...]; p/PRICE;
    + add dish; i/[INGREDIENT 1], [INGREDIENT 2],...]; p/PRICE; n/NAME;
6. Custom dish added message. Example: `add dish; n/pizza; i/cheese, sauce, bread; p/6.00` Message: `Dish pizza successfully added.`

Delete dish
1. Basic delete menu item functionality.
2. Informs user if they attempt to delete a menu item that does not exist. Custom message: `Dish [dish name] does not exist!`
3. Informs user if they attempt to delete dish when menu is empty or if item name is missing.
4. Custom dish deleted message. Example: `delete dish; n/pizza;` Message: `Dish pizza successfully removed!`

List menu item
1. Basic list menu item functionality.
2. Informs user if there are no items to list.

Search menu item
1. Basic search menu item functionality.
2. Informs user if no items match the search. Custom message: `There are no dishes that match the keyword [keyword]!`
3. Informs user if they attempt to search for a menu item when menu is empty.
4. Informs the user if keyword is missing.

Dish profit
1. Basic dish profit functionality.
2. Throws error if calculation is attempted for dish made of ingredients not listed in stock.
3. Throws an error if calculation is attempted for dish made of ingredients with zero stock.
4. Throws an error if calculation is attempted for a dish that would yield negative profit.
    
Load dish
1. Load dish input from saved file.

#### 2.3 Developer guide
1. Implementation and design considerations of search dish feature.

#### 2.4 User guide
1. Updated user guide for all dish related features.

#### 2.5 Team tasks
1. Contributed to brainstorming of project ideas and creation of user stories.
2. Used issue tracker to resolve issues and allocate work.