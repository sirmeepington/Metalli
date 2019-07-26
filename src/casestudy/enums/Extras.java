/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casestudy.enums;

/**
 *
 * @author Aidan
 */
public enum Extras {
    
    TREE_STAKE(25d,"Cannot be included with backing board."),
    BACKING_BOARD_SMALL(7d,"Cannot be included with tree stake."),
    BACKING_BOARD_MEDIUM(10d,"Cannot be included with tree stake."),
    BACKING_BOARD_LARGE(12d,"Cannot be included with tree stake."),
    SILHOUETTE_PICTURE(4.5d),
    FIXING_HOLES(0d),
    BORDER(3.5d),
    BLACK_PAINT_FILLED_LETTERING(0.07d,"per letter"),
    FINISHED(-1d);
    
    private final double price;
    private String note = "";

    public double getPrice(){
        return this.price;
    }
    
    public String getNote(){
        return this.note;
    }
    
    private Extras(double price){
        this.price = price;
    }
    
    private Extras(double price, String note){
        this.price = price;
        this.note = note;
    }
    
}

