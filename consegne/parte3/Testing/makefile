JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $*.java
SOURCES = ../puzzle/*.java
CLASSES := ../puzzle/Driver.java
default: classes
classes: $(SOURCES:.java=.class)
clean:
	rm ../puzzle/*.class
