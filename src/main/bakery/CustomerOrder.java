package bakery;

import java.util.*;
import java.io.*;

import bakery.*;

/**
 * Create customer order
 * @author thomas
 * @version 1.0
 */

public class CustomerOrder
    implements Serializable {

    private String name;

    private int level;

    private List<Ingredient> garnish;

    private List<Ingredient> recipe;

    private CustomerOrderStatus status;
   
    private static final long serialVersionUID = 7;

    /**
     *  func     */
    
    public enum CustomerOrderStatus {

	WAITING,FULFILLED,GARNISHED,IMPATIENT,GIVEN_UP;

    }
    
    /**
     *  Initiate order
     * @param name a
     * @param recipe b
     * @param garnish c
     * @param level d
     * @throws RuntimeException a
     */
    
    public CustomerOrder(String name, List<Ingredient> recipe, List<Ingredient> garnish, int level) throws RuntimeException {

        if ( recipe == null || recipe.size() == 0 || garnish.size() == 0 ) {

	    throw new WrongIngredientsException();

	}

	this.name = name;

	this.level = level;

	this.garnish = garnish;

	this.recipe = recipe;

    }

    /**
     * compare
     * @param target_map a
     * @param source_map a
     * @return bool
     */

    public static boolean compare_quantities(Map<String, Integer> target_map, Map<String, Integer> source_map) {

	int missing = 0;

	Integer i_target = 0;
	Integer i_source = 0;
	
	for (String key : target_map.keySet()) {

	    i_target = target_map.get(key);
	    i_source = source_map.get(key);

	    if (i_source == null) { i_source = 0; }

	    if (i_target > i_source) {

		missing = missing + (i_target - i_source);

	    }

	}

	Integer ducks = target_map.get("Helpful duck 𓅭 " );

	if ( ducks == null ) { ducks = 0; }

	if ( missing == 0 || ( ducks != 0 && ducks >= missing ) ) {

	    return true;
	    
	}
	
	return false;

    }

    /**
     * compare
     * @param target_map a
     * @param source_map a
     * @return bool
     */

    public static Map<String, Integer> used_quantities(Map<String, Integer> target_map, Map<String, Integer> source_map) {

	Map<String, Integer> used = new HashMap<>();

	int missing = 0;

	Integer i_target = 0;
	Integer i_source = 0;
	
	for (String key : target_map.keySet()) {

	    i_target = target_map.get(key);
	    i_source = source_map.get(key);

	    if (i_source == null) { i_source = 0; }

	    if (i_target > i_source) {

		missing = missing + (i_target - i_source);

		used.put(key, i_source);
	    
	    } else {

		used.put(key, i_target);

	    }

	}

	Integer ducks = target_map.get("Helpful duck 𓅭 ");

	if ( ducks == null ) { ducks = 0; }

	if ( missing == 0 || ( ducks != 0 && ducks >= missing ) ) {

	    used.put("Helpful duck 𓅭 ", missing);

	}
	
	return used;

    }

    /**
     * a
     * @param ingredients a
     * @return a
     */
    
    public boolean canFulfill(List<Ingredient> ingredients) {

	boolean res = compare_quantities(
					 list_to_quantities(recipe),
					 list_to_quantities(ingredients)
					 );

	return res;
	
    }

    /**
     * a
     * @param ingredients a
     * @return a
     */
    
    public boolean canGarnish(List<Ingredient> ingredients) {

	boolean res = compare_quantities(
					 list_to_quantities(garnish),
					 list_to_quantities(ingredients)
					 );

	return res;
	
    }

    /**
     * a
     * @param ingredients a
     * @param garnish a
     * @return a
     */
    
    public List<Ingredient> fulfill(List<Ingredient> ingredients, boolean garnish) {

	List<String> ingredients_used = new ArrayList<>();

	List<Ingredient> ingredients_used_final = new ArrayList<>();

	int i = 0;

	int quantity = 0;

	status = CustomerOrderStatus.FULFILLED;

	if ( garnish == true ) {

	    ingredients.addAll(this.garnish);

	    status = CustomerOrderStatus.GARNISHED;

	}

	System.out.println(ingredients);

	// System.out.println(list_to_quantities(ingredients));
	
	System.out.println(list_to_quantities(recipe));

	Map<String, Integer> used = used_quantities(
					  list_to_quantities(recipe),
					  list_to_quantities(ingredients)
						    );

	System.out.println(used);

	for (String key : used.keySet()) {

	    quantity = used.get(key);

	    for ( i = quantity ; i > 0 ; i-- ) {

		ingredients_used.add(key);

	    }

	}

	Collections.sort(ingredients_used);

	System.out.println(ingredients_used);

	System.out.println();

	for (String ingredient : ingredients_used) {

	    ingredients_used_final.add(new Ingredient(ingredient));

	}

	return ingredients_used_final;

    }

    /**
     * a
     * @return a
     */
    
    public List<Ingredient> getGarnish() {

	return garnish;

    }

    /**
     * a
     * @return a
     */
    
    public String getGarnishDescription() {

	List<String> g_list = new ArrayList<>();

        for (Ingredient g : garnish) {

	    g_list.add(g.toString());

        }

	String g_str = String.join(", ", g_list);

        return g_str;

    }

    /**
     * a
     * @return a
     */
    
    public int getLevel() {

	return level;

    }

    /**
     * a
     * @return a
     */
    
    public List<Ingredient> getRecipe() {

	return recipe;
	
    }

    /**
     * a
     * @return a
     */
    
    public String getRecipeDescription() {

	List<String> r_list = new ArrayList<>();

        for (Ingredient r : recipe) {

	    r_list.add(r.toString());

        }

	String r_str = String.join(", ", r_list);

        return r_str;
	
    }

    /**
     * a
     * @return a
     */
    
    public String toString() {

	return name;
	
    }

    /**
     * a
     * @return a
     */
    
    public CustomerOrderStatus getStatus() {

	return status;

    }

    /**
     * a
     * @param status a
     */
    
    public void setStatus(CustomerOrderStatus status) {

	this.status = status;

    }

    /**
     * a
     */
    
    public void abandon() {

	this.status = CustomerOrderStatus.GIVEN_UP;

    }

    /**
     * a
     * @param elements a
     * @return a
     */

    public static Map<String, Integer> list_to_quantities(List<Ingredient> elements) {

	Map<String, Integer> map = new HashMap<>();

	String e_str = "";

	for ( Ingredient e : elements ) {

	    e_str = e.toString();

	    map.put(e_str, map.getOrDefault(e_str, 0) + 1);

	}

	return map;

    }
    
    /**
     * a
     * @return a
     */
    
    public static CustomerOrder fast_order() {
	
        List<Ingredient> a = Ingredient.fast_ingrd_list();

	CustomerOrder blah = new CustomerOrder("test", a, a, 1);

	return blah;

    }

    /**
     * a
     * @return a
     */
    
    public static List<CustomerOrder> fast_order_list() {

	List<CustomerOrder> blah = new ArrayList<>();
        blah.add(fast_order());

	return blah;

    }

}
