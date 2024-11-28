package com.example.garbagecollectionapp;

public class Feedback {

    String name,number,address,feedback;

    public Feedback(String name, String number, String address, String feedback) {
        this.name = name;
        this.number = number;
        this.address = address;

        this.feedback = feedback;
    }


    public Feedback() {
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



    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}

