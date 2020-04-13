@echo off

set THIS_DIR=%~dp0
call %THIS_DIR%..\common.bat
call %THIS_DIR%replication-common.bat

title master
docker exec -it %MASTER_CONTAINER_NAME% redis-cli
