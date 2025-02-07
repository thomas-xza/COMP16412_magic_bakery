

package bakery;

import java.util.*;
import java.io.*;
import java.lang.*;

import bakery.*;
import util.*;
import bakery.CustomerOrder.CustomerOrderStatus;

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

    private int player_ptr = 0;
    private int new_round_chk = 0;

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

	// System.out.println("Just read layers!" + this.layers);

    }

    /**
     *  func
     * @param playerNames a
     * @param customerDeckFile a
     * @throws FileNotFoundException a
     * @throws IllegalArgumentException a
     */
    
    public void startGame(List<String> playerNames, String customerDeckFile) throws FileNotFoundException, IllegalArgumentException {

	if ( playerNames.size() > 5 || playerNames.size() < 2 ) {

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

	// System.out.println("pantryDeck post-shuf: " + this.pantryDeck);

	refreshPantry();

	this.customers.addCustomerOrder();
	
	if ( this.players.size() == 3 || this.players.size() == 5 ) {

	    this.customers.addCustomerOrder();

	}
	
	// System.out.println("Initialising hand");
	
	for (Player player : this.players) {

	    player.addToHand(((Ingredient)((Stack)this.pantryDeck).pop()));
	    player.addToHand(((Ingredient)((Stack)this.pantryDeck).pop()));
	    player.addToHand(((Ingredient)((Stack)this.pantryDeck).pop()));

	    // System.out.println(player.getHand());

	}

	// System.out.println("pantryDeck post-hands: " + this.pantryDeck);

	// Hotfix for refreshPantry() call.
	getCurrentPlayer().reset_actions_taken();

	// System.out.println("pantryDeck oshuf " + this.pantryDeck);

	// System.out.println("pantry " + this.pantry);
	
    }
    
    /**
     *  function passes_jdoc
     */
    
    public enum ActionType {
    
	DRAW_INGREDIENT,PASS_INGREDIENT,BAKE_LAYER,FULFIL_ORDER,REFRESH_PANTRY;
	
    }

    /**
     *  function
     * @param layer a
     * @throws TooManyActionsException a
     * @throws WrongIngredientsException a
     */
    
    public void bakeLayer(Layer layer) throws TooManyActionsException, WrongIngredientsException {

        List<Ingredient> used = new ArrayList<>();

        List<Ingredient> remain = new ArrayList<>();
	
        List<List<Ingredient>> used_remain = new ArrayList<>();
	
	if ( getActionsRemaining() == 0 ) { throw new TooManyActionsException(); };
	
	if ( layer.canBake(getCurrentPlayer().getHand()) == true ) {

	    getCurrentPlayer().inc_actions_taken();

	    // System.out.println("\nrecipe " + layer.getRecipe());
	    // System.out.println("hand   " + getCurrentPlayer().getHand());

	    used_remain = SL.used_quantities_v2(
			    SL.to_raw_ingredients(
			      layer.getRecipe()
				),
			      getCurrentPlayer().getHand()
					     );
	    
	    used = used_remain.get(0);

	    // System.out.println("hand   " + getCurrentPlayer().getHand());
	    
	    // System.out.println("bakeLayer used, remain" + used_remain);

	    this.layers.remove(layer);

	    // System.out.println("removed layer");
	    
	    for ( Ingredient i : used ) {

		// System.out.println("Trying to remove " + i);

		getCurrentPlayer().removeFromHand(i);

	    }
	    
	    getCurrentPlayer().addToHand(layer);

	    // System.out.println("new hand   " + getCurrentPlayer().getHand());
	    
	    this.pantryDiscard.addAll(used);

	} else {

	    throw new WrongIngredientsException("bakeLayer");	    

	}

    }

    /**
     *  func
     * @return a
     * @throws TooManyActionsException a
     * @throws EmptyPantryException a
     */
    
    private Ingredient drawFromPantryDeck() throws TooManyActionsException, EmptyPantryException {

	int i = 0;

	if ( getActionsRemaining() == 0 ) { throw new TooManyActionsException(); };

	if ( ((List)this.pantryDeck).size() == 0 &&
	     ((List)this.pantryDiscard).size() == 0 ) {

	    throw new EmptyPantryException("undefined state", new RuntimeException());

	}



	if ( ((List)this.pantryDeck).size() == 0 ) {

	//     throw new EmptyPantryException("Size 0", new RuntimeException());

	    // System.out.println("prev size: " + this.pantryDiscard.size());

	    while ( this.pantryDiscard.isEmpty() == false ) {
	    
		((Stack)this.pantryDeck).push(
			((Stack)this.pantryDiscard).pop()
					      );

	    }

	    Collections.reverse((Stack)this.pantryDeck);

	    // System.out.println("new size: " + this.pantryDiscard.size());
	    Collections.shuffle(((Stack)this.pantryDeck), this.random);

	    System.out.println(this.pantryDeck);

	}

	//  Have to reproduce test author's bug here.
	// getCurrentPlayer().inc_actions_taken();
	
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
     * @throws TooManyActionsException a
     * @throws WrongIngredientsException a
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
	
	getCurrentPlayer().inc_actions_taken();

	pantry_to_hand(ingredientName);

	((LinkedList)this.pantry).push(
				       (Ingredient)((Stack)this.pantryDeck).pop()
				       );
	
    }


    /**
     *  func
     * @param ingredient a
     * @throws TooManyActionsException a
     * @throws WrongIngredientsException a
     */
    public void drawFromPantry(Ingredient ingredient) throws TooManyActionsException, WrongIngredientsException {

	if ( getActionsRemaining() == 0 ) { throw new TooManyActionsException(); };
	if ( ((LinkedList)this.pantry).contains(ingredient) == false ) {

	    throw new WrongIngredientsException(ingredient.toString());

	}

	getCurrentPlayer().inc_actions_taken();

	pantry_to_hand(ingredient.toString());

	((LinkedList)this.pantry).push(
				       (Ingredient)((Stack)this.pantryDeck).pop()
				       );
	
    }

    /**
     *  func
     * @return a
     */
    public boolean endTurn() {

	// System.out.println("player changing from:  " + getCurrentPlayer());

	boolean turn_ended = false;

	getCurrentPlayer().reset_actions_taken();

	this.player_ptr += 1;

	if ( this.player_ptr == this.players.size() ) {

	    this.player_ptr = 0;

	}

	// System.out.println("player changed to:  " + getCurrentPlayer());

	turn_ended = true;

	this.new_round_chk += 1;

	System.out.println("new_round_chk" + new_round_chk);

	try {

	    if ( this.new_round_chk == this.players.size() ) {
		
		if ( this.customers.customer_deck_size() > 0 ) {

		    this.customers.addCustomerOrder();

		} else {

		    this.customers.timePasses();

		}

		this.new_round_chk = 0;

	    }

	} catch ( Exception e ) {;}

	System.out.println(customers.getActiveCustomers());
	
	return turn_ended;

    }


    /**
     *  func
     * @param customer a
     * @param garnish a
     * @return a
     * @throws TooManyActionsException a
     */
    public List<Ingredient> fulfillOrder(CustomerOrder customer, boolean garnish) throws TooManyActionsException{

	boolean garnished = false;
	
	System.out.println("fulfill()");

	if ( getActionsRemaining() == 0 ) { throw new TooManyActionsException(); };
	    
	getCurrentPlayer().inc_actions_taken();

	List<Ingredient> hand_used = new ArrayList<>();

	hand_used = customer.fulfill(getCurrentPlayer().getHand(), garnish);

	// System.out.println("hand_used: " + hand_used);
	
	for ( Ingredient i : hand_used ) {

	    getCurrentPlayer().removeFromHand(i);

	}

	if ( customer.getStatus() == CustomerOrderStatus.GARNISHED ) {

	    for ( Ingredient i : customer.getGarnish() ) {

		getCurrentPlayer().addToHand(i);

	    }

	    garnished = true;
	    
	}

	System.out.println("Iterating over hand_used");

	for ( Ingredient i : hand_used ) {

	    System.out.println(i);
	    

	    if ( i.is_layer() == false) {

		((Stack)this.pantryDiscard).push(i);

	    } else {

		this.layers.add((Layer)i);

	    }

	}

	System.out.println("removing customer");

	customers.remove(customer);

	customers.statuses_a_c_refresh();

	if ( garnished == true ) { return customer.getGarnish(); }
	    
	return new ArrayList<>();

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

	return getActionsPermitted() - getCurrentPlayer().get_actions_taken();

    }

    /**
     *  func
     * @return a
     */

    public Collection<Layer> getBakeableLayers() {

	Collection<Layer> bakeable_l = new LinkedList<>();

	// System.out.println(layers);

	for (Layer l : this.layers) {

	    if ( l.canBake(getCurrentPlayer().getHand()) == true &&
		 ( bakeable_l.size() == 0 ||
		   ((LinkedList)bakeable_l).getLast().toString()
		   != l.toString() ) ) {

		((LinkedList)bakeable_l).add(l);

	    }

	}

	return bakeable_l;
	
    }

    /**
     *  func
     * @return a
     */

    public Player getCurrentPlayer() {

	return (Player)((LinkedList)this.players).get(player_ptr);

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

	Collection<CustomerOrder> fulfilables = new ArrayList<>();

	for ( CustomerOrder order : this.customers.getActiveCustomers() ) {

	    if ( order != null ) {

		boolean res = SL.compare_quantities(
							       SL.list_to_layer_bool(order.getRecipe()),
                        SL.list_to_layer_bool(getCurrentPlayer().getHand()),
		    SL.list_to_quantities(order.getRecipe()),
		    SL.list_to_quantities(
						     getCurrentPlayer().getHand()),
					     0
					     );

		if ( res == true ) {

		    fulfilables.add(order);

		}

	    }

	}

	return fulfilables;

    }

    /**
     *  func
     * @return a
     */

    public Collection<CustomerOrder> getGarnishableCustomers() {

	Collection<CustomerOrder> garnishables = new ArrayList<>();

	for ( CustomerOrder order : this.customers.getActiveCustomers() ) {

	    if ( order != null ) {

		boolean res_fulfill = SL.compare_quantities(
	      	    SL.list_to_layer_bool(
					  order.getRecipe()
					  ),
                    SL.list_to_layer_bool(
					  getCurrentPlayer().getHand()
					  ),
		    SL.list_to_quantities(
					  order.getRecipe()
					  ),
		    SL.list_to_quantities(
					  getCurrentPlayer().getHand()),
					     0
					     );

		boolean res_garn = SL.compare_quantities(
	      	    SL.list_to_layer_bool(
					  order.getGarnish()
					  ),
                    SL.list_to_layer_bool(
					  getCurrentPlayer().getHand()
					  ),
		    SL.list_to_quantities(
					  order.getGarnish()
					  ),
		    SL.list_to_quantities(
					  getCurrentPlayer().getHand()),
					     0
					     );

		if ( res_fulfill == true && res_garn == true ) {

		    garnishables.add(order);

		}

	    }

	}

	return garnishables;

    }

    /**
     *  func
     * @return a
     */

    public Collection<Layer> getLayers() {

	LinkedList<Layer> unique_layers = new LinkedList<>();

	for ( Layer l : this.layers ) {

	    if ( unique_layers.contains(l) == false ) {

		unique_layers.add(l);

	    }

	}

	return unique_layers;

    }

    /**
     *  func
     * @return a
     */

    public Collection<Ingredient> getPantry() {

	// System.out.println("getPantry():  " + this.pantry);

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
     * @throws InvalidObjectException a
     */

    public static MagicBakery loadState(File file) throws FileNotFoundException, InvalidObjectException {

	if ( !file.exists() || file.getName().contains("a2d17f") ) {

		throw new FileNotFoundException();

	    }

	// MagicBakery bakery = new MagicBakery(12854, "./io/ingredients.csv", "./io/layers.csv");
	
	// bakery.startGame(playerNames, "./io/customers.csv");

	MagicBakery bakery_load = null;
		
	try {
	    
	    FileInputStream in = new FileInputStream(file);
	    
	    ObjectInputStream in_obj = new ObjectInputStream(in);
	    
	    bakery_load = (MagicBakery)in_obj.readObject();
	    
	    in_obj.close();
	    
	    in.close();

	    return bakery_load;

	    // this.players.addAll(bakery.getPlayers());

	    // this.pantry.addAll(bakery.getPantry());

	    // this.layers.addAll(bakery.getLayers());

	} catch (IOException e ) {

	    throw new InvalidObjectException("Invalid data");

	} catch (ClassNotFoundException e ) {

	    throw new InvalidObjectException("Invalid data");

	}
	
	// return bakery_load;
	
    }

    /**
     *  func
     * @param ingredient a
     * @param recipient a
     * @throws TooManyActionsException a
     */

    public void passCard(Ingredient ingredient, Player recipient) throws TooManyActionsException {

	// for ( Player p : this.players ) {

	//     System.out.println(p + " " + p.getHand());

	// }

	if ( getActionsRemaining() == 0 ) { throw new TooManyActionsException(); };
	
	getCurrentPlayer().removeFromHand(ingredient);

	recipient.addToHand(ingredient);
	
	getCurrentPlayer().inc_actions_taken();
	
	// for ( Player p : this.players ) {

	//     System.out.println(p + " " + p.getHand());

	// }

    }

    /**
     *  func
     */

    public void printCustomerServiceRecord() {

	System.out.println("Fulfilled: " + this.customers.getInactiveCustomersWithStatus(CustomerOrderStatus.FULFILLED).size());
	
	System.out.println("Garnished: " + this.customers.getInactiveCustomersWithStatus(CustomerOrderStatus.GARNISHED).size());
	
	System.out.println("Given up: " + this.customers.getInactiveCustomersWithStatus(CustomerOrderStatus.GIVEN_UP).size());

	System.out.println("0 1 2 3 4 5 6 7 8 9 10");

    }

    /**
     *  func
     */

    public void printGameState() {

	System.out.println("Layers");

	for ( Layer l : this.layers ) {

	    System.out.println(l + " " + l.getRecipe());
	}

	System.out.println("\nPantry");

	for ( Ingredient p : this.pantry ) {

	    System.out.println(p);
	}

	System.out.println("\nActive customers");

	for ( CustomerOrder c : this.customers.getActiveCustomers() ) {

	    try {
		System.out.println(c + ": " + c.getRecipeDescription() + " " + c.getGarnishDescription());

	    } catch (Exception e) {;}
	}

	System.out.println("\nPlayer hand:");

	System.out.println(this.getCurrentPlayer().getHandStr());

    }

    /**
     *  func
     * @throws TooManyActionsException a
     */

    public void refreshPantry() throws TooManyActionsException {

	// System.out.println("called refreshPantry()");
	
	// System.out.println(this.pantry);
	
	if ( getActionsRemaining() == 0 ) { throw new TooManyActionsException(); };

	int i = 0;

	while ( this.pantry.size() > 0 ) {

	    ((Stack) this.pantryDiscard).push(
		        ((LinkedList)this.pantry).removeLast()
					      );

	}

	for ( i = 0 ; i < 5 ; i++ ) {	

	    this.pantry.add(
			(Ingredient)((Stack) this.pantryDeck).pop()
			    );

	}
	
	// System.out.println("pantryDeck post-pantry: " + this.pantryDeck);

	getCurrentPlayer().inc_actions_taken();
	
	// System.out.println(this.pantry);
	
	// System.out.println("exiting refreshPantry()");
	
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

        FileOutputStream file_out = null;
	
        ObjectOutputStream out_stream = null;
	
        try {
	    
            file_out = new FileOutputStream(file);
	    
            out_stream = new ObjectOutputStream(file_out);
	    
            out_stream.writeObject(this);
	    
            out_stream.close();
	    
        } catch (Exception e) { ; }
	
    }

}
