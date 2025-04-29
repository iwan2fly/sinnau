@echo off
echo Building React frontend application...

cd %~dp0src\main\frontend

REM Install dependencies
call npm install

REM Build the React application
call npm run build

REM Create static directory if it doesn't exist
if not exist "..\resources\static" mkdir "..\resources\static"

REM Copy build files to Spring Boot static directory
xcopy /s /y "build\*" "..\resources\static\"

echo Frontend build completed and copied to Spring Boot static directory.