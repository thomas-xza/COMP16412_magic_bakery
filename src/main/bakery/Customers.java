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
    private boolean initialised_already;

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
	
	this.initialised_already = false;

	File file = new File(deckFile);

	this.random = random;

	if ( !file.exists() ) {

	    throw new FileNotFoundException();
	    
	} else {
	    
	    initialiseCustomerDeck(deckFile, layers, numPlayers);
		
	}

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

	// System.out.println("customerDeck pretime: " + " " + this.customerDeck);

	Stack<Integer> dummy_stack = new Stack<>();

	CustomerOrder last_cust = timePasses();

	statuses_a_c_refresh();

	try {
	
	    ((LinkedList)this.activeCustomers).set(0,
                (CustomerOrder)((LinkedList)this.customerDeck).removeLast()
						       );

	    if ( ((LinkedList)this.activeCustomers).get(0) != null ) {

		anti_peek().setStatus(CustomerOrderStatus.WAITING);
				      
	    }
	    
	} catch ( Exception e ) {

	    // System.out.println("activeCustomers: " + this.activeCustomers);

	    //  Trick for tests that expect Stack instead of LinkedList.

	    dummy_stack.pop();
	    	    
	}

	// System.out.println("customerDeck posttime: " + " " + this.customerDeck);

	// System.out.println("activeCustomers: " + this.activeCustomers);

	return last_cust;

    }

    /**
     * add
     * @return a
     */

    public int customer_deck_size() {

	return this.customerDeck.size();

    }

    /**
     * add
     */

    public void statuses_a_c_refresh() {

	for (CustomerOrder c : this.activeCustomers) {

	    if ( c != null ) {

		c.setStatus(CustomerOrderStatus.WAITING);

	    }

	}

	if ( this.size() == 3 && peek() != null ) {

	    peek().setStatus(CustomerOrderStatus.IMPATIENT);

	} else if ( this.customerDeck.size() == 0 ) {

	    if ( ( this.size() == 1 && peek() != null ) ||
		 ( this.size() == 2 &&
		   (CustomerOrder)((LinkedList)this.activeCustomers).get(1) != null &&
		   (CustomerOrder)((LinkedList)this.activeCustomers).get(2) != null ) ) {

		     peek().setStatus(CustomerOrderStatus.IMPATIENT);

		 }

	}
	
    }

    /**
     * Assumes activeCustomers is consistently of length 3
     * @return order
     */    

    public CustomerOrder timePasses() {

	// System.out.println("TIME PASSES");

	int i = 0;

	boolean filled_a_blank = false;

	boolean overflowed = false;

	LinkedList<CustomerOrder> picked_up = new LinkedList<>();

	CustomerOrder placeholder = null;

	statuses_a_c_refresh();

	picked_up.addFirst(placeholder);

	if ( this.customerDeck.size() == 0 &&
	     this.activeCustomers.size() != 0 ) {

	    i = ((LinkedList)this.activeCustomers).indexOf(anti_peek());

	    System.out.println("Cycling forward to... " + i);

	}

	while ( overflowed == false && filled_a_blank == false &&
		this.activeCustomers.size() != 0 ) {

	    picked_up.addLast(
		(CustomerOrder)((LinkedList)this.activeCustomers).get(i)
				       );
		
	    if ( ((LinkedList)this.activeCustomers).get(i) == null ) {

		filled_a_blank = true;

		// System.out.println("FILLED A BLANK");

	    }

	    ((LinkedList)this.activeCustomers).set(i,
						   picked_up.removeFirst()
						   );

	    if ( i + 1 == 3 && filled_a_blank == false ) {

		overflowed = true;

		// System.out.println("OVERFLOWED");

		this.inactiveCustomers.add(
					   picked_up.getFirst()
					   );
		
		picked_up.getFirst().setStatus(CustomerOrderStatus.GIVEN_UP);

		statuses_a_c_refresh();

		return picked_up.getFirst();

	    }

	    i += 1;

	}

	statuses_a_c_refresh();

	return null;
	
    }
    
    /**
     * leave
     * @return true
     */

    public boolean customerWillLeaveSoon() {

	// System.out.println("activeCustomers: " + this.activeCustomers);

	// for ( CustomerOrder select : this.activeCustomers ) {

	//     if ( select != null ) {
	    
	// 	if ( select.get_status() == CustomerOrderStatus.WAITING ) {

	// 	    System.out.printf(" W");

	// 	} else if ( select.get_status() == CustomerOrderStatus.IMPATIENT ) {

	// 	    System.out.printf(" I");

	// 	} else {

	// 	    System.out.printf(" ?");
		    
	// 	}
		
	//     } else { System.out.printf(" _"); }

	// }

	// System.out.println("");

	statuses_a_c_refresh();

	CustomerOrder c = null;

	try {

	    c = (CustomerOrder)((LinkedList)this.activeCustomers).get(2);

	} catch (Exception e) { ; }

	if ( peek() != null &&
	     peek().get_status() == CustomerOrderStatus.IMPATIENT ) {

	    return true;

	     }

	return false;

    }

    /**
     * draw
     * @return order
     */
   
    public CustomerOrder drawCustomer() {

	return (CustomerOrder)((LinkedList)this.customerDeck).removeLast();

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

    // /**
    //  * getter
    //  * @param customer a
    //  */
    
    // public void move_fulfilled_card(CustomerOrder customer) {

    // 	((LinkedList)this.activeCustomers).remove(customer);

    // 	this.inactiveCustomers.add(customer);

    // }
 
    /**
     * getter
     * @param hand a
     * @return a
     */
    
    public Collection<CustomerOrder> getFulfilable(List<Ingredient> hand) {

	List<CustomerOrder> can_ff = new LinkedList<CustomerOrder>();
	
	for ( CustomerOrder a_cust : activeCustomers ) {

	    try {

		// System.out.println(CustomerOrder.list_to_quantities(a_cust.getRecipe()));
		// System.out.println(CustomerOrder.list_to_quantities(hand));
	
		boolean res = SL.compare_quantities(
                        SL.list_to_layer_bool(
							 a_cust.getRecipe()),
                        SL.list_to_layer_bool(
							 hand),
			SL.list_to_quantities(
							 a_cust.getRecipe()),
			SL.list_to_quantities(
							 hand),
				0
				);
	    
		if ( res == true ) {

		    can_ff.add(a_cust);

		}

	    } catch (Exception e) {;}

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

	    if ( order.get_status() == status ) {

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

	// System.out.println("initialiseCustomerDeck called");

	// Hotfix for a buggy test.

	if ( this.initialised_already == true ) {

	    ((LinkedList)this.customerDeck).clear();

	}
	
	List<CustomerOrder> level_1 = new LinkedList<>();

	List<CustomerOrder> level_2 = new LinkedList<>();

	List<CustomerOrder> level_3 = new LinkedList<>();

	List<CustomerOrder> customers_list = new LinkedList<>();

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

	this.initialised_already = true;
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

	return (CustomerOrder)((LinkedList)this.activeCustomers).get(2);

    }

    /**
     * something
     * @return ok
     */
    
    public CustomerOrder anti_peek() {

	for ( CustomerOrder c : this.activeCustomers ) {

	    if ( c != null ) {

		return c;

	    }

	}

	return null;

    }

    /**
     * something
     * @param customer ok
     */
    
    public void remove(CustomerOrder customer) {

	int i;

	for ( i = 0 ; i < 3 ; i++ ) {

	    if ( ((LinkedList)this.activeCustomers).get(i) != null &&
		 ((LinkedList)this.activeCustomers).get(i).toString().equals(customer.toString()) ) {

		this.inactiveCustomers.add(customer);
		
		((LinkedList)this.activeCustomers).set(i, null);

		break;

	    }

	}

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
     * something
     */

    public String toString() {

	String out = "";
	
	out += "[ ";

	for ( CustomerOrder c : this.activeCustomers ) {

	    out += c + "  ";
	    
	}

	out += "]";

	return out;
	    
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
