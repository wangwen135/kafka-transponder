#!/bin/bash

cd `dirname $0`
BIN_DIR=`pwd`
cd ..
DEPLOY_DIR=`pwd`
CONF_DIR=$DEPLOY_DIR/conf

LIB_DIR=$DEPLOY_DIR/lib
LIB_JARS=`ls $LIB_DIR|grep .jar|awk '{print "'$LIB_DIR'/"$0}'|tr "\n" ":"`

MAIN_CLASS=com.chedaian.tools.Main
SERVICE_NAME=kafka-transponder

export CLASSPATH=.:$CONF_DIR:$LIB_JARS

nohup java -Dservice.name=$SERVICE_NAME $MAIN_CLASS > sdtout.log 2>&1 &
