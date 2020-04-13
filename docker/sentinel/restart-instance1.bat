@echo off

set THIS_DIR=%~dp0
call %THIS_DIR%..\common.bat
call %THIS_DIR%sentinel-common.bat

call %RESTART_CONTAINER% ^
    %MASTER_PATH% ^
    %MASTER_CONTAINER_NAME% ^
    redis-server

docker container ls
pause
