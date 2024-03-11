package util;
import java.util.*;
import java.io.*;

/**
 * Helper methods for console i/o.
 * @author thomas
 */

public class ConsoleUtils {

    public Console console;

    /**
     * Helper methods for console i/o.
     * @author thomas
     */
    
    public ConsoleUtils() {

	console = System.console();

	/**
	 * Helper methods for console i/o.
	 * @author thomas
	 */
    }

    /**
     * Wrapped around base method.
     * @return Input from user
     */

    public String readLine() {

	String input = console.readLine("");

	return input;

    }

    /**
     * Read line and format it.
     * @param fmt readLine customisations
     * @param args  readLine customisations
     * @return formatted string
     */

    public String readLine(String fmt, Object... args) {

	String input = console.readLine(fmt, args);

	return input;

    }

    /**
     * Get file path
     * @param Request
     * @return File object
     */

    public File promptForFilePath(String prompt) {

	String input = console.readLine(prompt);

	File path = new File(input);

	return path;

    }

    /**
     * Ask for player names.
     * @param prompt Request
     * @return Parsed input as strings.
     */

    public List<String> promptForNewPlayers(String prompt) {

	List<String> players = new ArrayList<>();

	String input = console.readLine(prompt);

	while (input != null && players.size() < 2) {

	    if ( players.contains(input) ) {

	    } else { players.add(input); }

	    input = console.readLine(prompt);

	    if (players.size() == 5) {

		input = null;

	    }

	}

	return players;

    }

    /**
     * @param prompt Request
     * @return Result as boolean.
     */

    public boolean promptForStartLoad(String prompt) {

	String input = console.readLine(prompt + "[S]tart/[L]oad");

	if (input.toLowerCase().contains("s")) {

	    return true;

	}

	return false;

    }

    /**
     * Yes/no input
     * @param prompt Request
     * @return Result as boolean.
     */

    public boolean promptForYesNo(String prompt) {

	String input = console.readLine(prompt + "[Y]es/[N]o");

	if (input.toLowerCase().contains("y")) {

	    return true;

	}

	return true;

    }

}
