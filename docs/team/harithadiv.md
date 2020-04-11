# Haritha Divakaran - Project Portfolio Page
## PROJECT: Professor Assistant Console (PAC)

## Overview
Pac is a event scheduler used by professors which helps to keep track of events.
The user interacts with it using a Command Line Interface (CLI).
It is written in Java, and has about 4.5k LOC.

## Summary of Contributions

### Major enhancement 1: added the ability to view events in a `calendar` 
- **What it does**: Allows the user to view events in a calendar format. The user has to input only the 
semester and the academic year, and the app will show the events that fall only in that particular time frame.

- **Justification**: This feature improves the product significantly as it gives the user a clear, convenient view of
all the events that he/she is concerned with only in a calendar format. Moreover, it gives an option for the user to 
view the events in another way besides a list format, which might be too cluttered. 

- **Highlights**: This enhancement required an in-depth analysis of design alternatives. The implementation too was 
challenging as it required displaying all the events in the correct corresponding months without compromising the 
structural display of the calendar. Moreover, it required identifying the events that only fall under a particular timeline 
and storing them in a data structure which would lead to easier display of the events in an orderly fashion as a calendar view. 


### Major enhancement 2: added the ability to add date and time to events
**What it does**: Allows the user to add date and time to an event in the format: yyyy-mm-dd HHmm, and displays it to
user in this format: E, MMM dd yyyy HHmm (displays the day of the week, and the name of the month) for easier viewing.

**Justification**: This feature allows the user to add date and time in a pure numerical format and display it in a format 
which is easier, by displaying the day of the week, ie. Mon-Sun and the month name, ie. Jan-Dec. 

**Highlights**: This enhancement was challenging as adding date and time to an event is an *optional* feature. Therefore, 
an empty string cannot be considered as a DateTimeParseException to be thrown. Thus, it required 
analysis of design alternatives to make sure that a wrong datetime format is indeed an exception to be thrown whereas an empty string is not. 
Moreover, it affected calendar implementation as not all events can be extracted for calendar view. 


### Major enhancement 3: added the ability to `edit` event particulars
**What it does**: Allows the user to edit event particular such as its `name`, `datetime`, `venue` and the `event` 
itself. 

**Justification**: This feature improves the product significantly as it provides a convenient way to rectify the user's
mistake when typing the particulars of an event. 

**Highlights**: This enhancement required changes to the existing EventParser. It also required more commands
to be added to EventCommandInterpreter and the UI class. 

### Minor enhancement 1: implemented `CalendarParser`
### Minor enhancement 2: added the ability to add Seminar
### Minor enhancement 3: added the ability to list event and seminar 

### Code contributed: [here](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#breakdown=true&search=harithadiv&sort=groupTitle&sortWithin=title&since=2020-03-01&timeframe=commit&mergegroup=false&groupSelect=groupByRepos)

### Other contributions:

#### Testing :
- I have written unit tests for `CalendarCommandInterpreter` and `seminar`

#### Necessary general code enhancements
- Improved readability of code by implementing private static final Strings and integers 
in most of the classes.

#### Update document that are not specific to a feature 
- Added target user profile and starting up instructions in user guide
- Added to FAQ in the user guide
- Added instructions for manual testing in the developer guide
- Added command summary in the user guide
- Added possible console messages and reasons in user guide 

#### Maintaining issue tracker
- Added issues pertaining to bugs, feature flaws or features related to features mentioned above. 
- Linked these issues to my PRs promptly. 

### Documentation
- I have written in the User Guide and Developer Guide features related to calendar and datetime features. 

### Community 
- PRs reviewed (with non-trivial comments)
[#29](https://github.com/AY1920S2-CS2113T-T12-4/tp/pull/29)
[#160](https://github.com/AY1920S2-CS2113T-T12-4/tp/pull/160)
[#178](https://github.com/AY1920S2-CS2113T-T12-4/tp/pull/178)


