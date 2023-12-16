package com.example.demo;

public class Food_Model {
    String Image, Name, Price;

    public Food_Model(String Image, String Name, String Price) {
        this.Image = Image;
        this.Name = Name;
        this.Price = Price;

    }

    public Food_Model()
    {

    }
    public String getImage() {

        return Image;
    }

    public void setImage(String Image) {

        this.Image = Image;
    }

    public String getName() {

        return Name;
    }

    public void setName(String Name) {

        this.Name = Name;
    }

    public String getPrice() {

        return Price;
    }

    public void setPrice(String Price) {

        this.Price = Price;
    }
}
