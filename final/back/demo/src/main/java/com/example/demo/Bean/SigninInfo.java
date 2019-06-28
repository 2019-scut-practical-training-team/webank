package com.example.demo.Bean;

public class SigninInfo {
    private boolean check;
    private String address;
    private int identity;

    public SigninInfo(boolean check, String address, int identity) {
        this.check = check;
        this.address = address;
        this.identity = identity;
    }

    public SigninInfo() {
    }


    public void setCheck(boolean check) {
        this.check = check;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public boolean isCheck() {
        return check;
    }

    public String getAddress() {
        return address;
    }

    public int getIdentity() {
        return identity;
    }
}