@echo off

set THIS_DIR=%~dp0
call %THIS_DIR%..\common.bat
call %THIS_DIR%cluster-common.bat

docker stop %CONTAINER1_NAME%
docker stop %CONTAINER2_NAME%
docker stop %CONTAINER3_NAME%
docker stop %CONTAINER4_NAME%
docker stop %CONTAINER5_NAME%
docker stop %CONTAINER6_NAME%

pause
