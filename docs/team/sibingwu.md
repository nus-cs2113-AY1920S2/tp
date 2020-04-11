# Wu Sibing - Project Portfolio Page

## Overview 
Restaurant Daily Report is a CLI app that generates a whitepaper, 
summarizing the internals of a restaurant. It’s aim is to provide 
restaurant owners a quick overview of how their restaurant is performing 
daily so that restaurant owners can better manage their business operations.

### Summary of Contributions
* **Code contributed:** [code on tP Code Dashboard](https://nus-cs2113-ay1920s2.github.io/tp-dashboard/#breakdown=true&search=sibingwu&sort=groupTitle&sortWithin=title&since=2020-03-01&timeframe=commit&mergegroup=false&groupSelect=groupByRepos)
* **Enhancements implemented:**
  1. Feature: Add a Reservation
      - What it does: Allows users to input `add reservation` to add a reservation with details to the `reservations` list in the application.
      - Justification: This feature is needed for restaurant to add the newly received reservation to the list in the application.
      - Highlights: The `AddReservationCommand` class will examine if the user misses some inputs or delimiters. Adding the reservation is handled by `ReservationList` class.
  
  2. Feature: Mark a Reservation
      - What it does: Allows users to input `delete reservation` to mark a reservation as **invalid** or input `mark reservation` to mark a reservation as **served**.
      - Justification: This feature is needed for restaurant to update the status of a reservation when they finish serving it or the reservation is canceled.
      - Highlights: The `VoidReservationCommand` or `MarkReservationCommand` class will examine if the user misses some inputs or delimiters. 
          Marking the reservation is handled by `ReservationList` class. 
     -    Some restriction is set to follow the common sense: a invalid reservation cannot be marked as served or invalid again; a served reservation cannot be marked as invalid.
       
  3. Feature: List the Reservations
      - What it does: Allows the users to input `list reservation` to list all reservations, or input `list served reservation` to list all **served** reservations, or input `list unserved reservation` to list all **unserved** reservations.
      - Justification: This feature is needed for restaurant to review all reservations.
      - Highlights: When certain types of reservations are required to be listed, the `ListServedCommand` and `ListUnservedCommand` class will do a linear search among `reservations` list and check the reservation status.
  
  4. Feature: Search the Reservations
      - What it does: Allows the users to input `search reservation` to search for a specific reservation or all reservations in a certain day.
      - Justification: This feature is needed for restaurant to look at the details of a specific reservation or review all the reservations in a specific day.
      - Highlights: The `SearchReservationCommand` class will do a linear search among `reservations` list to find the matched reservations.
  
  5. Feature: Load the `Reservation` from the **"report.txt"** file to the `reservations` list
      - What it does: The reservations in the txt file will be automatically loaded into the `reservations` list when the program is started.
      - Justification: This feature is needed for restaurant to view reservations added before.
      - Highlights: The `LoadReservation` class will be in charge of loading reservations in the txt file into the `reservations` list.
  
  6. Feature: Clear the `reservations` list
      - What it does: Allows the user to input `clear reservation` to clear all reservations.
      - Justification: This feature exists in case the restaurant really needs it.
      - Highlights: The `ReservationList` class will clear the `reservations` ArrayList directly.
  
  7. Adding Logger to the Reservation-related section and Ui
      - What it does: It records down all the `add`, `mark` and `delete` activities which can modify the `reservations` list. A fail to set up the logger will also be recorded.
      - Justification: The restaurant need to keep track of the log of its reservations.

* **Contributions to documentation:**
  1. Updated User Guide for all reservation-related features.
  2. Added the legend for command format of `Features` section in User Guide.

* **Contributions to the DG:**
  1. Added description for the `Search Reservation` features.
  2. Added content for Appendix A, C and D
  3. Added reservation-related content to Appendix B and E.

* **Contributions to team-based tasks:** Project Management
  1. Creating the milestones v1.0, v2.0, v2.1
  2. Maintaining the issue tracker: I used the [Github issues](https://github.com/AY1920S2-CS2113-T14-4/tp/issues?q=is%3Aissue+is%3Aclosed+author%3ASibingWu) to allocate the upcoming work.

* **Review/mentoring contributions:** Pull Request reviewed: [#148](https://github.com/AY1920S2-CS2113-T14-4/tp/pull/148), [#149](https://github.com/AY1920S2-CS2113-T14-4/tp/pull/149), [#171](https://github.com/AY1920S2-CS2113-T14-4/tp/pull/171)
