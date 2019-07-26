/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casestudy.orders;

import casestudy.enums.Extras;
import casestudy.enums.MetalTypes;
import java.util.EnumSet;

/**
 *
 * @author Aidan
 */
public class Order {
    
    public static int orderNumber;
    
    int orderNum;

    public int getOrderNum() {
        return orderNum;
    }

    public boolean isCompleted() {
        return completed;
    }
    
    EnumSet<Extras> extras;
    MetalTypes metalType;
    Lettering lettering;
    double discountAmount;
    
    
    private double total;
    public double totalDue;
    
    private boolean completed;
    
    public Order(){
        orderNumber++; 
        this.orderNum = orderNumber;
        Interface orderInterface = new Interface(); // Interface constructor handles new order prompt
        this.metalType = orderInterface.showMetalTypeMenu(); // Get choice
        System.out.println("\n"
                + "==============================================================================\n");
        this.lettering = orderInterface.showLetteringMenu();
        System.out.println("\n"
                + "==============================================================================\n");
        this.extras = orderInterface.showExtraMenu(this);
        System.out.println("\n"
                + "==============================================================================\n");
        this.discountAmount = orderInterface.showDiscount();
        System.out.println("\n"
                + "==============================================================================\n");
    }
    
    private double calculatePrice(){
        double calculatedTotal = 0d;
        calculatedTotal += metalType.getPrice();
        calculatedTotal += lettering.getPrice();
        for (Extras e : extras){
            if (e == Extras.BLACK_PAINT_FILLED_LETTERING){
                calculatedTotal += e.getPrice()*lettering.getText().length();
            } else {
                calculatedTotal += e.getPrice();
            }
        }
        return calculatedTotal;
    }
    
    /**
     * Calculates the
     * @return 
     */
    private double totalWithDiscount(){
        double calc = this.total;
        
        double reduction = this.total * this.discountAmount;
        return calc - reduction;
    }
    
    /**
     * Gathers the data from the class and outputs it to the screen.
     */
    public void outputOrderDetails(){
        System.out.println("Generating Order Details:"
                + "\n"
                + "\n=============================================================================="
                + "\nMetalli Engraving"
                + "\nOrder #"+this.orderNum+" complete."
                + "\nSign Type: "+metalType+" - £"+metalType.getPrice()+"\n");
        if (!lettering.text.isEmpty()){
            System.out.println("Lettering: £"+lettering.getPrice()+"\n\t'"+lettering.getText()+"'");
        } else {
            System.out.println("Lettering: None included. - £0.00");
        }
        System.out.println("\n"
                + "Extras:");
        if (extras.isEmpty()){
            System.out.println("\t"
                    + "No extras included - £0.00");
        } else {
            for(Extras e : extras){
                System.out.println("\t- "+e+" - £"+e.getPrice());
            }
        }
        System.out.println("\n"
                + "Total: £"+String.format("%.2f",calculatePrice()));
        if (discountAmount != 0d){
            System.out.println("Discount applied: "+(discountAmount*100)+"%");
        } else {
            System.out.println("No discount applied.");
        }
        System.out.println("Grand total: £"
                + String.format("%.2f",discountAmount != 0 ? totalWithDiscount() : calculatePrice()));
        this.completed = true;
        System.out.println("Thank you for using Metalli Engravings.\n==============================================================================");
    }
    
    @Override
    public String toString(){
        String s = "Order #"+this.orderNum+" - Total: "+this.calculatePrice()+" - Completed: "+(this.completed ? "Y" : "N");
        return s;
    }
}
