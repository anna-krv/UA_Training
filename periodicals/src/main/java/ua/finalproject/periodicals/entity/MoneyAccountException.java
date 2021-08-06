package ua.finalproject.periodicals.entity;

public class MoneyAccountException extends Exception {
    public String toString() {
        return "Problems with user account. Probably, should check the balance.";
    }
}
