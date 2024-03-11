package bakery;

public class Ingredient {

    private String name;

    public static Ingredient HELPFUL_DUCK;

    private static long serialVersionUID;

    public Ingredient(String name) {

	this.name = name;

    }

    public String toString() {

	return this.name;

    }

}
