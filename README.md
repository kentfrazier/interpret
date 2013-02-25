interpret
=========

A simple proof-of-concept web service for interpreting natural language queries into structured responses.

Steps to Get it Running
-----------------------
 * Install [Java EE JDK][java], and make sure it is on your path.
 * Install [Apache Maven][maven], and make sure it is on your path.
 * git clone this repository.
 * cd into the `interpret-server` directory.
 * run the `runtestserver.sh` script, which should bootstrap the project.
   Please note that this will have to download a lot of dependencies,
   including several quite large language models totaling a few hundred MB.
   It may take a while.
 * Open a web browser and go to [the parser page][parser].
 * To see minimal time extraction in effect, go to [the intepreter page][interpreter].
 * If you already have stuff running on port 8080, the script can take an
   argument specifying a different port, but the links listed above will need
   to be modified, of course.

[java]: http://www.oracle.com/technetwork/java/javaee/downloads/java-ee-sdk-6u3-jdk-7u1-downloads-523391.html
[maven]: http://maven.apache.org/download.cgi
[parser]: http://localhost:8080/parser/?phrase=This%20is%20a%20sentence%20I%20want%20to%20parse.
[interpreter]: http://localhost:8080/interpret/?phrase=I%20want%20to%20meet%20a%20mobile%20developer%20in%20austin%20this%20month
