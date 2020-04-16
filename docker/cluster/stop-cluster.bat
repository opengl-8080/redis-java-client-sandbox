@echo off

set THIS_DIR=%~dp0
call %THIS_DIR%..\common.bat
call %THIS_DIR%cluster-common.bat

for /l %%i in (1,1,6) do (
    docker stop %INSTANCE_BASE_NAME%%%i
    docker container rm %INSTANCE_BASE_NAME%%%i
)

pause
