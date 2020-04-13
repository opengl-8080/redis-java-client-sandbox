@echo off

set THIS_DIR=%~dp0
call %THIS_DIR%..\common.bat
call %THIS_DIR%sentinel-common.bat

call %RESTART_CONTAINER% ^
    %SLAVE1_PATH% ^
    %SLAVE1_CONTAINER_NAME% ^
    redis-server

docker container ls
pause
