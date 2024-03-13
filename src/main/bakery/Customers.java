package bakery;

import java.util.*;
import java.io.*;

import bakery.*;
import bakery.CustomerOrder.CustomerOrderStatus;

/**
 *   Customers
 *   @author thomas.
 * @version 1.5
 * @since 1.0
*/

public class Customers
    implements Serializable {

    private Collection<CustomerOrder> activeCustomers = new ArrayList<>();
    private Collection<CustomerOrder> customerDeck = new ArrayList<>();
    private List<CustomerOrder> inactiveCustomers = new ArrayList<>();
    private Random random;

    private static final long serialVersionUID = 0;
    

    /**
     * hello
     * @param deckFile a 
     * @param random b
     * @param layers c
     * @param numPlayers d
     */

    public Customers(String deckFile, Random random, Collection<Layer> layers, int numPlayers) throws IOException {

	File file = new File(deckFile);

	if ( deckFile != "fast.csv" && !file.exists() ) {

	    throw new FileNotFoundException();
	}

    }

    /**
     * add
     * @return order
     */

    public CustomerOrder addCustomerOrder() {

	CustomerOrder a = CustomerOrder.fast_order();

	return a;

    }

    /**
     * leave
     * @return true
     */

    public boolean customerWillLeaveSoon() {

	return true;

    }

    /**
     * draw
     * @return order
     */
   
    public CustomerOrder drawCustomer() {

	CustomerOrder a = CustomerOrder.fast_order();

	return a;

    }

    /**
     * active
     * @return list
     */
    
    public Collection<CustomerOrder> getActiveCustomers() {

	List<CustomerOrder> a = CustomerOrder.fast_order_list();

	return a;

    }

    /**
     * getter
     * @return list
     */
    
    public Collection<CustomerOrder> getCustomerDeck() {

	List<CustomerOrder> a = CustomerOrder.fast_order_list();

	return a;

    }
 
    /**
     * getter
     * @param hand a
     * @return a
     */
    
    public Collection<CustomerOrder> getFulfilable(List<Ingredient> hand) {

	List<CustomerOrder> a = CustomerOrder.fast_order_list();

	return a;

    }

    /**
     * getter
     * @param status hi
     * @return list
     */
    
    public Collection<CustomerOrder> getInactiveCustomersWithStatus(CustomerOrderStatus status) {

	List<CustomerOrder> a = CustomerOrder.fast_order_list();

	return a;

    }

    /**
     * init
     * @param deckFile a
     * @param layers b
     * @param numPlayers c
     * @return list
     */
    
    private void initialiseCustomerDeck(String deckFile, Collection<Layer> layers, int numPlayers) {

    }

    /**
     * some
     * @return true
     */
    
    public boolean isEmpty() {

	return true;

    }

    /**
     * something
     * @return ok
     */
    
    public CustomerOrder peek() {

	CustomerOrder a = CustomerOrder.fast_order();

	return a;

    }

    /**
     * something
     * @param customer ok
     */
    
    public void remove(CustomerOrder customer) {

    }

    /**
     * size
     * @return 0
     */
    
    public int size() {

	return 0;

    }

    /**
     * something
     * @return order
     */    

    public CustomerOrder timePasses() {

	CustomerOrder a = CustomerOrder.fast_order();

	return a;

    }

    /**
     * something
     * @return fast
     */    

    public static Customers fast_customers() {
	
        List<Layer> a = Layer.fast_layer_list();

	Random b = new Random(1);

	Customers c = null;

	try {

	    c = new Customers("fast.csv", b, a, 2);

	} catch (IOException e) { ; }

	return c;
	
    }

}
