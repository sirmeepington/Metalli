/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casestudy.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Aidan
 */
public class Generic {
       
    /**
     * Loops over generic HashMap and returns a list of the keys.
     * @param <T> Generic type for HashMap values.
     * @param in A LinkedHashMap to take the keys for.
     * @return A List of type String containing all the keys from the HashMap
     */
    public static <T> List<String> GetMapKeys(LinkedHashMap<String,T> in){
        List<String> tempList = new ArrayList<>();
        for (Map.Entry<String,T> entry : in.entrySet()){
            String key = entry.getKey();
            tempList.add(key);
        }
        
        return tempList;
    } 
}
