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
	List<CustomerOrder> sublist = new ArrayList<>();
	List<CustomerOrder> all_order_list = new ArrayList<>();

	try(
	
	    FileReader file = new FileReader(path);
	    BufferedReader stream = new BufferedReader(file);

	    )
	    {

		line = stream.readLine();		
		line = stream.readLine();
		
		while(line != null) {
		    
		    sublist = stringToCustomerOrder(line);

		    all_order_list.addAll(sublist);

		    line = stream.readLine();

		}

	}

	catch(FileNotFoundException e) {
	    System.out.println("No file found.");
	}

	catch(IOException e) {
	    System.out.println("Read error.");
	}

	return all_order_list;

    }

    private static CustomerOrder stringToCustomerOrder(String str, List<Layer> layers) {

	List<String> csv_line;
	Integer level;
	String order_name;
	List<String> recipe_parts_str = new ArrayList<>();
	List<Ingredient> layer_ingrds = new ArrayList<>();
	// List<Ingredient> recipe_ingrds = new ArrayList<>();
	// List<Layer> one_layer_list = new ArrayList<>();
	
	csv_line = Arrays.asList(str.split("\\s*,\\s*"));

	level = csv_line.get(0);

	order_name = csv_line.get(1);

        recipe_parts_str = Arrays.asList(csv_line.get(2).split("\\s*;\\s*"));

	for ( String recipe_part_str : recipe_parts_str ) {

	    bool part_is_layer = false;

	    for ( Layer layer : layers ) {

		if recipe_part_str.equals(layer.toString()) {

			part_is_layer = true;

			Layer target_layer = layer;

		    }

	    }

	    if ( part_is_layer == true ) {

		layer_ingrds.add(target_layer.getRecipe());

	    } else {

		layer_ingrds.add(new Ingredient(recipe_part_str));

	    }

	}
	
	one_layer_list.add(new Layer(layer_name, layer_ingrds));

	return one_layer_list;
	

    }

}
