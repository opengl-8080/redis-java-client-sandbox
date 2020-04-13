@echo off

set THIS_DIR=%~dp0
call %THIS_DIR%..\common.bat
call %THIS_DIR%cluster-common.bat

set /p INSTANCE_NUMBER=Input Instnce Number (1,2,3,4,5,6) : 
set INSTANCE_NAME=redis-instance%INSTANCE_NUMBER%

title %INSTANCE_NAME%
docker exec -it %INSTANCE_NAME% redis-cli -c
