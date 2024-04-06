package bakery;

import java.util.*;
import java.io.*;

import bakery.*;

/**
 * Stateless functions, to be called from multiple classes
 * @author thomas
 * @version 1.0
 */

public class SL {

    public static List<Layer> layers_loaded;
    
    /**
     * WARNING_DUMB_INVERTED_PARAMETER_ORDER
     * @param target_map a
     * @param source_map a
     * @param verbose v
     * @return bool
     */

    public static boolean compare_quantities(Map<String, Boolean> target_layers, Map<String, Boolean> source_layers, Map<String, Integer> target_map, Map<String, Integer> source_map, int verbose) {

	System.out.println("source_map:  " + source_map + source_layers);

	System.out.println("target_map:  " + target_map + target_layers);

	int missing_in = 0;
	int missing_total = 0;

	Integer i_source = 0;
	Integer i_target = 0;

	boolean res = false;
	
	// System.out.println(target_map.keySet());

	for (String key : target_map.keySet()) {

	    i_source = source_map.get(key);
	    i_target = target_map.get(key);

	    if (i_source == null) { i_source = 0; }
	    if (i_target == null) { i_target = 0; }

	    if ( verbose == 1 ) {

		// System.out.println("have: " + key + ": " + i_source);

		// System.out.println("want: " + key + ": " + i_target);

	    }

	    missing_in = i_target - i_source;

	    if ( target_layers.get(key) == true && missing_in > 0 ) {

		return false;
		
	    }

	    if (i_target > i_source) {

		missing_total += missing_in;

	    }

	}

	Integer ducks = source_map.get(Ingredient.duck_str());

	if ( ducks == null ) { ducks = 0; }

	if ( missing_total == 0 || ( ducks >= missing_total ) ) {

	    res = true;
	    
	}

	System.out.println(res);
	
	return res;

    }

    /**
     * WARNING_PARAMETER_ORDER_INVERTED
     * @param target a
     * @param in ingredients
     * @return a
     */

    public static List<List<Ingredient>> used_quantities_v2(List<Ingredient> in_target, List<Ingredient> in_src) {

	List<List<Ingredient>> output = new ArrayList<>();

	List<Ingredient> target = new ArrayList<>();
	target.addAll(in_target);

	List<Ingredient> in = new ArrayList<>();
	in.addAll(in_src);

	List<Ingredient> in_copy = new ArrayList<>();
	in_copy.addAll(in);
	
	List<Ingredient> used = new ArrayList<>();

	int missing = 0;
	int ducks_avail = 0;
	int ducks_used = 0;

	// System.out.println("in: " + in);

	//  Iterate through the recipe ingredients,
	//    sub-iterate through the hand ingredients, look for matches.
	//  Add them to an array of used ingredients.
	
	for (Ingredient t : target ) {

	    for (Ingredient i : in ) {

		// System.out.println(t.toString() + " " + i.toString());

		if ( t.toString().trim().equals(i.toString().trim()) ) {

		    // System.out.println("match!");

		    used.add(i);
		    in.remove(i);
		    break;

		}

	    }

	}

	//  Calculate how many ingredients missing, ducks needed.
	
	missing = target.size() - used.size();

	for ( Ingredient i : in ) {

	    if ( i.toString().equals(Ingredient.duck_str()) ) {

		ducks_avail += 1;

	    }

	}

	// System.out.println("missing: " + missing);
	// System.out.println("ducks_avail: " + ducks_avail);

	//  Find ducks if needed, extract them to used ingredients.
	
	if ( missing > 0 && ducks_avail >= missing ) {

	    while ( target.size() != used.size() ) {

		for ( Ingredient i : in ) {

		    if ( i.toString().equals(Ingredient.duck_str()) ) {

			    used.add(i);
			    in.remove(i);
			    break;

			}

		}

	    }

	}

	// System.out.println("ing: " + ing);

	// System.out.println("target:  " + target);

	// System.out.println("used:  " + used);

	//  If managed to make target, return ingredients used.
	//  Else return original stuff.

	if ( target.size() == used.size() ) {
	    
	    output.add(used);
	    output.add(in);

	} else {

	    output.add(new ArrayList<>());
	    output.add(in_copy);

	}

	return output;

    }

    /**
     * a
     * @param elements a
     * @return a
     */
    
    public static List<Ingredient> to_raw_ingredients(List<Ingredient> elements) {

	List<Ingredient> raw_ingredients = new ArrayList<>();

	for ( Ingredient e : elements ) {

	    if ( e instanceof Layer ) {

		raw_ingredients.addAll(((Layer)e).getRecipe());

	    } else {

		raw_ingredients.add(e);

	    }

	}

	return raw_ingredients;
	
    }

    /**
     * a
     * @param elements a
     * @return a
     */

    public static Map<String, Integer> list_to_quantities(List<Ingredient> elements) {

	Map<String, Integer> map = new HashMap<>();

	String e_str = "";

	for ( Ingredient e : elements ) {

	    e_str = e.toString();

	    map.put(e_str, map.getOrDefault(e_str, 0) + 1);

	}

	return map;

    }
    
    /**
     * a
     * @param elements a
     * @return a
     */

    public static Map<String, Boolean> list_to_layer_bool(List<Ingredient> elements) {

	Map<String, Boolean> map = new HashMap<>();

	for ( Ingredient e : elements ) {

	    if ( e instanceof Layer ) { map.put(e.toString(), true);

	    } else { map.put(e.toString(), false); }

	}

	return map;

    }
    
}
