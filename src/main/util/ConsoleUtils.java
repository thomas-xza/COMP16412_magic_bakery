package util;
import java.util.List;
import java.io.Console;

/**
 * Helper methods for console i/o.
 * @author thomas
 */

public class ConsoleUtils {

    public Console console = system.console();

    /**
     * Helper methods for console i/o.
     * @author thomas
     */
    
    public ConsoleUtils() {

	/**
	 * Helper methods for console i/o.
	 * @author thomas
	 */
    }

    public String readLine() {

	input = console.readLine("");

	return input;

    }

    public String readLine(String fmt, Object... args) {

	input = console.readLine("");

    }

    public File promptForFilePath(String prompt) {

	

    }

    // public List<String> promptForNewPlayers(String prompt) {

    // }

    // public boolean promptForStartLoad(String prompt) {

    // }

    // public boolean promptForYesNo(String prompt) {

    // }

}
