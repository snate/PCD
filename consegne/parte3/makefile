JC = javac
CLASSES := puzzle/PuzzleSolverClient.java puzzle/PuzzleSolverServer.java
.SUFFIXES: .java .class
.java.class:
	$(JC) $*.java
default: classes
classes: $(CLASSES:.java=.class)
clean:
	rm puzzle/*.class
