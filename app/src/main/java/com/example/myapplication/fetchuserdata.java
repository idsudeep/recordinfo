package com.example.myapplication;

public class fetchuserdata {
    String name;
    String email;
    String address;
    String mobileno;
    String doj;
    String post;

    public fetchuserdata(String name, String email, String address, String mobileno, String doj, String post) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.mobileno = mobileno;
        this.doj = doj;
        this.post = post;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}
