# Set Up for TechToday
## Team 14

This is a project template for a small CLI Java project. It uses Gradle for build automation and GitHub Actions for CI.

## Setting Up

Prerequisites: JDK 11, update Intellij to the most recent version

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project dialog first)
1. Set up the correct JDK version
   1. Click `Configure` > `Structure for New Projects` and then `Project Settings` > `Project` > `Project SDK`
   1. If JDK 11 is listed in the drop down, select it. If it is not, click `New...` and select the directory where you installed JDK 11
   1. Click `OK`
1. Click `Import Project`
1. **IMPORTANT: Locate the `build.gradle` file** in the project directory, select it, and click `OK`
1. If there are any further prompts, accept the defaults.
1. After the set up is complete, you can locate the `src/main/java/seedu/techtoday/TechToday.java` file, right-click it, and choose `Run TechToday.main()`. If the setup is correct, you should see something like the below:

         _ **_____________________________________________________________________________**_
         _                                                                                  _
         _                             Hello! Here's TechToday.                             _
         _            Let me show you some technology news to refresh your mind!            _
         _ **_____________________________________________________________________________**_
         _                    Your queries can be of the following forms:                   _
         _                                      1. help                                     _
         _                              2. view [article / job]                             _
         _                       3. save [article / job] INDEX_NUMBER                       _
         _                         4. create [article / job / note]                         _
         _                          5. list [article / job / note]                          _
         _                   6. delete [article / job / note] INDEX_NUMBER                  _
         _              7. addinfo [article / job / note] INDEX_NUMBER EXTRACT              _
         _                                      8. exit                                     _
         _                                                                                  _
         _ **_____________________________________________________________________________**_
              What can I do for you?

         All the required files do not exist. We will create completely new files to save your data.
         __________________________________________________________________________________________


Type exit and press enter to let the execution proceed to the end. Also note how Intellij is now using Gradle to run your code (you can make Intellij run the code without Gradle [this way](tutorials/assets/RunUsingIntellij.png)).

* Acknowledgement- The set-up portion is a direct adaptation of the set-up instructions given to us for this project.


## Build Automation using Gradle

This project is already configured to use Gradle. Read the following tutorial to learn how to use gradle to do tasks such as running tests, checking code against teh style rules, and generating the JAR file.
 
* [Gradle Tutorial](tutorials/gradleTutorial.md)

## Testing

To run _I/O redirection_ tests (aka _Text UI tests_), navigate to the `text-ui-test` and run the `runtest(.bat/.sh)` script.

To run JUnit tests (after you have added JUnit tests), you can run the `test` Gradle task.

## CI using GitHub Actions

The project is already configured to use [GitHub actions](https://github.com/features/actions). When you push a commit to your fork or PR against an upstream repo, GitHub actions will run automatically to build and verify the product.
