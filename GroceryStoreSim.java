
/*
 * Noor Hashem Al Ghalib Al Sharif 
 * 1725009
 * IBR
 * CPCS 204
 * nalsharif0045@stu.kau.edu.sa
 */

package grocerystore;

import java.util.Scanner;
import java.io.*;

/**
 *
 * @author nooralsharif
 */

public class GroceryStoreSim {

    private static int queue1Count;
    private static int queue2Count;
    private static int queue3Count;

    //-------------------------------------------
    
    public static void main(String[] args) throws FileNotFoundException {

        CashierQueue QueueLine1;
        CashierQueue QueueLine2;
        CashierQueue QueueLine3;
        CashierQueue QueueLine4;
        TransactionStack TransactionStack;
        
        //-------------------------------------------
        // FILE INPUT AND OUTPUT:
        
        File inputfile = new File("GroceryStoreSim.in");
        
        if (!inputfile.exists())
            System.out.println("The file dose not exists");
        
        Scanner input = new Scanner(inputfile);
        
        PrintWriter outputfile = new PrintWriter("GroceryStoreSim.out");
        
        TransactionStack = new TransactionStack();
        
        outputfile.println("Welcome to the Grocery Store Simulator");
        outputfile.println();
        
        //-------------------------------------------
        
        QueueLine1 = new CashierQueue("Line 1");
        QueueLine2 = new CashierQueue("Line 2");
        QueueLine3 = new CashierQueue("Line 3");
        QueueLine4 = new CashierQueue("outside line");
        
        
        int allDays = input.nextInt();
        int min;

        for (int Counterday = 1; Counterday <= allDays; Counterday++) {

                int customerday = input.nextInt();

                EnqueuenewCustomer(customerday, input, QueueLine4);
                
                min=0;
                
                outputfile.println("**********");
                outputfile.println("Day " + customerday + ":");
                outputfile.println("**********");
                outputfile.println();

                String currentTime = Time(min);
                
                
                //-------------------------------------------
                while (!currentTime.equals(" 11:59 PM")) {

                    //DEQUEUE:
                    Dequeue(QueueLine1, QueueLine2, QueueLine3, min, outputfile);

                    //CALCULATE TIME WAITING:
                    calculateWaitingTime(QueueLine1, QueueLine2, QueueLine3, min, TransactionStack, outputfile);

                    //ENQUEUE
                    Enqueue(QueueLine4, QueueLine1, QueueLine2, QueueLine3, min, outputfile);

                    min++;
                    currentTime = Time(min);
                }
                
                //-------------------------------------------
                outputfile.println();
                outputfile.println("*** Day " + customerday + ":  Grocery Store Report ***:");
                outputfile.println();
                
                //-------------------------------------------
                //PRINT STACK:
                TransactionStack.PrintStack(outputfile);
                outputfile.println();

            }

        outputfile.close();

    }
    
    
    //-------------------------------------------
    
    private static void startCustomerCheckout(CashierQueue queue, int min, PrintWriter outputfile) {

        if (queue.isEmpty() == false) {

            //ENQUEUE FIRST CUSTOMER:
            Customer customer = queue.peek();

            //SET WAITING TIME:
            if (customer.getWaitingTime() < 0) {

                int waitingTime = customer.getNumItems();
                customer.setWaitingTime(waitingTime);

                String CustomerInfo = Time(min) + ": " + customer.getFirstName() + " " + customer.getLastName();

                outputfile.println(CustomerInfo + " is at the front of Cashier " + queue.getName() + " and is now beginning the check out.");
            }

        }

    }
    
    //-------------------------------------------
    //CALCULATE WAITING TIME:
    //PUBLIC:
    public static void calculateWaitingTime(CashierQueue QueueLine1, CashierQueue QueueLine2, CashierQueue QueueLine3, int min, TransactionStack TransactionStack, PrintWriter outputfile) {
        
        //MINIMIZE TIME SPENT WAITING:
        //QUEUE LINE 1:
        calculateWaitingTime(QueueLine1, min, TransactionStack, outputfile);
        //QUEUE LINE 2:
        calculateWaitingTime(QueueLine2, min, TransactionStack, outputfile);
        //QUEUE LINE 3:
        calculateWaitingTime(QueueLine3, min, TransactionStack, outputfile);
        
        
        //CALCULATE WAITING TIME IN LINE:
        //QUEUE LINE 1:
        startCustomerCheckout(QueueLine1, min, outputfile);
        //QUEUE LINE 2:
        startCustomerCheckout(QueueLine2, min, outputfile);
        //QUEUE LINE 3:
        startCustomerCheckout(QueueLine3, min, outputfile);
    }
    //PRIVATE:
    private static void calculateWaitingTime(CashierQueue queue, int min, TransactionStack TransactionStack, PrintWriter outputfile) {

        if (!queue.isEmpty()) {

            Customer customer = queue.peek();
            int waitingTime = customer.getWaitingTime();

            //GET THE CUSTOMER READY TO DEQUEUE:
            if (waitingTime > 0) {

                waitingTime--;
                customer.setWaitingTime(waitingTime);

                if (waitingTime == 0) {
                    CustomerCheckout customerCheckout = new CustomerCheckout(customer);
                    
                    customerCheckout.setTimeNeededForCheckout(min);

                    TransactionStack.push(customerCheckout);

                    String CustomerInfo = Time(min) + ": " + customerCheckout.getFirstName() + " " + customerCheckout.getLastName();

                    outputfile.println(CustomerInfo + " has received Printed receipt and is leaving the Grocery Store.");

                }
            }
        }

    }
    
    //-------------------------------------------
    //CALCULATE TIME AND PRINTING TIME IN STRING:
    public static String Time(int min) {

        String minuets = String.valueOf(min % 60);
        
        if (minuets.length() == 1) {
            minuets = 0 + minuets;
        }

        int hour = min / 60;
        
        if (hour == 0) {
            hour = 12;
        }

        String hours = String.valueOf(hour);

        String Time = hours + ":" + minuets + " PM";
        
        return " " + Time;

    }
    
    //-------------------------------------------
    //DEQUEUE WHEN WAITING TIME IS DONE:
    private static void Dequeue(CashierQueue QueueLine1, CashierQueue QueueLine2, CashierQueue QueueLine3, int min, PrintWriter outputfile) {
        
        //QUEUE LINE 1 CUSTOMER:
        Customer customer = QueueLine1.peek();
        
        //QUEUE LINE 2 CUSTOMER:
        customer = QueueLine2.peek();
        
        //QUEUE LINE 3 CUSTOMER:
        customer = QueueLine3.peek();
        
        //-------------------------------------------
        
        //DEQUEUE WHEN WAITING TIME IS DONE = 0:
        //LINE 1:
        if (customer != null && customer.getWaitingTime() == 0) {
            QueueLine1.dequeue();
            queue1Count--;
            
            //PRINT:
            String CustomerInfo = Time(min) + ": " + customer.getFirstName() + " " + customer.getLastName();
            outputfile.println(CustomerInfo + " has finished Shopping and is now exiting the " + QueueLine1.getName() + ".");
        }
        
        //LINE 2:
        if (customer != null && customer.getWaitingTime() == 0) {
            QueueLine2.dequeue();
            queue2Count--;
            
            //PRINT:
            String CustomerInfo = Time(min) + ": " + customer.getFirstName() + " " + customer.getLastName();

            outputfile.println(CustomerInfo + " has finished Shopping and is now exiting the " + QueueLine2.getName() + ".");

        }
        
        //LINE 3:
        if (customer != null && customer.getWaitingTime() == 0) {

            QueueLine3.dequeue();
            queue3Count--;
            
            //PRINT:
            String CustomerInfo = Time(min) + ": " + customer.getFirstName() + " " + customer.getLastName();

            outputfile.println(CustomerInfo + " has finished Shopping and is now exiting the " + QueueLine3.getName() + ".");

        }
    }
    
    //-------------------------------------------
    //QUEUE NEW CUSTOMERS FROM INPUT FILE TO LINE:
    private static void EnqueuenewCustomer(int customerday, Scanner read, CashierQueue QueueLine4) {

        for (int CustomerCounter = 0; CustomerCounter < customerday; CustomerCounter++) {
            
            int arrivalMinute = read.nextInt();
            Customer customer = new Customer(read.next(), read.next(), arrivalMinute);

            int NumItems = read.nextInt();
            customer.setNumItems(NumItems);
            
            //SET TIME INITIALY TO -1 
            customer.setWaitingTime(-1);

            if (CustomerCounter == customerday) {
                break;
            }

            QueueLine4.enqueue(customer);

        }

    }
    
    //-------------------------------------------
    //ENQUEUE:
    private static void Enqueue(CashierQueue QueueLine4, CashierQueue QueueLine1, CashierQueue QueueLine2, CashierQueue QueueLine3, int min, PrintWriter outputfile) {

        Customer customer = QueueLine4.peek();
        
        while (customer != null && min == customer.getEnteryTime()) {

            customer = QueueLine4.dequeue();
            
            int arrivalMinute = customer.getEnteryTime();

            CashierQueue queue = null;
            
            //-------------------------------------------
            //DETERMINE THE LEAST FILLED LINE TO ENQUEUE THE CUSTOMER:
            
            //CHECK LINE 1:
            if (queue1Count <= queue2Count && queue1Count <= queue3Count) {
                queue = QueueLine1;
                queue1Count++;
                
            //CHECK LINE 2:    
            } else if (queue3Count <= queue3Count) {
                queue = QueueLine2;
                queue2Count++;
            
            //CHECK LINE 3:    
            } else {
                queue = QueueLine3;
                queue3Count++;
            }
            
            //ENQUEUE TO LINE AND PRINT TIME:
            if (arrivalMinute < 480) {
                
                queue.enqueue(customer);

                String CustomerInfo = Time(min) + ": " + customer.getFirstName() + " " + customer.getLastName();
                outputfile.println(CustomerInfo + " arrived at the Grocery Store and entered Cashier " + queue.getName() + ".");
            }
            customer = QueueLine4.peek();

        }

    }

}
