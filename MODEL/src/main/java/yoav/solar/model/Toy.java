package yoav.solar.model;

public class Toy extends BaseEntity{
    private String name;
    private double price;
    private String CategoryID;
    private Long Date;
    private String Loation;
   // private String picture;

    public Toy() {}

    public Toy(String name, double price) {
        this.name = name;
        this.price = price;
        //this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public String getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(String categoryID) {
        CategoryID = categoryID;
    }

    public Long getDate() {
        return Date;
    }

    public void setDate(Long date) {
        Date = date;
    }

    public String getLoation() {
        return Loation;
    }

    public void setLoation(String loation) {
        Loation = loation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
/*
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

 */
}
