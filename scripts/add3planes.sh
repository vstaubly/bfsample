curl -s -X POST -H "Content-Type: application/json" 'http://127.0.0.1:8080/' --data '{ "name": "Flight 1", "type": "VIP", "size": "Small" }'
curl -s -X POST -H "Content-Type: application/json" 'http://127.0.0.1:8080/' --data '{ "name": "Flight 2", "type": "Cargo", "size": "Large" }'
curl -s -X POST -H "Content-Type: application/json" 'http://127.0.0.1:8080/' --data '{ "name": "Flight 3", "type": "Emergency", "size": "Small" }'
