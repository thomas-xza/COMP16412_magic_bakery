package bakery;

import java.util.ArrayList;
import java.util.List;

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

}
