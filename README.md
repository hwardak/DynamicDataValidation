# DynamicDataValidation
Dynamic data validation using a HashMap in Java.


This is a part of a personal project I had worked on within Android Studio. 
The program presents the user with a form to fill out.
Within the form is an Employee ID field, the user is to select their own ID.
As each character is entered into the field, the program will immediately 
Validates whether or not given ID is acceptable, and present the user with 
feedback. 

This is accomplished through the use of the Hash Map data structure and 
TextChangeListener feature. The employee ID is used as the 'Key' in
the key-value pair, and the employee's form instance as the 'value.' 
The validation process involves checking whether the key is acceptable 
based on the client's business logic, and whether or not it already 
exists within the EmployeeRecord.

This will provide for the user a very quick and seamless experience.
For the managing group it provides an efficient, and easy-to-manage, 
and manipulate system. The same unique employee ID can also be used 
as the primary identifier in any relational database, furthering the
easy of manage and maintenance. 

Files...

Employee.java
The employee profile, suited to the needs of the client. Each 
instance stored with the local hashmap and a database.

EmployeeRecord.java
The Employee Hash Map and its functions are stored in this class.

EmployeeFormAdd.java
The Android activity that handles user input, and executes the 
dynamic validation process.

employee_form.xml
A single form, used across multiple activities (additional activities 
not included in this repo). Each activity modifies the .xml form 
custom to its needs. 







-Hasib Wardak
