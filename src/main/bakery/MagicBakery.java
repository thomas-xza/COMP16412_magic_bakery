package bakery;

import java.util.List;

import util.*;

public class MagicBakery {

    public MagicBakery(long seed, String ingredientDeckFile, String layerDeckFile) {

	List<Ingredient> ingrd_list = CardUtils.readIngredientFile(ingredientDeckFile);

	List<Layer> layer_list = CardUtils.readLayerFile(layerDeckFile);

	System.out.println(ingrd_list);

	System.out.println(ingrd_list.size());

	System.out.println(layer_list);

	System.out.println(layer_list.size());

    }

}
