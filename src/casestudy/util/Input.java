/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casestudy.util;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Aidan
 */
public class Input {
    
    /**
     * Gets input from the Scanner and System.in; loops until input matches <code>target</code>.
     * @param message Message to display before checking
     * @param target Valid message to compare against.
     */
    public static void VerifyInputString(String message, String target){
        Scanner sc = new Scanner(System.in);
        System.out.print(message);
        String input = sc.nextLine();
        while (!input.equals(target)){
            System.err.println("Invalid input. Please try again.");
            input = sc.nextLine();
        }
    }
    
    /**
     * 
     * @param message Message to display before asking for input. <code>null</code> for none.
     * @param targets A list of valid strings.
     * @return Choice from the list of targets.
     */
    public static String VerifyInputValues(String message, List<String> targets){
        Scanner sc = new Scanner(System.in);
        if (message != null){
            System.out.print(message);
        }
        String input = sc.nextLine();
        boolean valid = false;
        VALIDLOOP:
        while (!valid){
            for (String v : targets){
                if (v.equals(input)){
                    break VALIDLOOP;
                }
            }
            System.err.println("Invalid input. Please try again.");
            input = sc.nextLine();
        } 
        return input;
    }
    
    /**
     * Uses System.in and Scanner to get and verify lettering for the sign. 
     * @return Input string if valid, <code>null</code> otherwise
     */
    public static String VerifyLetteringText(){
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
        int length = in.length();
        if (length == 0){
            return "";
        }
        return in;
    }
    
    /**
     * Gets input and holds execution until valid input is given (10-25)
     * @return Valid font size
     */
    public static Double VerifyFontSize() throws NumberFormatException {
        Scanner sc = new Scanner(System.in);
        String inString  = sc.next();
        double in = Double.parseDouble(inString);
        while (in < 10 || in > 25){
            System.err.println("Invalid font size. 10mm - 25mm only. Please try again.");
            inString = sc.next();
            in = Double.parseDouble(inString);
        }
        return in;
    }
    
    /**
     * Gets input using Scanner and verifies that the discount code given is valid.
     * Holds execution until valid or skipped.
     * @return Valid discount code as a string or nothing for no string.
     */
    public static String VerifyDiscountCode(){
        Scanner sc = new Scanner(System.in);
        boolean valid = false;
        String in = sc.nextLine();
        while (!valid){
            if (in.isEmpty()){
                return "";
            }
            char[] digits = in.toCharArray();
            if (digits.length != 4){
                System.out.println("Please insert a valid discount code.");
            } else {
                if (Character.isDigit(digits[0]) && Character.isDigit(digits[1])){
                    if (Character.isLetter(digits[2]) && Character.isLetter(digits[3])){
                        break;
                    } else {
                        System.out.println("Please insert a valid discount code.");
                    }
                } else {
                    System.out.println("Please insert a valid discount code.");
                }
            }
        }
        return in;
    }
    
}
