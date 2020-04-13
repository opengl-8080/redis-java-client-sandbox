@echo off

set THIS_DIR=%~dp0
call %THIS_DIR%common.bat

title client
docker start -ai %REDIS_CLIENT_CONTAINER_NAME%
