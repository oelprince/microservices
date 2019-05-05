#!/bin/bash
/c/Users/oelprince/AppServer/openliberty-18.0.0.2/wlp/bin/server stop microservice
echo "Shutdown Database Container ..."
docker-compose down
echo "Shutdown Event Stream Container ..."
cd kafka-docker
docker-compose down
cd ..

