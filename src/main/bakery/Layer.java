package bakery;

import java.util.*;

import bakery.*;

public class Layer extends Ingredient {

    private List<Ingredient> recipe;

    private static long serialVersionUID;

    /**
     *  Initiate player
     * @param seed
     * @param ingredientDeckFile
     */

    public Layer(String name, List<Ingredient> recipe) {

	super(name);

	this.recipe = recipe;

    }
 
    /**
     *  Initiate player
     * @return a
     */
    public List<Ingredient> getRecipe() {

	return recipe;
	
    }

    /**
     *  Initiate player
     * @return a
     */
    public String getRecipeDescription() {

	String recipe_desc = "";

	for (Ingredient i : recipe) {

	    recipe_desc = recipe_desc.concat(i.toString());

	}

	return recipe_desc;

    }

    /**
     *  Initiate player
     * @param ingredients
     * @return a
     */
    public boolean canBake(List<Ingredient> ingredients) {

	return true;

    }

    /**
     *  Initiate player
     * @return a
     */
    public static Layer fast_layer() {

	List<Ingredient> a = Ingredient.fast_ingrd_list();

	Layer b = new Layer("b", a);

	return b;
	
    }

    /**
     *  Initiate player
     * @return a
     */
    public static List<Layer> fast_layer_list() {

	Layer c = fast_layer();

	List<Layer> d = new ArrayList<>();
	
        d.add(c);

        return d;
	
    }
    
    /**
     *  Initiate player
     * @return a
     */
    public static Collection<Layer> fast_layer_collection() {

	List<Layer> a = Layer.fast_layer_list();

	Collection<Layer> b = a;

	return b;
	
    }

}
