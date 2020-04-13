# PROJECT: Professor Assistant Console (PAC)

# Overview

Pac is a event scheduler which helps to keep track of events. 
Attendance and performance of students can be tracked under each event. 
The user interacts with it using a Command Line Interface (CLI). 
It is written in Java, and has about 8k lines of code (LOC).

# Summary of Contribution

## Major Enhancement 1: added the ability to create an `Event`

- **What it does**: It allows the user to add, edit and delete an event, which 
records its name, date, time and venue. A user can also link a list of students 
to any event to keep track of their attendance and performance in that event.

- **Justification**: `name`, `date`, `time`, `venue` are important details of 
an event. Recording attendance and performance is essential to the relevance 
of this application for a professor.

- **Highlight**: This is the backbone of the scheduler. All other features 
of this app, in one way or another, are linked to an `Event`.

## Major Enhancement 2: implemented `EventParser`

- **What it does**: It breaks down a `Event`-related command to return the 
appropriate `Command` object.

- **Justification**: This allows invalid user inputs to be identified, hence 
properly handled and shown to the user.

- **Highlight**: `EventParser` is essential for all `Event`-related commands to 
work. Other developers can add/modify existing `Event`-related command without 
worrying about parsing.

## Major Enhancement 3: implemented the control flow of the application

- **What it does**: It decides how the application starts up, and shuts down. 
This involves `Interpreter`, which decides the command category and its type
from a user input.

- **Highlight**: All developers need to understand how an user input leads to 
its response.

## Minor Enhancement: added the ability to load/save existing data

## Code contributed: [here](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#=undefined&search=lowjiayee)

## Other contributions

### Testing

I have written unit tests for `Event`, `EventParser` and `Interpreter`.

I have also written text-based UI test to test `Event`-related operations, such 
as `add`, `delete`, `edit` and `list`.

I have also written manual testing instructions for `Event` and `Storage`.

### Documentation

I have written in the User Guide and Developer Guide all the features/enhancements I have mentioned above.

Besides that, I have also written descriptions and drawn diagrams for Architecture, 
Command, Storage, Event (class) and Event (sequence).

### Community

- PRs reviewed (with non-trivial review comments): 
[#31](https://github.com/AY1920S2-CS2113T-T12-4/tp/pull/31), 
[#33](https://github.com/AY1920S2-CS2113T-T12-4/tp/pull/33), 
[#36](https://github.com/AY1920S2-CS2113T-T12-4/tp/pull/36), 
[#59](https://github.com/AY1920S2-CS2113T-T12-4/tp/pull/59), 
[#94](https://github.com/AY1920S2-CS2113T-T12-4/tp/pull/94), 
[#98](https://github.com/AY1920S2-CS2113T-T12-4/tp/pull/98),
[#113](https://github.com/AY1920S2-CS2113T-T12-4/tp/pull/113),
[#114](https://github.com/AY1920S2-CS2113T-T12-4/tp/pull/114),
[#116](https://github.com/AY1920S2-CS2113T-T12-4/tp/pull/116), 
[#161](https://github.com/AY1920S2-CS2113T-T12-4/tp/pull/161), and more.
