Project 1 - Music Library
=========================

For this project, you will implement a program to build a music library data structure to store the information in the last.fm data set available from the [Million Song Dataset](http://labrosa.ee.columbia.edu/millionsong/lastfm).

In this project, you will practice reading and writing files, parsing JSON data, and designing complex data structures. The most important element of this project is to design an efficient data structure.

## Set up
 
 - Clone your project repository as described in the [project guidelines](https://github.com/CS212-S16/lectures/blob/master/notes/projectguidelines.md). 
 - Copy the last.fm "SUBSET" data set you have used for homework into `Project/input`. The test cases assume you have a directory `Project/input/lastfm_subset` (and, unfortunately, the directory is too large to store on github).
 
## Functionality

**Driver** - You must have a class `Driver` that contains your main method. This class must be in the default package. 

**Main Logic** - The main logic of your program will proceed as follows:

1. Recursively traverse a directory containing many .json files, where each .json file contains information about a single song. 

2. As you read each file, add the song information (artist, title, track_id, and similars) to a data structure. 
	
3. Once you have built your entire library, you will save the text into a single .txt file, sorted in a given order.

**Execution** - Your program will expect three arguments to be passed as command line input, specified using flags. An example valid execution of your program would look as follows: `java Driver -input input/lastfm_subset -output /Users/srollins/cs212/Project/results -order tag`. If any of the three required flags is missing, or if any flag is not followed by an appropriate value, your program must exit gracefully (not by throwing exceptions!). The flags and their values may be reordered, so the following is also valid: `java Driver -output /Users/srollins/cs212/Project/results -order tag -input /Users/srollins/cs212/Project/input/lastfm_subset ` Example **invalid** executions would include `java Driver hello world` and `java Driver -input input/lastfm_subset -order title`. 

**Arguments - input** - The value following the `-input` flag will be the top-level directory containing the song data, in .json format. If the directory specified is not valid you program must exit gracefully. The value may specify a directory using *either* a relative or absolute path. 

**Arguments - output** - The value following the `-output` flag will be the name of the specific file where your program will save its output. The value may contain a file name, absolute path, or relative path. If the path specified is not valid, for example it specifies a directory that does not exist (e.g., `/BAD/file.txt`), your program must exit gracefully. 

**Arguments - order** - The value following the `-order` flag specifies the ordering to use when saving the music library data to a file. There are three possible valid values: `artist`, `title`, and `tag`. If the value specified is not one of these values, your program will exit without saving any data. For the three valid values, the expected output will look as follows:

Data sorted by `artist` will list the artist name, followed by a space, followed by `-`, followed by a space, followed by `title`, followed by a new line, and will be in alphabetical order by artist. If two songs have the same artist, they will then be sorted by the title. If two songs have the same artist and title, they will be sorted by the track_id. Example:

```
Steel Rain - Loaded Like A Gun
Tom Petty - A Higher Place (Album Version)
```

Data sorted by `title` will list the artist name, followed by a space, followed by `-`, followed by a space, followed by `title`, followed by a new line, and will be in alphabetical order by title. If two songs have the same title, they will then be sorted by the artist. If two songs have the same title and artist, they will be sorted by the track_id. Example:

```
Hushabye Baby - Cry, Cry, Cry
Aerosmith - Cryin'
```

Data sorted by `tag` will list the tag, followed by `:`, followed by a space, followed by the track_ids of all songs with that tag, separated by spaces. Data will be in alphabetical order by tag. 

```
Best of Bon Jovi: TRAUYZG12903CDA4E9 TRAZJOI12903CDA550 TRBAAOT128F4261A18 
Best of Grunge: TRAVBAH128F9305DFC 
```

## Requirements and hints 

- Use the `UTF-8` character encoding for all file processing (including reading and writing).
- Process all .json files, case **insensitive**, and ignore any files with a different extension.
- You are expected to use object-oriented design. As a hint, my solution uses four classes in addition to the driver: a class that parses the command line arguments; a class that stores data for a single song; a class that stores the entire library and has several complex data structures as its data members; and a class that traverses the file system and builds the library.
- Your main method should do very little.
- You will need to implement your own test code as you go. Do not rely only on the unit tests.
- Thorough error checking is required. Make sure to handle exceptions appropriately and verify all of your inputs.




## Submissionn

Follow these instructions *carefully* in order to submit your project: [Project Guidelines](https://github.com/CS212-S16/lectures/blob/master/notes/projectguidelines.md)
