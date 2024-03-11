package bakery;

import java.util.List;
import java.util.ArrayList;

public class Player  {

    private String name;

    private List<Ingredient> hand;

    private static long serialVersionUID;

    public Player(String name) {

	this.name = name;

    }

    public void addToHand(List<Ingredient> ingredients) {

    }

    public void addToHand(Ingredient ingredient) {

    }

    public boolean hasIngredient(Ingredient ingredient) {

	return true;

    }

    public void removeFromHand(Ingredient ingredient) {

    }

    public List<Ingredient> getHand() {

	List<Ingredient> hand = new ArrayList<>();

	return hand;

    }

    public String getHandStr() {

	return "A";

    }

    public String toString() {

	return name;

    }

}
