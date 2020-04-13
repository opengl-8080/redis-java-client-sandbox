@echo off

set THIS_DIR=%~dp0
call %THIS_DIR%..\common.bat
call %THIS_DIR%replication-common.bat

echo Run %MASTER_CONTAINER_NAME%
call %RUN_CONTAINER% ^
    %MASTER_DIR% ^
    %MASTER_PATH% ^
    %THIS_DIR%redis-master.conf ^
    %MASTER_CONTAINER_NAME% ^
    redis-server

echo Run %SLAVE_CONTAINER_NAME%
call %RUN_CONTAINER% ^
    %SLAVE_DIR% ^
    %SLAVE_PATH% ^
    %THIS_DIR%redis-slave.conf ^
    %SLAVE_CONTAINER_NAME% ^
    redis-server

docker ps
pause
