# Joseph Lim Wei Jie - Project Portfolio Page

## Overview
`Restaurant Daily Report` is a CLI app that generates a whitepaper, summarizing the internals of a restaurant. It’s aim is to provide restaurant owners a quick overview of how their restaurant is performing daily so that restaurant owners can better manage their business operations.

### Summary of Contributions

**Code contribution**: You can view my code contribution for this project [here:](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#breakdown=true&search=josephlimweijie)

* **Enhancement added:**
    1. `Add` functionality:
    - Allows flexibility in the ordering of the parameters specified from the user. For example: Entering `add stock; i/tomato; q/10; p/1.00;` and `add stock; i/tomato; 
    p/1.00; q/10;` are both acceptable.
    - Customizes error messages according to the input supplied from the user. For example: Entering `add stock; i/;` will display the message: `"The user's input must specify 
    the ingredient's name!"`. Entering `add stock; i/tomato; q/-10; p/1.00;` will display the message: `"Please enter a positive value for the quantity to be added!"`
    - Displays a reminder to the user if the user input a similar ingredient name that exists in the stock. This is inform the user if he/she has made a typo that was undesired.
    For example: Entering `add stock; i/ToMATo; q/10; p/1.00;` will display the following result, if tomato already exists in the stock:   
    <p align="center">
        <img src= "https://user-images.githubusercontent.com/59989652/78984371-a42d6b80-7b58-11ea-8e88-d9fa8adfdec8.png">
    </p>    
    2. `Delete` functionality:
    - Allows flexibility in the ordering of the parameters specified from the user. For example: entering `delete stock; i/tomato; q/10;` and `delete stock; i/tomato;` 
    are both acceptable.
    - Allows the user to delete the ingredient by quantity count or remove from the stock entirely. For example: Entering `delete stock; i/tomato;` removes tomato entirely from the stock. 
   Entering `delete stock; i/tomato; q/1;` reduces the quantity of tomatoes in the stock by 1. 
    - Customizes error messages according to the input supplied from the user. For example: Entering `delete stock; i/;` will display the message: `"The user's input must specify the
 ingredient's name!"` while entering `delete stock; i/tomato; q/-10;` will display the message: `"Please enter a positive value for the quantity to be added!"`
    
    3. `List` functionality
    - Lists ingredients in the stock from the highest quantity to the lowest quantity.

    4. `Search` functionality
    - Takes into account case-sensitivity of the keyword supplied from the user when searching against the stock.

    5. `Load` functionality
    - Loads the stock-related data from the `report.txt` file.

* **Contributions to documentations:**

    1. **User guide contribution:**
    - Anchoring using Table of Content at the top of the user guide.
    - `Section 1: Introduction`, `Section 2: Quick Start`, `Stock` related commands from `Section 3 - Section 4` and all images used in the User Guide.

    2. **Developer guide contribution:**
    - Anchoring using Table of Content at the top of the developer guide.
    - `1.1 Search stock feature` and `1.2 List stock ingredients in descending quantity`
    - Stock-related `User Stories` in `Appendix B` and `Instructions for Manual Testing` for `Dish` and `Stock` in `Appendix E`.

* **Team-based Task**
    1. Automate testing by incorporating test cases using text-ui-test.
    2. Release `Restaurant Daily Report v2.0` and `Restaurant Daily Report v2.1`.

**Review/Mentoring contributions:** Troubleshoot failing CI-Test in issue [#74](https://github.com/nus-cs2113-AY1920S2/forum/issues/74)


