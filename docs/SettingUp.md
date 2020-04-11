#HappyPills - Setting Up

### 1. Prerequisites
 
1.  JDK 11 or above
 
2.  IntelliJ IDE
 <table>
   <col width="20">
   <col width="200">
  <tr>
    <td><span> &#8505; </span></td>
    <td>IntelliJ by default has Gradle and JavaFx plugins installed. Do not disable them. 
    If you have disabled them, go to <code>File</code> > <code>Settings</code> > 
    <code>Plugins</code> to re-enable them.</td>
  </tr>
 </table>
 
### 2. Setting up the project on your computer
1.  Fork this repository, and clone the fork to your computer
 
2.  Open the IntelliJ IDE. If you are not in the welcome screen, click `File` &gt; `Close Project` to close the existing project.
 
3.  Set up the correct JDK version for Gradle
 
    1.  Click `Configure` &gt; `Project Defaults` &gt; `Project Structure`
 
    2.  Click `New...` and find the directory of the JDK
 
4.  Click on `Import Project`
 
5.  Locate and select the `build.gradle` file, then click `OK`
 
6.  Click `Open as Project`
 
7.  Click `OK` to use the default settings provided
 
### 3. Verifying the Setup
1.  In Intellij, run `seedu.happypills.HappyPills` and try a few commands.
    * Commands that you can try to get familiar with HappyPills:
        - `help`: list all the available commands in HappyPills
        - `add patient /ic S9876543F /n Eve /p 91265432 /dob 22/05/1999 /b O- /a School /rm Best Friend with Mallory`:
        Add patient's information in HappyPills.
        - `list patient`: list all the patients stored in HappyPills.

 
