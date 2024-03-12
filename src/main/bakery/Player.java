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

	hand = null;

    }

    /**
     * TO add to hand
     * @param ingredients List of ingredients to add
     */

    public void addToHand(List<Ingredient> ingredients) {

	hand.addAll(ingredients);

    }

    /**
     * to add 1 ingred to hand
     * @param ingredient Ingred to add to hand
     */

    public void addToHand(Ingredient ingredient) {

	hand.add(ingredient);

    }

    /**
     * check if in hand
     *  @param ingredient Ingred to check
     * @return True if in hand
     */

    public boolean hasIngredient(Ingredient ingredient) {

	if ( hand.contains(ingredient) == true ) {
	    
	    return true;

	}

	return false;

    }

    /**
     * rm from hand
     * @param ingredient Ingred to remove
     */

    public void removeFromHand(Ingredient ingredient) {

	//  May fail due to object IDs, Comparable<> not being finished...

	hand.remove(ingredient);

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

	List<String> h_list = Ingredient.ingrd_list_to_str_list(hand);

	String hand_str = String.join(", ", h_list);

	return hand_str;

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
