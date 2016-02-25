
import java.util.*;


/**
 * Parses command-line arguments into flag/value pairs for easy access.
 */
public class ArgumentParser {

	/** Stores arguments in a map, where the key is a flag. */
	private final Map<String, String> argumentMap;
	

	/**
	 * Initializes an empty argument map. The method {@link #parseArguments(String[])}
	 * should eventually be called to populate the map.
	 * @param flagCount 
	 * @param valueCount 
	 */
	public ArgumentParser() {
		argumentMap = new HashMap<>();
		
	}

	/**
	 * Initializes the argument map with the provided command-line arguments.
	 * Uses {@link #parseArguments(String[])} to populate the map.
	 *
	 * @param args command-line arguments
	 * @throws InvalidArgumentException 
	 * @see #parseArguments(String[])
	 */
	public ArgumentParser(String[] args) throws InvalidArgumentException {
		this();
		parseArguments(args);
	}

	/**
	 * Iterates through the array of command-line arguments. If a flag is
	 * found, will attempt to see if it is followed by a value. If so, the
	 * flag/value pair is added to the map. If it isn't followed by a Value
	 * then it exits the program gracefully
	 * 
	 *
	 * @param args command-line arguments
	 *
	 * @see #isFlag(String)
	 * @see #isValue(String)
	 */
	public void parseArguments(String[] args) throws InvalidArgumentException {
		if (args.length > 0) {
			for (int i = 0; i < args.length -1; i = i + 2) {
				if (isFlag(args[i]) == true && isValue(args[i + 1]) == true) {
					// check if flag = input
					if (args[i].equals("-input")) {
																// then check if value = correct input value
						argumentMap.put(args[i], args[i + 1]);
						
					}
					else if (args[i].equals("-output")) {
												
						argumentMap.put(args[i], args[i + 1]);
						
					}
					else if (args[i].equals("-order")) {
						
						argumentMap.put(args[i], args[i + 1]);
					}
					
					else {
						throw new InvalidArgumentException("Incorrect Command Line Arguments");
					}
					//		 add 1 to flagCount and add 1 to valueCount
					//		 argumentMap.put(args[i], args[i + 1]);
					// check if flag = output
					//		 then check if value = correct output value
					//		 add 1 to flagCount and add 1 to valueCount
					//		 argumentMap.put(args[i], args[i + 1]);
					// check if flag = order
					//		 then check if value = correct order value
					//		 add 1 to flagCount and add 1 to valueCount
					//		 argumentMap.put(args[i], args[i + 1]);
					//argumentMap.put(args[i], args[i + 1]);
				}
				
			}
			
			if (numFlags() != 3) {   									// if the number of flags isn't 3 then
				throw new InvalidArgumentException("Not correct amount of flags");	// then exit the program
			}
			
			
		} else {
			
			throw new InvalidArgumentException("No command line arguments");
		}
	}
	
	

	/**
	 * Tests if the provided argument is a flag by checking that it starts with
	 * a "-" dash symbol, and is followed by at least one non-whitespace
	 * character. For example, "-a" and "-1" are valid flags, but "-" and "- "
	 * are not valid flags.
	 *
	 * @param arg command-line argument
	 * @return true if the argument is a flag
	 */
	public static boolean isFlag(String arg) {
		
		
		if (arg != null && arg.startsWith("-")){		
			if (arg.trim().length() <= 1) {
				return false;
			}
			else {
				return true;
			}
		}	
		else{
			return false;
		}
	}

	/**
	 * Tests if the provided argument is a value by checking that it does not
	 * start with a "-" dash symbol, and contains at least one non-whitespace
	 * character. For example, "a" and "1" are valid values, but "-" and " "
	 * are not valid values.
	 *
	 * @param arg command-line argument
	 * @return true if the argument is a value
	 */
	public static boolean isValue(String arg) {
	
		if (arg != null &&  !arg.trim().startsWith("-")) {
			if (arg.trim().length() >= 1) {
				return true;
			}
			else {
			   return false;
			}
		}
		else{
			return false;
		}
		
	}

	/**
	 * Returns the number of flags stored.
	 *
	 * @return number of flags
	 */
	public int numFlags() {
	
		return argumentMap.size();
	}

	/**
	 * Tests if the provided flag is stored in the map.
	 *
	 * @param flag flag to check
	 * @return value if flag exists and has a value, or null if the flag
	 * does not exist or does not have a value
	 */
	public boolean hasFlag(String flag) {
		
		if (argumentMap.containsKey(flag)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Tests if the provided flag has a non-empty value.
	 *
	 * @param flag flag to check
	 * @return true if the flag exists and has a non-null non-empty value
	 */
	public boolean hasValue(String flag) {
		
		if (argumentMap.containsKey(flag) && argumentMap.get(flag) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns the value of a flag if it exists, and null otherwise.
	 *
	 * @param flag flag to check
	 * @return value of flag or null if flag does not exist or has no value
	 */
	public String getValue(String flag) {
		
		if (argumentMap.get(flag) != null) {
			return argumentMap.get(flag);
		} else {
			return null;
		}
	}

	@Override
	public String toString() {
		return argumentMap.toString();
	}
	
//	/*
//	 * Use this (and the unit tests) for debugging! 
//	 */
//	public static void main(String[] args) {
//		String[] test = new String[] {" -", "apple", "-b", "banana"};
//		ArgumentParser parser = new ArgumentParser(test);
//		
//		System.out.println("  Test: " + Arrays.toString(test));
//		System.out.println("Result: " + parser.toString());
//		
//		System.out.println(test[0]);
//		
//	}
}