#!/usr/bin/env bash

#BUILD
echo "*******************************"
echo "  Compile dashboard-download project"
echo "*******************************"

cd server

if [[ $# -eq 1 ]]
then
    if [[ $1 != "skipTests" ]]
    then
        echo "********* Invalid argument supplied! **********"
        exit 1
    else
        echo "********* Skipping TESTS! **********"
        mvn clean install -DskipTests
    fi
else
    mvn clean install
fi

cd ..

echo "**********************************"
echo "  Create dashboard-download dockerImage"
echo "**********************************"

    docker-compose -f docker-compose.yml build
    docker-compose -f docker-compose.yml up
