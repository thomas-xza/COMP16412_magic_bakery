
import java.util.*;
import java.io.*;
import java.lang.*;

import bakery.*;
import util.*;

public class BakeryDriver {

    // public BakeryDriver() {

	
	
    // }

    public static void main(String[] args)  {

	try {

	    MagicBakery game = new MagicBakery(0, args[0], args[1]);

	} catch (IOException e) {

	    System.out.println("File read error");

	}

	// Ingredient myobj = new Ingredient("Water");
	
	// Ingredient myobj_2 = new Ingredient("Tea");

	// System.out.println(myobj.toString());
	// System.out.println(myobj_2.toString());

	// ArrayList<Ingredient> ing_list = new ArrayList<>();
	// // create three new accounts, referenced by each element in the array
	// ing_list.add(new Ingredient("A"));
	// ing_list.add(new Ingredient("B"));
	// ing_list.add(new Ingredient("C"));

	// Layer ob_3 = new Layer("Dolce", ing_list);

	// System.out.println(ob_3.getRecipeDescription());
	
    }

}
