#!/bin/bash
make
rmiregistry &
java puzzle/PuzzleSolverServer "$1" &
