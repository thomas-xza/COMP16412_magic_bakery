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
    implements Serializable, Comparable<CustomerOrder> {
    
    private String name;

    private int level;

    private List<Ingredient> garnish;

    private List<Ingredient> recipe;

    private CustomerOrderStatus status;
   
    private static final long serialVersionUID = 7;

    /**
     *  func
     */
    
    public enum CustomerOrderStatus {

	WAITING,FULFILLED,GARNISHED,IMPATIENT,GIVEN_UP;

    }
    
    /**
     *  Initiate order
     * @param name a
     * @param recipe b
     * @param garnish c
     * @param level d
     * @throws WrongIngredientsException a
     */
    
    public CustomerOrder(String name, List<Ingredient> recipe, List<Ingredient> garnish, int level) throws WrongIngredientsException {

        if ( recipe == null || recipe.size() == 0 ) {

	    throw new WrongIngredientsException("test");

	}

	// System.out.println(name);

	// System.out.println(recipe);

	this.name = name;

	this.level = level;

	this.garnish = garnish;

	this.recipe = recipe;

	this.status = CustomerOrderStatus.WAITING;

    }

    /**
     * WARNING_DUMB_INVERTED_PARAMETER_ORDER
     * @param target_map a
     * @param source_map a
     * @param verbose v
     * @return bool
     */

    public static boolean compare_quantities(Map<String, Integer> target_map, Map<String, Integer> source_map, int verbose) {

	System.out.println("source_map:  " + source_map);

	System.out.println("target_map:  " + target_map);

	int missing = 0;

	Integer i_source = 0;
	Integer i_target = 0;

	boolean res = false;
	
	// System.out.println(target_map.keySet());

	for (String key : target_map.keySet()) {

	    i_source = source_map.get(key);
	    i_target = target_map.get(key);

	    if (i_source == null) { i_source = 0; }
	    if (i_target == null) { i_target = 0; }

	    if ( verbose == 1 ) {

		// System.out.println("have: " + key + ": " + i_source);

		// System.out.println("want: " + key + ": " + i_target);

	    }

	    if (i_target > i_source) {

		missing = missing + (i_target - i_source);

	    }

	}

	Integer ducks = source_map.get("helpful duck 𓅭 " );

	if ( ducks == null ) { ducks = 0; }

	if ( missing == 0 || ( ducks >= missing ) ) {

	    res = true;
	    
	}

	System.out.println(res);
	
	return res;

    }

    /**
     * WARNING_PARAMETER_ORDER_INVERTED
     * @param target a
     * @param in ingredients
     * @return a
     */

    public static List<List<Ingredient>> used_quantities_v2(List<Ingredient> target, List<Ingredient> in) {

	List<List<Ingredient>> output = new ArrayList<>();

	List<Ingredient> ing = in;
	List<Ingredient> ing_copy = new ArrayList<>();
	ing_copy.addAll(ing);
	List<Ingredient> used = new ArrayList<>();

	int missing = 0;
	int ducks_avail = 0;
	int ducks_used = 0;

	// System.out.println("in: " + in);

	//  Iterate through the target ingredients,
	//    sub-iterate through the hand, look for matches.
	//  Add them to an array of used ingredients.
	
	for (Ingredient t : target ) {

	    for (Ingredient i : ing ) {

		// System.out.println(t.toString() + " " + i.toString());

		if ( t.toString().trim().equals(i.toString().trim()) ) {

		    // System.out.println("match!");

		    used.add(i);
		    ing.remove(i);
		    break;

		}

	    }

	}

	//  Calculate how many ingredients missing, ducks needed.
	
	missing = target.size() - used.size();

	for ( Ingredient i : ing ) {

	    if ( i.toString() == "helpful duck 𓅭 " ) {

		ducks_avail += 1;

	    }

	}

	// System.out.println("missing: " + missing);
	// System.out.println("ducks_avail: " + ducks_avail);

	//  Find ducks if needed, extract them to used ingredients.
	
	if ( missing > 0 && ducks_avail >= missing ) {

	    while ( target.size() != used.size() ) {

		for ( Ingredient i : ing ) {

		    if ( i.toString().equals("helpful duck 𓅭 ") ) {

			    used.add(i);
			    ing.remove(i);
			    break;

			}

		}

	    }

	}

	// System.out.println("ing: " + ing);

	// System.out.println("target:  " + target);

	// System.out.println("used:  " + used);

	//  If managed to make target, return ingredients used.
	//  Else return original stuff.

	if ( target.size() == used.size() ) {
	    
	    output.add(used);
	    output.add(ing);

	} else {

	    output.add(new ArrayList<>());
	    output.add(ing_copy);

	}

	return output;

    }

    @Override
    /**
     *  func
     * @param o a
     * @return a
     */
    public boolean equals(Object o) {

	if (o == null ||
	    !this.name.equals(o.toString()) ) {
	    //	    this.getClass() != obj.getClass()) ||

	    return false;

	}

	return true;

    }

    @Override
    /**
     *  func
     * @return a
     */
    public int hashCode() {

	return this.name.hashCode();

    }

    @Override
    /**
     *  func
     * @param o a
     * @return a
     */
    public int compareTo(CustomerOrder o) {

	if ( o.toString() == null ) {

	    return -1;

	} else if ( this.name.equals(o.toString()) ) {

	    return 0;

	}

	return this.name.compareTo(o.toString());

    }


    /**
     * a
     * @param ingredients a
     * @return a
     */
    
    public boolean canFulfill(List<Ingredient> ingredients) {

	// System.out.println(this.name);

	boolean res = compare_quantities(
					 list_to_quantities(
					  to_raw_ingredients(recipe)
							    ),
					 list_to_quantities(
					  to_raw_ingredients(ingredients)
							    ),
					 0
					 );

	return res;
	
    }

    /**
     * a
     * @param elements a
     * @return a
     */
    
    public static List<Ingredient> to_raw_ingredients(List<Ingredient> elements) {

	List<Ingredient> raw_ingredients = new ArrayList<>();

	for ( Ingredient e : elements ) {

	    if ( e instanceof Layer ) {

		raw_ingredients.addAll(((Layer)e).getRecipe());

	    } else {

		raw_ingredients.add(e);

	    }

	}

	return raw_ingredients;
	
    }

    /**
     * a
     * @param ingredients a
     * @return a
     */
    
    public boolean canGarnish(List<Ingredient> ingredients) {

	boolean res = compare_quantities(
					 list_to_quantities(
					   to_raw_ingredients(garnish)
							    ),
					 list_to_quantities(
					   to_raw_ingredients(ingredients)
							    ),
					 0
					 );

	return res;
	
    }

    /**
     * a
     * @param ingredients a
     * @param garnish a
     * @return a
     * @throws WrongIngredientsException a
     */
    
    public List<Ingredient> fulfill(List<Ingredient> ingredients, boolean garnish) throws WrongIngredientsException {

	List<String> ingredients_used = new ArrayList<>();

	List<Ingredient> ingredients_used_final = new ArrayList<>();

	List<Ingredient> recipe_and_garnish = new ArrayList<>();
	
	List<List<Ingredient>> used_remain = new ArrayList<>();
	
	List<List<Ingredient>> used_remain_2 = new ArrayList<>();
	
	List<Ingredient> used = new ArrayList<>();
	
	List<Ingredient> remain = new ArrayList<>();

	int i = 0;

	int quantity = 0;

	Collections.sort(ingredients);
	Collections.sort(this.recipe);
	Collections.sort(this.garnish);

	recipe_and_garnish.addAll(this.recipe);
	recipe_and_garnish.addAll(this.garnish);

	// System.out.println("inputs: " + ingredients);
	// System.out.println("inputs: " + list_to_quantities(ingredients));
	// System.out.println("recipe: " + this.recipe);

	// if ( garnish == true ) {

	    // System.out.println("garnish: " + this.garnish); //

	// }

	if ( canFulfill(ingredients) == true ) {

	    used_remain = used_quantities_v2(
					     to_raw_ingredients(this.recipe),
					     to_raw_ingredients(ingredients)
					     );

	    used = used_remain.get(0);
	    remain = used_remain.get(1);

	    System.out.println("#1 used, remain:  " + used_remain);
	    // System.out.println("used:  " + used);
	    // System.out.println("remain:  " + remain);

	    this.status = CustomerOrderStatus.FULFILLED;
	    
	} else {

	    throw new WrongIngredientsException("fail");
	    
	}

	if ( garnish == true && canGarnish(remain) == true ) {

	    System.out.println("ATTEMPTING GARNISH");

	    used_remain_2 = used_quantities_v2(
					       to_raw_ingredients(this.garnish),
					       to_raw_ingredients(remain)
					     );

	    System.out.println("#2 used, remain:  " + used_remain_2);

	    used.addAll(used_remain_2.get(0));
	    remain = used_remain_2.get(1);

	    if ( used_remain_2.get(0).size() != 0 ) {

		this.status = CustomerOrderStatus.GARNISHED;

	    }
	    
	}

	Collections.sort(used);

	// System.out.println("final used: " + used);

	return used;

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

	return this.name;
	
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
     * @param fragment a
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
