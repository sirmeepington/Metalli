/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casestudy;

import static casestudy.util.Input.VerifyInputValues;
import java.util.Arrays;

/**
 * @author Aidan
 */
public class CaseStudy {

    /**
     * @param args the command line arguments
     */    
    public static void main(String[] args) {
        
        // Create OrderManagement object.
        OrderManagement orders = new OrderManagement();
        
        // Set a label for the main loop to break on exit.
        MAINLOOP:
        while (true){
            // Show menu.
            System.out.println("\nMetalli Engraving - Business order system."
                    + "\nAvailable choices:"
                    + "\n1. Create a new order."
                    + "\n2. Manage orders."
                    + "\n3. Quit."
                    + "\n");
            // Get choice and use switch/case to check values.
            String choice = VerifyInputValues("Please choose an action: ",Arrays.asList(new String[]{"1","2","3"}));
            switch (choice){
                case "1":
                    orders.newOrder();
                    break;
                case "2":
                    orders.doMenu();
                    break;
                case "3":
                    // Break the while(true) loop to exist the program
                    break MAINLOOP;
            }
        }
        
    }

    
}
