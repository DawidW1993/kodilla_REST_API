call runcrud
if "%ERRORLEVEL%" == "0" goto operwebsite
echo.
echo GRADLEW BUILD has errors - breaking work
goto fail

:operwebsite
start chrome http://localhost:8080/crud/v1/task/getTasks

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.