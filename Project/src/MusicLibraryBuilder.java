import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MusicLibraryBuilder {

	
	/*
	 * class that traverses the file system (lastfm_subset) and 
	 * builds the library (data structure initialization)
	 * 
	 */
	
	Path path;
	MusicLibrary lib;
	
	
	public MusicLibraryBuilder(Path path) {
		
		this.path = path;
		lib = new MusicLibrary();
			
		
	}
	
	public void traverseParser() {
		
		traverseParser(path);
	}
	
	public void traverseParser(Path path) {
		
		if(Files.isDirectory(path)) {
			try (DirectoryStream<Path> dir = Files.newDirectoryStream(path)) {
				
				for (Path entry : dir) {
					traverseParser(entry);
				}
				 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if (path.toString().endsWith(".json")) {
			// calls jsonGetter to open the file and send the contents to SongParser
			//System.out.println("in MLB path for valid json parser = " + path);
			this.jsonGetter(path);
		}
		
	}
	
	private void jsonGetter(Path path) {
		
		JSONParser parser = new JSONParser();

		try (BufferedReader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"))) {
			
			Object wholeFile = parser.parse(reader);
			//System.out.println("object is " + wholeFile);
			
			JSONObject contents = (JSONObject) wholeFile;
			//System.out.println("contents is " + contents);
			
			Song newSong = new Song(contents);
			
			//Song newSong = new Song(contents);
			//System.out.println("song is " + newSong.getArtist());
			
			lib.addSong(newSong); // add Sami about this implementation
			
			// now add that newSong to the library by calling the addSong method from MusicLibrary
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
