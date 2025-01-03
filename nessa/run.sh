#!/bin/bash

# Script to compile and run the MatrixOperations Java program

# Clear the terminal for better readability
clear

# Set the Java file name and class name
JAVA_FILE="Asgmt01.java"
CLASS_NAME="nessa.Asgmt01"

# Check if the Java file exists
if [ ! -f "$JAVA_FILE" ]; then
    echo "Error: $JAVA_FILE not found in the current directory!"
    exit 1
fi

# Compile the Java program
echo "Compiling $JAVA_FILE..."
javac -d . "$JAVA_FILE"
if [ $? -ne 0 ]; then
    echo "Compilation failed. Please fix the errors and try again."
    exit 1
fi

# Run the Java program
echo -e "Running $CLASS_NAME...\n"
java "$CLASS_NAME"
if [ $? -ne 0 ]; then
    echo "Program execution failed."
    exit 1
fi

# Cleanup compiled class files after execution
echo -e "\nCleaning up..."
rm -rf nessa
echo "Done."
