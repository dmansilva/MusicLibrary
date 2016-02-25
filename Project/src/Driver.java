
import java.nio.file.Path;
import java.nio.file.Paths;

public class Driver {
	
	public static void main(String[] args)  {
		
		
		try {
			ArgumentParser ap = new ArgumentParser(args);
			Path inputPath = Paths.get(ap.getValue("-input"));
			Path outputPath = Paths.get(ap.getValue("-output"));
			System.out.println("in main output path is " + outputPath);
			
			MusicLibraryBuilder mlb = new MusicLibraryBuilder(inputPath);
			MusicLibrary ml = new MusicLibrary();
			
			mlb.traverseParser();
			
			
				
				
				if (ap.getValue("-order").equals("artist")) {
					
					ml.outputByArtist(outputPath);
					
				}
				else if (ap.getValue("-order").equals("title")) {
					
					ml.outputByTitle(outputPath);
					
				}
				else if (ap.getValue("-order").equals("tag")) {
					
					ml.outputByTag(outputPath);
					
				}
				
				
		} catch (InvalidArgumentException e) {
			
			e.printStackTrace();
			System.out.println("Incorrect Arguments");
		}
		
	}

}
