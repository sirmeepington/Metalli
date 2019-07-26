/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casestudy.orders;

import casestudy.enums.Extras;
import casestudy.enums.MetalTypes;
import casestudy.util.Generic;
import casestudy.util.Input;
import casestudy.util.Values;
import java.util.EnumSet;
import java.util.LinkedHashMap;

/**
 *
 * @author Aidan
 */
public class Interface {
    
    // Constructor welcome call.
    // Calls when interface is generated. Says a welcome message :)
    public Interface(){        
        System.out.println("\nWelcome to Metalli Engraving. \tThis is order #"+Order.orderNumber+"\n");
    }
    
    // Gets the enum MetalTypes and outputs it to the screen
    // from the choices, a list of acceptable inputs is generated
    // when an acceptable choice is chosen
    // it gets said input and returns it
    protected MetalTypes showMetalTypeMenu(){
        System.out.println(Values.ShowSizes());
        System.out.print("Please choose the the material and size's #: ");
        LinkedHashMap<String,MetalTypes> metalChoices = Values.GetEnumChoices(MetalTypes.class);
        String choice = Input.VerifyInputValues(null,Generic.GetMapKeys(metalChoices));
        MetalTypes chosenMetalType = metalChoices.get(choice);
        System.out.println("\n"+chosenMetalType.toString()+" (#"+choice+") chosen.");
        return chosenMetalType;
    }
    
    
    // Shows the lettering intro message 
    // prompts for an input and verifies that its valid length
    // also calculates the cost of the lettering <- this took a while
    // returns an instance of the Lettering class
    // which contains the price and the text for easy access 
    protected Lettering showLetteringMenu(){
        System.out.println("Lettering is available at a price of £2.50 per 40 characters.\n\nPlease insert the text you would like; or leave it blank for none.");
        String lettering = Input.VerifyLetteringText();
        if (!lettering.isEmpty()){
            System.out.println("Please insert the font size for your text. 10mm - 25mm only.");
            // handles invalid font sizes
            double fontSize = -1d;
            while (fontSize == -1d){
                try {
                    fontSize = Input.VerifyFontSize(); // VerifyFontSize throws NumberFormatException instead of handling it itself.
                } catch (NumberFormatException ex){ }
            }
            double price = Values.CalculateLetteringCost(lettering);
            System.out.println("Your text: '"+lettering+"' at "+fontSize+"mm results to £"+String.format("%.2f",price)+".");
            Lettering out = new Lettering(lettering,price,fontSize);
            return out;
        } else {
            System.out.println("You have chosen not to include text. No charge has been added.");
            return new Lettering();
        }
    }
    
    // Shows the extra menu with the enum display function
    // Passes in the MetalTypes to make sure that the extras are valid
    // Also creates an empty EnumSet to manage the extras
    // allows to add / remove extras as well
    // also does the verification check between tree stake and backing board
    protected EnumSet<Extras> showExtraMenu(Order o){
        MetalTypes size = o.metalType;
        System.out.println("Optional extras are available:\n"+Values.ShowExtras());
        LinkedHashMap<String,Extras> extraChoices = Values.GetEnumChoices(Extras.class);
        String choice = Input.VerifyInputValues("Choose an extra you would like to add: ", Generic.GetMapKeys(extraChoices));
        EnumSet<Extras> e = EnumSet.noneOf(Extras.class);
        while (extraChoices.get(choice) != Extras.FINISHED){
            Extras eChoice = extraChoices.get(choice);
            if (e.contains(eChoice)){
                e.remove(eChoice);
                System.out.println("Removed "+eChoice+" from the list.");
            } else {
                // Put all the ugly stuff in a function 
                if (eChoice == Extras.BLACK_PAINT_FILLED_LETTERING && o.lettering.getText().isEmpty()){
                    System.out.println("You cannot include "+Extras.BLACK_PAINT_FILLED_LETTERING+" without text.");
                } else {
                    if (Values.IsExtraValid(e, eChoice)){
                        if (Values.ExtraSizeValid(size,eChoice)){
                            e.add(extraChoices.get(choice));
                            System.out.println("Added "+extraChoices.get(choice)+" to list.");
                        } else {
                            System.out.println("The backing board choice for the size is invalid.");
                        }
                    } else {
                        if (eChoice == Extras.TREE_STAKE){
                            System.out.println("You cannot add "+eChoice+" to your extras list as you have a backing board selected.");
                        } else {
                            System.out.println("You cannot add "+eChoice+" to your extras list as you have "+Extras.TREE_STAKE+" selected.");
                        }            
                    }
                }
            }
            choice = Input.VerifyInputValues("Choose an extra you would like to add: ", Generic.GetMapKeys(extraChoices));
        }
        
        return e;
    }
    
    // 
    protected double showDiscount(){
        System.out.println("Please insert a discount code, or leave blank to exclude one.");
        String discountCode = Input.VerifyDiscountCode();
        double discountAmount = 0d;
        if (!discountCode.equals("")){
            discountAmount = Values.CalculateDiscountDouble(discountCode);
            System.out.println("Your discount code is valid. It will be applied at checkout.");
        } else {
            System.out.println("You have chosen to not apply a discount code.");
        }
        return discountAmount;
    }
    


    
}
