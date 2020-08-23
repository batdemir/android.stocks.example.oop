[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![API](https://img.shields.io/badge/API-26%2B-red.svg?style=flat)](https://android-arsenal.com/api?level=26)
# Stocks Example OOP Application
This application is an application that shows the IMKB stocks and indices, and you can access the 
search and details.
## Screens
![Home Screen](/screens/Screenshot_20200823-180829_StocksExampleOOP.png) 
![Main Screen Result](/screens/Screenshot_20200823-180836_StocksExampleOOP.png)
![Main Screen Filter](/screens/Screenshot_20200823-180841_StocksExampleOOP.png)
![Main Screen Search](/screens/Screenshot_20200823-180940_StocksExampleOOP.png) 
![Detail Screen](/screens/Screenshot_20200823-180950_StocksExampleOOP.png)
## Used Libraries
### Retrofit
Retrofit turns your REST API into a Java interface.
[Retrofit](https://square.github.io/retrofit/)
### Material Design
Material is a design system created by Google to help teams build high-quality digital experiences 
for Android, iOS, Flutter, and the web.
[Material Design](https://material.io/)
### MPAndroidChart
A powerful & easy to use chart library for Android
[MPAndroidChart] (https://github.com/PhilJay/MPAndroidChart)
### Batdemir Library
Batdemir Library is a library that I developed myself. It contains functions and components that I 
frequently use in coding. If we need to examine in detail, it gives the model used for components 
such as "AlertDialog, AutoCompleteTextView, Spinner, EditText" as a component, and the filling 
operations contain functions for returning the selected items and validating. Using the Retrofit 
library, it contains the controls such as the internet status that includes API connection functions 
and which should be checked in these connections. And of course, it contains connection functions 
used in "Zebra" devices, which are frequently i used in their own studies.
[Batdemir Library](https://github.com/batdemirorg/android.batdemir.library)
### Batdemir Project Template
Batdemir project template, since I usually develop projects from scratch, I developed such a draft 
instead of creating all modules or base classes from scratch. The project template consists of 4 
modules "api, app, entity, utils". There are standard classes Application, BaseActivity, 
BindingFactory, ControllerFactory in the App module.
[Batdemir Project Template](https://github.com/batdemirorg/android.batdemir.template.project)
