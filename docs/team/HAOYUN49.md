# Project Portfolio -- Hao Yun
## Project Nuke <small>v2.1</small>

## Overview
**Nuke** is a simply yet powerful task management application that targets towards _NUS students_ and aims to facilitate record, classification, management and navigation of their acdemical tasks. The users can interact with **Nuke** using both a _Command Line Interface (CLI)_ and a complementary _Graphical User Interface (GUI)_. **Nuke** is written in Java, and has about 17000 lines of code.



## Summary of Contributions

### Code Contributed

[Functional and Test Code](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#breakdown=true&search=HAOYUN49&sort=totalCommits%20dsc&sortWithin=title&since=2020-03-01&timeframe=commit&mergegroup=false&groupSelect=groupByNone&tabOpen=true&tabType=authorship&tabAuthor=HAOYUN49&tabRepo=AY1920S2-CS2113T-T13-2%2Ftp%5Bmaster%5D) from _GitHub RepoSense_ 



### Major Enhancement

#### Implemented List Deadline Features
- What it does: allows the user to check their tasks of different modules or all modules in terms of the deadline.

- Justification: these features improve filtering function of the product and benefit the user greatly because a user can see the most urgent task and the least urgent task and then determine which task to complete right after.

- Highlights: the implementation mainly consists of two parts include integrating all tasks from different categories of different modules as user-added tasks are stored in a multitude of seperated lists and sorting the all these tasks in the ascending order of deadline. As users may simplify commands by omitting prefix if they are in the current directory or check tasks of another module in current directory, the implementation need to deal with different situations and filter tasks correctly.

#### Implemented Tag Features
- What it does: allows the user to add, delete several tags to every task to give more customized descriptions to their tasks and then list tasks out based on different tags.

- Justification: these features will improve filtering function of the application significantly. Users can use customized tags to describe tasks and filter tasks according to these tags to find tasks catering to their needs well.

- Highlights: This enhancement provides a new criteria for users to manage and navigate their tasks. The implementation includes adding a new attribute to existing object, filtering tasks of differnt categories of different modules in terms of tags which users can choose to use exact tag or keyword of tag to filter tasks leading t o changes to existing commands.



### Minor Enhancement
Added **help** command which will show a simple user guide to enable users find valid commands and corresponding format when using **Nuke**.



### Other Contributions
- Project managment:
  - Necessary general code enhancements
  
  - Maintaing the issue tracker
  
- Enhancements to existing features:
  - Wrote a majority of JunitTests for existing features to increase coverage to a greate extent

- Documentation

- Community
  - PRs reviewed (with non-trivial review comments)
  
  - Reported bugs and suggestions for other teams using CaTcher (with non-trivial issues)


