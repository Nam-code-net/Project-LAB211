package sample.controllers;


import java.util.ArrayList;
import sample.dto.I_Menu;
import sample.utils.Utils;

public class Menu extends ArrayList<String> implements I_Menu {

    public Menu() {
        super();
    }
    // must implement all abstract method of I_Menu interface

    @Override
    public void addItem(String s) {
        this.add(s);
    }

    @Override
    public void showMenu() {
        System.out.println("Welcome To Bike Stores Management System"); 
        for (int i = 0; i < this.size(); i++) {
            System.out.println(this.get(i));
        } //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean confirmYesNo(String welcome) {
         boolean result = false;
        String confirm = Utils.getString(welcome);
        if ("Y".equalsIgnoreCase(confirm)) {
            result = true;
        }
        return result;
    }

    @Override
    public int getChoice() {
        int maxOption = this.size();
        String inputMsg = "Choose [1.." + maxOption + "]: ";
        String errorMsg = "You are required to choose the option 1.." + maxOption;
        return Utils.getAnInteger(inputMsg, errorMsg, 1, maxOption);
    }

}
