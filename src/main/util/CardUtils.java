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
	String ingrd_name;
	Integer ingrd_quantity;
	List<String> csv_line;
	List<Ingredient> ingrd_list = new ArrayList<>();

	try(
	
	    FileReader ingrd_file = new FileReader(path);

	    BufferedReader ingrd_stream = new BufferedReader(ingrd_file);

	    )
	    {

		line = ingrd_stream.readLine();
		
		line = ingrd_stream.readLine();
		
		while(line != null) {
		    
		    //  Java's integer parser doesn't like whitespace.

		    csv_line = Arrays.asList(line.split("\\s*,\\s*"));

		    ingrd_name = csv_line.get(0);

		    ingrd_quantity = Integer.parseInt(csv_line.get(1));

		    while (ingrd_quantity > 0) {

			ingrd_list.add(new Ingredient(ingrd_name));

			ingrd_quantity = ingrd_quantity - 1;

		    }

		    line = ingrd_stream.readLine();

		}

	}

	catch(FileNotFoundException e) {

	    System.out.println("No file found.");

	}

	catch(IOException e) {

	    System.out.println("Read error.");

	}

	return ingrd_list;

    }

    // public static List<Layer> readLayerFile(String path) {

    // }

    // private static CustomerOrder stringToCustomerOrder(String str) {

    // }

    // private static List<Ingredient> stringToIngredient(String str) {

    // 	System.out.println(str);

    // }

    // private static List<Layer> stringToLayers(String str) {

    // }

    // public CardUtils() {

    // }

}
