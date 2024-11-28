package com.example.garbagecollectionapp;

public class Request {


    String Name,Mobileno,Address,area,gtype,lati,longi,imageurl;

    public Request() {
    }

    public Request(String name, String mobileno, String address, String area, String gtype, String lati, String longi, String imageurl) {
        Name = name;
        Mobileno = mobileno;
        Address = address;
        this.area = area;
        this.gtype = gtype;
        this.lati = lati;
        this.longi = longi;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getGtype() {
        return gtype;
    }

    public void setGtype(String gtype) {
        this.gtype = gtype;
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

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
