import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class SearchTest {

	@Test
	public void testValidQueries() {

		String testName = "testValidQueries";
		Path actualOutput = Paths.get("results/searchResults.json");
		Path expectedOutput = Paths.get("output/searchResults.json");
		String[] args = {"-input", "input/lastfm_subset",
				"-output", "results/songsByArtistSubset.txt",
				"-order", "artist",
				"-threads", "5",
				"-searchInput", "queries/queries.json",
				"-searchOutput", actualOutput.toString()};
		ProjectTest.checkProjectJsonOutput(testName, 
				args,
				actualOutput,
				expectedOutput);		
	}

	@Test
	public void testInvalidSearchInputPath() {
		String testName = "testInvalidSearchInputPath";
		Path actualOutput = Paths.get("results/searchResults.json");
	
		String[] args = {"-input", "input/lastfm_subset",
				"-output", "results/songsByArtistSubset.txt",
				"-order", "artist",
				"-threads", "5",
				"-searchInput", "queries/BADPATH.json",
				"-searchOutput", actualOutput.toString()};
		ProjectTest.checkExceptions(testName, args); 
	}

	@Test
	public void testEmptySearchInput() {
		String testName = "testEmptySearchInput";
		Path actualOutput = Paths.get("results/searchResults.json");
	
		String[] args = {"-input", "input/lastfm_subset",
				"-output", "results/songsByArtistSubset.txt",
				"-order", "artist",
				"-threads", "5",
				"-searchInput", "queries/empty.json",
				"-searchOutput", actualOutput.toString()};
		ProjectTest.checkExceptions(testName, args); 
	}

	@Test
	public void testMissingQueryType() {
		String testName = "testMissingQueryType";
		Path actualOutput = Paths.get("results/searchResults.json");
		Path expectedOutput = Paths.get("output/searchMissingType.json");
		
		String[] args = {"-input", "input/lastfm_subset",
				"-output", "results/songsByArtistSubset.txt",
				"-order", "artist",
				"-threads", "5",
				"-searchInput", "queries/noTitle.json",
				"-searchOutput", actualOutput.toString()};
		ProjectTest.checkProjectJsonOutput(testName, 
				args,
				actualOutput,
				expectedOutput);		
	}
	
}