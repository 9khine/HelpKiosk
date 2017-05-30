package com.prototype.helpkiosk.instructioncontrol;

import java.util.HashMap;

import javax.swing.JPanel;

public class ViewController {
	HashMap<String,JPanel> views;
    
    public ViewController(){
        views = new HashMap<String,JPanel>();
    }
    
    public void registerView(String name, JPanel view){
        views.put(name,view);
    }
    
    public void showView(String name){
        if (views.containsKey(name)){
            for (String key : views.keySet()){
                views.get(key).setVisible(key.equals(name));
            }
        }
    }
}
