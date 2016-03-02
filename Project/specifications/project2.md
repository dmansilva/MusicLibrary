Project 2 - Thread-safe Music Library
=====================================

:warning: The test cases for this project are not yet available.

For this project, you will extend your previous project to support concurrency. You must implement a thread-safe music library and use a work queue to build the library using multiple threads. Note that you will extend this to support search functionality in Project 3.

The input and output requirements of this project are identical to the previous project. In addition to the normal testing of your project, you must also compare the execution time of this project to your previous code.


## Functionality ##

For this project, your code must pass all of the previous project requirements and support the following additional functionality:

- Create a custom lock class that allows multiple concurrent read operations, but does not allow concurrent write or concurrent read/write operations.

- Create a thread-safe music library using the custom lock class above.

- Use a work queue to build your music library from a directory of files using multiple worker threads. Each worker thread should parse a single json file.

- Exit gracefully without calling `System.exit()` when all of the building operations are complete.

- You may NOT use any of the classes in the `java.util.concurrent` package.

Consider extending your classes from previous projects for this project.

## Configuration ##

**Arguments - threads** - The value following the `-threads` flag will be an integer specifying the number of threads to use to build your music library. If `-threads` is not specified use a default value of 10. If it is specified, verify that it is an integer between 1 and 1,000. If it is a floating point number, String, or integer less than 1 or greater than 1,000 you may use a default value of 10. In no case should your program throw an exception as a result of an invalid value for the `-threads` argument.

An example run of your program would look as follows:

```
java Driver -input input/lastfm_subset
			  -output results/songsByArtistSubset.txt 
			  -order artist 
			  -threads 5
```			

## Output ##

The output of your program should be the same as the previous project. 

## Hints ##

It is important to develop the project **iteratively**. One possible breakdown of tasks are:

- Consider extending your previous classes (e.g., `MusicLibrary`) to create multithreaded versions. You may need to add additional functionality to your single-threaded versions, but this tends to make the debugging process simpler.

- Create a thread-safe music library using the `synchronized` keyword. (Do not worry about efficiency yet.)

- Modify how you build your library to use multithreading and a work queue. Make sure you still pass the unit tests.

- Once you are sure this version works, convert your music library to use a custom lock class. Make sure you still pass the unit tests.

- Start worrying about efficiency. Make sure you are not under or over synchronizing, and that your multithreaded code is faster on average than your single-threaded code.

- Test your code in a multitude of settings and with different numbers of threads. Some issues will only come up occasionally, or only on certain systems.

- Lastly, do not start on this project until you understand the multithreading code shown in class. If you are stuck on the code shown in class, PLEASE SEEK HELP. You do not need to figure it out on your own—you can ask the CS tutors, the teacher assistant, or the instructor for help.

  :confused: I cannot stress this enough! If you do not understand the simpler examples shown in class, you will get sucked into a black hole of debugging for the project. We are here to help prevent this from happening.

The important part will be to **test your code as you go**. The JUnit tests provided only test the entire project as a whole, not the individual parts. You are responsible for testing the individual parts themselves.

:bulb: These hints may or may _not_ be useful depending on your approach. Do not be overly concerned if you do not find these hints helpful for your approach for this project.

## Benchmarking ##

The expected output files are identical to project 1.

You _must_ pass `ThreadTest.java` to be eligible for code review. You may sign up for code review even if you are not passing `ThreadStressTest.java`, but know that this means your code has efficiency issues.

## Submission ##
You must pass all of the Project 1 and Project 2 tests in order to qualify for code review.

Follow these instructions *carefully* in order to submit your project: [Project Guidelines](https://github.com/CS212-S16/lectures/blob/master/Notes/projectguidelines.md)
