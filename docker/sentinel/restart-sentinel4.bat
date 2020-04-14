@echo off

set THIS_DIR=%~dp0
call %THIS_DIR%..\common.bat
call %THIS_DIR%sentinel-common.bat

call %RESTART_CONTAINER% ^
    %SENTINEL4_PATH% ^
    %SENTINEL4_CONTAINER_NAME% ^
    redis-sentinel

docker container ls
pause
