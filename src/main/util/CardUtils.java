package util;

import bakery.*;

import java.util.*;
import java.io.*;

/**
 * class
 * @author thomas.
 * @version 1.5
 * @since 1.0
*/

public class CardUtils {

    /**
     *   File parsing functions. This is not to be substantiated.
     */

    private CardUtils() {

    }

    /**
     *  Takes a path as a string, reads file, converts to
     *  associated data type. Assumes valid data.
     *  @param path File path
     *  @return File data converted to ingredient objects.
     *  @throws FileNotFoundException a
     *  @throws IOException a
     */

    public static List<Ingredient> readIngredientFile(String path) throws FileNotFoundException, IOException {

	// System.out.println("Reading ing file...");

        File f = new File(path);

        if( !f.exists() ) { throw new FileNotFoundException(); }
	
	String line;
	List<Ingredient> sublist = new ArrayList<>();
	List<Ingredient> all_ingrd_list = new ArrayList<>();

	try {

	    FileReader file = new FileReader(path);
	    BufferedReader stream = new BufferedReader(file);
	    line = stream.readLine();
		
	    line = stream.readLine();
		
	    while(line != null) {
		    
		sublist = stringToIngredients(line);

		all_ingrd_list.addAll(sublist);

		line = stream.readLine();

	    }
	    
	} catch(FileNotFoundException e) {
	    ;

	} catch(IOException e) {
	    ;
	}

	return all_ingrd_list;

    }

    /**

       Takes a string in format exampled in ingredients.csv,
       converts to instances of Ingredient object.

       @param str Ingredient file CSV line.
       @return Ingredient objects.

    */
    
    private static List<Ingredient> stringToIngredients(String str) {

	String name;
	String quantity_str;
	Integer quantity;
	List<String> csv_line;
	List<Ingredient> sublist = new ArrayList<>();
	
	csv_line = Arrays.asList(str.split("\\s*,\\s*"));

	name = csv_line.get(0).replaceAll("^ *", "").replaceAll(" *$", "");

	quantity = Integer.parseInt(csv_line.get(1).replaceAll("^ *", "").replaceAll(" *$", ""));

	while (quantity > 0) {

	    sublist.add(new Ingredient(name));

	    quantity = quantity - 1;

	}

	return sublist;

    }

    /**

       Takes a path as a string, reads file, converts to
       associated data type. Assumes valid data.

       @param path File path.
       @return List of layer objects within file.
       @throws FileNotFoundException a

    */
    public static List<Layer> readLayerFile(String path) throws FileNotFoundException {

	// System.out.println("Reading layer file...");

        File f = new File(path);

        if( !f.exists() ) { throw new FileNotFoundException(); }

	String line;
	List<Layer> sublist = new ArrayList<>();
	List<Layer> all_layer_list = new ArrayList<>();

	try {
	    
	    FileReader file = new FileReader(path);
	    BufferedReader stream = new BufferedReader(file);

	    line = stream.readLine();		
	    line = stream.readLine();
		
	    while(line != null) {
		    
		sublist = stringToLayers(line);

		all_layer_list.addAll(sublist);

		line = stream.readLine();

	    }

	}

	catch(FileNotFoundException e) {
	    ;
       	}

	catch(IOException e) {
	    ;
	}

	return all_layer_list;

    }


    /**

       Takes a string in format exampled in layers.csv,
       converts to instances of Layer object.

       @param str : Line from CSV of layer
       @return : Layer objects

    */

    private static List<Layer> stringToLayers(String str) {
	List<String> csv_line;
	String layer_name;
	List<String> layer_ingrds_str = new ArrayList<>();
	List<Ingredient> layer_ingrds = new ArrayList<>();
	List<Layer> one_layer_list = new ArrayList<>();
	int i = 4;
	
	csv_line = Arrays.asList(str.split("\\s*,\\s*"));

	layer_name = csv_line.get(0).replaceAll("^ *", "").replaceAll(" *$", "");

	layer_name = layer_name;

	layer_ingrds_str = Arrays.asList(csv_line.get(1).split("\\s*;\\s*"));

	for ( String ingrd_str : layer_ingrds_str ) {

	    ingrd_str = ingrd_str.replaceAll("^ *", "").replaceAll(" *$", "");

	    layer_ingrds.add(new Ingredient(ingrd_str));

	}

	for ( i = 0 ; i < 4 ; i++ ) {
	
	    one_layer_list.add(new Layer(layer_name, layer_ingrds));

	}

	return one_layer_list;

    }

    /**

       Takes a string in format exampled in layers.csv,
       converts to instances of Ingredient object.

       @param path File path
       @param layers List of layer objects.
       @return Order objects.
       @throws FileNotFoundException a
    */

    public static List<CustomerOrder> readCustomerFile(String path, Collection<Layer> layers) throws FileNotFoundException {

	// System.out.println("Reading customer file...");

        File f = new File(path);

        if( !f.exists() ) { throw new FileNotFoundException(); }
	
	String line;
	CustomerOrder order;
	List<CustomerOrder> all_orders = new ArrayList<>();

	try {
	    
	    FileReader file = new FileReader(path);
	    BufferedReader stream = new BufferedReader(file);

	    line = stream.readLine();		
	    line = stream.readLine();

	    // System.out.println("Converting string to order");
		
	    while(line != null) {

		// System.out.println(line);

		order = stringToCustomerOrder(line, layers);

		all_orders.add(order);

		line = stream.readLine();

	    }

	}

	catch(FileNotFoundException e) {
	    ;
	}

	catch(IOException e) {
	    ;
	}

	// System.out.println("returning all orders");

	return all_orders;

    }


    /**

       Takes a list of strings, converts eac to instance of
       Ingredient object after checking if they are found in list
       of layers.

       @param data : String list containing layers and ingredients.
       @param layers : Layer objects.
       @return : List of ingredients derived via string input.

    */
    private static List<Ingredient> data_to_ingredients(List<String> data, Collection<Layer> layers) {

	List<Ingredient> ingrds = new ArrayList<>();
	List<Layer> layers_list = List.copyOf(layers);
	boolean part_is_layer = false;

	//  Next line is a workaround because Java dislikes empty objects.
	Layer target_layer = layers_list.get(0);

	for ( String recipe_part_str : data ) {

	    part_is_layer = false;

	    for ( Layer layer : layers ) {

		if ( recipe_part_str.equals(layer.toString()) ) {

			part_is_layer = true;

			target_layer = layer;

		    }

	    }

	    if ( part_is_layer == true ) {

		// ingrds.addAll(target_layer.getRecipe());

		ingrds.add(new Layer(recipe_part_str, target_layer.getRecipe()));

	    } else {

		ingrds.add(new Ingredient(recipe_part_str));

	    }

	}

	return ingrds;

    }

    /**

       Takes a string in format exampled in customers.csv,
       converts to instance of CustomerOrder object.

       @param str : CSV line of customer order
       @param layers : Layer objects
       @return : Order object.

    */
    private static CustomerOrder stringToCustomerOrder(String str, Collection<Layer> layers) {

	List<String> csv_line;
	Integer level;
	String name;
	List<String> recipe_parts_str = new ArrayList<>();
	List<String> garnish_parts_str = new ArrayList<>();
	List<Ingredient> recipe = new ArrayList<>();
	List<Ingredient> garnish = new ArrayList<>();
	
	csv_line = Arrays.asList(str.split("\\s*,\\s*"));

	// System.out.println(csv_line);

	level = Integer.parseInt(csv_line.get(0));

	name = csv_line.get(1);

        recipe_parts_str = Arrays.asList(csv_line.get(2).split("\\s*;\\s*"));

	recipe = data_to_ingredients(recipe_parts_str, layers);

	if (csv_line.size() == 4) {

	    garnish_parts_str = Arrays.asList(csv_line.get(3).split("\\s*;\\s*"));
	    garnish = data_to_ingredients(garnish_parts_str, layers);

	}

	// System.out.println("Sending to new CustomerOrder:");
	// System.out.println(name);
	// System.out.println(name.getClass());
	// System.out.println(recipe);
	// System.out.println(recipe.getClass());
	// System.out.println(garnish);
	// System.out.println(garnish.getClass());
	// System.out.println(level);

	// System.out.println("r: " + recipe + recipe.size() + " g: " + garnish);

	CustomerOrder order = new CustomerOrder(name, recipe, garnish, level);

	// System.out.println("order generated");

	return order;

    }

}
