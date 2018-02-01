# wordstat
A simple tool for calculating words frequency in a text file, written in Java. 

# Building the application
Run `./gradlew build` in the root directory.

# Running the application
`JDK 8` or higher is required to run the application.

Assuming the build has completed succesfully, go to `/build/libs` directory.
To run the compiled app:

`java -jar wordstat.jar [filePath] [topWords]`
or
`java -jar wordstat.jar [topWords]  [filePath]`

`filePath` argument is a local or web path to a file.
If it is a web path, the file will be downloaded and processed accordingly.
If it is a local path, there are two ways of program behaviour:

1. A single file with _exact_ file name match is found - the single file is processed.
2. Multiple files which match the pattern: [fileName]0.[extension], [fileName]1.[extension], (meaning: `java -jar wordstat text.txt 5` will match `text1.txt`, `text2.txt`, `text3.txt`, up to `textM.txt`)  etc. are found - all of these files files will be processed.

`topWords` argument is expected to be a natural number.

# Result

Each processed file is be stripped of interpunction (,:;.?!-) and split on whitespaces. 
The program counts the words and displays the `N (=topWords)` most frequent words in the file. 
If the file has too little distinct words, all the words will be displayed.
Counting is case-sensitive.
