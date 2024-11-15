package sample.view;

import java.util.ArrayList;
import java.util.List;
import sample.dto.I_List;
import sample.dto.I_Menu;
import sample.controllers.Menu;
import sample.controllers.ProductList;
import sample.utils.FileUtils;


public class ProductManagement {

    public static void main(String args[]) {
        I_Menu menu = new Menu();
    menu.addItem("1. Add new product");
    menu.addItem("2. Remove a product");
    menu.addItem("3. Update a product");
    menu.addItem("4. Sort list");
    menu.addItem("5. SearchByName");
    menu.addItem("6. Quit");

    int choice;
    boolean cont = false;  // Change to false so it continues until user confirms exit
    I_List list = new ProductList();

    do {
        menu.showMenu();  // Show the menu options
        choice = menu.getChoice();  // Get user's choice

        switch (choice) {
            case 1:
                list.add();
                break;
            case 2:
                list.remove();
                break;
            case 3:
                list.update();
                break;
            case 4:
                list.sort();
                list.output();
                break;
            case 5:
                list.searchbyName();
            case 6:
                cont = menu.confirmYesNo("Do you want to quit? (Y/N)");
                break;
            default:
                System.out.println("Invalid choice! Please select again.");
        }
    } while (!cont);  // Continue showing the menu until the user decides to quit


//        FileUtils fileUtils = new FileUtils();
//
//        // Define the path to the files
//        String brandFilePath = "Brand.txt";
//        String categoryFilePath = "Category.txt";
//
//        // Add bicycle-related brands
//        List<String> brands = new ArrayList<>();
//        brands.add("1, Trek");
//        brands.add("2, Asama");
//        brands.add("3, Giant");
//        brands.add("4, Cannondale");
//
//        // Write each brand to the Brand.txt file
//        for (String brand : brands) {
//            fileUtils.writeFile(brandFilePath, brand);
//        }
//
//        // Add bicycle-related categories
//        List<String> categories = new ArrayList<>();
//        categories.add("1, Mountain Bike");
//        categories.add("2, Road Bike");
//        categories.add("3, Hybrid Bike");
//        categories.add("4, Electric Bike");
//
//        // Write each category to the Category.txt file
//        for (String category : categories) {
//            fileUtils.writeFile(categoryFilePath, category);
//        }
//
//        System.out.println("Brands and categories added to the files.");
    

    }
}
