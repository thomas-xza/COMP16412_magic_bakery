package bakery;

import java.util.ArrayList;
import java.util.List;

public class Layer extends Ingredient {

    private ArrayList<Ingredient> recipe;

    private static long serialVersionUID;

    public Layer(String name, ArrayList<Ingredient> recipe) {

	super(name);
	// this.name = name;

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

}
