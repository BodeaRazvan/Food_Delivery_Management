package org.example.bll;

import java.io.Serializable;
import java.util.ArrayList;

public class CompositeProduct extends MenuItem implements Serializable {
    private int id;
    private String title;
    private double rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private int price;
    private ArrayList<BaseProduct> compositeProduct;

    public CompositeProduct(String title, ArrayList<BaseProduct> baseProducts){
        this.title=title;
        this.compositeProduct=baseProducts;
    }

    public CompositeProduct(){

    }

    public CompositeProduct(int id, String title, double rating, int calories, int protein, int fat, int sodium, int price, ArrayList<BaseProduct> compositeProduct) {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
        this.compositeProduct = compositeProduct;
    }

    @Override
    public int computePrice() {
        int price =0;
        for(BaseProduct baseProduct:compositeProduct){
            price+=baseProduct.getPrice();
        }
        return price;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    public ArrayList<BaseProduct> getArrayOfBaseProducts(){
        return this.compositeProduct;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public double getRating() {
        return rating;
    }

    @Override
    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public int getCalories() {
        return calories;
    }

    @Override
    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public int getProtein() {
        return protein;
    }

    @Override
    public void setProtein(int protein) {
        this.protein = protein;
    }

    @Override
    public int getFat() {
        return fat;
    }

    @Override
    public void setFat(int fat) {
        this.fat = fat;
    }

    @Override
    public int getSodium() {
        return sodium;
    }

    @Override
    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void setPrice(int price) {
        this.price = price;
    }

    public ArrayList<BaseProduct> getCompositeProduct() {
        return compositeProduct;
    }

    public void setCompositeProduct(ArrayList<BaseProduct> compositeProduct) {
        this.compositeProduct = compositeProduct;
    }
}
