package model;

import java.util.List;

public final class User {
    private String id;
    private String firstName;
    private String lastName;   
    private int age;
    private String work;
    private String city;
    private List<BankAccount> bankAccounts;


    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void addCard(BankAccount account){
        this.bankAccounts.add(account);
    }

    public void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public String getWork() { return work; }

    public void setWork(String work) { this.work = work; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public User() {
    }

    public User(String firstName, String lastName, int age, String work, String city,List<BankAccount> accouns) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.work = work;
        this.city = city;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
