package com.example.myapplication;

public class fetchuserdata {
    String userid;
    String fname ;
    String lname;
    String email;
    String mobileno;
    String address;
    String forpost;
    String doj;

    public fetchuserdata(String userid ,String fname, String lname, String email, String mobileno, String address, String forpost, String doj) {
        this.userid=userid;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.mobileno = mobileno;
        this.address = address;
        this.forpost = forpost;
        this.doj = doj;
    }

    public String getUserid() {return userid; }

    public void setUserid(String userid) {this.userid = userid;}

    public String getFname() {

        return fname ;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getForpost() {
        return forpost;
    }

    public void setForpost(String forpost) {
        this.forpost = forpost;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }
}
