@echo off

set VOLUME_PATH=%1
set CONTAINER_NAME=%2
set EXECUTABLE=%3

docker run ^
    -d ^
    --rm ^
    --name %CONTAINER_NAME% ^
    --net %DOCKER_NETWORK% ^
    -v %VOLUME_PATH%/redis.conf:%REDIS_CONFIG% ^
    %REDIS_IMAGE% ^
    %EXECUTABLE% %REDIS_CONFIG%
    
exit /b 0
