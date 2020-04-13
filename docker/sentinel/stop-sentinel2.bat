@echo off

set THIS_DIR=%~dp0
call %THIS_DIR%..\common.bat
call %THIS_DIR%sentinel-common.bat

docker container stop %SENTINEL2_CONTAINER_NAME%

docker container ls
pause
