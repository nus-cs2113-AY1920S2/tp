# Project Portfolio Page (PPP)  
## Project overview  
**Jikan** is a CLI time-tracker built in Java that  aims to help manage tasks and projects.  
Users can set tags and goals for their entries,  
ultimately being able to keep track of what's left to do and maintain an overview of how time was spent.  

## Summary of contributions  
### Code contributed  
[Link to tP Code Dashboard](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#search=ananda-lye)

### Enhancements implemented
* Find and Filter Activities
    * Developed the `find` and `filter` commands for users to view a sub-list of activities, matching for name and 
    tag keywords.
    * Both commands allow for multiple keywords to be matched with `find` accepting `/` separated keywords and `filter`
    accepting space-separated keywords. Activities which match either keyword will be included in the sub-list
    * Both commands can be limited to searching within the last shown list of activities by including the `-s` flag.
    This is particularly useful when used before graphing functions to omit undesired activities.
        * This can also be included as a single-line user input, separating commands by `;` which reduces the
        number of user input and printing required to achieve the same results.
	
* Last Shown List Implementation
    * Proposed and implemented the last shown list which is used in `list`, `find`, `filter` and `graph` commands.
    * This proposal sets the direction for how the Jikan software is used, as all analysis done by the user revolves
    around this functionality (combining `list` by date range, `find` and `filter` chaining, to allow `graph` to be 
    clutter-free).
    
* Activity Progress and Ui
    * Proposed and implemented the progress percentage based user messages, allowing for the progress bars in `end` and
    `graph` commands.
    

### Contributions to documentation
* Implemented and de-conflicted the high-level flow of the User Guide, distinguishing from Basic to Advanced features for 
improved format standardisation.
* Provided instructions and examples for `find` and `filter` commands and their multiple variations.

### Contributions to the DG
* Drafted write-ups and diagrams in the design section, mainly the overall architecture diagram and class diagram.
* Drafted the `find` and `filter` sections, including the sequence diagram, design considerations, and proposed features.

### Contributions to team-based tasks
* Generated ideas with the team on the set of features for the Jikan application.
* Made use of the issue tracker extensively to track enhancement and bugs found.
* Developed jUnit tests for `find`, `filter` and `list` commands.

### Review/mentoring contributions
* Provided feedback to teammates before and after implementation to ensure that everyone is on the same page.

### Contributions beyond the project team
* Provided feedback to the developer guide of another team.
    * [Reviewing of DG on Week 11](https://github.com/nus-cs2113-AY1920S2/tp/pull/14)
    
* Reported bugs in other team's product in PE dry run.
    * [PED](https://github.com/ananda-lye/ped/issues)

### Contributions to the User Guide (Extracts)
