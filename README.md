# README
This application is a simple ~~Java-EE~~ Jakarta-EE based web application.
These are the following software required, installed on a development machine in order to build the application:

 - Git (to checkout this project)
 - Java 8 SDK, or higher.
 - Maven 3.3 or higher.
 - Docker (Docker CE for development environment)

To build, go to the project root (where `pom.xml` resides) and run:

> mvn clean -X -Pdocker 

This will build & package the project as well as run the `docker` profile to build the maven image. 
Make sure that the docker daemon `dockerd` is running prior to running the command.

To run the docker instance, simply:

> mvn install -Pdocker

This will not only build the package but deploy the docker package on your docker engine.

I hope this will help you get understand docker. I had fun playing with this.

Thank you.
