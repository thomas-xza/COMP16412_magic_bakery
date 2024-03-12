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
     * @param level b
     * @param recipe a
     * @param garnish d
     */
    
    public CustomerOrder(String name, List<Ingredient> recipe, List<Ingredient> garnish, int level) {

	this.name = name;

	this.level = level;

	this.garnish = garnish;

	this.recipe = recipe;

    }

    /**
     * a
     * @param ingredients a
     * @return a
     */
    
    public boolean canFulfill(List<Ingredient> ingredients) {

	return true;

    }

    /**
     * a
     * @param ingredients a
     * @return a
     */
    
    public boolean canGarnish(List<Ingredient> ingredients) {

	return true;

    }

    /**
     * a
     * @param ingredients a
     * @param garnish a
     * @return a
     */
    
    public List<Ingredient> fulfill(List<Ingredient> ingredients, boolean garnish) {

        List<Ingredient> a = Ingredient.fast_ingrd_list();

	return a;

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

	String garnish_desc = "";

        for (Ingredient g : garnish) {

            garnish_desc = garnish_desc.concat(g.toString());

        }

        return garnish_desc;

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

	String recipe_desc = "";

        for (Ingredient g : recipe) {

            recipe_desc = recipe_desc.concat(g.toString());

        }

        return recipe_desc;
	
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
