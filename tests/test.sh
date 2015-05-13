#!/bin/bash
clear

#MAIN="MaintlAntlrLexer"
MAIN=MaintlJFlexLexer
FLAG="$CLASSPATH:../target/classes co."$MAIN

echo "Starting tests for"$Main

java -cp $FLAG test01 test02 - test03
