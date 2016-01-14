import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class LibraryTest {

	private static final String INPUTFLAG = "-input";
	private static final String OUTPUTFLAG = "-output";
	private static final String ORDERFLAG = "-order";
	
	private static final String ARTIST = "artist";
	private static final String TITLE = "title";
	private static final String TAG = "tag";
	
	
	@Test
	public void testBadInputPath() {
		ProjectTest.checkExceptions("testBadInputPath", new String[] {INPUTFLAG, "/BAD/PATH", OUTPUTFLAG, (ProjectTest.RESULT_DIR + "/badInputPath.txt"), ORDERFLAG, ARTIST});		
	}

	@Test
	public void testBadOutputPath() {
		ProjectTest.checkExceptions("testBadOutputPath", new String[] {INPUTFLAG, (ProjectTest.INPUT_DIR + "/lastfm_simple"), OUTPUTFLAG, "/BAD/PATH", ORDERFLAG, ARTIST});		
	}

	@Test
	public void testBadOrderValue() {
		ProjectTest.checkExceptions("testBadOrderValue", new String[] {INPUTFLAG, (ProjectTest.INPUT_DIR + "/lastfm_simple"), OUTPUTFLAG, (ProjectTest.RESULT_DIR + "/badOrderValue.txt"), ORDERFLAG, "BADORDER"});		
	}
	
	@Test
	public void testSimpleByArtist() {
		String file = "songsByArtistSimple.txt";
		String inputLocation = "lastfm_simple";
		String test = "testSimpleByArtist";
		runTest(file, inputLocation, test, ARTIST);		
	}
	
	@Test
	public void testSimpleByArtistWithTxtFile() {
		String file = "songsByArtistWithTxtFile.txt";
		String inputLocation = "lastfm_txtfile";
		String test = "testSimpleByArtistWithTxtFile";
		runTest(file, inputLocation, test, ARTIST);		
		
	}
	
	@Test
	public void testByArtist() {
		String file = "songsByArtistSubset.txt";
		String inputLocation = "lastfm_subset";
		String test = "testByArtist";
		runTest(file, inputLocation, test, ARTIST);		
		
	}

	@Test
	public void testByTitle() {
		String file = "songsByTitleSubset.txt";
		String inputLocation = "lastfm_subset";
		String test = "testByTitle";
		runTest(file, inputLocation, test, TITLE);		
		
	}
	
	@Test
	public void testByTag() {
		String file = "songsByTagSubset.txt";
		String inputLocation = "lastfm_subset";
		String test = "testByTag";
		runTest(file, inputLocation, test, TAG);		
		
	}

	
	private void runTest(String file, String inputLocation, String test, String order) {
		Path actual = Paths.get(ProjectTest.RESULT_DIR + "/" + file);
		Path expected = Paths.get(ProjectTest.OUTPUT_DIR + "/" + file);
		
		String[] args = new String[] {INPUTFLAG, (ProjectTest.INPUT_DIR + "/" + inputLocation), OUTPUTFLAG, (ProjectTest.RESULT_DIR + "/" + file), ORDERFLAG, order}; 
		ProjectTest.checkProjectOutput(test, 
				args, 
				actual, 
				expected);		
	}
	
}
