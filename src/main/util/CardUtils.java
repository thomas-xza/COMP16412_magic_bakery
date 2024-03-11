package util;

import bakery.*;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class CardUtils {

    public static List<Ingredient> readIngredientFile(String path) {

	String line;
	List<Ingredient> sublist = new ArrayList<>();
	List<Ingredient> all_ingrd_list = new ArrayList<>();

	try(
	
	    FileReader file = new FileReader(path);
	    BufferedReader stream = new BufferedReader(file);

	    )
	    {

		line = stream.readLine();
		
		line = stream.readLine();
		
		while(line != null) {
		    
		    sublist = stringToIngredient(line);

		    all_ingrd_list.addAll(sublist);

		    line = stream.readLine();

		}

	}

	catch(FileNotFoundException e) {
	    System.out.println("No file found.");
	}

	catch(IOException e) {
	    System.out.println("Read error.");
	}

	return all_ingrd_list;

    }

    private static List<Ingredient> stringToIngredient(String str) {

	String name;
	Integer quantity;
	List<String> csv_line;
	List<Ingredient> sublist = new ArrayList<>();
	
	csv_line = Arrays.asList(str.split("\\s*,\\s*"));

	name = csv_line.get(0);

	quantity = Integer.parseInt(csv_line.get(1));

	while (quantity > 0) {

	    sublist.add(new Ingredient(name));

	    quantity = quantity - 1;

	}

	return sublist;

    }

    public static List<Layer> readLayerFile(String path) {

	String line;
	List<Layer> sublist = new ArrayList<>();
	List<Layer> all_layer_list = new ArrayList<>();

	try(
	
	    FileReader file = new FileReader(path);
	    BufferedReader stream = new BufferedReader(file);

	    )
	    {

		line = stream.readLine();		
		line = stream.readLine();
		
		while(line != null) {
		    
		    sublist = stringToLayers(line);

		    all_layer_list.addAll(sublist);

		    line = stream.readLine();

		}

	}

	catch(FileNotFoundException e) {
	    System.out.println("No file found.");
	}

	catch(IOException e) {
	    System.out.println("Read error.");
	}

	return all_layer_list;

    }

    private static List<Layer> stringToLayers(String str) {

	List<String> csv_line;
	String layer_name;
	List<String> layer_ingrds_str = new ArrayList<>();
	List<Ingredient> layer_ingrds = new ArrayList<>();
	List<Layer> one_layer_list = new ArrayList<>();
	Integer i = 4;
	
	csv_line = Arrays.asList(str.split("\\s*,\\s*"));

	layer_name = csv_line.get(0);

	layer_ingrds_str = Arrays.asList(csv_line.get(1).split("\\s*;\\s*"));

	for ( String ingrd_str : layer_ingrds_str ) {

	    layer_ingrds.add(new Ingredient(ingrd_str));

	}
	
	while (i > 0) {

	    one_layer_list.add(new Layer(layer_name, layer_ingrds));

	    i = i - 1;

	}

	return one_layer_list;

    }

    public static List<CustomerOrder> readCustomerFile(String path, List<Layer> layers) {

	String line;
	CustomerOrder order;
	List<CustomerOrder> all_orders = new ArrayList<>();

	try(
	
	    FileReader file = new FileReader(path);
	    BufferedReader stream = new BufferedReader(file);

	    )
	    {

		line = stream.readLine();		
		line = stream.readLine();
		
		while(line != null) {
		    
		    order = stringToCustomerOrder(line, layers);

		    all_orders.add(order);

		    line = stream.readLine();

		}

	}

	catch(FileNotFoundException e) {
	    System.out.println("No file found.");
	}

	catch(IOException e) {
	    System.out.println("Read error.");
	}

	return all_orders;

    }

    private static List<Ingredient> data_to_ingredients(List<String> data, List<Layer> layers) {

	List<Ingredient> ingrds = new ArrayList<>();
	boolean part_is_layer = false;

	//  Next line is a workaround because Java dislikes empty objects.
	Layer target_layer = layers.get(0);


	for ( String recipe_part_str : data ) {

	    part_is_layer = false;

	    for ( Layer layer : layers ) {

		if ( recipe_part_str.equals(layer.toString()) ) {

			part_is_layer = true;

			target_layer = layer;

		    }

	    }

	    if ( part_is_layer == true ) {

		ingrds.addAll(target_layer.getRecipe());

	    } else {

		ingrds.add(new Ingredient(recipe_part_str));

	    }

	}

	return ingrds;

    }

    private static CustomerOrder stringToCustomerOrder(String str, List<Layer> layers) {

	List<String> csv_line;
	Integer level;
	String name;
	List<String> recipe_parts_str = new ArrayList<>();
	List<String> garnish_parts_str = new ArrayList<>();
	List<Ingredient> recipe = new ArrayList<>();
	List<Ingredient> garnish = new ArrayList<>();
	
	csv_line = Arrays.asList(str.split("\\s*,\\s*"));

	level = Integer.parseInt(csv_line.get(0));

	name = csv_line.get(1);

        recipe_parts_str = Arrays.asList(csv_line.get(2).split("\\s*;\\s*"));

	recipe = data_to_ingredients(recipe_parts_str, layers);

	if (csv_line.size() == 4) {

	    garnish_parts_str = Arrays.asList(csv_line.get(3).split("\\s*;\\s*"));
	    garnish = data_to_ingredients(garnish_parts_str, layers);

	}

	CustomerOrder order = new CustomerOrder(name, level, recipe, garnish);

	return order;

    }

}
