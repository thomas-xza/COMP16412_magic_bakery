package bakery;

import java.util.*;

import bakery.*;

/**
 *   Customers
 *   @author thomas.
 * @version 1.5
 * @since 1.0
*/

public class Customers {

    private Collection<CustomerOrder> activeCustomers = new ArrayList<>();
    private Collection<CustomerOrder> customerDeck = new ArrayList<>();
    private List<CustomerOrder> inactiveCustomers = new ArrayList<>();
    private Random random;

    private static long serialVersionUID;
    

    // /**
    //  * hello
    //  * @param deckFile a 
    //  * @param random b
    //  * @param layers c
    //  * @param numPlayers d
    //  */

    // public Customers(String deckFile, Random random, List<Layer> layers, int numPlayers) {

    // }

    // /**
    //  * add
    //  * @return order
    //  */

    // public CustomerOrder addCustomerOrder() {

    // 	CustomerOrder a = CustomerOrder.fast_order();

    // 	return a;

    // }

    // /**
    //  * leave
    //  * @return true
    //  */

    // public boolean customerWillLeaveSoon() {

    // 	return true;

    // }

    // /**
    //  * draw
    //  * @return order
    //  */
   
    // public CustomerOrder drawCustomer() {

    // 	CustomerOrder a = CustomerOrder.fast_order();

    // 	return a;

    // }

    // /**
    //  * active
    //  * @return list
    //  */
    
    // public Collection<CustomerOrder> getActiveCustomers() {

    // 	List<CustomerOrder> a = CustomerOrder.fast_order_list();

    // 	return a;

    // }

    // /**
    //  * getter
    //  * @return list
    //  */
    
    // public Collection<CustomerOrder> getCustomerDeck() {

    // 	List<CustomerOrder> a = CustomerOrder.fast_order_list();

    // 	return a;

    // }
 
    // /**
    //  * getter
    //  * @return list
    //  */
    
    // public Collection<CustomerOrder> getFulfilable(List<Ingredient> hand) {

    // 	List<CustomerOrder> a = CustomerOrder.fast_order_list();

    // 	return a;

    // }

    // /**
    //  * getter
    //  * @param stat hi
    //  * @return list
    //  */
    
    // public Collection<CustomerOrder> getInactiveCustomersWithStatus(CustomerOrderStatus status) {

    // 	List<CustomerOrder> a = CustomerOrder.fast_order_list();

    // 	return a;

    // }

    // /**
    //  * init
    //  * @param deckFile a
    //  * @param layers b
    //  * @param numPlayers c
    //  * @return list
    //  */
    
    // private void initialiseCustomerDeck(String deckFile, Collection<Layer> layers, int numPlayers) {

    // }

    // /**
    //  * some
    //  * @return true
    //  */
    
    // public boolean isEmpty() {

    // 	return true;

    // }

    // /**
    //  * something
    //  * @return ok
    //  */
    
    // public CustomerOrder peek() {

    // 	CustomerOrder a = CustomerOrder.fast_order();

    // 	return a;

    // }

    // /**
    //  * something
    //  * @param customer ok
    //  */
    
    // public void remove(CustomerOrder customer) {

    // }

    // /**
    //  * size
    //  * @return 0
    //  */
    
    // public int size() {

    // 	return 0;

    // }

    // /**
    //  * something
    //  * @return order
    //  */    

    // public CustomerOrder timePasses() {

    // 	CustomerOrder a = CustomerOrder.fast_order();

    // 	return a;

    // }

}
