Download this code and place it in your C: drive and follow  your run instructions.

After the application gets started, please run the project as maven Test.

All the test source code  is placed in C:\spring-petclinic\src\test\java\org\springframework\samples\petclinic\seleniumWD
 
The test cases use Chrome as browser for all the automation testing.
 
Known issues

1)To change to other browsers, make the changes in C:\spring-petclinic\src\test\java\org\springframework\samples\petclinic\seleniumWD\baseTest.java and
	a.       Uncomment the required browser lines
	b.       Comment the chrome browser lines
2) some test cases may fail in IE and FF where the background colour css property is used.
3)If you want to run on FF, please make sure you sure you use the following combo
	a.       Selenium 3 n for firefox 48+
	b.       Less than selenium 3 for firefox less than 48+