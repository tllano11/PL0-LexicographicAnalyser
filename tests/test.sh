#!/bin/bash
clear

declare -a main=('MaintlAntlrLexer' 'MaintlJFlexLexer')

#MAIN="MaintlAntlrLexer"
#MAIN=MaintlJFlexLexer

for i in "${main[@]}"; do    
    FLAG="$CLASSPATH:../target/classes co."$i
    echo "Starting tests for "$i
    java -cp $FLAG test01 test02 - test03
done
