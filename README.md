# Utilities

This project targetd for having all common utilities that I used in different project which are not under NDA. 

#Level In Source :
1. This is multimaven project. so, it can be build partially and deployable independent. 
2. No unit test planned
3. only intregration test 

#Level in Test :
1. Test Steps -> Reusable test steps, all are test
2. Workflows (group of test steps preset for testing)
3. Tests, for specific purposes
4. Groupped Tests
5. Tests Suits.


Libs:(gradually Increments)
Selenium-java
Junit
Allure-Junit



Running The tests :
Maven Test Run
    ->Locally
    ->From Jenkins or any CI (for CLI support , there will be an entry from Program -> main method)
    ->With Webdriver Remote Driver execution


Converted to multiple Maven Project
 1. Common utils
 2. selenium utils
 3. project pages + tests

#Contribute
Fork it.
Create a branch (git checkout -b myBranch)
Commit your changes (git commit -am "Added feature")
Push to the branch (git push origin myBranch)
Create a new Issue with a link to your branch, or just make a Pull Request.

