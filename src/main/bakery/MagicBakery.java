package bakery;

import java.util.List;

import util.*;

/**
 *   class
 *   @author thomas.
 * @version 1.5
 * @since 1.0
*/

public class MagicBakery {

    /**
     *  Initiate player
     * @param seed 
     * @param ingredientDeckFile
     * @param layerDeckFile
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
    public Ingredient drawFromPantryDeck() {

	Ingredient a = new Ingredient("a");
	
	return a

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

	List<Ingredient> ingrd_l = new ArrayList<>();
        Ingredient a = new Ingredient("a");
        ingrd_l.add(a);

	return ingrd_l;

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

    }

    /**
     *  func
     * @return a
     */

    public Player getCurrentPlayer() {

	Player a = new Player("A");

	return a

    }

    /**
     *  func
     * @return a
     */

    public Customers getCustomers() {

    }

    /**
     *  func
     * @return a
     */

    public Collection<CustomerOrder> getFulfillableCustomers() {

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

    }

    /**
     *  func
     * @return a
     */

    public Collection<Ingredient> getPantry() {

        List<Ingredient> ingrd_l = new ArrayList<>();
        Ingredient a = new Ingredient("a");
        ingrd_l.add(a);

        return ingrd_l;
    }

    /**
     *  func
     * @return a
     */

    public Collection<Player> getPlayers() {

    }

    /**
     *  func
     * @param file f
     * @return a
     */

    public static MagicBakery loadState(File file) {

    }

    /**
     *  func
     * @param ingredient a
     * @param recipient a
     * @return a
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
     * @return a
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
