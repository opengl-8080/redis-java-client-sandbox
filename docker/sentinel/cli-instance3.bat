@echo off

set THIS_DIR=%~dp0
call %THIS_DIR%..\common.bat
call %THIS_DIR%sentinel-common.bat

title instance2
docker exec -it %SLAVE2_CONTAINER_NAME% redis-cli
