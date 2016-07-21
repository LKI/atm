@echo off

setlocal

# Get launch directory
set LAUNCH_DIRECTORY=%~dp0
set LAUNCH_DIRECTORY=%LAUNCH_DIRECTORY:~0,-1%

# Make sure JAVA_HOME is in front of all other paths
if not "%JAVA_HOME%"=="" set PATH=%JAVA_HOME%\bin;%PATH%

# Run java command with "atm.dir" property
java -Datm.dir="%LAUNCH_DIRECTORY%" -jar "%LAUNCH_DIRECTORY%\lib\atm.jar" %*
