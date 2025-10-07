#!/bin/bash

# A simple script to find all Java files in the current directory
# and its subdirectories, and append their content to a single text file.

# --- Configuration ---
# The name of the final output file.
OUTPUT_FILE="abc.txt"
# The directory to search. "." means the current directory.
SEARCH_DIR="."

# --- Script Logic ---

# Announce the start of the process.
echo "Starting to concatenate Java files..."

# Clear the output file to ensure we start fresh.
# The '>' operator creates the file or overwrites it if it exists.
> "$OUTPUT_FILE"

# Use the 'find' command to locate all files ending in .java.
# The output is piped to a 'while' loop to process each file individually.
find "$SEARCH_DIR" -type f -name "*.java" | while read -r java_file; do
  
  # Log which file is being processed.
  echo "Adding content from: $java_file"

  # Append a header to the output file to clearly mark where the content of each file begins.
  echo "--- Start of content from: $java_file ---" >> "$OUTPUT_FILE"
  echo "" >> "$OUTPUT_FILE" # Add a blank line for better readability.

  # Use 'cat' to read the content of the current Java file and '>>' to append it to our output file.
  cat "$java_file" >> "$OUTPUT_FILE"

  # Append a footer to mark the end of the content for this file.
  echo "" >> "$OUTPUT_FILE"
  echo "--- End of content from: $java_file ---" >> "$OUTPUT_FILE"
  echo "" >> "$OUTPUT_FILE" # Add some space before the next file's content.

done

# Announce completion.
echo "Process complete. All Java files have been concatenated into $OUTPUT_FILE"