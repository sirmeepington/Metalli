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
public enum MetalTypes {

    ALUMINIUM_SMALL(18.51d),
    ALUMINIUM_MEDIUM(24.70d),
    ALUMINIUM_LARGE(29.95d),

    BRASS_SMALL(21.35d),
    BRASS_MEDIUM(28.50d),
    BRASS_LARGE(34.55d),

    STEEL_SMALL(20.77d),
    STEEL_MEDIUM(27.72d),
    STEEL_LARGE(33.60d);
    
    private final double price;
    
    public double getPrice(){
        return this.price;
    }
    
    private MetalTypes(double price){
        this.price = price;
    }
    
    public static MetalTypes get(int index){
        return MetalTypes.values()[index];
    }
}
