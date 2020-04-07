PROJECT: Nuke
==============================

---

Overview
========

This Nuke application is a simple yet powerful task management system that is dedicated to providing NUS students efficient organisation of *modules* and *tasks*. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 17 kLoC.

Summary of contributions
========================
**Code contributed**: \[[Functional code](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#breakdown=true&search=a11riseforme)\]

**Major enhancement**: 

1. added **the ability to undo/redo previous commands**

- What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
- Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
- Highlights: This enhancement does not affects existing commands, but it required an in-depth analysis of the data formation of the current project. The implementation too was challenging as it required to add in a middle step in the main function of the program. I have to make sure that it won't go wrong and adversely affects the main program.

added the **ability to preload the information of all modules provided by NUS**

- What it does: Preloads the information of all modules provided by NUS, including module codes and module titles.
- Justification: This feature makes our product more targeted at our intended user(NUS students) more, it also enables the auto-complete features when users are adding their modules by entering module codes, which enhances usability.
- Highlights: This enhancement is based on the json file which is retrieved from the [NUSMods](https://api.nusmods.com/v2/) API, and it has to deal with the case when there is no relevant json file on the disk, then the product needs to fetch the content from [NUSMods](https://api.nusmods.com/v2/) API, and if the user has no network connection(which is a very rare case), the product also needs to be compatible with such extreme case.
- Credits: 
  - [NUSMods](https://api.nusmods.com/v2/) API: For providing the API to retrieve the information of all the modules provided by NUS.
  - [FastJson](https://github.com/alibaba/fastjson) library: For parsing the json file.
  - [Commons IO](http://commons.apache.org/proper/commons-io/) from Apache: For downloading the json file from [NUSMods](https://api.nusmods.com/v2/) API.

**Minor enhancement**: added a history command that allows the user to navigate to previous commands using up/down keys.


-   **Other contributions**:

    -   Project management:

        -   Managed releases `v1.0` ,  `v2.0-cli` (2 releases) on GitHub

    -   Enhancements to existing features:

        -   Updated the GUI color scheme (Pull requests [\#33](https://github.com), [\#34](https://github.com))

        -   Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](https://github.com), [\#38](https://github.com))

    -   Documentation:

        -   Did cosmetic tweaks to existing contents of the User Guide: [\#14](https://github.com)

    -   Community:

        -   PRs reviewed (with non-trivial review comments): [\#12](https://github.com), [\#32](https://github.com), [\#19](https://github.com), [\#42](https://github.com)

        -   Contributed to forum discussions (examples: [1](https://github.com), [2](https://github.com), [3](https://github.com), [4](https://github.com))

        -   Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com), [2](https://github.com), [3](https://github.com))

        -   Some parts of the history feature I added was adopted by several other class mates ([1](https://github.com), [2](https://github.com))

    -   Tools:

        -   Integrated a third party library (Natty) to the project ([\#42](https://github.com))

        -   Integrated a new Github plugin (CircleCI) to the team repo

Contributions to the User Guide
===============================

<table><colgroup><col style="width: 100%" /></colgroup><tbody><tr class="odd"><td><p><em>Given below are sections I contributed to the User Guide. They showcase my ability to write documentation targeting end-users.</em></p></td></tr></tbody></table>

Contributions to the Developer Guide
====================================

<table><colgroup><col style="width: 100%" /></colgroup><tbody><tr class="odd"><td><p><em>Given below are sections I contributed to the Developer Guide. They showcase my ability to write technical documentation and the technical depth of my contributions to the project.</em></p></td></tr></tbody></table>

PROJECT: PowerPointLabs
=======================

---

*{Optionally, you may include other projects in your portfolio.}*