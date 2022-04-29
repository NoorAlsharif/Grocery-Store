
/*
 * Noor Hashem Al Ghalib Al Sharif 
 * 1725009
 * IBR
 * CPCS 204
 * nalsharif0045@stu.kau.edu.sa
 */
package grocerystore;

import java.io.*;

/**
 *
 * @author nooralsharif
 */
public class TransactionStack {

    private CustomerCheckout top;

    //-------------------------------------------
    //CONSTRUCTOR:
    public TransactionStack() {
        top = null;
    }

    //-------------------------------------------
    //isEmpty:
    public boolean isEmpty() {
        return (top == null);
    }

    //-------------------------------------------
    //PUSH:
    //PUBLIC:
    public void push(CustomerCheckout customer) {

        if (isEmpty()) {
            top = customer;
        } else {
            top = push(top, customer);
        }
    }

    //PRIVATE:
    private CustomerCheckout push(CustomerCheckout top, CustomerCheckout customer) {

        customer.setNext(top);
        top = customer;
        return top;

    }

    //-------------------------------------------
    //POP:
    //PUBLIC:
    public CustomerCheckout pop() {

        if (isEmpty()) {
            return null;
        }

        CustomerCheckout temp = top;

        top = pop(top);

        return temp;
    }

    //PRIVATE:
    private CustomerCheckout pop(CustomerCheckout top) {

        top = top.getNext();
        return top;
    }

    //-------------------------------------------
    //PEEK:
    //PUBLIC:
    public CustomerCheckout Top() {

        return Top(top);
    }

    //private:
    private CustomerCheckout Top(CustomerCheckout top) {
        return top;
    }

    //-------------------------------------------
    //PRINT:
    //PUBLIC:
    public void PrintStack(PrintWriter outputfile) {
        PrintStack(top, outputfile);

    }

    //PRIVATE:
    private void PrintStack(CustomerCheckout top, PrintWriter outputfile) {
        
        CustomerCheckout customerCheckout = pop();

        outputfile.println("Confirmed Served:");

        while (isEmpty() != true) {

            String timeCompleted = GroceryStoreSim.Time(customerCheckout.getTimeNeededForCheckout());

            outputfile.println();
            outputfile.println("Customer Name:     " + customerCheckout.getFirstName() + " " + customerCheckout.getLastName());
            outputfile.println("Time Completed:   " + timeCompleted);
            outputfile.println();

            customerCheckout = pop();

        }

        outputfile.println();

    }
}
