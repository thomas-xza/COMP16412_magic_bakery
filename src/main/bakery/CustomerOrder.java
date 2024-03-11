package bakery;

import java.util.*;

public class CustomerOrder {

    private String name;

    private Integer level;

    private List<Ingredient> garnish;

    private List<Ingredient> recipe;

    private CustomerOrderStatus status;
   
    public enum CustomerOrderStatus {

	WAITING,FULFILLED,GARNISHED,IMPATIENT,GIVEN_UP;

    }
    
    public CustomerOrder(String name, Integer level, List<Ingredient> recipe, List<Ingredient> garnish) {

	this.name = name;

	this.level = level;

	this.garnish = garnish;

	this.recipe = recipe;

    }

    public List<Ingredient> getGarnish() {

	return garnish;

    }

    public String getGarnishDescription() {

	String garnish_desc = "";

        for (Ingredient g : garnish) {

            garnish_desc = garnish_desc.concat(g.toString());

        }

        return garnish_desc;

    }

    public Integer getLevel() {

	return level;

    }

    public List<Ingredient> getRecipe() {

	return recipe;
	
    }

    public String getRecipeDescription() {

	String recipe_desc = "";

        for (Ingredient g : recipe) {

            recipe_desc = recipe_desc.concat(g.toString());

        }

        return recipe_desc;
	
    }

    public String toString() {

	return name;
	
    }

    public CustomerOrderStatus getStatus() {

	return status;

    }

    public void setStatus(CustomerOrderStatus status) {

	this.status = status;

    }

    public void abandon() {

	this.status = CustomerOrderStatus.GIVEN_UP;

    }

    public static CustomerOrder fast_order() {
	
        List<Ingredient> ingrd_l = new ArrayList<>();
        Ingredient a = new Ingredient("a");
        ingrd_l.add(a);
	
	CustomerOrder blah = new CustomerOrder("test", 1, ingrd_l, ingrd_l);

	return blah;

    }

    public static List<CustomerOrder> fast_order_list() {

	List<CustomerOrder> blah = new ArrayList<>();
        blah.add(fast_order());

	return blah;

    }

}
