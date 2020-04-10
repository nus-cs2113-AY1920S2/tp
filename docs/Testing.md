# HappyPills - Testing
 
### Running Tests
There are two ways to run tests.

#### Method 1 : Using IntelliJ JUnit Tests
- To run all test, right-click on `src/test/java` folder and choose `Run 'Tests in HappyPills'`
- To run a subset of tests, you can right-click on a test package, test class, or a test and choose `Run 'TEST'`.
 
#### Method 2 : Using Gradle
- To run all test using Gradle: Open a terminal and run the command `gradlew clean test` 
(Mac/Linux: `./gradlew clean test`)  
  <table>
    <col width="20">
    <col width="200">
   <tr>
     <td><span> &#8505; </span></td>  
     <td> See <a href="https://github.com/AY1920S2-CS2113T-T12-2/tp/blob/master/tutorials/gradleTutorial.md">Gradle Tutorial</a> 
     for more info on how to run tests using Gradle. </td>
     </td>
   </tr>
  </table>
  
### Using Input-Output Tests
- Right-click on `text-ui-test` folder and choose `Open in terminal` 
- Enters `runtest.bat` (Windows) or `runtest.sh` (Mac / Linux) in the terminal to run the script.
- This will run HappyPills with the commands given in the `input.txt` and compare its output in the `ACTUAL.txt` 
with the `EXPECTED.txt`.