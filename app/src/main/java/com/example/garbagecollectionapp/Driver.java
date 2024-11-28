package com.example.garbagecollectionapp;

public class Driver {

    String Name,Mobileno,Address,expe,lati,longi,area,imageurl;

    public Driver() {
    }

    public Driver(String name, String mobileno, String address, String expe, String lati, String longi, String area, String imageurl) {
        Name = name;
        Mobileno = mobileno;
        Address = address;
        this.expe = expe;
        this.lati = lati;
        this.longi = longi;
        this.area = area;
        this.imageurl = imageurl;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMobileno() {
        return Mobileno;
    }

    public void setMobileno(String mobileno) {
        Mobileno = mobileno;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getExpe() {
        return expe;
    }

    public void setExpe(String expe) {
        this.expe = expe;
    }

    public String getLati() {
        return lati;
    }

    public void setLati(String lati) {
        this.lati = lati;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
