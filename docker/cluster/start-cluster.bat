@echo off

set THIS_DIR=%~dp0
call %THIS_DIR%..\common.bat
call %THIS_DIR%cluster-common.bat

echo Run Instances
for /l %%i in (1,1,6) do call %RUN_CLUSTER_NODE% %%i %THIS_DIR%redis-cluster.conf

echo Create Cluster
docker exec -it %CONTAINER1_NAME% ^
    redis-cli --cluster create ^
    %IP_ADDRESS_BASE%1:6379 ^
    %IP_ADDRESS_BASE%2:6379 ^
    %IP_ADDRESS_BASE%3:6379

echo Add Slaves
docker exec -it %CONTAINER1_NAME% ^
    redis-cli --cluster add-node ^
    --cluster-slave ^
    %IP_ADDRESS_BASE%4:6379 %IP_ADDRESS_BASE%1:6379

docker exec -it %CONTAINER1_NAME% ^
    redis-cli --cluster add-node ^
    --cluster-slave ^
    %IP_ADDRESS_BASE%5:6379 %IP_ADDRESS_BASE%2:6379

docker exec -it %CONTAINER1_NAME% ^
    redis-cli --cluster add-node ^
    --cluster-slave ^
    %IP_ADDRESS_BASE%6:6379 %IP_ADDRESS_BASE%3:6379

echo Containers
docker ps

echo Cluster Nodes
docker exec -it %CONTAINER1_NAME% redis-cli cluster nodes

pause
