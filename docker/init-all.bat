@echo off

set THIS_DIR=%~dp0
call %THIS_DIR%common.bat

echo Create Network
docker network create ^
    --subnet 192.168.50.0/24 ^
    --driver bridge ^
    %DOCKER_NETWORK%

echo Create Work Directory
mkdir %WORK_DIR%

echo Create Client Junction
mklink /j %CLIENT_DIR% %THIS_DIR%..

echo Create redis-client Container
docker pull %CLIENT_BASE_IMAGE%
docker container create ^
    --name %REDIS_CLIENT_CONTAINER_NAME% ^
    -it ^
    --net %DOCKER_NETWORK% ^
    -v %CLIENT_VOLUME_PATH%:/%REDIS_CLIENT_CONTAINER_NAME% ^
    %CLIENT_BASE_IMAGE% /bin/bash

docker container ls -a

pause
