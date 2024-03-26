package bakery;

import java.util.*;
import java.io.*;
import java.lang.*;

import bakery.*;
import util.*;

/**
 * class
 * @author thomas.
 * @version 1.5
 * @since 1.0
*/

public class MagicBakery
    implements Serializable {

    private Customers customers;
    private Collection<Layer> layers;
    private Collection<Player> players;
    private Collection<Ingredient> pantry;
    private Collection<Ingredient> pantryDiscard;
    private Random random;
    private Collection<Ingredient> pantryDeck;

    private static final long serialVersionUID = 3;

    private int actions_taken = 0;

    /**
     * Initiate Magic
     * @param seed a
     * @param ingredientDeckFile a
     * @param layerDeckFile a
     * @throws FileNotFoundException a
     */
    
    public MagicBakery(long seed, String ingredientDeckFile, String layerDeckFile) throws FileNotFoundException {

	this.pantry = new LinkedList<Ingredient>();

	this.players = new LinkedList<Player>();

	this.pantryDiscard = new Stack<Ingredient>();

	this.pantryDeck = new Stack<Ingredient>();

	this.random = new Random(seed);
	
	File f_1 = new File(ingredientDeckFile);
	
	File f_2 = new File(layerDeckFile);
	
	if ( !f_1.exists() || !f_2.exists() ) {

	    throw new FileNotFoundException();

	}

	List<Ingredient> ingredients_list = null;

	try {

	    ingredients_list = CardUtils.readIngredientFile(ingredientDeckFile);

	} catch (IOException e) { ; }

	for ( Ingredient ingredient : ingredients_list ) {

	    ((Stack) this.pantryDeck).push(ingredient);

	}

	this.layers = CardUtils.readLayerFile(layerDeckFile);

    }

    /**
     *  func
     * @param playerNames a
     * @param customerDeckFile a
     * @throws FileNotFoundException a
     * @throws IllegalArgumentException a
     */
    
    public void startGame(List<String> playerNames, String customerDeckFile) throws FileNotFoundException, IllegalArgumentException {

	// System.out.println("Initialising players");

	if ( playerNames.size() > 5 ) {

	    throw new IllegalArgumentException();

	} else {

	    for ( String player_name : playerNames ) {

		this.players.add(new Player(player_name));

	    }

	}

	// System.out.println("Initialising customers");
	
	File f = new File(customerDeckFile);
	
	if( !f.exists() ) { throw new FileNotFoundException();

	} else {

	    try {
		
		this.customers = new Customers(customerDeckFile, this.random, this.layers, this.players.size());

	    } catch (IOException e) { ; }

	}

	// System.out.println("Initialising pantry");
	
	Collections.shuffle(((Stack)this.pantryDeck), this.random);

	for (int i = 0 ; i < 5 ; i++ ) {

	    ((LinkedList) this.pantry).add(((Stack)this.pantryDeck).pop());

	}

	// System.out.println("Initialising hand");
	
	for (Player player : this.players) {

	    player.addToHand(((Ingredient)((Stack)this.pantryDeck).pop()));
	    player.addToHand(((Ingredient)((Stack)this.pantryDeck).pop()));
	    player.addToHand(((Ingredient)((Stack)this.pantryDeck).pop()));

	}

    }
    
    /**
     *  function
     */
    
    public enum ActionType {
    
	DRAW_INGREDIENT,PASS_INGREDIENT,BAKE_LAYER,FULFIL_ORDER,REFRESH_PANTRY;
	
    }

    /**
     *  function
     * @param layer a
     * @throws TooManyActionsException
     */
    
    public void bakeLayer(Layer layer) {

	if ( getActionsRemaining() == 0 ) { throw new TooManyActionsException(); };
	this.actions_taken += 1;
	
    }

    /**
     *  func
     * @return a
     * @throws TooManyActionsException
     */
    
    private Ingredient drawFromPantryDeck() {

	if ( getActionsRemaining() == 0 ) { throw new TooManyActionsException(); };
	
	this.actions_taken += 1;
	
	return (Ingredient)((Stack)this.pantryDeck).pop();
	
    }

    /**
     *  func
     * @param ingredient a
     */

    public void pantry_to_hand(String ingredient) {
	
	for (Ingredient i : this.pantry) {

	    if ( i.toString() == ingredient ) {

		((LinkedList)this.pantry).remove(i);

		Player current_player = getCurrentPlayer();

		current_player.addToHand(i);

		break;

	    }

	}

    }
    
    /**
     *  func
     * @param ingredientName a
     * @throws TooManyActionsException
     */
    public void drawFromPantry(String ingredientName) throws TooManyActionsException, WrongIngredientsException {

	if ( getActionsRemaining() == 0 ) { throw new TooManyActionsException(); };

	boolean found = false;

	for ( Ingredient i : this.pantry ) {

	    if ( i.toString() == ingredientName ) {

		found = true;

	    }

	}	

	if ( found == false ) {

	    throw new WrongIngredientsException(ingredientName);

	}
	
	this.actions_taken += 1;

	pantry_to_hand(ingredientName);

    }


    /**
     *  func
     * @param ingredient a
     * @throws TooManyActionsException
     */
    public void drawFromPantry(Ingredient ingredient) {

	if ( getActionsRemaining() == 0 ) { throw new TooManyActionsException(); };
	if ( ((LinkedList)this.pantry).contains(ingredient) == false ) {

	    throw new WrongIngredientsException(ingredient.toString());

	}

	this.actions_taken += 1;

	pantry_to_hand(ingredient.toString());

    }

    /**
     *  func
     * @return a
     */
    public boolean endTurn() {

	return true;

    }


    /**
     *  func
     * @param customer a
     * @param garnish a
     * @return a
     * @throws TooManyActionsException
     */
    public List<Ingredient> fulfillOrder(CustomerOrder customer, boolean garnish) {
	
	if ( getActionsRemaining() == 0 ) { throw new TooManyActionsException(); };
	    

	this.actions_taken += 1;

	
	
        List<Ingredient> a = Ingredient.fast_ingrd_list();

	return a;

    }


    /**
     *  func
     * @return a
     */
    public int getActionsPermitted() {

	if ( this.players.size() <= 3 ) {

	    return 3;

	} else {

	    return 2;

	}

    }

    /**
     *  func
     * @return a
     */
    public int getActionsRemaining() {

	return getActionsPermitted() - this.actions_taken;

    }

    /**
     *  func
     * @return a
     */

    public Collection<Layer> getBakeableLayers() {

	List<Layer> a = Layer.fast_layer_list();

	return a;

    }

    /**
     *  func
     * @return a
     */

    public Player getCurrentPlayer() {

	return (Player)((Queue)this.players).peek();

    }

    /**
     *  func
     * @return a
     */

    public Customers getCustomers() {

	return this.customers;

    }

    /**
     *  func
     * @return a
     */

    public Collection<CustomerOrder> getFulfilableCustomers() {

	 List<CustomerOrder> a = CustomerOrder.fast_order_list();

	 return a;

    }

    /**
     *  func
     * @return a
     */

    public Collection<CustomerOrder> getGarnishableCustomers() {

	 List<CustomerOrder> a = CustomerOrder.fast_order_list();

	 return a;

    }

    /**
     *  func
     * @return a
     */

    public Collection<Layer> getLayers() {

	return this.layers;

    }

    /**
     *  func
     * @return a
     */

    public Collection<Ingredient> getPantry() {

	return this.pantry;

    }

    /**
     *  func
     * @return a
     */

    public Collection<Player> getPlayers() {

	return this.players;

    }

    /**
     *  func
     * @param file f
     * @return a
     * @throws FileNotFoundException a
     */

    public static MagicBakery loadState(File file) throws FileNotFoundException {

	Object result = null;

	// if( !file.exists() ) {

	//     throw new FileNotFoundException();

	// }
	
	MagicBakery a = null;

	a = new MagicBakery(0, "./io/ingredients.csv", "./io/layers.csv");

	return a;
	
    }

    /**
     *  func
     * @param ingredient a
     * @param recipient a
     * @throws TooManyActionsException
     */

    public void passCard(Ingredient ingredient, Player recipient) {

	if ( getActionsRemaining() == 0 ) { throw new TooManyActionsException(); };
	this.actions_taken += 1;
	
    }

    /**
     *  func
     */

    public void printCustomerServiceRecord() {

    }

    /**
     *  func
     */

    public void printGameState() {

    }

    /**
     *  func
     * @throws TooManyActionsException
     */

    public void refreshPantry() throws TooManyActionsException {

	if ( getActionsRemaining() == 0 ) { throw new TooManyActionsException(); };
	this.actions_taken += 1;
	
    }

    /**
     *  func
     * @param file a
     * @throws FileNotFoundException a
     */

    public void saveState(File file) throws FileNotFoundException {

	if (!file.exists()) {

	    throw new FileNotFoundException();

	}

    }

}
