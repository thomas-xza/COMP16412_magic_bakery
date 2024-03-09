package bakery;

import java.util.ArrayList;
import java.util.List;

public class Layer extends Ingredient {

    public ArrayList<Ingredient> recipe;

    public static long serialVersionUID;

    public Layer(String name, ArrayList<Ingredient> recipe) {

	super(name);
	// this.name = name;

	this.recipe = recipe;

    }

    public List<Ingredient> getRecipe() {

	return recipe;
	
    }

    public String getRecipeDescription() {

	return "Hello";

    }

    public boolean canBake(List<Ingredient> ingredients) {

	return true;

    }

}
