#!/bin/bash

clear
PS3='Enter your choice: '
options=("Run Antlr tests" "Run Antlr S.I." "Run Antlr misc" "Run JFlex tests"
	 "Run JFlex S.I." "Run JFlex misc" "Quit")
select opt in "${options[@]}"
do
    case $opt in
        "Run Antlr tests")
            FLAG="$CLASSPATH:../target/classes co.MaintlAntlrLexer"
	    java -cp $FLAG test01 test02 test03
            ;;
        "Run Antlr S.I.")
            FLAG="$CLASSPATH:../target/classes co.MaintlAntlrLexer"
	    java -cp $FLAG -
            ;;
        "Run Antlr misc")
            FLAG="$CLASSPATH:../target/classes co.MaintlAntlrLexer"
	    java -cp $FLAG test01 test02 - test03
            ;;
	"Run JFlex tests")
            FLAG="$CLASSPATH:../target/classes co.MaintlJFlexLexer"
	    java -cp $FLAG test01 test02 test03
            ;;
        "Run JFlex S.I.")
            FLAG="$CLASSPATH:../target/classes co.MaintlJFlexLexer"
	    java -cp $FLAG -
            ;;
        "Run JFlex misc")
            FLAG="$CLASSPATH:../target/classes co.MaintlJFlexLexer"
	    java -cp $FLAG test01 test02 - test03
            ;;
        "Quit")
            break
            ;;
        *) echo invalid option;;
    esac
done
