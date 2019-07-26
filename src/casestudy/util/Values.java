/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casestudy.util;

import casestudy.enums.Extras;
import casestudy.enums.MetalTypes;
import casestudy.orders.Order;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author Aidan
 */
public class Values {
        
    /**
     * Calculates the discount code multiplier from the string given.
     * @param in Discount code string
     * @return Discount multiplier
     */
    public static double CalculateDiscountDouble(String in){
        try {
            String inNew = String.join("",Arrays.copyOf(in.split(""), 1));
            double d = Double.parseDouble(inNew);
            return d/100;
        } catch (NumberFormatException ex){
            System.err.println("Error formatting valid code.");
            return 0d;
        }
    }
    
    /**
     * More specific method to iterate over MetalTypes enumerator and prints values with <code>price</code>
     * @return Multi-line string with all sizes.
     */
    public static String ShowSizes(){
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (MetalTypes e : MetalTypes.values()){
            sb.append(i)
              .append(". ")
              .append(e.name())
              .append(" - £")
              .append(String.format("%.2f",e.getPrice()))
              .append("\n");
            i++;
        }
        
        return sb.toString();
    }
    
    /**
     * Creates a HashMap of choices from an enumerator.
     * @param <T> Enum type to loop over
     * @param e Enum's <code>.class</code>
     * @return LinkedHashMap of choices; string key for the choice, generic typed value from the type given. Null if class given isn't an enum.
     */
    public static <T extends Enum> LinkedHashMap<String,T> GetEnumChoices(Class<T> e){
        if (!e.isEnum()){
            return null;
        }   
        LinkedHashMap<String,T> temp = new LinkedHashMap<>();
        int i = 0;
        T[] enumConstants = e.getEnumConstants();
        for (T enu : enumConstants){
            temp.put(Integer.toString(i+1),enu);
            i++;
        }
        
        return temp;
    }
    
    /**
     * Calculates the lettering cost of a certain String.
     * @param lettering String of characters to be put on the sign.
     * @return The cost of the sign, (2.5d per 40 chars)
     */
    public static double CalculateLetteringCost(String lettering){
        lettering = lettering.replaceAll(" ", "");
        final double pricePerChars = 2.5d;
        double out = pricePerChars+(Math.floor(lettering.length()/40d)*pricePerChars);
        return out;
    }
    
    /**
     * Takes orders from OrderManagement and returns a HashMap to make choices from
     * @param orders List of Orders from OrderManagement
     * @return A LinkedHashMap of choice String, and Order value.
     */
    public static LinkedHashMap<String,Order> GetOrdersFromList(List<Order> orders){
        LinkedHashMap<String,Order> t = new LinkedHashMap<>();
        int i = 1;
        for (Order o : orders){
            t.put(Integer.toString(i),o);
            i++;
        }
        return t;
    }
    
    /**
     * Specific method to loop over extras, including handling the <code>FINISHED</code> value.
     * @return Multi-line string of Extras enum values.
     */
    public static String ShowExtras(){
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Extras e : Extras.values()){
            String price = "";
            if (e.getPrice() == 0d){
                price = " - Free";
            } else if (e.getPrice() == -1d){
                price = "";
            } else {
                price = " - £"+e.getPrice();
            }
            sb.append(i+". "+e.name()+price+(e.getNote().isEmpty() ? "" : " - "+e.getNote())+"\n");

            i++;
        }
        return sb.toString();
    }

    /**
     * Generic method to print enum values/
     * @param <T> Enum class type.
     * @param e Enum class references - must match <T>
     * @return Multi-line string of values from the enum.
     */
    public static <T extends Enum> String ShowValues(Class<T> e){
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for(T c : e.getEnumConstants()){
            sb.append(i)
              .append(". ")
              .append(c.name())
              .append("\n");
            i++;
        }
        
        String s = sb.toString();
        sb.setLength(0);
        return s;
    }
    
    /**
     * 
     * Checks the values of the EnumSet to determine whether or not the choice given is valid.
     * Determines this by checking if Backing Board AND Tree Stake is selected/included. If so; the choice is invalid.
     * @param e EnumSet of currently selected Extras.
     * @param eChoice Current Extras value that is being checked.
     * @return Whether choice is valid or not.
     */
    public static boolean IsExtraValid(EnumSet<Extras> e, Extras eChoice){
        boolean backingBoard = (e.contains(Extras.BACKING_BOARD_LARGE) || e.contains(Extras.BACKING_BOARD_MEDIUM) || e.contains(Extras.BACKING_BOARD_SMALL) || eChoice == Extras.BACKING_BOARD_LARGE || eChoice == Extras.BACKING_BOARD_MEDIUM || eChoice == Extras.BACKING_BOARD_SMALL);
        boolean treeStake = (e.contains(Extras.TREE_STAKE) || eChoice == Extras.TREE_STAKE);
        
        if (backingBoard && treeStake){
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * Checks the current metal type's size and the choice to see if they match / are valid.
     * @param size Current MetalTypes size chosen.
     * @param choice Extra choice given.
     * @return Whether or not the extra size is valid.
     */ 
    public static boolean ExtraSizeValid(MetalTypes size, Extras choice){
        if (choice != Extras.BACKING_BOARD_LARGE && choice != Extras.BACKING_BOARD_MEDIUM && choice != Extras.BACKING_BOARD_SMALL){
            return true;
        }
        if (size == MetalTypes.ALUMINIUM_LARGE || size == MetalTypes.BRASS_LARGE || size == MetalTypes.STEEL_LARGE){
            if (choice == Extras.BACKING_BOARD_LARGE){
                return true;
            } else {
                return false;
            }
        } else if (size == MetalTypes.ALUMINIUM_MEDIUM || size == MetalTypes.BRASS_MEDIUM || size == MetalTypes.STEEL_MEDIUM){
            if (choice == Extras.BACKING_BOARD_MEDIUM){
                return true;
            } else {
                return false;
            }
        } else if (size == MetalTypes.ALUMINIUM_SMALL || size == MetalTypes.BRASS_SMALL || size == MetalTypes.STEEL_SMALL){
            if (choice == Extras.BACKING_BOARD_SMALL){
                return true;
            } else { 
                return false;
            }
        } 
        return true;
    }
    
}
