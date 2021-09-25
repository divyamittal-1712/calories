package com.exam.calorie.model;

public class CaloriesInModel {
    String name,quantity,meal;

    public CaloriesInModel(String name, String quantity, String meal) {
        this.name = name;
        this.quantity = quantity;
        this.meal = meal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }
}
