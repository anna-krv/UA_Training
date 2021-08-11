package ua.finalproject.periodicals.exception;

public class MoneyAccountException extends Exception {
    public MoneyAccountException(Long id) {
        super("account with id " + id + ": not enough money");
    }

}
