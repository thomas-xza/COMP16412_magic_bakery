package bakery;

import java.util.*;
import java.io.*;

import bakery.*;
import util.*;
import bakery.CustomerOrder.CustomerOrderStatus;

/**
 *   Customers
 *   @author thomas.
 * @version 1.5
 * @since 1.0
*/

public class Customers
    implements Serializable {

    private Collection<CustomerOrder> activeCustomers;
    private Collection<CustomerOrder> customerDeck;
    private List<CustomerOrder> inactiveCustomers;
    private Random random;

    private static final long serialVersionUID = 0;
    

    /**
     * hello
     * @param deckFile a 
     * @param random b
     * @param layers c
     * @param numPlayers d
     * @throws IOException a
     */

    public Customers(String deckFile, Random random, Collection<Layer> layers, int numPlayers) throws IOException {

	this.activeCustomers = new LinkedList<>();
	
	this.customerDeck = new ArrayList<>();
	
	this.inactiveCustomers = new LinkedList<>();

	File file = new File(deckFile);

	if ( !file.exists() ) {

	    throw new FileNotFoundException();
	    
	} else {
	    
	    initialiseCustomerDeck(deckFile, layers, numPlayers);
		
	}

	this.random = random;

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

	return activeCustomers;

    }

    /**
     * getter
     * @return list
     */
    
    public Collection<CustomerOrder> getCustomerDeck() {

	return customerDeck;

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

	List<CustomerOrder> customers_list = null;
	
	try {
	    
	    customers_list = CardUtils.readCustomerFile(deckFile, layers);

	} catch (IOException e) { ; }

	Map<Integer, Integer> map = new HashMap<>();

	Integer level_1 = 0;
	Integer level_2 = 0;
	Integer level_3 = 0;

	if ( numPlayers == 2 ) {

	    map.put(1, 4);
	    map.put(2, 2);
	    map.put(3, 1);

	} else if ( numPlayers == 3 || numPlayers == 4 ) {

	    map.put(1, 1);
	    map.put(2, 2);
	    map.put(3, 4);
	    
	} else if ( numPlayers == 5 ) {

	    map.put(1, 0);
	    map.put(2, 1);
	    map.put(3, 6);
	    
	}
	
	for ( CustomerOrder customer_order : customers_list ) {

	    Integer level = customer_order.getLevel();

	    if ( map.get(level) > 0 ) {

		map.put(level, map.get(level) - 1);

		((Stack) this.customerDeck).push(customer_order);

	    }

	}

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
