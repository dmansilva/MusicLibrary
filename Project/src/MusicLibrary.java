import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;



import org.json.simple.JSONArray;

public class MusicLibrary {
	
	/*
	 * a class that stores the entire library and has several complex data structures
	 * and its data members
	 * - store each song into the library, (addSong) method, very similar to MusicLibrary.java
	 * in the last homework
	 * Created 3 data structures
	 * 1. TreeMap: key is string (artist), value is a treeSet of songs to allow me to extract the proper value within song
	 * 2. TreeMap: key is string (title), value is a treeSet of songs to allow me to extract the proper value within song
	 * 3. TreeMap: key is string (tag), value is a treeSet of strings which will be all the trackIds from each song
	 */
	
	private TreeMap<String, TreeSet<Song>> byArtist;
	private TreeMap<String, TreeSet<Song>> byTitle;
	private TreeMap<String, TreeSet<String>> byTag;
	
	public MusicLibrary () {
		
		this.byArtist = new TreeMap<>();
		this.byTitle = new TreeMap<>();
		this.byTag = new TreeMap<>();
		
		
	}
	
	/*
	 * Adds the song to their proper data structure whether it is adding it to the byArtist, byTitle, or byTag's TreeMap 
	 * I created string variable for each key in order to check if that key already exists. If it already exists, add the song object to that key.
	 * If it doesn't exist use the put method to add that key along with adding a new TreeSet of songs which calls my ByArtistComparator class so 
	 * that it knows what to compare the songs by. For the tag I extract the JSONArray from the key tag and iterate through the JSONArray to get the 
	 * first line of the array from each Array within the JSONArray and add the first line to an ArrayList I created. I then iterate through the 
	 * ArrayList that has all the tags and very similar to byArtist and byTitle I check if the key already exist. If it does exist already I just add 
	 * trackId for the song to the already existing TreeSet. If it doesn't I use the put method to add the key (tag) followed by a new TreeSet of strings.
	 * 
	 */
	
	public void addSong(Song song) {
		
		String artist_check = song.getArtist();
		
		if (!this.byArtist.containsKey(artist_check)) {
			this.byArtist.put(artist_check, new TreeSet<Song>(new ByArtistComparator()));
		}
		
		this.byArtist.get(artist_check).add(song);
		
		String title_check = song.getTitle();
		
		if (!this.byTitle.containsKey(title_check)) {
			this.byTitle.put(title_check, new TreeSet<Song>(new ByTitleComparator()));
		}
		
		this.byTitle.get(title_check).add(song);
		
		// adding Songs by their tag
		
		JSONArray tag = (JSONArray) song.getTags();						
		ArrayList<String> eachTag = new ArrayList<String>();			
		for (int i = 0; i < tag.size(); i++) {							
			JSONArray temporary = (JSONArray) tag.get(i);				
			String temp = (String) temporary.get(0);					
			eachTag.add(temp);											
		}
		
		
		for (int i = 0; i < eachTag.size(); i++) {						
			if (!this.byTag.containsKey(eachTag.get(i))) {				
				this.byTag.put(eachTag.get(i), new TreeSet<String>());	
				this.byTag.get(eachTag.get(i)).add(song.getTrackId());	
			} else { 													
				this.byTag.get(eachTag.get(i)).add(song.getTrackId());	
			}
		}
	}
	
	
	/*
	 * outputByArtist is the method that I call in my driver to output to a txt file by Artist followed by Title.
	 * I take in path as a parameter because I will be calling it in my Driver class and will pass in the output
	 * path. Then I used try with resources to create a PrintWriter that will take the path as a parameter, so 
	 * that it will output to the desired path. Then I use a for loop to iterate through my keys in my TreeMap 
	 * named byArtist, by using navigableKeySet(). I then create a variable songs that is a TreeSet<Song> and set
	 * it equal to the value pair of the desired key. Then I used an iterator to iterate through the TreeSet. Within
	 * the while loop iterating through the TreeSet I set an object Song variable and save it to each iteration using
	 * .next(). Then I extract the title by calling getTitle() on the song object and save it to a string variable. 
	 * Then I write first the artist and then the title to the writer.
	 */
	
	public void outputByArtist(Path path) {
		
		try(PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path, Charset.forName("UTF-8")))) {
			
			for (String artist : this.byArtist.navigableKeySet()) {	
				TreeSet<Song> songs = this.byArtist.get(artist);      
				
				java.util.Iterator<Song> itr = songs.iterator();
				
				while (itr.hasNext()) {
					Song eachSong = itr.next();
					String titles = eachSong.getTitle();
					writer.println(artist + " - " +  titles);
				}
					
			}
		} catch (IOException e) {
			System.out.println("Invalid Output path");
		}
	}
	
	/*
	 * outputByTitle is the method that I call in my driver to output to a txt file by Title followed by Artist.
	 * I take in path as a parameter because I will be calling it in my Driver class and will pass in the output
	 * path. Then I used try with resources to create a PrintWriter that will take the path as a parameter, so 
	 * that it will output to the desired path. Then I use a for loop to iterate through my keys in my TreeMap 
	 * named byTitle, by using navigableKeySet(). I then create a variable songs that is a TreeSet<Song> and set
	 * it equal to the value pair of the desired key. Then I used an iterator to iterate through the TreeSet. Within
	 * the while loop iterating through the TreeSet I set an object Song variable and save it to each iteration using
	 * .next(). Then I extract the title by calling getTitle() on the song object and save it to a string variable. 
	 * Then I write first the title and then the artist to the writer.
	 */
	
	public void outputByTitle(Path path) {
		
		try(PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path, Charset.forName("UTF-8")))) {
			
			for (String title : this.byTitle.navigableKeySet()) {
				TreeSet<Song> songs = this.byTitle.get(title);
				
				java.util.Iterator<Song> itr = songs.iterator();
				
				while (itr.hasNext()) {
					Song eachSong = itr.next();
					String artist = eachSong.getArtist();
					writer.println(artist + " - " + title);
				}
			}
		} catch (IOException e) {
			System.out.println("Invalid Output path");
		}
	}
	
	/*
	 * outputByTag is the method that I call in my driver to output to a txt file by Tag.
	 * I take in path as a parameter because I will be calling it in my Driver class and will pass in the output
	 * path. Then I used try with resources to create a PrintWriter that will take the path as a parameter, so 
	 * that it will output to the desired path. Then I use a for loop to iterate through my keys in my TreeMap 
	 * named byTag, by using navigableKeySet(). I then create a variable songs that is a TreeSet<String> and set
	 * it equal to the value pair of the desired key. I then create a StringBuffer to add all the trackIds to.
	 * Then I used an iterator to iterate through the TreeSet. Within the while loop im iterating through the TreeSet 
	 * I append a space and all the trackIds. Once I've done that and the while loop is done. I write to the file
	 * the tag a : followed by the string representation of the StringBuffer I created with all the trackIds per tag.
	 */
	
	public void outputByTag(Path path) {
		
		try(PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path, Charset.forName("UTF-8")))) {
			
			for (String tag : this.byTag.navigableKeySet()) {
				TreeSet<String> stringOfIds = this.byTag.get(tag);
				
				java.util.Iterator<String> itr = stringOfIds.iterator();
				
				StringBuffer allTrackIds = new StringBuffer();
				
				while (itr.hasNext()) {
					
					allTrackIds.append(" " + itr.next());
					
				}
				writer.println(tag + ":" + allTrackIds.toString());
			}
		} catch (IOException e) {
			System.out.println("Invalid Output path");
		}
	}

}
