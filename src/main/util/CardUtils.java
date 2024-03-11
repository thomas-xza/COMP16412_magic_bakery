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

    // public static List<CustomerOrder> readCustomerFile(String path, Collection<Layer> layers) {

    // }

    public static List<Ingredient> readIngredientFile(String path) {

	String line;
	List<Ingredient> one_ingrd_list = new ArrayList<>();
	List<Ingredient> all_ingrd_list = new ArrayList<>();

	try(
	
	    FileReader ingrd_file = new FileReader(path);

	    BufferedReader ingrd_stream = new BufferedReader(ingrd_file);

	    )
	    {

		line = ingrd_stream.readLine();
		
		line = ingrd_stream.readLine();
		
		while(line != null) {
		    
		    //  Java's integer parser doesn't like whitespace.

		    one_ingrd_list = stringToIngredient(line);

		    all_ingrd_list.addAll(one_ingrd_list);

		    line = ingrd_stream.readLine();

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

	String ingrd_name;
	Integer ingrd_quantity;
	List<String> csv_line;
	List<Ingredient> one_ingrd_list = new ArrayList<>();
	
	csv_line = Arrays.asList(str.split("\\s*,\\s*"));

	ingrd_name = csv_line.get(0);

	ingrd_quantity = Integer.parseInt(csv_line.get(1));

	while (ingrd_quantity > 0) {

	    one_ingrd_list.add(new Ingredient(ingrd_name));

	    ingrd_quantity = ingrd_quantity - 1;

	}

	return one_ingrd_list;

    }

    public static List<Layer> readLayerFile(String path) {

	String line;
	List<Layer> one_layer_list = new ArrayList<>();
	List<Layer> all_layer_list = new ArrayList<>();

	try(
	
	    FileReader layer_file = new FileReader(path);

	    BufferedReader layer_stream = new BufferedReader(layer_file);

	    )
	    {

		line = layer_stream.readLine();
		
		line = layer_stream.readLine();
		
		while(line != null) {
		    
		    //  Java's integer parser doesn't like whitespace.

		    one_layer_list = stringToLayers(line);

		    all_layer_list.addAll(one_layer_list);

		    line = layer_stream.readLine();

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

    // private static CustomerOrder stringToCustomerOrder(String str) {

    // }

    // public CardUtils() {

    // }

}
