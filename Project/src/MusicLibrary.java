import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;


import java.util.TreeMap;
import java.util.TreeSet;





public class MusicLibrary {
	
	/*
	 * a class that stores the entire library and has several complex data structures
	 * and its data members
	 * - store each song into the library, (addSong) method, very similar to MusicLibrary.java
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
		

		for (int i = 0; i < song.getTags().size(); i++) {
			if (!this.byTag.containsKey(song.getTags().get(i))) {				
				this.byTag.put(song.getTags().get(i), new TreeSet<String>());	
				this.byTag.get(song.getTags().get(i)).add(song.getTrackId());	
			} else { 													
				this.byTag.get(song.getTags().get(i)).add(song.getTrackId());	
			}
		}
		
	}
	
	
	/*
	 * outputByArtist calls the outputArtistOrTitle method by passing in byArtist and path 
	 */
	
	public void outputByArtist(Path path) {
		
		outputArtistOrTitle(byArtist, path);
		

	}
	
	/*
	 * outputByTitle calls the outputArtistOrTitle method by passing in byTitle and path
	 */
	
	public void outputByTitle(Path path) {
		
		outputArtistOrTitle(byTitle, path);
		
	}
	
	/*
	 * outputArtistOrTitle takes in a TreeMap and path and outputs to a file in the specified path passed in. It 
	 * checks which treeMap is passed in and outputs accordingly
	 */
	
	public void outputArtistOrTitle(TreeMap<String, TreeSet<Song>> treeMaps, Path path) {
		
		try(PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path, Charset.forName("UTF-8")))) {
			
			for (String key : treeMaps.navigableKeySet()) {
				TreeSet<Song> songs = treeMaps.get(key);
				
				java.util.Iterator<Song> itr = songs.iterator();
				
				while (itr.hasNext()) {
					Song eachSong = itr.next();
					if (treeMaps.equals(byArtist)) {
						String title = eachSong.getTitle();
						writer.println(key + " - " + title);
					}
					else if (treeMaps.equals(byTitle)) {
						String artist = eachSong.getArtist();
						writer.println(artist + " - " + key);
					}
				}
				
			}
		} catch (IOException e) {
			
			System.out.println("Invalid Output path");
		}
	}
	
	/*
	 * outputByTag takes in the output path and outputs properly to that specified path using a printWriter 
	 * for the byTag.
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
