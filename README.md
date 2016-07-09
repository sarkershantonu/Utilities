# Automation-UI

Initial Plan :

Level In Source :
1. Common Usages : -> Package Start with Org.
    -> Utils
    -> Managers
    ->
2. Project Specific : -> Package Start with Project.
    -> Helpers : Project Specific Helpers
    -> Pages : POM Model
3.

Level in Test :
1. Test Steps -> Reusable test steps, all are test
2. Workflows (group of test steps preset for testing)
3. Tests, for specific purposes
4. Groupped Tests
5. Tests Suits.


Libs:(gradually Increments)
Selenium-java
Junit
Allure-Junit
SL4J
AspectJ

Running The tests :
Maven Test Run
    ->Locally
    ->From Jenkins or any CI (for CLI support , there will be an entry from Program -> main method)
    ->With Webdriver Remote Driver execution
    
    
Converted to multiple Maven Project 
 1. Common utils
 2. selenium utils
 3. project pages + tests