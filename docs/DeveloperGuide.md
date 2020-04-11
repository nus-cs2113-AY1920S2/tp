# Developer Guide
**By:** Team T14-4<br/>
**Since:** May 2020

* [1. Design & Implementation](#design-implementation)
    + [1.1. Search stock feature](#search-stock)
    + [1.2. List stock in descending quantities](#list-stock)
    + [1.3. Generate profit for the day](#generate-profit)
    + [1.4. Search reservation](#search-reservation)
    + [1.5. Search dish](#search-dish)
* [Appendix A: Product Scope](#product-scope)
* [Appendix B: User Stories](#user-stories)
* [Appendix C: Non-Functional Requirements](#nonfunctional-requirement)
* [Appendix D: Glossary](#glossary)
* [Appendix E: Instructions for Manual Testing](#manual-test)
    + [E.1. Launch and Shutdown](#e1-launch-and-shutdown)
    + [E.2. Add functionality](#e2-add-functionality) 
    + [E.3. Delete functionality](#e3-delete-functionality)
    + [E.4. Search functionality](#e4-search-functionality)
    + [E.5. Listing functionality](#e5-list-functionality)
    + [E.6. Clear functionality](#e6-clear-functionality)


<a name="design-implementation"></a>

## 1. Design & Implementation

<a name="search-stock"></a>

### 1.1 Search stock feature
#### 1.1.1 Proposed implementation

In the restaurant daily report, users can search against the stock category by supplying a keyword.

Given below is an example usage scenario and how the search mechanism behaves at each step.

Step 1. The user launches the application for the first time. An empty `stock` will be initialized.

Step 2. The user executes `add stock; i/tomato; q/10; p/0.40;` command to add a tomato ingredient into the `stock`. Further, the user may add more ingredients into the current `stock`. Suppose the user executes `add stock; i/potato; q/5; p/0.40;` and `add stock; i/rice; q/3; p/0.40;` as well.

Step 3. The user can now search against the current `stock` to see if an ingredient is stored in the `stock`. The user now executes `search stock; k/tomato;`, which will display the following result in the image. 

<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/78976850-a0deb380-7b49-11ea-9235-a4659d611f7b.png">
</p>

Step 4. If the ingredient that the user is searching for does not exist within the stock, a different message will be displayed as shown in the following image.

<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/78976997-ee5b2080-7b49-11ea-8ff1-e62e59e508f9.png">
</p>

The following sequence diagram shows how the search operation works:

<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/78683492-efaa0480-7921-11ea-82c4-e2b4abb9da92.png">
</p>


The sequence diagram can be interpreted as such:
1. `CommandParser` calls its own `CommandParser#parseCommand(...)`. 
2. Assuming the user input the search stock command correctly, `SearchStockCommand#SearchStockCommand(...)` constructor is called. 
3. The newly constructed `SearchStockCommand` invokes its `SearchStockCommand#parseIntoSearchKeyword(...)` and does not return anything.
4. The `CommandParser` then invokes `SearchStockCommand#execute(...)`, which then further invokes `Stock#searchStock(...)` from the Stock object.
5. The `Stock` object then self-invoke `Stock#checkIngredientInStock(...)` to see if there are ingredients that matches keyword that was passed into earlier.
6. If there are search results, `Stock#printSearchResult(...)` will display all the ingredients that matches the keyword given.
7. If there is no ingredient that matches the keyword, the program will display a different message to show the user.
8. Next, the time line returns back to `CommandParser` and the `SearchStockCommand` object is destroyed here.
9. If however, the user input did not input the search stock command correctly, `CommandParser` will invoke `CommandParser#errorCommand()` to notify the user.


#### 1.1.2 Design Considerations
##### Aspect: How search stock executes
* **Alternative 1 (current choice)**: List all ingredients that contains the keyword supplied.
  + Pros: Easy to implement.
  + Cons: All ingredients containing the keyword will be listed. This means that even if the keyword doesn't make sense, such as supplying "to", both potato and tomato will be listed since it contains "to".

* **Alternative 2**: The user supplies the exact ingredient name.
  + Pros: The ingredient that matches exactly with the keyword supplied will be listed.
  + Cons: The user have to input the ingredient name correctly each time he/she uses the search stock feature.

##### Aspect: Data structure to support the search stock feature.
* **Alternative 1 (current choice)**: Use a `List` to store all possible ingredient names that contain the keyword supplied.
  + Pros: Easy to understand.
  + Cons: The `List` is derived by converting the existing stock (which is a `HashMap`) into an ArrayList. In order to print out the ingredient's name, quantity and price, methods such as `getKey()` and `getValue()` have to be used.

* **Alternative 2**: Use the existing `HashMap` data structure employed by the `stock` object.
  + Pros: We do not need to create a new `List` to display the relevant results.
  + Cons: Iterating a HashMap can be done by converting it to an EntrySet or by using an iterator. These, compared to `List`, is much less straightforward.

<a name="list-stock"></a>

### 1.2 List stock ingredients in descending quantity
#### 1.2.1 Proposed implementation

In the restaurant daily report, users can view all the ingredients presently in the stock by supplying the input `list stock`. The ingredients will be ordered in descending quantities, that is, the ingredient that has the highest quantity will be listed first and vice versa.

Given below is an example usage scenario and how the listing mechanism behaves at each step.

Step 1. The user launches the application for the first time. An empty `stock` will be initialized.

Step 2. The user executes `add stock; i/tomato; q/10; p/0.40;` command to add a tomato ingredient into the `stock`. Further, the user may add more ingredients into the current `stock`. Suppose the user executes `add stock; i/potato; q/0; p/0.40;` and `add stock; i/rice; q/55; p/0.40;` as well.

Step 3. The user can now view the current `stock` to see what ingredients are there in the stock. The user now executes `list stock`, which will display the following result in the image. In this case, `rice` has the highest quantity of `55`, which is listed first as compared to `potato`, which has the lowest quantity of `0`. This can be seen from the image below.

<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/78977634-05e6d900-7b4b-11ea-8911-057c4ceb768b.png">
</p>

The listing mechanism process can be summarized in the following sequence diagram below:

<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/78683798-52030500-7922-11ea-8a8d-3aaa5d7af486.png">
</p>

The sequence diagram can be interpreted as such:
1. `CommandParser` calls its own `CommandParser#parseCommand(...)`. 
2. Assuming the user input the list stock command correctly, `ListStockCommand#ListStockCommand()` constructor is called. 
3. The time line returns back to `CommandParser`.
4. The `CommandParser` then invokes `ListStockCommand#execute(...)`, which then further invokes `Stock#ListStock()` from the Stock object.
5. The `Stock` object then self-invoke `Stock#printStock()` to print the ingredients that are in the stock to display it to the user.
6. Note that within the method `Stock#printStock()`, the hashMap in the `Stock` will be sorted in descending ingredient quantity.
7. If there is no ingredient that in the `Stock`, the program will display a different message to show the user.
8. Next, the time line returns back to `CommandParser` and the `ListStockCommand` object is destroyed here.
9. If however, the user input did not input the search stock command correctly, `CommandParser` will invoke `CommandParser#errorCommand()` to notify the user.


Alternatively, the following class diagram also explains listing operation works:

<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/78975984-dda9ab00-7b47-11ea-8df4-f9510b8328ee.png" width="900">
</p>

1. When the user first runs the application, the `Main` object is initialized. The `Main` object then initializes the `ui`, the `stock` object and other objects such as `reservations` etc. in its `Main#start()` method. 
2. When the `stock` object is created, it will create a `LoadStock` object, which loads all data from the `report.txt` through `LoadStock#loadStockData(...)`.
3. Next, moving back to `Main`, through the `Main#runCommandUntilExit()` method, it then instructs the `ui` object to scan and read for user input. A `CommandParser` object is then initialized to parse this user input into a command.
3. The `CommandParser` object detects `list stock;` as the user input, in which it will then create a `ListStockCommand` object.
4. The `ListStockCommand` object is then executed via its `ListStockCommand#execute(...)` method, which takes in the `stock` object initialized previously and instructs it to list all ingredients through its `Stock#listIngredient()` method.
5. Within  the `Stock#listIngredient()` method, a temporary `List` data structure is used to convert from the `HashMap` in the `stock` object. The list is then sorted by supplying a `new Comparator` that compares the ingredient's quantity. Afterwards, the sorted list is then printed to be displayed to the user.




#### 1.2.2 Design Considerations

##### Aspect: How listing stock executes
* **Alternative 1 (current choice)**: List all ingredients in the stock in descending quantities.
  + Pros: The user is able to tell which ingredients are the least/most quickly. This can help to facilitate quicker decision making in terms of what ingredients to re-stock.
  + Cons: The current ingredients are listed in a specific ordering. A change in the ordering requirement would mean that a change in the implementation of the `comparator`.

* **Alternative 2**: List all ingredients in the stock without any ordering.
  + Pros: Easy to implement.
  + Cons: Hard to identity which ingredients that require re-stocking quickly.

##### Aspect: Data structure to support the search stock feature.
* **Alternative 1 (current choice)**: Use a `List` to store all possible ingredient names.
  + Pros: Easy to understand.
  + Cons: The `List` is derived by converting the existing stock (which is a `HashMap`) into an ArrayList. In order to print out the ingredient's name, quantity and price, methods such as `getKey()` and `getValue()` have to be used.

* **Alternative 2**: Use the existing `HashMap` data structure employed by the `stock` object.
  + Pros: We do not need to create a new `List` to display the relevant results.
  + Cons: Iterating a HashMap can be done by converting it to an EntrySet or by using an iterator. Sorting it, however, as compared to `List`, is much less straightforward.

<a name="generate-profit"></a>

### 1.3 Generate profit for the day
#### 1.3.1 Proposed Implementation
In the restaurant daily report, the user can input the amount of items sold each day and a total profit will be generated, when the user inputs `profit`.

Below is an example usage scenario for the user.

Step 1. The user opens the program and an empty `sales` is initialized.

Step 2. The user can add sold items by inputting `sell dish; d/DISH; q/QUANTITY`. An example would be `sell dish; d/pasta; q/15;`.
The user can input as many sales as they like as long as the dish exists in the menu.

Step 3. The user can generate the profit by inputting `profit`.

The execution can be viewed in the sequence diagram below.
![](https://github.com/nguan1/tp/blob/master/docs/documentations/Ned/GenerateProfitSequenceDiagram.PNG)

#### 1.3.2 Design Considerations
##### Aspect: Using a separate class to perform sale commands
**Current Implementation**: Methods are stored in the sales class
* Pros: Easy to understand and implement.
* Cons: May make the sales class long and convoluted and may become difficult to find certain features or methods

**Alternative**: Commands are stored in separate classes
* Pros: Easier for a person who hasn't worked on the project to understand the structure
* Cons: Takes longer to implement


<a name="search-reservation"></a>

### 1.4 Search reservation
#### 1.4.1. Proposed Implementation

In the restaurant daily report, users can search against the reservation category by supplying either a reservation number or a date.

The feature implements the following operations:
* `SearchReservationCommand#parseInput()` - parse the user input for either reservation number or a date.
* `SearchReservationCommand#execute()` - search along the reservation list for the target reservation(s).

The following class diagram shows the structures relevant to the "search reservation" feature:
<p align="center">
    <img src="documentations\Sibing\ClassDiagramforSearchReservation.png" width="900">
</p>

Given below is an example usage scenario and how the search mechanism behaves at each step.

Step 1. The user launches the application for the first time. An empty `reservations` will be initialized.

Step 2. The user executes `add reservation; p/Peter; d/2020-03-12 12:00; n/3; c/98955555;` command to add a reservation into the `reservations` list.  
Further, the user may add more reservations into the current `reservations` list.  
Suppose the user executes `add reservation; p/Mary; d/2020-03-11 12:00; n/8; c/99998888;`  
and `add reservation; p/Lisa; m/no spicy food please; d/2020-03-12 12:00; n/3; c/98889999;` as well.

The following object diagram illustrates how the `reservations` list looks like:
<p align="center">
    <img src="documentations\Sibing\ObjectDiagramforSearchReservation.png" width="900">
</p>

Step 3. The user can now search against the current `reservations` list to see if an reservation is stored in the `reservations` list.  
If the user executes `search reservation; r/1;`, the mechanism is shown as follows:
* The `search reservation` command calls `SearchReservationCommand#execute()`.
* The branch with only valid reservation number will be executed, calling `ReservationList#getReservation(reservationNumber)` to fetch the target `Reservation` object.
* The following result with formatted information will be displayed in the image:

<p align="center">
    <img src="documentations\Sibing\SearchByIndex.png" width="300">
</p>

If the user executes `search reservation; d/2020-03-12;`, the mechanism is shown as follows:  
* The `search reservation` command calls `SearchReservationCommand#execute()`.
* The branch with only valid date will be executed, doing a linear search along the whole `reservations` list to pick out the `Reservation` objects with the same date.
* The following result will with formatted information be displayed in the image:

<p align="center">
    <img src="documentations\Sibing\SearchByDate.png" width="500">
</p>

If the user executes `search reservation; r/1; d/2020-03-13;`, the mechanism is shown as follows:  
* The `search reservation` command calls `SearchReservationCommand#execute()`.
* The branch with both valid date and reservation number will be executed.
* `SearchReservationCommand#execute()` is called to fetch the `Reservation` object in that reservation number.
* Date of the `Reservation` object is checked to see whether it matches the target date or not.
* In this case, the date does not match, so the following result will be displayed in the image:

<p align="center">
    <img src="documentations\Sibing\SearchByIndexnDate.png" width="300">
</p>

The following sequence diagram shows how `search reservation` operation works:

<p align="center">
    <img src="documentations\Sibing\SequenceDiagramforSearchReservation.png" width="900">
</p>

#### 1.4.2 Design Considerations
##### Aspect: How search reservation executes
* **Alternative 1 (current choice)**: Access reservation by `ReservationList#getReservation(reservationNumber)` if reservation number is provided,
or do linear search along the reservation list for reservations with date matched.
  + Pros: Easy to implement and understand.
  + Cons: May have performance issues in terms of time usage when searching along the reservation list.

* **Alternative 2**: Create a `HashMap` with date as the key and all Reservation objects in that date as the value.
Once a new Reservation object is created, it will be added into the `HashMap` according to its date.
  + Pros: Easier for searching in terms of date.
  + Cons: Extra memory space is needed.


##### Aspect: Data structure to support the search reservation feature.
* **Alternative 1 (current choice)**: Display all all `Reservation` objects that contain the reservation number or date provided to the screen directly.
  + Pros: Easy to implement and understand.
  + Cons: The target objects are not really "contained" in a data structure.

* **Alternative 2**: Use a `List` to store all `Reservation` objects that contain the reservation number or date provided.
  + Pros: Objects are stored in a data structure, which is easier to do operation on that specific collections of objects.
  + Cons: Extra memory space is needed.


<a name="search-dish"></a>

### 1.5 Search dish feature
#### 1.5.1 Proposed implementation

In restaurant daily report, a user can search from available menu items using the search dish commmand.

The search dish command takes in a keyword denoted by k/, and searches all dish names for dishes containing the keyword.

An example of the usage of search dish can be found below.

Step 1. User launches application. An empty `Menu` is initialized.

Step 2. User adds a menu item to the empty menu with `add dish; n/bacon pizza; i/cheese, bacon; p/7.00;`. Bacon pizza is now on the menu.

Step 3. User adds more menu items to the menu. For example, they can `add dish; n/chicken rice; i/chicken, rice; p/4.00;` and `add dish; n/pasta with bacon; i/pasta, bacon; p/6.00;`

Step 4. User searches the menu for any dish names containing a keyword. Let the user execute `search dish; k/bacon;`, the expected behavior can be found in the screenshot below.

<p align="center">
  <img src="https://user-images.githubusercontent.com/48315922/77872867-3e66e880-7216-11ea-9003-e04bda089f24.png">
</p>

#### 1.5.2 Design Considerations
##### Aspect: Execution of search dish
* **Alternative 1 (current choice)**: Search dish names for keyword
  + Pros: Intuitive use of search, easy to implement
  + Cons: If dishes are not named intuitively, such as `pasta with bacon`, feature becomes less useful

* **Alternative 2**: Search dish ingredients for keyword
  + Pros: Can find all dishes with a certain ingredient, which is useful if you really like something like `bacon` or are allergic to something like `cilantro`
  + Cons: Cannot easily find if the menu contains your favorite dish by name, like `ratatouille`

##### Aspect: Data structure in search dish
* **Alternative 1 (current choice)**: Create a new temporary `HashMap<String, Dish>` that contains all dishes that match the search
  + Pros: Flexible to use later on, easy to implement
  + Cons: Potentially duplicate work as similar functionality could be accomplished without creating a new HashMap

* **Alternative 2**: Use original dish HashMap to identify dishes matching search
  + Pros: No duplicate work and we're always using the same HashMap
  + Cons: If we choose to further develop this feature later on, we'll have to do the search again because we did not save the matching items any way

<a name="product-scope"></a>


## Appendix A: Product Scope
### Target user profile

The Restaurant Daily Report is a CLI application is designed for restaurant owners, who need a simple and efficient way to manage the operation of their business.
Ideally, the owner would be proficient at using desktop apps and is a quick typer.

### Value proposition

* Single application to store dishes, stock and reservations
* Can calculate the daily profit and most popular dish
* Allows the owner to see an overview of their restaurant
* Simple and easy to use interface

## Appendix B: User Stories

|Version| As a ... | I want to ... | So that ...|
|--------|----------|---------------|------------------|
|v1.0|restaurant owner|add a new ingredient into the stock|I can keep track of current ingredient quantities|
|v1.0|restaurant owner|delete an existing ingredient in the stock|I can remove ingredients that are no longer used in the kitchen|
|v1.0|restaurant owner|list all existing ingredients in the stock|I can view what are the ingredients and their quantities currently|
|v1.0|restaurant owner|add a newly received reservation|I can record the details about the reservation and make corresponding preparations|
|v1.0|restaurant owner|mark a reservation as invalid|I can update the status of the reservation if the reservation gets canceled|
|v1.0|restaurant owner|list all reservations|I can view what reservations the restaurant has currently|
|v1.0|restaurant owner|add dishes|I can introduce new dishes to the menu|
|v1.0|restaurant owner|list dishes|I can see all the dishes on the menu|
|v1.0|restaurant owner|delete dishes|I can remove dishes from the menu|
|v1.0|restaurant owner|add stock|I can add stock to the inventory|
|v1.0|restaurant owner|list stock|I can see how much stock I currently have|
|v1.0|restaurant owner|delete stock|I can remove the stock that has been used or spoiled
|v1.0|restaurant owner|save the dishes, stock and reservation|I have a document that contains all the important details about my restaurant|
|v2.0|restaurant owner|search an ingredient by giving a keyword|I can quickly find out the current ingredient's quantity and price|
|v2.0|restaurant owner|list the reservations in descending quantities|I can find out the ingredients that we have the most quickly|
|v2.0|restaurant owner|mark a reservation as served|I can update the status of the reservation|
|v2.0|restaurant owner|list all served reservations|I can view the achievement of served reservation|
|v2.0|restaurant owner|list all unserved reservations|I can know what reservations need to be prepared|
|v2.0|restaurant owner|search a reservation via reservation number|I can know the details about a specific reservation|
|v2.0|restaurant owner|search a reservation via a specific date|I can know the achievement on a certain date or know what reservations I need to prepare on a certain date|
|v2.0|restaurant owner|search dishes|I can know which dishes contain a certain word and the ingredients of those dishes|
|v2.0|restaurant owner|search stock|I can know the quantity and cost of specific ingredients|
|v2.1|restaurant owner|clear the reservations list|I can have an empty reservations list when situations, like moving restaurant to a new place, happen|
|v2.1|restaurant owner|load the dishes, stock and reservation|I don't have to re-enter the dishes, stock and reservations when I start up the program|
|v2.1|restaurant owner|clear the ingredients in the stock|I can reset the entire stock if there are too many unwanted ingredients stored in the program|
|v2.1|restaurant owner|load the stock data from a data file|I can port the data from one computer to another|
|v2.1|restaurant owner|search a reservation without being tied to case sensitivity|I know all the possible ingredients if I typed in a keyword|


<a name="nonfunctional-requirement"></a>

## Appendix C: Non-Functional Requirements

* Should work on any [mainstream OS](#mainstream-os) as long as it has `Java 11` or above installed.
* A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
* Commands should be intuitive and follow a logical order
* Information should be displayed in a easy to read format

<a name="glossary"></a>

## Appendix D: Glossary
<a name="mainstream-os"></a>
* *Mainstream OS* - Windows, Linux, Unix, OS-X

<a name="manual-test"></a>
## Appendix E: Instructions for Manual Testing
Given below are instructions to test the program manually.

<a name="e1-launch-and-shutdown"></a>
### E.1. Launch and Shutdown
1. Download the jar file and copy into an empty folder.
2. Open a Terminal in that folder.
3. Run the command `java -jar [CS2113-T14-4][RestaurantDailyReport].jar`. The CLI should appear in a few seconds. It should be a welcome page.


<a name="e2-add-functionality"></a>
### E.2. Add Functionality

#### E.2.1 Adding a dish into menu
Adding a dish into menu.
* Test case: `add dish; n/pizza; i/cheese, sauce; p/7.00;`
    - Expected: Dish `pizza` successfully added.
* Test case: `add dish; n/pizza; i/cheese, sauce;`
    - Expected: Dish `pizza` will not be added. An error message will be displayed to request the user to input the correct format.
* Other incorrect add commands to try: `add dish; n/pizza; sauce;` or `add dish;i/cheese, sauce;`
    - Expected: The dish will not be added. An error message will be displayed accordingly.

#### E.2.2. Adding an ingredient into stock
Adding an ingredient into stock.
* Test case: `add stock; i/tomato; q/10; p/0.50;`
    - Expected: Ingredient `tomato` added into the stock.
* Test case: `add stock; i/tomato; q/10; p/0.50;`
    - Expected: The quantity of `tomato` increased to `20` due to the addition from the previous entry.
* Test case: `add stock; i/rice; q/10; p/randomNumber;`
    - Expected: `rice` will not be added into the stock. An error message will be displayed to request the user to type in the price
    as a double.
* Test case: `add stock; i/tOMaTO; q/10; p/0.50`
    - Expected: tOMaTO will be added into the stock. However, because of case-sensitivity, the quantity will not add on to the previous `tomato` 
    in the stock. A message will be displayed to inform the user of similar ingredient names already present in the stock.
* Other incorrect add commands to try: `add stock; i/tomato; q/LOL; p/10` or `add stock; i/tomato;`
    - Expected: The ingredient will not be added. An error message will be displayed accordingly.
* Extra Notes: 
    - The price of the ingredient can be overwritten. 
    For example:
    - Adding `add stock; i/apple; q/10; p/0.50` and then `add stock; i/apple; q/10; p/10.00` will overwrite the current price of `$0.50` to `$10.00`. 
    **This is intentional.**

#### E.2.3. Adding a reservation
Adding a reservation to an empty list.
* Prerequisite: Clear the `reservations` list using `clear reservation;` command to ensure the empty list.
* Test case: `add reservation; p/Peter; d/2020-03-12 12:00; n/3; c/98955555;`
    - Expected: An unserved Reservation with reservation number [1] is added into the `reservations` list. Details of the added Reservation are displayed.
* Test case: `add reservation; p/David; d/2020-03-12 12:00; c/98887777;`
    - Expected: No Reservation is added. An error message shows to remind the user that "number of guests n/" is missing.
* Test case: `add reservation`
    - Expected: An error message shows to remind the user that it is a incorrect input format and the user can type `help` for the list of command.
* Other incorrect add commands to try: `add reservation; p/David d/2020-03-12 12:00; n/3; c/98887777;`, `add reservation;`...
    - Expected: No Reservation is added. Error messages for input missing displays.

<a name="e3-delete-functionality"></a>
### E.3. Delete functionality

#### E.3.1 Deleting a dish in the menu
Deleting a dish in the menu.
* Test case: `delete dish; n/pizza`
    - Expected: The dish `pizza` will be deleted from the menu. This is assuming you have followed the add commands in E.2.1.
* Test case: `delete dish; n/rice cake;`
    - Expected: The dish `rice cake` will not be deleted. An error message will be displayed to show that this dish does not exist. 

#### E.3.2. Delete an ingredient in the stock
Deleting an ingredient in the stock.
* Test case: `delete stock; i/tomato; q/1`
    - Expected: The quantity of tomato will be reduced by 1. Assuming that you have followed the add commands in E.2.2, the count of `tomato`
    should be `19` now.
* Test case:  `delete stock; i/tOMaTO;
    - Expected: The ingredient `tOMaTO` is completely removed in the stock.
* Test case: `delete stock; q/10`
    - Expected: No ingredient is deleted. An error message will be displayed to remind the user to input an ingredient name.
    
#### E.3.3 Deleting a reservation
Deleting a reservation while all reservations are listed.
* Prerequisites: List all reservations using the `list reservation;` command. Multiple reservations in the list. The status of Reservation[1] is Unserved.
* Test case: `delete reservation; r/1;`
    - Expected: The status of the first reservation in the list is changed to **Invalid**. The reservation itself still remains in the list.
* Test case: `delete reservation; r/X;` where X is a large number exceeding the maximum of reservation number
    - Expected: No Reservation is marked as Invalid. An error message shows to remind the user that there is no such reservation in the list.
* Test case: `delete reservation; r/2.3;`
    - Expected: No Reservation is marked as Invalid. An error message shows to remind the user to input a positive integer.

#### E.3.4 Marking a reservation
Marking a reservation as Served while all reservations are listed.
* Prerequisites: List all reservations using the `list reservation;` command. Multiple reservations in the list. The status of Reservation[2] is Unserved.
* Test case: `mark reservation; r/2;`
    - Expected: The status of the second reservation in the list is changed to **Served**.
    
  
<a name="e4-search-functionality"></a>
### E.4. Search functionality

#### E.4.1 Search a dish
Searching a dish in the menu.
* Test case: `search dish; k/piz;`
    - Expected: The dish `pizza` will be displayed.
* Test case: `search dish; k/sushi;`
    - Expected: An error message will be displayed as the dish `sushi` does not exist in the menu.
* Test case: `search dish; k/water`
    - Expected: An error message will be displayed to request the user to input in the following format: `search dish; k/KEYWORD;`
    

#### E.4.2 Search an ingredient
Searching an ingredient in the menu.
* Test case: `search stock; k/tomato;`
    - Expected: All possible ingredients' names that contain `tomato` will be displayed. Assuming you have followed E.3.2 strictly, the only ingredient
    that will be displayed is `tomato`. Note that we did not take into account the ingredient `apple` used in the `Extra Notes` in E.2.2.
* Test case: `search stock; k/banana`
    - Expected: A message will be displayed to show that there is currently no existing ingredient that matches the keyword you have just given.
* Test case: `search stock; k/TOMATO;`
    - Expected: The ingredient `tomato` will still be listed. The search stock functionality takes care of case-sensitivity of the keyword given.
* Test case: `search stock; tomato`
    - Expected: An error message will be displayed to request the user to input the keyword in the follow format of `k/keyword`. 

#### E.4.3 Search a reservation
Searching a reservation while all reservations are listed.
* Prerequisites: List all reservations using the `list reservation;` command. Multiple reservations in the list. Only Reservation[1] and Reservation[3] are on 2020-04-10.
* Test case: `search reservation; r/1;`
    - Expected: The details of the first reservation in the list are displayed on the screen.
* Test case: `search reservation; d/2020-04-10;`
    - Expected: The details of the Reservation[1] and Reservation[3] will be displayed on the screen. 
* Test case: `search reservation; r/1; d/2020-04-11;`
    - Expected: A message of no such reservation will display.



<a name="e5-list-functionality"></a>
### E.5 List functionality

#### E.5.1 List all dishes in the menu
Listing all dishes in the menu.
* Test case: `list dish;`
    - Expected: All dishes in the menu will be listed. Assuming you have followed up to E.4.1 strictly, there should be no dishes listed in the menu.
* Test case: `list dish`
    - Expected: An error message shows to remind the user that it is a incorrect input format and the user can type `help` for the list of command.  

#### E.5.2 List all ingredients in the stock
Listing all ingredients in the stock.
* Test case: `list stock;`
    - Expected: All ingredients in the stock will be listed. Assuming that you have followed up to E.4.2 strictly, the only ingredient listed would be
    `tomato`. Note that there will be a different message displayed to inform the user if the stock does not have any ingredient currently. 
    Also, we did not take into account the ingredient `apple` used in the `Extra Notes` in E.2.2.
* Test case: `list stock`
    - Expected: An error message shows to remind the user that it is a incorrect input format and the user can type `help` for the list of command.

#### E.5.3 List all reservations
Listing all reservations at the beginning of the program execution with reservation content already exist in the "report.txt" file.
* Prerequisites: Execute the program for the first time and add some reservations. Type `bye` to exit the program and the reservations added will be automatically saved to the "report.txt" file. Execute the program again.
* Test case: `list reservation;`
    - Expected: The details of reservations originally in the "report.txt" will display on the screen.
* Test case: `list reservation`
    - Expected: An error message shows to remind the user that it is a incorrect input format and the user can type `help` for the list of command.
  

#### E.5.4. List all Served reservations
Listing all served reservations while all reservations are listed.
* Prerequisites: List all reservations using the `list reservation;` command. Multiple reservations in the list. Only Reservation[2] and Reservation [3] are Served.
* Test case: `list served reservation;`
    - Expected: The details of Reservation[2] and Reservation[3] will display on the screen.
* Test case: `list served reservation`
    - Expected: An error message shows to remind the user that it is a incorrect input format and the user can type `help` for the list of command.


#### E.5.5. List all Unserved reservations
Listing all unserved reservations while all reservations are listed.
* Prerequisites: List all reservations using the `list reservation;` command. Multiple reservations in the list. Only Reservation[4] and Reservation [5] are Served.
* Test case: `list unserved reservations`
    - Expected: The details of Reservation[4] and Reservation[5] will display on the screen.
* Test case: `list unserved reservation`
    - Expected: An error message shows to remind the user that it is a incorrect input format and the user can type `help` for the list of command.

<a name="e6-clear-functionality"></a>  
### E.6 Clear functionality
#### E.6.1 Clear all ingredients 
Clear all ingredients in the stock.
* Test case: `clear stock;`
    - Expected: All ingredients in the stock are cleared. You can use `list stock;` to ensure this.
* Test case: `clear stock`
    - Expected: An error message shows to remind the user that it is a incorrect input format and the user can type `help` for the list of command.
    
#### E.6.2 Clear all reservations
Clear all reservations in the list while all reservations are listed.
* Prerequisites: List all reservations using the `list reservation;` command. Multiple reservations in the list.
* Test case: `clear reservation;`
    - Expected: All reservations are cleared, both in `reservations` list and in the "report.txt" file. User can type `list reservation` to make sure all reservations are cleared. 
* Test case: `clear reservation`
    - Expected: An error message shows to remind the user that it is a incorrect input format and the user can type `help` for the list of command.
