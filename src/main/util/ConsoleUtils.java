package util;

import java.util.*;
import java.io.*;

import bakery.*;
import bakery.MagicBakery.ActionType;

/**
 *   Blah
 *   @author thomas.
 * @version 1.5
 * @since 1.0
*/

public class ConsoleUtils {

    private Console console;

    /**
     * Helper methods for console i/o.
     */
    
    public ConsoleUtils() {

	console = System.console();

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
     * @param prompt blah
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
     * some desc
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

    /**
     * ask
     * @param prompt Request
     * @param bakery Game instance
     * @return blah
     */

    public Player promptForExistingPlayer(String prompt, MagicBakery bakery) {

	Player blah = new Player("john");

	return blah;

    }

    /**
     * Ask user for ingredient.
     * @param prompt Request
     * @param ingredients lists of bits
     * @return ingr
     */

    public Ingredient promptForIngredient(String prompt, Collection<Ingredient> ingredients) {

	Ingredient a = new Ingredient("a");

	return a;

    }

    /**
     * ask
     * @param prompt Request
     * @param bakery game
     * @return action
     */
    public ActionType promptForAction(String prompt, MagicBakery bakery) {
	
	return ActionType.REFRESH_PANTRY;
	
    }

    /**
     * Ask customer
     * @param prompt Request
     * @param customers list of custs
     * @return order
     */
    public CustomerOrder promptForCustomer(String prompt, Collection<CustomerOrder> customers) {

	CustomerOrder a = CustomerOrder.fast_order();

	return a;

    }
    

    /**
     * ask
     * @param prompt Request
     * @param collection a
     * @return answer
     */    
    
    private Object promptEnumerateCollection(String prompt, Collection<Object> collection) {

	Ingredient a = new Ingredient("A");

	return a;

    }
    
}
