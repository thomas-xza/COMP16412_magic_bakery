package bakery;

import java.util.*;

import bakery.*;

public class Layer extends Ingredient {

    private List<Ingredient> recipe;

    private static long serialVersionUID;

    public Layer(String name, List<Ingredient> recipe) {

	super(name);

	this.recipe = recipe;

    }

    public List<Ingredient> getRecipe() {

	return recipe;
	
    }

    public String getRecipeDescription() {

	String recipe_desc = "";

	for (Ingredient i : recipe) {

	    recipe_desc = recipe_desc.concat(i.toString());

	}

	return recipe_desc;

    }

    public boolean canBake(List<Ingredient> ingredients) {

	return true;

    }

    public static Layer fast_layer() {

	List<Ingredient> a = Ingredient.fast_ingrd_list();

	Layer b = new Layer("b", a);

	return b;
	
    }

    public static Collection<Layer> fast_layer_list() {

	Layer c = fast_layer();

	List<Layer> d = new ArrayList<>();
	
        d.add(c);

        return d;
	
    }

}
