#!/bin/bash

cd "$(dirname "$0")" 

javac src/*.java

java -cp src Furfeux ../manoir.txt  
