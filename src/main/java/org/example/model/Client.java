package org.example.model;

public class Client {
    private int id;
    private String username;
    private String password;
    private String email;
    private String address;

    public Client(){
    }

    public Client(String username, String password, String email, String address){
        this.username=username;
        this.password=password;
        this.email=email;
        this.address=address;
    }

    public Client(int id,String username, String password, String email, String address){
        this.id=id;
        this.username=username;
        this.password=password;
        this.email=email;
        this.address=address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
