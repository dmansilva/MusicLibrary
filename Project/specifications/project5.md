Project 5 - Advanced Features
===============================

For this project, you will extend your previous project to support your choice of advanced features. To be eligible for any credit, you must implement all *required* features, worth 25 of 100 points. For the remaining 75 points you may select from the list of additional features specified. 

The points awarded for a *complete* implementation for each feature are noted below. An incomplete implementation, for example an implementation that does not handle all possible error cases, may only receive partial credit.

You may earn up to 10 points of extra credit on this assignment.

## Deadlines

:warning: As noted in the syllabus, a student is only guaranteed **ONE** project submission per **WEEK**. If Project 4 *code review* is not *complete* by May 12, 2016, you will be required to submit Project 4 during the finals week and will **not** be eligible to submit Project 5.

If Project 3 *code review* is not *complete* by May 12, 2016, you will be required to submit Project 3 during the finals week and will **not** be eligible to submit Projects 4 or 5.

You are guaranteed only one code review during finals week. All code must be submitted to your git repository before your scheduled code review.


## Required Features

Required features are worth 25 out of 100 points.


**View song info (5 points)** - On any search result page, allow the user to select a specific song and see information about that song, including the artist, title, and the list of a similar songs from the original data set.

**View all artists sorted alphabetically (5 points)** - Provide a menu option that allows the user to view an alphabetical list of all artists in the library.

**View all artists sorted by play count (5 points)** - Provide a menu option that allows the user to view a list of all artists in the library sorted by *play count*. The play count must be retrieved from the last fm API: [http://www.last.fm/api/show/artist.getInfo](http://www.last.fm/api/show/artist.getInfo). It is recommended that this information be retrieved once for all artists in your library and stored in the database.

**View specific artist information (10 points)** - Allow the user to select a specific artist from a list of artists sorted alphabetically or by play count. Display specific information about that artist including the artist name, number of listeners, play count, and a bio. This information must be retrieved from the last fm API: [http://www.last.fm/api/show/artist.getInfo](http://www.last.fm/api/show/artist.getInfo). It is recommended that this information be retrieved once for all artists in your library and stored in the database.


## Additional Features

**Change password (5 points)** - Allow the user to change his/her password.

**Artist image (5 points)** - When displaying the information about an artist, show an image of that artist. You will need to use the last fm API to implement this feature.

**Search history (15 points)** - If a user is logged in, save a history of all searches performed by the user into a database table. For full credit, allow the user to view his/her history and also clear his/her history.

**Private search (5 points)** - *You may only implement this feature if you also implement Search history.* Allow a user who is logged in to turn on private search. If the user is in private search mode, do not save any searches to the search history.

**Last login time (5 points)** Track and display the last time the user logged into your site. 

**Persistent `MusicLibrary` (10 points)** - Store the `MusicLibrary` data in your database.

**Partial search (10 points)** - Allow the user to enter only part of an artist, title, or tag and return relevant results, for example the search for artist "Michael Ja" would return results for Michael Jackson.

**Case insensitive search (with case-sensitive results) (5 points)** - Ignore upper/lower case when searching, for example a search for "madonna" will return results for "Madonna". For full credit, the results displayed must show the data using the original case.

**Additional last fm APIs (10 points)** - Integrate other last fm APIs, for example top artist or track. The number of points awarded will depend on the sophistication of your implementation.

**Integrate other web services (10 points)** - Integrate other web services, for example login with Facebook.

**Branding (5 points)** - Brand your site with a logo, color scheme, etc.

**Suggested searches (10 points)** - Suggest popular searches, for example artist or title, based on all searches by all users in your system.

**Templates (5 points)** - Use StringTemplate to generate your HTML instead of several `println()` statements. See for [http://www.cs.usfca.edu/~parrt/course/601/lectures/stringtemplate.html](http://www.cs.usfca.edu/~parrt/course/601/lectures/stringtemplate.html) more information on StringTemplate.

**Graceful shutdown (10 points)** - Allow an administrator to trigger a graceful shutdown of your search engine without calling `System.exit()`. You will need to create a special servlet for this feature.

# Submission 
You do **not** need to pass any unit tests for this project. You *may* remove the code that saves the `MusicLibrary` and the search output to a file.

Before you qualify for code review, we will verify that your site is up at your specified node and that it implements all of the required functionality specified above. If any functionality is missing or does not work as specified above, you will need to revise your solution before code review.

Follow these instructions *carefully* in order to submit your project: [Project Guidelines](https://github.com/CS212-S16/lectures/blob/master/Notes/projectguidelines.md)

:warning: As noted in the syllabus, a student is only guaranteed **ONE** project submission per **WEEK**. If Project 4 *code review* is not *complete* by May 12, 2016, you will be required to submit Project 4 during the finals week and will **not** be eligible to submit Project 5.

If Project 3 *code review* is not *complete* by May 12, 2016, you will be required to submit Project 3 during the finals week and will **not** be eligible to submit Projects 4 or 5.

