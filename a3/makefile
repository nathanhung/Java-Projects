# Add an environment variable for JAVA_FX_HOME that points to the lib directory of the JavaFX 13 runtime
default:
	javac -sourcepath src src/*.java -d . --module-path $(JAVA_FX_HOME)/lib --add-modules javafx.controls
	jar cmf src/manifest.mf out/Main.jar *.class
	rm *.class

run: default
	java --module-path $(JAVA_FX_HOME)/lib --add-modules javafx.controls -jar out/Main.jar

clean:
	-@rm out/src/production/*.class	
	-@rm out/src/*.class
	-@rm out/*.jar
	-@rm *.class