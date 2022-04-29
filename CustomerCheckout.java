/*
 * Noor Hashem Al Ghalib Al Sharif 
 * 1725009
 * IBR
 * CPCS 204
 * nalsharif0045@stu.kau.edu.sa
 */
package grocerystore;

/**
 *
 * @author nooralsharif
 */

public class CustomerCheckout {
    
    private String firstName;
    private String lastName;
    private int numItems;
    private int entryTime;
    private int timeNeededForCheckout;
    private CustomerCheckout next;
    
    //-------------------------------------------
    //CONSTRUCTOR:
    public CustomerCheckout(Customer customer) {
        
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.entryTime = customer.getEnteryTime();
        this.numItems = customer.getNumItems();
    }
    
    //-------------------------------------------
    //ACCESSORS:
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public int getNumItems() {
        return numItems;
    }
    
    public int getEntryTime() {
        return entryTime;
    }
        
    public int getTimeNeededForCheckout() {
        return timeNeededForCheckout;
    }
    
    public CustomerCheckout getNext() {
        return next;
    }
    
    //-------------------------------------------
    //MUTATORS:
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setEntryTime(int entryTime) {
        this.entryTime = entryTime;
    }
    
    public void setTimeNeededForCheckout(int timeNeededForCheckout) {
        this.timeNeededForCheckout = timeNeededForCheckout;
    }
    
    public void setNext(CustomerCheckout next) {
        this.next = next;
    }
    
    
    
}
