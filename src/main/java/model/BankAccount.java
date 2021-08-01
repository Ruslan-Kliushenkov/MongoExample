package model;

public class BankAccount {
    private String bankService;
    private String number;
    private String userId;

    public BankAccount(String bankService, String number, String userId) {
        this.bankService = bankService;
        this.number = number;
        this.userId = userId;
    }

    public BankAccount() {
    }


    public String getBankService() {
        return bankService;
    }

    public void setBankService(String bankService) {
        this.bankService = bankService;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
