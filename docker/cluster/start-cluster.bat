@echo off

set THIS_DIR=%~dp0
call %THIS_DIR%..\common.bat
call %THIS_DIR%cluster-common.bat

echo Run Instances
call %RUN_CLUSTER_NODE% ^
    %CONTAINER1_DIR% ^
    %CONTAINER1_PATH% ^
    %THIS_DIR%redis-cluster.conf ^
    %CONTAINER1_NAME% ^
    192.168.50.101

call %RUN_CLUSTER_NODE% ^
    %CONTAINER2_DIR% ^
    %CONTAINER2_PATH% ^
    %THIS_DIR%redis-cluster.conf ^
    %CONTAINER2_NAME% ^
    192.168.50.102

call %RUN_CLUSTER_NODE% ^
    %CONTAINER3_DIR% ^
    %CONTAINER3_PATH% ^
    %THIS_DIR%redis-cluster.conf ^
    %CONTAINER3_NAME% ^
    192.168.50.103

call %RUN_CLUSTER_NODE% ^
    %CONTAINER4_DIR% ^
    %CONTAINER4_PATH% ^
    %THIS_DIR%redis-cluster.conf ^
    %CONTAINER4_NAME% ^
    192.168.50.104

call %RUN_CLUSTER_NODE% ^
    %CONTAINER5_DIR% ^
    %CONTAINER5_PATH% ^
    %THIS_DIR%redis-cluster.conf ^
    %CONTAINER5_NAME% ^
    192.168.50.105

call %RUN_CLUSTER_NODE% ^
    %CONTAINER6_DIR% ^
    %CONTAINER6_PATH% ^
    %THIS_DIR%redis-cluster.conf ^
    %CONTAINER6_NAME% ^
    192.168.50.106

echo Create Cluster
docker exec -it %CONTAINER1_NAME% ^
    redis-cli --cluster create ^
    192.168.50.101:6379 ^
    192.168.50.102:6379 ^
    192.168.50.103:6379

echo Add Slaves
docker exec -it %CONTAINER1_NAME% ^
    redis-cli --cluster add-node ^
    --cluster-slave ^
    192.168.50.104:6379 192.168.50.101:6379

docker exec -it %CONTAINER1_NAME% ^
    redis-cli --cluster add-node ^
    --cluster-slave ^
    192.168.50.105:6379 192.168.50.102:6379

docker exec -it %CONTAINER1_NAME% ^
    redis-cli --cluster add-node ^
    --cluster-slave ^
    192.168.50.106:6379 192.168.50.103:6379

echo Containers
docker ps

echo Cluster Nodes
docker exec -it %CONTAINER1_NAME% redis-cli cluster nodes

pause
