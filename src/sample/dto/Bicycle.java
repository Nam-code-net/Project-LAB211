package sample.dto;


public class Bicycle {
    private String id;
    private String name;
    private int brandId;
    private int categoryId;
    private int modelYear;
    private double listPrice;

    public Bicycle() {
    }

    public Bicycle(String id, String name, int brandId, int categoryId, int modelYear, double listPrice) {
        this.id = id;
        this.name = name;
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.modelYear = modelYear;
        this.listPrice = listPrice;
    }

    

    // Constructors

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBrandId() {
        return brandId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getModelYear() {
        return modelYear;
    }

    public double getListPrice() {
        return listPrice;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
    }

     @Override
    public String toString() {
        return id + "," + name + "," + brandId + "," + categoryId + "," + modelYear + "," + listPrice;
    }
   
}

