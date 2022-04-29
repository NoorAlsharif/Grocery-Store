
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
public class CashierQueue {

    private Customer front;
    private Customer back;
    private String name;

    //-------------------------------------------
    //CONSTRUCTOR:
    public CashierQueue(String name) {

        front = null;
        back = null;

        this.name = name;
    }

    //-------------------------------------------
    //ACCESSOR:
    public String getName() {
        return name;
    }

    //-------------------------------------------
    //MUTATOR:
    public void setName(String name) {
        this.name = name;
    }

    //-------------------------------------------
    public boolean isEmpty() {
        return (front == null);
    }

    //-------------------------------------------
    //ENQUEUE:
    //PUBLIC:
    public void enqueue(Customer customer) {

        if (isEmpty()) {
            front = back = enqueue(back, customer);
        } else {
            back = enqueue(back, customer);
        }
    }

    //PRIVATE:
    private Customer enqueue(Customer back, Customer customer) {

        back.setNext(customer);
        back = customer;
        return back;
    }

    //-------------------------------------------
    //DEQUEUE:
    //PUBLIC:
    public Customer dequeue() {

        Customer temp = front;
        front = dequeue(front);

        if (front == null) {
            back = null;
        }
        return temp;
    }

    //PRIVATE:
    private Customer dequeue(Customer front) {

        front = front.getNext();
        return front;
    }

    //-------------------------------------------
    //PEEK:
    //PUBLIC:
    public Customer peek() {

        return peek(front);
    }

    //PRIVATE:
    private Customer peek(Customer front) {

        return front;
    }

}
