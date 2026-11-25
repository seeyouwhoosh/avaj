.PHONY: all compile run clean
.POSIX:

SRC_DIR = src/avaj
BUILD_DIR = target

SOURCES = $(shell find $(SRC_DIR) -name "*.java")

all: compile run

compile:
	mkdir -p $(BUILD_DIR)
	find $(SRC_DIR) -name "*.java" > sources.txt
	javac -d $(BUILD_DIR) @sources.txt

run:
	java -cp $(BUILD_DIR) avaj.Main scenario.txt

clean:
	rm -rf $(BUILD_DIR)
	rm -f simulation.txt

