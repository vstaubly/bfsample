#!/bin/sh

curl -s -X POST -H "Content-Type: application/json" 'http://127.0.0.1:8080/' --data '{ "name": "Flight 4", "type": "Passenger", "size": "Small" }'
curl -s -X POST -H "Content-Type: application/json" 'http://127.0.0.1:8080/' --data '{ "name": "Flight 5", "type": "Passenger", "size": "Large" }'
curl -s -X POST -H "Content-Type: application/json" 'http://127.0.0.1:8080/' --data '{ "name": "Flight 6", "type": "Emergency", "size": "Large" }'
echo " "
