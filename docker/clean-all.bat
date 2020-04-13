@echo off

set THIS_DIR=%~dp0
call %THIS_DIR%common.bat

echo Remove Network
docker network rm %DOCKER_NETWORK%

echo Remove Work Directory
rmdir /s /q %WORK_DIR%

echo Remove Client Container
docker container rm %REDIS_CLIENT_CONTAINER_NAME%

pause
