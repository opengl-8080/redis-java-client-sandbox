@echo off

set THIS_DIR=%~dp0
call %THIS_DIR%..\common.bat
call %THIS_DIR%sentinel-common.bat

docker stop %MASTER_CONTAINER_NAME%
docker stop %SLAVE1_CONTAINER_NAME%
docker stop %SLAVE2_CONTAINER_NAME%
docker stop %SENTINEL1_CONTAINER_NAME%
docker stop %SENTINEL2_CONTAINER_NAME%
docker stop %SENTINEL3_CONTAINER_NAME%
docker stop %SENTINEL4_CONTAINER_NAME%

pause
