@echo off
echo Testing Batch endpoints...
echo.
echo 1. Getting all batches:
curl -X GET http://localhost:8080/api/v1/batches -H "Content-Type: application/json"
echo.
echo.
echo 2. Getting all purchase details to find valid IDs:
curl -X GET http://localhost:8080/api/v1/purchase-details -H "Content-Type: application/json"
echo.
echo.
echo 3. Testing batches by purchase detail ID 1:
curl -X GET http://localhost:8080/api/v1/batches/purchase-detail/1 -H "Content-Type: application/json"
echo.
pause