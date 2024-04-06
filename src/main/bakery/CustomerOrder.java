package bakery;

import java.util.*;
import java.io.*;

import bakery.*;
import util.*;

/**
 * CustomerOrder class
 * @author thomas
 * @version 1.0
 * @since 1.0
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
     *  Contains the various customer order statuses
     */
    
    public enum CustomerOrderStatus {

	WAITING,FULFILLED,GARNISHED,IMPATIENT,GIVEN_UP;

    }
    
    /**
     * Constructor
     * @param name of order
     * @param recipe ingredients
     * @param garnish ingredients
     * @param level of difficulty
     * @throws WrongIngredientsException if bad recipe input
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
     * Check if recipe is fulfillable with ingredients provided
     * @param ingredients to try to fulfill with
     * @return true or false
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
     * Check if recipe can be garnished
     * @param ingredients to try to garnish with
     * @return result
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
     * fulfill
     * @param ingredients to try and fulfill with
     * @param garnish as in whether to do so
     * @return ingredients used to fulfill/garnish
     * @throws WrongIngredientsException if cannot fulfill
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
	// System.out.println("inputs: " + SL.list_to_quantities(ingredients));
	// System.out.println("recipe: " + this.recipe);

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

	    // System.out.println("#1 used, remain:  " + used_remain);
	    // System.out.println("used:  " + used);
	    // System.out.println("remain:  " + remain);

	    this.status = CustomerOrderStatus.FULFILLED;
	    
	} else {

	    throw new WrongIngredientsException("fail");
	    
	}

	if ( garnish == true && canGarnish(remain) == true ) {

	    // System.out.println("ATTEMPTING GARNISH");

	    used_remain_2 = SL.used_quantities_v2(
					       this.garnish,
					       remain
					     );

	    // System.out.println("#2 used, remain:  " + used_remain_2);

	    if ( used_remain_2.get(0).size() != 0 ) {

		this.status = CustomerOrderStatus.GARNISHED;

	    }
	    
	    used.addAll(used_remain_2.get(0));
	    remain = used_remain_2.get(1);

	}

	Collections.sort(used);

	// System.out.println("final used: " + used);

	return used;

    }

    /**
     * get garnish for this recipe
     * @return ingredients of garnish
     */

    public List<Ingredient> getGarnish() {

	return garnish;

    }

    /**
     * get garnish desc
     * @return as string
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
     * get level of recipe
     * @return int
     */

    public int getLevel() {

	return level;

    }
    
    /**
     * get recipe of order
     * @return ingredients
     */

    public List<Ingredient> getRecipe() {

	return recipe;
	
    }

    /**
     * get recipe desc
     * @return as string
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
     * convert to string
     * @return as string
     */

    public String toString() {

	return this.name;
	
    }
    
    /**
     * get status of order
     * @return as enum
     */

    public CustomerOrderStatus getStatus() {

	return status;

    }
    
    /**
     * set status of order
     * @param status to set
     */

    public void setStatus(CustomerOrderStatus status) {

	this.status = status;

    }

    
    /**
     *  abandon order
     */

    public void abandon() {

	this.status = CustomerOrderStatus.GIVEN_UP;

    }

}
