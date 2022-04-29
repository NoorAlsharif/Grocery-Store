
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

public class Customer {

    private String firstName;
    private String lastName;
    private int numItems;
    private int enteryTime;
    private Customer next;
    private int InWaitingTime;
    
    //-------------------------------------------
    //CONSTRUCTORS:

    public Customer(String firstName, String lastName, int enteryTime) {
        
        this.firstName = firstName;
        
        this.lastName = lastName;
        
        this.enteryTime = enteryTime;
        
        next = null;
    }
    
    public Customer(String firstName, String lastName, int enteryTime, int orderTime, Customer next) {
        
        this.firstName = firstName;
        
        this.lastName = lastName;
        
        this.enteryTime = enteryTime;
        
        this.InWaitingTime = orderTime;
        
        this.next = next;
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
    
    public int getEnteryTime() {
        return enteryTime;
    }
    
    public Customer getNext() {
        return next;
    }
    
    public int getWaitingTime() {
        return InWaitingTime;
    }
    
    
    //-------------------------------------------
    
    //MUTATORS:
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
     
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setNumItems(int numItems) {
        this.numItems = numItems;
    }
    
    public void setEnteryTime(int enteryTime) {
        this.enteryTime = enteryTime;
    }
    
    public void setNext(Customer next) {
        this.next = next;
    }
    
    public void setWaitingTime(int InWaitingTime) {
        this.InWaitingTime = InWaitingTime;
    }

    
}
