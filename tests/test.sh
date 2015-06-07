#!/bin/bash

clear
FLAGA="$CLASSPATH:../target/classes co.MaintlAntlrLexer"
FLAGJ="$CLASSPATH:../target/classes co.MaintlJFlexLexer"
FLAGP="$CLASSPATH:../target/classes co.MaintlAntlrParser"

PS3='Enter your choice: '
options=("Run Antlr tests" "Run Antlr S.I." "Run Antlr misc" "Run Antlr N.A." "Run JFlex tests"
	 "Run JFlex S.I." "Run JFlex misc" "Run JFlex N.A." "Run Antlr Parser" "Quit")
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
	"Run Antlr Parser")
	    java -cp $FLAGP test04
	    ;;
        "Quit")
            break
            ;;
        *) echo invalid option;;
    esac
done
