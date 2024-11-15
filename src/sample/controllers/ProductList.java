package sample.controllers;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import sample.dto.I_List;
import sample.dto.Bicycle;
import sample.utils.FileUtils;
import sample.utils.Utils;
public class ProductList extends ArrayList<Bicycle> implements I_List {
    private static final String PRODUCT_FILE_PATH = "Product.txt";
    private int highestId = 0;
    public void loadHighestId() {
    for (Bicycle product : this) {
        int productId = Integer.parseInt(product.getId().substring(1));
        if (productId > highestId) {
            highestId = productId;
        }
    }
}
    @Override
    public void searchbyName() {
        String searchStr = Utils.getString("Enter a part of Product Name to search: ");
    List<Bicycle> foundProducts = new ArrayList<>();
    
    
    for (Bicycle product : this) {
        if (product.getName().toLowerCase().contains(searchStr.toLowerCase())) {
            foundProducts.add(product);
        }
    }
    
    if (foundProducts.isEmpty()) {
        System.out.println("Have no any Product.");
    } else {
        foundProducts.sort(Comparator.comparingInt(Bicycle::getModelYear));
        System.out.println("Found Products:");
       
        System.out.printf("+--------------+--------------------+--------------------+--------------------+----------------+--------------+\n");
        System.out.printf("| %-12s | %-18s | %-18s | %-18s | %-14s | %-12s |\n", 
                  "PRODUCT ID", "NAME", "BRAND", "CATEGORY", "MODEL YEAR", "LIST PRICE");
        System.out.printf("+--------------+--------------------+--------------------+--------------------+----------------+--------------+\n");

// Print each product
        for (Bicycle product : foundProducts) {
        System.out.printf("| %-12s | %-18s | %-18s | %-18s | %-14d | %-12.2f |\n", 
                      product.getId(), 
                      product.getName(), 
                      product.getBrandId(), 
                      product.getCategoryId(), 
                      product.getModelYear(), 
                      product.getListPrice());
}

// Print the footer
        System.out.printf("+--------------+--------------------+--------------------+--------------------+----------------+--------------+\n");

        
    }
    
    Utils.pressEnterToContinue();
}
            
    @Override
  public void add() {
    int getchoice = 9; // biến này để làm cái thăm dò xem có muốn nhập tiêp cai khác ko , nếu nhập 1 thì có , nhập 0 thì thoát ra menu chính luôn
    do {
        // Load the highest ID once when the program starts
        if (highestId == 0) {  
            loadHighestId();
        }

        // Generate unique product ID based on the highest ID
        String id = "P" + (++highestId);  // tăng highestId mỗi lần thêm sản phẩm mới

        // Get Product Name with validation
        String name = Utils.getString("Enter Product Name: ");

        // Validate Brand ID (check if it exists in Brand.txt)
        int brandId;
        do {
            brandId = Utils.getInt("Enter Brand ID: ", 1, Integer.MAX_VALUE);
            if (!Utils.validateBrandId(brandId)) {
                System.out.println("Brand ID does not exist in Brand.txt. Please try again.");
            }
        } while (!Utils.validateBrandId(brandId));

        // Validate Category ID (check if it exists in Category.txt)
        int categoryId;
        do {
            categoryId = Utils.getInt("Enter Category ID: ", 1, Integer.MAX_VALUE);
            if (!Utils.validateCategoryId(categoryId)) {
                System.out.println("Category ID does not exist in Category.txt. Please try again.");
            }
        } while (!Utils.validateCategoryId(categoryId));

        // Validate Model Year (must be between 1900 and current year)
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        int modelYear = Utils.getInt("Enter Model Year: ", 1900, currentYear);

        // Validate List Price (positive number)
        double listPrice = Utils.getDouble("Enter List Price: ", 1, Integer.MAX_VALUE);

        // Create and add new product
        Bicycle newProduct = new Bicycle(id, name, brandId, categoryId, modelYear, listPrice);
        this.add(newProduct);

        System.out.println("Product added successfully.");

        // Save to file
        saveToFile();

        // Ask if user wants to add more products
        getchoice = Utils.getInt("Do you want to add more products? (yes: 1, no: 0)", 0, 1);
        if (getchoice == 0) {
            System.out.println("----------Back to main menu!!!-------------");
        }

    } while (getchoice != 0); // Do while loop to continue or stop
}



    @Override
    public void remove() {
        loadFromFile(); // Load products from file before removing
        String productId = Utils.getString("Enter Product ID: ");
        Bicycle product = findById(productId);

        if (product == null) {
            System.out.println("Product does not exist.");
        } else {
            boolean confirm = Utils.confirmYesNo("Are you sure you want to delete this product? (Y/N)");
            if (confirm) {
                this.remove(product);
                saveToFile(); // Save to file after deletion
                System.out.println("Product deleted successfully.");
            } else {
                System.out.println("Deletion cancelled.");
            }
        }

        Utils.pressEnterToContinue();
    }

    @Override
    public void update() {
    loadFromFile(); // Load products from file before updating
    String productId = Utils.getString("Enter Product ID: ");
    Bicycle product = findById(productId);

    if (product == null) {
        System.out.println("Product does not exist.");
    } else {
        String newName = Utils.getString("Enter new Product Name (leave blank to keep unchanged): ", product.getName());
        int brandId = Utils.getInt("Enter new Brand ID (or 0 to keep unchanged): ", 0, Integer.MAX_VALUE);
        int categoryId = Utils.getInt("Enter new Category ID (or 0 to keep unchanged): ", 0, Integer.MAX_VALUE);
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        int modelYear = Utils.getInt("Enter new Model Year (or 0 to keep unchanged): ", 0, currentYear);
        double listPrice = Utils.getDouble("Enter new List Price (or 0 to keep unchanged): ", 0.0, Double.MAX_VALUE); 

        // Only update the fields if valid values are provided
        product.setName(newName); // Name is already handled by Utils.getString to retain old name if blank
        if (brandId != 0 && Utils.validateBrandId(brandId)) product.setBrandId(brandId);
        if (categoryId != 0 && Utils.validateCategoryId(categoryId)) product.setCategoryId(categoryId);
        if (modelYear != 0) product.setModelYear(modelYear);
        if (listPrice > 0) product.setListPrice(listPrice); // List price won't be 0 because of validation

        saveToFile(); // Save to file after updating
        System.out.println("Product updated successfully.");
    }

    Utils.pressEnterToContinue();
}


    @Override
    public void sort() {
        loadFromFile(); // Load products before sorting
        // Sort by list price descending, and if equal, by name ascending
        this.sort((p1, p2) -> {
            int priceComparison = Double.compare(p2.getListPrice(), p1.getListPrice());
            return priceComparison != 0 ? priceComparison : p1.getName().compareTo(p2.getName());
        });
        saveToFile(); // Save sorted products to file
    }

    @Override
    public void output() {
        

        // Sort products
//        sort();

        // Display products
        System.out.printf("+--------------+--------------------+--------------------+--------------------+----------------+--------------+\n");
System.out.printf("| %-12s | %-18s | %-18s | %-18s | %-14s | %-12s |\n", 
                  "PRODUCT ID", "NAME", "BRAND", "CATEGORY", "MODEL YEAR", "LIST PRICE");
System.out.printf("+--------------+--------------------+--------------------+--------------------+----------------+--------------+\n");

// Print each product
    for (Bicycle product : this) {
    System.out.printf("| %-12s | %-18s | %-18s | %-18s | %-14d | %-12.2f |\n", 
                      product.getId(), 
                      product.getName(), 
                      product.getBrandId(), 
                      product.getCategoryId(), 
                      product.getModelYear(), 
                      product.getListPrice());
}

// Print the footer
System.out.printf("+--------------+--------------------+--------------------+--------------------+----------------+--------------+\n");




        Utils.pressEnterToContinue();
    }

    private Bicycle findById(String id) {
        for (Bicycle product : this) {
            if (product.getId().equalsIgnoreCase(id)) {
                return product;
            }
        }
        return null;
    }

    public void saveToFile() {
        List<String> lines = new ArrayList<>();
        for (Bicycle product : this) {
            String line = product.getId() + "," + product.getName() + "," + product.getBrandId() + ","
                    + product.getCategoryId() + "," + product.getModelYear() + "," + product.getListPrice();
            lines.add(line);
        }
        FileUtils.overwriteFile(PRODUCT_FILE_PATH, lines);
        System.out.println("Products saved to file successfully.");
    }

    public void loadFromFile() {
        this.clear();
        List<String> lines = FileUtils.readFile(PRODUCT_FILE_PATH);
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 6) {
                String id = parts[0];
                String name = parts[1];
                int brandId = Integer.parseInt(parts[2]);
                int categoryId = Integer.parseInt(parts[3]);
                int modelYear = Integer.parseInt(parts[4]);
                double listPrice = Double.parseDouble(parts[5]);

                Bicycle product = new Bicycle(id, name, brandId, categoryId, modelYear, listPrice);
                this.add(product);
            }
        }
        System.out.println("Products loaded from file successfully.");
    }
}