# README
This application is a simple ~~Java-EE~~ Jakarta-EE based web application.
These are the following software required, installed on a development machine in order to build the application:

 - Git (to checkout this project)
 - Java 8 SDK, or higher.
 - Maven 3.3 or higher.
 - A Java EE 7 certified enterprise application container. Preferred, Java EE 8 certified application container.

To build, go to the project root (where `pom.xml` resides) and run:

> mvn clean install -X

Inside the `target` folder you will find the `vatit-phonebook.war` application. Deploy the WAR file in any ~~JavaEE~~ Jakarta-EE certified container.

Thank you.
