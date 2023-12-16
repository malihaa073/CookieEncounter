package com.example.demo;

public class FinalCartItem {
    public String name, price, usrnm, location, instruction, bname, phn;

    public FinalCartItem(String name, String price, String usrnm) {
        this.name = name;
        this.price = price;
        this.usrnm = usrnm;
    }
    public FinalCartItem(String name, String price, String usrnm, String location, String instruction, String bname, String phn) {
        this.name = name;
        this.price = price;
        this.usrnm = usrnm;
        this.location = location;
        this.instruction= instruction;
        this.bname = bname;
        this.phn = phn;
    }

    public FinalCartItem() {
    }
}
