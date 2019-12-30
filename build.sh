#!/usr/bin/env bash

#BUILD
echo "*******************************"
echo "  Compile dashboard-download project"
echo "*******************************"

mvn clean install

echo "**********************************"
echo "  Create dashboard-download dockerImage"
echo "**********************************"

    docker-compose -f docker-compose.yml build
    docker-compose -f docker-compose.yml up

