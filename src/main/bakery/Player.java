package bakery;

import java.util.*;
import java.io.*;

import bakery.*;

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

    private int actions_taken;
    
    /**
     *  Initiate player
     * @param name Player name
     */

    public Player(String name) {

	this.name = name;

	this.actions_taken = 0;

	hand = new ArrayList<Ingredient>();

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

	for ( Ingredient i : hand ) {

	    if ( i.toString() == ingredient.toString() ) {

		return true;

	    }

	}

	return false;

    }

    /**
     * rm from hand
     * @param ingredient a
     * @throws WrongIngredientsException a
     */

    public void removeFromHand(Ingredient ingredient) throws WrongIngredientsException {

	if ( hasIngredient(ingredient) == true ) {

	    hand.remove(ingredient);

	} else {

	    throw new WrongIngredientsException(ingredient.toString());

	}

    }

    /**
     * empty hand
     *  @return hand
     */

    public List<Ingredient> clear_hand() {

	List<Ingredient> hand_copy = new ArrayList<>();
	
	hand_copy.addAll(this.hand);	

	this.hand.clear();

	return hand_copy;

    }

    /**
     *  get hand as oops objects
     *  @return hand
     */

    public List<Ingredient> getHand() {

	// System.out.println(hand);

	return hand;

    }

    /**
     *  get hand as string
     *  @return hand as str
     */
     
    public String getHandStr() {

	Map<String, Integer> hand_map = CustomerOrder.list_to_quantities(hand);

	List<String> hand_list = new ArrayList<String>();

	String i_str = "";

	for (String key : hand_map.keySet()) {

	    i_str = key;

	    if (hand_map.get(key) > 1) {

		if ( key.contains("𓅭") ) {

		    i_str = key + "(x" + hand_map.get(key) + ")";

		} else {

		    i_str = key + " (x" + hand_map.get(key) + ")";

		}

	    }

	    i_str = i_str.substring(0, 1).toUpperCase() + i_str.substring(1);
		    	
	    hand_list.add(i_str);

	}

	Collections.sort(hand_list);

	return String.join(", ", hand_list);

    }

    /**
     *  output a string - name of player
     * @return player name
     */

    public String toString() {

	return name;

    }
 
    /**
     * get actions taken
     * @return actions
     */

    public int get_actions_taken() {

	return this.actions_taken;

    }
 
    /**
     * inc actions taken
     */

    public void inc_actions_taken() {

	this.actions_taken += 1;

    }
 
    /**
     * reset actions taken
     */

    public void reset_actions_taken() {

	this.actions_taken = 0;

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
