package bakery;

import java.util.*;
import java.io.*;

import bakery.*;

/**
 *  class
 *   @author thomas.
 * @version 1.5
 * @since 1.0
*/

public class Layer extends Ingredient
    implements Serializable, Comparable<Layer> {

    private List<Ingredient> recipe;

    private static long serialVersionUID;

    /**
     * func
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
     * a
     * @param ingredients
     * @return a
     */
    
    public boolean canBake(List<Ingredient> ingredients) {

	return true;

    }

    /**
     * a
     * @return a
     */
    
    public int hashCode() {

	return 1;

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

    @Override
    public int compareTo(Layer a) {
	
        return 1;
	
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
