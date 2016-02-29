import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Song {
	
	/*
	 * Class that stores data from a single song, very similar to Song.java in last homework
	 * Got variables for artist, trackId, title and similars and tags
	 * 
	 */

	private String artist;
	private String trackId;
	private String title;
	private ArrayList<String> similars;
	private ArrayList<String> tags;
	
	/*
	 * Constructor Overloading. First constructor takes in all the variables for each part of a song and 
	 * Initializes them.
	 */
	
	
	public Song(String artist, String trackId, String title, ArrayList<String> similars, ArrayList<String> tags) {
		
		this.artist = artist;
		this.trackId = trackId;
		this.title = title;
		this.similars = similars;
		this.tags = tags;
		
		
	}
	
	/**
	 * Constructor that takes as input a JSONObject as illustrated in the example above and
	 * constructs a Song object by extract the relevant data by intializing their respective 
	 * variables to value in the song.
	 * @param object
	 * 
	 */
	
	public Song(JSONObject object) {
		
		this.artist = (String)object.get("artist");
		this.trackId = (String)object.get("track_id");
		this.title = (String)object.get("title");
		this.similars = (JSONArray)object.get("similars");
		this.tags = (JSONArray)object.get("tags");
		
	}
	
	
	/**
	 * Return artist.
	 * @return
	 */
	public String getArtist() {
		//TODO: complete method
		return artist;
	}

	/**
	 * Return track ID.
	 * @return
	 */
	public String getTrackId() {
		//TODO: complete method
		return trackId;
	}

	/**
	 * Return title.
	 * @return
	 */
	public String getTitle() {
		//TODO: complete method
		return title;
	}

	/**
	 * Return a list of the track IDs of all similar tracks.
	 * @return
	 */
	public ArrayList<String> getSimilars() {
		//TODO: complete method
		return similars;
	}

	/**
	 * Return a list of all tags for this track.
	 * @return
	 */
	public ArrayList<String> getTags() {
		//TODO: complete method
		return tags;
	}	
}
