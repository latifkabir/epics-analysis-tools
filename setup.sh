#!/bin/bash

CSS_HOME="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
export CSS_HOME
if [[ -z "${CSS_HOME}" ]]
then
    echo "CSS_HOME is not set"
else
    export JAVA_HOME=$CSS_HOME/jdk-19.0.1
    export PATH=$JAVA_HOME/bin::$PATH
    export CLASSPATH=$CLASSPATH:$CSS_HOME:$CSS_HOME/lib/*:$CSS_HOME/lib/phoebus_lib/*
fi


