import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParsing implements Runnable {
	
	Path path;
	ThreadSafeMusicLibrary lib;
	
	
	public JsonParsing (Path path, ThreadSafeMusicLibrary lib) {
		this.path = path;
		this.lib = lib;
		
	}

	@Override
	public void run() {
		
		jsonGetter(path);
	}
	
	/*
	 * My jsonGetter method takes in a valid json file from my traverseParser method and extracts the JSONObject.
	 * Then passes that JSONObject to the creation of a Song instance object and then passes that Song instance
	 * to my MusicLibrary by using the addSong method.
	 */
	
	
	private void jsonGetter(Path path) {
			
			JSONParser parser = new JSONParser();
	
			try (BufferedReader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"))) {
				
				Object wholeFile = parser.parse(reader);
				
				JSONObject contents = (JSONObject) wholeFile;
				
				Song newSong = new Song(contents);
				
				lib.addSong(newSong);
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			} 
		}

}
