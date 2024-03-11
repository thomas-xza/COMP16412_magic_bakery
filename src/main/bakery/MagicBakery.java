package bakery;

import java.util.List;

import util.*;

public class MagicBakery {

    public MagicBakery(long seed, String ingredientDeckFile, String layerDeckFile) {

	List<Ingredient> ingrd_list = CardUtils.readIngredientFile(ingredientDeckFile);

	List<Layer> layer_list = CardUtils.readLayerFile(layerDeckFile);

	List<CustomerOrder> order_list = CardUtils.readCustomerFile("customers.csv", layer_list);

	System.out.println(ingrd_list);

	System.out.println(ingrd_list.size());

	System.out.println(layer_list);

	System.out.println(layer_list.size());

	System.out.println(order_list);

	System.out.println(order_list.size());

    }

    public enum ActionType {
    
	DRAW_INGREDIENT,PASS_INGREDIENT,BAKE_LAYER,FULFIL_ORDER,REFRESH_PANTRY;
	
    }
    
}
