#!/bin/bash
make
cd ../
java puzzle.Driver "$1" "$2" "$3"
cd Testing/
make clean
