# Cai Runze - Project Portfolio Page

## Overview
Personal Module Manager is a software that tracks the number of modular credits, modules taken and modules required 
for its user to graduate. The software is for NUS Students who want to plan their modules per semester in an easy way 
and it is optimized for those who want to type fast with CLI. It is written in Java.

### Summary of Contributions

#### Code contributed
The given link is my [code contribution](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#=undefined&search=renzotsai).

#### Enhancements implemented
In this project, I design the part of Storage, Exception and some commands.

##### Storage
Storage in our software include three parts: `Storage Available Modules List`, `Storage Semester List` and 
`Storage Person Info`. `Available Modules List` and `Semester List` are stored in CSV file and Person Info is 
stored in TXT file. And each time the user runs the software, these three file will be loaded.

Each time, after modifying `Available Modules List` and `Semester List`, this software will update their CSV files
to make sure user's data will not be lost if the software meets a unexpected halt. 

As for Person Info, after several changes to our code, now the Person info in this version keeps the same and will not 
be changed. But I still keep this part for future enhancements e.g: support multi-user.


##### Exception
Exception in our software include three parts: `Input Exception`, `Runtime Exception` and 
`Storage Exception`. All of them extend from `ModuleManagerException`.

`Input Exception` is used in handling exception when user's input is a wrong format. `Runtime Exception` is used in 
handling all the exceptions when execute user's command. And `Storage Exception`is used in handling the exceptions when
load and store data.

`Input Exception`, `Runtime Exception` will be thrown during parse and execute the command and will be caught in 
`Duke().run()`. `Storage Exception` will be thrown during load and store and will be caught in `Duke()`. All the exceptions 
will be recorded in log file.

##### Commands
- Add to semester command: This command allows users to assign different modules to different semester.

- View all modules command: This command allows users to view all the available modules in the `Available Modules List`.

#### Contributions to documentation
- Write the Draft of our UG v1.0 and v2.0 and revised them.

#### Contributions to the Developer Guide
- Write the part of `Add to semseter` and draw a UML diagram to explain this implementation.

#### Contributions to team-based tasks

- Setting up organization, team repo and Gradle.

- Maintaining the issue tracker.

- Release management: update the jar file of release v2.0 after finding something wrong with the previous one.

#### Review/mentoring contributions:
- Review over 80% code enhancement's PRs and give code revise suggestion by making a PR to their branch. 
e.g [#42 PR in our team repo](https://github.com/AY1920S2-CS2113-T15-3/tp/pull/42) and 
[Revise suggestion PR to teammate's repo](https://github.com/chengTzeNing/tp/pull/3).

- Help teammates understanding some data structures in our code by making some slides to explain. 
[Google Slides: Available Modules List's data structure](https://docs.google.com/presentation/d/1UeuonNaPafGD5bX36J_iroB_umS6lvyD37ECp8OfvSI/edit)

#### Contributions beyond the project team
- responses posted in forum: [a possible answer to #89](https://github.com/nus-cs2113-AY1920S2/forum/issues/89#issuecomment-605184683)
(why Java CI build failure only for windows)
- bugs reported in other team's products: [bugs reports to CS2113T-T12-4](https://github.com/RenzoTsai/ped/issues)