package bakery;

import java.util.ArrayList;

public class CustomerOrder {

    public String name;

    public Integer level;

    public ArrayList<Ingredient> garnish;

    public ArrayList<Ingredient> recipe;

    public CustomerOrder(String name, Integer level, ArrayList<Ingredient> recipe, ArrayList<Ingredient> garnish) {

	this.name = name;

	this.level = level;

	this.garnish = garnish;

	this.recipe = recipe;

    }

    public ArrayList<Ingredient> getGarnish() {

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

    public ArrayList<Ingredient> getRecipe() {

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

	return "Test";
	
    }

}
