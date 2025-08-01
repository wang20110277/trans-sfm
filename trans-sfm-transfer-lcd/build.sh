# !/bin/bash

APP_NAME="lcd"
BASE_PATH="output"
DEPLOY_ENVS=("F" "A" "production")
#DEPLOY_ENVS=("ONE")
START_SH="start"
STOP_SH="stop"


# 清空编译路径
rm -rf output
mkdir output

mvn -e -B -U clean package -Dmaven.test.skip=true

for env in ${DEPLOY_ENVS[@]}
do
    if [ $? -ne 0 ] ; then
       exit -1
    fi
    cp -r target/${APP_NAME}*.jar ${APP_NAME}.jar
    cp src/main/resources/config/application-${env}.yaml application-${env}_out.yaml
    tar -cvf ${APP_NAME}.tar ${APP_NAME}.jar ${START_SH} ${STOP_SH} application-${env}_out.yaml

    rm application-${env}_out.yaml
    rm -rf ${APP_NAME}.jar

    mkdir -p output/${env}/${APP_NAME}/${APP_NAME}
    mv ${APP_NAME}.tar output/${env}/${APP_NAME}/${APP_NAME}
    rm -rf ${APP_NAME}.tar
    echo ${APP_NAME}.tar >> output/${env}/${APP_NAME}/${APP_NAME}/order.txt

    cd output/${env}

    tar -cvf ${APP_NAME}.tar ${APP_NAME}/${APP_NAME}/*
    cd -
    rm -rf output/${env}/${APP_NAME}
done