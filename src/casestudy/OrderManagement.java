/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casestudy;

import casestudy.orders.Order;
import casestudy.util.Generic;
import casestudy.util.Input;
import casestudy.util.Values;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author Aidan
 */
public class OrderManagement {
    
    // List of orders that will exist during runtime.
    List<Order> orders = new ArrayList<>();
    
    /**
     * Creates a new order and adds it to the list.
     * @param o The order to be added. If given as a <code>new</code> block the order will be initialized before adding.
     */    
    private void addOrder(Order o){
        orders.add(o);
    }
    
    /**
     * Creates a new order and outputs the details from it once completed.
     */
    public void newOrder(){
        Order o = new Order();
        o.outputOrderDetails();
        addOrder(o);
    }
    
    /**
     * Shows a menu for the OrderManagement.
     * Lists all orders as well as giving the option to return to the main menu.\
     * If the user inputs / chooses an order, runs the method to choose it.
     */
    public void doMenu(){
        System.out.println("\nOrders menu. Currently recorded orders: \n0. Back");
        int i = 1;
        StringBuilder sb = new StringBuilder();
        for (Order o : orders){
            sb.append(i+". "+o.toString()+"\n");
            i++;
        }
        System.out.println(sb.toString());
        sb.setLength(0);
        
        LinkedHashMap<String,Order> choices = Values.GetOrdersFromList(this.orders);
        
        List<String> choicesList = Generic.GetMapKeys(choices);
        choicesList.add("0");
        String choice = Input.VerifyInputValues("Please insert a order number: ", choicesList);
        if (choice.equals("0")){
            return;
        }
        Order o = choices.get(choice);
        chooseOrder(o);
    }
    
    /**
     * If the order exists in the current Order list; remove it.
     * @param o 
     */
    private void deleteOrder(Order o){
        if (orders.contains(o)){
            orders.remove(o);
        }
    }
    
    /**
     * Gets the order chosen and shows information / options about said choice.
     * Allows the user to either view the order or delete its record.
     * @param o Order chosen on the menu.
     */
    public void chooseOrder(Order o){
        System.out.println("Order #"+o.getOrderNum()+" selected: \n"
                + "Options:\n"
                + "1. View order details.\n"
                + "2. Delete order\n"
                + "3. Back");
        
        String choice = Input.VerifyInputValues("Select an option: ", Arrays.asList( new String[] {"1","2","3"} ) );
        switch (choice){
            case "1":
                    System.out.println("Printing order information:");
                    o.outputOrderDetails();
                break;
            
            case "2":
                    System.out.println("Deleting order #"+o.getOrderNum());
                    deleteOrder(o);
                    System.out.println("Order deleted...");
                break;
            
            case "3":
                break;
        }
        
    }
    
}
