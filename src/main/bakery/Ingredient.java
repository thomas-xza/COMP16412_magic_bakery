package bakery;

import java.util.*;

public class Ingredient {

    private String name;

    public static Ingredient HELPFUL_DUCK;

    private static long serialVersionUID;

    /**
     *  func
     * @param name a
     */

    public Ingredient(String name) {

	this.name = name;

    }

    /**
     *  func
     * @return a
     */
    public String toString() {

	return name;

    }

    /**
     *  func
     * @return a
     */
    public static List<Ingredient> fast_ingrd_list() {
	
        Ingredient a = new Ingredient("a");
        List<Ingredient> b = new ArrayList<>();
	
        b.add(a);

        return b;

    }

}
