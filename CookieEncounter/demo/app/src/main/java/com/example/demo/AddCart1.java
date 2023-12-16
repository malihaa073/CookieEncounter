package com.example.demo;

public class AddCart1 {
    String name,price, usrnm, fid, fqnt;

    public AddCart1(String name, String price, String usrnm, String fid, String fqnt) {
        this.name = name;
        this.price = price;
        this.usrnm = usrnm;
        this.fid = fid;
        this.fqnt = fqnt;
    }
    public AddCart1()
    {

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUsrnm() {
        return usrnm;
    }

    public void setUsrnm(String usrnm) {
        this.usrnm = usrnm;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFqnt() {
        return fqnt;
    }

    public void setFqnt(String fqnt) {
        this.fqnt = fqnt;
    }
}
