#!/bin/bash
echo "Start Main Database Container ..."
docker-compose up -d
echo "Start Event Stream Container ..."
cd kafka-docker
docker-compose up -d
cd ..
#echo "Build Project and Publish Artifacts ..."
#mvn clean install
echo "Start Application Service"
/c/Users/oelprince/AppServer/openliberty-18.0.0.2/wlp/bin/server start microservice
tail -f /c/Users/oelprince/AppServer/openliberty-18.0.0.2/wlp/usr/servers/microservice/logs/messages.log
