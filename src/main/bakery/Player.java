package bakery;

import java.util.*;
import java.io.*;

/**
 *   class
 *   @author thomas.
 * @version 1.5
 * @since 1.0
*/

public class Player
    implements Serializable {

    private String name;

    private List<Ingredient> hand;

    private static final long serialVersionUID = 4;

    /**
     *  Initiate player
     * @param name Player name
     */

    public Player(String name) {

	this.name = name;

    }

    /**
     * TO add to hand
     * @param ingredients List of ingredients to add
     */

    public void addToHand(List<Ingredient> ingredients) {

    }

    /**
     * to add 1 ingred to hand
     * @param ingredient Ingred to add to hand
     */

    public void addToHand(Ingredient ingredient) {

    }

    /**
     * check if in hand
     *  @param ingredient Ingred to check
     * @return True if in hand
     */

    public boolean hasIngredient(Ingredient ingredient) {

	return true;

    }

    /**
     * rm from hand
     * @param ingredient Ingred to remove
     */

    public void removeFromHand(Ingredient ingredient) {

    }

    /**
     *  get hand as oops objects
     *  @return hand
     */

    public List<Ingredient> getHand() {

	List<Ingredient> hand = new ArrayList<>();

	return hand;

    }

    /**
     *  get hand as string
     *  @return hand as str
     */
     
    public String getHandStr() {

	return "A";

    }

    /**
     *  output a string - name of player
     * @return player name
     */

    public String toString() {

	return name;

    }
 
    /**
     *  func
     * @return a
     */
    
    public static List<Player> fast_player_list() {

	Player a = new Player("a");

	List<Player> b = new ArrayList<>();

	b.add(a);

	return b;
	
    }

}
