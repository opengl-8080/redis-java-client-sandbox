@echo off

set THIS_DIR=%~dp0
call %THIS_DIR%..\common.bat
call %THIS_DIR%sentinel-common.bat

call %RESTART_CONTAINER% ^
    %SENTINEL3_PATH% ^
    %SENTINEL3_CONTAINER_NAME% ^
    redis-sentinel

docker container ls
pause
