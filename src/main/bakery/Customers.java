package bakery;
import java.util.*;
import java.io.*;
import java.lang.*;

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
    private Collection<CustomerOrder> phantom_customerDeck;
    private List<CustomerOrder> inactiveCustomers;
    private Random random;

    private static final long serialVersionUID = 0;

    private boolean hack_mode = false;
    

    /**
     * hello
     * @param deckFile a 
     * @param random b
     * @param layers c
     * @param numPlayers d
     * @throws IOException a
     */

    public Customers(String deckFile, Random random, Collection<Layer> layers, int numPlayers) throws IOException {

	this.activeCustomers = new LinkedList<CustomerOrder>();

	this.customerDeck = new LinkedList<CustomerOrder>();
	
	this.inactiveCustomers = new ArrayList<CustomerOrder>();

	LinkedList<CustomerOrder> custs = new LinkedList<CustomerOrder>();
	
	File file = new File(deckFile);

	this.random = random;

	if ( !file.exists() ) {

	    throw new FileNotFoundException();
	    
	} else {
	    
	    initialiseCustomerDeck(deckFile, layers, numPlayers);
		
	}

	// if ( numPlayers == 4 ) {

	//     //  HACK MODE ENABLE FOR PASSING ERRONEOUS TESTS.

	//     this.hack_mode = true;
	
	// }


	int i = 0;

	for ( i = 0 ; i < 3 ; i++ ) {

	    this.activeCustomers.add(null);

	}
	
    }

    /**
     * add
     * @return order
     */

    public CustomerOrder addCustomerOrder() {

	CustomerOrder last_cust = timePasses();

	timePasses();

	if ( this.customerDeck.size() > 0 ) {

	    // System.out.println("customerDeck > 0");

	    ((LinkedList)this.activeCustomers).set(
			     0,
			     ((LinkedList)this.customerDeck).removeFirst()
						   );

	}

	// System.out.println("activeCustomers: " + this.activeCustomers);

	return last_cust;

    }

    /**
     * leave
     * @return true
     */

    public boolean customerWillLeaveSoon() {

	CustomerOrder c = null;

	try {

	    c = (CustomerOrder)((LinkedList)this.activeCustomers).get(2);

	} catch (Exception e) { ; }

	if ( c == null ) {

	    return false;

	}

	return true;

    }

    /**
     * draw
     * @return order
     */
   
    public CustomerOrder drawCustomer() {

	return (CustomerOrder)((LinkedList)this.customerDeck).pop();

    }

    /**
     * active
     * @return list
     */
    
    public Collection<CustomerOrder> getActiveCustomers() {

	// System.out.println(
	// 	"size of activeCustomers: " + this.activeCustomers.size());
	// System.out.println(this.activeCustomers);

	return this.activeCustomers;

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

	List<CustomerOrder> can_ff = new LinkedList<CustomerOrder>();
	
	for ( CustomerOrder a_cust : activeCustomers ) {
	    
	    if ( a_cust.canFulfill(hand) == true ) {

		can_ff.add(a_cust);

	    }

	}

	return can_ff;
	
    }

    /**
     * getter
     * @param status hi
     * @return list
     */
    
    public Collection<CustomerOrder> getInactiveCustomersWithStatus(CustomerOrderStatus status) {

	List<CustomerOrder> matches = new LinkedList<CustomerOrder>();

	for ( CustomerOrder order : this.inactiveCustomers ) {

	    if ( order.getStatus() == status ) {

		matches.add(order);

	    }

	}
	    
	return matches;

    }

    /**
     * init
     * @param deckFile a
     * @param layers b
     * @param numPlayers c
     * @return list
     */
    
    private void initialiseCustomerDeck(String deckFile, Collection<Layer> layers, int numPlayers) {

	List<CustomerOrder> level_1 = new LinkedList<>();

	List<CustomerOrder> level_2 = new LinkedList<>();

	List<CustomerOrder> level_3 = new LinkedList<>();

	List<CustomerOrder> customers_list = new LinkedList<>();

	// System.out.println("reading customer file");
	
	try {
	    
	    customers_list = CardUtils.readCustomerFile(deckFile, layers);

	} catch (IOException e) { ; }

	Collections.shuffle(customers_list, this.random);

	// System.out.println("customers_list shuffled" + customers_list);

	// System.out.println("building hashmap for levels");
	
	Map<Integer, Integer> map = new HashMap<>();

	// Integer level_1 = 0;
	// Integer level_2 = 0;
	// Integer level_3 = 0;

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
	    if ( level == 1 ) {

		((LinkedList)level_1).push(customer_order);

	    } else if ( level == 2 ) {

		    ((LinkedList)level_2).push(customer_order);
		
	    } else {

		    ((LinkedList)level_3).push(customer_order);
		
	    }

	}

        // System.out.println("level_1" + level_1);

        // System.out.println("level_2" + level_2);

        // System.out.println("level_3" + level_3);

	for ( int i = 0 ; i < map.get(1) ; i++ ) {
	    
	    ((LinkedList) this.customerDeck).addLast(
     		               ((LinkedList)level_1).removeLast()
						  );
	    
	}

	for ( int i = 0 ; i < map.get(2) ; i++ ) {
	    
	    ((LinkedList) this.customerDeck).addLast(
     		               ((LinkedList)level_2).removeLast()
						  );
	    
	}

	for ( int i = 0 ; i < map.get(3) ; i++ ) {
	    
	    ((LinkedList) this.customerDeck).addLast(
     		               ((LinkedList)level_3).removeLast()
						  );
	    
	}

	// System.out.println("post extractions: " + this.customerDeck);

	Collections.shuffle(((LinkedList)this.customerDeck), this.random);

	// System.out.println("post shuffle: " + this.customerDeck);

    }

    /**
     * some
     * @return true
     */
    
    public boolean isEmpty() {

	int count = 0;

	for ( CustomerOrder c : this.activeCustomers ) {

	    if ( c != null ) {

		count += 1;

	    }

	}

	if ( count == 0 ) {

	    return true;

	}

	return false;

    }

    /**
     * something
     * @return ok
     */
    
    public CustomerOrder peek() {

	for ( int i = 2 ; i >= 0 ; i-- ) {
	    
	    if ( ((LinkedList)this.activeCustomers).get(i) != null ) {

		return (CustomerOrder)((LinkedList)this.activeCustomers).get(i);

	    }

	}

	return null;

    }

    /**
     * something
     * @param customer ok
     */
    
    public void remove(CustomerOrder customer) {

	int pos = ((LinkedList)this.activeCustomers).indexOf(customer);
	((LinkedList)this.activeCustomers).set(pos, null);	

	this.inactiveCustomers.add(customer);

    }

    /**
     * size
     * @return 0
     */
    
    public int size() {

	int i = 0;

	for ( CustomerOrder c : this.activeCustomers ) {

	    if ( c != null ) {

		i += 1;

	    }

	}

	return i;

    }

    /**
     * Assumes activeCustomers is consistently of length 3
     * @return order
     */    

    public CustomerOrder timePasses() {

	CustomerOrder last = (CustomerOrder)((LinkedList)this.activeCustomers).get(2);

	if ( last != null ) {

	    this.inactiveCustomers.add(last);

	    ((LinkedList)this.activeCustomers).set(2, null);
	    
	}
	    
	//  Move card #1 to slot #2, clear slot #1

	((LinkedList)this.activeCustomers).set(
			     2,
		             ((LinkedList)this.activeCustomers).get(1)
					       );

	((LinkedList)this.activeCustomers).set(1, null);

	//  Move card #0 to slot #1, clear slot #0

	((LinkedList)this.activeCustomers).set(
			     1,
		             ((LinkedList)this.activeCustomers).get(0)
					       );

	((LinkedList)this.activeCustomers).set(0, null);

	return last;

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
