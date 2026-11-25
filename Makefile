.PHONY: all compile run clean
.POSIX:

SRC_DIR = src/avaj
BUILD_DIR = target

SOURCES = $(shell find $(SRC_DIR) -name "*.java")

SCENARIO ?= scenario.txt

all: compile run

compile:
	mkdir -p $(BUILD_DIR)
	find $(SRC_DIR) -name "*.java" > sources.txt
	javac -d $(BUILD_DIR) @sources.txt

run:
	@if [ ! -d target ] || [ ! -d target/avaj ]; then\
		echo "Some files are unfound, try compiling: 'make compile'";\
		exit 1;\
	fi
	java -cp $(BUILD_DIR) avaj.Main $(SCENARIO)

clean:
	rm -rf $(BUILD_DIR)
	rm -f simulation.txt
	rm -f sources.txt

