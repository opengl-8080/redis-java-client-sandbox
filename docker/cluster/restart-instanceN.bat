@echo off

set THIS_DIR=%~dp0
call %THIS_DIR%..\common.bat
call %THIS_DIR%cluster-common.bat

set /p INSTANCE_NUMBER=Input Instnce Number (1,2,3,4,5,6) : 
set INSTANCE_NAME=%INSTANCE_BASE_NAME%%INSTANCE_NUMBER%

docker container start %INSTANCE_NAME%

echo Containers
docker ps

echo Cluster Nodes
docker exec -it %CONTAINER1_NAME% redis-cli cluster nodes

pause
