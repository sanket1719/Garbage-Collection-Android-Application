package com.example.garbagecollectionapp;

public class Complaint {

    String name,number,address,des;

    public Complaint(String name, String number, String address, String des) {
        this.name = name;
        this.number = number;
        this.address = address;
        this.des = des;
    }

    public Complaint() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
