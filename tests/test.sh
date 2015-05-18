#!/bin/bash

clear
FLAGA="$CLASSPATH:../target/classes co.MaintlAntlrLexer"
FLAGJ="$CLASSPATH:../target/classes co.MaintlJFlexLexer"


PS3='Enter your choice: '
options=("Run Antlr tests" "Run Antlr S.I." "Run Antlr misc" "Run Antlr N.A." "Run JFlex tests"
	 "Run JFlex S.I." "Run JFlex misc" "Run JFlex N.A." "Quit")
select opt in "${options[@]}"
do
    case $opt in
        "Run Antlr tests")
	    java -cp $FLAGA test01 test02 test03
            ;;
        "Run Antlr S.I.")
	    java -cp $FLAGA -
            ;;
        "Run Antlr misc")
	    java -cp $FLAGA test01 - test02 - test03
            ;;
	"Run Antlr N.A.")
	    java -cp $FLAGA
	    ;; 
	"Run JFlex tests")
	    java -cp $FLAGJ test01 test02 test03 
            ;;
        "Run JFlex S.I.")
	    java -cp $FLAGJ -
            ;;
        "Run JFlex misc")
	    java -cp $FLAGJ test01 - test02 - test03
            ;;
	"Run JFlex N.A.")
	    java -cp $FLAGJ
	    ;;
        "Quit")
            break
            ;;
        *) echo invalid option;;
    esac
done
