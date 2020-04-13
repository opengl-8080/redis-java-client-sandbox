@echo off

set VOLUME_DIR=%1
set VOLUME_PATH=%2
set ORG_CONFIG=%3
set CONTAINER_NAME=%4
set IP_ADDRESS=%5

mkdir %VOLUME_DIR%
copy /y %ORG_CONFIG% %VOLUME_DIR%\redis.conf
docker run ^
    -d ^
    --rm ^
    --name %CONTAINER_NAME% ^
    --net %DOCKER_NETWORK% ^
    -v %VOLUME_PATH%/redis.conf:%REDIS_CONFIG% ^
    --ip %IP_ADDRESS% ^
    %REDIS_IMAGE% ^
    redis-server %REDIS_CONFIG%
    
exit /b 0
