package bakery;

import java.util.*;
import java.io.*;

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
    private Collection<Ingredient> pantryDeck;
    private Collection<Ingredient> pantryDiscard;
    private Random random;

    private static final long serialVersionUID = 3;

    /**
     * Initiate Magic
     * @param seed a
     * @param ingredientDeckFile a
     * @param layerDeckFile a
     */
    
    public MagicBakery(long seed, String ingredientDeckFile, String layerDeckFile) {

	List<Ingredient> ingrd_list = CardUtils.readIngredientFile(ingredientDeckFile);

	List<Layer> layer_list = CardUtils.readLayerFile(layerDeckFile);

	List<CustomerOrder> order_list = CardUtils.readCustomerFile("customers.csv", layer_list);

	System.out.println(ingrd_list);

	System.out.println(ingrd_list.size());

	System.out.println(layer_list);

	System.out.println(layer_list.size());

	System.out.println(order_list);

	System.out.println(order_list.size());

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
     */
    
    public void bakeLayer(Layer layer) {

    }

    /**
     *  func
     * @return a
     */
    
    private Ingredient drawFromPantryDeck() {

	Ingredient a = new Ingredient("a");
	
	return a;

    }


    /**
     *  func
     * @param ingredientName a
     */
    public void drawFromPantry(String ingredientName) {

    }


    /**
     *  func
     * @param ingredient a
     */
    public void drawFromPantry(Ingredient ingredient) {

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
     */
    public List<Ingredient> fulfillOrder(CustomerOrder customer, boolean garnish) {

        List<Ingredient> a = Ingredient.fast_ingrd_list();

	return a;

    }


    /**
     *  func
     * @return a
     */
    public int getActionsPermitted() {

	return 1;

    }

    /**
     *  func
     * @return a
     */
    public int getActionsRemaining() {

	return 1;

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

	Player a = new Player("A");

	return a;

    }

    /**
     *  func
     * @return a
     */

    public Customers getCustomers() {

	Customers a = Customers.fast_customers();

	return a;

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

	List<Layer> a = Layer.fast_layer_list();

	return a;

    }

    /**
     *  func
     * @return a
     */

    public Collection<Ingredient> getPantry() {

        List<Ingredient> a = Ingredient.fast_ingrd_list();

	return a;

    }

    /**
     *  func
     * @return a
     */

    public Collection<Player> getPlayers() {

	List<Player> a = Player.fast_player_list();

	return a;

    }

    /**
     *  func
     * @param file f
     * @return a
     */

    public static MagicBakery loadState(File file) {

	MagicBakery a = new MagicBakery(1, "a", "a");
	return a;

    }

    /**
     *  func
     * @param ingredient a
     * @param recipient a
     */

    public void passCard(Ingredient ingredient, Player recipient) {

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
     */

    public void refreshPantry() {

    }

    /**
     *  func
     * @param file a
     */

    public void saveState(File file) {

    }

    /**
     *  func
     * @param playerNames a
     * @param customerDeckFile a
     */
    
    public void startGame(List<String> playerNames, String customerDeckFile) {

    }
    
}
