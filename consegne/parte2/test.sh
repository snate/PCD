#!/bin/bash
make
java puzzle/Test "$1" "$2"
make clean
