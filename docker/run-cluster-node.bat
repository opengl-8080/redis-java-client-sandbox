@echo off

set INSTANCE_NUMBER=%1
set ORG_CONFIG=%2

set INSTANCE_NAME=%INSTANCE_BASE_NAME%%INSTANCE_NUMBER%
set IP_ADDRESS=%IP_ADDRESS_BASE%%INSTANCE_NUMBER%
set VOLUME_DIR=%CLUSTER_DIR%\instance%INSTANCE_NUMBER%
set VOLUME_PATH=%CLUSTER_PATH%/instance%INSTANCE_NUMBER%

mkdir %VOLUME_DIR%
copy /y %ORG_CONFIG% %VOLUME_DIR%\redis.conf
docker run ^
    -d ^
    --name %INSTANCE_NAME% ^
    --net %DOCKER_NETWORK% ^
    -v %VOLUME_PATH%/redis.conf:%REDIS_CONFIG% ^
    --ip %IP_ADDRESS% ^
    %REDIS_IMAGE% ^
    redis-server %REDIS_CONFIG%

exit /b 0
