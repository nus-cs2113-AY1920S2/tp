@echo off
setlocal enableextensions
pushd %~dp0

cd ..
call gradlew shadowJar

cd build\libs
for /f "tokens=*" %%a in (
    'dir /b duke-0.0.1.jar'
) do (
    set jarloc=%%a
)

cd ..\..\text-ui-test

java -jar ..\build\libs\%jarloc% < input.txt > ACTUAL.TXT

FC ACTUAL.TXT EXPECTED.TXT >NUL && ECHO Test passed! || Echo Test failed!

FC ACTUAL.TXT EXPECTED.TXT
