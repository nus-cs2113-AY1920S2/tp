# Developer Guide

## 1. Design & Implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### 1.1 [Proposed] Search stock feature
#### 1.1.1 Proposed implementation

In the restaurant daily report, users can search against the stock category by supplying a keyword.

Given below is an example usage scenario and how the search mechanism behaves at each step.

Step 1. The user launches the application for the first time. An empty `stock` will be initialized.

Step 2. The user executes `add stock; i/tomato; q/10; p/$0.40;` command to add a tomato ingredient into the `stock`. Further, the user may add more ingredients into the current `stock`. Suppose the user executes `add stock; i/potato; q/5; p/$0.40;` and `add stock; i/rice; q/3; p/$0.40;` as well.

Step 3. The user can now search against the current `stock` to see if an ingredient is stored in the `stock`. The user now executes `search stock; tomato`, which will display the following result in the image. 

<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/77285807-d9d6f580-6d0c-11ea-8716-b4cb55877662.PNG">
</p>

The following sequence diagram shows how the search operation works:

<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/77295796-858a4080-6d21-11ea-8d4f-f942d2312927.png">
</p>

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

### 1.2 [Proposed] List stock ingredients in descending quantity
#### 1.2.1 Proposed implementation

In the restaurant daily report, users can view all the ingredients presently in the stock by supplying the input `list stock`. The ingredients will be ordered in descending quantities, that is, the ingredient that has the highest quantity will be listed first and vice versa.

Given below is an example usage scenario and how the listing mechanism behaves at each step.

Step 1. The user launches the application for the first time. An empty `stock` will be initialized.

Step 2. The user executes `add stock; i/tomato; q/10; p/$0.40;` command to add a tomato ingredient into the `stock`. Further, the user may add more ingredients into the current `stock`. Suppose the user executes `add stock; i/potato; q/0; p/$0.40;` and `add stock; i/rice; q/55; p/$0.40;` as well.

Step 3. The user can now view the current `stock` to see what ingredients are there in the stock. The user now executes `list stock`, which will display the following result in the image. In this case, `rice` has the highest quantity of `55`, which is listed first as compared to `potato`, which has the lowest quantity of `0`. This can be seen from the image below.

<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/77300028-5cb97980-6d28-11ea-9a27-7d118de3431f.PNG">
</p>

The following class diagram shows how the listing operation works:

<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/77316340-36093c00-6d44-11ea-9ee7-2f120b74364c.png">
</p>

* 1. When the user first runs the application, the Main object is initialized. The Main object then initializes the ui and the stock object in its `start()` method. 
* 2. Through its `runCommandUntilExit()` method, it then instructs the ui object to scan and read for user input. A CommandParser object is then initialized to parse this user input into a command.
* 3. The CommandParser object detects `list stock` as the user input, in which it will then create a ListStockCommand object.
* 4. The ListStockCommand object is then executed via its `execute(stock)` method, which takes in the stock object initialized previously and instruct it to list all ingredients through its `listIngredient()` method.
* 5. Within  the `listIngredient()` method, a temporary `List` data structure is used to convert from the `HashMap` in the stock object. The list is then sorted by supplying a `new Comparator` that compares the ingredient's quantity. Afterwards, the sorted list is then printed to be displayed to the user.


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


## 2. Product Scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## 3. User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## 4. Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## 5. Instructions for Manual Testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
