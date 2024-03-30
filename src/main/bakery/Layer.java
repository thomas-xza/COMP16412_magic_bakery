package bakery;

import java.util.*;
import java.io.*;

import bakery.*;

/**
 *  class
 * @author thomas.
 * @version 1.5
 * @since 1.0
*/

public class Layer extends Ingredient
    implements Serializable, Comparable<Ingredient> {

    private List<Ingredient> recipe;

    private static final long serialVersionUID = 2;

    /**
     * func
     * @param name a
     * @param recipe a
     * @throws RuntimeException a
     */

    public Layer(String name, List<Ingredient> recipe) throws RuntimeException {
	
	super(name);

	if ( recipe == null || recipe.size() == 0 ) {

		throw new WrongIngredientsException("test");

	    }

	this.recipe = recipe;

    }
 
    /**
     *  func
     * @return a
     */
    public List<Ingredient> getRecipe() {

	return recipe;
	
    }

    /**
     *  func
     * @return a
     */
    public String getRecipeDescription() {

        List<String> r_list = new ArrayList<>();

        for (Ingredient r : recipe) {

            r_list.add(r.toString());

        }

	Collections.sort(r_list);

        String r_str = String.join(", ", r_list);

	return r_str;

    }

    /**
     * a
     * @param ingredients a
     * @return a
     */
    
    public boolean canBake(List<Ingredient> ingredients) {

        boolean res = CustomerOrder.compare_quantities(
			CustomerOrder.list_to_quantities(this.recipe),
		        CustomerOrder.list_to_quantities(ingredients),
			0
                                         );

	

        return res;

    }

    /**
     * a
     * @return a
     */
    
    public int hashCode() {

	return this.toString().hashCode() + Collections.sort(this.recipe).hashCode();

    }

    /**
     *  func
     * @return a
     */
    public static Layer fast_layer() {

	List<Ingredient> a = Ingredient.fast_ingrd_list();

	Layer b = new Layer("b", a);

	return b;
	
    }

    /**
     *  func
     * @return a
     */
    public static List<Layer> fast_layer_list() {

	Layer c = fast_layer();

	List<Layer> d = new ArrayList<>();
	
        d.add(c);

        return d;
	
    }
    
    /**
     *  func
     * @return a
     */
    public static Collection<Layer> fast_layer_collection() {

	List<Layer> a = Layer.fast_layer_list();

	Collection<Layer> b = a;

	return b;
	
    }

}
