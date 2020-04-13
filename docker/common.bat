@echo off

rem set THIS_DIR=%~dp0
rem :INPUT_DOCKER_TYPE
rem set VALID_DOCKER_TYPE=false
rem if "%DOCKER_TYPE%" == "desktop" set VALID_DOCKER_TYPE=true
rem if "%DOCKER_TYPE%" == "toolbox" set VALID_DOCKER_TYPE=true
rem 
rem if %VALID_DOCKER_TYPE% == true (
rem     goto MAIN
rem ) else (
rem     set /p DOCKER_TYPE=Docker ‚ÌŽí—Þ‚ð“ü—Í [desktop or toolbox] : 
rem     goto INPUT_DOCKER_TYPE
rem )
rem 
rem :MAIN
rem if "%DOCKER_TYPE%" == "desktop" call %THIS_DIR%init-desktop.bat
rem if "%DOCKER_TYPE%" == "toolbox" call %THIS_DIR%init-toolbox.bat

set REDIS_CLIENT_CONTAINER_NAME=redis-client
set DOCKER_NETWORK=redis-net

set REDIS_IMAGE=redis:5.0.8
set REDIS_CONFIG=/redis/redis.conf

set WORK_DIR=C:\Users\Public\redis-work
set CLIENT_DIR=%WORK_DIR%\%REDIS_CLIENT_CONTAINER_NAME%
set REPLICATION_DIR=%WORK_DIR%\replication

set VOLUME_PATH=/c/Users/Public/redis-work
set CLIENT_VOLUME_PATH=%VOLUME_PATH%/%REDIS_CLIENT_CONTAINER_NAME%

set CLIENT_BASE_IMAGE=openjdk:11.0.6-jdk

set RUN_CONTAINER=%~dp0run-container.bat
set RESTART_CONTAINER=%~dp0restart-container.bat
set RUN_CLUSTER_NODE=%~dp0run-cluster-node.bat

exit /b 0
