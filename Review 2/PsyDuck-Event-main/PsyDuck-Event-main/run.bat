@echo off
set "JAVA_HOME=C:\Program Files\Java\jdk-21.0.10"
set "PATH=%JAVA_HOME%\bin;%PATH%"
echo Starting Stitch Campus Event Hub using JDK 21...
.\mvnw.cmd spring-boot:run
