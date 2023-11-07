package com.example.demo.beans;

public class User {
    private Long id;
    private String username;
    private String password;
    private String fullname;
    private int gender;
    private String phoneNumber;
    private String email;
    private String cccd;
    private String role;
    private String imageName;
    private String bankAccount;
    private String nameBankAccount;
    private int valid;

    public User(Long id,
                String username,
                String password,
                String fullname,
                int gender,
                String phoneNumber,
                String email,
                String cccd,
                String role,
                String imageName,
                String bankAccount,
                String nameBankAccount,
                int valid) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.cccd = cccd;
        this.role = role;
        this.imageName = imageName;
        this.bankAccount = bankAccount;
        this.nameBankAccount = nameBankAccount;
        this.valid = valid;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getNameBankAccount() {
        return nameBankAccount;
    }

    public void setNameBankAccount(String nameBankAccount) {
        this.nameBankAccount = nameBankAccount;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullname='" + fullname + '\'' +
                ", gender=" + gender +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", cccd='" + cccd + '\'' +
                ", role='" + role + '\'' +
                ", imageName='" + imageName + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", nameBankAccount='" + nameBankAccount + '\'' +
                ", valid=" + valid +
                '}';
    }
}
