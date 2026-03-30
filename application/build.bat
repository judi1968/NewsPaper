@echo off

REM ====== PATH SANS ESPACES ======
for %%i in ("%CD%") do set "PROJECT_DIR=%%~fsi"

set APP_NAME=newspaper
set SRC_DIR=%PROJECT_DIR%\src\main\java
set WEB_DIR=%PROJECT_DIR%\src\main\webapp
set BUILD_DIR=%PROJECT_DIR%\build
set LIB_DIR=%PROJECT_DIR%\lib
set TOMCAT_WEBAPPS_SOURCE="C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps"
set "TOMCAT_WEBAPPS=%TOMCAT_WEBAPPS_SOURCE%"
set "APP_PATH=%TOMCAT_WEBAPPS%\%APP_NAME%.war"

echo Projet = %PROJECT_DIR%

REM ====== RESET BUILD ======
if exist %BUILD_DIR% rmdir /s /q %BUILD_DIR%
mkdir %BUILD_DIR%\WEB-INF\classes
mkdir %BUILD_DIR%\WEB-INF\lib

REM ====== COPIE DES LIB ======
xcopy "%LIB_DIR%" "%BUILD_DIR%\WEB-INF\lib" /E /I /Y > nul

REM ====== COMPILATION ======
echo Compilation des sources Java...
dir /s /b "%SRC_DIR%\*.java" > "%PROJECT_DIR%\sources.txt"

javac -cp "%LIB_DIR%\*;%BUILD_DIR%\WEB-INF\classes" ^
     -d "%BUILD_DIR%\WEB-INF\classes" ^
     -parameters @"%PROJECT_DIR%\sources.txt"

del "%PROJECT_DIR%\sources.txt"

REM ====== COPIE WEB ======
echo Copie vers le build...
xcopy %WEB_DIR% %BUILD_DIR% /E /I /Y > nul

REM ====== CREATION WAR ======
echo Creation du war...
cd %BUILD_DIR%
jar -cvf %APP_NAME%.war * > nul
cd ..

REM ====== DEPLOIEMENT ======
echo Copie vers le serveur ...
if exist %APP_PATH% del %APP_PATH%
copy %BUILD_DIR%\%APP_NAME%.war %TOMCAT_WEBAPPS_SOURCE%

echo Deploiement termine avec succes.
