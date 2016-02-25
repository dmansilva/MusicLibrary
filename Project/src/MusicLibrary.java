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
	 */
	
	private TreeMap<String, TreeSet<Song>> byArtist;
	private TreeMap<String, TreeSet<Song>> byTitle;
	private TreeMap<String, TreeSet<String>> byTag;
	
	public MusicLibrary () {
		
		this.byArtist = new TreeMap<>();
		this.byTitle = new TreeMap<>();
		this.byTag = new TreeMap<>();
		
		
	}
	
	public void addSong(Song song) {
		
		String artist_check = song.getArtist();
		
		if (!this.byArtist.containsKey(artist_check)) {
			this.byArtist.put(artist_check, new TreeSet<Song>(new ByArtistComparator()));
		}
		
		// if there is already an TreeSet of songs for that artist, it add that song info to the already existing TreeSet
		this.byArtist.get(artist_check).add(song);
		
		String title_check = song.getTitle();
		
		if (!this.byTitle.containsKey(title_check)) {
			this.byTitle.put(title_check, new TreeSet<Song>(new ByTitleComparator()));
		}
		
		this.byTitle.get(title_check).add(song);
		
		// adding Songs by their tag
		
		JSONArray tag = (JSONArray) song.getTags();						// set a new JSONArray tag equal to the JSONArray that you get from getTags method
		ArrayList<String> eachTag = new ArrayList<String>();			// create a new arrayList to put each tag into
		for (int i = 0; i < tag.size(); i++) {							// iterate through outter JSONArray tag
			JSONArray temporary = (JSONArray) tag.get(i);				// set a new JSONArray temporary equal to the JSONArray you get with each iteration
			String temp = (String) temporary.get(0);					// set a new String temp equal to the first element of each JSONArray temporary which will be your tag
			eachTag.add(temp);											// add your tag (called temp) into the arraylist
		}
		
		
		for (int i = 0; i < eachTag.size(); i++) {						// iterate through arrayList that has all the tags for the song you passed into the addSong method
			if (!this.byTag.containsKey(eachTag.get(i))) {				// if treeMap byTag doesn't contain the tag 
				this.byTag.put(eachTag.get(i), new TreeSet<String>());	// put that tag and a new TreeSet of strings into treeMap byTag
				this.byTag.get(eachTag.get(i)).add(song.getTrackId());	// once a treeSet is created for the unique tag then we must go and add the trackId from the song object
			} else { 													// if treeMap byTag does contain the tag already
				this.byTag.get(eachTag.get(i)).add(song.getTrackId());	// get that tag from treeMap byTag and add that trackId variable
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
			// TODO Auto-generated catch block
			System.out.println("Invalid Output path");
		}
	}
	
	public void outputByTitle(Path path) {
		
		try(PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path, Charset.forName("UTF-8")))) {
			
			for (String title : this.byTitle.navigableKeySet()) {
				TreeSet<Song> songs = this.byTitle.get(title);
				
				java.util.Iterator<Song> itr = songs.iterator();
				
				while (itr.hasNext()) {
					Song eachSong = itr.next();
					String artist = eachSong.getArtist();
					writer.println(artist + " - " + title);
					//System.out.println(path);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Invalid Output path");
		}
	}
	
	public void outputByTag(Path path) {
		
		try(PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path, Charset.forName("UTF-8")))) {
			
			for (String tag : this.byTag.navigableKeySet()) {
				TreeSet<String> stringOfIds = this.byTag.get(tag);
				
				java.util.Iterator<String> itr = stringOfIds.iterator();
				
				StringBuffer allTrackIds = new StringBuffer();
				
				while (itr.hasNext()) {
					
					allTrackIds.append(" " + itr.next());
					
				}
				writer.println(tag + " -" + allTrackIds.toString());
				//writer.println(tag + " - " + byTag.get(tag));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Invalid Output path");
		}
	}
	
	//Don't need these methods
	
	public TreeSet<Song> getSongsByArtist(String artist) {
		
		return this.byArtist.get(artist);
	}
	
	public TreeSet<Song> getSongsByTitle(String title) {
		
		return this.byTitle.get(title);
	}
	
	
	public TreeSet<String> getSongsbyTag(String tag) {
		
		return this.byTag.get(tag);
	}

}
