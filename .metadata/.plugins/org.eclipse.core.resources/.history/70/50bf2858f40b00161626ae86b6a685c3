import java.nio.file.Path;
import java.util.TreeSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class ThreadSafeMusicLibrary extends MusicLibrary {
	
	private Lock lock;
	
	public ThreadSafeMusicLibrary () {
		
		super();
		this.lock = new Lock();
		
	}


	
	public void addSong(Song song) {
		
		lock.lockWrite();
		super.addSong(song);
		lock.unlockWrite();
		
	}
	
	// @Override
	
	public void outputByTitle(Path path) {
		
		lock.lockRead();
		super.outputByTitle(path);
		lock.unlockRead();
		
	}
	
	// @Override
	
	public void outputByArtist(Path path) {
		
		lock.lockRead();
		super.outputByArtist(path);
		lock.unlockRead();
		
	}
	
	// @Override
	
	public void outputByTag(Path path) {
		
		lock.lockRead();
		super.outputByTag(path);
		lock.unlockRead();
		
	}
	
	public JSONArray searchByArtist(String artist) {
		
		JSONArray similarSongs = new JSONArray();
		lock.lockRead();
		similarSongs = super.searchByArtist(artist);
		lock.unlockRead();
		return similarSongs;
	}
	
	public JSONArray searchByTitle(String title) {
		
		JSONArray similarSongs = new JSONArray();
		lock.lockRead();
		similarSongs = super.searchByTitle(title);
		lock.unlockRead();
		return similarSongs;
	}
	
	public JSONArray searchByTag(String tag) {
		
		JSONArray similarSongs = new JSONArray();
		lock.lockRead();
		similarSongs = super.searchByTag(tag);
		lock.unlockRead();
		return similarSongs;
	}
	
	public JSONObject jSonOutput(JSONObject obj) {
		
		JSONObject object = new JSONObject();
		lock.lockRead();
		object = super.jSonOutput(obj);
		lock.unlockRead();
		return object;
	}

}
