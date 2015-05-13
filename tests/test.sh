#!/bin/bash

MAIN = "MaintlAntlerLexer"
#MAIN = MaintlJFlexLexer
FLAG = "../target/classes co.":$MAIN

java -cp $FLAG test01
