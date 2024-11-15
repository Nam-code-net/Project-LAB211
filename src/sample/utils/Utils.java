
package sample.utils;

import java.util.List;
import java.util.Scanner;


public class Utils {

    public static String getString(String welcome) {
        boolean check = true;
        String result = "";
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print(welcome);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println("Input text!!!");
            } else {
                check = false;
            }
        } while (check);
        return result;
    }

    public static String getString(String welcome, String oldData) {
        String result = oldData;
        Scanner sc = new Scanner(System.in);
        System.out.print(welcome);
        String tmp = sc.nextLine();
        if (!tmp.isEmpty()) {
            result = tmp;
        }
        return result;
    }
    public static double getDouble(String prompt, double min, double max) {  // Check double , dùng cho nhập tiền $
        double number = 0;
        boolean isValid = false;

        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println(prompt);
                number = Double.parseDouble(sc.nextLine());

                if (number >= min && number < max) {
                    isValid = true;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        } while (!isValid);

        return number;
    }

    public static int getInt(String welcome, int min, int max) {
        boolean check = true;
        int number = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                check = false;
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number > max || number < min);
        return number;
    }

    public static int getInt(String welcome, int min, int max, int oldData) {
        boolean check = true;
        int number = oldData;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                String tmp = sc.nextLine();
                if (tmp.isEmpty()) {
                    check = false;
                } else {
                    number = Integer.parseInt(tmp);
                    check = false;
                }
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number > max || number < min);
        return number;
    }
    public static int getAnInteger(String inputMsg, String errorMsg, int min, int max) {
        int n;
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                if (n < min || n > max) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static boolean confirmYesNo(String welcome) {
        boolean result = false;
        String confirm = Utils.getString(welcome);
        if ("Y".equalsIgnoreCase(confirm)) {
            result = true;
        }
        return result;
    }
     public static boolean validateBrandId(int brandId) {
        // Read from Brand.txt and validate if the brandId exists
        List<String> brandList = FileUtils.readFile("Brand.txt");
        for (String brand : brandList) {
            String[] brandData = brand.split(",");
            if (Integer.parseInt(brandData[0]) == brandId) {
                return true;
            }
        }
        return false;
    }

    public static boolean validateCategoryId(int categoryId) {
        // Read from Category.txt and validate if the categoryId exists
        List<String> categoryList = FileUtils.readFile("Category.txt");
        for (String category : categoryList) {
            String[] categoryData = category.split(",");
            if (Integer.parseInt(categoryData[0]) == categoryId) {
                return true;
            }
        }
        return false;
    }
    public static String getCategoryNameById(int id) {
    List<String> lines = FileUtils.readFile("Category.txt");
    for (String line : lines) {
        String[] parts = line.split(",");
        if (parts.length >= 2) {
            int categoryId = Integer.parseInt(parts[0]);
            String categoryName = parts[1];
            if (categoryId == id) {
                return categoryName;
            }
        }
    }
    return "Unknown Category"; // Return a default value if the category is not found
}
    public static String getBrandNameById(int id) {
    List<String> lines = FileUtils.readFile("Brand.txt");
    for (String line : lines) {
        String[] parts = line.split(",");
        if (parts.length >= 2) {
            int brandId = Integer.parseInt(parts[0]);
            String brandName = parts[1];
            if (brandId == id) {
                return brandName;
            }
        }
    }
    return "Unknown Brand"; // Return a default value if the brand is not found
}

    public static void pressEnterToContinue() {
    System.out.println("Press Enter to continue...");
    try {
        System.in.read();  // Wait for the user to press Enter
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
}
