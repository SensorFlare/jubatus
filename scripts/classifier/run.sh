#!/bin/bash

cd ../../

source /opt/jubatus/profile

kill -9 $(ps aux | grep jubaclassifier | grep "resources" | awk '{print $2}')

nohup jubaclassifier --configpath src/main/resources/gender.json  > nohup.out &

mvn compile

mvn exec:java -Dexec.mainClass="com.sensorflare.classification.GenderClassifier"

kill -9 $(ps aux | grep jubaclassifier | grep "resources" | awk '{print $2}')