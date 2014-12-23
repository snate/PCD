#!/bin/bash
make
java puzzle/PuzzleSolver "$1" "$2"
make clean
