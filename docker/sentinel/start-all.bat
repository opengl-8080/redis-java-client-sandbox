@echo off

set THIS_DIR=%~dp0
call %THIS_DIR%..\common.bat
call %THIS_DIR%sentinel-common.bat

echo Run Master Container
call %RUN_CONTAINER% ^
    %MASTER_DIR% ^
    %MASTER_PATH% ^
    %THIS_DIR%redis-master.conf ^
    %MASTER_CONTAINER_NAME% ^
    redis-server

echo Run Slave Containers
call %RUN_CONTAINER% ^
    %SLAVE1_DIR% ^
    %SLAVE1_PATH% ^
    %THIS_DIR%redis-slave.conf ^
    %SLAVE1_CONTAINER_NAME% ^
    redis-server
call %RUN_CONTAINER% ^
    %SLAVE2_DIR% ^
    %SLAVE2_PATH% ^
    %THIS_DIR%redis-slave.conf ^
    %SLAVE2_CONTAINER_NAME% ^
    redis-server

echo Run Sentinels
call %RUN_CONTAINER% ^
    %SENTINEL1_DIR% ^
    %SENTINEL1_PATH% ^
    %THIS_DIR%redis-sentinel.conf ^
    %SENTINEL1_CONTAINER_NAME% ^
    redis-sentinel
call %RUN_CONTAINER% ^
    %SENTINEL2_DIR% ^
    %SENTINEL2_PATH% ^
    %THIS_DIR%redis-sentinel.conf ^
    %SENTINEL2_CONTAINER_NAME% ^
    redis-sentinel
call %RUN_CONTAINER% ^
    %SENTINEL3_DIR% ^
    %SENTINEL3_PATH% ^
    %THIS_DIR%redis-sentinel.conf ^
    %SENTINEL3_CONTAINER_NAME% ^
    redis-sentinel
call %RUN_CONTAINER% ^
    %SENTINEL4_DIR% ^
    %SENTINEL4_PATH% ^
    %THIS_DIR%redis-sentinel.conf ^
    %SENTINEL4_CONTAINER_NAME% ^
    redis-sentinel

docker ps
pause
exit /b 0
