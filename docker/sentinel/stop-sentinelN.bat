@echo off

set THIS_DIR=%~dp0
call %THIS_DIR%..\common.bat
call %THIS_DIR%sentinel-common.bat

set /p CONTAINER_NUMBER=��~���� Sentinel �̃R���e�i�ԍ� : 
set CONTAINER_NAME=%SENTINEL_CONTAINER_NAME_BASE%%CONTAINER_NUMBER%


docker container stop %CONTAINER_NAME%

docker container ls
pause
