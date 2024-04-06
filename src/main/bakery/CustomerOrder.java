package bakery;

import java.util.*;
import java.io.*;

import bakery.*;
import util.*;

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

    public static List<Layer> layers_loaded;

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

	boolean res = SL.compare_quantities(
					 SL.list_to_layer_bool(recipe),
					 SL.list_to_layer_bool(ingredients),
					 SL.list_to_quantities(
					  recipe
							    ),
					 SL.list_to_quantities(
					  ingredients
							    ),
					 0
					 );

	return res;
	
    }

    /**
     * a
     * @param ingredients a
     * @return a
     */
    
    public boolean canGarnish(List<Ingredient> ingredients) {

	boolean res = SL.compare_quantities(
					    SL.list_to_layer_bool(
								  garnish),
					 SL.list_to_layer_bool(
							       ingredients),
					    SL.list_to_quantities(
								  garnish),
					 SL.list_to_quantities(
					   ingredients
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

	System.out.println("inputs: " + ingredients);
	System.out.println("inputs: " + SL.list_to_quantities(ingredients));
	System.out.println("recipe: " + this.recipe);

	// if ( garnish == true ) {

	    // System.out.println("garnish: " + this.garnish); //

	// }

	if ( canFulfill(ingredients) == true ) {

	    used_remain = SL.used_quantities_v2(
					     this.recipe,
					     ingredients
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

	    used_remain_2 = SL.used_quantities_v2(
					       this.garnish,
					       remain
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
