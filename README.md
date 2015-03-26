# stadtra
laboratory project for partial comletion of CMSC 128

1. clone this repo on your local machine
2. create environment variables:
    TOMCAT_HOME="path/to/tomcat/server" (yes, you should have downloaded tomcat by now)
    JAVA_HOME="path/to/jdk 1.7/installation"
3. download the third party libraries. you can find them in our facebook group
4. import stadtra to eclipse. 
    you will notice that there are many errors. that is okay. 
    there are errors because there are references to third party libraries not yet in the project
5. go to window->show view->servers. create a new server and select tomcat 8. supply info and finish
6. right click stadtra in project explorer and click properties. go to java build path, select libraries
7. select each empty library and click add external jars. select the corresponding jar files (the ones downloaded from fb)
8. on the right, expand java compiler; select annotation processing. make sure the checkboxes are checked and 
    the values src/qrydsl and defaultOverite is true.
7. expand annotation processing and selectfactory path. click add external jars and 
    select querydsl-jpa-3.2.3-apt-hibernate-one-jar.jar in qrydsl
8. click ok and the project will rebuild
9. again right click stadtra in project explorer and click properties. go to jpa and set these values
    platform = generic 2.0
    type = user library (check hibernate)
    9b. add connection->mysql 
        input a useful name and description, and then next
        click new driver definition (beside the dropdown)
        go to jar list tab and add jar/zip
        select mysql-connector-java-5.1.34-bin.jar, ok, finish
    persistent class management = discover annotated classes automatically
    hit ok
10. again right click stadtra in project explorer and click properties. expand jpa and select entity generation
    default package = com.cmsc128.stadtra.entities
    hit ok
11. edit build.properties file
12. window->show view->ant->add build file->select build.xml
    expand stadtra v1.0
    double click 300-deploy-all
13. go to tomcat directory->webapps->stadtra->we-inf->lib and paste contents of runtime jars
