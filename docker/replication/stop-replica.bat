@echo off

set THIS_DIR=%~dp0
call %THIS_DIR%..\common.bat
call %THIS_DIR%replication-common.bat

docker stop %MASTER_CONTAINER_NAME%
docker stop %SLAVE_CONTAINER_NAME%

pause
