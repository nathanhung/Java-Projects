# super simple makefile
# call it using 'make NAME=name_of_code_file_without_extension'
# (assumes a .java extension)
NAME = "rename"
ARG1 = "-p"
ARG2 = "-q"
VAL1 = "1"
VAL2 = "2"

default:
	@echo "Compiling..."
	javac $(NAME).java

run: 
	@echo "Running..."
	java $(NAME)

test: 
	@echo Test 1: No args
	java $(NAME)

	@echo Test 2: Too many keys
	java $(NAME) $(ARG1) $(ARG2) $(VAL2)

	@echo Test 3: Too many values
	java $(NAME) $(ARG1) $(VAL1) $(VAL2)

	@echo Test 4: Correct syntax
	java $(NAME) $(ARG1) $(VAL1) $(ARG2) $(VAL2)

clean:
	rm -rf *.class
