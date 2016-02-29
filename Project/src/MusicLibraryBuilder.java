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
	 * class that traverses the file system (given input path) and 
	 * builds the library (data structure initialization)
	 * 
	 */
	
	Path path;
	MusicLibrary lib;
	
	/*
	 * Constructor is initializing a path as well as a new MusicLibrary, an instance of the MusicLibrary class
	 */
	
	
	public MusicLibraryBuilder(Path path) {
		
		this.path = path;
		lib = new MusicLibrary();
		
	}
	
	/*
	 * Method OverLoading for traverseParser. The first one is just a helper method so that I don't have to pass a
	 * path variable as a parameter when I call it in my driver. The second traverseParser method takes in a path
	 * and recursively traverses the path if it a directory until we reach a valid json file. If it a valid json or 
	 * JSON file I path the file to my jsonGetter method to extract my JSONObject.
	 */
	
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
				e.printStackTrace();
			}
		}
		
		else if (path.toString().endsWith(".json") || path.toString().endsWith(".JSON")) {
			
			this.jsonGetter(path);
		}
		
	}
	
	/*
	 * My jsonGetter method takes in a valid json file from my traverseParser method. I first create a JSONParser. Then
	 * I use try with resources to open the file using a BufferedReader. Once I've opened the file I call parser.parse 
	 * to extract the entire file and cast that into an object called wholeFile. Then I call the wholeFile object to a
	 * JSONObject. The create an instance of the song class and add the JSONObject into there. Then I call the addSong 
	 * method passing in the newSong variable on my MusicLibrary instance i called lib.
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
	
	/*
	 * I created a getMusicLibrary method so that I can get the MusicLibrary instance I created here in my Driver class
	 */
	
	public MusicLibrary getMusicLibrary() {
		return lib;
	}
}
