import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.FileSystems;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	ThreadTest.ThreadConfigurationTest.class,
	ThreadTest.ThreadOutputTest.class	
})
public class ThreadTest {

	/** Configure this on your system if you want to have a longer timeout. */
	public static final int TIMEOUT = 60000;


	/* Arguments
	 * Required:
	 -input <input_path> 
	 -output <output_path> 
	 -order <tag|artist|title>

	 * Optional:
	-threads <number_threads> 
	-queries <query_path>
	 */			

	public static class ThreadOutputTest {

		@Test(timeout = TIMEOUT)
		public void testSimpleTenThreads() {

			String testName = "testSimpleTenThreads";
			String[] args = {"-input", "input/lastfm_simple",
					"-output", "results/songsByArtistSimple.txt",
					"-order", "artist",
					"-threads", "10"};

			ProjectTest.checkProjectOutput(testName, args, 
					FileSystems.getDefault().getPath("results/songsByArtistSimple.txt"), //actual 
					FileSystems.getDefault().getPath("output/songsByArtistSimple.txt")); //expected
		}


		@Test(timeout = TIMEOUT)
		public void testComplexTenThreads() {

			String testName = "testComplexTenThreads";
			String[] args = {"-input", "input/lastfm_subset",
					"-output", "results/songsByArtistSubset.txt",
					"-order", "artist",
					"-threads", "10"};

			ProjectTest.checkProjectOutput(testName, args, 
					FileSystems.getDefault().getPath("results/songsByArtistSubset.txt"), //actual 
					FileSystems.getDefault().getPath("output/songsByArtistSubset.txt")); //expected
		}

		@Test(timeout = TIMEOUT)
		public void testComplexOneThread() {

			String testName = "testComplexOneThread";
			String[] args = {"-input", "input/lastfm_subset",
					"-output", "results/songsByArtistSubset.txt",
					"-order", "artist",
					"-threads", "1"};

			ProjectTest.checkProjectOutput(testName, args, 
					FileSystems.getDefault().getPath("results/songsByArtistSubset.txt"), //actual 
					FileSystems.getDefault().getPath("output/songsByArtistSubset.txt")); //expected
		}

		@Test(timeout = TIMEOUT)
		public void testComplexFiveThreads() {

			String testName = "testComplexFiveThreads";
			String[] args = {"-input", "input/lastfm_subset",
					"-output", "results/songsByArtistSubset.txt",
					"-order", "artist",
					"-threads", "5"};

			ProjectTest.checkProjectOutput(testName, args, 
					FileSystems.getDefault().getPath("results/songsByArtistSubset.txt"), //actual 
					FileSystems.getDefault().getPath("output/songsByArtistSubset.txt")); //expected
		}

	}

	public static class ThreadConfigurationTest {

		@Test(timeout = TIMEOUT)
		public void testNumberThreadsDecimal() {

			String testName = "testNumberThreadsDecimal";
			String[] args = {"-input", "input/lastfm_subset",
					"-output", "results/songsByArtistSubset.txt",
					"-order", "artist",
					"-threads", "5.2"};

			ProjectTest.checkExceptions(testName, args); 
		}

		@Test(timeout = TIMEOUT)
		public void testNumberThreadsNegative() {

			String testName = "testNumberThreadsNegative";
			String[] args = {"-input", "input/lastfm_subset",
					"-output", "results/songsByArtistSubset.txt",
					"-order", "artist",
					"-threads", "-4"};

			ProjectTest.checkExceptions(testName, args); 
		}

		@Test(timeout = TIMEOUT)
		public void testNumberThreadsString() {

			String testName = "testNumberThreadsString";
			String[] args = {"-input", "input/lastfm_subset",
					"-output", "results/songsByArtistSubset.txt",
					"-order", "artist",
					"-threads", "A1"};

			ProjectTest.checkExceptions(testName, args); 
		}
	}
}