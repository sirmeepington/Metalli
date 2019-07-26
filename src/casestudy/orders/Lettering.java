/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casestudy.orders;

/**
 *
 * @author Aidan
 */
public class Lettering {
    
    String text;
    double price;
    double fontSize;
    
    public Lettering(String text,double price, double fontSize){
        this.text = text;
        this.price = price;
        this.fontSize = fontSize;
    }
    
    public Lettering(){
        this.text = "";
        this.price = 0.0d;
        this.fontSize = 10d;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
}
