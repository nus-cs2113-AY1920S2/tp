### Overview
**Nuke** is a simply yet powerful task management application that targets towards _NUS students_ and aims to facilitate record, classification, management and navigation of their acdemical tasks. The users can interact with **Nuke** using both a _Command Line Interface (CLI)_ and a complementary _Graphical User Interface (GUI)_. **Nuke** is written in Java, and has about 17000 lines of code.


### Summary of Contributions

#### Code Contributed

[Functional and Test Code](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#breakdown=true&search=HAOYUN49&sort=totalCommits%20dsc&sortWithin=title&since=2020-03-01&timeframe=commit&mergegroup=false&groupSelect=groupByNone&tabOpen=true&tabType=authorship&tabAuthor=HAOYUN49&tabRepo=AY1920S2-CS2113T-T13-2%2Ftp%5Bmaster%5D) from _GitHub RepoSense_ 


#### Major Enhancement

##### Implemented List Sorted Taks Features
- What it does: allows the user to check their _task_s of different _module_s or all _module_s in the ascending order of _deadline_ or descending order of _priority_.

- Justification: these features improve filtering function of the product and benefit the user greatly because a user can see the most urgent task and the least urgent task and then determine which task to complete right after.

- Highlights: the implementation mainly consists of two parts include integrating all tasks from different categories of different modules as user-added tasks are stored in a multitude of seperated lists and sorting the all these tasks in terms of deadine or priority. As users may simplify commands by omitting prefix or only type into keywords, the implementation need to deal with different situations and filter tasks correctly which is challenging.

##### Implemented Tag Features
- What it does: allows the user to add, delete several tags to every task to give more customized descriptions to their tasks and then list tasks out based on different tags.

- Justification: these features will improve filtering function of the application significantly. Users can use customized tags to describe tasks and filter tasks according to these tags to find tasks catering to their needs well.

- Highlights: This enhancement provides a new criteria for users to manage and navigate their tasks. The implementation is challenging as a new attribute is added to Task object which means all operations on this new attributes need to be implemented. Therefore, a lot of other classes and components is modified and connected to realize new features of tag.


#### Minor Enhancement
- Added **help** command which will show a simple user guide to enable users find valid commands and corresponding format when using **Nuke**.
- Added **Data Manager** which is a base class used to realize some commands. 

#### Other Contributions
- Project managment:
  - Necessary general code enhancements
  - Maintaing the issue tracker
  - Modify codes and add JavaDoc (includes mine and others) to pass CI on _GitHub_ regularly.
  
- Enhancements to existing features:
  - Wrote a majority of JunitTests for existing features to increase coverage to a greate extent ([#78](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/78/files), [#173](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/173), [#176](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/176), [#177](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/176), [#180](https://github.com/AY1920S2-CS2113T-T13-2/tp/pull/180))

- Documentation
  - User Guide:
    - Documented Add Tag/ List Tag/ Delete Tag([#commit](https://github.com/AY1920S2-CS2113T-T13-2/tp/commit/19c7a70cce7e9fa5ad4e896f60704da4406dbaf0#diff-e3e2a9bfd88566b05001b02a3f51d286)) and Add File/Add Task/List Sorted Tasks([#commit](https://github.com/HAOYUN49/tp/commit/d3d542b81d2474459d570576b182ab026c99f7cd#diff-572f9bedcb201b96c74241fb42e29fcf))
    
  - Developer Guide
    - Documented User Stories Section of Appendix Section. ([#commit](https://github.com/AY1920S2-CS2113T-T13-2/tp/commit/37d6f8373731cdc34af91d98e1a713c1eb0e0048#diff-2fff7a74a4bc5eedf2b5dfeb29633018))
    - Documented Class Diagrams of Add Command Classes and List Command Classes([#commit](https://github.com/AY1920S2-CS2113T-T13-2/tp/commit/4d6011dd18122f897b17964a0f1c0e1064b5671f#diff-2fff7a74a4bc5eedf2b5dfeb29633018)), Documented Arichitecture Diagram([#commit](https://github.com/AY1920S2-CS2113T-T13-2/tp/commit/8973f3a786d2510e878e2e08e1dc37089e31725f#diff-2fff7a74a4bc5eedf2b5dfeb29633018)) and Component Interactions Diagram ([#commit](https://github.com/AY1920S2-CS2113T-T13-2/tp/commit/93c4d6284eaf3d127716372dabf3255df76c2f55#diff-2fff7a74a4bc5eedf2b5dfeb29633018))
    - Added up more test cases of Tag features in Manul Testing Section ([#commit](https://github.com/AY1920S2-CS2113T-T13-2/tp/commit/f5bccec92b600f2709ac457bd600da017a2ca2ba#diff-2fff7a74a4bc5eedf2b5dfeb29633018))
    - Documented Design Section ([#commit](https://github.com/AY1920S2-CS2113T-T13-2/tp/commit/51b6430a509a876b5e5de816b99c8c72f18d59c1#diff-2fff7a74a4bc5eedf2b5dfeb29633018))
    
- Community
  - PRs reviewd (with non-trivial 7 review comments: reported bugs during the PE-Dry run) ([#1](https://github.com/HAOYUN49/ped/issues/1), [#2](https://github.com/HAOYUN49/ped/issues/2), [#3](https://github.com/HAOYUN49/ped/issues/3), [#4](https://github.com/HAOYUN49/ped/issues/4), [#5](https://github.com/HAOYUN49/ped/issues/5), [#6](https://github.com/HAOYUN49/ped/issues/6), [#7](https://github.com/HAOYUN49/ped/issues/7))
