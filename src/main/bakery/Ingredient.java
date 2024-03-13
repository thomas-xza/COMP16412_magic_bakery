package bakery;

import java.util.*;
import java.io.*;

/**
 *  class
 *   @author thomas.
 * @version 1.5
 * @since 1.0
*/

public class Ingredient
    implements Serializable, Comparable<Ingredient> {

    private String name;

    /**
     * something (not sure)
     */

    public final static Ingredient HELPFUL_DUCK = new Ingredient("Helpful duck");

    private static final long serialVersionUID = 1;

    /**
     *  func
     * @param name a
     */

    public Ingredient(String name) {

	this.name = name;

    }

    /**
     *  func
     * @param o a
     * @return a
     */
    public boolean equals(Object o) {

	return true;

    }

    /**
     *  func
     * @return a
     */
    public int hashCode() {

	return 1;

    }

    /**
     *  func
     * @param ingredient a
     * @return a
     */

    @Override
    public int compareTo(Ingredient ingredient) {

	if ( ingredient.name == null ) {

	    return -1;

	} else if ( this.name == ingredient.name ) {

	    return 0;

	}

	return this.name.compareTo(ingredient.name);

    }


    // @Override
    // /**
    //  *  func
    //  * @param o a
    //  * @return a
    //  */
    // public int compareTo(Object o) {

    // 	this.name.compareTo(o.name);

    // }

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

    /**
     *  func
     * @param ingredients a
     * @return a
     */
    public static List<String> ingrd_list_to_str_list(List<Ingredient> ingredients) {

	List<String> s_list = new ArrayList<>();

        for (Ingredient i : ingredients) {

            s_list.add(i.toString());

        }

	return s_list;

    }


}
