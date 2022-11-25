package com.example.mydrinkinggame;

public class User {
    private String email;
    private String password;
    private String Name;
    private String bottle;

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getBottle(){
        return bottle;
    }
    public void setBottle(String bottle){
        this.bottle=bottle;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
